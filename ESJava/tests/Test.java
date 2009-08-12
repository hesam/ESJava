import java.util.ArrayList;
import polyglot.ext.esj.primitives.*;

class Test {
    protected ESJList nums;
    
    public ESJList nums() { return nums; }
    
    public boolean isFoo() { return isFoo_univQuantify_0(); }
    
    boolean isFoo_univQuantify_0() { for (int i : nums()) if (!(this.nums().get(i) < 0)) return false;
                                     return true; }
    
    boolean isFoo_log() { return isFoo_univQuantify_0(); }
    
    public static void main(String[] args) { Test t1 = new Test(); }
    
    public Test() { super(); }
}
