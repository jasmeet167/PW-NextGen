/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

import java.sql.*;
import java.util.*;

import com.csc.fsg.life.pw.common.StructureNode;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.avm.*;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.descriptor.wma.T000XARow;
import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderMERGEDX;
import com.csc.fsg.life.rest.model.TreeNodeData.LazyTypeEnum;

/**
 * Class ProductWriterThread
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

/* Modifications: CCCV-D911, T0103, T0091, ENH961, ENH962 ,T0120, T0129 */
// CCCV-D911 pad spaces if company code is less than 3 chars

public class ProductWriter {
	private Hashtable<String, String> _xaCache = new Hashtable<String, String>();

	private static final int TOKEN_LENGTH = 24;

	private static final String TAB = "\t";

	private static final String NEW_LINE = "\n";

	private PlanMergeAssistent pma = null;
	private IndexMergeAssistent ima = null;
	
	public ProductWriter(PlanMergeAssistent pma, IndexMergeAssistent ima){
		this.pma = pma;
		this.ima = ima;
	}
	
	private String getXAQuery() {
		StringBuffer sb = new StringBuffer();
		sb
		        .append(" SELECT DISTINCT SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR ");
		sb
		        .append(" FROM  SESSION.MERGEDXA WHERE  COMPANY_CODE = ? AND PRODUCT_PREFIX = ?  ");
		sb.append(" AND PRIMARY_TABLE_ID = ? AND PRIMARY_PTR_SUBSET = ?  ");
		sb
		        .append(" ORDER BY SECONDARY_TABLE_ID, SECNDRY_PTR_SUBSET, SECNDRY_TABLE_VAR ");
		return sb.toString();
	}

	private String getPlanQuery(PlanCriteriaTO planCriteria) {
		return new SQLBuilderMERGEDX(planCriteria.getEnvironment(), planCriteria).buildSelectForWriterStatement();
	}

	private String getPointerQuery(PlanCriteriaTO planCriteria) {
		return new SQLBuilderMERGEDX(planCriteria.getEnvironment(), planCriteria).buildSelectTablePtrsForWriterStatement();
	}

	public PlanBuffer getPlanListStream(LazyTypeEnum lazyType, String env, String company, String product,
	        Connection conn, boolean viewChanges) throws Exception {

		PlanBuffer buffer = new PlanBuffer();

		Set<String> planKeys = pma.getPlans();
		for (String planKey : planKeys ) {
			PlanTO planTO = new PlanTO(planKey, "|");
			if ( !planTO.getCompanyCode().equals(company) || !planTO.getProductCode().equals(product) )
				continue;
			planTO.setEnvironment(env);
			String planType = planTO.getPlanType();
			PlanCriteriaTO tmpPlan = new PlanCriteriaTO(planTO);
			tmpPlan.setViewChanges(viewChanges);
			
			if (isPlanApplicable(planType, lazyType)) {
				String plan = writePlan(tmpPlan, conn);
				if (planType.equalsIgnoreCase("B") || planType.equalsIgnoreCase("H")) 
					buffer.appendBase(plan);
				else if (planType.equalsIgnoreCase("R"))
					buffer.appendRider(plan);
				else if (planType.equalsIgnoreCase("P"))
					buffer.appendArch(plan);
				else if (planType.equalsIgnoreCase("W"))
					buffer.appendPayout(plan);
			}
		}

		return buffer;
	}

	private boolean isPlanApplicable(String planType, LazyTypeEnum lazyType)
	{
		if (lazyType == null)
			return true;

		if (planType.equalsIgnoreCase("P"))
			if (lazyType == LazyTypeEnum.PDF)
				return true;

		if (planType.equalsIgnoreCase("H"))
			if (lazyType == LazyTypeEnum.H)
				return true;

		if (planType.equalsIgnoreCase("B"))
			if (lazyType == LazyTypeEnum.B)
				return true;

		if (planType.equalsIgnoreCase("R"))
			if (lazyType == LazyTypeEnum.R)
				return true;

		if (planType.equalsIgnoreCase("W"))
			if (lazyType == LazyTypeEnum.P)
				return true;

		return false;
	}

	private String writePlan(PlanCriteriaTO planCriteria, Connection conn)
		throws Exception
	{
		String productPrefix = planCriteria.getProductPrefix();
		String planType = planCriteria.getPlanType();
		
		StringBuffer buffer = new StringBuffer();
		if (!(productPrefix.equals("N") || productPrefix.equals("H"))) 
			buffer.append("0" + TAB + "4" + TAB);
		else
			buffer.append("0" + TAB + "3" + TAB);
		
		if (planType.trim().equals("W")) {	
			buffer.append("24").append(TAB);                     
		} else if (!planType.trim().equals("R")) {
			buffer.append("10").append(TAB);                     
		} else {
			buffer.append("11").append(TAB);                     
		}
		
		int tableAuth = Constants.NODE_ATTR_INQUIRY;
//		if (user.isPermitted(planCriteria.getEnvironment(), planCriteria.getCompanyCode(), "000", PolicyConstants.UPDATE))
			tableAuth = Constants.NODE_ATTR_UPDATE;
		
		buffer.append(tableAuth).append(TAB);                     
		buffer.append(getDisplayName(planCriteria, conn)).append(TAB);
		buffer.append(planCriteria.getPlanKey(TAB));
		buffer.append(NEW_LINE);

		return buffer.toString();
	}

	public String getPlanDetailStream(Connection conn, PlanCriteriaTO planCriteria)
		throws Exception
	{
		PreparedStatement xaStmt = null;

		StringBuffer buffer = new StringBuffer();
		String productPrefix = planCriteria.getProductPrefix();

		try {
			xaStmt = conn.prepareStatement(getXAQuery());

			int level = 5;
			Set<PlanRowTO> planRows = pma.getPlanRows(planCriteria);
			for (PlanRowTO planRow : planRows ) {
				planCriteria.setTablePtrId(planRow.getTablePtrId());
				planCriteria.setTablePtrVar(planRow.getTablePtrVar());
				planCriteria.setTablePtrSubset(planRow.getTablePtrSubset());
				
				String ddlName = EnvironmentManager.getInstance()
					.getEnvironment(planCriteria.getEnvironment())
					.getTableDescMgr().getDDLName(planCriteria.getTablePtrId());
				if (ddlName==null) {
					continue;
				}

				// ENH962 - use getStructureNode
				StructureNode sn = TreeUtils.getStructureNode(
						planCriteria.getProductPrefix(),
						planCriteria.getPlanType(), planCriteria.getTablePtrId(),
						planCriteria.getTablePtrVar(), "1", "0");
						
				String tablePrepend = sn.getTranslateText();
				if ( tablePrepend == null )
					tablePrepend = "";
				else if ( tablePrepend.length() > 0 )
					tablePrepend += " ";
				
				if ((productPrefix.equals("N") || productPrefix.equals("H"))) 
					level = 4;
				
				int tableAuth = Constants.NODE_ATTR_INQUIRY;
//				if (user.isPermitted(planCriteria.getEnvironment(), planCriteria.getCompanyCode(), planCriteria.getTablePtrId(), PolicyConstants.UPDATE))
					tableAuth = Constants.NODE_ATTR_UPDATE;
					
				buffer.append(sn.getNodeId()+TAB+level+TAB);
				buffer.append("14").append(TAB);
				buffer.append(tableAuth);                         
				buffer.append(TAB);
				buffer.append((tablePrepend + AVManager.getTableName(planCriteria.getEnvironment(),ddlName)).trim()).append(TAB);
				buffer.append(ddlName).append(TAB);
				buffer.append(planCriteria.getTablePtrId()).append(TAB);
				buffer.append(planCriteria.getTablePtrVar()).append(TAB);
				buffer.append(planCriteria.getTablePtrSubset()).append(TAB);
				buffer.append(planCriteria.getCompanyCode()).append(TAB);
				buffer.append(planCriteria.getPlanKey("|")).append("|").append(TAB);
				buffer.append("F").append(TAB);
				buffer.append(NEW_LINE);
				
				writePointers(planCriteria, sn.getNodeId() + "", buffer, level, ddlName, xaStmt);
			}

			return buffer.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			Utils.closePreparedStatement(xaStmt);
		}
	}

	private void writePointers(PlanCriteriaTO planCriteria, String parentNodeId, 
	        StringBuffer buffer, int level, String ddlName,
	        PreparedStatement xaStmt) throws Exception {
		String productPrefix = planCriteria.getProductPrefix();
		
		StringBuffer key = new StringBuffer(planCriteria.getCompanyCode()).append(productPrefix).append(planCriteria.getTablePtrId()).append(planCriteria.getTablePtrSubset()).append(planCriteria.getTablePtrVar());
		String xaValue = _xaCache.get(key.toString());
		
		if (xaValue != null) 
		{
			writePointersFromCache(new PlanCriteriaTO(planCriteria), parentNodeId, buffer, level, xaStmt, productPrefix, xaValue);
		} else 
		{
			
			ResultSet rs = null;
			StringBuffer value = new StringBuffer();
			List<T000XARow> children = ima.getXAChildren(planCriteria.getCompanyCode(),
					planCriteria.getProductPrefix(), planCriteria.getTablePtrId(),
					planCriteria.getTablePtrSubset());
			if ( children != null ) {
				for ( T000XARow row : children ) {
					value.append(pad(row.getCompanyCode(),3));
					value.append(row.getProductPrefix());
					value.append(row.getSecondaryTableId());
					value.append(row.getSecndryPtrSubset());
					value.append(row.getSecndryTableVar());
				}
			} else {
				value.append("L");
			}

			_xaCache.put(key.toString(), value.toString());
			writePointers(planCriteria, parentNodeId,  buffer, level, ddlName,xaStmt);
		}
	}

	/*
	private void writePointers(PlanCriteriaTO planCriteria, String parentNodeId, 
	        StringBuffer buffer, int level, String ddlName,
	        PreparedStatement xaStmt) throws Exception {
		String productPrefix = planCriteria.getProductPrefix();
		
		StringBuffer key = new StringBuffer(planCriteria.getCompanyCode()).append(productPrefix).append(planCriteria.getTablePtrId()).append(planCriteria.getTablePtrSubset()).append(planCriteria.getTablePtrVar());
		String xaValue = _xaCache.get(key.toString());
		
		if (xaValue != null) 
		{
			writePointersFromCache(new PlanCriteriaTO(planCriteria), parentNodeId, buffer, level, xaStmt, productPrefix, xaValue);
		} else 
		{
			
			ResultSet rs = null;
			StringBuffer value = new StringBuffer();
			try{
				
				xaStmt.setString(1, pad(planCriteria.getCompanyCode(),3));
				xaStmt.setString(2, planCriteria.getProductPrefix());
				xaStmt.setString(3, planCriteria.getTablePtrId());
				xaStmt.setString(4, planCriteria.getTablePtrSubset());

				rs = xaStmt.executeQuery();
				if (rs.next()) {
					do {
						// TT wmA SPR 6516 - pad company code
						value.append(pad(planCriteria.getCompanyCode(),3));
						value.append(planCriteria.getProductPrefix());
						value.append(rs.getString(1));
						value.append(rs.getString(2));
						value.append(rs.getString(3));
					} while (rs.next());
				} else {
					value.append("L");
				}
			} catch (Exception e) {
				throw e;
			} finally {
				Utils.closeResultSet(rs);
			}

			_xaCache.put(key.toString(), value.toString());
			writePointers(planCriteria, parentNodeId,  buffer, level, ddlName,xaStmt);
		}
	}
*/	
	/** V-D911 pad spaces if company code is less than 3 chars */
	private String pad(String s, int len) {
		String padding = "                                                                                                    ";
		return (s + padding).substring(0, len);
	}

	private void writePointersFromCache(PlanCriteriaTO planCriteria, String parentNodeId, StringBuffer buffer, int level, PreparedStatement xaStmt, String productPrefix, String xaValue) throws Exception {
		if (!(xaValue).equals("L"))    
		{
			int tokens = xaValue.length() / TOKEN_LENGTH;
			for (int i = 0; i < tokens; i++) 
			{
				String valueSt = xaValue.substring(i * TOKEN_LENGTH,(i + 1) * TOKEN_LENGTH);
				planCriteria.setTablePtrId(valueSt.substring(4, 7));
				planCriteria.setTablePtrSubset(valueSt.substring(7, 23));
				planCriteria.setTablePtrVar(valueSt.substring(23, 24));
				
				String valueDDLName = EnvironmentManager.getInstance()
					.getEnvironment(planCriteria.getEnvironment())
					.getTableDescMgr().getDDLName(planCriteria.getTablePtrId());
				if (valueDDLName==null)
				{
					continue;
				}
				
				int childLevel = level + 1;

				int tempLevel = childLevel;
				if ((productPrefix.equals("N") || productPrefix.equals("H"))) 
					tempLevel = childLevel-3;
				else
					tempLevel = childLevel-4;
											
				// ENH962 - use getStructureNode
				StructureNode sn = TreeUtils.getStructureNode(
						productPrefix, planCriteria.getPlanType(),
						planCriteria.getTablePtrId(), planCriteria.getTablePtrVar(),
						tempLevel+"", parentNodeId);
				String nodeId = sn.getNodeId() + "";
				
				String tablePrepend = sn.getTranslateText();
				if ( tablePrepend == null )
					tablePrepend = "";
				else if ( tablePrepend.length() > 0 )
					tablePrepend += " ";		
				
				String ddlName = EnvironmentManager.getInstance()
					.getEnvironment(planCriteria.getEnvironment())
					.getTableDescMgr().getDDLName(planCriteria.getTablePtrId());

				int tableAuth = Constants.NODE_ATTR_INQUIRY;
//				if (user.isPermitted(planCriteria.getEnvironment(), planCriteria.getCompanyCode(), planCriteria.getTablePtrId(), PolicyConstants.UPDATE))
					tableAuth = Constants.NODE_ATTR_UPDATE;
				
				buffer.append(nodeId).append(TAB);
				buffer.append(childLevel).append(TAB);
				buffer.append("14").append(TAB);
				buffer.append(tableAuth);  
				buffer.append(TAB);
				buffer.append((tablePrepend + AVManager.getTableName(planCriteria.getEnvironment(),ddlName)).trim()).append(TAB);
				buffer.append(valueDDLName).append(TAB);
				buffer.append(planCriteria.getTablePtrId()).append(TAB);
				buffer.append(planCriteria.getTablePtrVar()).append(TAB);
				buffer.append(planCriteria.getTablePtrSubset()).append(TAB);
				buffer.append(planCriteria.getCompanyCode()).append(TAB);
				buffer.append(planCriteria.getPlanKey("|")).append("|").append(TAB);
				buffer.append("F").append(TAB);
				buffer.append(NEW_LINE);
				
				writePointers(planCriteria, nodeId, buffer, childLevel,valueDDLName,xaStmt);
			}
		}
	}

	private String getDisplayName(PlanCriteriaTO planCriteria, Connection conn) {
		String planName = null;
		try {
				planName = PlanNameAssistent.getPlanName(planCriteria,conn);
				if ((planName == null) || (planName.trim().equals(""))) 
			    	planName = planCriteria.getPlanCode();
		} catch (Exception e) {
			//IGNORE EXCEPTION
			planName = planCriteria.getPlanCode();
		}
		return planName;
	}
}
