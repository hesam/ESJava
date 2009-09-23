package polyglot.ext.esj.primitives;

//import polyglot.ext.esj.tologic.*;

import java.util.HashSet;
import java.util.Iterator;

public class ESJSet<E> extends HashSet<E> {

    public Integer dontcare;

    public ESJSet<E> union(E e) {
	add(e);
	return this;
    }

    public ESJSet<E> minus(E e) {
	remove(e);
	return this;
    }

    /*
    // MAP 
    public ESJSet fieldsClosure(boolean isReflexive, java.lang.String ... fieldNs) {
	ESJSet res = new ESJSet();
	Iterator i = iterator();
	while(i.hasNext())
	    res.addAll(((ESJObject) i.next()).fieldsClosure(isReflexive, fieldNs));
	return res;
    }
    */
}
