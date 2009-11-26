package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogIntComposite extends LogObject {

    static int VarCtr = 0;

    public LogIntComposite(String string) {
	this(string, null, 0, false);
    }

    public LogIntComposite(String string, String decl) {
	this(string, decl, 0, false);
    }

    public LogIntComposite(String string, String decl, int listSize, boolean isaListInstVar) {
	super(string, "i" + VarCtr++, "IntExpression", decl, listSize, isaListInstVar);
    }

    public String sumValue_log() {
	return string;
    }

    public String id_sumValue_log() {
	return id;
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, LogObject o2) {
	return new LogInt("(" + sumValue_log() + " " + kodkodiOp + " " + o2.sumValue_log() + ")",
			  id() + "." + kodkodOp + "(" + o2.id_sumValue_log() + ")");
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, ESJObject o2) {
	return new LogInt("(" + sumValue_log() + " " + kodkodiOp + " " + o2.var_log().sumValue_log() + ")",
			  id() + "." + kodkodOp + "(" + o2.var_log().id_sumValue_log() + ")");
    }


}
