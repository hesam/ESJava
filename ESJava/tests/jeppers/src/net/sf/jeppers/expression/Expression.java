/* 
 * Copyright (c) 2004, Cameron Zemek
 * 
 * This file is part of JExpression.
 * 
 * JExpression is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * JExpression is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */
package net.sf.jeppers.expression;

import java.math.BigDecimal;
import java.util.*;

import net.sf.jeppers.expression.function.*;

//HS
import com.capsicumcorp.swing.spreadsheet.*;
import polyglot.ext.esj.primitives.*;
import polyglot.ext.esj.tologic.*;

/**
 * @author <a href="mailto:grom@capsicumcorp.com">Cameron Zemek</a>
 */
public class Expression {
	final static private int DIVIDE_PRECISION = 31;
	
    CellReference cellRef, varCellRef; //HS
	private HashMap<CellReference,Integer> variableMap;
	private Map<String,Function> functionMap;
        private Map<String,CellReference> varRefMap; //HS
	private Token[] postfixExpression; // in postfix form	
	private String expression; // in infix form
	private Integer cachedResult;
        private Workbook workbook; //HS
	
	private List references;
	
    public Expression(CellReference cellRef, 
		      String expression, Token[] postfixExpression, List<String> references,
		      HashMap<CellReference,Integer> variableMap, Map<String,Function> functionMap,
		      Map<String,CellReference> varRefMap, Workbook workbook) {
 	        this.cellRef = cellRef; //HS
		this.expression = expression;
		this.postfixExpression = postfixExpression;
		this.references = references;
		this.variableMap = variableMap;
		this.functionMap = functionMap;
		this.varRefMap = varRefMap;
		this.workbook = workbook; //HS
	}
	
	public String getExpression() {
		return expression;
	}
	
	public Integer getCachedResult() {
		return cachedResult;
	}

        // HS
	public void cachedResult(Integer i) {
	    this.cachedResult = i;
	}
	
	public List getReferences() {
		return references;
	}


    public boolean toLogic() { return false; }

    /*
    public LogFormula toLogic_log() {
	Stack stack = new Stack();	
	for (int i = 0; i < postfixExpression.length; i++) {
	    Token token = postfixExpression[i];
	    if (TokenType.isOperand(token)) {
		if(token.getType() == TokenType.VAR) {
		    this.varCellRef = varRefMap.get(token.getText());
		    //System.out.println("hi: " + workbook.cellValueMap() + " " + varCellRef + " " + workbook.cellValueMap_log());
		    stack.push(workbook.cellValueMap_log().get_log(new LogIntAtom(LogMap.get1_log(varCellRef))));
		} else {
		    stack.push(token.getText());
		}
	    } else if(token.getType() == TokenType.METHOD_CALL) {
		stack.push(token.getText());
	    } else {				
		Object loperand = null;
		Object roperand = null;
		if (TokenType.isUnary(token)) {
		    loperand = stack.pop();
		} else {
		    roperand = stack.pop();
		    loperand = stack.pop();
		}
		//System.out.println("left= " + loperand + " (" + loperand.getClass() + ") right=" + roperand + " (" + roperand.getClass() +")");
		Object res;
		if (loperand instanceof LogIntAtom)
		    res = ((LogIntAtom) loperand).arithOp(token.getType().symbol(), roperand instanceof LogObject ? (LogObject) roperand : new LogInt((String) roperand));
		else
		    res = ((LogInt) loperand).arithOp(token.getType().symbol(), roperand instanceof LogObject ? (LogObject) roperand : new LogInt((String) roperand));
		
		stack.push(res);
	    }
	}
	LogFormula p = workbook.cellValueMap_log().get_log(new LogIntAtom(LogMap.get1_log(cellRef))).cmpOp("=", (LogObject) stack.pop());
	//System.out.println("pop: " + p + " " + p.getClass());
	//cachedResult = (p instanceof String) ? Integer.parseInt((String) p) : ((BigDecimal) p).intValue();
	return p;
    }
    */

    public LogFormula toLogic_log() {
	Stack stack = new Stack();	
	for (int i = 0; i < postfixExpression.length; i++) {
	    Token token = postfixExpression[i];
	    if (TokenType.isOperand(token)) {
		if(token.getType() == TokenType.VAR) {
		    this.varCellRef = varRefMap.get(token.getText());
		    //System.out.println("hi: " + workbook.cellValueMap() + " " + varCellRef + " " + workbook.cellValueMap_log());
		    stack.push(varCellRef.cachedResult_log());
		} else {
		    stack.push(token.getText());
		}
	    } else if(token.getType() == TokenType.METHOD_CALL) {
		stack.push(token.getText());
	    } else {				
		Object loperand = null;
		Object roperand = null;
		if (TokenType.isUnary(token)) {
		    loperand = stack.pop();
		} else {
		    roperand = stack.pop();
		    loperand = stack.pop();
		}
		System.out.println("left= " + loperand + " (" + loperand.getClass() + ") right=" + roperand + " (" + roperand.getClass() +")");
		Object res;
		/*if (loperand instanceof LogIntAtom)
		  res = ((LogIntAtom) loperand).arithOp(token.getType().symbol(), roperand instanceof LogObject ? (LogObject) roperand : new LogInt((String) roperand));*/
		if (loperand instanceof ESJInteger)
		    if (roperand instanceof String)
			res = ((ESJInteger) loperand).arithOp(token.getType().symbol(), new LogInt((String) roperand));
		    else
			res = ((ESJInteger) loperand).arithOp(token.getType().symbol(), (ESJInteger) roperand);
		else
		    res = ((LogIntComposite) loperand).arithOp(token.getType().symbol(), roperand instanceof LogObject ? (LogObject) roperand : new LogInt((String) roperand));
		
		stack.push(res);
	    }
	}
	Object pop = stack.pop();	
	LogFormula p;
	if (pop instanceof LogObject)
	    p = cellRef.cachedResult_log().cmpOp("=",  (LogObject) pop);
	else
	    p = cellRef.cachedResult_log().cmpOp("=",  (ESJObject) pop);
	//System.out.println("pop: " + p + " " + p.getClass());
	//cachedResult = (p instanceof String) ? Integer.parseInt((String) p) : ((BigDecimal) p).intValue();
	return p;
    }

    private ESJSet<Expression> modifieds() {
	ESJSet<Expression> r = new ESJSet<Expression>();
	r.add(this);
	return r;
    }
	public Integer evaluate() /*throws InvalidArguments, 
				   UnknownFunctionException, UnknownVariableException */ //HS
	//modifies fields Expression.cachedResult
	//modifies objects modifieds()
	//ensures toLogic()
                 {
		if (postfixExpression.length == 0) {
			return null;
		}
		Stack stack = new Stack();	
		for (int i = 0; i < postfixExpression.length; i++) {
			Token token = postfixExpression[i];
			if (TokenType.isOperand(token)) {
				if(token.getType() == TokenType.VAR) {
				    //Object value = variableMap.get(token.getText());
				    this.varCellRef = varRefMap.get(token.getText());
				    //System.out.println("variableMap: " + variableMap + " this: " + this.cellRef + " var cell: " + varCellRef + " get: " + variableMap.get(this.varCellRef));
				    Object value = variableMap.get(varRefMap.get(token.getText())); //HS

					if (value == null) {
						throw new UnknownVariableException(token.getText());
					}
					stack.push(value);
				} else {
					stack.push(token.getText());
				}
			} else if(token.getType() == TokenType.METHOD_CALL) {
				int noArgs = token.getNumberOfArguments();
				List arguments = new ArrayList(noArgs);
				for (int j = 0; j < noArgs; j++) {
					Object arg = stack.pop();
					arguments.add(arg);
				}
				Collections.reverse(arguments);
				Object res = applyFunction(token.getText(), arguments);
				stack.push(res);
			} else {				
				Object loperand = null;
				Object roperand = null;
				if (TokenType.isUnary(token)) {
					loperand = stack.pop();
				} else {
					roperand = stack.pop();
					loperand = stack.pop();
				}
				Object res = applyOperator(token.getType(), loperand, roperand);
				stack.push(res);
			}
		}
		Object p = stack.pop();
		//System.out.println("pop: " + p + " " + p.getClass());
		//cachedResult = (p instanceof String) ? Integer.parseInt((String) p) : ((BigDecimal) p).intValue();
		cachedResult = p instanceof BigDecimal ? ((BigDecimal) p).intValue() : (Integer) p; //HS
		
		return cachedResult;
	}
	
	private Object applyFunction(String functionName, List<Object> arguments) 
		throws InvalidArguments, UnknownFunctionException {
		Function func = (Function) functionMap.get(functionName);
		if (func == null) {
			throw new UnknownFunctionException(functionName);
		}
		return func.run(arguments);
	}
	
	private Object applyOperator(TokenType operator, Object loperand, Object roperand) {
		// String operators
		if (operator == TokenType.CONCAT) {
			String s1 = loperand.toString();
			String s2 = roperand.toString();
			return s1.concat(s2);
		}			
		
		// Boolean operators
		Boolean a = null;
		Boolean b = null;
		if (loperand instanceof Boolean) {
			a = (Boolean) loperand;
		}
		if (roperand != null && roperand instanceof Boolean) {
			b = (Boolean) roperand;
		}
		if (operator == TokenType.AND) {
			return Boolean.valueOf(a.booleanValue() && b.booleanValue());
		} else if (operator == TokenType.OR) {
			return Boolean.valueOf(a.booleanValue() || b.booleanValue());
		} else if (operator == TokenType.NOT) {
			return Boolean.valueOf(!a.booleanValue());
		}		
		
		// Math operators
		BigDecimal x = parseNumber(loperand);
		BigDecimal y = parseNumber(roperand);
		if (operator == TokenType.PLUS) {
			return x.add(y);
		} else if (operator == TokenType.MINUS) {
			return x.subtract(y);			
		} else if (operator == TokenType.MULTIPLY) {
			return x.multiply(y);
		} else if (operator == TokenType.DIVIDE) {
			return x.divide(y, DIVIDE_PRECISION, BigDecimal.ROUND_HALF_EVEN);
		} else if (operator == TokenType.MOD) {
			// TODO Improve mod operator
			return new BigDecimal(x.doubleValue() % y.doubleValue());
		} else if (operator == TokenType.POWER) {
			// TODO Improve power operator
			return new BigDecimal(Math.pow(x.doubleValue(), y.doubleValue()));
		} else if (operator == TokenType.SIGN_MINUS) {
			return x.negate();
		} else if (operator == TokenType.SIGN_PLUS) {
			return x.abs();
		}
		
		// Relation operators
		if (operator == TokenType.LESS_THEN) {
			return new Boolean(x.compareTo(y) < 0);
		} else if (operator == TokenType.LESS_EQUAL) {
			return new Boolean(x.compareTo(y) <= 0);
		} else if (operator == TokenType.GREATER_THEN) {
			return new Boolean(x.compareTo(y) > 0);
		} else if (operator == TokenType.GREATER_EQUAL) {
			return new Boolean(x.compareTo(y) >= 0);
		} else if (operator == TokenType.EQUAL) {
			return new Boolean(x.compareTo(y) == 0);
		} else if (operator == TokenType.NOT_EQUAL) {
			return new Boolean(x.compareTo(y) != 0);
		}
		
		return null;
	}
	
	private BigDecimal parseNumber(Object value) {
		if (value == null) {
			return new BigDecimal(0);
		}
		
		BigDecimal num;
		try {
			num = new BigDecimal(value.toString());
		} catch (NumberFormatException e) {
			num = new BigDecimal(0);
		}
		return num;
	}

    // HS
    public String toString() {
	String r = "Exp [ ";
	if (postfixExpression != null)
	    for (int i = 0; i < postfixExpression.length; i++) {
		Token token = postfixExpression[i];
		r += token+" ";
	    }
	r += "]";
	return r;
    }
}

