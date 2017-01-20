/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.rules;

/**
 * @author Peter Santoro
 */

import java.lang.reflect.*;

/**
 * Class RulesMgr
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class RulesMgr {

	static final String BASEPATH = "com.csc.fsg.life.pw.common.rules.";

	static final String RULES_BASECLASS = "com.csc.fsg.life.pw.common.rules.BaseRule";

	static final String STRING_CLASS = "java.lang.String";

	private String _table;

	private Class _consParams[];

	private Object _objParams[];

	/**
	 * Constructor RulesMgr
	 * 
	 * @param table
	 * @throws ClassNotFoundException
	 */

	public RulesMgr(String table) throws ClassNotFoundException {

		this();
		_table = table;
	}

	/**
	 * Method getRules
	 * 
	 * @param column
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */

	public BaseRule getRules(String column) throws ClassNotFoundException,
	        NoSuchMethodException, InstantiationException,
	        IllegalAccessException, InvocationTargetException, Throwable {
		return getRules(_table, column);
	}

	/**
	 * Method getRules
	 * 
	 * @param table
	 * @param column
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */

	public BaseRule getRules(String table, String column)
	        throws ClassNotFoundException, NoSuchMethodException,
	        InstantiationException, IllegalAccessException,
	        InvocationTargetException, Throwable {

		String rulesClass = getRulesClass(table, column);

		_objParams[0] = table;
		_objParams[1] = column;

		return (BaseRule) createObject(rulesClass, _consParams, _objParams,
		        RULES_BASECLASS);
	}

	private String getRulesClass(String table, String column) {

		StringBuffer sb = new StringBuffer(80);

		sb.append(BASEPATH);

		sb.append(table);
		sb.append(".");
		sb.append(column);

		return sb.toString();
	}

	private Object createObject(String className, Class consParams[],
	        Object objParams[], String baseClass)
	        throws ClassNotFoundException, NoSuchMethodException,
	        InstantiationException, IllegalAccessException,
	        InvocationTargetException, Throwable { // SRAMAM 10/2003 WAS5 also
		// bubbles up Error objects

		Object obj;
		Class xclass;
		Constructor cons;

		xclass = Class.forName(className);

		if (!inheritsFrom(xclass, baseClass)) {
			throw new InstantiationException("class must inherit from "
			        + baseClass);
		}

		cons = xclass.getConstructor(consParams);

		obj = cons.newInstance(objParams);

		return obj;
	}

	private boolean inheritsFrom(Class c, String superClass) {

		while ((c = c.getSuperclass()) != null) {
			if (c.getName().equals(superClass)) {
				return (true);
			}
		}

		return true;
	}

	private RulesMgr() throws ClassNotFoundException {

		_consParams = new Class[2];
		_consParams[0] = Class.forName(STRING_CLASS);
		_consParams[1] = Class.forName(STRING_CLASS);

		_objParams = new Object[2];
		_objParams[0] = null;
		_objParams[1] = null;
	}
}
