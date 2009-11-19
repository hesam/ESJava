package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogIntAtom extends LogObject {

    public LogIntAtom(String string) {
	this(string, 0, false);
    }

    public LogIntAtom(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }

    public LogObject intValue_log() {
	return this;
    }


}
