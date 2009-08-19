import java.util.ArrayList;
import polyglot.ext.esj.primitives.*;
import polyglot.ext.esj.tologic.*;

class Test {
    protected ESJList nums;
    
    public Test() { super();
                    this.nums = new ESJList(); }
    
    public ESJList nums() { return nums; }
    
    public LogSet nums_log() { return new LogSet("A?.r?"); }
    
    public boolean isFoo(int j) { return nums.get(0) > j; }
    
    LogFormula isFoo_log(int j) { return nums.get_log(new LogInt("0")).cmpOp(">", j); }
    
    public void m1() { try { nums.add(0);
                             assert isFoo(3); }
                       catch (Throwable rte) { m1_fallback(); } }
    
    boolean m1_fallback() { System.out.println("fallback initiated...");
                            LogFormula blah = this.isFoo_log(new LogInt("3"));
                            System.out.println(blah);
                            return true; }
    
    public static void main(String[] args) { Test t1 = new Test();
                                             t1.nums.add(0);
                                             t1.nums.add(3);
                                             t1.nums.add(5);
                                             t1.nums.add(1); }
}
