package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

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
	this(instVar, domain, range, false, false, 0, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaList){
	this(instVar, domain, range, isaList, false, 0, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaList, boolean isaPrime, int fixedSize) {
	this(instVar, domain, range, isaList, isaPrime, fixedSize, false);
    }

    public LogRelation(String instVar, Class domain, Class range, boolean isaList, 
		       boolean isaPrime, int fixedSize, boolean isaListInstVar) {
	super();
	this.instVar = instVar;
	this.domain = domain;
	this.range = range;
	this.fixedSize = fixedSize;
	this.isaPrime = isaPrime;
	this.isaList = isaList;
	this.isaListInstVar = isaListInstVar;
	this.id = "r" + this.RelCtr++;
    }

    public String instVar() { return instVar; }
    public String id() { return id; }
    public Class domain() { return domain; }
    public Class range() { return range; }
    public ArrayList subRels() { return subRels; }
    public int fixedSize() { return fixedSize; }
    public boolean isaPrime() { return isaPrime; }
    public boolean isaList() { return isaList; }
    public boolean isaListInstVar() { return isaListInstVar; }
    public boolean hasFixedSize() { return fixedSize != 0; }

    public void put_log(int key, Object value) {
	if (value instanceof ArrayList) {
	    ArrayList l = new ArrayList();
	    for(int v: (ArrayList<Integer>)value) {
		l.add(LogMap.get1(v));
	    }
	    put(key, l);
	} else {
	    put(key, LogMap.get1(value));
	}
    }

    public void atIntIdxPut_log(int index, Object value) {
	put_log(ESJInteger.log(index),value);
    }


}
