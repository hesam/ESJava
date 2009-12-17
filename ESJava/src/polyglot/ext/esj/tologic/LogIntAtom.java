package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.io.CharArrayWriter;
import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Expression;
import kodkod.ast.IntExpression;
import kodkod.ast.Formula;

public class LogIntAtom  {
    protected IntExpression expression;
    public IntExpression expression() { return expression; }

    public LogIntAtom(IntExpression expression) {
	super();
	this.expression = expression;
    }

    public IntExpression sum() { return expression; }

    public Formula eq(IntExpression o2) {
	return expression.eq(o2);
    }

    public Formula gt(IntExpression o2) {
	return expression.gt(o2);
    }

    public Formula gte(IntExpression o2) {
	return expression.gte(o2);
    }

    public Formula lt(IntExpression o2) {
	return expression.lt(o2);
    }

    public Formula lte(IntExpression o2) {
	return expression.lte(o2);
    }
    
    public Formula eq(ESJObject o2) {
	return expression.eq(o2.var_log().expression().sum());
    }
    
    public Formula gt(ESJObject o2) {
	return expression.gt(o2.var_log().expression().sum());
    }
    
    public Formula gte(ESJObject o2) {
	return expression.gte(o2.var_log().expression().sum());
    }
    
    public Formula lt(ESJObject o2) {
	return expression.lt(o2.var_log().expression().sum());
    }
    
    public Formula lte(ESJObject o2) {
	return expression.lte(o2.var_log().expression().sum());
    }

}
