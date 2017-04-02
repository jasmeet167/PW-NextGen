/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;

/* Modifications: ENH874, T0103, T0091, ENH1006, T0129 */
/**
 * This class constructs WIPRow objects of various types. In the future it may
 * be used to hide object pooling. Creation date: (10/12/2001 4:59:53 PM)
 * 
 * @author: Carl Ericksen
 */

public final class WIPRowFactory {

	/**
	 * WIPRowFactory constructor comment.
	 */

	private WIPRowFactory() {
		super();
	}

	/**
	 * This method iterates through the passed result set, creates and populates
	 * WIPRow objects of the appropriate type, and adds them to the passed
	 * collection. Creation date: (10/12/2001 5:02:57 PM)
	 * 
	 * @param rs
	 * @param iWipRows
	 * @param wipType
	 * @throws Exception
	 * @throws WIPRowException
	 */

	public static void createWIPRow(ResultSet rs, IWIPRows iWipRows, int wipType)
	        throws WIPRowException, Exception {

//		// ResultSetMetaData rsmd = rs.getMetaData();
//		WIPProperties wipProp = WIPProperties.getInstance();
//		WIPRows rows = (WIPRows) iWipRows;
//		WIPTableFilter filter = rows.getRequestFilter();
//		SqlExpressionEvaluator ee = null;
//		if ( filter.hasUserCondition() )
//			ee = new SqlExpressionEvaluator(filter.getUserCondition());
//
//		if (wipType == Constants.PLAN_WIP) {
//			while (rs.next()) {
//				PlanWIPRow pWipRow = createPlanRow(rs);
//				
//				if ( filter.hasUserCondition() ) {
//					if ( !wipRowMatchesFilter(ee, filter, pWipRow) )
//						continue;
//				}
//
//				iWipRows.add(pWipRow);
//			}
//		} else if (wipType == Constants.INDEX_WIP) {
//			while (rs.next()) {
//				IndexWIPRow iWipRow = createIndexRow(rs, wipProp);
//				
//				if ( filter.hasUserCondition() ) {
//					if ( !wipRowMatchesFilter(ee, filter, iWipRow) )
//						continue;
//				}
//
//				iWipRows.add(iWipRow);
//			}
//		} else if (wipType == Constants.AUDIT_LOG) {
//			// ENH874 - support audit paging
//			if (filter.getPagingSize() == -1) {
//				while (rs.next()) {
//					AuditRow aRow = createAuditRow(iWipRows, rs, wipProp);
//					rows.add(aRow);
//				}
//			} else {
//				pageAuditRows(rs, rows, wipProp);
//			}
//		} else {
//			int pagingSize = filter.getPagingSize();
//
//			if (pagingSize == -1) {
//				while (rs.next()) {
//					CommonWIPRow cWipRow = createCommonWIPRow(rs, wipProp);
//
//					if ( filter.hasUserCondition() ) {
//						if ( !wipRowMatchesFilter(ee, filter, cWipRow) )
//							continue;
//					}
//
//					rows.add(cWipRow);
//				}
//			} else {
//				pageWIPRows(rs, rows, wipProp);
//			}
//
//		}
	}

	private static void pageWIPRows(ResultSet rs, WIPRows rows,
	        WIPProperties wipProp) throws Exception {
		CommonWIPRow cWipRow = null;
		WIPTableFilter filter = rows.getRequestFilter();
		int pagingSize = filter.getPagingSize();

		ArrayList<CommonWIPRow> prevRows = new ArrayList<CommonWIPRow>();
		boolean isPageModeSet = filter.hasPagingModeSet();
		boolean isNext = false;

		if (isPageModeSet)
			isNext = filter.isPagingNext();

		SqlExpressionEvaluator ee = null;
		if ( filter.hasUserCondition() )
			ee = new SqlExpressionEvaluator(filter.getUserCondition());

		while (rs.next()) {
			cWipRow = createCommonWIPRow(rs, wipProp);

			if ( filter.hasUserCondition() ) {
				if ( !wipRowMatchesFilter(ee, filter, cWipRow) )
					continue;
			}
			
			if (filter.isInitTableOpen() || isNext) {
				if (rows.size() < pagingSize)
					rows.add(cWipRow);
				else {
					rows.setNextInd(true);
					break;
				}
			} else if (!isNext) {
				if (prevRows.size() < pagingSize)
					prevRows.add(cWipRow);
				else {
					rows.setPrevInd(true);
					break;
				}
			}
		}

		if (isPageModeSet && !isNext) {
			for (int j = (prevRows.size()) - 1; j >= 0; j--)
				rows.add(prevRows.get(j));
		}

	}

	private static boolean wipRowMatchesFilter(SqlExpressionEvaluator ee,
			WIPTableFilter wipFilter, WIPRow wipRow) throws Exception {
		String tableId = wipFilter.getTableIds().get(0);
		//String tableId = wipFilter.getData().getTablePtrId();
		Environment environment = EnvironmentManager.getInstance().getEnvironment(wipFilter.getEnvironment());
		TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(tableId);
		if (td == null) {
			throw new Exception("Table Descriptor not found for table " + tableId);
		}
		
		RowManager rowManager = new RowManager(environment.getId());
		
		Row row = rowManager.getBlankRow(tableId);
		row.setColumns(wipRow.getNewConcatKey()+wipRow.getNewData(), Constants.STAB);
		return ee.eval(td, row);
	}

	// ENH874 - new method to support audit paging
	private static void pageAuditRows(ResultSet rs, WIPRows rows,
	        WIPProperties wipProp) throws Exception {
//		AuditRow aRow = null;
//		AuditTableFilter filter = (AuditTableFilter) rows.getRequestFilter();
//		int pagingSize = filter.getPagingSize();
//
//		ArrayList<AuditRow> prevRows = new ArrayList<AuditRow>();
//		boolean isPageModeSet = filter.hasPagingModeSet();
//		boolean isNext = false;
//
//		if (isPageModeSet)
//			isNext = filter.isPagingNext();
//
//		if ( isNext ) {
//			rows.setPrevInd(true);
//			filter.setPageNumber(filter.getPageNumber()+1);
//		}
//		if ( filter.isPagingPrevious() ) {
//			rows.setNextInd(true);
//			filter.setPageNumber(filter.getPageNumber()-1);
//		}
//
//		while (rs.next()) {
//			aRow = createAuditRow(rows, rs, wipProp);
//
//			if (filter.isInitTableOpen() || isNext) {
//				if (rows.size() < pagingSize)
//					rows.add(aRow);
//				else {
//					rows.setNextInd(true);
//					break;
//				}
//			} else if (!isNext) {
//				if (prevRows.size() < pagingSize)
//					prevRows.add(aRow);
//				else {
//					rows.setPrevInd(true);
//					break;
//				}
//			}
//		}
//
//		if (isPageModeSet && !isNext) {
//			for (int j = (prevRows.size()) - 1; j >= 0; j--)
//				rows.add(prevRows.get(j));
//		}

	}

	/**
	 * This method creates a single WIPRow from the next row in the result set.
	 * Created to support new apply method -rts27Oct04.
	 */

	public static WIPRow createWIPRow(ResultSet rs, int wipType)
	        throws WIPRowException, Exception {

		WIPProperties wipProp = WIPProperties.getInstance();

		if (wipType == Constants.PLAN_WIP)
			return createPlanRow(rs);

		else if (wipType == Constants.INDEX_WIP)
			return createIndexRow(rs, wipProp);

		else
			return createCommonWIPRow(rs, wipProp);
	}

	/**
	 * This method creates a CommonWIPRow from a valid ResultSet element
	 * 
	 * @param rs
	 * @param wipProp
	 * @return
	 * @throws java.lang.Exception
	 * @throws java.sql.SQLException
	 */

	public static CommonWIPRow createCommonWIPRow(ResultSet rs,
	        WIPProperties wipProp) throws java.sql.SQLException,
	        java.lang.Exception {

		CommonWIPRow cWipRow = new CommonWIPRow();

		cWipRow.setDbSequence(rs.getLong(wipProp.getDBSequence()));
		cWipRow.setEnvironment(rs.getString(wipProp.getEnvironment()));
		cWipRow.setDdlName(rs.getString(wipProp.getDDLName()));
		cWipRow.setTableId(rs.getString(wipProp.getTableId()));
		cWipRow.setCompanyCode(rs.getString(wipProp.getCompanyCode()));
		cWipRow.setProductPrefix(rs.getString(wipProp.getProductPrefix()));
		cWipRow.setSubsetName(rs.getString(wipProp.getSubsetName()));
		cWipRow.setNewConcatKey(rs.getString(wipProp.getNewConcatKey()));
		cWipRow.setNewData(rs.getString(wipProp.getNewData()));
		cWipRow.setOperation(rs.getString(wipProp.getOperation()));
		cWipRow.setOldConcatKey(rs.getString(wipProp.getOldConcatKey()));
		cWipRow.setOldData(rs.getString(wipProp.getOldWIPData()));
		cWipRow.setProjectName(rs.getString(wipProp.getProjectName()));
		cWipRow.setChangeUserId(rs.getString(wipProp.getChangeUserId()));
		cWipRow.setPromoteUserId(rs.getString(wipProp.getPromoteUserId()));
		cWipRow.setTimestamp(rs.getString(wipProp.getTimeStamp()));
		cWipRow.setPackageId(rs.getString(wipProp.getPackageId()));
		cWipRow.setErrorIndicator(rs.getString(wipProp.getErrorIndicator()));
		cWipRow.setOldErrorInd(rs.getString(wipProp.getErrorIndicator()));
		cWipRow.setPromotionLock(rs.getString(wipProp.getPromotionLock()));
		cWipRow.setEbcdicKey(rs.getString(wipProp.getEBCDICKey()));
		return cWipRow;
	}

	/**
	 * This method creates a AuditWIPRow from a valid ResultSet element
	 * 
	 * @param iWipRows
	 * @param rs
	 * @param wipProp
	 * @return
	 * @throws java.lang.Exception
	 * @throws java.sql.SQLException
	 */

//	public static AuditRow createAuditRow(IWIPRows iWipRows, ResultSet rs,
//	        WIPProperties wipProp) throws java.sql.SQLException,
//	        java.lang.Exception {
//
//		AuditRow aRow = new AuditRow();
//
//		aRow.setEnvironment(iWipRows.getRequestFilter().getEnvironment());
//		aRow.setCompanyCode(rs.getString(wipProp
//		        .getCompanyCode(Constants.AUDIT_LOG)));
//		aRow.setDdlName(rs.getString(wipProp.getDDLName(Constants.AUDIT_LOG)));
//		aRow.setSubsetName(rs.getString(wipProp
//		        .getSubsetName(Constants.AUDIT_LOG)));
//		aRow.setNewConcatKey(rs.getString(wipProp
//		        .getNewConcatKey(Constants.AUDIT_LOG)));
//
//		aRow.setTableId(rs.getString(wipProp.getTableId(Constants.AUDIT_LOG)));
//		aRow.setProjectName(rs.getString(wipProp
//		        .getProjectName(Constants.AUDIT_LOG)));
//		aRow.setChangeUserId(rs.getString(wipProp
//		        .getChangeUserId(Constants.AUDIT_LOG)));
//		aRow.setPromoteUserId(rs.getString(wipProp
//		        .getPromoteUserId(Constants.AUDIT_LOG)));
//		aRow.setPackageId(rs.getString(wipProp
//		        .getPackageId(Constants.AUDIT_LOG)));
//		aRow.setOperation(rs.getString(wipProp
//		        .getOperation(Constants.AUDIT_LOG)));
//		aRow.setOldConcatKey(rs.getString(wipProp
//		        .getOldConcatKey(Constants.AUDIT_LOG)));
//		aRow.setOldData(rs.getString(wipProp.getOldData(Constants.AUDIT_LOG)));
//		// Convert High Values only for the Key
//		// Use the existing old concat key here
//		// String convertedData = aRow.getOldConcatKey() +
//		// data.substring(td.getKeyColumnsLength());
//		// aRow.setOldData(convertedData);
//		aRow.setOldData(rs.getString(wipProp.getOldData()));
//
//		// Use the existing new concat key here
//		// convertedData = aRow.getNewConcatKey() +
//		// data.substring(td.getKeyColumnsLength());
//		// aRow.setNewData(convertedData);
//		aRow.setNewData(rs.getString(wipProp.getNewData(Constants.AUDIT_LOG)));
//
//		aRow.setProductPrefix(rs.getString(wipProp
//		        .getProductPrefix(Constants.AUDIT_LOG)));
//
//		aRow.setTimestamp(rs.getString(wipProp
//		        .getTimeStamp(Constants.AUDIT_LOG)));
//		return aRow;
//	}

	/**
	 * This method creates a IndexWIPRow from a valid ResultSet element
	 * 
	 * @param rs
	 * @param wipProp
	 * @return
	 * @throws java.lang.Exception
	 * @throws java.sql.SQLException
	 */

	public static IndexWIPRow createIndexRow(ResultSet rs, WIPProperties wipProp)
	        throws java.sql.SQLException, java.lang.Exception {

		IndexWIPRow iWipRow = new IndexWIPRow();

		iWipRow.setDbSequence(rs.getLong(wipProp.getDBSequence()));
		iWipRow.setEnvironment(rs.getString(wipProp.getEnvironment()));
		iWipRow.setCompanyCode(rs.getString(wipProp.getCompanyCode()));
		iWipRow.setProductPrefix(rs.getString(wipProp.getProductPrefix()));
		iWipRow.setParentTableId(rs.getString(wipProp.getPrimaryTableId()));
		iWipRow.setParentTableSubset(rs
		        .getString(wipProp.getPrimaryPtrSubset()));
		iWipRow.setChildTableId(rs.getString(wipProp.getSecondaryTableId()));
		iWipRow.setChildTableSubset(rs.getString(wipProp
		        .getSecondaryPtrSubset()));
		iWipRow.setChildTableVar(rs.getString(wipProp.getSecondaryTableVar()));
		iWipRow.setOperation(rs.getString(wipProp.getOperation()));
		iWipRow.setOldConcatKey(rs.getString(wipProp.getOldConcatKey()));
		iWipRow.setProjectName(rs.getString(wipProp.getProjectName()));
		iWipRow.setChangeUserId(rs.getString(wipProp.getChangeUserId()));
		iWipRow.setPromoteUserId(rs.getString(wipProp.getPromoteUserId()));
		iWipRow.setTimestamp(rs.getString(wipProp.getTimeStamp()));
		iWipRow.setPackageId(rs.getString(wipProp.getPackageId()));
		iWipRow.setErrorIndicator(rs.getString(wipProp.getErrorIndicator()));
		iWipRow.setOldErrorInd(rs.getString(wipProp.getErrorIndicator()));
		iWipRow.setPromotionLock(rs.getString(wipProp.getPromotionLock()));
		return iWipRow;
	}

	/**
	 * This method creates a PlanWIPRow from a valid ResultSet element
	 * 
	 * @param rs
	 * @param wipProp
	 * @return
	 * @throws java.lang.Exception
	 * @throws java.sql.SQLException
	 */

	public static PlanWIPRow createPlanRow(ResultSet rs)
	        throws java.sql.SQLException, java.lang.Exception {

		WIPProperties wipProp = WIPProperties.getInstance();
		PlanWIPRow pWipRow = new PlanWIPRow();

		pWipRow.setDbSequence(rs.getLong(wipProp.getDBSequence()));
		// refactor plan key.
		pWipRow.setDataNew(new PlanRowTO(rs));
		/*
		pWipRow.setEnvironment(rs.getString(wipProp.getEnvironment()));
		pWipRow.setCompanyCode(rs.getString(wipProp.getCompanyCode()));
		pWipRow.setProductPrefix(rs.getString(wipProp.getProductPrefix()));
		pWipRow.setProductSuffix(rs.getString(wipProp.getProductSuffix()));
		pWipRow.setPlanCode(rs.getString(wipProp.getPlanCode()));
		pWipRow.setIssueState(rs.getString(wipProp.getIssueState()));
		pWipRow.setLob(rs.getString(wipProp.getLOB()));
		pWipRow.setEffDate(rs.getString(wipProp.getEffectiveDate()));
		pWipRow.setPlanType(rs.getString(wipProp.getPlanType()));
		pWipRow.setTablePtrId(rs.getString(wipProp.getTablePtrId()));
		pWipRow.setTablePtrSubset(rs.getString(wipProp.getTablePtrSubset()));
		pWipRow.setTablePtrVar(rs.getString(wipProp.getTablePtrVar()));
		*/
		pWipRow.setOperation(rs.getString(wipProp.getOperation()));
		// refactor plan key.
		pWipRow.setDataOld(new PlanRowTO(rs, wipProp));
		/*
		pWipRow.setOldCompanyCode(rs.getString(wipProp.getOldCompanyCode()));
		pWipRow
		        .setOldProductPrefix(rs
		                .getString(wipProp.getOldProductPrefix()));
		pWipRow
		        .setOldProductSuffix(rs
		                .getString(wipProp.getOldProductSuffix()));
		pWipRow.setOldPlanCode(rs.getString(wipProp.getOldPlanCode()));
		pWipRow.setOldIssueState(rs.getString(wipProp.getOldIssueState()));
		pWipRow.setOldLob(rs.getString(wipProp.getOldLOB()));
		pWipRow.setOldEffDate(rs.getString(wipProp.getOldEffectiveDate()));
		pWipRow.setOldPlanType(rs.getString(wipProp.getOldPlanType()));
		pWipRow.setOldPtrId(rs.getString(wipProp.getOldPtrId()));
		pWipRow.setOldPtrVar(rs.getString(wipProp.getOldPtrVar()));
		pWipRow.setOldPtrSubset(rs.getString(wipProp.getOldPtrSubset()));
		*/
		pWipRow.setProjectName(rs.getString(wipProp.getProjectName()));
		pWipRow.setChangeUserId(rs.getString(wipProp.getChangeUserId()));
		pWipRow.setPromoteUserId(rs.getString(wipProp.getPromoteUserId()));
		pWipRow.setTimestamp(rs.getString(wipProp.getTimeStamp()));
		pWipRow.setPackageId(rs.getString(wipProp.getPackageId()));
		pWipRow.setErrorIndicator(rs.getString(wipProp.getErrorIndicator()));
		pWipRow.setOldErrorInd(rs.getString(wipProp.getErrorIndicator()));
		pWipRow.setPromotionLock(rs.getString(wipProp.getPromotionLock()));
		return pWipRow;
	}

	/**
	 * This method creates a blank WIPRow of the specified type Creation date:
	 * (10/12/2001 5:27:33 PM)
	 * 
	 * @return com.csc.fsg.life.pw.web.io.WIPRow
	 * @param type
	 *            java.lang.String
	 */

	public static WIPRow createWIPRowOfType(int type) {

		WIPRow wipRow = null;

		if (type == Constants.COMMON_WIP) {
			wipRow = new CommonWIPRow();
		} else if (type == Constants.PLAN_WIP) {
			wipRow = new PlanWIPRow();
		} else if (type == Constants.INDEX_WIP) {
			wipRow = new IndexWIPRow();
//		} else if (type == Constants.AUDIT_LOG) {
//			wipRow = new AuditRow();
		}
		return wipRow;
	}

	/**
	 * This method searches for and replaces any unexpected high value
	 * substitutions with the appropriate value
	 * 
	 * @param String
	 *            inputString
	 * @return String outputString
	 */

	// private static String convertData(String s) {
	// return s;
	// StringBuffer sb = new StringBuffer(s.length());
	// for (int i = 0; i < s.length(); i++) {
	// sb.append(Utils.convertBytes(s.substring(i, i+1)));
	// }
	// return sb.toString();
	// }
}
