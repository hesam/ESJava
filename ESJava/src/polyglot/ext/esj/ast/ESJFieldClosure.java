package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.Context;
import polyglot.ext.esj.types.ESJTypeSystem;

import java.util.*;

import polyglot.ext.jl5.ast.*;

/** a field access with (reflexive) transitive closure **/
public interface ESJFieldClosure extends Field {

    public boolean isReflexive();
    public String id();
    public void parentMethod(JL5MethodDecl m);
}
