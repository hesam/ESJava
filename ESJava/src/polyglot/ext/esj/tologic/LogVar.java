package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogVar extends LogObject {

    static int VarCtr = 0;

    public LogVar(String string) {
	this(string, string, 0, false);
    }

    public LogVar(String string, String decl) {
	this(string, decl, 0, false);
    }

    public LogVar(String string, String decl, int listSize, boolean isaListInstVar) {
	super(string, "e" + VarCtr++, "Expression", decl, listSize, isaListInstVar);
    }

    public LogObject intValue_log() { return this; }

    public String sumValue_log() {
	return "sum (" + string + ")";
    }

    public LogSet arithOp(String o, LogObject o2) {
	return new LogSet("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")");
    }



}
