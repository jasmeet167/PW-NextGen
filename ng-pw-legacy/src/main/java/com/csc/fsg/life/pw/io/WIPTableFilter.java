/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import java.util.Vector;

import com.csc.fsg.life.pw.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.util.Constants;

/* Modifications: ENH874, T0103, T0091, ENH1006 */

/**
 * Class WIPTableFilter
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class WIPTableFilter {

	private String  envId = null;

	private String companyCode = null;

	private Vector<String> tableIds = null;

	private Vector ddlNames = null;

	private String subsetName = null;

	private Vector<String> projectNames = null;

	private Vector changeUserIds = null;

	private Vector packageIds = null;

	private String productPrefix = null;

	private String productSuffix = null;

	private Vector operations = null;

	private Vector dbSequence = null;

	private boolean withErrors = false;

	private boolean locked = false;

	// Plan Table and Index Table filter items (T000X, T000XA)
	private String parentTableId = null;

	private String parentTableSubset = null;

	private String childTableId = null;

	private String childTableSubset = null;

	private String childTableVar = null;

	private String planCode = null;

	private String issueState = null;

	private String lob = null;

	private String effDate;

	private String tablePtrId = null;

	private String tablePtrSubset = null;

	private String tablePtrVar = null;

	private String planType = null;

	// Paging & Search filter items
	private String keyForPaging;

	private String pagingMode;

	private String ebcdicKey;

	private String locator;

	private String view;

	private boolean initTableOpen;

	private String oldConcatKey;

	private int pagingSize = -1;

	private String userCondition = null;

	/**
	 * Constructor WIPTableFilter
	 */

	public WIPTableFilter() {}

	public WIPTableFilter(PlanCriteriaTO planCriteria) {
		this();
		setData(planCriteria);
	}
	
	public PlanCriteriaTO getData() {
		PlanCriteriaTO planCriteria = new PlanCriteriaTO();
		planCriteria.setEnvironment(getEnvironment());
		planCriteria.setCompanyCode(getCompanyCode());
		planCriteria.setProductPrefix(getProductPrefix());
		planCriteria.setProductSuffix(getProductSuffix());
		planCriteria.setPlanCode(getPlanCode());
		planCriteria.setIssueState(getIssueState());
		planCriteria.setLineOfBusiness(getLob());
		planCriteria.setEffectiveDate(getEffDate());
		planCriteria.setPlanType(getPlanType());
		String tableId = "";
		if (getTableIds() != null) {
			tableId = getTableIds().firstElement();
		}
		if (tableId.equals(Constants.TABLE_ZERO_ID)) {
			planCriteria.setTablePtrId(getTablePtrId());
			planCriteria.setTablePtrVar(getTablePtrVar());
			planCriteria.setTablePtrSubset(getTablePtrSubset());
		} else if (!tableId.equals("")) {
			planCriteria.setTablePtrId(tableId);
			planCriteria.setTablePtrSubset(getSubsetName());
		}
		if (getProjectNames() != null && !getProjectNames().isEmpty()) {
			planCriteria.setProjectName(getProjectNames().firstElement());				
		}
		return planCriteria;		
	}

	public void setData(PlanCriteriaTO planCriteria) {
		setEnvironment(planCriteria.getEnvironment());
		setCompanyCode(planCriteria.getCompanyCode());
		setProductPrefix(planCriteria.getProductPrefix());
		setProductSuffix(planCriteria.getProductSuffix());
		setPlanCode(planCriteria.getPlanCode());
		setIssueState(planCriteria.getIssueState());
		setLob(planCriteria.getLineOfBusiness());
		setEffDate(planCriteria.getEffectiveDate());
		setPlanType(planCriteria.getPlanType());
		setTablePtrId(planCriteria.getTablePtrId());
		setTablePtrSubset(planCriteria.getTablePtrSubset());
		setTablePtrVar(planCriteria.getTablePtrVar());
		if (planCriteria.getProjectName() != null) {
			Vector<String> projects = new Vector<String>();
			projects.addElement(planCriteria.getProjectName());
			setProjectNames(projects);
		}
	}

	/**
	 * Method getChangeUserIds
	 * 
	 * @return
	 */

	public Vector getChangeUserIds() {
		return changeUserIds;
	}

	/**
	 * Method getCompanyCode
	 * 
	 * @return
	 */

	public String getCompanyCode() {
		return companyCode;
	}

	/**
	 * Method getDbSequence
	 * 
	 * @return
	 */

	public Vector getDbSequence() {
		return dbSequence;
	}

	/**
	 * Method getEnvironment
	 * 
	 * @return
	 */

	public String getEnvironment() {
		return envId;
	}

	/**
	 * Method getPackageIds
	 * 
	 * @return
	 */

	public Vector getPackageIds() {
		return packageIds;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @return
	 */

	public String getProductPrefix() {
		return productPrefix;
	}

	/**
	 * Method getProductSuffix
	 * 
	 * @return
	 */

	public String getProductSuffix() {
		return productSuffix;
	}

	/**
	 * Method getProjectNames
	 * 
	 * @return
	 */

	public Vector<String> getProjectNames() {
		return projectNames;
	}

	/**
	 * Method getSubsetName
	 * 
	 * @return
	 */

	public String getSubsetName() {
		return subsetName;
	}

	/**
	 * Method getTableIds
	 * 
	 * @return
	 */

	public Vector<String> getTableIds() {
		return tableIds;
	}

	/**
	 * Method getDdlNames
	 * 
	 * @return
	 */

	public Vector getDdlNames() {
		return ddlNames;
	}

	/**
	 * Method getOperations
	 * 
	 * @return
	 */

	public Vector getOperations() {
		return operations;
	}

	/**
	 * Method isLocked
	 * 
	 * @return
	 */

	public boolean isLocked() {
		return locked;
	}

	/**
	 * Method isWithErrors
	 * 
	 * @return
	 */

	public boolean isWithErrors() {
		return withErrors;
	}

	/**
	 * Method getParentTableId
	 * 
	 * @return
	 */

	public String getParentTableId() {
		return parentTableId;
	}

	/**
	 * Method getParentTableSubset
	 * 
	 * @return
	 */

	public String getParentTableSubset() {
		return parentTableSubset;
	}

	/**
	 * Method getChildTableId
	 * 
	 * @return
	 */

	public String getChildTableId() {
		return childTableId;
	}

	/**
	 * Method getChildTableSubset
	 * 
	 * @return
	 */

	public String getChildTableSubset() {
		return childTableSubset;
	}

	/**
	 * Method getChildTableVar
	 * 
	 * @return
	 */

	public String getChildTableVar() {
		return childTableVar;
	}

	/**
	 * Method getPlanCode
	 * 
	 * @return
	 */

	public String getPlanCode() {
		return planCode;
	}

	/**
	 * Method getIssueState
	 * 
	 * @return
	 */

	public String getIssueState() {
		return issueState;
	}

	/**
	 * Method getLob
	 * 
	 * @return
	 */

	public String getLob() {
		return lob;
	}

	/**
	 * Method getEffDate
	 * 
	 * @return
	 */

	public String getEffDate() {
		return effDate;
	}

	/**
	 * Method getTablePtrId
	 * 
	 * @return
	 */

	public String getTablePtrId() {
		return tablePtrId;
	}

	/**
	 * Method getTablePtrSubset
	 * 
	 * @return
	 */

	public String getTablePtrSubset() {
		return tablePtrSubset;
	}

	/**
	 * Method getTablePtrVar
	 * 
	 * @return
	 */

	public String getTablePtrVar() {
		return tablePtrVar;
	}

	/**
	 * Method getPlanType
	 * 
	 * @return
	 */

	public String getPlanType() {
		return planType;
	}

	/**
	 * Method setChangeUserIds
	 * 
	 * @param changeUserIds
	 */

	public void setChangeUserIds(Vector changeUserIds) {
		this.changeUserIds = changeUserIds;
	}

	/**
	 * Method setCompanyCode
	 * 
	 * @param companyCode
	 */

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * Method setDbSequence
	 * 
	 * @param dbSequence
	 */

	public void setDbSequence(Vector dbSequence) {
		this.dbSequence = dbSequence;
	}

	/**
	 * Method setEnvironment
	 * 
	 * @param environment
	 */

	public void setEnvironment(String envId) {
		this.envId = envId;
	}

	/**
	 * Method setLocked
	 * 
	 * @param newLocked
	 */

	public void setLocked(boolean newLocked) {
		locked = newLocked;
	}

	/**
	 * Method setPackageIds
	 * 
	 * @param packageIds
	 */

	public void setPackageIds(Vector packageIds) {
		this.packageIds = packageIds;
	}

	/**
	 * Method setProductPrefix
	 * 
	 * @param productPrefix
	 */

	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}

	/**
	 * Method setProductSuffix
	 * 
	 * @param productSuffix
	 */

	public void setProductSuffix(String productSuffix) {
		this.productSuffix = productSuffix;
	}

	/**
	 * Method setProjectNames
	 * 
	 * @param projectNames
	 */

	public void setProjectNames(Vector<String> projectNames) {
		this.projectNames = projectNames;
	}

	/**
	 * Method setOperations
	 * 
	 * @param operations
	 */

	public void setOperations(Vector operations) {
		this.operations = operations;
	}

	/**
	 * Method setSubsetName
	 * 
	 * @param subsetName
	 */

	public void setSubsetName(String subsetName) {
		this.subsetName = subsetName;
	}

	/**
	 * Method setTableIds
	 * 
	 * @param tableIds
	 */

	public void setTableIds(Vector<String> tableIds) {
		this.tableIds = tableIds;
	}

	/**
	 * Method setDdlNames
	 * 
	 * @param ddlNames
	 */

	public void setDdlNames(Vector<String> ddlNames) {
		this.ddlNames = ddlNames;
	}

	/**
	 * Method setWithErrors
	 * 
	 * @param newWithErrors
	 */

	public void setWithErrors(boolean newWithErrors) {
		withErrors = newWithErrors;
	}

	/**
	 * Method setParentTableId
	 * 
	 * @param newParentTableId
	 */

	public void setParentTableId(String newParentTableId) {
		parentTableId = newParentTableId;
	}

	/**
	 * Method setParentTableSubset
	 * 
	 * @param newParentTableSubset
	 */

	public void setParentTableSubset(String newParentTableSubset) {
		parentTableSubset = newParentTableSubset;
	}

	/**
	 * Method setChildTableId
	 * 
	 * @param newChildTableId
	 */

	public void setChildTableId(String newChildTableId) {
		childTableId = newChildTableId;
	}

	/**
	 * Method setChildTableSubset
	 * 
	 * @param newChildTableSubset
	 */

	public void setChildTableSubset(String newChildTableSubset) {
		childTableSubset = newChildTableSubset;
	}

	/**
	 * Method setChildTableVar
	 * 
	 * @param newChildTableVar
	 */

	public void setChildTableVar(String newChildTableVar) {
		childTableVar = newChildTableVar;
	}

	/**
	 * Method setPlanCode
	 * 
	 * @param newPlanCode
	 */

	public void setPlanCode(String newPlanCode) {
		planCode = newPlanCode;
	}

	/**
	 * Method setIssueState
	 * 
	 * @param newIssueState
	 */

	public void setIssueState(String newIssueState) {
		issueState = newIssueState;
	}

	/**
	 * Method setLob
	 * 
	 * @param newLob
	 */

	public void setLob(String newLob) {
		lob = newLob;
	}

	/**
	 * Method setEffDate
	 * 
	 * @param newEffDate
	 */

	public void setEffDate(String newEffDate) {
		effDate = newEffDate;
	}

	/**
	 * Method setTablePtrId
	 * 
	 * @param newTablePtrId
	 */

	public void setTablePtrId(String newTablePtrId) {
		tablePtrId = newTablePtrId;
	}

	/**
	 * Method setTablePtrSubset
	 * 
	 * @param newTablePtrSubset
	 */

	public void setTablePtrSubset(String newTablePtrSubset) {
		tablePtrSubset = newTablePtrSubset;
	}

	/**
	 * Method setTablePtrVar
	 * 
	 * @param newTablePtrVar
	 */

	public void setTablePtrVar(String newTablePtrVar) {
		tablePtrVar = newTablePtrVar;
	}

	/**
	 * Method setPlanType
	 * 
	 * @param newPlanType
	 */

	public void setPlanType(String newPlanType) {
		planType = newPlanType;
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

	public String toString() {

		StringBuffer sb = new StringBuffer();

		sb.append("ENVIRONEMT: ");
		if (envId != null) {
			sb.append(envId).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("COMPANY CODE: ");
		if (companyCode != null) {
			sb.append(companyCode).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("TABLE IDS: ");
		if (tableIds != null) {
			sb.append(tableIds.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("SUBSET NAME: ");
		if (subsetName != null) {
			sb.append(subsetName).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("PROJECT NAMES: ");
		if (projectNames != null) {
			sb.append(projectNames.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("CHANGE USERIDS: ");
		if (changeUserIds != null) {
			sb.append(changeUserIds.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("PACKAGE IDS: ");
		if (packageIds != null) {
			sb.append(packageIds.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("PRODUCT PREFIX: ");
		if (productPrefix != null) {
			sb.append(productPrefix).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		sb.append("PRODUCT SUFFIX: ");
		if (productSuffix != null) {
			sb.append(productSuffix);
		} else {
			sb.append("NULL");
		}

		sb.append("DB SEQUENCE: ");
		if (dbSequence != null) {
			sb.append(dbSequence.toString()).append("\t");
		} else {
			sb.append("NULL").append("\t");
		}

		return sb.toString();
	}

	/**
	 * Returns the keyForPaging.
	 * 
	 * @return String
	 */
	public String getKeyForPaging() {
		return keyForPaging;
	}

	/**
	 * Returns the pagingMode.
	 * 
	 * @return String
	 */
	public String getPagingMode() {
		return pagingMode;
	}

	/**
	 * Sets the keyForPaging.
	 * 
	 * @param keyForPaging
	 *            The keyForPaging to set
	 */
	public void setKeyForPaging(String keyForPaging) {
		this.keyForPaging = keyForPaging;
	}

	/**
	 * Sets the pagingMode.
	 * 
	 * @param pagingMode
	 *            The pagingMode to set
	 */
	public void setPagingMode(String pagingMode) {
		this.pagingMode = pagingMode;
	}

	public void setEbcdicKey(String ebcdicKey) {
		this.ebcdicKey = ebcdicKey;
	}

	public String getEbcdicKey() {
		return ebcdicKey;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	public String getLocator() {
		return locator;
	}

	public boolean hasPagingModeSet() {
		if (pagingMode == null)
			return false;
		return true;
	}

	public boolean isPagingNext() {
		if (pagingMode != null && pagingMode.equalsIgnoreCase("next"))
			return true;
		else
			return false;
	}

	// ENH874 - new method to support audit paging
	public boolean isPagingPrevious() {
		return (pagingMode != null && pagingMode.equalsIgnoreCase("previous"));
	}

	public void setInitTableOpen(boolean initTableOpen) {
		this.initTableOpen = initTableOpen;
	}

	public boolean isInitTableOpen() {
		return initTableOpen;
	}

	/**
	 * Returns the view.
	 * 
	 * @return String
	 */
	public String getView() {
		return view;
	}

	/**
	 * Sets the view.
	 * 
	 * @param view
	 *            The view to set
	 */
	public void setView(String view) {
		this.view = view;
	}

	/**
	 * @return
	 */
	public int getPagingSize() {
		return pagingSize;
	}

	/**
	 * @param i
	 */
	public void setPagingSize(int i) {
		pagingSize = i;
	}

	/**
	 * @return
	 */
	public String getOldConcatKey() {
		return oldConcatKey;
	}

	/**
	 * @param string
	 */
	public void setOldConcatKey(String ock) {
		oldConcatKey = ock;
	}
	
	public String getUserCondition() {
		return userCondition;
	}
	
	public void setUserCondition(String userCondition) {
		this.userCondition = userCondition;
	}
	
	public boolean hasUserCondition() {
		return ( userCondition != null && userCondition.length() > 0 );
	}
}
