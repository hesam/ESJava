package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;

public class LogMap {

    static Hashtable JtoLog = new Hashtable(); // Java Objs to Solver Atoms
    static Hashtable LogtoJ = new Hashtable(); 
    static int AtomCtr = ESJInteger.BoundsSize();

    public static void put1(Object key, int value) { 
	JtoLog.put(key,value);
    }

    public static int get1(Object key) { 
	return (Integer) JtoLog.get(key);
    }

    public static void put2(int key, Object value) { 
	LogtoJ.put(key,value);
    }

    public static Object get2(int key) { 
	return LogtoJ.get((Object)key);
    }    

}
