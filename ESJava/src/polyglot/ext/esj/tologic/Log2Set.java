package polyglot.ext.esj.tologic;

import polyglot.ext.esj.tologic.*;
import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Variable;
import kodkod.ast.Formula;
import kodkod.ast.Expression;
import kodkod.ast.IntExpression;
import kodkod.ast.IntConstant;

public class Log2Set extends Log2Object {

    protected int listSize;

    public Log2Set(Expression expression) {
	this(expression, 0);
    }

    public Log2Set(Expression expression, int listSize) {
	super(expression);
	this.listSize = listSize;
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

    public Log2Set allButLast_log2() {
	return new Log2Set(expression.difference(ESJInteger.atom_log2(listSize-1)), listSize - 1);
    }

    public static Formula quantifyOp2(Log2Set quantSet, boolean quantKindIsaOneOrLone, String quantKind, Log2Var quantVar, Formula quantClause) {
	Expression quantSetExpr = quantSet.expression();
	Variable v = (Variable) quantVar.expression();
	if (quantSetExpr.arity() > 1)
	    quantSetExpr = quantSetExpr.project(IntConstant.constant(1));
	return quantClause.forAll(v.oneOf(quantSetExpr));
    }

    public static Expression setComprehensionOp2(Log2Set quantSet, Log2Var quantVar, Formula quantClause) {
	Expression quantSetExpr = quantSet.expression();
	Variable v = (Variable) quantVar.expression();
	//if (quantSetExpr.arity() > 1)
	//quantSetExpr = quantSetExpr.project(IntConstant.constant(1));
	return quantClause.comprehension(v.oneOf(quantSetExpr));
    }
}
