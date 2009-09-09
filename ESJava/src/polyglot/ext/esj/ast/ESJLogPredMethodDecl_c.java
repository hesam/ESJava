package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.util.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;
import polyglot.visit.*;

import polyglot.ext.jl5.types.FlagAnnotations;
import polyglot.ext.jl5.visit.JL5AmbiguityRemover;

import polyglot.ext.jl5.ast.*;

import java.util.*;


public class ESJLogPredMethodDecl_c extends ESJMethodDecl_c
    implements ESJLogPredMethodDecl {

    protected boolean isFallback;

    public ESJLogPredMethodDecl_c(Position pos, FlagAnnotations flags,
				  TypeNode returnType, String name,
				  List formals, List throwTypes, Block body, 
				  List paramTypes, boolean isPredicate, boolean isFallback) {

	super(pos, flags, returnType, name, formals, throwTypes, body, paramTypes, isPredicate);
	this.isFallback = isFallback;
    }

    public boolean isFallback() {
	return isFallback;
    }

}
