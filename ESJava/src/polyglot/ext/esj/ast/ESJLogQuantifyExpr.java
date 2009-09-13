package polyglot.ext.esj.ast;

import java.util.*;

import polyglot.types.*;
import polyglot.ast.*;
import polyglot.ext.jl5.ast.*;

// an ast node for representing an expression in a predicate method
public interface ESJLogQuantifyExpr extends Expr {

    public String id();
    public FormulaBinary.Operator quantKind();
    public String quantVarN();
    public List quantVarD();
    public List quantVarD2();
    public Expr quantListExpr();
    public ESJQuantifyClauseExpr quantClauseExpr();
    public ESJLogPredMethodDecl parentMethod();
    public void parentMethod(ESJLogPredMethodDecl m);
    public void quantVarD2(List quantVarD2);
}

