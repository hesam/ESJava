import java.util.ArrayList;

class Test {
    protected ArrayList<Integer> nums;
    
    public ArrayList<Integer> nums() { return nums; }
    
    public boolean isFoo() { return isFoo_existQuantify_0() && nums.size() > 1; }
    
    boolean isFoo_existQuantify_0() { for (int w : nums()) if (w < 0) return true;
                                      return false; }
    
    public void fallback() { System.out.println("fallback initiated..."); }
    
    public void m1() { try { nums.add(0);
                             assert isFoo(); }
                       catch (Throwable rte) { fallback(); } }
    
    public static void main(String[] args) { Test t1 = new Test();
                                             t1.m1(); }
    
    public Test() { super(); }
}
