package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;

import java.util.HashSet;
import java.util.Iterator;

public class ESJSet<E> extends HashSet<E> {

    public Integer dontcare;
    int relationizerStep = 0;    
    public LogVar var_log;
    public ESJSet<E> old;
    int clonerStep = 0;
    
    boolean isRelationized() { return this.relationizerStep == LogMap.relationizerStep(); }
    boolean isCloned() { return this.clonerStep == LogMap.clonerStep(); }

    public ESJSet(LogVar dontcare, boolean isQuantifyVar) {
	super();
    }

    public ESJSet() {
	super();
    }

    public ESJSet<E> clone() {
	ESJSet<E> res = (ESJSet<E>) super.clone();
	this.old = res;
	return res;
    }

    public void relationize() { 
	//if (!isRelationized()) { 
	    //this.relationizerStep++;
	    //relationizeOld(); 
	    //} 
    }
    
    public void relationizeOld() { 
	;
    }

    public ESJSet<E> plus(E e) {
	add(e);
	return this;
    }

    public ESJSet<E> minus(E e) {
	remove(e);
	return this;
    }

    /*
    public LogFormula quantifyOp(boolean quantKindIsaOneOrLone, String quantKind, LogObject quantVarN, LogFormula quantClauseExpr) {
	if (size() == 0)
	    return new LogFormula("true");
	else {
	    String p =  " [" + quantVarN + ": one " + "??" + "] | " + quantClauseExpr.string();
	    String q = quantKindIsaOneOrLone ? " {" + p + "} " : p;
	    return new LogFormula("(" + quantKind + q + ")");
	}
	}*/


}
