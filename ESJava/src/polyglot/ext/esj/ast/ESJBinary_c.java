package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.ext.jl.ast.*;
import polyglot.visit.*;
import polyglot.types.*;
import polyglot.util.*;

// binary expressions appearing in predicates
public class ESJBinary_c extends Binary_c implements ESJBinary {

    protected String kodkodiOp, kodkodOp;

    public ESJBinary_c(Position pos, Expr left, Operator op, Expr right) {
	this(pos, left, op, right, null, null);
    }

    public ESJBinary_c(Position pos, Expr left, Operator op, Expr right, String kodkodiOp, String kodkodOp) {
	super(pos, left, op, right);
	this.kodkodiOp = kodkodiOp;
	this.kodkodOp = kodkodOp;
    }

    public String kodkodiOp() { return kodkodiOp; }
    public String kodkodOp() { return kodkodOp; }


}
