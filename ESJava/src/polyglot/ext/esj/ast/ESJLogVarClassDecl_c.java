package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;

import polyglot.ext.jl5.types.FlagAnnotations;
import polyglot.ext.jl5.visit.JL5AmbiguityRemover;

import polyglot.ext.jl5.types.JL5Context;
import polyglot.ext.jl5.types.TypeVariable;

import polyglot.ext.jl5.ast.*;


import java.util.*;

/** Adds a predicate to method declarations. **/
public class ESJLogVarClassDecl_c extends JL5ClassDecl_c
    implements ESJLogVarClassDecl {

    protected List logPredMtdDecls;

    public ESJLogVarClassDecl_c(Position pos, FlagAnnotations fl, String name, 
				 TypeNode superType, List interfaces, ClassBody body, 
				 List<ParamTypeNode> paramTypes) {
        super(pos, fl, name, superType, interfaces, body, paramTypes);
    }
    

    public List logPredMtdDecls() { return logPredMtdDecls; }
    public void logPredMtdDecls(List d) { logPredMtdDecls = d; }

}