package com.csc.fsg.life.rest.model.tree;

import java.io.Serializable;
import java.sql.Date;

import org.joda.time.LocalDate;
import org.springframework.util.StringUtils;

public class PlanKey
	implements Serializable
{
	static private final long serialVersionUID = 364937485377082868L;

	private String environmentId = null;
	private String companyCode = null;
	private String productPrefix = null;
	private String productSuffix = null;
	private String planCode = null;
	private String issueState = null;
	private String lineOfBusiness = null;
	private LocalDate effectiveDate = null;
	private String planType = null;
	private String tablePtrId = null;
	private String tablePtrVar = null;
	private String tablePtrSubset = null;

	public PlanKey()
	{
	}

	public void setValues(String[] streamComponents)
	{
		int i = 0;
		if (i < streamComponents.length)
			companyCode = streamComponents[i++];
		if (i < streamComponents.length)
			productPrefix = streamComponents[i++];
		if (i < streamComponents.length)
			productSuffix = streamComponents[i++];
		if (i < streamComponents.length)
			planCode = streamComponents[i++];
		if (i < streamComponents.length)
			issueState = streamComponents[i++];
		if (i < streamComponents.length)
			lineOfBusiness = streamComponents[i++];
		if (i < streamComponents.length) {
			Date date = Date.valueOf(streamComponents[i++]);
			effectiveDate = new LocalDate(date.getTime());
		}
		if (i < streamComponents.length)
			planType = streamComponents[i++];
		if (i < streamComponents.length)
			tablePtrId = streamComponents[i++];
		if (i < streamComponents.length)
			tablePtrVar = streamComponents[i++];
		if (i < streamComponents.length)
			tablePtrSubset = streamComponents[i++];
	}

	public String getEnvironmentId()
	{
		return environmentId;
	}

	public void setEnvironmentId(String environmentId)
	{
		this.environmentId = environmentId;
	}

	public String getCompanyCode()
	{
		return companyCode;
	}

	public void setCompanyCode(String companyCode)
	{
		this.companyCode = companyCode;
	}

	public String getProductPrefix()
	{
		return productPrefix;
	}

	public void setProductPrefix(String productPrefix)
	{
		this.productPrefix = productPrefix;
	}

	public String getProductSuffix()
	{
		return productSuffix;
	}

	public void setProductSuffix(String productSuffix)
	{
		this.productSuffix = productSuffix;
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

	public String getLineOfBusiness()
	{
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness)
	{
		this.lineOfBusiness = lineOfBusiness;
	}

	public LocalDate getEffectiveDate()
	{
		return effectiveDate;
	}

	public void setEffectiveDate(LocalDate effectiveDate)
	{
		this.effectiveDate = effectiveDate;
	}

	public String getPlanType()
	{
		return planType;
	}

	public void setPlanType(String planType)
	{
		this.planType = planType;
	}

	public String getTablePtrId()
	{
		return tablePtrId;
	}

	public void setTablePtrId(String tablePtrId)
	{
		this.tablePtrId = tablePtrId;
	}

	public String getTablePtrVar()
	{
		return tablePtrVar;
	}

	public void setTablePtrVar(String tablePtrVar)
	{
		this.tablePtrVar = tablePtrVar;
	}

	public String getTablePtrSubset()
	{
		return tablePtrSubset;
	}

	public void setTablePtrSubset(String tablePtrSubset)
	{
		this.tablePtrSubset = tablePtrSubset;
	}

	public boolean isEmpty()
	{
		if (StringUtils.hasText(environmentId))
			return false;
		if (StringUtils.hasText(companyCode))
			return false;
		if (StringUtils.hasText(productPrefix))
			return false;
		if (StringUtils.hasText(productSuffix))
			return false;
		if (StringUtils.hasText(planCode))
			return false;
		if (StringUtils.hasText(issueState))
			return false;
		if (StringUtils.hasText(lineOfBusiness))
			return false;
		if (effectiveDate != null)
			return false;
		if (StringUtils.hasText(planType))
			return false;
		if (StringUtils.hasText(tablePtrId))
			return false;
		if (StringUtils.hasText(tablePtrVar))
			return false;
		if (StringUtils.hasText(tablePtrSubset))
			return false;

		return true;
	}
}
