package com.csc.fsg.life.rest.model;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.util.StringUtils;

import com.csc.fsg.life.rest.model.TreeNodeData.LazyTypeEnum;

public class BusinessRulesTreeSearchInput
	implements Serializable
{
	static private final long serialVersionUID = -9135750839082607328L;

	private LazyTypeEnum lazyType = null;
	private String productCode = null;
	private String planCode = null;
	private String issueState = null;
	private String lob = null;
	private Date effDate = null;
	private boolean viewChanges = true;
	private boolean includeOrphans = false;

	public LazyTypeEnum getLazyType()
	{
		return lazyType;
	}

	public void setLazyType(LazyTypeEnum lazyType)
	{
		this.lazyType = lazyType;
	}

	public void setLazyType(String lazyType)
	{
		if (StringUtils.hasText(lazyType)) {
			if (LazyTypeEnum.C.toString().equals(lazyType))
				this.lazyType = LazyTypeEnum.C;
			else if (LazyTypeEnum.PDF.toString().equals(lazyType))
				this.lazyType = LazyTypeEnum.PDF;
			else if (LazyTypeEnum.H.toString().equals(lazyType))
				this.lazyType = LazyTypeEnum.H;
			else if (LazyTypeEnum.B.toString().equals(lazyType))
				this.lazyType = LazyTypeEnum.B;
			else if (LazyTypeEnum.R.toString().equals(lazyType))
				this.lazyType = LazyTypeEnum.R;
			else if (LazyTypeEnum.P.toString().equals(lazyType))
				this.lazyType = LazyTypeEnum.P;
			else if (LazyTypeEnum.O.toString().equals(lazyType))
				this.lazyType = LazyTypeEnum.O;
			else
				throw new IllegalArgumentException("Unknown String equivalent of LazyTypeEnum detected");
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
