import java.util.ArrayList;
import polyglot.ext.esj.primitives.*;

class Test {
    protected ESJList nums;
    
    public ESJList nums() { return nums; }
    
    public boolean isFoo() { return this.nums().get(0) < 0; }
    
    public static void main(String[] args) { Test t1 = new Test(); }
    
    public Test() { super(); }
}
