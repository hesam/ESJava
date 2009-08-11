package polyglot.ext.esj.tologic;

import java.util.Hashtable;
import java.util.ArrayList;

public class LogMap {

    static Hashtable JtoLog = new Hashtable(); 


    public static void put(Object key, Object value) { 
	JtoLog.put(key,value);
    }

    public static Object get(Object key) { 
	return JtoLog.get(key);
    }

    public static void main(String[] args) {
	System.out.println();
    }

}
