package polyglot.ext.esj.ast;

import polyglot.parse.VarDeclarator;
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
    protected ESJQuantifyClauseExpr quantClauseExpr;

    public ESJPredMethodDecl_c(Position pos, FlagAnnotations flags,
			       TypeNode returnType, String name,
			       List formals,
			       List throwTypes, Block body, List paramTypes, String quantMtdId, boolean quantKind,
			       String quantVarN, List quantVarD, Expr quantListExpr, ESJQuantifyClauseExpr quantClauseExpr) {
	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes);
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

    public ESJQuantifyClauseExpr quantClauseExpr() {
	return quantClauseExpr;
    }

    /** Reconstruct the method. */
    protected MethodDecl_c reconstruct(TypeNode returnType, List formals,
				       List throwTypes, Block body, Expr quantListExpr, 
				       ESJQuantifyClauseExpr quantClauseExpr, List quantVarD) {
	if (returnType != this.returnType ||
	    ! CollectionUtil.equals(formals, this.formals) ||
	    quantListExpr != this.quantListExpr ||
	    quantClauseExpr != this.quantClauseExpr ||
	    ! CollectionUtil.equals(throwTypes, this.throwTypes) ||
	    body != this.body) {
	    ESJPredMethodDecl_c n = (ESJPredMethodDecl_c) copy();
	    n.formals = formals; //TypedList.copyAndCheck(throwTypes, Formal.class, true);
	    n.throwTypes = TypedList.copyAndCheck(throwTypes, TypeNode.class, true);
	    n.quantListExpr = quantListExpr;
	    n.quantClauseExpr = quantClauseExpr;
	    n.quantVarD = TypedList.copyAndCheck(quantVarD, LocalDecl.class, true);
	    n.body = body;
	    return n;
	}

	return this;
    }
    
    // Visit the children of the method. 
    public Node visitChildren(NodeVisitor v) {
	TypeNode returnType = (TypeNode) visitChild(this.returnType, v);
	List formals = visitList(this.formals, v);
	Expr quantListExpr = (Expr) visitChild(this.quantListExpr, v);
	ESJQuantifyClauseExpr quantClauseExpr = (ESJQuantifyClauseExpr) visitChild(this.quantClauseExpr, v);
	//List quantVarD = visitList(this.quantVarD, v);
	List throwTypes = visitList(this.throwTypes, v);
	Block body = (Block) visitChild(this.body, v);
	return reconstruct(returnType, formals, throwTypes, body, quantListExpr, 
			   quantClauseExpr, this.quantVarD);
    }

    public Node typeCheck(TypeChecker tc) throws SemanticException {
	return super.typeCheck(tc);
    }

    
    public Context enterScope(Node child, Context c) {

	c.addVariable(quantClauseExpr.quantVarI());
	//child.addDecls(c);

	for (Formal f : (List<Formal>) formals) {
	    c.addVariable(c.typeSystem().localInstance(null,  flags(),f.declType(), f.name()));
	}
	
        return super.enterScope(child, c);
    }
    

}
