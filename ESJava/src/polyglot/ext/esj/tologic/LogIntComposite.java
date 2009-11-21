package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogIntComposite extends LogObject {

    public LogIntComposite(String string) {
	this(string, 0, false);
    }

    public LogIntComposite(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public String sumValue_log() {
	return string;
    }

    public LogInt arithOp(String o, LogObject o2) {
	return new LogInt("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")");
    }

}
