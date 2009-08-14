package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.ArrayList;

public class ESJList extends ArrayList<Integer> {

    protected LogRelation rel_log;
    protected ESJList prime;

    // Constructor
    public ESJList() {
	this(false, 0);
    }

    public ESJList(boolean isaPrime, int fixedSize) {
	super();
	this.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, true, isaPrime, fixedSize);
	this.prime = isaPrime ? null : newPrime();
    }

    public ESJList newPrime() {
	return new ESJList(true, size());
    }

    public ESJList prime_log() { return prime; }

    public LogRelation rel_log() {
	return rel_log;
    }

    public LogSet indices_log() {
	int s = rel_log.hasFixedSize() ? rel_log.fixedSize() : size();	
	return new LogSet(ESJInteger.zeroTo_log(s).string(), s, false); 
    }

    public LogSet lastIndex_log() {
	int s = rel_log.hasFixedSize() ? rel_log.fixedSize() : size();	
	return ESJInteger.atom_log(s);
    }

    public LogAtom get_log(LogInt index) {
	return new LogAtom(LogObject.join(index.intValue_log().string(),rel_log.id()));
    }                              

    public void fallback() { System.out.println("--> fallback initiated..."); }
    
    public ESJList prime() { return this; }

    public boolean add (Integer o) {	
	super.add(o);
	rel_log.atIntIdxPut_log(size()-1, o);
	return true;
    }

    public void add(int index, Integer element) {
	super.add(index, element);
	rel_log.atIntIdxPut_log(index, element);
    }
    
    public ESJList copy(int from, int to) { ESJList res = new ESJList();
                                            for (int i = from; i <= to; i++) { res.add(get(i)); }
                                            return res; }
    
    public ESJList indices() { return ESJInteger.range(0, size() - 1); }
    
    public ESJList allButLast() { return copy(0, size() - 2); }
    
    public int count(Integer e) { int ct = 0;
                                 for (int i = 0; i < size(); i++) if (e.equals(get(i))) ct++;
                                 return ct; }
    
    public void println() { System.out.print("[");
                            for (int i = 0; i < size() - 1; i++) { System.out.print(get(i));
                                                                   System.out.print(", "); }
                            if (size() > 0) { System.out.print(get(size() - 1)); }
                            System.out.println("]"); }
    
    public static void main(String[] args) { ESJList ta = new ESJList();
                                             int[] a = { 1, 2, 3, 4, 4, 5 };
                                             for (int i = 0; i < a.length; i++) { ta.add(new Integer(a[i]));
					     }
                                             ta.println(); }
    
}
