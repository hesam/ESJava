import java.util.ArrayList;
import polyglot.ext.esj.primitives.*;
import polyglot.ext.esj.tologic.*;

class Test {
    protected ArrayList<Integer> nums;
    
    public ArrayList<Integer> nums() { return nums; }
    
    public boolean isFoo(int j) { return isFoo_univQuantify_0(j) && nums.get(0) < j * 2; }
    
    boolean isFoo_univQuantify_0(int j) { for (Integer w : nums()) if (!(w > j)) return false;
                                          return true; }
    
    LogFormula isFoo_log(int j) { return new LogFormula(new LogFormula(""), "&&", new LogFormula("")); }
    
    public void fallback() { System.out.println("--> fallback initiated..."); }
    
    public void m1() { try { nums.add(0);
                             assert isFoo(3); }
                       catch (Throwable rte) { fallback(); } }
    
    public static void main(String[] args) { Test t1 = new Test(); }
    
    public Test() { super(); }
}
