package polyglot.ext.esj.tologic;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogRelation extends Hashtable {

    static int RelCtr = 0;

    // id instVar domain range fixedSize isaPrime isaList isaListInstVar subRels
    protected String id;
    protected String instVar;
    protected Class domain;
    protected Class range;
    protected ArrayList subRels;
    protected int fixedSize;
    protected boolean isaPrime;
    protected boolean isaList;
    protected boolean isaListInstVar;

    public LogRelation(String instVar, Class domain, Class range) {
	this(instVar, domain, range, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaPrime) {
	super();
	this.instVar = instVar;
	this.domain = domain;
	this.range = range;
	this.isaPrime = isaPrime;
	this.id = "r" + this.RelCtr++;
	this.isaList = false;
	this.isaListInstVar = false;
	this.isaPrime = false;
    }

    public String instVar() { return instVar; }
    public Class domain() { return domain; }
    public Class range() { return range; }
    public ArrayList subRels() { return subRels; }
    public int fixedSize() { return fixedSize; }
    public boolean isaPrime() { return isaPrime; }
    public boolean isaList() { return isaList; }
    public boolean isaListInstVar() { return isaListInstVar; }

    public void isaList(boolean b) { isaList = b; }
    public void isaListInstVar(boolean b) { isaListInstVar = b; }

    public void put_log(Object key, Object value) {
	if (value instanceof ArrayList) {
	    ArrayList l = new ArrayList();
	    for(Object v: (ArrayList)value) {
		l.add(LogMap.get(v));
	    }
	    put(key, l);
	} else {
	    put(key, LogMap.get(value));
	}
    }

    public static void main(String[] args) {
	LogRelation t1 = new LogRelation("blah", String.class, String.class, false);
	System.out.println(t1);
    }

}
