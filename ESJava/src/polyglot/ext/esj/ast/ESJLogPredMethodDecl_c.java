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

    protected String quantMtdId;
    protected boolean quantKind;
    protected String quantVarN;
    protected List quantVarD;
    protected Expr quantListExpr;
    protected ESJQuantifyClauseExpr quantClauseExpr;
    protected LocalInstance quantVarI;

    public ESJLogPredMethodDecl_c(Position pos, FlagAnnotations flags,
				  TypeNode returnType, String name,
				  List formals, List throwTypes, Block body, 
				  List paramTypes, boolean quantKind, String quantVarN, List quantVarD, 
				  LocalInstance quantVarI, Expr quantListExpr, ESJQuantifyClauseExpr quantClauseExpr) {

	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes);
	this.quantKind = quantKind;
	this.quantVarN = quantVarN;
	this.quantVarD = quantVarD;
	this.quantVarI = quantVarI;
	this.quantListExpr = quantListExpr;
	this.quantClauseExpr = quantClauseExpr;
    }

    public boolean quantKind() {
	return quantKind;
    }

    public String quantVarN() {
	return quantVarN;
    }

    public List quantVarD() {
	return quantVarD;
    }

    public LocalInstance quantVarI() {
	return quantVarI;
    }

    public Expr quantListExpr() {
	return quantListExpr;
    }

    public ESJQuantifyClauseExpr quantClauseExpr() {
	return quantClauseExpr;
    }


    /** Reconstruct the method. */
    protected MethodDecl_c reconstruct(TypeNode returnType, List formals,
				       List throwTypes, Block body, boolean quantKind, String quantVarN,
				       List quantVarD, Expr quantListExpr, ESJQuantifyClauseExpr quantClauseExpr) {
	if (returnType != this.returnType ||
	    ! CollectionUtil.equals(formals, this.formals) ||
	    ! CollectionUtil.equals(throwTypes, this.throwTypes) ||
	    quantListExpr != this.quantListExpr ||
	    quantClauseExpr != this.quantClauseExpr ||
	    body != this.body) {
	    ESJLogPredMethodDecl_c n = (ESJLogPredMethodDecl_c) copy();
	    n.formals = formals; //TypedList.copyAndCheck(throwTypes, Formal.class, true);
	    n.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
	    n.quantVarD = quantVarD;
	    n.quantVarI = quantVarI;
	    n.quantKind = quantKind;
	    n.quantVarN = quantVarN;
	    n.quantListExpr = quantListExpr;
	    n.quantClauseExpr = quantClauseExpr;
	    n.body = body;
	    return n;
	}

	return this;
    }
    
    // Visit the children of the method. 
    public Node visitChildren(NodeVisitor v) {
	TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
	List formals = visitList(this.formals, v);
	System.out.println("1");
	//List quantVarD = (List) visitList(this.quantVarD, v);
	Expr quantListExpr = (Expr) visitChild(this.quantListExpr, v);
	System.out.println("2a");
	ESJQuantifyClauseExpr quantClauseExpr = (ESJQuantifyClauseExpr) visitChild(this.quantClauseExpr, v);
	List throwTypes = visitList(this.throwTypes, v);
	System.out.println("2");
	Block body = (Block) visitChild(this.body, v);
	System.out.println("3");
	return reconstruct(returnType, formals, throwTypes, body, this.quantKind, this.quantVarN, this.quantVarD, quantListExpr, quantClauseExpr);
    }

    public Context enterScope(Node child, Context c) {

	if (child instanceof Block) { //ESJQuantifyExpr) {
	    /*try {
		 System.out.println("adding: " + quantVarI);
		    System.out.println("\nchild: " + child + "\n" + child.getClass() + "\n" );
		    c.addVariable(quantVarI.type(c.typeSystem().typeForName("polyglot.ext.esj.tologic.LogObject")));

		    } catch (SemanticException e) {}*/

	    c.addVariable(quantVarI);

	    for (Formal f : (List<Formal>) formals) {
		c.addVariable(c.typeSystem().localInstance(null,  flags(),f.declType(), f.name()));
	    }
	}

	if (child instanceof ESJQuantifyClauseExpr){ // || child instanceof ESJLogQuantifyExpr) {

	    /*try {
		 System.out.println("2 adding: " + quantVarI);
		    System.out.println("\nchild: " + child + "\n" + child.getClass() + "\n" );
		    c.addVariable(quantVarI.type(c.typeSystem().typeForName("polyglot.ext.esj.tologic.LogObject")));

		    } catch (SemanticException e) {}*/

	    c.addVariable(quantVarI);

	    for (Formal f : (List<Formal>) formals) {
		c.addVariable(c.typeSystem().localInstance(null,  flags(),f.declType(), f.name()));
	    }

	}
	
	return super.enterScope(child, c);
    }


}
