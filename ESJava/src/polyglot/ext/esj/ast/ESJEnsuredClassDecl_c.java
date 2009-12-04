package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.ast.*;
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
public class ESJEnsuredClassDecl_c extends JL5ClassDecl_c
    implements ESJEnsuredClassDecl {

    protected Expr ensuresExpr;

    public ESJEnsuredClassDecl_c(Position pos, FlagAnnotations fl, String name, 
				 TypeNode superType, List interfaces, ClassBody body, 
				 List<ParamTypeNode> paramTypes) {
        super(pos, fl, name, superType, interfaces, body, paramTypes);
	//this.ensuresExpr = ensuresExpr;
    }
    
    public Expr ensuresExpr() {
	return ensuresExpr;
    }
    /*
    protected ClassDecl reconstruct(TypeNode superClass, List interfaces, ClassBody body,
				    List annotations, List paramTypes, Expr ensuresExpr) {
        if (superClass != this.superClass || !CollectionUtil.equals(interfaces, this.interfaces)
                || body != this.body || !CollectionUtil.equals(annotations, this.annotations)
	        || ensuresExpr != this.ensuresExpr
                || !CollectionUtil.equals(paramTypes, this.paramTypes)) {
            ESJEnsuredClassDecl_c n = (ESJEnsuredClassDecl_c) copy();
            n.superClass = superClass;
            n.interfaces = TypedList.copyAndCheck(interfaces, TypeNode.class, false);
            n.body = body;
            n.annotations = TypedList.copyAndCheck(annotations, AnnotationElem.class, false);
            n.paramTypes = paramTypes;
	    n.ensuresExpr = ensuresExpr;
            return n;
        }
        return this;
    }

    public Node visitChildren(NodeVisitor v) {
        List annots = visitList(this.annotations, v);
        List paramTypes = visitList(this.paramTypes, v);
        TypeNode superClass = (TypeNode) visitChild(this.superClass, v);
        List interfaces = visitList(this.interfaces, v);
        ClassBody body = (ClassBody) visitChild(this.body, v);
	Expr ensuresExpr = (Expr) visitChild(this.ensuresExpr, v);
        return reconstruct(superClass, interfaces, body, annots, paramTypes, ensuresExpr);
    }
    */

}