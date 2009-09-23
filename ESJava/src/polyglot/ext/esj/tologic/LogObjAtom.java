package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogObjAtom extends LogObject {

    public LogObjAtom(String string) {
	this(string, 0, false);
    }

    public LogObjAtom(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public LogObject intValue_log() {
	return this;
    }

    public LogFormula cmpOp(String o, LogObject o2) {
	return new LogFormula("(" + string + " " + o + " " + o2.string() + ")");
    }


}
