package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogAtom extends LogObject {

    public LogAtom(String string) {
	this(string, 0, false);
    }

    public LogAtom(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public LogObject intValue_log() {
	return this;
    }

    public LogInt arithOp(String o, LogObject o2) {
	return new LogInt("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")");
    }

}
