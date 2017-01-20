package com.csc.fsg.life.pw.io;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;

import com.csc.fsg.life.pw.util.Constants;
import com.csc.fsg.life.pw.util.PWDate;

/* Modifications: ENH1006, T0129, T0106 */

public class SqlExpressionEvaluator {

	Queue<String> postfix = null;
	
	public SqlExpressionEvaluator(String sqlExpr) {
		sqlExpr = sqlExpr.replaceAll("NOT LIKE", "NOTLIKE");
		postfix = infixToPostfix(sqlExpr);
	}
	
	public boolean eval(TableDescriptor td, Row row) throws Exception {
		try {
			Queue<String> tmpPostfix = new LinkedList<String>(postfix);
			boolean ret = evaluatePostfix(tmpPostfix, td, row);
			return ret;
		} catch (Exception e) {
			throw new InfoException(Constants.USER_FILTER_ERROR + e.getMessage());
		}
	}
	
	private Queue<String> infixToPostfix(String sqlExpr) {
		Queue<String> postfix = new LinkedList<String>();
		Stack<String> operatorStack = new Stack<String>();
		String[] tokens = sqlExpr.split("[\\s]");
		
		for (String token : tokens) {
			if ( token.isEmpty() )
				continue;
			if ( isRelationalOperator(token) ) {
				if ( !operatorStack.empty() ) {
					String top = operatorStack.peek();
					if ( !top.equals("(") && isBooleanOperator(token) ) {
						postfix.add(top);
						operatorStack.pop();
					}
				}
				operatorStack.push(token);
			} else if ( isBooleanOperator(token) ) {
				if ( !operatorStack.empty() ) {
					String top = operatorStack.peek();
					if ( !top.equals("(") ) {
						if ( isRelationalOperator(top) ) {
							postfix.add(top);
							operatorStack.pop();
						}
					}
				}
				operatorStack.push(token);
			} else if ( token.equals("(") ) {
				operatorStack.push(token);
			} else if ( token.equals(")") ) {
				while ( !operatorStack.empty() ) {
					String top = operatorStack.pop();
					if ( top.equals("(") )
						break;
					postfix.add(top);
				}
			} else {
				postfix.add(token);
			}
		}
		while ( !operatorStack.empty() ) {
			postfix.add(operatorStack.pop());
		}

		/*
		StringBuffer debugMsg = new StringBuffer();
		for ( String token : postfix ) {
			debugMsg.append(token).append(" "); 
		}
		System.out.println("infixToPostfix output: " + debugMsg);
		*/
		
		return postfix;
	}
	
	private boolean evaluatePostfix(Queue<String> postfix, TableDescriptor td, Row row)
			throws Exception {
		Stack<Boolean> valueStack = new Stack<Boolean>();
		Stack<String> operandStack = new Stack<String>();
		while ( !postfix.isEmpty() ) {
			String token = postfix.remove();
			if ( isRelationalOperator(token) ) {
				if ( operandStack.size() < 2 )
					throw new InfoException("Missing operand for \"" + token + "\"");
				String operand2 = operandStack.pop();
				if ( operand2.startsWith("'") ) {
					operand2 = operand2.replaceAll("\'", "");
				}
				String operand1 = operandStack.pop();
				if ( !td.hasField(operand1) )
					throw new InfoException("Bad filter token <" + operand1 + ">");
				String value1 = (String) td.getValueOfColumn(operand1, row);
				ColumnDescriptor cd = td.getColumnDescriptor(operand1);
				boolean newval = false;
				int dataType = cd.getDataType();
				switch (dataType) {
				case 1: // CHAR
					newval = evaluateString(value1, operand2, token);
					break;
				case 3: // DOUBLE
				case 5: // SHORT
					operand2 = operand2.replace(".", "");
					newval = evaluateDouble(new Double(value1), new Double(operand2), token);
					break;
				case 91: // DATE
					newval = evaluateDate(value1, operand2, token);
					break;
				default:
					throw new Exception("Unsupported data type " + dataType);
				}
				valueStack.push(Boolean.valueOf(newval));
			} else if ( isBooleanOperator(token) ) {
				if ( valueStack.size() < 2 )
					throw new InfoException("Missing operand for \"" + token + "\"");
				Boolean val1 = valueStack.pop();
				Boolean val2 = valueStack.pop();
				boolean newval = false;
				if ( token.equalsIgnoreCase("AND") ) {
					newval = val1.booleanValue() && val2.booleanValue();
				} else if ( token.equalsIgnoreCase("OR") ) {
					newval = val1.booleanValue() || val2.booleanValue();
				}
				valueStack.push( Boolean.valueOf(newval) );
			} else {
				operandStack.push(token);
			}
		}
		if ( valueStack.empty() )
			throw new InfoException("Missing final value");
		return valueStack.pop().booleanValue();
	}
	
	private boolean isRelationalOperator(String token) {
		return ( token.equals("=") || token.equals(">") || token.equals(">=") || 
				 token.equals("<>") || token.equals("<") || token.equals("<=") || 
				 token.equals("LIKE") || token.equals("NOTLIKE") );
	}
	
	private boolean isBooleanOperator(String token) {
		return ( token.equalsIgnoreCase("AND") || token.equalsIgnoreCase("OR") );
	}
	
	private boolean evaluateString(String val1, String val2, String operator) throws Exception {
		val1 = val1.trim();
		val2 = val2.trim();
		if ( operator.equals("=") )
			return val1.equals(val2);
		if ( operator.equals("<>") )
			return !val1.equals(val2);
		if ( operator.equals(">") )
			return val1.compareTo(val2) > 0;
		if ( operator.equals(">=") )
			return val1.compareTo(val2) >= 0;
		if ( operator.equals("<") )
			return val1.compareTo(val2) < 0;
		if ( operator.equals("<=") )
			return val1.compareTo(val2) <= 0;
		
		if ( operator.equals("LIKE") || operator.equals("NOTLIKE") ) {
			String pattern = val2.replaceAll("%", ".*");
			pattern = pattern.replaceAll("_", ".");
			pattern = "^" + pattern + "$";
			Pattern p = Pattern.compile(pattern);
			if ( operator.equals("LIKE") )
				return  p.matcher(val1).matches();
			return  !p.matcher(val1).matches();
		}
		
		throw new InfoException("Bad filter operator " + operator);
	}
	
	private boolean evaluateDouble(Double val1, Double val2, String operator) throws Exception {
		if ( operator.equals("=") )
			return val1.equals(val2);
		if ( operator.equals("<>") )
			return !val1.equals(val2);
		if ( operator.equals(">") )
			return val1.compareTo(val2) > 0;
		if ( operator.equals(">=") )
			return val1.compareTo(val2) >= 0;
		if ( operator.equals("<") )
			return val1.compareTo(val2) < 0;
		if ( operator.equals("<=") )
			return val1.compareTo(val2) <= 0;
		throw new InfoException("Bad filter operator " + operator);
	}
	
	private boolean evaluateDate(String val1, String val2, String operator) throws Exception {		
		PWDate date1 = new PWDate(val1);
		PWDate date2 = new PWDate(val2);
		if ( operator.equals("=") )
			return date1.equals(date2);
		if ( operator.equals("<>") )
			return !date1.equals(date2);
		if ( operator.equals(">") )
			return date1.after(date2);
		if ( operator.equals(">=") )
			return !date1.before(date2);
		if ( operator.equals("<") )
			return date1.before(date2);
		if ( operator.equals("<=") )
			return !date1.after(date2);
		throw new InfoException("Bad filter operator " + operator);
	}
	
//	public static void main(String[] args) {		
//		try {
//			TableDescriptor td = new T024XDescriptor();
//			T024XRow row = new T024XRow();
//			row.setEffectiveDate("2009-01-01");
//			row.setMemoCode("CO");
//			row.setFundNumber("5");
//			
//			String input = "EFFECTIVE_DATE > '2009-01-01'";
//			System.out.println("input: " + input);
//			SqlExpressionEvaluator ee = new SqlExpressionEvaluator(input);
//			boolean ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//			
//			input = "EFFECTIVE_DATE < '2009-01-01'";
//			System.out.println("input: " + input);
//			ee = new SqlExpressionEvaluator(input);
//			ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//			
//			input = "EFFECTIVE_DATE = '2009-01-01'";
//			System.out.println("input: " + input);
//			ee = new SqlExpressionEvaluator(input);
//			ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//			
//			input = "MEMO_CODE = 'MT' AND EFFECTIVE_DATE = '2009-01-01'";
//			System.out.println("input: " + input);
//			ee = new SqlExpressionEvaluator(input);
//			ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//			
//			input = "( MEMO_CODE = 'MT' OR MEMO_CODE = 'CO' ) AND EFFECTIVE_DATE = '2009-01-01'";
//			System.out.println("input: " + input);
//			ee = new SqlExpressionEvaluator(input);
//			ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//			
//			input = "EFFECTIVE_DATE = '2009-01-01' AND ( MEMO_CODE = 'MT' OR MEMO_CODE = 'CO' )";
//			System.out.println("input: " + input);
//			ee = new SqlExpressionEvaluator(input);
//			ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//			
//			input = "( FUND_NUMBER > 5 )";
//			System.out.println("input: " + input);
//			ee = new SqlExpressionEvaluator(input);
//			ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//			
//			input = "( FUND_NUMBER >= 5 )";
//			System.out.println("input: " + input);
//			ee = new SqlExpressionEvaluator(input);
//			ret = ee.eval(td, row);
//			System.out.println("ret: " + ret);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	 }
}
