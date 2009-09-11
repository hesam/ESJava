package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogFormula extends LogObject {

    public LogFormula(String string) {
	this(string, 0, false);
    }
    /*
    public LogFormula(LogObject s1, String o, LogObject s2) {
	this("(" + s1.string() + o + s2.string() + ")", 0, false);
    }
    */
    public LogFormula(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public LogFormula formulaOp(String o, LogFormula o2) {
	return new LogFormula("(" + string + " " + o + " " + o2.string() + ")");
    }

    public LogFormula unaryOp(String o) {
	return new LogFormula(o + "( " + string + ")");
    }

    public LogFormula notOp() {
	return new LogFormula("!( " + string + ")");
    }
    /*
    public static LogFormula quantifyOp(ArrayList<LogSet> quantListExpr, ArrayList<Boolean> quantKindIsaOneOrLone, ArrayList<String> quantKind, ArrayList<LogObject> quantVarN, LogFormula quantClauseExpr) {
	String p = "";	
	for (int i=0;i<quantKind.size();i++)
	    p += quantKind.get(i) + " [" + quantVarN.get(i) + ": one " + quantListExpr.get(i).string() + "] | ";
	p += quantClauseExpr.string();
	String q = quantKindIsaOneOrLone.get(0) ? " {" + p + "} " : p;
	return new LogFormula("(" + q + ")");
	}*/

    public static LogFormula quantifyOp(LogFormula f) {
	//return new LogFormula("(");// + string + " " + o + " " + o2.string() + ")");
	return f;
    }

}
