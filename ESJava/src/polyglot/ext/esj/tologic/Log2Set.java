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
    protected Class range;

    public Log2Set(Expression expression) {
	this(expression, 0, null);
    }

    public Log2Set(Expression expression, int listSize) {
	this(expression, listSize, null);
    }

    public Log2Set(Expression expression, int listSize, Class range) {
	super(expression);
	this.listSize = listSize;
	this.range = range;
    }

    public int listSize() { return listSize; }
    public Class range() { return range; }

    public IntExpression get_log2(Expression obj) {
	return obj.join(expression).sum();
    }                              

    public Expression get_log2(IntExpression index) {
	return index.toExpression().join(expression);
    }                              

    /*
    public Expression get_log2(ESJObject itm) {
	return itm.var_log2().expression().join(expression);
    }
    */

    public Formula contains_log2(Log2Object itm) {
	return expression.intersection(itm.expression()).some();
    }

    public Formula contains_log2(ESJObject itm) {
	return expression.intersection(itm.var_log2().expression()).some();
    }

    public Formula containsKey_log2(Log2Object itm) {
	return itm.expression().join(expression).some();
    }

    public Formula containsKey_log2(ESJObject itm) {
	return itm.var_log2().expression().join(expression).some();
    }

    public Log2Set values_log2() {
	return new Log2Set(expression.project(IntConstant.constant(1)));
    }

    public Log2Set plus_log2(Log2Set o2) {
	return new Log2Set(expression.union(o2.expression()));
    }

    public Log2Set plus_log2(ESJObject o2) { //FIXME
	return new Log2Set(expression.union(LogMap.objToSingletonRelation_log2(o2).expression()));
    }

    public Log2Set plus_log2(IntConstant o2) { //FIXME
	return new Log2Set(expression.union(o2.toExpression()));
    }

    public Log2Set plus_log2(IntExpression o2) { //FIXME
	return new Log2Set(expression.union(o2.toExpression()));
    }

    public Log2Set minus_log2(Log2Set o2) {
	return new Log2Set(expression.difference(o2.expression()));
    }

    public Log2Set minus_log2(ESJObject o2) { //FIXME
	return new Log2Set(expression.difference(LogMap.objToSingletonRelation_log2(o2).expression()));
    }

    public Log2Set minus_log2(IntConstant o2) { //FIXME
	return new Log2Set(expression.difference(o2.toExpression()));
    }

    public Log2Set minus_log2(IntExpression o2) { //FIXME
	return new Log2Set(expression.difference(o2.toExpression()));
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

    public static Log2Set setComprehensionOp2(Log2Set quantSet, Log2Var quantVar, Formula quantClause) {
	Expression quantSetExpr = quantSet.expression();
	Variable v = (Variable) quantVar.expression();
	return new Log2Set(quantClause.comprehension(v.oneOf(quantSetExpr)));
    }
}
