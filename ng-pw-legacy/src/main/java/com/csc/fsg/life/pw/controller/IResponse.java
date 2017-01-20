package com.csc.fsg.life.pw.controller;

import java.io.IOException;
import java.util.*;

/* Modifications: CCCV-E190, V-E190, V-E105, T0091 */

public interface IResponse {

	public void println(String res);
	public void print(String res);
	public void print(int res);
	public void print(char res);

	public String getContentType();
	public void setContentType(String string);

	public Object getResponse();
	public void setResponse(Object buffer);

	public HashMap<String, String> getHeader();
	public void setHeaderInfo(String key, String value);
	
	public String getForwardURL();
	public void setForwardURL(String string);
	
	public String getRedirectURL();
	public void setRedirectURL(String string);
	
	public boolean isIncrementalResponse();
	
	public void close() throws IOException;
	public void println(String s, boolean flush);
}