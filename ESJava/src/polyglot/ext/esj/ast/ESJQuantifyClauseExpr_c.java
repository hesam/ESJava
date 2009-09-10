package polyglot.ext.esj.ast;

import java.util.*;
import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;


// A temporary class used for passing an ordered dispatch method declaration.
public class ESJQuantifyClauseExpr_c extends Expr_c implements ESJQuantifyClauseExpr {

    protected List quantVarD;
    protected Expr expr;

    public ESJQuantifyClauseExpr_c(Position pos, List quantVarD, Expr expr) {
	super(pos);
	this.quantVarD = quantVarD;
	this.expr = expr;
    }

    public List quantVarD() {
	return quantVarD;
    }

    public Expr expr() {
	return expr;
    }

    public ESJQuantifyClauseExpr quantVarD(List l) {
	this.quantVarD = l;
	return this;
    }

    public Expr expr(Expr e) {
	this.expr = e;
	return this;
    }


    public List acceptCFG(CFGBuilder v, List succs) {
	return new ArrayList();
    }
    
    public Term entry() {
	return null;
    }

    // Reconstruct the pred expr.
    protected ESJQuantifyClauseExpr_c reconstruct(List quantVarD, Expr expr) {
	if (expr != this.expr) {
	    ESJQuantifyClauseExpr_c n = (ESJQuantifyClauseExpr_c) copy();
	    n.quantVarD = quantVarD; //TypedList.copyAndCheck(quantVarD, LocalDecl.class, true);
	    n.expr = expr;

	    return n;
	} else {
	    return this;
	}
    }


      // Visit the children of the method.
    public Node visitChildren(NodeVisitor v) {
	//List quantVarD = (List) visitList(this.quantVarD, v);
	
	Expr expr = (Expr) visitChild(this.expr, v);
	return reconstruct(this.quantVarD, expr);
    }

    
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	//System.out.println("ESJQuantifyClauseExpr tc...");
	ESJQuantifyClauseExpr n = (ESJQuantifyClauseExpr) super.typeCheck(tc);	
	n = (ESJQuantifyClauseExpr)n.type(tc.typeSystem().Boolean()); //FIXME
	n.expr(n.expr().type(tc.typeSystem().Boolean()));
	/*
	    // make sure the predicateExpr has type boolean
	if (!(quantClauseExpr.type().isBoolean())) {
	    throw new SemanticException("A quantify clause must have type "
				        + "boolean.", position());
					}*/
	    // make sure that the restrictions on array accesses are met
	//System.out.println("ESJQuantifyClauseExpr tc done");
	return super.typeCheck(tc);
	//return n;
    } 

}

