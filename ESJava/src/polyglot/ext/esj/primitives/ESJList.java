package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.ArrayList;

public class ESJList extends ArrayList<Integer> {

    protected LogRelation rel_log;
    protected ESJList prime;

    // Constructor

    // 1. keep my pre-state copy in prime field
    // 2. relationize both of us
    public void setPrime() {
	this.prime = copy(0,size()-1);
	this.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, true, true, size());
    }

    public ESJList prime() { return prime; }
    
    public ESJList prime_log() { return prime; }

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

    public LogAtom get_log(LogObject index) {
	return new LogAtom(LogObject.join_log(index.intValue_log().string(),rel_log.id()));
    }                              

    public LogInt count_log(LogObject itm) {
	return new LogInt("#(" + rel_log.id() + "." + itm.string() + ")");
    }

    public LogInt size_log() {
	return new LogInt("#(" + rel_log.id() + ")");
    }
   
    // copies obj plus its relation
    public ESJList copy(int from, int to) { ESJList res = new ESJList();
	                                    res.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class, true, false, size());
                                            for (int i = from; i <= to; i++) {
						Integer itm = get(i);
						res.add(itm); 
						res.rel_log.atIntIdxPut_log(i, itm);
					    }
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
