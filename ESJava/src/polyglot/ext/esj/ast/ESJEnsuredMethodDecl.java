package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.Context;
import polyglot.ext.esj.types.ESJTypeSystem;

import java.util.*;

import polyglot.ext.jl5.ast.*;

/** a method decl including an ensures clause as a post condition assertion **/
public interface ESJEnsuredMethodDecl extends JL5MethodDecl {

    public Expr ensuresExpr();
    public JL5Formal catchFormal();
    public boolean ensuresExprHasOld();
    public List modifiables();
}
