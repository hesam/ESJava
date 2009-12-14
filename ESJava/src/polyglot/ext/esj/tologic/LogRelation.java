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

import kodkod.ast.Relation;
import kodkod.ast.Expression;
import kodkod.ast.Formula;
import kodkod.instance.Bounds;
import kodkod.instance.TupleSet;
import kodkod.instance.TupleFactory;



public class LogRelation extends Hashtable {

    static int RelCtr = 0;

    protected String id;
    protected Relation kodkodRel;
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
	this(instVar, domain, range, null, null, false, false, false, false, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, 
		       Relation kodkodRel, boolean isaListInstVar, boolean isaMapInstVar){
	this(instVar, domain, range, indexingDomain, kodkodRel, isaListInstVar, isaMapInstVar, false, false, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, 
		       Relation kodkodRel, boolean isaListInstVar, boolean isaMapInstVar, 
		       boolean isUnknown){
	this(instVar, domain, range, indexingDomain, kodkodRel, isaListInstVar, isaMapInstVar, isUnknown, false, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, 
		       Relation kodkodRel, boolean isaListInstVar, boolean isaMapInstVar, 
		       boolean isUnknown, boolean isResultVar){
	this(instVar, domain, range, indexingDomain, kodkodRel, isaListInstVar, isaMapInstVar, isUnknown, isResultVar, false, 0);
    }

    public LogRelation(String instVar, Class domain, Class range, Class indexingDomain, 
		       Relation kodkodRel, boolean isaListInstVar, boolean isaMapInstVar, 
		       boolean isUnknown, boolean isResultVar, boolean isaList, int fixedSize) {
	super();
	this.instVar = instVar;
	this.domain = domain;
	this.domainName = LogMap.shortClassName(domain);
	this.range = range;
	this.indexingDomain = indexingDomain;
	this.fixedSize = fixedSize;
	this.isUnknown = isUnknown;
	this.isResultVar = isResultVar;
	this.kodkodRel = kodkodRel;
	this.isaListInstVar = isaListInstVar;
	this.isaMapInstVar = isaMapInstVar;
	this.isaCollectionInstVar = isaListInstVar || isaMapInstVar;
	this.isaList = isaList;
	this.id = isResultVar ? "s0" : ((isaCollectionInstVar ? "m3_" : "r") + this.RelCtr++);
	this.isRangeEnum = range.isEnum();
	if (isaCollectionInstVar && isUnknown) {
	    this.subRels = new ArrayList();
	    String sRelN = instVar + "_subrel";
	    Relation r2 = Relation.nary(sRelN, 2);
	    this.subRels.add(new LogRelation(sRelN, indexingDomain, range, null, r2, false, false, true, false));
	}
	if (LogMap.SolverOpt_debug1())
	    System.out.println("new relation " + this.id + " " + instVar + " old: " + !isUnknown);
    }

    public String instVar() { return instVar; }
    public String id() { LogMap.addAsProblemRel(this,id); return id; }
    public String getId() { return id; }
    public Relation kodkodRel() { LogMap.addAsProblemRel(this,id); return kodkodRel; }
    public Relation getKodkodRel() { return kodkodRel; }
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
    public void kodkodRel(Relation rel) { this.kodkodRel = rel; }
    public static int RelCtr() { return RelCtr; }

    public boolean isModifiable(HashMap modifiableFields) {
	return modifiableFields == null || isResultVar ||
	    modifiableFields.containsKey(domainName + "." + instVar);
    }

    public String domain_log() { 
	//return isaCollectionInstVar ? ESJInteger.zeroTo_log(fixedSize).string() : LogMap.bounds_log(domain, false, false).string();
	return isaList ? ESJInteger.zeroTo_log(fixedSize).string() : LogMap.bounds_log(domain, false, false).string();
    }

    public Relation domain_log2() { 
	return isaList ? (Relation) ESJInteger.zeroTo_log2(fixedSize).expression()  : 
	    LogMap.bounds_log2(domain, false);
    }

    public String range_log(boolean isBoundsDef, Class resultVarType) { 
	// have to add 'null' to the set of possible values for the ref field
	return LogMap.bounds_log(isResultVar ? resultVarType : range, !isRangeEnum, isBoundsDef).string();
    }

    public Relation range_log2(boolean isBoundsDef, Class resultVarType) { 
	// have to add 'null' to the set of possible values for the ref field
	return LogMap.bounds_log2(isResultVar ? resultVarType : range, isBoundsDef);
    }

    public Expression rangePlusNull_log2(boolean isBoundsDef, Class resultVarType, HashSet<?> modifiableObjects) { 
	Expression res = range_log2(isBoundsDef, resultVarType);
	boolean addNull = !(isRangeEnum || range == Integer.class);
	return addNull ? res.union(LogMap.null_log2()) : res;
    }

    public String fullDomainRange_log(Class resultVarType) {
	return (isResultVar ? "" : domain_log() + "->") + (isaCollectionInstVar ? listInstVarDomain_log() : "") + range_log(true, resultVarType);
    }

    public TupleSet fullDomainRange_log2(Class resultVarType, HashSet<?> modifiableObjects, TupleSet lower) {
	TupleSet dB;
	if (modifiableObjects == null) {
	    Relation d = domain_log2();
	    dB = LogMap.ProblemBounds.upperBound(d);
	} else
	    dB = LogMap.tupleSetBounds_log2(domain, false, modifiableObjects);

	boolean addNull = !(isRangeEnum || range == Integer.class);
	TupleSet rB;
	if (addNull)
	    rB = LogMap.tupleSetBounds_log2(range, true, null);
	else {
	    Relation r = range_log2(true, resultVarType);
	    rB = LogMap.ProblemBounds.upperBound(r);
	}
	if (isaCollectionInstVar)
	    dB = dB.product(LogMap.ProblemBounds.upperBound(listInstVarDomain_log2()));

	TupleSet res = isResultVar ? rB : dB.product(rB);
	if (modifiableObjects != null && lower != null)
	    res.addAll(lower);
	return res;
    }

    // FIXME
    public String listInstVarDomain_log() {
	return (fixedSize > 0 ? 
		ESJInteger.zeroTo_log(fixedSize-1).string() : 
		LogMap.bounds_log(indexingDomain, false, false).string()) + "->";
    }

    public Relation listInstVarDomain_log2() {
	return LogMap.bounds_log2(indexingDomain, false);
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
	// FIXME:
	if (isResultVar)
	    return null;
	LogRelation r = this;
	boolean filterObjects = false;
	Set s = r.keySet();
	if (isUnknown) {
	    if (modifiableObjects == null)
		return "{}";	    
	    filterObjects = true;
	    r = LogMap.instVarRelOld_log(this);
	    HashSet sNew = new HashSet();
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

    public TupleSet lowerBound_log2(HashSet<?> modifiableObjects) {
	// FIXME:
	if (isResultVar)
	    return null;
	LogRelation r = this;
	boolean filterObjects = false;
	Set s = r.keySet();
	if (isUnknown) {
	    if (modifiableObjects == null)
		return null;	    
	    filterObjects = true;
	    r = LogMap.instVarRelOld_log(this);
	    HashSet sNew = new HashSet();
	    for (Object k : r.keySet())
		if (!modifiableObjects.contains(k))
		    sNew.add(k);
	    s = sNew;
	}

	TupleFactory factory = LogMap.ProblemFactory();
	TupleSet lower = factory.noneOf(kodkodRel.arity());

	CharArrayWriter o = new CharArrayWriter();
	o.append("{");
	Iterator itr = s.iterator();
	while (itr.hasNext()) {
	    Object k = itr.next();
	    Object v = r.get(k);
	    if (v instanceof ArrayList) {
		if (isaCollectionInstVar) {
		    ArrayList lv = (ArrayList) v;
		    int lvs = lv.size() - 1;
		    for(int c = 0; c <= lvs; c++)
			lower.add(factory.tuple(k).product(factory.tuple(c)).product(factory.tuple(lv.get(c))));
		} else {
		    for(Object e : (ArrayList) v)
			lower.add(factory.tuple(k).product(factory.tuple(e)));
		}
	    } else if (v instanceof HashMap) {
		HashMap lv = (HashMap) v;
		ArrayList keys = new ArrayList(lv.keySet());
		int lvs = keys.size() - 1;
		for(int c = 0; c <= lvs; c++) {
		    Object theKey = keys.get(c);
		    lower.add(factory.tuple(k).product(factory.tuple(theKey)).product(factory.tuple(lv.get(theKey))));
		}
	    } else {
		lower.add(factory.tuple(k).product(factory.tuple(v)));
	    }
	}
	return lower;
    }

    public String log(HashSet<?> modifiableObjects, Class resultVarType) {
	String lower = lowerBound_log(modifiableObjects);
	if (isUnknown()) {
	    CharArrayWriter o = new CharArrayWriter();
	    o.append("[" + lower + ", " + fullDomainRange_log(resultVarType) + "]");
	    if (isaCollectionInstVar)
		for (LogRelation s : (ArrayList<LogRelation>) subRels)
		    o.append("\nbounds " + s.id() + ": [{}, " +  listInstVarDomain_log() + range_log(false, null) + "] ");
	    return o.toString();
	}
	return lower;
    }

    public void log2(Relation kodkodRel, HashSet<?> modifiableObjects, Class resultVarType) {
	TupleSet lower = lowerBound_log2(modifiableObjects);
	if (isUnknown()) {
	    TupleSet upper = fullDomainRange_log2(resultVarType, modifiableObjects, lower);
	    if (isaCollectionInstVar) {
		Relation i = LogMap.bounds_log2(indexingDomain, false);
		TupleSet iB = LogMap.ProblemBounds.upperBound(i);
		boolean addNull = (range != Integer.class);
		TupleSet rB = addNull ? LogMap.tupleSetBounds_log2(range, true, null) :
		    LogMap.ProblemBounds.upperBound(LogMap.bounds_log2(range, false));
		TupleSet sRelB = iB.product(rB);
		for (LogRelation s : (ArrayList<LogRelation>) subRels)		    
		    LogMap.ProblemBounds.bound(s.kodkodRel(), sRelB);
	    }
	    if (lower == null) 
		LogMap.ProblemBounds.bound(kodkodRel, upper);
	    else
		LogMap.ProblemBounds.bound(kodkodRel, lower, upper);
	} else {
	    LogMap.ProblemBounds.boundExactly(kodkodRel, lower);
	}
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

    public Formula funDef_log2() {
	if (isResultVar)
	    return kodkodRel.one();
	Formula f = null;
	Expression r = rangePlusNull_log2(false, null, null);
	if (isaCollectionInstVar) {
	    Expression d = listInstVarDomain_log2();	    
	    Expression dl = domain_log2().join(kodkodRel);	    
	    for (LogRelation s : (ArrayList<LogRelation>) subRels) {
		Relation sRel = s.kodkodRel();
		Formula fNew = sRel.function(d,r).and(dl.eq(sRel));
		f = f == null ? fNew : f.and(fNew);
	    }
	} else {
	    Expression d = domain_log2();	    
	    //o.append("FUNCTION(" + id + ", " + d + "->one " + r + ") && ");
	    f = kodkodRel.function(d,r);
	}
	return f;
    }

}
