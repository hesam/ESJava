package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.ArrayList;

public class ESJList extends ArrayList<Integer> {

    protected LogRelation rel_log;

    // Constructor
    public ESJList() {
	super();
	this.rel_log = new LogRelation("ESJList" , Integer.class, Integer.class);
	rel_log.isaList(true);	
    }

    public LogRelation rel_log() {
	return rel_log;
    }


    public void fallback() { System.out.println("--> fallback initiated..."); }
    
    public ESJList prime() { return this; }
    
    public ESJList copy(int from, int to) { ESJList res = new ESJList();
                                            for (int i = from; i <= to; i++) { res.add(get(i)); }
                                            return res; }
    
    public ESJList indices() { return ESJInteger.range(0, size() - 1); }
    
    public ESJList allButLast() { return copy(0, size() - 2); }
    
    public int count(Object e) { int ct = 0;
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
