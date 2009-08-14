import java.util.ArrayList;
import polyglot.ext.esj.primitives.*;
import polyglot.ext.esj.tologic.*;

class Test {
    protected ESJList nums;
    
    public ESJList nums() { return nums; }
    
    public boolean isFoo(int j) { return nums.get(0) < j; }
    
    LogFormula isFoo_log(int j) { return nums.get_log(new LogInt("0")).ArithOp("<", j); }
    
    public void fallback() { System.out.println("--> fallback initiated..."); }
    
    public void m1() { try { nums.add(0);
                             assert isFoo(3); }
                       catch (Throwable rte) { fallback(); } }
    
    public static void main(String[] args) { Test t1 = new Test(); }
    
    public Test() { super(); }
}
