/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.util.*;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.web.controller.BaseAction;

/* Modifications: T0091 */

public abstract class WizObject {

	public WizObject() {
		initialize();
	}

	public void initialize() {
	}

	public WizObject newInstanceOf(Class theClass) {

		WizObject object = null;

		try {
			object = (WizObject) theClass.newInstance();
		} catch (InstantiationException instantiationException) {
			throw WrapperException.wrap(instantiationException);
		} catch (IllegalAccessException illegalAccessException) {
			throw WrapperException.wrap(illegalAccessException);
		}
		return object;
	}

	public Vector<String> asVector(String array[]) {
		Vector<String> vector = new Vector<String>(array.length);
		for (String element : array) {
			vector.addElement(element);
		}
		return vector;
	}

	public String toFullString() {
		StringBuffer buffer = new StringBuffer();
		toFullString(buffer);
		return buffer.toString();
	}

	public void toFullString(StringBuffer buffer) {
		buffer.append(toString());
	}

	public static String getRequestParam(HashMap params, String name) {
		return BaseAction.getRequestParam(params, name);
	}

	public static String[] getRequestParams(HashMap params, String name) {
		return BaseAction.getRequestParams(params, name);
	}

}
