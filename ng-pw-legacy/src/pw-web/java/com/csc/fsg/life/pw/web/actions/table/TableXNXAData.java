package com.csc.fsg.life.pw.web.actions.table;

import java.util.*;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.controller.BaseAction;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.io.*;

/* Modifications: T0103, T0091, WMABASEIXI-1997, WMABASEIXI-2189 */

/**
 * @author smunigun
 */

public class TableXNXAData implements ITableManager {

	/** For Entire Table View */

	public final StringBuffer getData(HashMap req, int userAuthority)
	        throws Exception {

		StringBuffer sb = new StringBuffer();

		String table = BaseAction.getRequestParam(req, "table");
		String tableId = null;
		if (table.length() > 3) {
			tableId = table.substring(table.length() - 4, table.length() - 1);
		} else {
			tableId = table;
		}

		String envId= BaseAction.getRequestParam(req, "environment");
		String company_code = BaseAction.getRequestParam(req, "company_code");
		String userCondition = BaseAction.getRequestParam(req, "userCondition");

		PlanCriteriaTO planCriteria = new PlanCriteriaTO();
		planCriteria.setEnvironment(envId);
		planCriteria.setCompanyCode(company_code);

		// TT wmA SPR 6143 - set view changes properly
		String viewType = BaseAction.getRequestParam(req, "changes");
		planCriteria.setViewChanges("with".equals(viewType));
		
		HashMap treeKey = new HashMap(2);
		treeKey.put("schema", envId);
		treeKey.put("company_code", company_code);
		if ( userCondition != null && userCondition.trim().length() > 0 )
			treeKey.put("userCondition", userCondition);
		
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);

		ArrayList<Object> mergedRows = null;
		if (tableId.equalsIgnoreCase(Constants.TABLE_ZERO_ID)) {
			mergedRows = new PlanTableStream(planCriteria).mergePlanTable(userCondition);
			//mergedRows = new PlanTableStream(environment).getPlanTableRowsWithMerging(treeKey);
		} else {
			// WMABASEIXI-2189 check viewType
			if ( "with".equals(viewType) ) {
				mergedRows = new PlanTableStream(environment)
					.getIndexTableRowsWithMerging(treeKey);
			} else {
				mergedRows = new PlanTableStream(environment)
					.getIndexTableRowsWithOutMerging(treeKey);
			}
		}

		Iterator<Object> it = mergedRows.iterator();

		/** PAGING LINE */

		sb.append("").append(Constants.STAB);
		sb.append(Constants.NO_LOCATE);
		sb.append("").append(Constants.STAB);
		sb.append(Constants.STAB);
		sb.append("").append(Constants.STAB);
		sb.append(Constants.PAGING_NO_NEXT);
		sb.append(Constants.STAB);
		sb.append(Constants.PAGING_NO_PREV);
		sb.append(Constants.EOL);

		/** TABLE AND COLUMN DESC LINE */

		
		TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(tableId);

		// WMABASEIXI-1997 - send read-only table attribute
		//int attribute = userAuthority | Constants.TABLE_ATTR_READ_ONLY;
		// WMABASEIXI-2189 - back out 1997 change.  Read-only handled on client side.
		int attribute = userAuthority;
		sb.append(attribute).append(Constants.STAB);
		td.toStream(sb);
		sb.append(Constants.EOL);

		ColumnDescriptor descriptor = null;
		for (int i = 1; i <= td.getNumberOfColumns(); i++) {
			descriptor = td.getColumn(i);
			descriptor.toStream(sb, "*", environment.getId());
			sb.append(Constants.EOL);
		}

		/** Data */
		Object obj = null;
		while (it.hasNext()) {
			obj = it.next();
			if (obj instanceof Row) {
				sb.append("0").append(Constants.STAB);
				for (int i = 1; i <= td.getNumberOfColumns(); i++) {
					descriptor = td.getColumn(i);
					sb.append(
					        WizUtils.pwTrim(descriptor.getValue((Row) obj)
					                .toString())).append(Constants.STAB);
				}
				sb.append(
				        UtilMethods
				                .buildWithDelim((Row) obj, environment.getId(), true))
				        .append(Constants.STAB);
				sb.append(
				        UtilMethods.buildWithDelim((Row) obj, environment.getId(),
				                false)).append(Constants.EOL);
			} else {
				if (obj instanceof CommonWIPRow) {
					sb.append(((CommonWIPRow) obj).toString());
					sb.append(Constants.EOL);
				} else if (obj instanceof PlanWIPRow) {
					sb.append(((PlanWIPRow) obj).toString());
					sb.append(Constants.EOL);
				// WMABASEIXI-2189 include IndexWIPRow
				} else if (obj instanceof IndexWIPRow) {
					sb.append(((IndexWIPRow) obj).toString());
					sb.append(Constants.EOL);
				}
			}
		}
		return sb;
	}

	/** For Business Rule View */

	// public static StringBuffer getPlanNodeInfo(HashMap req)
	// throws Exception {
	// StringBuffer sb = new StringBuffer();
	//
	// Integer highval = new Integer(63);
	//
	// String schema = BaseAction.getRequestParam(req,"environment");
	// String company = BaseAction.getRequestParam(req,"company_code");
	// String prdPrefix = BaseAction.getRequestParam(req,"product_prefix");
	// String prdSuffix = BaseAction.getRequestParam(req,"product_suffix");
	// String lob = BaseAction.getRequestParam(req,"lob");
	// String issueStates = BaseAction.getRequestParam(req,"issue_state");
	// String planCode = BaseAction.getRequestParam(req,"plan_code");
	// String effDate = BaseAction.getRequestParam(req,"effdate");
	//		
	// String planType = BaseAction.getRequestParam(req,"plantype");
	//
	// String viewType = BaseAction.getRequestParam(req,"changes");
	//
	// // if (issueStates.trim().startsWith("|"))
	// // issueStates = DataUtils.toHighValues(issueStates.trim());
	// //
	// // if (lob.trim().startsWith("|"))
	// // lob = DataUtils.toHighValues(lob.trim());
	// //
	// // if (prdSuffix.trim().startsWith("|"))
	// // prdSuffix = DataUtils.toHighValues(prdSuffix.trim());
	//
	// String key = company + "|" + prdPrefix + "|" + prdSuffix + "|"
	// + planCode + "|" + issueStates + "|" + lob + "|" + effDate
	// + "|" + planType;
	//
	// HashMap treeKey = new HashMap(9);
	//
	// PlanTableStream plnStream = new PlanTableStream(schema);
	// PlanTableStream.fillKey(schema, key, treeKey);
	//
	// ArrayList mergedRows = null;
	//
	// if (viewType != null) {
	// if (viewType.trim().equals("with")
	// || viewType.trim().equalsIgnoreCase("only")) {
	// Vector projectsVector = DataUtils.getProjectsVector(req);
	// if (projectsVector.size() > 0)
	// treeKey.put("projects", projectsVector);
	//
	// mergedRows = plnStream.mergePlanTable(treeKey, "with");
	// } else {
	// mergedRows = plnStream.mergePlanTable(treeKey, "without");
	//
	// }
	// }
	//
	// /** PAGING LINE */
	//
	// sb.append("").append(Constants.STAB);
	// sb.append(Constants.NO_LOCATE);
	// sb.append("").append(Constants.STAB);
	// sb.append(Constants.STAB);
	// sb.append("").append(Constants.STAB);
	// sb.append(Constants.PAGING_NO_NEXT);
	// sb.append(Constants.STAB);
	// sb.append(Constants.PAGING_NO_PREV);
	// sb.append(Constants.EOL);
	//
	// /** TABLE AND COLUMN DESC LINE */
	//
	// TableDescriptorManager tdm = TableDescriptorManager.getInstance();
	// TableDescriptor td = tdm.getTableDescriptor(Constants.TABLE_ZERO_ID);
	//
	// sb.append("0").append(Constants.STAB);
	// td.toStream(sb);
	// sb.append(Constants.EOL);
	//
	// ColumnDescriptor descriptor = null;
	// for (int i = 1; i <= td.getNumberOfColumns(); i++) {
	// descriptor = td.getColumn(i);
	// descriptor.toStream(sb,prdPrefix);
	// sb.append(Constants.EOL);
	// }
	//
	// /** DATA Stream */
	// Iterator it = null;
	// if (mergedRows != null)
	// it = mergedRows.iterator();
	//
	// Object obj = null;
	// while (it.hasNext()) {
	// obj = it.next();
	// if (obj instanceof Row) {
	// sb.append("0").append(Constants.STAB);
	// for (int i = 1; i <= td.getNumberOfColumns(); i++) {
	// descriptor = td.getColumn(i);
	// sb.append(
	// WizUtils.pwTrim(descriptor.getValue((Row) obj)
	// .toString())).append(Constants.STAB);
	// }
	// sb.append(UtilMethods.buildWithDelim((Row) obj, schema,true))
	// .append(Constants.STAB);
	// sb.append(UtilMethods.buildWithDelim((Row) obj, schema,false))
	// .append(Constants.EOL);
	// } else {
	// sb.append(((CommonWIPRow) obj).toString());
	// }
	// }
	//
	// return sb;
	// }
}
