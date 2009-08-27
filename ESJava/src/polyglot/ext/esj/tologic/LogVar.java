package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogVar extends LogObject {

    protected String logType;

    public LogVar(String string, String logType) {
	this(string, logType, 0, false);
    }

    public LogVar(String string, String logType, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
	this.logType = logType;
    }

    public LogObject intValue_log() {
	return this;
    }

    public String sumValue_log() {
	return logType.equals("int") ? "sum (" + string + ")" : string;
    }

    public LogSet arithOp(String o, LogObject o2) {
	return new LogSet("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")");
    }



}
