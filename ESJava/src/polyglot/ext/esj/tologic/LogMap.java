package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.util.AbstractCollection;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Collection;
import java.util.Iterator;

import java.io.CharArrayWriter;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

import kodkod.ast.Formula;
import kodkod.ast.operator.FormulaOperator;
import kodkod.ast.Expression;
import kodkod.ast.Relation;
import kodkod.ast.IntConstant;
import kodkod.instance.TupleFactory;
import kodkod.instance.TupleSet;
import kodkod.instance.Tuple;
import kodkod.instance.Bounds;
import kodkod.instance.Universe;
import kodkod.instance.Instance;
import kodkod.engine.Solver;
import kodkod.engine.Solution;
import kodkod.engine.satlab.SATFactory;
import kodkod.engine.config.Options;

public class LogMap {

    static SATFactory SolverOpt_Solver = SATFactory.MiniSat;   
    static String SolverOpt_Host = "localhost";
    static int SolverOpt_Port = 9128;
    static String SolverOpt_Flatten = "false";
    static int SolverOpt_SymmetryBreaking = 10;
    static int SolverOpt_debugLevel = 0;
    static boolean SolverOpt_debug1 = false, SolverOpt_debug2 = false;
    static boolean SolverOpt_Kodkod = true;

    static HashMap JtoLog; // = new HashMap(); // Java Objs to Solver Atoms
    static HashMap JtoLog2; // = new HashMap(); // Java Objs to Kodkod (singleton) relations
    static HashMap LogtoJ; // = new HashMap(); 
    static HashMap ProblemRels; // Holds relations for given problem
    static Formula ProblemFunDefs2;
    static ArrayList ProblemUnknowns;
    static HashMap<Object,Relation> ClassRels; // Holds kodkod relations for each class
    static ArrayList<Integer> ProblemAtoms; // = new HashSet(); // kodkod atoms
    static Bounds ProblemBounds; // kodkod bounds
    static TupleFactory ProblemFactory; // kodkod factory
    static HashMap InstVarRels = new HashMap(); // Holds inst var relations for each class
    static HashMap InstVarRels2 = new HashMap(); // Holds inst var Kodkod relations for each class
    static HashMap ClassAtoms = new HashMap(); // Holds atoms for each class
    static HashMap ClassNewInstAtoms = new HashMap(); // Holds atoms for new instances for each class
    static HashMap ClassConstrs = new HashMap(); // Holds class constr for each class
    static HashMap Enums = new HashMap(); // Holds enums
    //static HashMap EnumAtoms = new HashMap(); // Holds atoms for enums
    
    static int AtomCtr = ESJInteger.BoundsSize(); // mapping of objs to number ids
    static int relationizerStep = 0; // time steps get incremented when ensured mtd is run
                                            // used to keep track which objs need to be 
                                            // re-relationized at current time (since may've been
                                            // updated.
    static int clonerStep = 0; //FIXME (should be same as relationizerStep)

    /*
    // insert mappings for null and enums
    static {
	LogtoJ.put(AtomCtr,null);
	LogMap.put1(null,AtomCtr++);
    }
    */

    public static void setNullAtomAt(int i) {
	LogtoJ.put(i,null);
	LogMap.put1(null,i);
    }

    public static int SolverOpt_debugLevel() { return SolverOpt_debugLevel; }
    public static boolean SolverOpt_debug1() { return SolverOpt_debug1; }
    public static boolean SolverOpt_debug2() { return SolverOpt_debug2; }

    public static boolean SolverOpt_Kodkod() { return SolverOpt_Kodkod; }
    public static void SolverOpt_Kodkod(boolean b) { SolverOpt_Kodkod = b; }
    public static void SolverOpt_Solver(SATFactory s) { SolverOpt_Solver = s; }
    public static void SolverOpt_SymmetryBreaking(int s) { SolverOpt_SymmetryBreaking = s; }

    public static void SolverOpt_debugLevel(int l) {
	SolverOpt_debugLevel = l;
	if (l > 0)
	    SolverOpt_debug1 = true;
	if (l > 1)
	    SolverOpt_debug2 = true;
    }

    public static int relationizerStep() { return relationizerStep; }
    public static void incrRelationizerStep() { relationizerStep++; }
    public static int clonerStep() { return clonerStep; }
    public static void incrClonerStep() { clonerStep++; }
    public static HashMap ProblemRels() { return ProblemRels; }
    public static TupleFactory ProblemFactory() { return ProblemFactory; }
    public static Bounds ProblemBounds() { return ProblemBounds; }
    public static HashMap<Object,Relation> ClassRels() { return ClassRels; }
    public static HashMap ClassAtoms() { return ClassAtoms; }
    public static HashMap ClassNewInstAtoms() { return ClassNewInstAtoms; }
    public static void resetMaps() {
	JtoLog = new HashMap();
	LogtoJ = new HashMap();
	JtoLog2 = new HashMap();    
	ProblemAtoms = new ArrayList();       
    }

    public static void initRelationize() {
	// FIXME?	
	/*
	JtoLog = new HashMap();
	LogtoJ = new HashMap();
	ESJInteger.setBounds(ESJInteger.MIN_VALUE,ESJInteger.MAX_VALUE);
	*/
	ProblemRels = new HashMap();
	ClassRels = new HashMap<Object,Relation>();

	AtomCtr = ESJInteger.BoundsSize()+1;
	relationizerStep++;
	clonerStep++;
    }


    public static void ObjToAtomMap(HashMap<Class,Integer> newInstances) {

	if (SolverOpt_debug1)
	    System.out.println("classes: " + ClassAtoms);

	for (Class c : (Set<Class>) ClassAtoms.keySet()) {

	    // make atoms from objs
	    boolean isEnum = c.isEnum();
	    // reset rels FIXME	   
	    if (!isEnum) 
		for (LogRelation r : (Collection<LogRelation>) ((HashMap) InstVarRels.get(c)).values())
		    r.clear();

	    ClassAtoms.put(c, new ArrayList());
	    boolean newObjsAllowed = newInstances != null && newInstances.containsKey(c);
	    if (newObjsAllowed)
		ClassNewInstAtoms.put(c, new ArrayList());
	    if (SolverOpt_debug1 && newObjsAllowed)
		System.out.println(c + " can instantiate " + newInstances.get(c) + " new obj.");

	    newAtoms(c, isEnum, newObjsAllowed ? newInstances.get(c) : 0);
	}
	if (SolverOpt_debug1)
	    System.out.println(" JtoLog --> " + JtoLog);

	//FIXME: move..
	initKodkod();
    }

    public static void initKodkod() {
	// init kodkod problem
	Universe universe = new Universe(LogMap.ProblemAtoms);
	LogMap.ProblemFactory = universe.factory();
	LogMap.ProblemBounds = new Bounds(universe);

	ESJInteger.intBounds_log();
	if (SolverOpt_debug1)
	    System.out.println(ClassRels);
    }

    public static void newAtoms(Class c, boolean isEnum, int newInstances) { // FIXME?
	if (SolverOpt_debug1)
	    System.out.println("initing class: " + c + " (ctr=" + AtomCtr+")" + "\n");

	ArrayList classAs = (ArrayList) ClassAtoms.get(c);
	try {
	    ArrayList objs;
	    Object [] args = new Object[2];
	    args[0] = null;
	    args[1] = false;

	    objs = isEnum ? (ArrayList) Enums.get(c) : ((ESJObject) ((Constructor) ClassConstrs.get(c)).newInstance(args)).allInstances2();
	    if (SolverOpt_debug1)
		System.out.println("objs: " + objs);

	    for (Object obj : objs) {
		classAs.add(AtomCtr);
		LogtoJ.put(AtomCtr,obj);
		LogMap.put1(obj,AtomCtr);
		if (!isEnum) {
		    ESJObject o = (ESJObject) obj;
		    // FIXME? (if new obj instantiated during faulty method...)
		    //if (!o.isCloned())
		    //continue;
		    if (o.old() != null)
			LogMap.put1(o.old(),AtomCtr);
		}
		AtomCtr++;
	    }

	    int i = newInstances;
	    if (i > 0) {
		ArrayList classNIAs = (ArrayList) ClassNewInstAtoms.get(c);
		while (i-- > 0) {
		    int a = AtomCtr++;
		    ProblemAtoms.add(a);
		    classAs.add(a);
		    classNIAs.add(a);
		    LogtoJ.put(a,c);
		}
	    }

	} catch (Exception e) { e.printStackTrace(); System.exit(1); }
    }

    public static void put1(Object key, int value) { 
	JtoLog.put(key,value);
	//String valueStr = "A" + value;
	if (!ProblemAtoms.contains(value)) //Str))
	    ProblemAtoms.add(value); //Str);
    }

    public static int get1(Object key) {

	if (SolverOpt_debug2) {
	    System.out.println("get1: " + key);
	    System.out.println(" --> " + JtoLog.get(key));
	    System.out.println(JtoLog);
	    }
	return (Integer) JtoLog.get(key);
    }

    public static HashSet<Integer> get1s(HashSet<?> s) {
	HashSet<Integer> res = new HashSet<Integer>();
	for (Object o : s)
	    res.add(get1(o));
	return res;		    
    }

    public static Relation get1_log(Object key) { 
	if (JtoLog2.containsKey(key)) 
	    return (Relation) JtoLog2.get(key);
	Relation r = Relation.unary(""+get1(key));
	JtoLog2.put(key,r);
	return r;
    }

    public static void put2(int key, Object value) { 
	LogtoJ.put(key,value);
    }

    public static Object get2(int key) { 
	return LogtoJ.get((Object)key);
    }


    public static Relation bounds_log(Class c, boolean isBoundDef) {
	if (SolverOpt_debug1) {
	    System.out.println("getting log bounds for: " + c);
	    System.out.println(c);
	    System.out.println(ClassAtoms);
	}
	if (ClassRels.containsKey(c))
	    return ClassRels.get(c);
	Relation cRel = Relation.unary(c.getName());	

      	TupleSet cRel_upper = ProblemFactory.noneOf(1);
	ArrayList atoms = (ArrayList) ClassAtoms.get(c);
	for (Object a : atoms)
	    cRel_upper.add(ProblemFactory.tuple(a));
	    
	ProblemBounds.boundExactly(cRel, cRel_upper);
	ClassRels.put(c,cRel);
	
	return cRel;
    }

    public static TupleSet tupleSetBounds_log(Class range, boolean addNull, HashSet<?> modifiableObjects) {
	TupleFactory factory = LogMap.ProblemFactory();
	TupleSet rB = factory.noneOf(1);
	ArrayList atoms = (ArrayList) ClassAtoms.get(range);
	if (modifiableObjects == null)
	    for (Object a : atoms)
		rB.add(ProblemFactory.tuple(a));
	else {
	    for (Object a : atoms)
		if (modifiableObjects.contains(a))
		    rB.add(ProblemFactory.tuple(a));
	    if (ClassNewInstAtoms.containsKey(range)) { 
		ArrayList nAtoms = (ArrayList) ClassNewInstAtoms.get(range);
		for (Object a : nAtoms)
		    rB.add(ProblemFactory.tuple(a));
	    }
	}
	if (addNull)
	    rB.add(factory.tuple(LogMap.get1(null)));
	return rB;
    }

    public static TupleSet tupleSetNewInstBounds_log(Class range) {
	TupleFactory factory = LogMap.ProblemFactory();
	TupleSet rB = factory.noneOf(1);
	ArrayList atoms = (ArrayList) ClassNewInstAtoms.get(range);
	for (Object a : atoms)
	    rB.add(ProblemFactory.tuple(a));
	return rB;
    }

    public static void newClassForLog(Class c) {
	if (!(ClassAtoms.containsKey(c) || c == int.class || c == Integer.class)) {
	    try {
		ClassAtoms.put(c, new ArrayList());
		if (c.isEnum()) {
		    ArrayList es = new ArrayList();
		    for (Object e : c.getEnumConstants())
			es.add(e);
		    Enums.put(c, es);
		} else {
		    Class[] parameterTypes = new Class[2];
		    parameterTypes[0] = LogVar.class;
		    parameterTypes[1] = boolean.class;
		    ClassConstrs.put(c, c.getConstructor(parameterTypes));
		}
	    } catch (Exception e) { System.out.println(e); System.exit(1); }		
	    if (!InstVarRels.containsKey(c)) {
		InstVarRels.put(c, new HashMap());
		InstVarRels2.put(c, new HashMap());
	    }
	}
	
    }

    public static String newInstVarRel(Class c, String instVar, Class domain, Class range, Class indexingDomain, boolean isaList, boolean isaMap, boolean isUnknown, boolean isResultVar) {
	String k = instVar;
	boolean isaCollection = isaList || isaMap;
	int relSize = isaCollection ? 3 : 2;
	if (!isUnknown) {
	    k += "_old";
	    // mark enum and class
	    newClassForLog(range);	  
	}
	String rN = LogMap.shortClassName(c) + "." + k;
	Relation r2 = isResultVar ? Relation.unary(rN) : Relation.nary(rN, relSize);
	LogRelation r = new LogRelation(instVar, domain, range, indexingDomain, r2, isaList, isaMap, isUnknown, isResultVar);
	((HashMap) InstVarRels.get(c)).put(k,r);
	((HashMap) InstVarRels2.get(c)).put(k,r2);
	if (SolverOpt_debug1)
	    System.out.println("InstVarRels:" + InstVarRels);
	return r.id;
    }

    //FIXME
    public static LogRelation instVarRel_log0(Object obj, String instVar) {
	return (LogRelation) ((HashMap) InstVarRels.get(obj.getClass())).get(instVar);
    }

    public static Relation instVarRel_log(Object obj, String instVar) {
	String id = instVarRel_log0(obj, instVar).id(); // marks it involved rel
	return (Relation) ((HashMap) InstVarRels2.get(obj.getClass())).get(instVar);
    }

    //FIXME
    public static LogRelation instVarRel_log0(Class c, String instVar) {
	return (LogRelation) ((HashMap) InstVarRels.get(c)).get(instVar);
    }

    public static Relation instVarRel_log(Class c, String instVar) {
	String id = instVarRel_log0(c, instVar).id(); // marks it involved rel
	return (Relation) ((HashMap) InstVarRels2.get(c)).get(instVar);
    }

    public static LogRelation instVarRelOld_log0(LogRelation r) {
	return (LogRelation) ((HashMap) InstVarRels.get(r.domain())).get(r.instVar()+"_old");
    }

    public static Relation instVarRelOld_log(LogRelation r) {
	return (Relation) ((HashMap) InstVarRels2.get(r.domain())).get(r.instVar()+"_old");
    }

    public static LogRelation getResultVarRel_log0(Object obj, String typeName) {
	return (LogRelation) ((HashMap) InstVarRels.get(obj.getClass())).get("result" + typeName);
    }

    public static Relation getResultVarRel_log(Object obj, String typeName) {
	LogRelation r = getResultVarRel_log0(obj, typeName);
	LogMap.addAsProblemRel(r,r.id());
	return (Relation) ((HashMap) InstVarRels2.get(obj.getClass())).get("result" + typeName);
    }



    public static LogVar objInstVarStr_log(ESJObject obj, String instVar, Class c) {
	Relation s2 = instVarRel_log(obj, instVar);
	Expression s1 = obj.isQuantifyVar() ? 
	    obj.var_log().expression() : objToSingletonRelation_log(obj).expression();
	return new LogVar(s1.join(s2));
    }

    public static LogSet objInstVarSet_log(ESJObject obj, String instVar) {
	Relation s2 = instVarRel_log(obj, instVar);
	Expression s1 = obj.isQuantifyVar() ? 
	    obj.var_log().expression() : objToSingletonRelation_log(obj).expression();
	return new LogSet(s1.join(s2));
    }

    public static LogObject objToSingletonRelation_log(Object obj) {
	    Relation objRel;
	    if (ClassRels.containsKey(obj))
		objRel = ClassRels.get(obj);
	    else {
		Integer objAtom = get1(obj);
		objRel = Relation.unary("A" + objAtom);
		TupleSet obj_upper = ProblemFactory.noneOf(1);
		obj_upper.add(ProblemFactory.tuple(objAtom));
		ProblemBounds.boundExactly(objRel, obj_upper);
		ClassRels.put(obj,objRel);
	    }
	    return new LogObject(objRel);
    }


    public static LogIntAtom intToSingletonRelation_log(Integer i) {
        return new LogIntAtom(IntConstant.constant(i)); //ESJInteger.atom_log(i));
    }
    

    public static Relation null_log() {
	//return new LogObject(ClassRels.get(null));
	return ClassRels.get(null);
    }

   public static LogSet instVarClosure_log(ESJObject obj, boolean isOld, boolean isSimple, boolean isReflexive, String... instVars) {
	//if (SolverOpt_debug1)
	//System.out.println("instVarClosure_log -> idOld: " + isOld + " isVar: " + (obj.var_log() != null));
	String fA = isOld ? "_old" : "";
	Expression fNs = instVarRel_log(obj, instVars[0]+fA);
	for(int i=1;i<instVars.length;i++)
	    fNs = fNs.union(instVarRel_log(obj, instVars[i]+fA));
	Expression s1 = obj.isQuantifyVar() ? 
	    obj.var_log().expression() : objToSingletonRelation_log(obj).expression();
	if (!isSimple)
	    if (isReflexive)
		fNs = fNs.reflexiveClosure();
	    else
		fNs = fNs.closure();
	Expression res = s1.join(fNs).difference(ClassRels.get(null));
	return new LogSet(res, 0, obj.getClass());
    }

    //FIXME: this is not closure: a setmap of fields
    public static LogSet instVarClosure_log(LogSet obj, boolean isOld, boolean isSimple, boolean isReflexive, String... instVars) {
	Class range = obj.range();
	String fA = isOld ? "_old" : "";
	Expression fNs = instVarRel_log(range, instVars[0]+fA);
	for(int i=1;i<instVars.length;i++)
	    fNs = fNs.union(instVarRel_log(range, instVars[i]+fA));
	Expression s1 = obj.expression();
	Expression res = s1.join(fNs);
	res = res.difference(ClassRels.get(null));
	return new LogSet(res);
    }


    public static boolean solve(Object obj, Object formula, Class resultVarType, HashMap<String,String> modifiableFields, HashSet<?> modifiableObjects, long startMethodTime) {
	ProblemFunDefs2 = null;
	ProblemUnknowns = new ArrayList<LogRelation>();
	//ArrayList unknowns = new ArrayList<LogRelation>();
	String spacer = "\n";
	boolean isNonVoid = resultVarType != null;
	Set probRels = new HashSet(ProblemRels.keySet());
	if (SolverOpt_debug1) {
	    System.out.println("problem involves rels: ");
	    for (Object k : probRels) {
		LogRelation r =  (LogRelation) ProblemRels.get(k);
		System.out.println(r.getId() + ": " + r.instVar());
	    }
	    if (modifiableFields != null)
		System.out.println("modifiable fields: " + modifiableFields);
	    if (modifiableObjects != null)
		System.out.println("modifiable objs: " + modifiableObjects);
	}       

	for (Object k : probRels) {
	    LogRelation r =  (LogRelation) ProblemRels.get(k);
	    boolean isModifiableRelation = r.isModifiable(modifiableFields);
	    boolean isUnknown = r.isUnknown() && isModifiableRelation;
	    if (SolverOpt_debug1)
		System.out.println("rel " + r.getId() + " " + r.instVar() + " " + r.isUnknown() + " " + isModifiableRelation);

	    if (!r.isUnknown() || isModifiableRelation)
		r.log(r.getKodkodRel(), isUnknown && modifiableObjects != null ? LogMap.get1s(modifiableObjects) : null, resultVarType, null);
	    else 
		//instVarRelOld_log(r).log(r.getKodkodRel(), null, null);
		r.log(r.getKodkodRel(), null, null, instVarRelOld_log0(r));
	}
	Formula finalFormula = ProblemFunDefs2 == null ? (Formula) formula : Formula.compose(FormulaOperator.AND, ProblemFunDefs2, (Formula) formula);
	//ch.append(csq);
	//ch.flush();
	if (SolverOpt_debug1) {
	    System.out.println("int bits: " + ESJInteger.bitWidth());
	    System.out.println("Bounds: " + ProblemBounds);
	    System.out.println("Formula: " + finalFormula.toString());
	}

	Solver solver = new Solver();
	solver.options().setSolver(SolverOpt_Solver);
	solver.options().setBitwidth(ESJInteger.bitWidth());
	solver.options().setFlatten(false);
	solver.options().setIntEncoding(Options.IntEncoding.TWOSCOMPLEMENT);
	solver.options().setSymmetryBreaking(SolverOpt_SymmetryBreaking);
	solver.options().setSkolemDepth(0);
	System.out.flush();
	System.out.println("waiting for solver...");


	Solution sol = solver.solve(finalFormula,ProblemBounds);
	System.out.println("solver done.");
	if (SolverOpt_debug1)
	    System.out.println(sol);
	else {
	    System.out.println(sol.stats());
	}
	System.out.println("total method invocation time: " + (System.currentTimeMillis() - startMethodTime) + " ms");
	Instance model = sol.instance();
	if (model==null)
	    return false;
	else {
	    commitModel(obj, ProblemUnknowns, model);
	    return true;
	}
    }

    public static void addAsFunDef2(LogRelation r, Formula f) { 
	ProblemFunDefs2 = ProblemFunDefs2 == null ? f : ProblemFunDefs2.and(f);
	ProblemUnknowns.add(r);
    }

    public static void addAsUnknown(LogRelation r) { 
	ProblemUnknowns.add(r);
    }

    public static void addAsProblemRel(LogRelation r, String id) { 
	ProblemRels.put(id,r); 
    }

    public static void addAsProblemRel(Object obj, String instVar) { 
	LogRelation r = instVarRel_log0(obj, instVar);
	ProblemRels.put(r.id(),r);
    }

    public static void commitModel(Object obj, ArrayList unknowns, Instance model) {
	ArrayList val = null;
	HashMap<Integer,Object> newInstances = new HashMap<Integer,Object>();
	for (LogRelation u : (ArrayList<LogRelation>) unknowns) {
	    Iterator<Tuple> iter = model.tuples(u.getKodkodRel()).iterator();
	    if (u.isResultVar) {
		((ESJObject) obj).result(get2((Integer) iter.next().atom(0)));
		continue;
	    }
	    if (obj instanceof ArrayList) {
		while (iter.hasNext()) {
		    Tuple itm = iter.next();
		    ((ESJList<Integer>) obj).set((Integer) get2((Integer) itm.atom(0)), (Integer) get2((Integer) itm.atom(1)));
		}
	    } else {
		if (u.isaListInstVar()) {
		    Class[] paramTypes = new Class[0];
		    Object[] args = new Object[0];
		    Class c = u.domain();
		    try { 
			Method m = c.getDeclaredMethod(u.instVar(), paramTypes);
			Integer lastSeen = null;
			ArrayList l = null;
			while (iter.hasNext()) {
			    Tuple itm = iter.next();
			    Integer last = (Integer) itm.atom(0);
			    if (l == null || lastSeen != last) {
				l = (ArrayList) m.invoke(get2(last),args);
				lastSeen = last;
			    }
			    Integer idx = (Integer) get2((Integer) itm.atom(1));
			    while (idx >= l.size())
				l.add(null);
			    l.set(idx,get2((Integer) itm.atom(2)));
			}
		    } catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		    }		    
		} else if (u.isaMapInstVar()) {
		    Class[] paramTypes = new Class[0];
		    Object[] args = new Object[0];
		    Class c = u.domain();
		    try { 
			Method m = c.getDeclaredMethod(u.instVar(), paramTypes);
			Integer lastSeen = null;
			HashMap map = null;
			while (iter.hasNext()) {
			    Tuple itm = iter.next();
			    Integer last = (Integer) itm.atom(0);
			    if (map == null || lastSeen != last) {
				map = (HashMap) m.invoke(get2(last),args);
				lastSeen = last;
			    }
			    map.put(get2((Integer) itm.atom(1)),get2((Integer) itm.atom(2)));
			}
		    } catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		    }		    
		} else {
		    Class[] paramTypes = new Class[1];
		    Object[] args = new Object[1];
		    paramTypes[0] = /*isResultVar ? Object.class :*/ u.range();
		    Class c = u.domain();
		    try { 
			Method m = c.getDeclaredMethod(u.instVar(), paramTypes); 
			while (iter.hasNext()) {
			    Tuple itm = iter.next();		
			    Integer i0 = (Integer) itm.atom(0);
			    Integer i1 = (Integer) itm.atom(1);
			    Object o0 = get2(i0);
			    Object o1 = get2(i1);
			    if (o1 instanceof Class) { // obj is new. must instantiate it!
				if (newInstances.containsKey(i1))
				    o1 = newInstances.get(i1);
				else { 
				    o1 = instantiateNewObj((Class) o1);
				    newInstances.put(i1, o1);
				}
			    } 
			    args[0] = o1;
			    if (o0 instanceof Class) { // obj is new. must instantiate it!
				if (newInstances.containsKey(i0))
				    o0 = newInstances.get(i0);
				else { 
				    o0 = instantiateNewObj((Class) o0);
				    newInstances.put(i0, o0);
				}
			    } 
			    m.invoke(o0,args);
			}
		    } catch (Exception e) {
			    e.printStackTrace();
			System.exit(1);
		    }
		}
	    }
	    
	}
    }
    
    public static Object instantiateNewObj(Class c) {
	Object [] args = new Object[2];
	args[0] = null;
	args[1] = false;
	Object o = null;
	try {
	    o = ((Constructor) ClassConstrs.get(c)).newInstance(args);
	    //o = c.newInstance();
	} catch (Exception e) { e.printStackTrace(); System.exit(1); }
	return o;
    }

    public static String shortClassName(Class c) { 
	String longName = c.getName();
	int pi = longName.lastIndexOf(46); // char '.'
	return pi == -1 ? longName : longName.substring(pi+1);
    }
}
