package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;

import polyglot.ext.jl5.types.FlagAnnotations;
import polyglot.ext.jl5.visit.JL5AmbiguityRemover;

import polyglot.ext.jl5.types.JL5Context;
import polyglot.ext.jl5.types.TypeVariable;

import polyglot.ext.jl5.ast.*;


import java.util.*;

/** Adds a predicate to method declarations. **/
public class ESJEnsuredMethodDecl_c extends JL5MethodDecl_c
    implements ESJEnsuredMethodDecl {

    protected Expr ensuresExpr;
    protected JL5Formal catchFormal;

    public ESJEnsuredMethodDecl_c(Position pos, FlagAnnotations flags,
				  TypeNode returnType, String name,
				  List formals, List throwTypes, Block body, 
				  List paramTypes, Expr ensuresExpr, 
				  JL5Formal catchFormal) {
	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes);
	this.ensuresExpr = ensuresExpr;
	this.catchFormal = catchFormal;
    }
    
    public Expr ensuresExpr() {
	return ensuresExpr;
    }

    public JL5Formal catchFormal() {
	return catchFormal;
    }

    public boolean ensuresExprHasOld() {
	return ensuresExprHasOldH(ensuresExpr);
    }

    public boolean ensuresExprHasOldH(Node n) {
	if (n instanceof Binary)
	    return ensuresExprHasOldH(((Binary) n).left()) ||
		ensuresExprHasOldH(((Binary) n).left());
	else if (n instanceof Call) { 
	    if (((Call) n).name().equals("old"))
		return true;
	    for (Expr a : (List<Expr>) ((Call) n).arguments())
		if (ensuresExprHasOldH(a))
		    return true;
	    return ensuresExprHasOldH(((Call) n).target()); 
	} 
	else return false;
    }

    /** Reconstruct the method. */
    protected MethodDecl_c reconstruct(TypeNode returnType, List formals,
				       List throwTypes, Block body, Expr ensuresExpr, JL5Formal catchFormal) {
	if (returnType != this.returnType ||
	    ! CollectionUtil.equals(formals, this.formals) ||
	    ensuresExpr != this.ensuresExpr ||
	    ! CollectionUtil.equals(throwTypes, this.throwTypes) ||
	    body != this.body) {
	    ESJEnsuredMethodDecl_c n = (ESJEnsuredMethodDecl_c) copy();
	    n.returnType = returnType;
	    n.formals = TypedList.copyAndCheck(formals, Formal.class, true);
	    n.ensuresExpr = ensuresExpr;
	    n.catchFormal = catchFormal;
	    n.throwTypes = TypedList.copyAndCheck(throwTypes,
						  TypeNode.class, true);
	    n.body = body;
	    return n;
	}

	return this;
    }

      /** Visit the children of the method. */
    public Node visitChildren(NodeVisitor v) {
	TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
	List formals = visitList(this.formals, v);
	Expr ensuresExpr = (Expr) visitChild(this.ensuresExpr, v);
	JL5Formal catchFormal = (JL5Formal) visitChild(this.catchFormal, v);
	List throwTypes = visitList(this.throwTypes, v);
	Block body = (Block) visitChild(this.body, v);
	return reconstruct(returnType, formals, throwTypes, body, ensuresExpr, catchFormal);
    }
    /*
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	return this;
    }
    */

    public Context enterScope(Node child, Context c) {
	if (child instanceof Expr) {
	    for (Formal f : (List<Formal>) formals) {
		c.addVariable(c.typeSystem().localInstance(null, flags(),f.declType(), f.name()));
	    }
	    
	}

	return super.enterScope(child, c);
	}


}