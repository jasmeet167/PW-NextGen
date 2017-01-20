/*
* THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL
* SERVICES GROUP. IT MAY NOT BE COPIED IN WHOLE OR
* IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF
* CSC FINANCIAL SERVICES GROUP.
*/
/*------------Starting  PLANWIP*/
package com.csc.fsg.life.pw.io.descriptor.wma;

import com.csc.fsg.life.pw.io.*;

public class PLANWIPDescriptor extends TableDescriptor{

	private static final String pagingSql = "SELECT ENVIRONMENT,  COMPANY_CODE,  PRODUCT_PREFIX,  PRODUCT_SUFFIX,  PLAN_CODE,  ISSUE_STATE,  LINE_OF_BUSINESS,  EFFECTIVE_DATE,  PLAN_TYPE,  TABLE_PTR_ID,  TABLE_PTR_VAR,  TABLE_PTR_SUBSET,  OPERATION,  OLD_COMPANY_CODE,  OLD_PRODUCT_PREFIX,  OLD_PRODUCT_SUFFIX,  OLD_PLAN_CODE,  OLD_ISSUE_STATE,  OLD_LOB,  OLD_EFFECTIVE_DATE,  OLD_PLAN_TYPE,  OLD_PTR_ID,  OLD_PTR_VAR,  OLD_PTR_SUBSET,  PROJECT_NAME,  CHANGE_USER_ID,  PROMOTE_USER_ID,  TIME_STAMP,  PACKAGE_ID,  ERROR_INDICATOR,  PROMOTION_LOCK,  DB_SEQUENCE   FROM ";

	public void initialize() {
		setRowClass(PLANWIPRow.class);
		setTableName("PLANWIP");
		setTableId("NWI");
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
		addColumnDescriptor(new	ColumnDescriptor	(this,"getTablePtrId","setTablePtrId","TABLE_PTR_ID,1,12,3,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getTablePtrVar","setTablePtrVar","TABLE_PTR_VAR,1,13,1,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getTablePtrSubset","setTablePtrSubset","TABLE_PTR_SUBSET,1,14,16,0,true"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOperation","setOperation","OPERATION,1,15,6,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldCompanyCode","setOldCompanyCode","OLD_COMPANY_CODE,1,16,3,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldProductPrefix","setOldProductPrefix","OLD_PRODUCT_PREFIX,1,17,1,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldProductSuffix","setOldProductSuffix","OLD_PRODUCT_SUFFIX,1,18,1,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldPlanCode","setOldPlanCode","OLD_PLAN_CODE,1,19,6,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldIssueState","setOldIssueState","OLD_ISSUE_STATE,1,22,3,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldLob","setOldLob","OLD_LOB,1,23,3,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldEffectiveDate","setOldEffectiveDate","OLD_EFFECTIVE_DATE,91,24,10,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldPlanType","setOldPlanType","OLD_PLAN_TYPE,1,25,1,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldPtrId","setOldPtrId","OLD_PTR_ID,1,26,3,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldPtrVar","setOldPtrVar","OLD_PTR_VAR,1,27,1,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getOldPtrSubset","setOldPtrSubset","OLD_PTR_SUBSET,1,28,16,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getProjectName","setProjectName","PROJECT_NAME,1,29,16,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getChangeUserId","setChangeUserId","CHANGE_USER_ID,1,30,32,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getPromoteUserId","setPromoteUserId","PROMOTE_USER_ID,1,31,32,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getTimeStamp","setTimeStamp","TIME_STAMP,93,32,26,6,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getPackageId","setPackageId","PACKAGE_ID,1,33,16,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getErrorIndicator","setErrorIndicator","ERROR_INDICATOR,1,34,1,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getPromotionLock","setPromotionLock","PROMOTION_LOCK,1,35,1,0,false"));
		addColumnDescriptor(new	ColumnDescriptor	(this,"getDbSequence","setDbSequence","DB_SEQUENCE,1,36,16,0,false"));
	}

	public String getPagingSQL(String schemaName,boolean isSubsetMode,boolean isNext, boolean locator) {
		String pagingWhere = null;
		String order = null;
		 if (isNext) {		
				 if (isSubsetMode) 
					 if (locator) 
						pagingWhere = ".PLANWIP WHERE ( COMPANY_CODE = ? ) AND ( PRODUCT_PREFIX = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE CONCAT  TABLE_PTR_ID CONCAT  TABLE_PTR_VAR CONCAT  TABLE_PTR_SUBSET >= ?  ) ";
					else 
						pagingWhere = ".PLANWIP WHERE ( COMPANY_CODE = ? ) AND ( PRODUCT_PREFIX = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE CONCAT  TABLE_PTR_ID CONCAT  TABLE_PTR_VAR CONCAT  TABLE_PTR_SUBSET > ?  ) ";
				else 
					 if (locator) 
						pagingWhere = ".PLANWIP WHERE ( COMPANY_CODE = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE CONCAT  TABLE_PTR_ID CONCAT  TABLE_PTR_VAR CONCAT  TABLE_PTR_SUBSET >= ?  ) ";
					else 
						pagingWhere = ".PLANWIP WHERE ( COMPANY_CODE = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE CONCAT  TABLE_PTR_ID CONCAT  TABLE_PTR_VAR CONCAT  TABLE_PTR_SUBSET > ?  ) ";
		 order = " ORDER BY ENVIRONMENT , COMPANY_CODE , PRODUCT_PREFIX , PRODUCT_SUFFIX , PLAN_CODE , ISSUE_STATE , LINE_OF_BUSINESS , EFFECTIVE_DATE , PLAN_TYPE , TABLE_PTR_ID , TABLE_PTR_VAR , TABLE_PTR_SUBSET"; 
		}
		else {		
				 if (isSubsetMode) 
					pagingWhere = ".PLANWIP WHERE ( COMPANY_CODE = ? ) AND ( PRODUCT_PREFIX = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE CONCAT  TABLE_PTR_ID CONCAT  TABLE_PTR_VAR CONCAT  TABLE_PTR_SUBSET < ?  ) ";
				else 
					pagingWhere = ".PLANWIP WHERE ( COMPANY_CODE = ? ) AND (  ENVIRONMENT CONCAT  COMPANY_CODE CONCAT  PRODUCT_PREFIX CONCAT  PRODUCT_SUFFIX CONCAT  PLAN_CODE CONCAT  ISSUE_STATE CONCAT  LINE_OF_BUSINESS CONCAT  CHAR(EFFECTIVE_DATE)  CONCAT  PLAN_TYPE CONCAT  TABLE_PTR_ID CONCAT  TABLE_PTR_VAR CONCAT  TABLE_PTR_SUBSET < ?  ) ";
		 order = " ORDER BY ENVIRONMENT DESC  , COMPANY_CODE DESC  , PRODUCT_PREFIX DESC  , PRODUCT_SUFFIX DESC  , PLAN_CODE DESC  , ISSUE_STATE DESC  , LINE_OF_BUSINESS DESC  , EFFECTIVE_DATE DESC  , PLAN_TYPE DESC  , TABLE_PTR_ID DESC  , TABLE_PTR_VAR DESC  , TABLE_PTR_SUBSET DESC ";
		}
		return pagingSql+schemaName+pagingWhere+order;
	}
	public String prepareInsertStmt(String schemaName) {
		StringBuffer sb = new StringBuffer();
		sb.append("INSERT INTO ").append(schemaName);
		sb.append(".PLANWIP ( ");
		sb.append("ENVIRONMENT, COMPANY_CODE, PRODUCT_PREFIX, PRODUCT_SUFFIX, PLAN_CODE, ISSUE_STATE, LINE_OF_BUSINESS, EFFECTIVE_DATE, PLAN_TYPE, TABLE_PTR_ID, TABLE_PTR_VAR, TABLE_PTR_SUBSET, OPERATION, OLD_COMPANY_CODE, OLD_PRODUCT_PREFIX, OLD_PRODUCT_SUFFIX, OLD_PLAN_CODE, OLD_ISSUE_STATE, OLD_LOB, OLD_EFFECTIVE_DATE, OLD_PLAN_TYPE, OLD_PTR_ID, OLD_PTR_VAR, OLD_PTR_SUBSET, PROJECT_NAME, CHANGE_USER_ID, PROMOTE_USER_ID, TIME_STAMP, PACKAGE_ID, ERROR_INDICATOR, PROMOTION_LOCK, DB_SEQUENCE )");
		sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
		return sb.toString();
	}

	
	public String prepareUpdateStmt(String schemaName) {
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE ").append(schemaName);
		sb.append(".PLANWIP ");
		sb.append(" SET ENVIRONMENT = ?, COMPANY_CODE = ?, PRODUCT_PREFIX = ?, PRODUCT_SUFFIX = ?, PLAN_CODE = ?, ISSUE_STATE = ?, LINE_OF_BUSINESS = ?, EFFECTIVE_DATE = ?, PLAN_TYPE = ?, TABLE_PTR_ID = ?, TABLE_PTR_VAR = ?, TABLE_PTR_SUBSET = ?, OPERATION = ?, OLD_COMPANY_CODE = ?, OLD_PRODUCT_PREFIX = ?, OLD_PRODUCT_SUFFIX = ?, OLD_PLAN_CODE = ?, OLD_ISSUE_STATE = ?, OLD_LOB = ?, OLD_EFFECTIVE_DATE = ?, OLD_PLAN_TYPE = ?, OLD_PTR_ID = ?, OLD_PTR_VAR = ?, OLD_PTR_SUBSET = ?, PROJECT_NAME = ?, CHANGE_USER_ID = ?, PROMOTE_USER_ID = ?, TIME_STAMP = ?, PACKAGE_ID = ?, ERROR_INDICATOR = ?, PROMOTION_LOCK = ?, DB_SEQUENCE = ? ");
		sb.append(" WHERE ENVIRONMENT = ?  AND COMPANY_CODE = ?  AND PRODUCT_PREFIX = ?  AND PRODUCT_SUFFIX = ?  AND PLAN_CODE = ?  AND ISSUE_STATE = ?  AND LINE_OF_BUSINESS = ?  AND EFFECTIVE_DATE = ?  AND PLAN_TYPE = ?  AND TABLE_PTR_ID = ?  AND TABLE_PTR_VAR = ?  AND TABLE_PTR_SUBSET = ? ");
		return sb.toString();
	}

	
	public String prepareDeleteStmt(String schemaName) {
		StringBuffer sb = new StringBuffer();
		sb.append("DELETE FROM ").append(schemaName);
		sb.append(".PLANWIP ");
		sb.append(" WHERE ENVIRONMENT = ?  AND COMPANY_CODE = ?  AND PRODUCT_PREFIX = ?  AND PRODUCT_SUFFIX = ?  AND PLAN_CODE = ?  AND ISSUE_STATE = ?  AND LINE_OF_BUSINESS = ?  AND EFFECTIVE_DATE = ?  AND PLAN_TYPE = ?  AND TABLE_PTR_ID = ?  AND TABLE_PTR_VAR = ?  AND TABLE_PTR_SUBSET = ? ");
		return sb.toString();
	}

}
/*------------End  PLANWIPTableDescp.java-----------*/
