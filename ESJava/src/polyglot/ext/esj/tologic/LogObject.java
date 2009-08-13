package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogObject  {

    protected String string;
    //log0type log0listType 
    protected int listSize;
    protected boolean isaListInstVar;

    public LogObject(String string) {
	this(string, 0, false);
    }

    public LogObject(LogObject s1, String o, LogObject s2) {
	this("(" + s1.string() + o + s2.string() + ")", 0, false);
    }

    public LogObject(String string, int listSize, boolean isaListInstVar) {
	super();
	this.string = string;
	this.listSize = listSize;
	this.isaListInstVar = isaListInstVar;
    }


    public String string() {
	return string;
    }

    public String toString() {
	return string;
    }

    public int listSize() {
	return listSize;
    }

    public boolean isaListInstVar() {
	return isaListInstVar;
    }

    public LogObject intValue_log() {
	return new LogSet("Int [" + string + "]");
    }

    public String sumValue_log() {
	return "sum (" + string + ")";
    }

    public static String join(String s1, String s2) {
	return s1 + "." + s2;
    }


}
