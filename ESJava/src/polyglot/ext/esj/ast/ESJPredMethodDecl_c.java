package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;

import polyglot.ext.jl5.types.FlagAnnotations;
import polyglot.ext.jl5.visit.JL5AmbiguityRemover;

import polyglot.ext.jl5.ast.JL5MethodDecl_c;

import java.util.*;

/** Adds a predicate to method declarations. **/
public class ESJPredMethodDecl_c extends JL5MethodDecl_c
    implements ESJPredMethodDecl {

    protected String quantMtdId;
    protected boolean quantKind;
    protected String quantVarN;
    protected List quantVarD;
    protected Expr quantListExpr;
    protected Expr quantClauseExpr;

    public ESJPredMethodDecl_c(Position pos, FlagAnnotations flags,
			       TypeNode returnType, String name,
			       List formals,
			       List throwTypes, Block body, String quantMtdId, boolean quantKind,
			       String quantVarN, List quantVarD, Expr quantListExpr, Expr quantClauseExpr) {
	super(pos, flags, returnType, name, formals, throwTypes, body);
	this.quantMtdId = quantMtdId;
	this.quantKind = quantKind;
	this.quantVarN = quantVarN;
	this.quantVarD = quantVarD;
	this.quantListExpr = quantListExpr;
	this.quantClauseExpr = quantClauseExpr;
	
    }
    
    public String id() {
	return quantMtdId;
    }
    
    public boolean quantKind() {
	return quantKind;
    }

    public String quantVar() {
	return quantVarN;
    }

    public List quantVarD() {
	return quantVarD;
    }

    public Expr quantListExpr() {
	return quantListExpr;
    }

    public Expr quantClauseExpr() {
	return quantClauseExpr;
    }

    /** Reconstruct the pred expr. */

    /*
      
      FIXME: investigate.... (from JL5MethodDecl_c.java):

    @Override
    public Context enterScope(Context c) {
        c = super.enterScope(c);
        for (ParamTypeNode pn : paramTypes) {
            c = ((JL5Context)c).addTypeVariable((TypeVariable)pn.type());
        }
        return c;
    }

    @Override
    public Node buildTypes(TypeBuilder tb) throws SemanticException {
        return super.buildTypes(tb);
    }

     */

    protected ESJPredMethodDecl_c reconstruct() { 

	/*(FlagAnnotations flags,
			       TypeNode returnType, String name,
			       List formals,
			       List throwTypes, Block body, String quantMtdId, boolean quantKind,
			       String quantVarN, List quantVarD, Expr quantListExpr, Expr quantClauseExpr) {*/
	return this; //super.reconstruct();
    }


    public Node visitChildren(NodeVisitor v) {
	super.visitChildren(v);
	Expr quantLExpr = (Expr) visitChild(this.quantListExpr, v);
	Expr quantCExpr = (Expr) visitChild(this.quantClauseExpr, v);
	return (reconstruct()); //this.flags, this.returnType, this.name, this.formals, this.throwTypes, this.body, this.quantMtdId, this.quantKind, this.quantVarN, this.quantVarD, this.quantListExpr, this.quantClauseExpr);
	//return this; //super.visitChildren(v);
    }
    

    public Node typeCheck(TypeChecker tc) throws SemanticException {
	return this;
    }

    
}
