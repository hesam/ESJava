package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogInt extends LogObject {

    static int VarCtr = 0;

    public LogInt(int i) {
	this(String.valueOf(i), "IntConstant.constant(" + i + ")", 0, false);
    }

    public LogInt(String string) {
	this(string, null, 0, false);
    }

    public LogInt(String string, String decl) {
	this(string, decl, 0, false);
    }

    public LogInt(String string, String decl, int listSize, boolean isaListInstVar) {
	super(string, "i" + VarCtr++, "IntExpression", decl, listSize, isaListInstVar);
    }

    public LogObject intValue_log() {
	return this;
    }

    public String sumValue_log() {
	return string;
    }

    public String id_sumValue_log() {
	return id;
    }

    public String sumValue_log2() {
	return id;
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, LogObject o2) {
	return new LogInt("(" + string + " " + kodkodiOp + " " + o2.sumValue_log() + ")",
			  id() + "." + kodkodOp + "(" + o2.id_sumValue_log() + ")");
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, ESJObject o2) {
	return new LogInt("(" + string + " " + kodkodiOp + " " + o2.var_log().sumValue_log() + ")",
			  id() + "." + kodkodOp + "(" + o2.var_log().id_sumValue_log() + ")");
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, Integer o2) {
	return new LogInt("(" + string + " " + kodkodiOp + " " + (o2+"") + ")",
			  id() + "." + kodkodOp + "(IntConstant.constant(" + o2 + "))");
    }

    public LogInt arithOp2(String o, LogObject o2) {
	return new LogInt(string + "." + o + "(" + o2.sumValue_log2() + ")");
    }

    public LogInt arithOp2(String o, ESJObject o2) {
	return new LogInt(string + "." + o + "(" + o2.var_log().sumValue_log2() + ")");
    }

    public LogInt arithOp2(String o, Integer o2) {
	return new LogInt(string + "." + o + "(" + (o2+"") + ")");
    }

}
