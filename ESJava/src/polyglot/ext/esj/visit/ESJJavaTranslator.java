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

    public ESJJavaTranslator(Job job, TypeSystem ts, NodeFactory jlnf) {
	super(job, ts, jlnf);
	this.nf = (ESJNodeFactory) jlnf;
	//System.out.println("init Translating...");
    }


    public ESJEnsuredClassDecl DesugarEnsuredClassDecl (ESJEnsuredClassDecl classDecl) throws SemanticException {
	return classDecl;
    }
    
    public JL5MethodDecl DesugarEnsuredMethodDecl (ESJEnsuredMethodDecl methodDecl) throws SemanticException {

	List extraMtdBody = new TypedList(new LinkedList(), Stmt.class, false);
	Expr assertExpr = nf.FormulaBinary(null, 
					   nf.Call(null, null, "verifyInvariants", 
						   new TypedList(new LinkedList(), Expr.class, false)), 
					   Binary.COND_AND, 
					   methodDecl.ensuresExpr());
	extraMtdBody.add(nf.Eval(null, 
				 nf.Call(null, null, "setPrime", 
					 new TypedList(new LinkedList(), Expr.class, false))));
	extraMtdBody.addAll(methodDecl.body().statements());
	extraMtdBody.add(nf.JL5Assert(null, assertExpr, null));

	List catches = new TypedList(new LinkedList(), Catch.class, false);
	Block extraMtdBlock = nf.Block(null, extraMtdBody);
	List catchBody = new TypedList(new LinkedList(), Stmt.class, false);
	catchBody.add(nf.Eval(null, nf.Call(null, nf.Local(null,"rte"), "printStackTrace",
					      new TypedList(new LinkedList(), Expr.class, false))));

	catchBody.add(nf.Eval(null, nf.Call(null, null, methodDecl.name() + "_fallback",
					      new TypedList(new LinkedList(), Expr.class, false))));
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
	boolean quantKind = methodDecl.quantKind();
	String quantVarN = methodDecl.quantVarN();
	Expr quantList = methodDecl.quantListExpr();
	List quantVarD = methodDecl.quantVarD();
	ESJQuantifyClauseExpr quantExpr = methodDecl.quantClauseExpr();
	List extraMtdBody = new TypedList(new LinkedList(), Stmt.class, false);
	List quantClauseStmts = new TypedList(new LinkedList(), Stmt.class, false);
	Expr quantMainIfExpr = quantKind ? nf.Unary(null, Unary.NOT, quantExpr.expr()) : quantExpr.expr();
	Stmt quantMainStmt = nf.JL5If(null, quantMainIfExpr, 
				      nf.JL5Return(null, nf.BooleanLit(null, !quantKind)), null);
	quantClauseStmts.add(quantMainStmt);	    
	Stmt forLoopBody = nf.Block(null, quantClauseStmts);
	Stmt forLoop = nf.ExtendedFor(null,quantVarD, quantList, quantMainStmt);
	extraMtdBody.add(forLoop);
	extraMtdBody.add(nf.JL5Return(null, nf.BooleanLit(null, quantKind)));
	Block extraMtdBlock = nf.Block(null, extraMtdBody);
	methodDecl = (ESJPredMethodDecl) methodDecl.body(extraMtdBlock);

	return (JL5MethodDecl) methodDecl;

    }


    public ESJLogPredMethodDecl DesugarLogPredMethodDecl(ESJLogPredMethodDecl methodDecl) throws SemanticException {
	return (ESJLogPredMethodDecl) toLogicExpr(methodDecl);
    }

    public Node toLogicExpr(Node r) throws SemanticException {

	//System.out.println("Hi. We've got a: " + r.getClass());
	//System.out.println(r);
	if (r instanceof ESJLogPredMethodDecl) {	 
	    ESJLogPredMethodDecl methodDecl = (ESJLogPredMethodDecl) r;
	    List formals = methodDecl.formals(); /*new TypedList(new LinkedList(), Formal.class, false);
	    for (Formal f : (List<Formal>) methodDecl.formals()) {
		System.out.println("getting type map for: " + f.type().toString());
		formals.add(f.type((TypeNode) JTypeToLog.get(f.type().toString())));
		}*/ //FIXME?
	    Block block = (Block) toLogicExpr(methodDecl.body());
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, ""));
	    FlagAnnotations fl = new FlagAnnotations(); 
	    fl.classicFlags(Flags.NONE);
	    fl.annotations(new TypedList(new LinkedList(), AnnotationElem.class, false));
	    if (!methodDecl.isFallback()) {
		methodDecl = (ESJLogPredMethodDecl) methodDecl.returnType(nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogFormula")));
	    }
	    //System.out.println(block.statements());		
	    return methodDecl.formals(formals).body(block);
	    				    
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
	} else if (r instanceof ESJLogQuantifyExpr) {
	    ESJLogQuantifyExpr q = (ESJLogQuantifyExpr) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    // FIXME
	    Expr qListExpr = (q.quantListExpr() instanceof Special) ? 
		nf.Call(null, null, "range_log", new TypedList(new LinkedList(), Expr.class, false)) : 
		(Expr) toLogicExpr(q.quantListExpr());
	    args.add(nf.BooleanLit(null, q.quantKind()));
	    args.add(nf.Local(null, q.quantVarN()));
	    args.add((Expr) toLogicExpr(q.quantClauseExpr().expr()));
	    return nf.Call(null, qListExpr, "quantifyOp", args);
	} else if (r instanceof ESJQuantifyTypeExpr) {
	    return nf.Call(null, ((ESJQuantifyTypeExpr) r).theType(), "allInstances_log",new TypedList(new LinkedList(), Expr.class, false));
	} else if (r instanceof Return) {
	    return nf.JL5Return(null, (Expr) toLogicExpr(((Return) r).expr()));
	} else if (r instanceof Eval) {
	    return r;
	    //return nf.Eval(null, (Expr) toLogicExpr(((Eval) r).expr()));
	} else if (r instanceof Call) {
	    Call c = (Call) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    for (Expr e : (List<Expr>) c.arguments()) {
		args.add((Expr) toLogicExpr(e));
	    }
	    return nf.Call(null, (Receiver) toLogicExpr(c.target()), c.name() + "_log" , args);
	} else if (r instanceof Field) {
	    return r;
	}  else if (r instanceof ESJQuantVarLocalDecl) {
	    LocalDecl l = (LocalDecl) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, LogObject.genVar_log())); 
	    args.add(nf.StringLit(null, l.type().toString()));
	    return l.type(nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogVar"))).init(nf.JL5New(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogVar")), args, null, new TypedList(new LinkedList(), TypeNode.class, false ))); //FIXME
	} else if (r instanceof JL5LocalDecl) {
	    LocalDecl l = (LocalDecl) r;

	    List args4 = new TypedList(new LinkedList(), Expr.class, false);
	    args4.add(nf.StringLit(null, "&&"));
	    args4.add((Expr) toLogicExpr(l.init()));

	    Expr e1 = nf.Call(null, null, "verifyInvariants_log", new TypedList(new LinkedList(), Expr.class, false));
	    Expr probFormula = nf.Call(null, e1, "formulaOp", args4);

	    return l.type(nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogFormula"))).init(probFormula);
	} else if (r instanceof LocalDecl) {
	    LocalDecl l = (LocalDecl) r;
	    return r;
	} else if (r instanceof Local) {
	    return r;
	}  else if (r instanceof Special) {
	    return r;
	} else if (r instanceof IntLit) {
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, "" + ((IntLit) r).value() ));
	    return nf.JL5New(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogInt")), args, null, new TypedList(new LinkedList(), TypeNode.class, false));
	} else if (r instanceof StringLit) {
	    return r;
	} else if (r instanceof BooleanLit) {
	    return r;
	} else if (r instanceof JL5CanonicalTypeNode) {
	    return r;
	}
	/*else if (r instanceof Expr) {
	    return r;
	    }*/
	else {
	    throw new RuntimeException("Don't know how to convert " + r.getClass() +
				       " to a Logic expression.");
				       }
	}

    // quantify expr desugars to a method call (defined above)
    public Expr DesugarQuantifyExpr (ESJQuantifyExpr a)  {
	boolean quantKind = a.quantKind();
	String quantId = a.parentMethod().name()  + "_" + a.id();
	String quantVarN = a.quantVarN();
	Expr quantList = a.quantListExpr();
	ESJQuantifyClauseExpr quantExpr = a.quantClauseExpr();
	List args = new TypedList(new LinkedList(), Expr.class, false);
	for(Formal f : (List<Formal>)(a.parentMethod().formals())) {
	    args.add(new Local_c(null,f.name()));
	}
	return nf.Call(null,null,quantId, args);
    }

    public Expr DesugarQuantifyTypeExpr (ESJQuantifyTypeExpr a)  {
	return nf.Call(null, a.theType(), "allInstances",new TypedList(new LinkedList(), Expr.class, false));
    }

    protected Node clearPureFlag(MethodDecl md) {
      return md.flags(md.flags().clear(((ESJTypeSystem)typeSystem()).Pure()));
    }

    protected Node leaveCall(Node n) throws SemanticException {
	if (n instanceof ESJEnsuredClassDecl) {	    
	    return super.leaveCall(DesugarEnsuredClassDecl((ESJEnsuredClassDecl)n));
	} else if (n instanceof ESJPredMethodDecl) {	    
	    return super.leaveCall(DesugarPredMethodDecl((ESJPredMethodDecl)n));
	} else if (n instanceof ESJLogPredMethodDecl) {	    
	    return super.leaveCall(DesugarLogPredMethodDecl((ESJLogPredMethodDecl)n));
	} else if (n instanceof ESJEnsuredMethodDecl) {
	    return super.leaveCall(DesugarEnsuredMethodDecl((ESJEnsuredMethodDecl)n));
	} else if (n instanceof ESJQuantifyExpr) {
	    return super.leaveCall(DesugarQuantifyExpr((ESJQuantifyExpr)n));
	} else if (n instanceof ESJQuantifyTypeExpr) {
	    return super.leaveCall(DesugarQuantifyTypeExpr((ESJQuantifyTypeExpr)n));
	    }
	else { 	
	    return super.leaveCall(n);
	}
    }


    /*
    public TypeNode array (TypeNode n, int dims) throws Exception
  {
    if (dims > 0)
      {
	if (n instanceof CanonicalTypeNode)
	  {
	    Type t = ((CanonicalTypeNode) n).type ();
	      return nf.CanonicalTypeNode (null, ts.arrayOf (t, dims));
	  }
	return nf.ArrayTypeNode (null, array (n, dims - 1));
      }
    else
      {
	return n;
      }
  }
*/

}



