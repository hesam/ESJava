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
import polyglot.ext.jl5.types.*;
import polyglot.visit.*;
import polyglot.ext.jl5.visit.JL5AmbiguityRemover;



import java.util.*;

/** a field access with (reflexive) transitive closure **/
public class ESJFieldClosure_c extends Field_c
    implements ESJFieldClosure {

    protected static int idCtr = 0;

    protected boolean isReflexive;
    protected boolean isMulti;
    protected List multiNames;
    protected String id;
    protected JL5MethodDecl parentMethod;
    protected String theType;

    public ESJFieldClosure_c(Position pos, Receiver target, String name, boolean isReflexive, List multiNames, String theType) {
	super(pos, target, name);
	this.id = Integer.toString(idCtr++);
	this.isReflexive = isReflexive;
	this.isMulti = multiNames.size() > 1;
	this.multiNames = multiNames;
	this.theType = theType;
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

    public boolean isMulti() { 
	return isMulti; 
    }

    public List multiNames() {
	return multiNames;
    }

    public String theType() {
	return theType;
    }

    public void parentMethod(JL5MethodDecl m) {
	this.parentMethod = m;
    }


    public Node typeCheck(TypeChecker tc) throws SemanticException {
	ESJFieldClosure n = (ESJFieldClosure) super.typeCheck(tc);
	JL5TypeSystem ts = (JL5TypeSystem) tc.typeSystem();
	Type t = ts.typeForName("java.util.HashSet");
	ParameterizedType pt = ts.parameterizedType((JL5ParsedClassType) t);
	ArrayList<Type> at = new ArrayList<Type>();
	at.add(ts.typeForName(theType)); //FIXME
	pt.typeArguments(at);
	n = (ESJFieldClosure)n.type(pt);
	return n;
    }

}