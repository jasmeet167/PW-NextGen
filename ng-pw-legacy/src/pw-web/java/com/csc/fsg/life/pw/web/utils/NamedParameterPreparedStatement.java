package com.csc.fsg.life.pw.web.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.web.log.PWServerLogManager;

/* Modifications: T0129 */

public class NamedParameterPreparedStatement {       

	private static Log _log = PWServerLogManager.getLog(NamedParameterPreparedStatement.class
	        .getPackage().getName());

	private PreparedStatement ps = null;    
	private Map<String, List<Integer>> nameToIndexMap = null;   

	public NamedParameterPreparedStatement(Connection conn, String query) throws SQLException 
	{
		_log.debug("NamedParameterPreparedStatement query in: " + query);
		String parsedQuery=parse(query);       
		_log.debug("NamedParameterPreparedStatement query out: " + parsedQuery);
		ps = conn.prepareStatement(parsedQuery);    
	}
	
	private String parse(String query) {
		nameToIndexMap = new HashMap<String, List<Integer>>();
		StringBuffer parsedQuery = new StringBuffer();
		boolean inSingleQuote = false;
		boolean inDoubleQuote = false;
		int paramNum = 1;
		int length = query.length();

		for (int i=0; i < length; i++) {
			char c = query.charAt(i);
			if (inSingleQuote) {
				if (c == '\'') 
					inSingleQuote = false;
			} else if (inDoubleQuote) {
				if (c == '"') 
					inDoubleQuote = false;
			} else if (c == '\'') {
				inSingleQuote = true;
			} else if (c == '"') {
				inDoubleQuote = true;
			} else if (c == ':') {
				int j = i+1;
				if ( (j < length) && Character.isJavaIdentifierStart(query.charAt(j)) ) {
					j++;
					while ( (j < length) && Character.isJavaIdentifierPart(query.charAt(j)) )
						j++;
					String name = query.substring(i+1, j);
					i += name.length();
					c = '?';

					List<Integer> paramNumList = nameToIndexMap.get(name);
					if (paramNumList == null) {
						paramNumList = new ArrayList<Integer>();
						nameToIndexMap.put(name, paramNumList);
					}
					paramNumList.add(Integer.valueOf(paramNum));
					paramNum++;
				}
			}
			parsedQuery.append(c);
		}

		return parsedQuery.toString();
    }

    private List<Integer> getParamNums(String name) {
        List<Integer> paramNums = nameToIndexMap.get(name);
        if( paramNums == null ) 
            throw new IllegalArgumentException("Unknown parameter: "+name);
        return paramNums;
    }

    public void setObject(String name, Object value) throws SQLException {
    	List<Integer> paramNums = getParamNums(name);
        for (Integer paramNum : paramNums)
			ps.setObject(paramNum.intValue(), value);
    }

    public void setString(String name, String value) throws SQLException {
    	List<Integer> paramNums = getParamNums(name);
        for (Integer paramNum : paramNums)
			ps.setString(paramNum.intValue(), value);
    }

    public void setInt(String name, int value) throws SQLException {
    	List<Integer> paramNums = getParamNums(name);
        for (Integer paramNum : paramNums)
			ps.setInt(paramNum.intValue(), value);
    }

    public void setLong(String name, long value) throws SQLException {
    	List<Integer> paramNums = getParamNums(name);
        for (Integer paramNum : paramNums)
			ps.setLong(paramNum.intValue(), value);
    }

    public void setTimestamp(String name, Timestamp value) throws SQLException {
    	List<Integer> paramNums = getParamNums(name);
        for (Integer paramNum : paramNums)
			ps.setTimestamp(paramNum.intValue(), value);
    }

    public PreparedStatement getStatement() {
        return ps;
    }

    public boolean execute() throws SQLException {
        return ps.execute();
    }

    public ResultSet executeQuery() throws SQLException {
        return ps.executeQuery();
    }

    public int executeUpdate() throws SQLException {
        return ps.executeUpdate();
    }

    public void close() {
    	try {
    		ps.close();
    	} catch (Exception e) {
    		//IGNORE
		}
    }

    public void addBatch() throws SQLException {
        ps.addBatch();
    }

    public int[] executeBatch() throws SQLException {
        return ps.executeBatch();
    }
}