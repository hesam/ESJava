package polyglot.ext.esj.visit;

import polyglot.ext.esj.tologic.*;

import polyglot.visit.*;
import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.esj.ast.*;
import polyglot.types.*;
import polyglot.ext.esj.types.*;
import polyglot.ext.jl5.types.*;
import polyglot.util.*;




import polyglot.frontend.Job;

import java.util.*;

/** Visitor that translates ESJ AST nodes to Java AST nodes, for
    subsequent output. **/
public class ESJJavaTranslator extends ContextVisitor {

	// String for mangling the name of each dispatchee method
    protected static String dispatcheeStr = "$body";

	// String for making the formal names of the dispatcher method
    public static String argStr = "arg$";
    
    protected ESJNodeFactory nf;

    //FIXME 
    //static HashMap LogMtdDecls = new HashMap(); 
    static List<String> quantVars;
    static boolean isLogVarLogPredMtd;

    public ESJJavaTranslator(Job job, TypeSystem ts, NodeFactory jlnf) {
	super(job, ts, jlnf);
	this.nf = (ESJNodeFactory) jlnf;
	//System.out.println("init Translating...");
    }

    // FIXME
    /*
    public JL5ClassDecl DesugarLogVarClassDecl(ESJLogVarClassDecl classDecl) throws SemanticException {
	//System.out.println("logvar class:" + classDecl.logPredMtdDecls());
	List classMs = new TypedList(new LinkedList(), ClassMember.class, false);
	for (ClassMember m : (List<ClassMember>) classDecl.body().members()) {
	    if (m instanceof JL5MethodDecl) {
		JL5MethodDecl md = (JL5MethodDecl) m;
		if (LogMtdDecls.containsKey(md.name())) {
		    JL5MethodDecl ref = (JL5MethodDecl) LogMtdDecls.get(md.name());
		    classMs.add(md.returnType(ref.returnType()).body(ref.body()));
		}
	    } else {
		classMs.add(m);
	    }	    
	}
	
	return (JL5ClassDecl) classDecl.body(nf.JL5ClassBody(null, classMs));
    }
    */

    public JL5MethodDecl DesugarEnsuredMethodDecl (ESJEnsuredMethodDecl methodDecl) throws SemanticException {

	List extraMtdBody = new TypedList(new LinkedList(), Stmt.class, false);
	Expr call1 = nf.Call(null, null, "verifyInvariants", 
			     new TypedList(new LinkedList(), Expr.class, false));
	Expr assertExpr = methodDecl.ensuresExpr() == null ? call1 : 
	    nf.FormulaBinary(null, call1, Binary.COND_AND, methodDecl.ensuresExpr());
	extraMtdBody.add(nf.Eval(null, nf.Call(null, null, "initEnsuredMethod", new TypedList(new LinkedList(), Expr.class, false))));
	extraMtdBody.addAll(methodDecl.body().statements());
	extraMtdBody.add(nf.JL5Assert(null, assertExpr, null));

	List catches = new TypedList(new LinkedList(), Catch.class, false);
	Block extraMtdBlock = nf.Block(null, extraMtdBody);
	List catchBody = new TypedList(new LinkedList(), Stmt.class, false);
	catchBody.add(nf.Eval(null, nf.Call(null, nf.Local(null,"rte"), "printStackTrace",
					      new TypedList(new LinkedList(), Expr.class, false))));
	List args = new TypedList(new LinkedList(), Expr.class, false);
	for (Formal f : (List<Formal>) methodDecl.formals())
	    args.add( nf.Local(null,f.name()));
	catchBody.add(nf.Eval(null, nf.Call(null, null, methodDecl.name() + "_fallback", args)));
	Block catchBlock = nf.Block(null,catchBody);
	catches.add(nf.JL5Catch(null, methodDecl.catchFormal(), catchBlock));
	List tryBody = new TypedList(new LinkedList(), Stmt.class, false);
	tryBody.add(nf.Try(null, extraMtdBlock, catches));
	Block tryCatchBlock = nf.Block(null, tryBody);
	methodDecl = (ESJEnsuredMethodDecl) methodDecl.body(tryCatchBlock);
	return (JL5MethodDecl) methodDecl;
    }


    // quantify expr method desugars into a foreach stmt
    public JL5MethodDecl DesugarPredMethodDecl (ESJPredMethodDecl methodDecl)  {

	String quantMtdId = methodDecl.id();	  
	FormulaBinary.Operator quantKind = methodDecl.quantKind();
	boolean quantKindBool1 = quantKind == FormulaBinary.ALL ? true : false;
	boolean quantKindBool2 = quantKind == FormulaBinary.SOME ? false : true;
	boolean quantKindIsaCount = quantKind == FormulaBinary.ONE ||
	    quantKind == FormulaBinary.LONE;
	String quantVarN = methodDecl.quantVarN();
	Expr quantList = methodDecl.quantListExpr();
	List quantVarD = methodDecl.quantVarD();
	ESJQuantifyClauseExpr quantExpr = methodDecl.quantClauseExpr();
	List extraMtdBody = new TypedList(new LinkedList(), Stmt.class, false);
	List quantClauseStmts = new TypedList(new LinkedList(), Stmt.class, false);
	Expr quantMainIfExpr = quantKindBool1 ? nf.Unary(null, Unary.NOT, quantExpr.expr()) : quantExpr.expr();
	Local quantCount = nf.Local(null, "quantCount");
	Stmt ifExprPart = quantKindIsaCount ? 
	    nf.Eval(null, nf.Unary(null, Unary.POST_INC, quantCount)) : 
	    nf.JL5Return(null, nf.BooleanLit(null, !quantKindBool2));
	Stmt quantMainStmt = nf.JL5If(null, quantMainIfExpr, ifExprPart, null);
	quantClauseStmts.add(quantMainStmt);	    
	if (quantKindIsaCount) {
	    extraMtdBody.add((Stmt) methodDecl.body().statements().get(0));
	    Expr countViolation = nf.Binary(null, quantCount, Binary.GT, nf.IntLit(null, IntLit.INT, 1));
	    Stmt quantMainStmt2 = nf.JL5If(null, countViolation, nf.JL5Return(null, nf.BooleanLit(null, !quantKindBool2)), null);
	    quantClauseStmts.add(quantMainStmt2);	    
	}
	Stmt forLoopBody = nf.Block(null, quantClauseStmts);
	Stmt forLoop = nf.ExtendedFor(null,quantVarD, quantList, forLoopBody);
	extraMtdBody.add(forLoop);
	Stmt retStmt = quantKind == FormulaBinary.ONE ? 
	    nf.JL5Return(null, nf.Binary(null, quantCount, Binary.EQ, nf.IntLit(null, IntLit.INT, 1))) :
	    nf.JL5Return(null, nf.BooleanLit(null, quantKindBool2));
	extraMtdBody.add(retStmt);
	Block extraMtdBlock = nf.Block(null, extraMtdBody);
	methodDecl = (ESJPredMethodDecl) methodDecl.body(extraMtdBlock);

	return (JL5MethodDecl) methodDecl;

    }

    public ESJLogPredMethodDecl DesugarLogPredMethodDecl(ESJLogPredMethodDecl methodDecl) throws SemanticException {
	isLogVarLogPredMtd = methodDecl.isLogVar();
	ESJLogPredMethodDecl res = (ESJLogPredMethodDecl) toLogicExpr(methodDecl);
	//LogMtdDecls.put(res.name(),res);
	return res;
    }

    public Node toLogicExpr(Node r) throws SemanticException {
	//System.out.println("have:" + r.getClass() + " " + r);
	if (r instanceof ESJLogPredMethodDecl) {	 
	    ESJLogPredMethodDecl methodDecl = (ESJLogPredMethodDecl) r;
	    List formals = methodDecl.formals(); /*new TypedList(new LinkedList(), Formal.class, false);
	    for (Formal f : (List<Formal>) methodDecl.formals()) {
		System.out.println("getting type map for: " + f.type().toString());
		formals.add(f.type((TypeNode) JTypeToLog.get(f.type().toString())));
		}*/ //FIXME?
	    List inits = new TypedList(new LinkedList(), Stmt.class, false);
	    //System.out.println(methodDecl.body().statements());
	    quantVars = new ArrayList(); //FIXME
	    if (!methodDecl.isFallback()) {
		Expr e = ((Return) methodDecl.body().statements().get(0)).expr();
		List quantVarD = getQuantVarDs(e); //FIXME	
		for (LocalDecl l : (List<LocalDecl>) quantVarD) {
		    //TypeNode tn = nf.CanonicalTypeNode(null,ts.typeForName(((AmbTypeNode) l.type()).name()));
		    TypeNode tn = l.type();
		    quantVars.add(l.name());
		    List args = new TypedList(new LinkedList(), Expr.class, false);
		    List args2 = new TypedList(new LinkedList(), Expr.class, false);
		    args2.add(nf.StringLit(null, LogObject.genVar_log())); 
		    args2.add(nf.ClassLit(null, tn));
		    TypeNode logVarTN = nf.CanonicalTypeNode(null, ts.typeForName((tn.type().isPrimitive() ? "polyglot.ext.esj.primitives.ESJInteger" : tn.toString())));
		    args.add(nf.JL5New(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogVar")), args2, null, new TypedList(new LinkedList(), TypeNode.class, false)));
		    //TypeNode logVarTN = nf.CanonicalTypeNode(null, ts.typeForName((tn.type().isPrimitive() ? "polyglot.ext.esj.tologic." : tn.toString()) + "LogVar"));
		    
		    inits.add(l.type(logVarTN).init(nf.JL5New(null, logVarTN, args, null, new TypedList(new LinkedList(), TypeNode.class, false))));
		}
	    }
	    Block block = (Block) toLogicExpr(methodDecl.body());
	    inits.addAll(block.statements());
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, ""));
	    FlagAnnotations fl = makeFlagAnnotations(); 
	    fl.classicFlags(Flags.NONE);
	    fl.annotations(new TypedList(new LinkedList(), AnnotationElem.class, false));
	    if (!methodDecl.isFallback()) {
		methodDecl = (ESJLogPredMethodDecl) methodDecl.returnType(nf.CanonicalTypeNode(null, ts.typeForName(methodDecl.isPredicate() ? "polyglot.ext.esj.tologic.LogFormula" : "polyglot.ext.esj.tologic.LogSet")));
	    }
	    return methodDecl.formals(formals).body(nf.Block(null,inits));
	    				    
	} else if (r instanceof Block) {
	    List body = new TypedList(new LinkedList(), Stmt.class, false);
	    for (Stmt s : (List<Stmt>) ((Block) r).statements()) {
		body.add((Stmt) toLogicExpr(s));
	    }
	    return nf.Block(null, body);
	} else if (r instanceof FormulaBinary) {
	    Binary b = (Binary) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, b.operator().toString()));
	    args.add((Expr) toLogicExpr(b.right()));
	    return nf.Call(null,(Expr) toLogicExpr(b.left()), "formulaOp", args);
	} else if (r instanceof CmpBinary) {
	    Binary b = (Binary) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    String binOp = b.operator().equals(Binary.EQ) ? "=" : b.operator().toString();
	    args.add(nf.StringLit(null, binOp));
	    args.add((Expr) toLogicExpr(b.right()));
	    Call c = nf.Call(null,(Expr) toLogicExpr(b.left()), "cmpOp", args);
	    if (b.operator().equals(Binary.NE)) {		
		return nf.Call(null,c, "notOp", 
			       new TypedList(new LinkedList(), Expr.class, false));
	    } else {
		return c;
	    }
	} else if (r instanceof Binary) {
	    Binary b = (Binary) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, b.operator().toString()));
	    args.add((Expr) toLogicExpr(b.right()));
	    return nf.Call(null,(Expr) toLogicExpr(b.left()), "arithOp", args);
	} else if (r instanceof Unary) {
	    Unary u = (Unary) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, u.operator().toString()));
	    return nf.Call(null,(Expr) toLogicExpr(u.expr()), "unaryOp", args);
	} else if (r instanceof JL5Conditional) {
	    JL5Conditional c = (JL5Conditional) r;
	    return nf.JL5Conditional(null, c.cond(), (Expr) toLogicExpr(c.consequent()), (Expr) toLogicExpr(c.alternative()));
	} else if (r instanceof ESJLogQuantifyExpr) {
	    ESJLogQuantifyExpr q = (ESJLogQuantifyExpr) r;
	    boolean quantKindIsaCount = q.quantKind() == FormulaBinary.ONE ||
		q.quantKind() == FormulaBinary.LONE;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    // FIXME
	    Expr qListExpr = (q.quantListExpr() instanceof Special) ? 
		nf.Call(null, null, "range_log", new TypedList(new LinkedList(), Expr.class, false)) : 
		(Expr) toLogicExpr(q.quantListExpr());
	    args.add(nf.BooleanLit(null, quantKindIsaCount));
	    args.add(nf.StringLit(null, q.quantKind().toString()));
	    args.add(nf.Field(null, nf.Local(null, q.quantVarN()), "var_log"));
	    args.add((Expr) toLogicExpr(q.quantClauseExpr().expr()));
	    return nf.Call(null, qListExpr, "quantifyOp", args);
	} else if (r instanceof ESJQuantifyTypeExpr) {
	    return nf.Call(null, nf.CanonicalTypeNode(null, ts.typeForName(((ESJQuantifyTypeExpr) r).theType())), "allInstances_log",new TypedList(new LinkedList(), Expr.class, false));
	    
	} else if (r instanceof Return) {
	    return nf.JL5Return(null, (Expr) toLogicExpr(((Return) r).expr()));
	} else if (r instanceof Eval) {
	    return r;
	} else if (r instanceof JL5Assert) {
	    return r;
	} else if (r instanceof ESJFieldClosureCall) {
	    ESJFieldClosureCall c = (ESJFieldClosureCall) r;
	    return instVarClosureGet_log(c.target(), c.arguments());
	} else if (r instanceof ESJFieldCall) {
	    ESJFieldCall c = (ESJFieldCall) r;
	    return instVarGet_log(c.target(), c.name(), c.type().isReference());
	} else if (r instanceof Call) {
	    Call c = (Call) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    for (Expr e : (List<Expr>) c.arguments()) {
		 args.add((Expr) toLogicExpr(e));
	    }
	    if (c.target() instanceof Local && quantVars.contains(((Local) c.target()).name())) {
		return nf.Call(null, c.target(), c.name() + "_log2" , args);
	    } else {
		return nf.Call(null, 
			       (c.target() == null || 
				//c.target() instanceof Field ||
				c.target() instanceof Special) ?
			       c.target() : 
			       (Receiver) toLogicExpr(c.target()), 
			       c.name() + "_log" , args);
	    }
	} else if (r instanceof Field) {
	    Field f = (Field) r;
	    return instVarGet_log(f.target(), f.name(), f.type().isReference());
	} else if (r instanceof JL5LocalDecl) {
	    LocalDecl l = (LocalDecl) r;
	    Expr e1 = nf.Call(null, null, "verifyInvariants_log", new TypedList(new LinkedList(), Expr.class, false));
	    Expr probFormula;
	    if (l.init() == null) {
		probFormula = e1;
	    } else {
		List args4 = new TypedList(new LinkedList(), Expr.class, false);
		args4.add(nf.StringLit(null, "&&"));
		args4.add((Expr) toLogicExpr(l.init()));
		probFormula = nf.Call(null, e1, "formulaOp", args4);
	    }
	    return l.type(nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogFormula"))).init(probFormula);
	} else if (r instanceof LocalDecl) {
	    LocalDecl l = (LocalDecl) r;
	    return r;
	} else if (r instanceof Local) {
	    Local l = (Local) r;
	    //return r;
	    return quantVars.contains(l.name()) ? nf.Field(null, l, "var_log") : r; //FIXME?
	}  else if (r instanceof Special) {
	    return isLogVarLogPredMtd ? nf.Field(null, (Special) r, "var_log") : r; //FIXME
	} else if (r instanceof IntLit) {
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, "" + ((IntLit) r).value() ));
	    return nf.JL5New(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogInt")), args, null, new TypedList(new LinkedList(), TypeNode.class, false));
	} else if (r instanceof StringLit) {
	    return r;
	} else if (r instanceof NullLit) {
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    return nf.Call(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogMap")), "null_log", args);
	} else if (r instanceof BooleanLit) {
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, "" + ((BooleanLit) r).value() ));
	    return nf.JL5New(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogFormula")), args, null, new TypedList(new LinkedList(), TypeNode.class, false));
	} else if (r instanceof CanonicalTypeNode) {
	    return r;
	}
	else {
	    throw new RuntimeException("Don't know how to convert " + r.getClass() +
				       " to a Logic expression.");
				       }
	}

    // quantify expr desugars to a method call (defined above)
    public Expr DesugarQuantifyExpr (ESJQuantifyExpr a) {

	String quantId = a.parentMethod().name()  + "_" + a.id();
	List args = new TypedList(new LinkedList(), Expr.class, false);
	for(Formal f : (List<Formal>)(a.parentMethod().formals())) {
	    args.add(new Local_c(null,f.name()));
	}
	for(LocalDecl d : (List<LocalDecl>)(a.quantVarD2())) {
	    args.add(new Local_c(null,d.name()));
	}	
	return nf.Call(null,null,quantId, args);
    }

    public Expr DesugarQuantifyTypeExpr (ESJQuantifyTypeExpr a) throws SemanticException  {
	return nf.Call(null, nf.CanonicalTypeNode(null, ts.typeForName(a.theType())), "allInstances",new TypedList(new LinkedList(), Expr.class, false));
    }

    public Expr DesugarESJFieldClosure (ESJFieldClosure f) throws SemanticException  {
	List args = new TypedList(new LinkedList(), Expr.class, false);
	ESJFieldClosure fc = (ESJFieldClosure) f;
	args.add(nf.BooleanLit(null, fc.isReflexive()));	
	for (int i=0; i<fc.multiNames().size(); i++) 
	    args.add(nf.StringLit(null, (String) fc.multiNames().get(i)));
	Expr res = nf.ESJFieldClosureCall(null, fc.target(), "fieldsClosure", args);
	return res;
    }

    protected Node clearPureFlag(MethodDecl md) {
      return md.flags(md.flags().clear(((ESJTypeSystem)typeSystem()).Pure()));
    }

    /*
    protected NodeVisitor enterCall(Node parent, Node n) throws SemanticException {
	return super.enterCall(parent,n);
    }
    */

    protected Node leaveCall(Node n) throws SemanticException {
	//System.out.println("have: " + n.getClass() + " " + n);

	/*if (n instanceof ESJLogVarClassDecl) {	  
	    return super.leaveCall(DesugarLogVarClassDecl((ESJLogVarClassDecl) n));
	    } else*/ if (n instanceof ESJPredMethodDecl) {	    
	    return super.leaveCall(DesugarPredMethodDecl((ESJPredMethodDecl)n));
	} else if (n instanceof ESJLogPredMethodDecl) {	    
	    return super.leaveCall(DesugarLogPredMethodDecl((ESJLogPredMethodDecl)n));
	} else if (n instanceof ESJEnsuredMethodDecl) {
	    return super.leaveCall(DesugarEnsuredMethodDecl((ESJEnsuredMethodDecl)n));
	} else if (n instanceof ESJQuantifyExpr) {
	    return super.leaveCall(DesugarQuantifyExpr((ESJQuantifyExpr)n));
	} else if (n instanceof ESJQuantifyTypeExpr) {
	    return super.leaveCall(DesugarQuantifyTypeExpr((ESJQuantifyTypeExpr)n));
	} else if (n instanceof ESJFieldClosure) {
	    return super.leaveCall(DesugarESJFieldClosure((ESJFieldClosure)n));
	    }
	else {
	    return super.leaveCall(n);
	}
    }

    public List getQuantVarDs(Expr e) {
	List quantVarD = new TypedList(new LinkedList(), LocalDecl.class, false);
	getQuantVarDsHelper(e, quantVarD);
	return quantVarD;
    }
    
    void getQuantVarDsHelper(Expr e, List quantVarD) {
	if (e instanceof FormulaBinary) {
	    FormulaBinary b = (FormulaBinary) e;
	    getQuantVarDsHelper(b.left(), quantVarD);
	    getQuantVarDsHelper(b.right(), quantVarD);
	} else if (e instanceof Conditional) {
	    Conditional c = (Conditional) e;
	    getQuantVarDsHelper(c.consequent(), quantVarD);
	    getQuantVarDsHelper(c.alternative(), quantVarD);
	} else if (e instanceof ESJLogQuantifyExpr) {
	    ESJLogQuantifyExpr e2 = (ESJLogQuantifyExpr) e;
	    quantVarD.addAll(e2.quantVarD());
	    quantVarD.addAll(e2.quantVarD2());
	}
    }

    public Node instVarGet_log(Receiver target, String name, boolean isReference) throws SemanticException {

	    List instVarGetArgs = new TypedList(new LinkedList(), Expr.class, false);
	    instVarGetArgs.add((Receiver) toLogicExpr(target));
	    instVarGetArgs.add(nf.StringLit(null, name));
	    return nf.Call(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogMap")), isReference ? "objInstVar_log" : "intInstVar_log", instVarGetArgs);
    }

    public Node instVarClosureGet_log(Receiver target, List origArgs) throws SemanticException {

	    List instVarGetArgs = new TypedList(new LinkedList(), Expr.class, false);
	    instVarGetArgs.add((Receiver) toLogicExpr(target));
	    //instVarGetArgs.add(target);
	    instVarGetArgs.addAll(origArgs);
	    return nf.Call(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogMap")), "instVarClosure_log", instVarGetArgs);
    }
  
    FlagAnnotations makeFlagAnnotations() {
	      FlagAnnotations fl2 = new FlagAnnotations(); 
              fl2.classicFlags(Flags.NONE);
              fl2.annotations(new TypedList(new LinkedList(), AnnotationElem.class, false));
	      return fl2;
    }	      

}



