package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.io.CharArrayWriter;
import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Expression;
import kodkod.ast.Formula;

public class Log2Object  {
    protected Expression expression;
    public Expression expression() { return expression; }

    public Log2Object(Expression expression) {
	super();
	this.expression = expression;
    }

    public Expression sum() { return expression; }

    public Formula equals_log2(Log2Object o2) {
	return expression.eq(o2.expression());
    }

    public Formula equals_log2(ESJObject o2) {
	return expression.eq(o2.var_log2().expression());
    }

}
