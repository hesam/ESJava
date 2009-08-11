package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogFormula extends LogObject {

    public LogFormula(String string) {
	this(string, 0, false);
    }

    public LogFormula(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }


}
