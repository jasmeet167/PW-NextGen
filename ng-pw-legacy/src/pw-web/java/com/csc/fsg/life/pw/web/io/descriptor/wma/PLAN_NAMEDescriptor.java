/*
* THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL
* SERVICES GROUP. IT MAY NOT BE COPIED IN WHOLE OR
* IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF
* CSC FINANCIAL SERVICES GROUP.
*/
/*------------Starting  PLAN_NAME*/
package com.csc.fsg.life.pw.web.io.descriptor.wma;

import com.csc.fsg.life.pw.web.io.*;

public class PLAN_NAMEDescriptor extends TableDescriptor{

	private static final String pagingSql = "SELECT ENVIRONMENT,  COMPANY_CODE,  PRODUCT_PREFIX,  PRODUCT_SUFFIX,  PLAN_CODE,  ISSUE_STATE,  LINE_OF_BUSINESS,  EFFECTIVE_DATE,  PLAN_TYPE,  PLAN_NAME   FROM ";

	public void initialize() {
		setRowClass(PLAN_NAMERow.class);
		setTableName("PLAN_NAME");
		setTableId("NAM");
		super.initialize();
	}

	public void initializeColumnDescriptors() {
		super.initializeColumnDescriptors();
		addColumnDescriptor(new	ColumnDescriptor	(this,"getEnvironment","setEnvironment","ENVIRONMENT,1,1,33,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getCompanyCode","setCompanyCode","COMPANY_CODE,1,2,3,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getProductPrefix","setProductPrefix","PRODUCT_PREFIX,1,3,1,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getProductSuffix","setProductSuffix","PRODUCT_SUFFIX,1,4,1,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getPlanCode","setPlanCode","PLAN_CODE,1,5,6,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getIssueState","setIssueState","ISSUE_STATE,1,8,3,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getLineOfBusiness","setLineOfBusiness","LINE_OF_BUSINESS,1,9,3,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getEffectiveDate","setEffectiveDate","EFFECTIVE_DATE,91,10,10,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getPlanType","setPlanType","PLAN_TYPE,1,11,1,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getPlanName","setPlanName","PLAN_NAME,1,12,30,0,false"));
	}

	public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator) {
		String pagingWhere = null;
		String order = null;
		 if (isNext) {		
				 if (isSubsetMode) 
					 if (locator) 
						pagingWhere = ".PLAN_NAME WHERE ( COMPANY_CODE = ? ) AND ( PRODUCT_PREFIX = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE  CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE >= ?  ) ";
					else 
						pagingWhere = ".PLAN_NAME WHERE ( COMPANY_CODE = ? ) AND ( PRODUCT_PREFIX = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE  CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE > ?  ) ";
				else 
					 if (locator) 
						pagingWhere = ".PLAN_NAME WHERE ( COMPANY_CODE = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE >= ?  ) ";
					else 
						pagingWhere = ".PLAN_NAME WHERE ( COMPANY_CODE = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE > ?  ) ";
		 order = " ORDER BY ENVIRONMENT , COMPANY_CODE , PRODUCT_PREFIX , PRODUCT_SUFFIX , PLAN_CODE , ISSUE_STATE , LINE_OF_BUSINESS , EFFECTIVE_DATE , PLAN_TYPE"; 
		}
		else {		
				 if (isSubsetMode) 
					pagingWhere = ".PLAN_NAME WHERE ( COMPANY_CODE = ? ) AND ( PRODUCT_PREFIX = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE < ?  ) ";
				else 
					pagingWhere = ".PLAN_NAME WHERE ( COMPANY_CODE = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE < ?  ) ";
		 order = " ORDER BY ENVIRONMENT DESC  , COMPANY_CODE DESC  , PRODUCT_PREFIX DESC  , PRODUCT_SUFFIX DESC  , PLAN_CODE DESC  , ISSUE_STATE DESC  , LINE_OF_BUSINESS DESC  , EFFECTIVE_DATE DESC  , PLAN_TYPE DESC ";
		}
		return pagingSql+schemaName+pagingWhere+order;
	}
	public String prepareInsertStmt(String schemaName) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ").append(schemaName);
		sb.append(".PLAN_NAME ( ");
		sb.append("ENVIRONMENT, COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, ISSUE_STATE, LINE_OF_BUSINESS, EFFECTIVE_DATE, PLAN_TYPE, PLAN_NAME )");
		sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		return sb.toString();
	}

	
	public String prepareUpdateStmt(String schemaName) {
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ").append(schemaName);
		sb.append(".PLAN_NAME ");
		sb.append(" SET ENVIRONMENT = ?, COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, PLAN_CODE = ?, ISSUE_STATE = ?, LINE_OF_BUSINESS = ?, EFFECTIVE_DATE = ?, PLAN_TYPE = ?, PLAN_NAME = ? ");
		sb.append(" WHERE ENVIRONMENT = ?  AND COMPANY_CODE = ?  AND PRODUCT_PREFIX = ?  AND PRODUCT_SUFFIX = ?  AND PLAN_CODE = ?  AND ISSUE_STATE = ?  AND LINE_OF_BUSINESS = ?  AND EFFECTIVE_DATE = ?  AND PLAN_TYPE = ? ");
		return sb.toString();
	}

	
	public String prepareDeleteStmt(String schemaName) {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM ").append(schemaName);
		sb.append(".PLAN_NAME ");
		sb.append(" WHERE ENVIRONMENT = ?  AND COMPANY_CODE = ?  AND PRODUCT_PREFIX = ?  AND PRODUCT_SUFFIX = ?  AND PLAN_CODE = ?  AND ISSUE_STATE = ?  AND LINE_OF_BUSINESS = ?  AND EFFECTIVE_DATE = ?  AND PLAN_TYPE = ? ");
		return sb.toString();
	}

}
/*------------End  PLAN_NAMETableDescp.java-----------*/
