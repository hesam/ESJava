package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.io.CharArrayWriter;
import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Expression;

public class Log2Object  {
    protected Expression expression;
    public Expression expression() { return expression; }

    public Log2Object(Expression expression) {
	super();
	this.expression = expression;
    }

    public Expression sum() { return expression; }
}
