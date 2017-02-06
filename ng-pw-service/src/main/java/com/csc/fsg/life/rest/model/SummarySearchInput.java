package com.csc.fsg.life.rest.model;

import java.io.Serializable;

public class SummarySearchInput
	implements Serializable
{
	static private final long serialVersionUID = 2458170108440451089L;

	private String filterAspect = null;
	private String productCode = null;
	private String planCode = null;
	private String issueState = null;
	private String lob = null;

	public String getFilterAspect()
	{
		return filterAspect;
	}

	public void setFilterAspect(String filterAspect)
	{
		this.filterAspect = filterAspect;
	}

	public String getProductCode()
	{
		return productCode;
	}

	public void setProductCode(String productCode)
	{
		this.productCode = productCode;
	}

	public String getPlanCode()
	{
		return planCode;
	}

	public void setPlanCode(String planCode)
	{
		this.planCode = planCode;
	}

	public String getIssueState()
	{
		return issueState;
	}

	public void setIssueState(String issueState)
	{
		this.issueState = issueState;
	}

	public String getLob()
	{
		return lob;
	}

	public void setLob(String lob)
	{
		this.lob = lob;
	}
}
