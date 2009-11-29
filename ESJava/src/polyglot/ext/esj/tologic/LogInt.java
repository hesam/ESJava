package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogInt extends LogObject {

    public LogInt(int i) {
	this(String.valueOf(i), 0, false);
    }

    public LogInt(String string) {
	this(string, 0, false);
    }

    public LogInt(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public LogObject intValue_log() {
	return this;
    }

    public String sumValue_log() {
	return string;
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, LogObject o2) {
	return new LogInt("(" + string + " " + kodkodiOp + " " + o2.sumValue_log() + ")");
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, ESJObject o2) {
	return new LogInt("(" + string + " " + kodkodiOp + " " + o2.var_log().sumValue_log() + ")");
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, Integer o2) {
	return new LogInt("(" + string + " " + kodkodiOp + " " + (o2+"") + ")");
    }

}
