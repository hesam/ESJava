package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.ArrayList;

import kodkod.ast.Relation;
import kodkod.ast.IntExpression;
import kodkod.ast.Expression;

public class ESJList<E> extends ArrayList<E> {

    protected LogRelation rel_log;
    protected ESJList old;
    int relationizerStep = 0;    
    int clonerStep = 0;

    public LogVar var_log;
    public LogVar var_log() { return var_log; }

    // Constructor
    public ESJList() { super(); }

    public ESJList(LogVar dontcare, boolean isQuantifyVar) {
	super();
	if (isQuantifyVar)
	    this.var_log =
		dontcare;
    }


    // keep my pre-state copy in old field 
    public ESJList<E> clone() {
	ESJList<E> res = (ESJList<E>) super.clone();
	for (Object e : (ESJList<Object>) this)
	    if (e instanceof ESJObject)
		((ESJObject)e).clone();
	this.old = res;

	Relation r = Relation.nary("r" + LogRelation.RelCtr(), 2);
	this.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, Integer.class, r, false, false, true, false, true, size());

	return res;
    }

    boolean isRelationized() { return this.relationizerStep == LogMap.relationizerStep(); }
    boolean isCloned() { return this.clonerStep == LogMap.clonerStep(); }

    // relationize me (receiver will be old)
    public void relationize() {
	if (!isRelationized()) { 
	    this.relationizerStep++;
	    Relation rOld = Relation.nary("r" + LogRelation.RelCtr(), 2);
	    this.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, Integer.class, rOld, false, false, false, false, true, size());
	    int i = 0;
	    // FIXME
	    for (Object e : (ESJList<Object>) this) {
		if (e instanceof ESJObject)
		    ((ESJObject) e).relationize();
		rel_log.put_log(i++, e);
	    }
	}

    }

    public void relationizeOld() { 
    }


    public ESJList<E> old() { return old; }
    
    public LogRelation rel_log() {
	return rel_log;
    }

    public LogSet this_log() {
	return new LogSet(rel_log.id());
    }

    public LogSet range_log() {
	return new LogSet(rel_log.id() + "[1]");
    }

    public LogSet indices_log() {
	int s = rel_log.hasFixedSize() ? rel_log.fixedSize() : size();	
	return new LogSet(ESJInteger.zeroTo_log(s).string(), s, false); 
    }

    public Log2Set indices_log2() {
	int s = rel_log.hasFixedSize() ? rel_log.fixedSize() : size();	
	return ESJInteger.zeroTo_log2(s);
    }

    public LogSet lastIndex_log() {
	int s = rel_log.hasFixedSize() ? rel_log.fixedSize() - 1 : size() - 1;	
	return ESJInteger.atom_log(s);
    }

    public LogIntAtom get_log(LogObject index) {
	return new LogIntAtom(LogObject.join_log(index.intValue_log().string(),rel_log.id()));
    }                              

    public LogIntAtom get_log(ESJObject index) {
	return new LogIntAtom(LogObject.join_log(index.var_log().intValue_log().string(),rel_log.id()));
    }                              

    public Expression get_log2(ESJObject index) {
	return index.var_log2().expression().join(rel_log.kodkodRel());
    }                              

    public Expression get_log2(IntExpression index) {
	return index.toExpression().join(rel_log.kodkodRel());
    }                              

    public Expression get_log2(Log2IntAtom index) {
	return index.sum().toExpression().join(rel_log.kodkodRel());
    }                              


    public LogInt count_log(LogObject itm) {
	return new LogInt("#(" + rel_log.id() + "." + itm.string() + ")");
    }

    public LogInt count_log(ESJObject itm) {
	return new LogInt("#(" + rel_log.id() + "." + itm.var_log().string() + ")");
    }

    /*
    public IntExpression count_log2(ESJObject itm) {
	return rel_log.kodkodRel().join(itm.var_log2().expression()).count();
	}*/

    public Expression count_log2(ESJObject itm) {
	return rel_log.kodkodRel().join(itm.var_log2().expression()).count().toExpression();
    }

    public LogInt size_log() {
	return new LogInt("#(" + rel_log.id() + ")");
    }
   
    public LogFormula contains_log(LogObject itm) {
	return new LogFormula("some (" + rel_log.id() + "." + itm.string() + ")");
    }

    //FIXME:
    public LogFormula contains_log(Integer itm) {
	return new LogFormula("some (" + rel_log.id() + "." + ESJInteger.atom_log(itm) + ")");
    }

    // copies obj plus its relation

    /*
    public ESJList<E> copy(int from, int to) { 
	ESJList<E> res = new ESJList<E>();
	    this.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, Integer.class, null, false, false, true, false, true, size());
	for (int i = from; i <= to; i++) {
	    E itm = get(i);
	    res.add(itm); 
	    res.rel_log.put_log(i, itm);
	}
	return res; }    
    */

    public ESJList<Integer> indices() { return ESJInteger.range(0, size() - 1); }
    
    public ESJList<E> allButLast() { 
	ESJList<E> res = clone();
	res.remove(size()-1);
	return res;
    }
    
    public int count(E e) { int ct = 0;
                                 for (int i = 0; i < size(); i++) if (e.equals(get(i))) ct++;
                                 return ct; }

    public int sum() { int s = 0; for (int i = 0; i < size(); i++) s += (Integer) get(i); return s; }
    
    public static void main(String[] args) { ESJList<Integer> ta = new ESJList<Integer>();
                                             int[] a = { 1, 2, 3, 4, 4, 5 };
                                             for (int i = 0; i < a.length; i++) { ta.add(new Integer(a[i]));
					     }
                                             System.out.println(ta); }
    
}
