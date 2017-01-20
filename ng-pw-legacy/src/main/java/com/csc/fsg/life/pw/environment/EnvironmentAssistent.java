
package com.csc.fsg.life.pw.environment;

import java.sql.*;
import java.util.*;


import com.csc.fsg.life.pw.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.util.*;
//import com.csc.fsg.life.pw.actions.tree.PlanMergeAssistent;
import com.csc.fsg.life.pw.config.*;
import com.csc.fsg.life.pw.io.*;
import com.csc.fsg.life.pw.util.*;
//import com.csc.fsg.life.pw.web.utils.sql.SQLBuilderMERGEDX;

/* Modifications: T0103, T0091 ,CCCV-E-ISSUE_STATE, T0129 */

public class EnvironmentAssistent {
	
	private Hashtable<String, Hashtable<String, Integer>> rowCountCache = null;

	private Hashtable<String, String> companyCodesAndNames;
	
	private Environment environment = null;
	
	private PlanTableInfo planTableInfo = null; 

	public EnvironmentAssistent(Environment environment){
		this.environment = environment;
		this.planTableInfo = new PlanTableInfo(environment);
	}
	
	public void synchronizeCompanies() {
		companyCodesAndNames = null;
	}
	
	public int countRowsForCompany(String tableDDLName, String companyCode,
	        Connection con) throws  Exception {

		Hashtable<String, Integer> companyHash = null;
		Integer rowCount = null;
		int count = -1;

		companyCode = (companyCode.trim()).toUpperCase();
		tableDDLName = (tableDDLName.trim()).toUpperCase();

		if (rowCountCache == null) {
			rowCountCache = new Hashtable<String, Hashtable<String, Integer>>();
		}

		companyHash = rowCountCache.get(companyCode);

		if (companyHash == null) {
			companyHash = new Hashtable<String, Integer>();
			rowCountCache.put(companyCode, companyHash);
		} else {
			rowCount = companyHash.get(tableDDLName);
			if ((rowCount != null) && ((count = rowCount.intValue()) != -1)) {
				return count;
			}
		}

		Statement stmt = null;
		ResultSet resultSet = null;

		try {
			String sqlString = "SELECT COUNT(*) FROM "
			        + environment.getSchema() + "." + tableDDLName
			        + " where COMPANY_CODE = '" + companyCode + "'";

			stmt = con.createStatement();
			resultSet = SqlPW.query(SqlPW.SQL_ROWS_FOR_COMPANY, stmt, sqlString);

			resultSet.next();
			count = resultSet.getInt(1);

			companyHash = rowCountCache.get(companyCode);
			companyHash.put(tableDDLName, Integer.valueOf(count));

		} catch (Exception exception) {
			companyHash.put(companyCode, Integer.valueOf(-1));
		} finally {
			Utils.closeResultSet(resultSet);
			Utils.closeStatement(stmt);
		}

		return count;
	}
	
	public void initRowCountCache(String companyCode, Set<String> tableNames, Connection con) {
		if (rowCountCache == null) {
			rowCountCache = new Hashtable<String, Hashtable<String, Integer>>();
		}

		Hashtable<String, Integer> companyHash = rowCountCache.get(companyCode);

		if (companyHash != null)
			return;
		
		companyHash = new Hashtable<String, Integer>();
		rowCountCache.put(companyCode, companyHash);
		
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = con.createStatement();
			
			int numSelects = 0;
			StringBuffer sql = new StringBuffer();
			for (String tableName : tableNames ) {
				if ( sql.length() > 0 ) {
					sql.append("\nUNION ALL\n");
				}
				sql.append("select '").append(tableName).append("' as tableName");
				sql.append(", count(*) as numRows from ");
				sql.append(environment.getSchema()).append(".").append(tableName);
		        sql.append(" where COMPANY_CODE = '").append(companyCode).append("'");
		        numSelects++;
		        if ( numSelects >= 20 ) {	// limit number of unions
					rs = SqlPW.query(SqlPW.SQL_ROWS_FOR_COMPANY, stmt, sql.toString());
					while ( rs.next() ) {
						companyHash.put(rs.getString(1), Integer.valueOf(rs.getInt(2)));
					}
					rs.close();
					numSelects = 0;
					sql.setLength(0);
		        }
			}

			rs = SqlPW.query(SqlPW.SQL_ROWS_FOR_COMPANY, stmt, sql.toString());
			while ( rs.next() ) {
				companyHash.put(rs.getString(1), Integer.valueOf(rs.getInt(2)));
			}
		} catch (Exception exception) {
			companyHash.put(companyCode, Integer.valueOf(-1));
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
	}

	public void clearRowCountCache() {
		rowCountCache = null;
	}

	public Hashtable<String, String> getCompanyCodesAndNames() throws Exception {

		if ( (companyCodesAndNames != null) && !companyCodesAndNames.isEmpty() )
			return companyCodesAndNames;
		
		companyCodesAndNames = new Hashtable<String, String>();
		
		TableFilter filter = new TableFilter();
		filter.setTableName("T001X");
		
		TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(filter.getTableId());
		Vector<Row> rows = td.getTableRows(environment,filter);
				
		int size = rows.size();
		for (int i = 0; i < size; i++) {
			Row row = rows.elementAt(i);
			companyCodesAndNames.put(row.getCompanyCode(), row.getValueOf(FieldNames.T001X_COMPANY_NAME));
		}
		
		return companyCodesAndNames;
	}

	

	public ArrayList<String> getNextPlanKey(PlanCriteriaTO planCriteria)
	throws Exception {
		return planTableInfo.getNextPlanKey(planCriteria);
	}

	public ArrayList<String> getIssueStates(PlanCriteriaTO planCriteria)
	throws Exception {
		return planTableInfo.getIssueStates(planCriteria);
	}
	
	// refactor plan key.
	/* @deprecated use getNextPlanKey(PlanCriteriaTO)
	public ArrayList getPlanCodes(String companyCode, String product,
	        boolean includeWIP) throws Exception {
		return planTableInfo.getPlanCodes(companyCode, product, includeWIP);
	}

	public ArrayList getBasePlanCodes(String companyCode, String product)
	        throws Exception {
		return planTableInfo.getPlanCodesForType(companyCode, product, "B");

	}

	public ArrayList getPayoutPlanCodes(String companyCode, String product)
	        throws Exception {
		return planTableInfo.getPlanCodesForType(companyCode, product, "W");
	}
	*/

	public ArrayList<String> getProductCodes(PlanCriteriaTO planCriteria) throws Exception {

		ArrayList<String> products = null, productsToReturn = null;
		HashMap<String, String> temp = null;
		HashMap<String, String> distinctPrefixes = null;
		String pp = null, ps = null, productCode = null;
		InstallConfigBean installBean = ProductManager.getInstance().
			getProduct(environment.getProductSystem()).getInstallBean();
		
		try {
			products = planTableInfo.getNextPlanKey(planCriteria);
			productsToReturn = new ArrayList<String>();
			temp = new HashMap<String, String>();
			distinctPrefixes = new HashMap<String, String>();
			for (int i = 0; i < products.size(); i++) {
				productCode = products.get(i);

				if (productCode.length() > 1) {
					pp = productCode.substring(0, 1);

					ps = productCode.substring(1, 2);
				} else {
					pp = productCode.substring(0, 1);
				}
				if (pp == null) {
					pp = "";
				}
				if (!distinctPrefixes.containsKey(pp)) {
					distinctPrefixes.put(pp, "");
					String productwithstarSuffix = pp + "*";

					if (!temp.containsKey(productwithstarSuffix)
						&& installBean.isProductInstalled(pp)) {
						productsToReturn.add(productwithstarSuffix);
						temp.put(productwithstarSuffix, "");
					}
				}

				if (ps == null) {
					ps = "";
				}
//				if (ps.equals(Constants.HIGHVALUES_1_BYTE)) {
//					ps = "*";
//				}
				String productKey = pp + ps;

				if (!temp.containsKey(productKey)
						&& installBean.isProductInstalled(pp)) {
					productsToReturn.add(productKey);
					temp.put(productKey, "");
				}
			}

	
		} finally {
			products = null;
		}
		if (productsToReturn.size() == 0)
			return installBean.getDefaultProductList();
		else 
			return productsToReturn;	//from Table Zero (T000X)
	}

	// refactor plan key.
	/* @deprecated use getProductCodes(PlanCriteriaTO)
	public ArrayList getProducts(String companyCode, boolean includeWIP)
	        throws Exception {

		ArrayList products = null, productsToReturn = null;
		HashMap distinctPrefixes = null;
		String pp = null, ps = null, productCode = null;
		InstallConfigBean installBean = ProductManager.getInstance().
								getProduct(environment.getProductSystem()).getInstallBean();
		
		try {
			products = planTableInfo.getProducts(companyCode, includeWIP);
			productsToReturn = new ArrayList();
			HashMap temp = new HashMap();
			distinctPrefixes = new HashMap();
			
		
			
			for (int i = 0; i < products.size(); i++) {
				productCode = (String) products.get(i);

				if (productCode.length() > 1) {
					pp = productCode.substring(0, 1);

					ps = productCode.substring(1, 2);
				} else {
					pp = productCode.substring(0, 1);
				}
				if (pp == null) {
					pp = "";
				}
				if (!distinctPrefixes.containsKey(pp)) {
					distinctPrefixes.put(pp, "");
					String productwithstarSuffix = pp + "*";

					if (!temp.containsKey(productwithstarSuffix)
					        && installBean
					                .isProductInstalled(pp)) {
						productsToReturn.add(productwithstarSuffix);
						temp.put(productwithstarSuffix, "");
					}
				}

				if (ps == null) 
					ps = "";
		
				String productKey = pp + ps;

				if (!temp.containsKey(productKey)
				        && installBean.isProductInstalled(
				                pp)) {
					productsToReturn.add(productKey);
					temp.put(productKey, "");
				}
			}

		} catch (Exception e) {
			throw WrapperException.wrap(e);
		} 
		
		if (productsToReturn.size() == 0)
			return installBean.getDefaultProductList();
		else
			return productsToReturn; 
	}
	*/

	// refactor plan key.
	/* @deprecated use getNextPlanKey(PlanCriteriaTO)
	public ArrayList getIssueStates(String companyCode, String product,
	        String planCode, boolean includeWIP) throws Exception {
		return planTableInfo.getIssueStates(companyCode, product, planCode,
				includeWIP);
	}

	public ArrayList getIssueStates(String companyCode, String product)
	        throws Exception {
		return planTableInfo.getIssueStates(companyCode, product, null, false);
	}


	public ArrayList getLinesOfBusiness(String companyCode, String product,
	        String planCode, String issueState, boolean includeWIP)
	        throws Exception {
		return planTableInfo.getLOB(companyCode, product, planCode,
		        issueState, includeWIP);
	}
	
	public ArrayList getPlanEffectiveDate(String companyCode, String product,
	        String planCode, String issueState, String lob, boolean includeWIP)
	        throws Exception {
		return planTableInfo.getEffDates(companyCode, product, planCode,
		        issueState, lob, includeWIP);
	}
	*/
	
	public Vector<String> getLOBCodes(String company) throws Exception {
		Vector<String> lobCodes = new Vector<String>();
		TableFilter filter = new TableFilter();
		if (company!=null && company.trim().length()>0)
			filter.setCompanyCode(company);
		Vector<Row> rows = environment.getTableDescMgr().getTableDescriptor("008").getTableRows(environment,filter);

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			String lobCode = row.getValueOf(FieldNames.T008X_LOB_CODE);
			if (!lobCodes.contains(lobCode)) 
				lobCodes.addElement(lobCode);
		}
		return lobCodes;
	}


	public Hashtable<String, String> getLOBCodesAndNames(String company) throws Exception {

		Hashtable<String, String> lobCodesAndNames = new Hashtable<String, String>();
		TableFilter filter = new TableFilter();
		if (company!=null && company.trim().length()>0)
			filter.setCompanyCode(company);
		Vector<Row> rows = environment.getTableDescMgr().getTableDescriptor("008").getTableRows(environment,filter); 

		for (int i = 0; i < rows.size(); i++) {
			Row row = rows.elementAt(i);
			String lobCode = row.getValueOf(FieldNames.T008X_LOB_CODE);
			if (!lobCodesAndNames.containsKey(lobCode)) 
				lobCodesAndNames.put(lobCode, row.getValueOf(FieldNames.T008X_LOB_NAME));
		}
		return lobCodesAndNames;
	}


}
