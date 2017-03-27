package com.csc.fsg.life.rest.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.util.StringUtils;

public class BusinessRuleTreeSearchInput
	implements Serializable
{
	static private final long serialVersionUID = -9135750839082607328L;

	private TreeNodeLazyType lazyType = null;
	private String productCode = null;
	private String planCode = null;
	private String issueState = null;
	private String lob = null;
	private Date effDate = null;
	private boolean viewChanges = true;
	private boolean includeOrphans = false;

	public TreeNodeLazyType getLazyType()
	{
		return lazyType;
	}

	public void setLazyType(TreeNodeLazyType lazyType)
	{
		this.lazyType = lazyType;
	}

	public void setLazyType(String lazyType)
	{
		if (StringUtils.hasText(lazyType)) {
			if (TreeNodeLazyType.C.toString().equals(lazyType))
				this.lazyType = TreeNodeLazyType.C;
			else if (TreeNodeLazyType.PDF.toString().equals(lazyType))
				this.lazyType = TreeNodeLazyType.PDF;
			else if (TreeNodeLazyType.H.toString().equals(lazyType))
				this.lazyType = TreeNodeLazyType.H;
			else if (TreeNodeLazyType.B.toString().equals(lazyType))
				this.lazyType = TreeNodeLazyType.B;
			else if (TreeNodeLazyType.R.toString().equals(lazyType))
				this.lazyType = TreeNodeLazyType.R;
			else if (TreeNodeLazyType.P.toString().equals(lazyType))
				this.lazyType = TreeNodeLazyType.P;
			else if (TreeNodeLazyType.O.toString().equals(lazyType))
				this.lazyType = TreeNodeLazyType.O;
			else
				throw new IllegalArgumentException("Unknown String equivalent of TreeNodeLazyType detected");
		}
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

	public boolean getViewChanges()
	{
		return viewChanges;
	}

	public void setViewChanges(boolean viewChanges)
	{
		this.viewChanges = viewChanges;
	}

	public boolean getIncludeOrphans()
	{
		return includeOrphans;
	}

	public void setIncludeOrphans(boolean includeOrphans)
	{
		this.includeOrphans = includeOrphans;
	}
}
