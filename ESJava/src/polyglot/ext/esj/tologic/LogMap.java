package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;
import polyglot.ext.esj.solver.Kodkodi.Kodkodi;

import java.util.HashMap;
import java.util.ArrayList;
import java.io.CharArrayWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.lang.reflect.Method;

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
    static boolean SolverOpt_debug = false;

    static HashMap JtoLog = new HashMap(); // Java Objs to Solver Atoms
    static HashMap LogtoJ = new HashMap(); 
    static HashMap ProblemRels = new HashMap(); // Holds relations for given problem  
    static HashMap InstVarRels = new HashMap(); // Holds inst var relations for each class
    static HashMap ClassAtoms = new HashMap(); // Holds atoms for each class

    static int AtomCtr = ESJInteger.BoundsSize();

    public static void SolverOpt_debug(boolean b) {
	SolverOpt_debug = b;
    }

    public static void newAtom(Object key) { 
	Class c = key.getClass();	
	if (!ClassAtoms.containsKey(c))
	    ClassAtoms.put(c, new ArrayList());
	((ArrayList) ClassAtoms.get(c)).add(AtomCtr);
	LogtoJ.put(AtomCtr,key);
	JtoLog.put(key,AtomCtr++);
    }

    public static void put1(Object key, int value) { 
	JtoLog.put(key,value);
    }

    public static int get1(Object key) { 
	return (Integer) JtoLog.get(key);
    }

    public static String get1_log(Object key) { 
	return "A" + get1(key);
    }

    public static void put2(int key, Object value) { 
	LogtoJ.put(key,value);
    }

    public static Object get2(int key) { 
	return LogtoJ.get((Object)key);
    }

    // FIXME
    public static String bounds_log(Class c) {
	//return c.allInstances_log().string();
	//System.out.println(c);
	if (c == int.class || c == Integer.class) 
	    return ESJInteger.allInstances_log().string();
	else {
	    ArrayList atoms = (ArrayList) ClassAtoms.get(c);
	    return "u" + atoms.size() + '@' + atoms.get(0);
	}
    }


    public static String newInstVarRel(String classStr, String instVar, String domainStr, Class range, boolean isUnknown) {
	try {
	    String k = instVar + (isUnknown ? "" : "_prime");
	    Class domain = Class.forName(domainStr);
	    LogRelation r = new LogRelation(instVar, domain, range, false, isUnknown);
	    if (!InstVarRels.containsKey(classStr))
		InstVarRels.put(classStr, new HashMap());
	    ((HashMap) InstVarRels.get(classStr)).put(k,r);
	    //System.out.println(InstVarRels);
	    return r.id();
	} catch (ClassNotFoundException e) {
	    System.out.println(e);
	    return null;
	}       
    }

    public static LogRelation instVarRel_log(Object obj, String instVar) {
	return (LogRelation) ((HashMap) InstVarRels.get(obj.getClass().getName())).get(instVar);
    }

    public static LogAtom instVar_log(Object obj, String instVar) {
	return new LogAtom("(" + get1_log(obj) + "." + instVarRel_log(obj, instVar).id() + ")");
    }

    public static boolean solve(Object obj, Object formula) {

	CharArrayWriter problem = new CharArrayWriter();
	CharArrayWriter funDefs = new CharArrayWriter();
	ArrayList unknowns = new ArrayList<LogRelation>();
	String spacer = "\n";

	getProblemRels(obj);
	
	problem.append("solver: " + SolverOpt_Solver + spacer);
	problem.append("symmetry_breaking: " + SolverOpt_SymmetryBreaking + spacer);
	problem.append("flatten: " + SolverOpt_Flatten + spacer);
	problem.append("bit_width: " + ESJInteger.bitWidth() + spacer);
	problem.append("univ: u" + AtomCtr + spacer);
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
	if (SolverOpt_debug)
	    System.out.println(problem.toString());
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
		if (SolverOpt_debug)
		    System.out.println(model);
		commitModel(obj, unknowns, model);
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


    public static void getProblemRels(Object obj) {

	if (obj instanceof ArrayList) {
	    ESJList o = (ESJList) obj;

	    ProblemRels.put(o.rel_log().id(),o.rel_log());
	    ProblemRels.put(o.prime_log().rel_log().id(),o.prime_log().rel_log());
	} else {
	    HashMap classInstVarRels = (HashMap) InstVarRels.get(obj.getClass().getName());
	    //System.out.println(classInstVarRels);
	    for (Object k : classInstVarRels.keySet() ) {
		LogRelation r =  (LogRelation) classInstVarRels.get(k);
		ProblemRels.put(r.id(),r);
	    }
	}
	//System.out.println(ProblemRels);

    }

    public static void commitModel(Object obj, ArrayList unknowns, ArrayList model) {
	HashMap modelRels = (HashMap) model.get(1);
	for (LogRelation u : (ArrayList<LogRelation>) unknowns) {
	    ArrayList val = (ArrayList) modelRels.get(u.id());
	    //System.out.println(val);
	    if (obj instanceof ArrayList) {
		for (ArrayList v : (ArrayList<ArrayList>) val) {
		    ((ESJList) obj).set((Integer) get2((Integer) v.get(0)), (Integer) get2((Integer) v.get(1)));
		}
	    } else {
		
		Class[] paramTypes = new Class[1];
		Object[] args = new Object[1];
		paramTypes[0] = u.range();
		//System.out.println("lookup mtd: " + u.instVar() + " " + paramTypes);
		try { 
		    Method m = obj.getClass().getDeclaredMethod(u.instVar(), paramTypes); 
		    //System.out.println(m);
		    for (ArrayList v : (ArrayList<ArrayList>) val) {
			//System.out.println(get2((Integer) v.get(0)));
			//System.out.println(get2((Integer) v.get(1)));
			args[0] = get2((Integer) v.get(1));
			m.invoke(get2((Integer) v.get(0)),args);
		    }
		} catch (Exception e) {
		    System.out.println("duh" + e);
		}
		
	    }
	    
	}
    }
    

}
