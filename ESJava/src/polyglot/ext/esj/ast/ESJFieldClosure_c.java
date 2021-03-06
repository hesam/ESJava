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

    protected FormulaBinary.Operator kind;
    protected boolean isMulti;
    protected List multiNames;
    protected String id;
    protected JL5MethodDecl parentMethod;
    protected String theType;

    public ESJFieldClosure_c(Position pos, Receiver target, String name, FormulaBinary.Operator kind, List multiNames, String theType) {
	super(pos, target, name);
	this.id = Integer.toString(idCtr++);
	this.kind = kind;
	this.isMulti = multiNames.size() > 1;
	this.multiNames = multiNames;
	this.theType = theType;
    }

    public FormulaBinary.Operator kind() {
	return kind;
    }

    public boolean isSimple() {
	return kind == FormulaBinary.SIMP;
    }
    
    public boolean isReflexive() {
	return kind == FormulaBinary.RFLX;
    }

    public boolean isSetFieldsMap() {
	return kind == FormulaBinary.MAP;
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
	Type t = ts.typeForName("polyglot.ext.esj.primitives.ESJSet"); //"java.util.HashSet");
	ParameterizedType pt = ts.parameterizedType((JL5ParsedClassType) t);
	ArrayList<Type> at = new ArrayList<Type>();
	at.add(n.type());
	pt.typeArguments(at);
	n = (ESJFieldClosure)n.type(pt);
	return n;
    }

}