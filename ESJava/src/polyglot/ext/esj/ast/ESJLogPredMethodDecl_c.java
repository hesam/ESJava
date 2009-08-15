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


public class ESJLogPredMethodDecl_c extends JL5MethodDecl_c
    implements ESJLogPredMethodDecl {

    protected LocalInstance quantVarI;
    protected List predExprs;

    public ESJLogPredMethodDecl_c(Position pos, FlagAnnotations flags,
				  TypeNode returnType, String name,
				  List formals, List throwTypes, Block body, 
				  List paramTypes, LocalInstance quantVarI, List predExprs) {

	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes);
	this.quantVarI = quantVarI;
	this.predExprs = predExprs;
    }

    public LocalInstance quantVarI() {
	return quantVarI;
    }


    public List predExprs() {
	return predExprs;
    }

    /** Reconstruct the method. */
    protected MethodDecl_c reconstruct(TypeNode returnType, List formals,
				       List throwTypes, Block body, 
				       List predExprs) {
	if (returnType != this.returnType ||
	    ! CollectionUtil.equals(formals, this.formals) ||
	    ! CollectionUtil.equals(throwTypes, this.throwTypes) ||
	    body != this.body) {
	    ESJLogPredMethodDecl_c n = (ESJLogPredMethodDecl_c) copy();
	    n.formals = formals; //TypedList.copyAndCheck(throwTypes, Formal.class, true);
	    n.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
	    n.quantVarI = quantVarI;
	    n.predExprs = predExprs; //TypedList.copyAndCheck(predExprs, Expr.class, true);

	    n.body = body;
	    return n;
	}

	return this;
    }
    
    // Visit the children of the method. 
    public Node visitChildren(NodeVisitor v) {
	TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
	List formals = visitList(this.formals, v);
	//List predExprs = visitList(this.predExprs, v);
	List throwTypes = visitList(this.throwTypes, v);
	Block body = (Block) visitChild(this.body, v);
	return reconstruct(returnType, formals, throwTypes, body, this.predExprs);
    }

    public Context enterScope(Node child, Context c) {
	if (child instanceof Block) {
	    	    try {
			//for (ESJQuantifyExpr q : (List<ESJQuantifyExpr>) predExprs()) {
		    System.out.println("\nchild: " + child + "\n" + child.getClass() + "\n" );
		    c.addVariable(quantVarI.type(c.typeSystem().typeForName("polyglot.ext.esj.tologic.LogObject")));

		    //}
		} catch (SemanticException e) {}
	}
	
	return super.enterScope(child, c);
    }


}
