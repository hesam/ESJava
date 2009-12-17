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
import kodkod.ast.Decls;

public class LogSet extends LogObject {

    protected int listSize;
    protected Class range;

    public LogSet(Expression expression) {
	this(expression, 0, null);
    }

    public LogSet(Expression expression, int listSize) {
	this(expression, listSize, null);
    }

    public LogSet(Expression expression, int listSize, Class range) {
	super(expression);
	this.listSize = listSize;
	this.range = range;
    }

    public int listSize() { return listSize; }
    public Class range() { return range; }

    /*
    public IntExpression get_log(Expression obj) {
	return obj.join(expression).sum();
	} */                             

    public Expression get_log(Expression obj) {
	return obj.join(expression);
    }                              

    public Expression get_log(IntExpression index) {
	return index.toExpression().join(expression);
    }                              

    public Expression get_log(ESJObject itm) {
	return itm.var_log().expression().join(expression);
    }
    

    public Formula contains_log(LogObject itm) {
	return expression.intersection(itm.expression()).some();
    }

    public Formula contains_log(ESJObject itm) {
	return expression.intersection(itm.var_log().expression()).some();
    }

    public Formula containsKey_log(LogObject itm) {
	return itm.expression().join(expression).some();
    }

    public Formula containsKey_log(ESJObject itm) {
	return itm.var_log().expression().join(expression).some();
    }

    public LogSet values_log() {
	return new LogSet(expression.project(IntConstant.constant(1)));
    }

    public LogSet plus_log(LogSet o2) {
	return new LogSet(expression.union(o2.expression()));
    }

    public LogSet plus_log(ESJObject o2) { //FIXME
	return new LogSet(expression.union(LogMap.objToSingletonRelation_log(o2).expression()));
    }

    public LogSet plus_log(IntConstant o2) { //FIXME
	return new LogSet(expression.union(o2.toExpression()));
    }

    public LogSet plus_log(IntExpression o2) { //FIXME
	return new LogSet(expression.union(o2.toExpression()));
    }

    public LogSet minus_log(LogSet o2) {
	return new LogSet(expression.difference(o2.expression()));
    }

    public LogSet minus_log(ESJObject o2) { //FIXME
	return new LogSet(expression.difference(LogMap.objToSingletonRelation_log(o2).expression()));
    }

    public LogSet minus_log(IntConstant o2) { //FIXME
	return new LogSet(expression.difference(o2.toExpression()));
    }

    public LogSet minus_log(IntExpression o2) { //FIXME
	return new LogSet(expression.difference(o2.toExpression()));
    }

    /*
    public IntExpression size_log() {
	return expression.count();
	} */                             

    public Expression size_log() {
	return expression.count().toExpression();
    }                              

    public LogSet allButLast_log() {
	return new LogSet(expression.difference(ESJInteger.atom_log(listSize-1)), listSize - 1);
    }

    // FIXME
    public static Formula quantifyOp2(LogSet quantSet, boolean quantKindIsaOneOrLone, String quantKind, LogVar quantVar, Formula quantClause) {
	Expression quantSetExpr = quantSet.expression();
	Variable v = (Variable) quantVar.expression();
	if (quantSetExpr.arity() > 1)
	    quantSetExpr = quantSetExpr.project(IntConstant.constant(1));
	Decls varDecl = v.oneOf(quantSetExpr);
	return quantKind.equals("all") ? quantClause.forAll(varDecl) : quantClause.forSome(varDecl);
    }

    public static LogSet setComprehensionOp2(LogSet quantSet, LogVar quantVar, Formula quantClause) {
	Expression quantSetExpr = quantSet.expression();
	Variable v = (Variable) quantVar.expression();
	return new LogSet(quantClause.comprehension(v.oneOf(quantSetExpr)));
    }
}
