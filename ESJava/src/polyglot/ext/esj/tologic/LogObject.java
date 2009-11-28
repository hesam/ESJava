package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.io.CharArrayWriter;
import java.util.Hashtable;
import java.util.ArrayList;

public class LogObject  {

    static int VarCtr = 0;

    protected String string;
    protected String id;
    protected String decl;
    protected String kodkodType;
    protected int listSize;
    protected boolean isaListInstVar;

    static protected CharArrayWriter allDecls = new CharArrayWriter();

    public static String allDecls() { return allDecls.toString(); }

    public LogObject(String string) {
	this(string, "o" + VarCtr++, null, null, 0, false);
    }

    public LogObject(String string, int listSize, boolean isaListInstVar) {
	this(string, "o" + VarCtr++, null, null, listSize, isaListInstVar);
    }

    public LogObject(String string, String id, String kodkodType, int listSize, boolean isaListInstVar) {
	this(string, id, kodkodType, null, listSize, isaListInstVar);
    }

    public LogObject(String string, String id, String kodkodType, String decl, int listSize, boolean isaListInstVar) {
	super();
	this.string = string;
	this.id = id;
	this.kodkodType = kodkodType;
	this.decl = id + " = " + decl + ";\n";
	this.listSize = listSize;
	this.isaListInstVar = isaListInstVar;
	if (string != null)
	    allDecls.append(kodkodType + " " + this.decl +"\t" + string +"\n");
    }

    public static String genVar_log() {
    	return "S" + VarCtr++;
    }

    public String id() { return id; }

    public String decl() { return decl; }

    public String kodkodType() { return kodkodType; }

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

    public String id_sumValue_log() {
	return id + ".sum()";
    }

    public String sumValue_log2() {
	//return new LogInt("sum (" + string + ")");
	return "sum (" + id + ")";
    }

    public LogFormula equals_log(LogObject o2) {
	return new LogFormula("(" + string + " = " + o2.string() + ")");
    }

    public LogFormula equals_log(ESJObject o2) {
	return new LogFormula("(" + string + " = " + o2.var_log().string() + ")");
    }

    public static String join_log(String s1, String s2) {
	return s1 + "." + s2;
    }

    public LogFormula cmpOp(String kodkodiOp, String kodkodOp, LogObject o2) {
	return new LogFormula("(" + sumValue_log() + " " + kodkodiOp + " " + o2.sumValue_log() + ")",
			      id_sumValue_log() + "." + kodkodOp + "(" + o2.id_sumValue_log() + ")");
    }

    public LogFormula cmpOp2(String o, LogObject o2) {
	return new LogFormula(sumValue_log2() + "." + o + "(" + o2.sumValue_log2() + ")");
    }

    public LogFormula cmpOp(String kodkodiOp, String kodkodOp, ESJObject o2) {
	return new LogFormula("(" + sumValue_log() + " " + kodkodiOp + " " + o2.var_log().sumValue_log() + ")",
			      id_sumValue_log() + "." + kodkodOp + "(" + o2 + ")");
    }

    public LogFormula cmpOp2(String o, ESJObject o2) {
	return new LogFormula(sumValue_log2() + "." + o + "(" + o2.var_log().sumValue_log2() + ")");
    }

    public LogFormula cmpOp(String kodkodiOp, String kodkodOp, Integer o2) {
	LogInt nli = new LogInt(o2.toString());
	return new LogFormula("(" + sumValue_log() + " " + kodkodiOp + " " + nli.sumValue_log() + ")",
			      id_sumValue_log() + "." + kodkodOp + "(" + nli.id() + ")");
  }


}
