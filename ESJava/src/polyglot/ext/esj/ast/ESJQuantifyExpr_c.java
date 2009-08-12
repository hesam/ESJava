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
public class ESJQuantifyExpr_c extends Expr_c implements ESJQuantifyExpr {

    protected static int idCtr = 0;
    protected boolean quantKind;
    protected String id,quantVarN;
    protected List quantVarD;
    protected Expr quantListExpr;
    protected ESJQuantifyClauseExpr quantClauseExpr;
    protected JL5MethodDecl parentMethod;

    public ESJQuantifyExpr_c(Position pos, boolean quantKind, String quantVarN, List quantVarD, LocalInstance quantVarI, Expr quantListExpr, Expr quantClauseExpr) {
	super(pos);
	this.id = (quantKind ? "univQuantify_": "existQuantify_") + Integer.toString(idCtr++);
	this.quantKind = quantKind;
	this.quantVarN = quantVarN;
	this.quantVarD = quantVarD;
	this.quantListExpr = quantListExpr;
	this.quantClauseExpr = new ESJQuantifyClauseExpr_c(pos, quantVarD, quantVarI, quantClauseExpr);
    }

    public Expr quantListExpr() {
	return quantListExpr;
    }

    public ESJQuantifyClauseExpr quantClauseExpr() {
	return quantClauseExpr;
    }

    public String id() {
	return id;
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

    public JL5MethodDecl parentMethod() {
	return parentMethod;
    }

    public void parentMethod(JL5MethodDecl m) {
	this.parentMethod = m;
    }

    public List acceptCFG(CFGBuilder v, List succs) {
	return new ArrayList();
    }
    
    public Term entry() {
	return null;
    }

    // Reconstruct the pred expr.
    protected ESJQuantifyExpr_c reconstruct(boolean quantKind, String quantVarN, List quantVarD, Expr quantListExpr, ESJQuantifyClauseExpr quantClauseExpr) {
	
	if (quantListExpr != this.quantListExpr || quantClauseExpr != this.quantClauseExpr) {
	    ESJQuantifyExpr_c n = (ESJQuantifyExpr_c) copy();
	    n.quantKind = quantKind;
	    n.quantVarN = quantVarN;
	    n.quantVarD = TypedList.copyAndCheck(quantVarD, LocalDecl.class, true);
	    n.quantListExpr = quantListExpr;
	    n.quantClauseExpr = quantClauseExpr;
	    return n;
	}
	return this;
    }

    // Visit the children of the method. 

    public Node visitChildren(NodeVisitor v) {

	List quantVarD = (List) visitList(this.quantVarD, v);
	Expr quantListExpr = (Expr) visitChild(this.quantListExpr, v);
	ESJQuantifyClauseExpr quantClauseExpr = (ESJQuantifyClauseExpr) visitChild(this.quantClauseExpr, v);
	return reconstruct(this.quantKind, this.quantVarN, quantVarD, quantListExpr, quantClauseExpr);
    }

    
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	ESJQuantifyExpr n = (ESJQuantifyExpr) super.typeCheck(tc);
	n = (ESJQuantifyExpr)n.type(tc.typeSystem().Boolean()); //FIXME
	/*
	System.out.println("ESJQuantifyExpr tc...");
	System.out.println(n);
	System.out.println(n.type());
	System.out.println(quantListExpr);
	//System.out.println(((Expr)(quantListExpr.typeCheck(tc))).type());
	System.out.println(quantClauseExpr);
	//quantClauseExpr = (ESJQuantifyClauseExpr)(quantClauseExpr.typeCheck(tc)); //FIXME
	System.out.println(quantClauseExpr.type());
	*/
	/*
	    // make sure the predicateExpr has type boolean
	if (!(quantClauseExpr.type().isBoolean())) {
	    throw new SemanticException("A quantify clause must have type "
				        + "boolean.", position());
					}*/
	    // make sure that the restrictions on array accesses are met
	//System.out.println("ESJQuantifyExpr tc done");
	return n;
    } 

}

