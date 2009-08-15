package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.ext.jl.ast.*;
import polyglot.visit.*;
import polyglot.types.*;
import polyglot.util.*;

// binary expressions appearing in predicates
public class CmpBinary_c extends Binary_c implements CmpBinary {

    public CmpBinary_c(Position pos, Expr left, Operator op,Expr right) {
	super(pos, left, op, right);
    }
}
