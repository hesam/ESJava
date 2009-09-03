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
public class ESJLogQuantifyExpr_c extends Expr_c implements ESJLogQuantifyExpr {

    protected static int idCtr = 0;
    protected FormulaBinary.Operator quantKind;
    protected String id,quantVarN;
    protected List quantVarD;
    protected Expr quantListExpr;
    protected ESJQuantifyClauseExpr quantClauseExpr;
    protected JL5MethodDecl parentMethod;

    public ESJLogQuantifyExpr_c(Position pos, FormulaBinary.Operator quantKind, String quantVarN, List quantVarD, Expr quantListExpr, Expr quantClauseExpr) {
	super(pos);
	this.id = (quantKind == FormulaBinary.ALL ? "univQuantify_": "existQuantify_") + Integer.toString(idCtr++);
	this.quantKind = quantKind;
	this.quantVarN = quantVarN;
	this.quantVarD = quantVarD;
	this.quantListExpr = quantListExpr;
	this.quantClauseExpr = new ESJQuantifyClauseExpr_c(pos, quantVarD, quantClauseExpr);
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

    public FormulaBinary.Operator quantKind() {
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
    protected ESJLogQuantifyExpr_c reconstruct(FormulaBinary.Operator quantKind, String quantVarN, List quantVarD, Expr quantListExpr, ESJQuantifyClauseExpr quantClauseExpr) {
	
	if (quantListExpr != this.quantListExpr || quantClauseExpr != this.quantClauseExpr) {
	    ESJLogQuantifyExpr_c n = (ESJLogQuantifyExpr_c) copy();
	    n.quantKind = quantKind;
	    n.quantVarN = quantVarN;
	    n.quantVarD = quantVarD; //TypedList.copyAndCheck(quantVarD, LocalDecl.class, true);
	    n.quantListExpr = quantListExpr;
	    n.quantClauseExpr = quantClauseExpr;
	    return n;
	}
	return this;
    }

    // Visit the children of the method. 

    public Node visitChildren(NodeVisitor v) {
	//List quantVarD = (List) visitList(this.quantVarD, v);
	Expr quantListExpr = (Expr) visitChild(this.quantListExpr, v);
	ESJQuantifyClauseExpr quantClauseExpr = (ESJQuantifyClauseExpr) visitChild(this.quantClauseExpr, v);
	return reconstruct(this.quantKind, this.quantVarN, this.quantVarD, quantListExpr, quantClauseExpr);
    }

    
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	ESJLogQuantifyExpr n = (ESJLogQuantifyExpr) super.typeCheck(tc);
	n = (ESJLogQuantifyExpr)n.type(tc.typeSystem().Boolean()); //FIXME
	return n;
    } 
    /*
    public Context enterScope(Node child, Context c) {
	
	if (child instanceof ESJQuantifyClauseExpr) {

	    LocalDecl d = (LocalDecl) quantVarD.get(0);
	    System.out.println("hi1: " + d + " " + d.type() + " " + d.declType());
	    //d = d.localInstance(c.typeSystem().localInstance(null,null, d.declType(), d.name()));	   
	    //c.addVariable(d.localInstance());
	    System.out.println("hi1: " + d.localInstance());
	    if (d.declType() != null) {
		this.quantClauseExpr = this.quantClauseExpr().quantVarD(quantVarD);
	    }
	}

	return super.enterScope(child, c);
	}*/


}

