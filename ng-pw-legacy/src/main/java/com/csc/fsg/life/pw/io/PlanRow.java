/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

import com.csc.fsg.life.pw.util.Constants;

import java.io.Serializable;
import java.util.Comparator;

/* Modifications: T0129 */

/**
 * Class PlanRow
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class PlanRow extends Row implements Comparator, Comparable, Serializable {

	private String _planKey;

	private String _planCode;

	private String _productPrefix;

	private String _productSuffix;

	private String _issueState;

	private String _lineOfBusiness;

	private String _effectiveDate;

	private String _tablePtrId;

	private String _tablePtrVar;

	private String _tablePtrSubset;

	private String _planType;

	/**
	 * Constructor PlanRow
	 */

	public PlanRow() {
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

	public String toString() {
		return getPlanKey(Constants.SPIPE);
	}

	/**
	 * Method compare
	 * 
	 * @param object1
	 * @param object2
	 * @return
	 */

	public int compare(Object object1, Object object2) {

		PlanRow planRow1 = (PlanRow) object1;
		PlanRow planRow2 = (PlanRow) object2;

		return planRow1.getPlanKey(Constants.SPIPE).compareTo(
		        planRow2.getPlanKey(Constants.SPIPE));
	}

	/**
	 * Method compareTo
	 * 
	 * @param object
	 * @return
	 */

	public int compareTo(Object object) {

		PlanRow planRow = (PlanRow) object;

		return getPlanKey(Constants.SPIPE).compareTo(
		        planRow.getPlanKey(Constants.SPIPE));
	}

	public boolean equals(Object obj) {
		if ( !(obj instanceof PlanRow) )
			return false;
		PlanRow planRow = (PlanRow) obj;
		return getPlanKey(Constants.SPIPE).equals(
		        planRow.getPlanKey(Constants.SPIPE));
	}
	
	public int hashCode() {
		return getPlanKey(Constants.SPIPE).hashCode();
	}
	
	/**
	 * Method getEffectiveDate
	 * 
	 * @return
	 */

	public String getEffectiveDate() {
		return _effectiveDate;
	}

	/**
	 * Method getIssueState
	 * 
	 * @return
	 */

	public String getIssueState() {
		return _issueState;
	}

	/**
	 * Method getLineOfBusiness
	 * 
	 * @return
	 */

	public String getLineOfBusiness() {
		return _lineOfBusiness;
	}

	/**
	 * Method getPlanCode
	 * 
	 * @return
	 */

	public String getPlanCode() {
		return _planCode;
	}

	/**
	 * Method getPlanType
	 * 
	 * @return
	 */

	public String getPlanType() {
		return _planType;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @return
	 */

	public String getProductPrefix() {
		return _productPrefix;
	}

	/**
	 * Method getProductSuffix
	 * 
	 * @return
	 */

	public String getProductSuffix() {
		return _productSuffix;
	}

	/**
	 * Method getTablePtrId
	 * 
	 * @return
	 */

	public String getTablePtrId() {
		return _tablePtrId;
	}

	/**
	 * Method getTablePtrSubset
	 * 
	 * @return
	 */

	public String getTablePtrSubset() {
		return _tablePtrSubset;
	}

	/**
	 * Method getTablePtrVar
	 * 
	 * @return
	 */

	public String getTablePtrVar() {
		return _tablePtrVar;
	}

	/**
	 * Method setEffectiveDate
	 * 
	 * @param s
	 */

	public void setEffectiveDate(String s) {
		_effectiveDate = s;
	}

	/**
	 * Method setIssueState
	 * 
	 * @param s
	 */

	public void setIssueState(String s) {
		_issueState = s;
	}

	/**
	 * Method setLineOfBusiness
	 * 
	 * @param s
	 */

	public void setLineOfBusiness(String s) {
		_lineOfBusiness = s;
	}

	/**
	 * Method setPlanCode
	 * 
	 * @param s
	 */

	public void setPlanCode(String s) {
		_planCode = s;
	}

	/**
	 * Method setPlanKey
	 * 
	 * @param s
	 */

	public void setPlanKey(String s) {
		_planKey = s;
	}

	/**
	 * Method setPlanType
	 * 
	 * @param s
	 */

	public void setPlanType(String s) {
		_planType = s;
	}

	/**
	 * Method setProductPrefix
	 * 
	 * @param s
	 */

	public void setProductPrefix(String s) {
		_productPrefix = s;
	}

	/**
	 * Method setProductSuffix
	 * 
	 * @param s
	 */

	public void setProductSuffix(String s) {
		_productSuffix = s;
	}

	/**
	 * Method setTablePtrVar
	 * 
	 * @param s
	 */

	public void setTablePtrVar(String s) {
		_tablePtrVar = s;
	}

	/**
	 * Method setTablePtrSubset
	 * 
	 * @param s
	 */

	public void setTablePtrSubset(String s) {
		_tablePtrSubset = s;
	}

	/**
	 * Method setTablePtrId
	 * 
	 * @param s
	 */

	public void setTablePtrId(String s) {

		if (s != null) {
			_tablePtrId = padZeros(s);
		} else {
			_tablePtrId = null;
		}
	}

	/**
	 * Method padZeros
	 * 
	 * @param s
	 * @return
	 */

	public String padZeros(String s) {

		String pad = null;
		String pz = s.trim();
		int len = pz.length();

		if (len == 1) {
			pad = "00";
		} else if (len == 2) {
			pad = "0";
		}
		if (pad != null) {
			StringBuffer sb = new StringBuffer();
			sb.append(pad);
			sb.append(pz);
			pz = sb.toString();
		}
		return pz;
	}

	/**
	 * Method getClonedPlanRow
	 * 
	 * @return
	 */

	public PlanRow getClonedPlanRow() {

		PlanRow row = new PlanRow();

		row._effectiveDate = _effectiveDate;
		row._issueState = _issueState;
		row._lineOfBusiness = _lineOfBusiness;
		row._planCode = _planCode;
		row._planKey = getPlanKey(Constants.SPIPE);
		row._planType = _planType;
		row._productPrefix = _productPrefix;
		row._productSuffix = _productSuffix;
		row.setTablePtrId(_tablePtrId);
		row._tablePtrSubset = _tablePtrSubset;
		row._tablePtrVar = _tablePtrVar;
		row._companyCode = super._companyCode;
		row._tableDescriptor = super._tableDescriptor;
		row._key = super._key;
		row._oldConcatKey = super._oldConcatKey;
		return row;
	}

	/**
	 * Method getConcatKey
	 * 
	 * @return
	 */

	public String getConcatKey() {

		StringBuffer sb = new StringBuffer();
		sb.append(super._companyCode).append(Constants.SPIPE);
		sb.append(_productPrefix).append(Constants.SPIPE);
		sb.append(_productSuffix).append(Constants.SPIPE);
		sb.append(_planCode).append(Constants.SPIPE);
		sb.append(_issueState).append(Constants.SPIPE);
		sb.append(_lineOfBusiness).append(Constants.SPIPE);
		sb.append(_effectiveDate).append(Constants.SPIPE);
		sb.append(_planType).append(Constants.SPIPE);
		sb.append(_tablePtrId).append(Constants.SPIPE);
		sb.append(_tablePtrVar).append(Constants.SPIPE);
		sb.append(_tablePtrSubset);
		return sb.toString();
	}

	/**
	 * @deprecated use getPlanKey( String delimiter ) by passing Constants.SPIPE
	 *             as an argument
	 * @return
	 */

	public String getConcatPlanKey() {

		StringBuffer sb = new StringBuffer();
		sb.append(super._companyCode).append(Constants.SPIPE);
		sb.append(_productPrefix).append(Constants.SPIPE);
		sb.append(_productSuffix).append(Constants.SPIPE);
		sb.append(_planCode).append(Constants.SPIPE);
		sb.append(_issueState).append(Constants.SPIPE);
		sb.append(_lineOfBusiness).append(Constants.SPIPE);
		sb.append(_effectiveDate).append(Constants.SPIPE);
		sb.append(_planType);
		return sb.toString();
	}

	/**
	 * Method getPlanKey
	 * 
	 * @param delimiter
	 * @return
	 */

	public String getPlanKey(String delimiter) {

		if (_planKey == null) {
			StringBuffer sb = new StringBuffer();
			sb.append(super._companyCode).append(delimiter);
			sb.append(_productPrefix).append(delimiter);
			sb.append(_productSuffix).append(delimiter);
			sb.append(_planCode).append(delimiter);
			sb.append(_issueState).append(delimiter);
			sb.append(_lineOfBusiness).append(delimiter);
			sb.append(_effectiveDate).append(delimiter);
			sb.append(_planType);
			_planKey = sb.toString();
		}
		return _planKey;
	}

	/**
	 * Method getPlanKey
	 * 
	 * @return
	 */

	// public String getPlanKey() {
	// return getPlanKey(delim);
	// }
	public String buildData() throws Exception {
		return buildKey();
	}
}
