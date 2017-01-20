package com.csc.fsg.life.tools.copybook.model;

import java.util.HashMap;
import java.util.Map;

/* Modifications: T0127 */

public class CopybookInfo {

	private String name;
	private String pkg;
	private int internalNameSize;	
	private Map<String, CopybookFieldGenInfo> fieldGenInfoMap = new HashMap<String, CopybookFieldGenInfo>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPkg() {
		return pkg;
	}

	public void setPkg(String pkg) {
		this.pkg = pkg;
	}

	public Map<String, CopybookFieldGenInfo> getFieldGenInfoMap() {
		return fieldGenInfoMap;
	}
	
	public void addFieldGenInfo(String fullqualname, CopybookFieldGenInfo fieldGenInfo) {
		fieldGenInfoMap.put(fullqualname, fieldGenInfo);
	}
	
	public int getInternalNameSize()
	{
		return internalNameSize;
	}

	public void setInternalNameSize(int internalNameSize) 
	{
		this.internalNameSize = internalNameSize;
	}
	
}