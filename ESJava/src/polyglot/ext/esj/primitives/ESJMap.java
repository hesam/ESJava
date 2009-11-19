package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.HashMap;

public class ESJMap<K,V> extends HashMap<K,V> {

    protected LogRelation rel_log;
    protected ESJMap old;
    int relationizerStep = 0;    
    int clonerStep = 0;

    public LogVar var_log;
    public LogVar var_log() { return var_log; }

    // Constructor
    public ESJMap() { super(); }

    public ESJMap(LogVar dontcare, boolean isQuantifyVar) {
	super();
	if (isQuantifyVar)
	    this.var_log =
		dontcare;
    }


    // keep my pre-state copy in old field 

    public ESJMap<K,V> clone() {
	ESJMap<K,V> res = (ESJMap<K,V>) super.clone();
	this.old = res;
	return res;
	}

    boolean isRelationized() { return this.relationizerStep == LogMap.relationizerStep(); }
    boolean isCloned() { return this.clonerStep == LogMap.clonerStep(); }



    // relationize me and my old
    public void relationize() {
	if (!isRelationized()) { 
	    this.relationizerStep++;
	    /*
	    this.rel_log = new LogRelation("ESJMap" , Integer.class, Integer.class, K, true, false, true, false, size());
	    old.rel_log = new LogRelation("ESJMap" , Integer.class, Integer.class, K, true, false, false, false, size());
	    int i = 0;
	    // FIXME
	    for (Object e : (ESJMap<Object>) this) {
		if (e instanceof ESJObject)
		    ((ESJObject) e).relationize();
		rel_log.put_log(i, e);
		old.rel_log.put_log(i, e);
		i++;
	    }
	    */
	}
    }
    
    public void relationizeOld() { 
    }

    public ESJMap<K,V> old() { return old; }
    
    public LogRelation rel_log() {
	return rel_log;
    }

    public LogSet this_log() {
	return new LogSet(rel_log.id());
    }

    public LogSet range_log() {
	return new LogSet(rel_log.id() + "[1]");
    }

    /*
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

    public ESJMap<K,V> copy(int from, int to) { 
	ESJMap<K,V> res = new ESJMap<K,V>();
	res.rel_log = new LogRelation("ESJMap" , Integer.class, Integer.class, K, true, true, false, size());
	for (int i = from; i <= to; i++) {
	    E itm = get(i);
	    res.add(itm); 
	    res.rel_log.put_log(i, itm);
	}
	return res; }    


    public ESJMap<Integer> indices() { return ESJInteger.range(0, size() - 1); }
    
    public ESJMap<K,V> allButLast() { 
	ESJMap<K,V> res = clone();
	res.remove(size()-1);
	return res;
    }
    
    public int count(E e) { int ct = 0;
                                 for (int i = 0; i < size(); i++) if (e.equals(get(i))) ct++;
                                 return ct; }
    
    public static void main(String[] args) { ESJMap<Integer> ta = new ESJMap<Integer>();
                                             int[] a = { 1, 2, 3, 4, 4, 5 };
                                             for (int i = 0; i < a.length; i++) { ta.add(new Integer(a[i]));
					     }
                                             System.out.println(ta); }

*/    
}
