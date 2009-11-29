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
   
    public LogInt arithOp(String kodkodiOp, String kodkodOp, LogObject o2) {
	return new LogInt("(" + sumValue_log() + " " + kodkodiOp + " " + o2.sumValue_log() + ")");
    }

    public LogInt arithOp(String kodkodiOp, String kodkodOp, ESJObject o2) {
	return new LogInt("(" + sumValue_log() + " " + kodkodiOp + " " + o2.var_log().sumValue_log() + ")");
    }


}
