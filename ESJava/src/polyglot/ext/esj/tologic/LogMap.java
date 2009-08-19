package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;
import java.io.CharArrayWriter;

public class LogMap {

    static String SolverOpt_Solver = "MiniSat";
    static String SolverOpt_Host = "localhost";
    static int SolverOpt_Port = 9128;
    static String SolverOpt_Flatten = "false";
    static int SolverOpt_SymmetryBreaking = 10;
        
    static Hashtable JtoLog = new Hashtable(); // Java Objs to Solver Atoms
    static Hashtable LogtoJ = new Hashtable(); 
    static Hashtable ProblemRels = new Hashtable(); // Holds relations for given problem  

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

    // FIXME
    public static String bounds_log(Class c) {
	return  ESJInteger.allInstances_log().string();
    }

    public static boolean solve(Object obj, Object formula) {

	/*
log0preProblemLines

   | spacer problem problemUnknowns funDefs |

     ESOOPLogSolver ProblemRels associationsDo: [:a | | rel |
        rel := a value.
        problem nextPutAll: 'bounds ' , a key , ': ' , rel log0 , spacer. 
        rel isUnknown ifTrue: 
          [ problemUnknowns add: rel.
            funDefs nextPutAll: rel log0funDef ] ].
     problem nextPutAll: (ESOOPInteger log0intBounds , spacer).
     problem nextPutAll: 'solve ' , spacer , funDefs contents.
     ^{problemUnknowns. problem contents}
	 */
	CharArrayWriter problem = new CharArrayWriter();
	CharArrayWriter funDefs = new CharArrayWriter();
	ArrayList unknowns = new ArrayList();
	String spacer = "\n";

	ESJList o = (ESJList) obj;

	ProblemRels.put(o.rel_log().id(),o.rel_log());
	ProblemRels.put(o.prime_log().rel_log().id(),o.prime_log().rel_log());
	System.out.println(ProblemRels);

	
	problem.append("solver: " + SolverOpt_Solver + spacer);
	problem.append("symmetry_breaking: " + SolverOpt_SymmetryBreaking + spacer);
	problem.append("flatten: " + SolverOpt_Flatten + spacer);
	problem.append("bit_with: " + ESJInteger.bitWidth() + spacer);
	problem.append("univ: u" + (AtomCtr+1) + spacer);
	problem.append(ESJInteger.intBounds_log() + spacer);
	for (Object k : ProblemRels.keySet() ) {
	    LogRelation r =  (LogRelation) ProblemRels.get(k);
	    problem.append("bounds " + k + ": " + r.log() + spacer);
	    if (r.isUnknown()) {
		unknowns.add(r);
		funDefs.append(r.funDef_log());
	    }
	}
	problem.append("solve " + funDefs.toString() + spacer + formula.toString() + spacer);
	
	//ch.append(csq);
	//ch.flush();
	System.out.println(problem.toString());

	return false;
    }

	

}
