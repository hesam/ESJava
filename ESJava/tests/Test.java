import java.util.ArrayList;
import polyglot.ext.esj.primitives.*;
import polyglot.ext.esj.tologic.*;

class Test {
    protected ESJList nums;
    
    public Test() { super();
                    this.nums = new ESJList(); }
    
    public ESJList nums() { return nums; }
    
    public LogSet nums_log() { return new LogSet("A?.r?"); }
    
    public boolean isFoo(int j) { return isFoo_univQuantify_0(j); }
    
    boolean isFoo_univQuantify_0(int j) { for (Integer w : nums()) if (!(w > j)) return false;
                                          return true; }
    
    LogFormula isFoo_log(LogInt j) { LogObject w = new LogObject("w");
                                     return this.nums_log().quantifyOp(true, "w", w.cmpOp(">", j)); }
    
    public void fallback() { System.out.println("--> fallback initiated..."); }
    
    public void m1() { try { nums.add(0);
                             assert isFoo(3); }
                       catch (Throwable rte) { fallback(); } }
    
    public static void main(String[] args) { Test t1 = new Test();
                                             t1.nums.add(0);
                                             t1.nums.add(3);
                                             t1.nums.add(5);
                                             t1.nums.add(1); 
					     System.out.println(t1.isFoo_log(new LogInt("2"))); }
}
