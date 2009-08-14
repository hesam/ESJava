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

    public LogFormula FormulaOp(String o, LogFormula o2) {
	return new LogFormula("(" + string + " " + o + " " + o2.string() + ")");
    }


}
