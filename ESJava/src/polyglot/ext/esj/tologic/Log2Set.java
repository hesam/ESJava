package polyglot.ext.esj.tologic;

import polyglot.ext.esj.tologic.*;
import polyglot.ext.esj.primitives.*;

import java.util.Hashtable;
import java.util.ArrayList;

import kodkod.ast.Variable;
import kodkod.ast.Formula;
import kodkod.ast.Expression;
import kodkod.ast.IntExpression;

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

    public Expression allButLast_log2() {
	return expression.difference(ESJInteger.atom_log2(listSize-1));
    }

    public static Formula quantifyOp2(Expression quantSet, boolean quantKindIsaOneOrLone, String quantKind, Log2Var quantVar, Formula quantClause) {
	return quantClause.forAll(((Variable) quantVar.expression()).oneOf(quantSet));
    }

}
