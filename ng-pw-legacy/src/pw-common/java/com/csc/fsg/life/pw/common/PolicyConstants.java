package com.csc.fsg.life.pw.common;

/* Modifications: T0120, ENH1043 */

public interface PolicyConstants {
	String ROLE = "role";

	String RESOURCE_ID = "resource-id";

	String ACTION_ID = "action-id";

	String VIEW = "VIEW";

	String UPDATE = "UPDATE";

	String UPDATE_ETV = "UPDATE_ETV";

	String AUDIT_PURGE = "AUDIT_PURGE";
	
	String PROMOTE = "PROMOTE";
	
	final String[] envActions = { VIEW, UPDATE, UPDATE_ETV, AUDIT_PURGE, PROMOTE}; 
}
