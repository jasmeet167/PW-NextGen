package com.csc.fsg.life.session;

import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.SecurityManager;

/* Modifications: ENH1220 */

public class ServiceSession {
	
	private SecurityManager baseSecurityManager;
	private UserContext userContext;
	
	/** Thread local variable containing the service Session*/
    private static ThreadLocal<ServiceSession> session = new ThreadLocal<ServiceSession>();
	
	public SecurityManager getBaseSecurityManager() {
		return baseSecurityManager;
	}
	public void setBaseSecurityManager(SecurityManager baseSecurityManager) {
		this.baseSecurityManager = baseSecurityManager;
	}
	public UserContext getUserContext() {
		return userContext;
	}
	public void setUserContext(UserContext userContext) {
		this.userContext = userContext;
	}
	
	
	public static ServiceSession getSession() {
        return session.get();
    }
    
    public static void setSession(ServiceSession env) {
        session.set(env);
    }

}
