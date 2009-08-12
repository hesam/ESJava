package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.esj.ast.*;
import polyglot.types.*;
import polyglot.ext.esj.types.*;


import java.util.Hashtable;
import java.util.ArrayList;

public class LogMap {

    static Hashtable JtoLog = new Hashtable(); // Java Objs to Solver Atoms
    static Hashtable LogtoJ = new Hashtable(); 

    static int AtomCtr = ESJInteger.BoundsSize();

    public static void put1(Object key, int value) { 
	JtoLog.put(key,value);
    }

    public static int get1(Object key) { 
	return (Integer) JtoLog.get(key);
    }

    public static void put2(int key, Object value) { 
	LogtoJ.put(key,value);
    }

    public static Object get2(int key) { 
	return LogtoJ.get((Object)key);
    }
    /*
    public static Receiver toLogicExpr(
	Receiver r, NodeFactory nf, ESJTypeSystem ts) {
	if (r instanceof Local) {
	    return r;
	} else if (r instanceof Field) {
	    Field f = (Field) r;
// we assume for now that this is a target, i.e. it is a simple path
// to a field (rather than the prefix being some arbitrary expression)
	    return f.target(toLogicExpr(f.target(), nf, ts));
	} else if (r instanceof Call) {
	    Call c = (Call) r;
	    return c.target(toLogicExpr(c.target(), nf, ts));
	} else if (r instanceof Cast) {
	    Cast cast = (Cast) r;
	    return cast.expr((Expr) toLogicExpr(cast.expr(), nf, ts));
	} else if ((r instanceof Lit) || (r instanceof Special)) {
	    return r;
	} else if (r instanceof Binary) {
	    Binary b = (Binary) r;
	    return nf.Binary(null,
			     (Expr) toLogicExpr(b.left(), nf, ts), b.operator(),
			     (Expr) toLogicExpr(b.right(), nf, ts));
	} else if (r instanceof Unary) {
	    Unary u = (Unary) r;
	    return nf.Unary(null,
			    u.operator(), (Expr) toLogicExpr(u.expr(), nf, ts));
	} else if (r instanceof TypeNode) {
	    return r;
	} else if (r instanceof ArrayAccess) {
	    ArrayAccess a = (ArrayAccess) r;
	    return nf.ArrayAccess(null,
				  (Expr) toLogicExpr(a.array(), nf, ts),
				  (Expr) toLogicExpr(a.index(), nf, ts));
	} else {
	    throw new RuntimeException("Don't know how to convert " + r +
				       " to a Logic expression.");
	}
    }
    */

    public static void main(String[] args) {
	System.out.println();
    }

}
