package com.csc.fsg.life.pw.common.transferobjects;

import java.util.HashMap;

/* Modifications: T0103, T0091 */
// T0103 - new class added for refactor plan key

public abstract class PlanTOBase {
	
	public static final String ENVIRONMENT_NAME = "ENVIRONMENT";
	public static final String SCHEMA_NAME = "SCHEMA";
	public static final String COMPANY_CODE_NAME = "COMPANY_CODE";
	public static final String PRODUCT_CODE_NAME = "PRODUCT_CODE";
	public static final String PRODUCT_PREFIX_NAME = "PRODUCT_PREFIX";
	public static final String PRODUCT_SUFFIX_NAME = "PRODUCT_SUFFIX";
	public static final String PLAN_CODE_NAME = "PLAN_CODE";
	public static final String ISSUE_STATE_NAME = "ISSUE_STATE";
	public static final String LINE_OF_BUSINESS_NAME = "LINE_OF_BUSINESS";
	public static final String EFFECTIVE_DATE_NAME = "EFFECTIVE_DATE";
	public static final String PLAN_TYPE_NAME = "PLAN_TYPE";
	public static final String PLAN_NAME_NAME = "PLAN_NAME";
	
	public static final String ENVIRONMENT_KEY = ENVIRONMENT_NAME.toLowerCase();
	public static final String SCHEMA_KEY = SCHEMA_NAME.toLowerCase();
	public static final String COMPANY_CODE_KEY = COMPANY_CODE_NAME.toLowerCase();
	public static final String PRODUCT_CODE_KEY = PRODUCT_CODE_NAME.toLowerCase();
	public static final String PRODUCT_PREFIX_KEY = PRODUCT_PREFIX_NAME.toLowerCase();
	public static final String PRODUCT_SUFFIX_KEY = PRODUCT_SUFFIX_NAME.toLowerCase();
	public static final String PLAN_CODE_KEY = PLAN_CODE_NAME.toLowerCase();
	public static final String ISSUE_STATE_KEY = ISSUE_STATE_NAME.toLowerCase();
	public static final String LINE_OF_BUSINESS_KEY = LINE_OF_BUSINESS_NAME.toLowerCase();
	public static final String EFFECTIVE_DATE_KEY = EFFECTIVE_DATE_NAME.toLowerCase();
	public static final String PLAN_TYPE_KEY = PLAN_TYPE_NAME.toLowerCase();
	public static final String PLAN_NAME_KEY = PLAN_NAME_NAME.toLowerCase();
	
	public static int PLAN_KEYS = 8;
	protected static int PLAN_FIELDS = PLAN_KEYS + 3;
	
	private String environment = "";
	private String companyCode = "";
	private String productPrefix = "";
	private String productSuffix = "";
	private String planCode = "";
	private String issueState = "";
	private String lineOfBusiness = "";
	private String effectiveDate = "";
	private String planType = "";
	private String planName = "";
	
	public int getPLAN_FIELDS() {
		return PLAN_FIELDS;
	}

	protected String getFieldValue(String value) {
		if (value == null) {
			value = "";
		}
		return value.toUpperCase().trim();
	}

	protected String getTokenValue(String value) {
		if (value.equals("")) {
			value = " ";
		}
		return value;
	}

	protected String getMapValue(HashMap hashMap, String key) {
		String value = (String) hashMap.get(key);
		if (value == null) {
			value = "";
		}
		return value;
	}
	
	protected void setMapValue(HashMap<String, String> hashMap, String key, String value) {
		if ( (value != null) && !value.equals("") ) {
			hashMap.put(key, value);
		}
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = getFieldValue(companyCode);
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = getFieldValue(effectiveDate);
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		//CCC-VE712
		if (environment == null) 
			environment = "";
		this.environment = environment.trim();//getFieldValue(environment);
	}

	public String getIssueState() {
		return issueState;
	}

	public void setIssueState(String issueState) {
		this.issueState = getFieldValue(issueState);
	}

	public String getLineOfBusiness() {
		return lineOfBusiness;
	}

	public void setLineOfBusiness(String lineOfBusiness) {
		this.lineOfBusiness = getFieldValue(lineOfBusiness);
	}

	public String getPlanCode() {
		return planCode;
	}

	public void setPlanCode(String planCode) {
		this.planCode = getFieldValue(planCode);
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = getFieldValue(planType);
	}

	public String getProductPrefix() {
		return productPrefix;
	}

	public void setProductPrefix(String productPrefix) {
		this.productPrefix = getFieldValue(productPrefix);
	}

	public String getProductSuffix() {
		return productSuffix;
	}

	public void setProductSuffix(String productSuffix) {
		this.productSuffix = getFieldValue(productSuffix);
	}

	public String getProductCode() {
		if ( productPrefix != null && productPrefix.trim().length() > 0 ) {
			if ( productSuffix != null && productSuffix.trim().length() > 0 ) {
				return productPrefix + productSuffix;
			}
			return productPrefix + " ";
		}
								
		return null;
	}

	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = getFieldValue(planName);
	}
}

