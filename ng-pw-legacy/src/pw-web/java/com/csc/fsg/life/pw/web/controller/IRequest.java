package com.csc.fsg.life.pw.web.controller;

import java.io.BufferedReader;
import java.util.HashMap;

//import com.csc.fsg.life.pw.common.User;

/** Modifications V-E190, V-E105, T0106 */
public interface IRequest {

	public static final int SCOPE_REQUEST = 0;

	public static final int SCOPE_SESSION = 1;

	public static final int SCOPE_APPLICATION = 2;

	public HashMap getRequest();

	public void setRequest(HashMap req);

//	public User getUser();

//	public void setUser(User obj);

	public BufferedReader getReader();

	public void setReader(BufferedReader obj);

	public String getContentType();

	public void setContentType(String string);

//	public void setSession(PWSession map);

//	public PWSession getSession();

	public void setAttribute(String key, Object obj, int scope)
	        throws Exception;

	public Object getAttribute(String key, int scope) throws Exception;

	public void removeAttribute(String key, int scope) throws Exception;

	public String getRemoteAddr();

	public void setRemoteAddr(String addr);

	public String getActionName();

	public void setActionName(String string);

	public String getContextPath();

	public void setContextPath(String context);
	
	public boolean isIncrementalResponse();
	
	public void setIncrementalResponse(boolean b);
	
//	public void setSessionLauncher(SessionLauncher launcher);

	public void launchNewSession();

}
