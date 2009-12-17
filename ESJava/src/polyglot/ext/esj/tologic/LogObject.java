package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.io.CharArrayWriter;
import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Expression;
import kodkod.ast.IntExpression;
import kodkod.ast.Formula;

public class LogObject  {
    protected Expression expression;
    public Expression expression() { return expression; }

    public LogObject(Expression expression) {
	super();
	this.expression = expression;
    }

    public Expression sum() { return expression; }

    public Formula equals_log(LogObject o2) {
	return expression.eq(o2.expression());
    }

    public Formula equals_log(ESJObject o2) {
	return expression.eq(o2.var_log().expression());
    }

}
