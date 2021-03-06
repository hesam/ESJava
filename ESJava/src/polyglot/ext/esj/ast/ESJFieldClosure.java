package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.Context;
import polyglot.ext.esj.types.ESJTypeSystem;

import java.util.*;

import polyglot.ext.jl5.ast.*;

/** a field access with (reflexive) transitive closure **/
public interface ESJFieldClosure extends Field {

    public FormulaBinary.Operator kind();
    public boolean isSimple();
    public boolean isReflexive();
    public boolean isSetFieldsMap();
    public boolean isMulti();
    public List multiNames();
    public String id();
    public void parentMethod(JL5MethodDecl m);
    public String theType();
}
