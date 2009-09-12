package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;

import java.util.*;

import polyglot.ext.jl5.ast.JL5MethodDecl;

/** the relational logic counterpart of the predicate method decl **/
public interface ESJLogPredMethodDecl extends ESJMethodDecl {

    public boolean isFallback();
    public boolean isLogVar();
}
