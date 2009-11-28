package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import kodkod.ast.Formula;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogFormula extends LogObject {

    static int VarCtr = 0;

    public LogFormula(boolean b) {
	this(b+"", "Formula." + (b ? "TRUE" : "FALSE"), 0, false);
    }

    public LogFormula(String string) {
	this(string, null, 0, false);
    }

    public LogFormula(String string, String decl) {
	this(string, decl, 0, false);
    }

    public LogFormula(String string, String decl, int listSize, boolean isaListInstVar) {
	super(string, "f" + VarCtr++, "Formula", decl, listSize, isaListInstVar);
    }

    public LogFormula formulaOp(String kodkodiOp, String kodkodOp, LogFormula o2) {
	return new LogFormula("(" + string + " " + kodkodiOp + " " + o2.string() + ")", 
			      id + "." + kodkodOp + "(" + o2.id() + ")");
    }

    public LogFormula formulaOp2(String o, LogFormula o2) {
	return new LogFormula(id + "." + o + "(" + o2.id() + ")");
    }

    public LogFormula unaryOp(String o) {
	return new LogFormula(o + "( " + string + ")");
    }

    public static LogFormula binaryOp(String o1, String kodkodiOp, String kodkodOp, String o2) {
	return new LogFormula("( " + o1 + " " + kodkodiOp + " " + o2 + ")");
    }

    public static Formula binaryOp2(String o1, String kodkodiOp, String kodkodOp, String o2) {
	return null;
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
