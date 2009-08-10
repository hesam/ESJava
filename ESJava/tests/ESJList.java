import java.util.ArrayList;

public class ESJList extends ArrayList<Integer> {
    public void fallback() { System.out.println("--> fallback initiated..."); }
    
    public ESJList prime() { return this; }
    
    public boolean isSorted() { return isSorted_univQuantify_0(); }
    
    boolean isSorted_univQuantify_0() { for (int i : indices().allButLast()) if (!(get(i) <= get(i + 1))) return false;
                                        return true; }
    
    public boolean isPermutationOf(ESJList L) { return isPermutationOf_univQuantify_1(L); }
    
    boolean isPermutationOf_univQuantify_1(ESJList L) {
        for (int i : ESJInteger.allInstances()) if (!(count(i) == L.count(i))) return false;
        return true; }
    
    public void sort() {
        try {
            for (int i = size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) { if (get(j).intValue() > get(i).intValue()) { Integer t = get(i);
                                                                                           remove(i);
                                                                                           add(i, get(j));
                                                                                           remove(j);
                                                                                           add(j, t); } } }
            assert this.prime().isPermutationOf(this) && this.prime().isSorted();
        }
        catch (Throwable rte) { fallback(); }
    }
    
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
                                             ESJList tb = new ESJList();
                                             int[] a = { 1, 2, 3, 4, 4, 5 };
                                             int[] b = { 4, 3, 2, 4, 5, 1 };
                                             for (int i = 0; i < a.length; i++) { ta.add(new Integer(a[i]));
                                                                                  tb.add(new Integer(b[i])); }
                                             ta.println();
                                             System.out.println("sorted?...");
                                             System.out.println(ta.isSorted());
                                             tb.println();
                                             System.out.println("sorted?...");
                                             System.out.println(tb.isSorted());
                                             System.out.println("permutations?...");
                                             System.out.println(ta.isPermutationOf(tb));
                                             System.out.println("sorting second...");
                                             tb.sort();
                                             System.out.println(tb);
                                             System.out.println("now sorted?...");
                                             System.out.println(tb.isSorted()); }
    
    public ESJList() { super(); }
}
