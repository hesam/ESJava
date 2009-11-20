package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogObject  {

    static int VarCtr = 0;

    protected String string;
    //log0type log0listType 
    protected int listSize;
    protected boolean isaListInstVar;

    public LogObject(String string) {
	this(string, 0, false);
    }

    public LogObject(String string, int listSize, boolean isaListInstVar) {
	super();
	this.string = string;
	this.listSize = listSize;
	this.isaListInstVar = isaListInstVar;
    }

    public static String genVar_log() {
    	return "S" + VarCtr++;
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
	//return new LogInt("sum (" + string + ")");
	return "sum (" + string + ")";
    }

    public LogFormula equals_log(LogObject o2) {
	return new LogFormula("(" + string + " = " + o2.string() + ")");
    }

    public static String join_log(String s1, String s2) {
	return s1 + "." + s2;
    }

    public LogFormula cmpOp(String o, LogObject o2) {
	return new LogFormula("(" + sumValue_log() + " " + o + " " + o2.sumValue_log() + ")");
    }

    public LogFormula cmpOp(String o, ESJObject o2) {
	return new LogFormula("(" + sumValue_log() + " " + o + " " + o2.var_log().sumValue_log() + ")");
    }


}
