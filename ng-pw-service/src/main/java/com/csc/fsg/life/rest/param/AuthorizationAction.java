package com.csc.fsg.life.rest.param;

public enum AuthorizationAction {
	NONE { @Override public String toString() { return ""; }},
	VIEW { @Override public String toString() { return "view"; }},
	UPDATE { @Override public String toString() { return "update"; }},
	UPDATE_ETV { @Override public String toString() { return "update_etv"; }},
	PROMOTE { @Override public String toString() { return "promote"; }},
	AUDIT_PURGE { @Override public String toString() { return "audit_purge"; }}
}
