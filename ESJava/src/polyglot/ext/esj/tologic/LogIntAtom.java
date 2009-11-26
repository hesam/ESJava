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

    public LogInt arithOp(String o, LogObject o2) {
	return new LogInt("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")",
			  id_sumValue_log() + "." + o + "(" + o2.id_sumValue_log() + ")");
    }

    public LogInt arithOp(String o, ESJObject o2) {
	return new LogInt("(" + sumValue_log() + " " + o + " " + o2.var_log().sumValue_log() + ")",
			  id_sumValue_log() + "." + o + "(" + o2.var_log().id_sumValue_log() + ")");
    }

}
