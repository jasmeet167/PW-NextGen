/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.util;

import java.io.IOException;
import com.csc.fsg.life.common.util.ExtProperties;
import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.util.Constants;

/**
 * Class WIPProperties
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class WIPProperties {

	private static ExtProperties wipProp = null;

	private static final WIPProperties INSTANCE = new WIPProperties();

	static {
		try {
			wipProp = new ExtProperties("WIP.properties");
		} catch (IOException ioe) {
			throw WrapperException.wrap(ioe, IOException.class);
		} catch (IllegalArgumentException iae) {
			throw WrapperException.wrap(iae, IllegalArgumentException.class);
		}
	}

	private WIPProperties() {
	}

	/**
	 * Method getInstance
	 * 
	 * @return
	 */

	public static synchronized WIPProperties getInstance() {
		return (INSTANCE);
	}

	/**
	 * Method getEnvironment
	 * 
	 * @return
	 */

	public String getEnvironment() {
		return getEnvironment(1);
	}

	/**
	 * Method getEnvironment
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getEnvironment(int typeOfWip) throws IllegalArgumentException {

		String env = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			env = wipProp.getProperty("COMMONWIP.ENVIRONMENT");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			env = wipProp.getProperty("PLANWIP.ENVIRONMENT");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			env = wipProp.getProperty("INDEXWIP.ENVIRONMENT");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			env = wipProp.getProperty("AUDIT.ENVIRONMENT");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return env;
	}

	/**
	 * Method getCompanyCode
	 * 
	 * @return
	 */

	public String getCompanyCode() {
		return getCompanyCode(1);
	}

	/**
	 * Method getCompanyCode
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getCompanyCode(int typeOfWip) throws IllegalArgumentException {

		String cCode = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			cCode = wipProp.getProperty("COMMONWIP.COMPANY_CODE");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			cCode = wipProp.getProperty("PLANWIP.COMPANY_CODE");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			cCode = wipProp.getProperty("INDEXWIP.COMPANY_CODE");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			cCode = wipProp.getProperty("AUDIT.COMPANY_CODE");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return cCode;
	}

	/**
	 * Method getTableName
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getTableName(int typeOfWip) throws IllegalArgumentException {

		String tableName = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			tableName = wipProp.getProperty("COMMONWIP.TABLENAME");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			tableName = wipProp.getProperty("PLANWIP.TABLENAME");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			tableName = wipProp.getProperty("INDEXWIP.TABLENAME");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			tableName = wipProp.getProperty("AUDIT.TABLENAME");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return tableName;
	}

	/**
	 * Method getDBSequence
	 * 
	 * @return
	 */

	public String getDBSequence() {
		return getDBSequence(1);
	}

	/**
	 * Method getDBSequence
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getDBSequence(int typeOfWip) throws IllegalArgumentException {

		String dbSequence = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			dbSequence = wipProp.getProperty("COMMONWIP.DB_SEQUENCE");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			dbSequence = wipProp.getProperty("PLANWIP.DB_SEQUENCE");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			dbSequence = wipProp.getProperty("INDEXWIP.DB_SEQUENCE");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			dbSequence = wipProp.getProperty("AUDIT.DB_SEQUENCE");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return dbSequence;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @return
	 */

	public String getProductPrefix() {
		return getProductPrefix(1);
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getProductPrefix(int typeOfWip)
	        throws IllegalArgumentException {

		String pPrefix = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			pPrefix = wipProp.getProperty("COMMONWIP.PRODUCT_PREFIX");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			pPrefix = wipProp.getProperty("PLANWIP.PRODUCT_PREFIX");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			pPrefix = wipProp.getProperty("INDEXWIP.PRODUCT_PREFIX");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			pPrefix = wipProp.getProperty("AUDIT.PRODUCT_PREFIX");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return pPrefix;
	}

	/**
	 * Method getProductSuffix
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getProductSuffix() throws IllegalArgumentException {

		return wipProp.getProperty("PLANWIP.PRODUCT_SUFFIX");

	}

	/**
	 * Method getOperation
	 * 
	 * @return
	 */

	public String getOperation() {
		return getOperation(1);
	}

	/**
	 * Method getOperation
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getOperation(int typeOfWip) throws IllegalArgumentException {

		String operation = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			operation = wipProp.getProperty("COMMONWIP.OPERATION");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			operation = wipProp.getProperty("PLANWIP.OPERATION");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			operation = wipProp.getProperty("INDEXWIP.OPERATION");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			operation = wipProp.getProperty("AUDIT.OPERATION");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return operation;
	}

	/**
	 * Method getOldConcatKey
	 * 
	 * @return
	 */

	public String getOldConcatKey() {
		return getOldConcatKey(1);
	}

	/**
	 * Method getOldConcatKey
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getOldConcatKey(int typeOfWip)
	        throws IllegalArgumentException {

		String oldConcatKey = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			oldConcatKey = wipProp.getProperty("COMMONWIP.OLD_CONCAT_KEY");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			oldConcatKey = wipProp.getProperty("PLANWIP.OLD_CONCAT_KEY");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			oldConcatKey = wipProp.getProperty("INDEXWIP.OLD_CONCAT_KEY");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			oldConcatKey = wipProp.getProperty("AUDIT.OLD_CONCAT_KEY");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return oldConcatKey;
	}

	public String getOldData(int typeOfWip) throws IllegalArgumentException {

		String oldData = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			oldData = wipProp.getProperty("COMMONWIP.OLD_DATA");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			oldData = wipProp.getProperty("PLANWIP.OLD_DATA");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			oldData = wipProp.getProperty("INDEXWIP.OLD_DATA");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			oldData = wipProp.getProperty("AUDIT.OLD_DATA");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return oldData;
	}

	/**
	 * Method getProjectName
	 * 
	 * @return
	 */

	public String getProjectName() {
		return getProjectName(1);
	}

	/**
	 * Method getProjectName
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getProjectName(int typeOfWip) throws IllegalArgumentException {

		String projectName = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			projectName = wipProp.getProperty("COMMONWIP.PROJECT_NAME");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			projectName = wipProp.getProperty("PLANWIP.PROJECT_NAME");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			projectName = wipProp.getProperty("INDEXWIP.PROJECT_NAME");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			projectName = wipProp.getProperty("AUDIT.PROJECT_NAME");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return projectName;
	}

	/**
	 * Method getChangeUserId
	 * 
	 * @return
	 */

	public String getChangeUserId() {
		return getChangeUserId(1);
	}

	/**
	 * Method getChangeUserId
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getChangeUserId(int typeOfWip)
	        throws IllegalArgumentException {

		String changeUserId = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			changeUserId = wipProp.getProperty("COMMONWIP.CHANGE_USER_ID");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			changeUserId = wipProp.getProperty("PLANWIP.CHANGE_USER_ID");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			changeUserId = wipProp.getProperty("INDEXWIP.CHANGE_USER_ID");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			changeUserId = wipProp.getProperty("AUDIT.CHANGE_USER_ID");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return changeUserId;
	}

	/**
	 * Method getPromoteUserId
	 * 
	 * @return
	 */

	public String getPromoteUserId() {
		return getPromoteUserId(1);
	}

	/**
	 * Method getPromoteUserId
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getPromoteUserId(int typeOfWip)
	        throws IllegalArgumentException {

		String promoteUserId = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			promoteUserId = wipProp.getProperty("COMMONWIP.PROMOTE_USER_ID");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			promoteUserId = wipProp.getProperty("PLANWIP.PROMOTE_USER_ID");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			promoteUserId = wipProp.getProperty("INDEXWIP.PROMOTE_USER_ID");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			promoteUserId = wipProp.getProperty("AUDIT.PROMOTE_USER_ID");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return promoteUserId;
	}

	/**
	 * Method getTimeStamp
	 * 
	 * @return
	 */

	public String getTimeStamp() {
		return getTimeStamp(1);
	}

	/**
	 * Method getTimeStamp
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getTimeStamp(int typeOfWip) throws IllegalArgumentException {

		String timeStamp = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			timeStamp = wipProp.getProperty("COMMONWIP.TIME_STAMP");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			timeStamp = wipProp.getProperty("PLANWIP.TIME_STAMP");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			timeStamp = wipProp.getProperty("INDEXWIP.TIME_STAMP");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			timeStamp = wipProp.getProperty("AUDIT.TIME_STAMP");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return timeStamp;
	}

	/**
	 * Method getPackageId
	 * 
	 * @return
	 */

	public String getPackageId() {
		return getPackageId(1);
	}

	/**
	 * Method getPackageId
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getPackageId(int typeOfWip) throws IllegalArgumentException {

		String packageId = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			packageId = wipProp.getProperty("COMMONWIP.PACKAGE_ID");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			packageId = wipProp.getProperty("PLANWIP.PACKAGE_ID");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			packageId = wipProp.getProperty("INDEXWIP.PACKAGE_ID");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			packageId = wipProp.getProperty("AUDIT.PACKAGE_ID");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return packageId;
	}

	/**
	 * Method getErrorIndicator
	 * 
	 * @return
	 */

	public String getErrorIndicator() {
		return getErrorIndicator(1);
	}

	/**
	 * Method getErrorIndicator
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getErrorIndicator(int typeOfWip)
	        throws IllegalArgumentException {

		String errorIndicator = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			errorIndicator = wipProp.getProperty("COMMONWIP.ERROR_INDICATOR");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			errorIndicator = wipProp.getProperty("PLANWIP.ERROR_INDICATOR");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			errorIndicator = wipProp.getProperty("INDEXWIP.ERROR_INDICATOR");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return errorIndicator;
	}

	/**
	 * Method getPromotionLock
	 * 
	 * @return
	 */

	public String getPromotionLock() {
		return getPromotionLock(1);
	}

	/**
	 * Method getPromotionLock
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getPromotionLock(int typeOfWip)
	        throws IllegalArgumentException {

		String promotionLock = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			promotionLock = wipProp.getProperty("COMMONWIP.PROMOTION_LOCK");
		} else if (typeOfWip == Constants.PLAN_WIP) {
			promotionLock = wipProp.getProperty("PLANWIP.PROMOTION_LOCK");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			promotionLock = wipProp.getProperty("INDEXWIP.PROMOTION_LOCK");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return promotionLock;
	}

	/**
	 * Method getPlanCode
	 * 
	 * @return
	 */

	public String getPlanCode() {
		return wipProp.getProperty("PLANWIP.PLAN_CODE");
	}

	/**
	 * Method getIssueState
	 * 
	 * @return
	 */

	public String getIssueState() {
		return wipProp.getProperty("PLANWIP.ISSUE_STATE");
	}

	/**
	 * Method getLOB
	 * 
	 * @return
	 */

	public String getLOB() {
		return wipProp.getProperty("PLANWIP.LINE_OF_BUSINESS");
	}

	/**
	 * Method getEffectiveDate
	 * 
	 * @return
	 */

	public String getEffectiveDate() {
		return wipProp.getProperty("PLANWIP.EFFECTIVE_DATE");
	}

	/**
	 * Method getPlanType
	 * 
	 * @return
	 */

	public String getPlanType() {
		return wipProp.getProperty("PLANWIP.PLAN_TYPE");
	}

	/**
	 * Method getTablePtrId
	 * 
	 * @return
	 */

	public String getTablePtrId() {
		return wipProp.getProperty("PLANWIP.TABLE_PTR_ID");
	}

	/**
	 * Method getTablePtrVar
	 * 
	 * @return
	 */

	public String getTablePtrVar() {
		return wipProp.getProperty("PLANWIP.TABLE_PTR_VAR");
	}

	/**
	 * Method getTablePtrSubset
	 * 
	 * @return
	 */

	public String getTablePtrSubset() {
		return wipProp.getProperty("PLANWIP.TABLE_PTR_SUBSET");
	}

	// discreet old key in lieu of oldConcatKey

	/**
	 * Method getOldCompanyCode
	 * 
	 * @return
	 */

	public String getOldCompanyCode() {
		return wipProp.getProperty("PLANWIP.OLD_COMPANY_CODE");
	}

	/**
	 * Method getOldProductPrefix
	 * 
	 * @return
	 */

	public String getOldProductPrefix() {
		return wipProp.getProperty("PLANWIP.OLD_PRODUCT_PREFIX");
	}

	/**
	 * Method getOldProductSuffix
	 * 
	 * @return
	 */

	public String getOldProductSuffix() {
		return wipProp.getProperty("PLANWIP.OLD_PRODUCT_SUFFIX");
	}

	/**
	 * Method getOldPlanCode
	 * 
	 * @return
	 */

	public String getOldPlanCode() {
		return wipProp.getProperty("PLANWIP.OLD_PLAN_CODE");
	}

	/**
	 * Method getOldIssueState
	 * 
	 * @return
	 */

	public String getOldIssueState() {
		return wipProp.getProperty("PLANWIP.OLD_ISSUE_STATE");
	}

	/**
	 * Method getOldLOB
	 * 
	 * @return
	 */

	public String getOldLOB() {
		return wipProp.getProperty("PLANWIP.OLD_LOB");
	}

	/**
	 * Method getOldEffectiveDate
	 * 
	 * @return
	 */

	public String getOldEffectiveDate() {
		return wipProp.getProperty("PLANWIP.OLD_EFFECTIVE_DATE");
	}

	/**
	 * Method getOldPlanType
	 * 
	 * @return
	 */

	public String getOldPlanType() {
		return wipProp.getProperty("PLANWIP.OLD_PLAN_TYPE");
	}

	/**
	 * Method getOldPtrId
	 * 
	 * @return
	 */

	public String getOldPtrId() {
		return wipProp.getProperty("PLANWIP.OLD_PTR_ID");
	}

	/**
	 * Method getOldPtrVar
	 * 
	 * @return
	 */

	public String getOldPtrVar() {
		return wipProp.getProperty("PLANWIP.OLD_PTR_VAR");
	}

	/**
	 * Method getOldPtrSubset
	 * 
	 * @return
	 */

	public String getOldPtrSubset() {
		return wipProp.getProperty("PLANWIP.OLD_PTR_SUBSET");
	}

	// end

	/**
	 * Method getDDLName
	 * 
	 * @return
	 */

	public String getDDLName() {
		return getDDLName(1);
	}

	/**
	 * Method getDDLName
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getDDLName(int typeOfWip) throws IllegalArgumentException {

		String ddlName = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			ddlName = wipProp.getProperty("COMMONWIP.DDLNAME");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			ddlName = wipProp.getProperty("AUDIT.DDLNAME");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return ddlName;
	}

	/**
	 * Method getTableId
	 * 
	 * @return
	 */

	public String getTableId() {
		return getTableId(1);
	}

	/**
	 * Method getTableId
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getTableId(int typeOfWip) throws IllegalArgumentException {

		String tableId = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			tableId = wipProp.getProperty("COMMONWIP.TABLE_ID");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			tableId = wipProp.getProperty("AUDIT.TABLE_ID");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return tableId;
	}

	/**
	 * Method getSubsetName
	 * 
	 * @return
	 */

	public String getSubsetName() {
		return getSubsetName(1);
	}

	/**
	 * Method getSubsetName
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getSubsetName(int typeOfWip) throws IllegalArgumentException {

		String subsetName = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			subsetName = wipProp.getProperty("COMMONWIP.SUBSET_NAME");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			subsetName = wipProp.getProperty("AUDIT.SUBSET_NAME");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return subsetName;
	}

	/**
	 * Method getNewConcatKey
	 * 
	 * @return
	 */

	public String getNewConcatKey() {
		return getNewConcatKey(1);
	}

	/**
	 * Method getNewConcatKey
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getNewConcatKey(int typeOfWip)
	        throws IllegalArgumentException {

		String newConcatKey = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			newConcatKey = wipProp.getProperty("COMMONWIP.NEW_CONCAT_KEY");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			newConcatKey = wipProp.getProperty("AUDIT.NEW_CONCAT_KEY");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return newConcatKey;
	}

	/**
	 * Method getNewData
	 * 
	 * @return
	 */

	public String getNewData() {
		return getNewData(1);
	}

	/**
	 * Method getNewData
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getNewData(int typeOfWip) throws IllegalArgumentException {

		String newData = null;

		if (typeOfWip == Constants.COMMON_WIP) {
			newData = wipProp.getProperty("COMMONWIP.NEW_DATA");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			newData = wipProp.getProperty("AUDIT.NEW_DATA");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return newData;
	}

	/**
	 * Method getPrimaryTableId
	 * 
	 * @return
	 */

	public String getPrimaryTableId() {
		return wipProp.getProperty("INDEXWIP.PRIMARY_TABLE_ID");
	}

	/**
	 * Method getPrimaryPtrSubset
	 * 
	 * @return
	 */

	public String getPrimaryPtrSubset() {
		return wipProp.getProperty("INDEXWIP.PRIMARY_PTR_SUBSET");
	}

	/**
	 * Method getSecondaryTableId
	 * 
	 * @return
	 */

	public String getSecondaryTableId() {
		return wipProp.getProperty("INDEXWIP.SECONDARY_TABLE_ID");
	}

	/**
	 * Method getSecondaryPtrSubset
	 * 
	 * @return
	 */

	public String getSecondaryPtrSubset() {
		return wipProp.getProperty("INDEXWIP.SECONDARY_PTR_SUBSET");
	}

	/**
	 * Method getSecondaryTableVar
	 * 
	 * @return
	 */

	public String getSecondaryTableVar() {
		return wipProp.getProperty("INDEXWIP.SECONDARY_TABLE_VAR");
	}

	/**
	 * Method getOldData
	 * 
	 * @return
	 */

	public String getOldData() {
		return wipProp.getProperty("AUDIT.OLD_DATA");
	}

	public String getOldWIPData() {
		return wipProp.getProperty("COMMONWIP.OLD_DATA");
	}

	/**
	 * Method getSchema
	 * 
	 * @param typeOfWip
	 * @return
	 * @throws IllegalArgumentException
	 */

	public String getSchema(int typeOfWip) throws IllegalArgumentException {

		String wipSchema = null;

		if (typeOfWip == Constants.PLAN_WIP) {
			wipSchema = wipProp.getProperty("PLANWIP.SCHEMA");
		} else if (typeOfWip == Constants.INDEX_WIP) {
			wipSchema = wipProp.getProperty("INDEXWIP.SCHEMA");
		} else if (typeOfWip == Constants.COMMON_WIP) {
			wipSchema = wipProp.getProperty("COMMONWIP.SCHEMA");
		} else if (typeOfWip == Constants.AUDIT_LOG) {
			wipSchema = wipProp.getProperty("AUDIT.SCHEMA");
		} else {
			throwIllegalArgumentException(typeOfWip);
		}
		return wipSchema;
	}

	private void throwIllegalArgumentException(int typeOfWip) {
		throw new IllegalArgumentException("Invalid Argument : " + typeOfWip);
	}

	/**
	 * Method getEBCDICKey.
	 * 
	 * @return String
	 */
	public String getEBCDICKey() {

		return wipProp.getProperty("COMMONWIP.EBCDIC_KEY");
	}

}
