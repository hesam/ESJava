package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.HashMap;

public class ESJMap<K,V> extends HashMap<K,V> {

    protected LogRelation rel_log;
    protected ESJMap old;
    int relationizerStep = 0;    
    int clonerStep = 0;

    // Constructor
    public ESJMap() { super(); }


    // keep my pre-state copy in old field 

    public ESJMap<K,V> clone() {
	ESJMap<K,V> res = (ESJMap<K,V>) super.clone();
	for (Object e : values())
	    if (e instanceof ESJObject)
		((ESJObject)e).clone();
	this.old = res;
	return res;
	}

    boolean isRelationized() { return this.relationizerStep == LogMap.relationizerStep(); }
    boolean isCloned() { return this.clonerStep == LogMap.clonerStep(); }



    // relationize me and my old
    public void relationize() {
	if (!isRelationized()) { 
	    this.relationizerStep++;
	    // FIXME
	    for (Object e : values())
		if (e instanceof ESJObject)
		    ((ESJObject) e).relationize();
	    relationizeOld(); 
	}
    }
    
    public void relationizeOld() { 
    }

    public ESJMap<K,V> old() { return old; }
    
    public LogRelation rel_log() {
	return rel_log;
    }

    

}
