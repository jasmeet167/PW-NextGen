package com.csc.fsg.life.rest.model;

import java.io.Serializable;
import java.sql.Date;

public class BusinessRuleTreeSearchInput
	implements Serializable
{
	static private final long serialVersionUID = -9135750839082607328L;

	private boolean areChangesIncluded = true;
	private String productCode = null;
	private String planCode = null;
	private String issueState = null;
	private String lob = null;
	private Date effDate = null;
	private boolean areOrphansIncluded = false;

	public boolean isAreChangesIncluded()
	{
		return areChangesIncluded;
	}

	public void setAreChangesIncluded(boolean areChangesIncluded)
	{
		this.areChangesIncluded = areChangesIncluded;
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

	public Date getEffDate()
	{
		return effDate;
	}

	public void setEffDate(Date effDate)
	{
		this.effDate = effDate;
	}

	public boolean areOrphansIncluded()
	{
		return areOrphansIncluded;
	}

	public void setOrphansIncluded(boolean areOrphansIncluded)
	{
		this.areOrphansIncluded = areOrphansIncluded;
	}
}
