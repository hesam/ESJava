package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.AbstractCollection;
import java.util.Iterator;
import java.io.CharArrayWriter;

public class LogRelation extends Hashtable {

    static int RelCtr = 0;

    // id instVar domain range fixedSize isUnknown isaCollection isaCollectionInstVar subRels
    protected String id;
    protected String instVar;
    protected Class domain;
    protected Class range;
    protected ArrayList subRels;
    protected int fixedSize;
    protected boolean isUnknown;
    protected boolean isaCollection;
    protected boolean isaCollectionInstVar;
    protected boolean isRangeEnum;

    public LogRelation(String instVar, Class domain, Class range) {
	this(instVar, domain, range, false, false, 0, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaCollection){
	this(instVar, domain, range, isaCollection, false, 0, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaCollection, boolean isUnknown){
	this(instVar, domain, range, isaCollection, isUnknown, 0, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaCollection, boolean isUnknown, int fixedSize) {
	this(instVar, domain, range, isaCollection, isUnknown, fixedSize, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaCollection, 
		       boolean isUnknown, int fixedSize, boolean isaCollectionInstVar) {
	super();
	this.instVar = instVar;
	this.domain = domain;
	this.range = range;
	this.fixedSize = fixedSize;
	this.isUnknown = isUnknown;
	this.isaCollection = isaCollection;
	this.isaCollectionInstVar = isaCollectionInstVar;
	this.id = "r" + this.RelCtr++;
	this.isRangeEnum = range.isEnum();
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
    public boolean isaCollection() { return isaCollection; }
    public boolean isaCollectionInstVar() { return isaCollectionInstVar; }
    public boolean isRangeEnum() { return isRangeEnum; }
    public boolean hasFixedSize() { return fixedSize != 0; }
    public void fixedSize(int s) { fixedSize = s; }
    public void incrFixedSize() { fixedSize++; }

    public boolean isModifiable(HashMap modifiableFields) {
	return modifiableFields == null ? true : 
	    modifiableFields.containsKey(domain.getName() + "." + instVar);
    }

    public String domain_log() { 
	return isaCollection ? ESJInteger.zeroTo_log(fixedSize).string() : LogMap.bounds_log(domain, false, false).string();
    }

    public String range_log(boolean isBoundsDef) { 
	// have to add 'null' to the set of possible values for the ref field
	return LogMap.bounds_log(range, !isRangeEnum, isBoundsDef).string();
    }

    public String fullDomainRange() {
	return domain_log() + "->" + (isaCollectionInstVar ? listInstVarDomain_log() : "") + range_log(true);
    }

    // FIXME
    public String listInstVarDomain_log() {
	return ESJInteger.zeroTo_log(fixedSize-1).string();
    }
   
    public void put_log(Object key, Object value) {
	if (LogMap.SolverOpt_debug1())
	    System.out.println("trying put key " + key + " value " + value + " ");
	if (value instanceof AbstractCollection) {
	    ArrayList l = new ArrayList();
	    for(Object v : (AbstractCollection) value) {
		l.add(LogMap.get1(v));
	    }
	    put(LogMap.get1(key), l);
	} else {
	    put(LogMap.get1(key), LogMap.get1(value));
	}
	if (LogMap.SolverOpt_debug1())
	    System.out.println("id: " + id + " contents: " + this);
    }

    /*
    public void atIntIdxPut_log(int index, Object value) {
	put_log(index,value);
    }
    */

    public String lowerBound_log() {
	CharArrayWriter o = new CharArrayWriter();
	o.append("{");
	Set s = keySet();
	Iterator itr = s.iterator();
	for (int i=0;i<s.size()-1;i++) {
	    Object k = itr.next();
	    Object v = get(k);
	    if (v instanceof ArrayList) {
		for(Object e : (ArrayList) v)
		    o.append("[A" + k + ", A" + e + "], ");
	    } else
		o.append("[A" + k + ", A" + v + "], ");
	}
	if (itr.hasNext()) {
	    Object k = itr.next();
	    Object v = get(k);
	    if (v instanceof ArrayList) {
		ArrayList lv = (ArrayList) v;
		int lvs = lv.size() - 1;
		for(int c = 0; c < lvs; c++) {
		    o.append("[A" + k + ", A" + lv.get(c) + "],");
		}
		o.append("[A" + k + ", A" + lv.get(lvs) + "]");
	    } else
		o.append("[A" + k + ", A" + v + "]");
	}
	o.append("}");
	return o.toString();
    }

    public String log() {
	String lower = lowerBound_log();
	if (isUnknown()) {
	    CharArrayWriter o = new CharArrayWriter();
	    o.append("[" + lower + ", " + fullDomainRange() + "]");
	    if (isaCollectionInstVar) {
		for (LogRelation s : (ArrayList<LogRelation>) subRels) {
		    o.append(" bounds " + s + ": [{}, " +  listInstVarDomain_log() + "->" + range() + "] ");
		}
	    }
	    return o.toString();
	}
	return lower;
    }

    public String funDef_log() {
	CharArrayWriter o = new CharArrayWriter();
	String r = range_log(false);
	if (isaCollectionInstVar) {
	    String d = listInstVarDomain_log();	    
	    for (LogRelation s : (ArrayList<LogRelation>) subRels) {
		o.append("FUNCTION(" + s.id() + ", " + d + "->one " + r + ") && ");
	    }
	} else {
	    String d = domain_log();	    
	    o.append("FUNCTION(" + id + ", " + d + "->one " + r + ") && ");
	}
	return o.toString();
    }

}
