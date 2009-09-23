package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.Context;
import polyglot.ext.esj.types.ESJTypeSystem;

import java.util.*;

import polyglot.ext.jl5.ast.*;

/** a class decl including an ensures clause as an invariant  **/
public interface ESJLogVarClassDecl extends JL5ClassDecl {

    public List logPredMtdDecls();
    public void logPredMtdDecls(List d);
}
