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


public class ESJQuantVarLocalDecl_c extends JL5LocalDecl_c
    implements ESJQuantVarLocalDecl {

    public ESJQuantVarLocalDecl_c(Position pos, FlagAnnotations flags, TypeNode type, String name, Expr init) {
	super(pos,flags,type,name,init);
    }


}
