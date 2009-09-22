package polyglot.ext.esj.ast;

import java.util.*;
import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.jl5.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;


// A temporary class used for passing an ordered dispatch method declaration.
public class ESJLogQuantifyExpr_c extends Expr_c implements ESJLogQuantifyExpr {

    protected static int idCtr = 0;
    protected FormulaBinary.Operator quantKind;
    protected String id,quantVarN;
    protected List quantVarD, quantVarD2;
    protected Expr quantListExpr;
    protected ESJQuantifyClauseExpr quantClauseExpr;
    protected ESJLogPredMethodDecl parentMethod;
    protected boolean isComprehension;

    public ESJLogQuantifyExpr_c(Position pos, FormulaBinary.Operator quantKind, String quantVarN, List quantVarD, List quantVarD2, Expr quantListExpr, Expr quantClauseExpr, ESJLogPredMethodDecl parentMethod, boolean isComprehension) {
	super(pos);
	this.id = (isComprehension ? "setComprehension_" : quantKind == FormulaBinary.ALL ? "univQuantify_": "existQuantify_") + Integer.toString(idCtr++);
	this.quantKind = quantKind;
	this.quantVarN = quantVarN;
	this.quantVarD = quantVarD;
	this.quantVarD2 = quantVarD2;
	this.parentMethod = parentMethod;
	this.quantListExpr = quantListExpr;
	this.isComprehension = isComprehension;
	this.quantClauseExpr = new ESJQuantifyClauseExpr_c(pos, quantClauseExpr);
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

    public List quantVarD2() {
	return quantVarD2;
    }

    public ESJLogPredMethodDecl parentMethod() {
	return parentMethod;
    }

    public boolean isComprehension() {
	return isComprehension;
    }

    public void parentMethod(ESJLogPredMethodDecl m) {
	this.parentMethod = m;
    }

    public void quantVarD2(List quantVarD2) {
	this.quantVarD2 = quantVarD2;
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
	List quantVarD = (List) visitList(this.quantVarD, v);
	//List quantVarD2 = (List) visitList(this.quantVarD2, v);
	Expr quantListExpr = (Expr) visitChild(this.quantListExpr, v);
	ESJQuantifyClauseExpr quantClauseExpr = (ESJQuantifyClauseExpr) visitChild(this.quantClauseExpr, v);
	return reconstruct(this.quantKind, this.quantVarN, quantVarD, quantListExpr, quantClauseExpr);
    }

    
    public Node typeCheck(TypeChecker tc) throws SemanticException { //FIXME

	JL5TypeSystem ts = (JL5TypeSystem) tc.typeSystem();
	NodeFactory nf = tc.nodeFactory();
	ESJLogQuantifyExpr n = (ESJLogQuantifyExpr) super.typeCheck(tc);

	if (isComprehension) {
	    Type t = ts.typeForName("polyglot.ext.esj.primitives.ESJSet"); 
	    ParameterizedType pt = ts.parameterizedType((JL5ParsedClassType) t);
	    ArrayList<Type> at = new ArrayList<Type>();
	    at.add(((LocalDecl) quantVarD.get(0)).declType());
	    pt.typeArguments(at);
	    n = (ESJLogQuantifyExpr)n.type(pt);
	} else {
	    n = (ESJLogQuantifyExpr)n.type(ts.Boolean());
	}

	List newQuantVarD2 = new TypedList(new LinkedList(), LocalDecl.class, false);	
	for (LocalDecl d : (List<LocalDecl>) quantVarD2) {
	    if (d.type() instanceof AmbTypeNode)
		newQuantVarD2.add((d.type() instanceof AmbTypeNode) ? 
				  d.type(nf.CanonicalTypeNode(null,ts.typeForName(((AmbTypeNode) d.type()).name()))) :
				  d);
	    else
		newQuantVarD2.add(d);  
	}
	n.quantVarD2(newQuantVarD2);
	//System.out.println("now adding " + n.parentMethod().name() + " -> " + n.quantVarD() + " " + n.quantVarD2());
	//n.parentMethod().addQuantVarD(n.quantVarD());
	//n.parentMethod().addQuantVarD2(n.quantVarD2());
	return n;

    } 


}

