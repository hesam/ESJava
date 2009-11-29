package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogVar extends LogObject {

    public LogVar(String string) {
	this(string, 0, false);
    }

    public LogVar(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public LogObject intValue_log() { return this; }

    public String sumValue_log() {
	return "sum (" + string + ")";
    }

    public LogSet arithOp(String o, LogObject o2) {
	return new LogSet("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")");
    }



}
