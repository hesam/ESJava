package polyglot.ext.esj.ast;

import java.util.*;
import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.parse.JL5Name;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;


// A temporary class used for passing an ordered dispatch method declaration.
public class ESJQuantifyTypeExpr_c extends Expr_c implements ESJQuantifyTypeExpr {

    protected CanonicalTypeNode theType;

    public ESJQuantifyTypeExpr_c(Position pos, CanonicalTypeNode theType) {
	super(pos);
	this.theType = theType;

    }

    public CanonicalTypeNode theType() {
	return theType;
    }

    public List acceptCFG(CFGBuilder v, List succs) {
	return new ArrayList();
    }
    
    public Term entry() {
	return null;
    }

    /*
    protected ESJQuantifyTypeExpr_c reconstruct(String quantVar, Expr expr) {
	return this;
    }

    public Node visitChildren(NodeVisitor v) {
	return this;
    }
    */
    
    public Node typeCheck(TypeChecker tc) throws SemanticException {
	//System.out.println("ESJQuantifyTypeExpr tc...");
	//System.out.println("ESJQuantifyTypeExpr tc done");
	ESJQuantifyTypeExpr n = (ESJQuantifyTypeExpr) super.typeCheck(tc);
	n = (ESJQuantifyTypeExpr)n.type(tc.typeSystem().typeForName("ESJList")); //"java.util.ArrayList")); FIXME

	return n;
    } 

}


