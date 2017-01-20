package com.csc.fsg.life.pw.controller;

import java.util.HashMap;
import java.util.Hashtable;

public abstract class BaseAction implements IAction {

	public BaseAction() {
	}

	public abstract void process(IRequest req, IResponse res) throws Exception;

	public boolean isRequestValid(Hashtable params) throws Exception {
		return true;
	}

	public static String getRequestParam(HashMap params, String name) {
		String s = (String) params.get(name);
		// if ((s != null) && (s.length() == 0)) {
		// s = null;
		// }
		return s;
	}

	public static String[] getRequestParams(HashMap params, String name) {
		String[] s = null;

		Object obj = params.get(name);

		if (obj != null) {
			if (obj instanceof String) {
				s = new String[1];
				s[0] = (String) obj;
			} else
				s = (String[]) obj;
		}

		return s;
	}

}
