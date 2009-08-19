package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
import java.io.CharArrayWriter;

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
    public boolean isUnknown() { return isaPrime; }
    public boolean isaList() { return isaList; }
    public boolean isaListInstVar() { return isaListInstVar; }
    public boolean hasFixedSize() { return fixedSize != 0; }
    public void fixedSize(int s) { fixedSize = s; }
    public void incrFixedSize() { fixedSize++; }

    public String domain_log() { 
	return isaList ? ESJInteger.zeroTo_log(fixedSize).string() : LogMap.bounds_log(domain);
    }

    public String range_log() { 
	return LogMap.bounds_log(range);
    }

    public String fullDomainRange() {
	return domain_log() + "->" + (isaListInstVar ? listInstVarDomain_log() : "") + range_log();
    }

    // FIXME
    public String listInstVarDomain_log() {
	return ESJInteger.zeroTo_log(fixedSize-1).string();
    }
   
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

    public String lowerBound_log() {
	CharArrayWriter o = new CharArrayWriter();
	o.append("{");
	Set s = keySet();
	Iterator itr = s.iterator();
	for (int i=0;i<s.size()-1;i++) {
	    Object k = itr.next();
	    Object v = get(k);
	    if (v instanceof ArrayList) {
		for(int e : (ArrayList<Integer>) v) {
		    o.append("[A" + k + ", A" + ESJInteger.log(e) + ", A" + e + "], ");
		}
	    } else {
		o.append("[A" + k + ", A" + v + "], ");
	    }	    
	}
	if (itr.hasNext()) {
	    Object k = itr.next();
	    Object v = get(k);
	    if (v instanceof ArrayList) {
		for(int e : (ArrayList<Integer>) v) {
		    o.append("[A" + k + ", A" + ESJInteger.log(e) + ", A" + e + "]");
		}
	    } else {
		o.append("[A" + k + ", A" + v + "]");
	    }	    
	}
	o.append("}");
	return o.toString();
    }

    public String log() {
	String lower = lowerBound_log();
	if (isUnknown()) {
	    CharArrayWriter o = new CharArrayWriter();
	    o.append("[" + lower + ", " + fullDomainRange() + "]");
	    if (isaListInstVar) {
		for (LogRelation s : (ArrayList<LogRelation>) subRels) {
		    o.append(" bounds " + s + ": [{}, " +  listInstVarDomain_log() + "->" + range() + "] ");
		}
	    }
	    return o.toString();
	}
	return lower;
    }

    /*log0funDef

     | d r o |
        r := self range. 
       ^isaListInstVar 
            ifTrue: [ d := self listInstVarDomain.
                          o := WriteStream with: ''.
                          subRels associationsDo: [:assoc | | subrel |
                             subrel := assoc key.
                             o nextPutAll: 'FUNCTION(' ,  subrel , ', ' , d , '->one ' , r , ') && '.
                             o nextPutAll: subrel , ' = ' , assoc value , '.' , id , ' && '].
                          o contents ] 
           ifFalse: [ d := self domain. 
                          'FUNCTION(' ,  id , ', ' , d , '->one ' , r , ') && '  ].
       
*/
    public String funDef_log() {
	CharArrayWriter o = new CharArrayWriter();
	String r = range_log();
	if (isaListInstVar) {
	    String d = listInstVarDomain_log();	    
	    for (LogRelation s : (ArrayList<LogRelation>) subRels) {
		o.append("FUNCTION(" + s.id() + ", " + d + "->one " + r + ") && ");
	    }
	} else {
	    String d = domain_log();	    
	    o.append("FUNCTION(" + id + ", " + d + "->one " + r + ") && ");
	}
	return o.toString();
    }

}
