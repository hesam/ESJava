package polyglot.ext.esj.tologic;

import polyglot.ext.esj.primitives.*;

import java.io.CharArrayWriter;
import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Expression;
import kodkod.ast.IntExpression;
import kodkod.ast.Formula;

public class Log2IntAtom  {
    protected Expression expression;
    public Expression expression() { return expression; }

    public Log2IntAtom(Expression expression) {
	super();
	this.expression = expression;
    }

    public IntExpression sum() { return expression.sum(); }

}
