package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl5.types.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;

import polyglot.ext.jl5.types.FlagAnnotations;
import polyglot.ext.jl5.visit.JL5AmbiguityRemover;

import polyglot.ext.jl5.ast.JL5MethodDecl_c;

import java.util.*;

/** Adds a predicate to method declarations. **/
public class ESJMethodDecl_c extends JL5MethodDecl_c
    implements ESJMethodDecl {

    protected boolean isPredicate;
    
    public ESJMethodDecl_c(Position pos, FlagAnnotations flags,
			   TypeNode returnType, String name,
			   List formals,
			   List throwTypes, Block body, List paramTypes, boolean isPredicate) {
	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes);
	this.isPredicate = isPredicate;
	
    }
    
    public boolean isPredicate() {
	return isPredicate;
    }



}
