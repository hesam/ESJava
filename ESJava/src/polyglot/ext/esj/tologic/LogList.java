package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogList extends LogObject {

    public LogList(String string) {
	this(string, 0, false);
    }

    public LogList(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public boolean isEmpty() {
	return string.equals("u0");
    }

    public LogSet allButLast_log() {
	return arithOp("-", ESJInteger.atom_log(listSize-1));
    }

    public LogIntAtom get_log(LogInt index) {
	return new LogIntAtom(LogObject.join_log(index.intValue_log().string(),string));
    }                              

    public LogSet arithOp(String o, LogObject o2) {
	return new LogSet("(" + string + " " + o + " " + o2.string() + ")");
    }

    public LogFormula quantifyOp(boolean quantKindIsaOneOrLone, String quantKind, LogObject quantVarN, LogFormula quantClauseExpr) {
	if (isEmpty())
	    return new LogFormula("true");
	else {
	    String p =  " [" + quantVarN + ": one " + string + "] | " + quantClauseExpr.string();
	    String q = quantKindIsaOneOrLone ? " {" + p + "} " : p;
	    return new LogFormula("(" + quantKind + q + ")");
	}
    }

    public LogInt count_log(LogObject itm) {
	return new LogInt("#(" + string + "." + itm.string() + ")");
    }

    public LogInt size_log() {
	return new LogInt("#(" + string + ")");
    }

    public LogFormula contains_log(LogObject itm) {
	return new LogFormula("some (" + string + "." + itm.string() + ")");
    }

    public LogFormula contains_log(ESJObject itm) {
	return new LogFormula("some (" + string + "." + itm.var_log().string() + ")");
    }


}
