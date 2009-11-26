package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogIntComposite extends LogObject {

    public LogIntComposite(String string) {
	this(string, null, 0, false);
    }

    public LogIntComposite(String string, String decl) {
	this(string, decl, 0, false);
    }

    public LogIntComposite(String string, String decl, int listSize, boolean isaListInstVar) {
	super(string, decl, listSize, isaListInstVar);
    }

    public String sumValue_log() {
	return string;
    }

    public String id_sumValue_log() {
	return id;
    }

    public LogInt arithOp(String o, LogObject o2) {
	return new LogInt("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")",
			  id() + "." + o + "(" + o2.id_sumValue_log() + ")");
    }

    public LogInt arithOp(String o, ESJObject o2) {
	return new LogInt("(" + sumValue_log() + " " + o + " " + o2.var_log().sumValue_log() + ")",
			  id() + "." + o + "(" + o2.var_log().id_sumValue_log() + ")");
    }


}
