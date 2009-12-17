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


    // Constructor
    public ESJList() { super(); }


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

    public LogSet indices_log() {
	int s = rel_log.hasFixedSize() ? rel_log.fixedSize() : size();	
	return ESJInteger.zeroTo_log(s);
    }

    public Expression get_log(ESJObject index) {
	return index.var_log().expression().join(rel_log.kodkodRel());
    }                              

    public Expression get_log(IntExpression index) {
	return index.toExpression().join(rel_log.kodkodRel());
    }                              

    public Expression get_log(LogIntAtom index) {
	return index.sum().toExpression().join(rel_log.kodkodRel());
    }                              

    /*
    public IntExpression count_log(ESJObject itm) {
	return rel_log.kodkodRel().join(itm.var_log().expression()).count();
	}*/

    public Expression count_log(ESJObject itm) {
	return rel_log.kodkodRel().join(itm.var_log().expression()).count().toExpression();
    }


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
