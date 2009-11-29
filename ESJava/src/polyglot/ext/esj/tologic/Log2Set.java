package polyglot.ext.esj.tologic;

import polyglot.ext.esj.tologic.*;
import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Expression;
import kodkod.ast.IntExpression;

public class Log2Set extends Log2Object {

    public Log2Set(Expression expression) {
	super(expression);
    }

    public IntExpression get_log2(Expression obj) {
	return obj.join(expression).sum();
    }                              

    public Expression get_log2(IntExpression index) {
	return index.toExpression().join(expression);
    }                              

    public IntExpression size_log2() {
	return expression.count();
    }                              


}
