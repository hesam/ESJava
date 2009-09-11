package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;

import polyglot.ext.jl5.types.FlagAnnotations;
import polyglot.ext.jl5.visit.JL5AmbiguityRemover;

import polyglot.ext.jl5.ast.*;

import java.util.*;


public class ESJLogPredMethodDecl_c extends ESJMethodDecl_c
    implements ESJLogPredMethodDecl {

    protected boolean isFallback;
    protected List quantVarD;

    public ESJLogPredMethodDecl_c(Position pos, FlagAnnotations flags,
				  TypeNode returnType, String name,
				  List formals, List throwTypes, Block body, 
				  List paramTypes, List quantVarD, boolean isPredicate, boolean isFallback) {

	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes, isPredicate);
	this.quantVarD = quantVarD;
	this.isFallback = isFallback;
    }

    public List quantVarD() {
	return quantVarD;
    }

    public boolean isFallback() {
	return isFallback;
    }

    protected MethodDecl_c reconstruct(TypeNode returnType, List formals,
				       List throwTypes, Block body,  
				       List quantVarD, boolean isPredicate, boolean isFallback) {
	if (returnType != this.returnType ||
	    ! CollectionUtil.equals(formals, this.formals) ||
	    ! CollectionUtil.equals(throwTypes, this.throwTypes) ||
	    body != this.body) {
	    ESJLogPredMethodDecl_c n = (ESJLogPredMethodDecl_c) copy();
	    n.formals = formals; //TypedList.copyAndCheck(throwTypes, Formal.class, true);
	    n.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
	    n.quantVarD = TypedList.copyAndCheck(quantVarD, LocalDecl.class, true);
	    n.isPredicate = isPredicate;
	    n.isFallback = isFallback;
	    n.body = body;
	    return n;
	}

	return this;
    }


    // Visit the children of the method. 
    public Node visitChildren(NodeVisitor v) {
	TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
	List formals = visitList(this.formals, v);
	//List quantVarD = visitList(this.quantVarD, v);
	List throwTypes = visitList(this.throwTypes, v);
	Block body = (Block) visitChild(this.body, v);
	return reconstruct(returnType, formals, throwTypes, body, quantVarD, this.isPredicate, this.isFallback);
    }
    /*
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	    // findout what type is quantListExpr list of (can be a subtype a generic...)
	    TypeSystem ts = tc.typeSystem();
	    Type t = (Type) quantListExpr().type();

	    while (! ((JL5ParsedClassType) t).isGeneric()) {
		t = (ReferenceType) ts.superType((ReferenceType) t);
	    }
	    if (t instanceof ParameterizedType) {
		List newVarD = new TypedList(new LinkedList(), LocalDecl.class, false);
		for (LocalDecl d : (List<LocalDecl>) quantVarD) {
		    newVarD.add(d.type(d.type().type((Type) ((ParameterizedType) t).typeArguments().get(0))));
		}
		this.quantVarD = newVarD;
	    }


	    return super.typeCheck(tc);
    }
    */

    public Context enterScope(Node child, Context c) {
	/*
	if (!isFallback && child instanceof Block) {	    
	    Expr e = ((Return) ((Block) child).statements().get(0)).expr();
	    if (e instanceof ESJLogQuantifyExpr) {
		ESJLogQuantifyExpr ch = (ESJLogQuantifyExpr) e;
		System.out.println("ehhmmmm" + ch.quantClauseExpr().expr() + " " + ch.quantVarD() + " " + ch.quantVarD2());
		for (LocalDecl d : (List<LocalDecl>) ch.quantVarD2())
		    c.addVariable(c.typeSystem().localInstance(null, flags(),d.declType(), d.name()));
		
	    }
	    }*/


	return super.enterScope(child, c);
	}


}
