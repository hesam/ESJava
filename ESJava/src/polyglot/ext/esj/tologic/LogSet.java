package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogSet extends LogObject {

    protected Class range;

    public LogSet(String string) {
	this(string, 0, false, null);
    }
    public LogSet(String string, Class range) {
	this(string, 0, false, range);
    }

    public LogSet(String string, int listSize, boolean isaListInstVar) {
	this(string, listSize, isaListInstVar, null);	
    }

    public LogSet(String string, int listSize, boolean isaListInstVar, Class range) {
	super(string, listSize, isaListInstVar);
	this.range = range;
    }

    public Class range() { return range; }

    public boolean isEmpty() {
	return string.equals("u0");
    }

    public LogSet allButLast_log() {
	return arithOp("-", ESJInteger.atom_log(listSize-1));
    }

    public LogIntAtom get_log(LogObject index) {
	return new LogIntAtom(LogObject.join_log(index.intValue_log().string(),string));
    }                              

    public LogIntAtom get_log(ESJInteger index) {
	return new LogIntAtom(LogObject.join_log(index.var_log().intValue_log().string(),string));
    }                              

    // FIXME
    /*
    public LogInt get_log(int index) {
	return new LogInt(LogObject.join_log(ESJInteger.log_str(index),string));
    } */                             

    public LogIntAtom get_log(ESJObject index) {
	return new LogIntAtom(LogObject.join_log(index.var_log().intValue_log().string(),string));
    }                              

    /*
    public LogInt get_log(ESJInteger index) {
	return new LogInt(LogObject.join_log(
						     index.var_log == null ? 
						     index.log_str() :
						     index.var_log().intValue_log().string(),
						     string));
    } */                             

    public LogSet arithOp(String o, LogObject o2) {
	return new LogSet("(" + string + " " + o + " " + o2.string() + ")");
    }

    public LogFormula quantifyOp(boolean quantKindIsaOneOrLone, String quantKind, LogObject quantVarN, LogFormula quantClauseExpr) {
	if (isEmpty())
	    return new LogFormula("true");
	else {
	    String s = string + (isaListInstVar ? "[1]" : "");
	    String p =  " [" + quantVarN + ": one " + s + "] | " + quantClauseExpr.string();
	    String q = quantKindIsaOneOrLone ? " {" + p + "} " : p;
	    return new LogFormula("(" + quantKind + q + ")");
	}
    }
    
    public LogSet setComprehensionOp(LogObject quantVarN, LogFormula quantClauseExpr) {
	if (isEmpty())
	    return new LogSet("u0");
	else {
	    String s = string + (isaListInstVar ? "[1]" : "");
	    String p =  " [" + quantVarN + ": one " + s + "] | " + quantClauseExpr.string();
	    String q = " {" + p + "} ";
	    return new LogSet(q);
	}
    }

    public LogInt count_log(LogObject itm) {
	return new LogInt("#(" + string + "." + itm.string() + ")");
    }

    public LogInt size_log() {
	return new LogInt("#(" + string + ")");
    }

    public LogFormula contains_log(LogObject itm) {
	return new LogFormula("some (" + string + " & " + itm.string() + ")");
    }

    public LogFormula contains_log(ESJObject itm) {
	return new LogFormula("some (" + string + " & " + itm.var_log().string() + ")");
    }

    public LogFormula containsKey_log(LogObject itm) {
	return new LogFormula("some (" + itm.string() + "." + string + ")");
    }

    public LogFormula containsKey_log(ESJObject itm) {
	return new LogFormula("some (" + itm.var_log().string() + "." + string + ")");
    }

    public LogSet indices_log() {
	return new LogSet(string + "[0]");
    }

    public LogSet plus_log(LogSet o2) {
	return arithOp("+", o2);
    }

    public LogSet plus_log(ESJObject o2) { //FIXME
	return arithOp("+", new LogObjAtom(LogMap.get1_log(o2)));
    }

    public LogSet minus_log(LogSet o2) {
	return arithOp("-", o2);
    }

    public LogSet minus_log(ESJObject o2) { //FIXME
	return arithOp("-", new LogObjAtom(LogMap.get1_log(o2)));
    }

    public LogSet minus_log(Integer o2) { //FIXME
	return arithOp("-", new LogObjAtom(LogMap.get1_log(o2)));
    }

}
