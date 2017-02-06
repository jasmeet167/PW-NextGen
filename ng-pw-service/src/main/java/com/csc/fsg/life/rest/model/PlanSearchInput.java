package com.csc.fsg.life.rest.model;

import java.io.Serializable;

public class PlanSearchInput
	implements Serializable
{
	static private final long serialVersionUID = 1503370388134913074L;

	private Boolean isViewChangesEffective = null;
	private String productCode = null;
	private String planCode = null;
	private String issueState = null;
	private String lob = null;

	public Boolean isViewChangesEffective()
	{
		return isViewChangesEffective;
	}

	public void setViewChangesEffective(Boolean isViewChangesEffective)
	{
		this.isViewChangesEffective = isViewChangesEffective;
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
