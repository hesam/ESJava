package polyglot.ext.esj.ast;

import java.util.*;

import polyglot.ast.*;
import polyglot.ext.jl5.parse.JL5Name;




// an ast node for representing the quantified type which the quantified expr involves
// (which implies all instances of that type)
public interface ESJQuantifyTypeExpr extends Expr {

    public TypeNode theType();

}

