package polyglot.ext.esj.visit;

import polyglot.ext.esj.tologic.*;
import polyglot.visit.*;
import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.esj.ast.*;
import polyglot.types.*;
import polyglot.ext.jl.types.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.esj.types.*;
import polyglot.util.*;

import polyglot.frontend.Job;

//import java.util.*;
import java.util.Hashtable;
import java.util.List;
import java.util.LinkedList;

/** Visitor that translates ESJ AST nodes to Java AST nodes, for
    subsequent output. **/
public class ESJLogicTranslator extends ContextVisitor {

	// String for mangling the name of each dispatchee method
    protected static String dispatcheeStr = "$body";

	// String for making the formal names of the dispatcher method
    public static String argStr = "arg$";
    
    protected ESJNodeFactory nf;

    static Hashtable JTypeToLog = new Hashtable();

    public ESJLogicTranslator(Job job, TypeSystem ts, NodeFactory jlnf) {
	super(job, ts, jlnf);
	this.nf = (ESJNodeFactory) jlnf;
	try {
	    JTypeToLog.put("int", nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogInt"))); 
	    JTypeToLog.put("polyglot.ext.esj.primitives.ESJList", nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogSet"))); 
	} catch (SemanticException e) {
	    
	}
	//System.out.println("init Logic Translating...");
    }

    protected Node leaveCall(Node n) throws SemanticException {
	
	if (n instanceof ESJLogPredMethodDecl) {
	    return super.leaveCall(toLogicExpr(n));
	} else {
	    return super.leaveCall(n);
	}
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

	    System.out.println(block.statements());		
	    return methodDecl.returnType(nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogFormula"))).formals(formals).body(block);
	    				    
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
	    args.add(nf.StringLit(null, b.operator().toString()));
	    args.add((Expr) toLogicExpr(b.right()));
	    return nf.Call(null,(Expr) toLogicExpr(b.left()), "cmpOp", args);
	} else if (r instanceof Binary) {
	    Binary b = (Binary) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, b.operator().toString()));
	    args.add((Expr) toLogicExpr(b.right()));
	    return nf.Call(null,(Expr) toLogicExpr(b.left()), "arithOp", args);
	} else if (r instanceof ESJLogQuantifyExpr) {
	    ESJLogQuantifyExpr q = (ESJLogQuantifyExpr) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    System.out.println("quantify expr: " + q.quantListExpr().type());
	    args.add(nf.BooleanLit(null, q.quantKind()));
	    args.add(nf.Local(null, q.quantVarN()));
	    args.add((Expr) toLogicExpr(q.quantClauseExpr().expr()));
	    return nf.Call(null,(Expr) toLogicExpr(q.quantListExpr()), "quantifyOp", args);
	} else if (r instanceof ESJQuantifyTypeExpr) {
	    return nf.Call(null, ((ESJQuantifyTypeExpr) r).theType(), "allInstances_log",new TypedList(new LinkedList(), Expr.class, false));
	} else if (r instanceof Return) {
	    return nf.JL5Return(null, (Expr) toLogicExpr(((Return) r).expr()));
	} else if (r instanceof Call) {
	    Call c = (Call) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    for (Expr e : (List<Expr>) c.arguments()) {
		args.add((Expr) toLogicExpr(e));
	    }
	    return nf.Call(null, (Receiver) toLogicExpr(c.target()), c.name() + "_log" , args);
	} else if (r instanceof Field) {
	    return r;
	} else if (r instanceof LocalDecl) {
	    LocalDecl l = (LocalDecl) r;
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, LogObject.genVar_log())); 
	    args.add(nf.StringLit(null, l.type().toString()));
	    return l.type(nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogVar"))).init(nf.JL5New(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogVar")), args, null, new TypedList(new LinkedList(), TypeNode.class, false ))); //FIXME
	} else if (r instanceof Local) {
	    return r;	    
	}  else if (r instanceof Special) {
	    return r;
	} else if (r instanceof IntLit) {
	    List args = new TypedList(new LinkedList(), Expr.class, false);
	    args.add(nf.StringLit(null, "" + ((IntLit) r).value() ));
	    return nf.JL5New(null, nf.CanonicalTypeNode(null, ts.typeForName("polyglot.ext.esj.tologic.LogInt")), args, null, new TypedList(new LinkedList(), TypeNode.class, false));
	}
	/*else if (r instanceof Expr) {
	    return r;
	    }*/
	else {
	    throw new RuntimeException("Don't know how to convert " + r.getClass() +
				       " to a Logic expression.");
				       }
	}

        
}



