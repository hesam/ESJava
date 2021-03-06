package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl5.ast.*;
import polyglot.types.*;
import polyglot.ext.esj.types.ESJTypeSystem;

import java.util.*;

import polyglot.ext.jl5.ast.JL5MethodDecl;

/** a method decl w/ a body of a quantify expr... will be desugared to a foreach stmt. **/
public interface ESJMethodDecl extends JL5MethodDecl {

    public boolean isPredicate();

}
