package polyglot.ext.esj.ast;

import java.util.*;

import polyglot.types.*;
import polyglot.ast.*;
import polyglot.ext.jl5.ast.*;

// an ast node for representing an expression in a predicate method
public interface ESJLogQuantifyExpr extends Expr {

    public String id();
    //public List id();
    public FormulaBinary.Operator quantKind();
    //public List quantKind();
    public String quantVarN();
    //public List quantVarN();
    public List quantVarD();
    public List quantVarD2();
    public Expr quantListExpr();
    //public List quantListExpr();
    public ESJQuantifyClauseExpr quantClauseExpr();
    public JL5MethodDecl parentMethod();
    public void parentMethod(JL5MethodDecl m);
    public void addVars(List quantVarD2);
}

