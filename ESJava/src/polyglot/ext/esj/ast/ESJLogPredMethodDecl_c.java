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

    public ESJLogPredMethodDecl_c(Position pos, FlagAnnotations flags,
				  TypeNode returnType, String name,
				  List formals, List throwTypes, Block body, 
				  List paramTypes) {

	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes);
    }


    /** Reconstruct the method. */

    protected MethodDecl_c reconstruct(TypeNode returnType, List formals,
				       List throwTypes, Block body) {
	if (returnType != this.returnType ||
	    ! CollectionUtil.equals(formals, this.formals) ||
	    ! CollectionUtil.equals(throwTypes, this.throwTypes) ||
	    body != this.body) {
	    ESJLogPredMethodDecl_c n = (ESJLogPredMethodDecl_c) copy();
	    n.formals = formals; //TypedList.copyAndCheck(throwTypes, Formal.class, true);
	    n.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
	    n.body = body;
	    return n;
	}

	return this;
    }


    // Visit the children of the method. 
    public Node visitChildren(NodeVisitor v) {
	TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
	List formals = visitList(this.formals, v);
	List throwTypes = visitList(this.throwTypes, v);
	Block body = (Block) visitChild(this.body, v);
	return reconstruct(returnType, formals, throwTypes, body);
    }


}
