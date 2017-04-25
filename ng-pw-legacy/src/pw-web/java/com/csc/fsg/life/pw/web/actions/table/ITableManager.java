package com.csc.fsg.life.pw.web.actions.table;

import java.util.HashMap;

/**
 * @author smunigun
 */
public interface ITableManager {

	/**
	 * @param req
	 * @param user
	 * @return Data Stream to be returned to client
	 * @throws Exception
	 */
	public abstract StringBuffer getData(HashMap req, int userAuthority)
	        throws Exception;
}
