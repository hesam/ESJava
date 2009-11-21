package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.Iterator;
import java.io.CharArrayWriter;

public class LogRelation extends Hashtable {

    static int RelCtr = 0;

    protected String id;
    protected String instVar;
    protected Class domain;
    protected String domainName;
    protected Class range;
    protected Class indexingDomain;
    protected ArrayList subRels;
    protected int fixedSize;
    protected boolean isResultVar;
    protected boolean isUnknown;
    protected boolean isaCollectionInstVar;
    protected boolean isaList, isaListInstVar, isaMapInstVar;
    protected boolean isRangeEnum;

    public LogRelation(String instVar, Class domain, Class range) {
	this(instVar, domain, range, null, false, false, false, false, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, boolean isaListInstVar, boolean isaMapInstVar){
	this(instVar, domain, range, indexingDomain, isaListInstVar, isaMapInstVar, false, false, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, boolean isaListInstVar, boolean isaMapInstVar, boolean isUnknown){
	this(instVar, domain, range, indexingDomain, isaListInstVar, isaMapInstVar, isUnknown, false, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, boolean isaListInstVar, boolean isaMapInstVar, boolean isUnknown, boolean isResultVar){
	this(instVar, domain, range, indexingDomain, isaListInstVar, isaMapInstVar, isUnknown, isResultVar, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, 
		       boolean isaListInstVar, boolean isaMapInstVar, boolean isUnknown, 
		       boolean isResultVar, boolean isaList, int fixedSize) {
	super();
	this.instVar = instVar;
	this.domain = domain;
	String longName = domain.getName();
	int pi = longName.lastIndexOf(46); // char '.'
	if (pi == -1) {
	    this.domainName = longName;
	} else {
	    this.domainName = longName.substring(pi+1);
	}
	this.range = range;
	this.indexingDomain = indexingDomain;
	this.fixedSize = fixedSize;
	this.isUnknown = isUnknown;
	this.isResultVar = isResultVar;
	this.isaListInstVar = isaListInstVar;
	this.isaMapInstVar = isaMapInstVar;
	this.isaCollectionInstVar = isaListInstVar || isaMapInstVar;
	this.isaList = isaList;
	this.id = (isaCollectionInstVar ? "m3_" : (isResultVar ? "s" : "r")) + this.RelCtr++;
	this.isRangeEnum = range.isEnum();
	if (isaCollectionInstVar && isUnknown) {
	    this.subRels = new ArrayList();	    
	    this.subRels.add(new LogRelation(instVar + "_subrel", indexingDomain, range, null, false, false, true, false));
	}
	if (LogMap.SolverOpt_debug1())
	    System.out.println("new relation " + this.id + " " + instVar + " old: " + !isUnknown);
    }

    public String instVar() { return instVar; }
    //public String id() { return id; }
    public String id() { LogMap.addAsProblemRel(this,id); return id; }
    public Class domain() { return domain; }
    public Class range() { return range; }
    public ArrayList subRels() { return subRels; }
    public int fixedSize() { return fixedSize; }
    public boolean isUnknown() { return isUnknown; }
    public boolean isaCollectionInstVar() { return isaCollectionInstVar; }
    public boolean isaListInstVar() { return isaListInstVar; }
    public boolean isaMapInstVar() { return isaMapInstVar; }
    public boolean isResultVar() { return isResultVar; }
    public boolean isRangeEnum() { return isRangeEnum; }
    public boolean hasFixedSize() { return fixedSize != 0; }
    public void fixedSize(int s) { fixedSize = s; }
    public void incrFixedSize() { fixedSize++; }
    public void range(Class range) { this.range = range; }

    public boolean isModifiable(HashMap modifiableFields) {
	return modifiableFields == null || isResultVar ||
	    modifiableFields.containsKey(domainName + "." + instVar);
    }

    public String domain_log() { 
	//return isaCollectionInstVar ? ESJInteger.zeroTo_log(fixedSize).string() : LogMap.bounds_log(domain, false, false).string();
	return isaList ? ESJInteger.zeroTo_log(fixedSize).string() : LogMap.bounds_log(domain, false, false).string();
    }

    public String range_log(boolean isBoundsDef, Class resultVarType) { 
	// have to add 'null' to the set of possible values for the ref field
	return LogMap.bounds_log(isResultVar ? resultVarType : range, !isRangeEnum, isBoundsDef).string();
    }

    public String fullDomainRange(Class resultVarType) {
	return (isResultVar ? "" : domain_log() + "->") + (isaCollectionInstVar ? listInstVarDomain_log() : "") + range_log(true, resultVarType);
    }

    // FIXME
    public String listInstVarDomain_log() {
	return (fixedSize > 0 ? 
		ESJInteger.zeroTo_log(fixedSize-1).string() : 
		LogMap.bounds_log(indexingDomain, false, false).string()) + "->";
    }
   
    // FIXME
    public void put_log(Object key, Object value) {
	if (LogMap.SolverOpt_debug2())
	    System.out.println("rel" + id + " trying put key " + key + " value " + value + " ");
	if (value instanceof AbstractCollection) {
	    ArrayList l = new ArrayList();
	    for(Object v : (AbstractCollection) value) {
		l.add(LogMap.get1(v));
	    }
	    put(LogMap.get1(key), l);
	} else if (value instanceof AbstractMap) {
	    HashMap map = (HashMap) value;
	    HashMap l = new HashMap();
	    for(Object v : map.keySet()) {
		l.put(LogMap.get1(v),LogMap.get1(map.get(v)));
	    }
	    put(LogMap.get1(key), l);
	} else {
	    put(LogMap.get1(key), LogMap.get1(value));
	}
	if (LogMap.SolverOpt_debug2())
	    System.out.println("id: " + id + " contents: " + this);
    }

    /*
    public void atIntIdxPut_log(int index, Object value) {
	put_log(index,value);
    }
    */

    public String lowerBound_log(HashSet<?> modifiableObjects) {
	LogRelation r = this;
	boolean filterObjects = false;
	Set s = r.keySet();
	if (isUnknown) {
	    if (modifiableObjects == null)
		return "{}";	    
	    filterObjects = true;
	    r = LogMap.instVarRelOld_log(this);
	    HashSet sNew = new HashSet();
	    System.out.println("howdy contents: " + r + " " + modifiableObjects);
	    for (Object k : r.keySet())
		if (!modifiableObjects.contains(k))
		    sNew.add(k);
	    s = sNew;
	}
	CharArrayWriter o = new CharArrayWriter();
	o.append("{");
	Iterator itr = s.iterator();
	for (int i=0;i<s.size()-1;i++) {
	    Object k = itr.next();
	    Object v = r.get(k);
	    if (v instanceof ArrayList) {
		if (isaCollectionInstVar) {
		    ArrayList lv = (ArrayList) v;
		    int lvs = lv.size() - 1;
		    for(int c = 0; c <= lvs; c++)
			o.append("[A" + k + ", A" + ESJInteger.log(c) + ", A" + lv.get(c) + "], ");
		} else {
		    for(Object e : (ArrayList) v)
			o.append("[A" + k + ", A" + e + "], ");
		}
	    } else if (v instanceof HashMap) {
		HashMap lv = (HashMap) v;
		ArrayList keys = new ArrayList(lv.keySet());
		int lvs = keys.size() - 1;
		for(int c = 0; c <= lvs; c++) {
		    Object theKey = keys.get(c);
		    o.append("[A" + k + ", A" + theKey + ", A" + lv.get(theKey) + "], ");
		}
	    } else
		o.append("[A" + k + ", A" + v + "], ");
	}
	if (itr.hasNext()) {
	    Object k = itr.next();
	    Object v = r.get(k);
	    if (v instanceof ArrayList) {
		ArrayList lv = (ArrayList) v;
		int lvs = lv.size() - 1;
		if (isaCollectionInstVar) {
		    for(int c = 0; c < lvs; c++)
			o.append("[A" + k + ", A" + ESJInteger.log(c) + ", A" + lv.get(c) + "],");
		    o.append("[A" + k + ", A" + ESJInteger.log(lvs) + ", A" + lv.get(lvs) + "]");
		} else {
		    for(int c = 0; c < lvs; c++) {
			o.append("[A" + k + ", A" + lv.get(c) + "],");
		    }
		    o.append("[A" + k + ", A" + lv.get(lvs) + "]");
		}
	    } else if (v instanceof HashMap) {
		HashMap lv = (HashMap) v;
		ArrayList keys = new ArrayList(lv.keySet());
		int lvs = keys.size() - 1;
		for(int c = 0; c < lvs; c++) {
		    Object theKey = keys.get(c);
		    o.append("[A" + k + ", A" + theKey + ", A" + lv.get(theKey) + "],");
		}
		Object theKey = keys.get(lvs);
		o.append("[A" + k + ", A" + theKey + ", A" + lv.get(theKey) + "]");
	    } else
		o.append("[A" + k + ", A" + v + "]");
	}
	o.append("}");
	return o.toString();
    }

    public String log(HashSet<?> modifiableObjects, Class resultVarType) {
	String lower = lowerBound_log(modifiableObjects);
	if (isUnknown()) {
	    CharArrayWriter o = new CharArrayWriter();
	    o.append("[" + lower + ", " + fullDomainRange(resultVarType) + "]");
	    if (isaCollectionInstVar)
		for (LogRelation s : (ArrayList<LogRelation>) subRels)
		    o.append("\nbounds " + s.id() + ": [{}, " +  listInstVarDomain_log() + range_log(false, null) + "] ");
	    return o.toString();
	}
	return lower;
    }

    public String funDef_log() {
	if (isResultVar)
	    return "one " + id() + " && ";
	CharArrayWriter o = new CharArrayWriter();
	String r = range_log(false, null);
	if (isaCollectionInstVar) {
	    String d = listInstVarDomain_log();	    
	    for (LogRelation s : (ArrayList<LogRelation>) subRels)
		o.append("FUNCTION(" + s.id() + ", " + d + "one " + r + ") && (" + 
			 domain_log() + "." + id + " = " + s.id() + ") && ");
	} else {
	    String d = domain_log();	    
	    o.append("FUNCTION(" + id + ", " + d + "->one " + r + ") && ");
	}
	return o.toString();
    }

}
