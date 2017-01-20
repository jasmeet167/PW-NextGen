package com.csc.fsg.life.tools.copybook.model;

public class EventInfo {

	private String name;
	private String interfaces;
	private CopybookInfo copybookInfo = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInterfaces() {
		return interfaces;
	}

	public void setInterfaces(String interfaces) {
		this.interfaces = interfaces;
	}

	public CopybookInfo getCopybookInfo() {
		return copybookInfo;
	}

	public void setCopybookInfo(CopybookInfo copybookInfo) {
		this.copybookInfo = copybookInfo;
	}
}