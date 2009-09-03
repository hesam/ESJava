package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.ext.jl.ast.*;
import polyglot.visit.*;
import polyglot.types.*;
import polyglot.util.*;

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

/** a field access with (reflexive) transitive closure **/
public class ESJFieldClosure_c extends Field_c
    implements ESJFieldClosure {

    protected static int idCtr = 0;

    protected boolean isReflexive;
    protected String id;
    protected JL5MethodDecl parentMethod;

    public ESJFieldClosure_c(Position pos, Receiver target, String name, boolean isReflexive) {
	super(pos, target, name);
	this.id = Integer.toString(idCtr++);
	this.isReflexive = isReflexive;
    }
    
    public boolean isReflexive() {
	return isReflexive;
    }

    public String id() {
	return id;
    }

    public JL5MethodDecl parentMethod() {
	return parentMethod;
    }

    public void parentMethod(JL5MethodDecl m) {
	this.parentMethod = m;
    }

    public Node typeCheck(TypeChecker tc) throws SemanticException {
	ESJFieldClosure n = (ESJFieldClosure) super.typeCheck(tc);
	n = (ESJFieldClosure)n.type(tc.typeSystem().typeForName("java.util.ArrayList")); 
	return n;
    }

}