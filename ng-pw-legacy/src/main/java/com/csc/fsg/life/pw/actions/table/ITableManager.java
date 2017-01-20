package com.csc.fsg.life.pw.actions.table;

import java.util.HashMap;
import java.util.List;

import com.csc.fsg.life.pw.io.Row;

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
	public abstract List<Row> getData(HashMap req, int userAuthority)
	        throws Exception;
}
