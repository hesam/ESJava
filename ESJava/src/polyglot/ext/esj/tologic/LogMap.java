package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;
import polyglot.ext.esj.solver.Kodkodi.Kodkodi;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.CharArrayWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.CommonTokenStream;

public class LogMap {

    static String SolverOpt_Solver = "\"MiniSat\"";
    static String SolverOpt_Host = "localhost";
    static int SolverOpt_Port = 9128;
    static String SolverOpt_Flatten = "false";
    static int SolverOpt_SymmetryBreaking = 10;
        
    static HashMap JtoLog = new HashMap(); // Java Objs to Solver Atoms
    static HashMap LogtoJ = new HashMap(); 
    static HashMap ProblemRels = new HashMap(); // Holds relations for given problem  

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

	CharArrayWriter problem = new CharArrayWriter();
	CharArrayWriter funDefs = new CharArrayWriter();
	ArrayList unknowns = new ArrayList<LogRelation>();
	String spacer = "\n";

	ESJList o = (ESJList) obj;

	ProblemRels.put(o.rel_log().id(),o.rel_log());
	ProblemRels.put(o.prime_log().rel_log().id(),o.prime_log().rel_log());
	//System.out.println(ProblemRels);

	
	problem.append("solver: " + SolverOpt_Solver + spacer);
	problem.append("symmetry_breaking: " + SolverOpt_SymmetryBreaking + spacer);
	problem.append("flatten: " + SolverOpt_Flatten + spacer);
	problem.append("bit_width: " + ESJInteger.bitWidth() + spacer);
	problem.append("univ: u" + (AtomCtr+1) + spacer);
	for (Object k : ProblemRels.keySet() ) {
	    LogRelation r =  (LogRelation) ProblemRels.get(k);
	    problem.append("bounds " + k + ": " + r.log() + spacer);
	    if (r.isUnknown()) {
		unknowns.add(r);
		funDefs.append(r.funDef_log());
	    }
	}
	problem.append(ESJInteger.intBounds_log() + spacer);
	problem.append("solve " + funDefs.toString() + spacer + formula.toString() + ";");
	
	//ch.append(csq);
	//ch.flush();
	//System.out.println(problem.toString());
	String solution = Kodkodi.ESJCallSolver(problem.toString());
	SolverOutputParser parser = null;
	try {
	    ByteArrayInputStream solutionStream = new ByteArrayInputStream(solution.getBytes("UTF-8"));
	    ANTLRInputStream stream = new ANTLRInputStream(solutionStream);
	    SolverOutputLexer lexer = new SolverOutputLexer(stream);
	    parser = new SolverOutputParser(new CommonTokenStream(lexer));
	} catch (Exception e) {
            e.printStackTrace();
	}
	try {
	    parser.solutions();
	    ArrayList models = parser.models();
	    ArrayList model = ((ArrayList<ArrayList>) models).get(0);
	    boolean satisfiable = (Boolean) model.get(0);

	    if (satisfiable) {
		//System.out.println(model);
		HashMap modelRels = (HashMap) model.get(1);
		for (LogRelation u : (ArrayList<LogRelation>) unknowns) {
		    ArrayList val = (ArrayList) modelRels.get(u.id());
		    for (ArrayList v : (ArrayList<ArrayList>) val) {
			((ESJList) obj).set((Integer) get2((Integer) v.get(0)), (Integer) get2((Integer) v.get(1)));
		    }
		}
		return true;
	    } else {
		return false;
	    }

	    
	} catch (RecognitionException e) { 
	    System.out.println("parsing solver result failed!"); 
            e.printStackTrace();
	}

	return false;
    }

	

}
