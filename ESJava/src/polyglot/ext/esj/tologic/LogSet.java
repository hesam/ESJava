package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogSet extends LogObject {

    public LogSet(String string) {
	this(string, 0, false);
    }

    public LogSet(String string, int listSize, boolean isaListInstVar) {
	super(string, listSize, isaListInstVar);
    }


}
