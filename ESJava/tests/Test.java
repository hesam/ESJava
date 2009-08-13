import java.util.ArrayList;
import polyglot.ext.esj.primitives.*;

class Test {
    protected ArrayList<String> words;
    
    public ArrayList<String> words() { return words; }
    
    public boolean isFoo(int j) { return isFoo_univQuantify_0(j); }
    
    boolean isFoo_univQuantify_0(int j) { for (String w : words()) if (!(this.words().size() > j)) return false;
                                          return true; }
    
    boolean isFoo_log(int j) { return isFoo_univQuantify_0(j); }
    
    public void fallback() { System.out.println("--> fallback initiated..."); }
    
    public void m1() { try { words.add("0");
                             assert isFoo(3); }
                       catch (Throwable rte) { fallback(); } }
    
    public static void main(String[] args) { Test t1 = new Test(); }
    
    public Test() { super(); }
}
