package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.ArrayList;

public class ESJList<E> extends ArrayList<E> {

    protected LogRelation rel_log;
    protected ESJList old;

    // Constructor


    // keep my pre-state copy in old field and relationize it
    public void setOld() {
	this.old = copy(0,size()-1);
    }

    // relationize me
    public void relationize() {
	this.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, true, true, size());
    }

    public ESJList<E> old() { return old; }
    
    //public ESJList<E> old_log() { return old; }

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

    public LogInt count_log(LogObject itm) {
	return new LogInt("#(" + rel_log.id() + "." + itm.string() + ")");
    }

    public LogInt count_log(ESJObject itm) {
	return new LogInt("#(" + rel_log.id() + "." + itm.var_log().string() + ")");
    }

    public LogInt size_log() {
	return new LogInt("#(" + rel_log.id() + ")");
    }
   
    public LogFormula contains_log(LogObject itm) {
	return new LogFormula("some (" + rel_log.id() + "." + itm.string() + ")");
    }

    // copies obj plus its relation
    public ESJList<E> copy(int from, int to) { ESJList<E> res = new ESJList<E>();
	                                    res.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, true, false, size());
                                            for (int i = from; i <= to; i++) {
						E itm = get(i);
						res.add(itm); 
						res.rel_log.put_log(i, itm);
					    }
                                            return res; }
    
    public ESJList<Integer> indices() { return ESJInteger.range(0, size() - 1); }
    
    public ESJList<E> allButLast() { return copy(0, size() - 2); }
    
    public int count(E e) { int ct = 0;
                                 for (int i = 0; i < size(); i++) if (e.equals(get(i))) ct++;
                                 return ct; }
    
    public static void main(String[] args) { ESJList<Integer> ta = new ESJList<Integer>();
                                             int[] a = { 1, 2, 3, 4, 4, 5 };
                                             for (int i = 0; i < a.length; i++) { ta.add(new Integer(a[i]));
					     }
                                             System.out.println(ta); }
    
}
