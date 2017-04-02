/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

import java.sql.*;
import java.util.*;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.config.*;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.rest.model.TreeNodeLazyType;

/* Modifications: T0103, T0091 ,HAVMENH, CCCV-E501 ,T0120, WMABASEIXI-4515, T0129, ENH1063.06, WMA-1209 */

/**
 * Class CompanyWriterThread
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class CompanyWriter {
	private static final String TAB = "\t";

	private static final String NEW_LINE = "\n";

	public String getStream(TreeNodeLazyType lazyType, String env, String company, String prefix,
	        Connection wipConn, Connection mfConn, boolean viewChanges,
	        boolean includeOrphans,PlanMergeAssistent pm, IndexMergeAssistent im)
	        throws Exception {

		StringBuffer treeStream = new StringBuffer();

		// WMABASEIXI-4515 - check for disabled
		int tableAuth = Constants.NODE_ATTR_DISABLED;
//		if (user.isPermitted(env, company, PolicyConstants.VIEW)) {
//			tableAuth = Constants.NODE_ATTR_INQUIRY;
//			if (user.isPermitted(env, company, PolicyConstants.UPDATE))
				tableAuth = Constants.NODE_ATTR_UPDATE;
//		}
		
		// Return the Company information only if not lazy-loading a branch in Business Rule Tree
		if (lazyType == null) {
			treeStream.append("999999" + TAB + "1" + TAB + "3" + TAB).append(
					tableAuth).append(TAB);
			
			Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
			String companyName = environment.getAssistent() .getCompanyCodesAndNames().get(company);
			if (companyName==null)
				companyName = company;
			treeStream.append(companyName).append(TAB);
			treeStream.append(company).append(NEW_LINE);
		}
		
		if (lazyType == TreeNodeLazyType.C) {
			CommonTablesWriter ctw = new CommonTablesWriter(env, company,
			        viewChanges, mfConn, wipConn);
			treeStream.append("999999" + TAB + "2" + TAB + "7" + TAB).append(
					tableAuth).append("\tCommon\n");
			treeStream.append(ctw.getStream());
			return treeStream.toString();
		}

		ArrayList al = getProductsList(company, wipConn);

		String system = EnvironmentManager.getInstance().getEnvironment(env).getProductSystem();
		InstallConfigBean installBean = ProductManager.getInstance().getProduct(system).getInstallBean();
		if (al.isEmpty()){
			al = installBean.getDefaultProductList();
		}

		tableAuth = Constants.NODE_ATTR_DISABLED;
//		if (user.isPermitted(env, company, PolicyConstants.VIEW)) {
//			tableAuth = Constants.NODE_ATTR_INQUIRY;
//			if (user.isPermitted(env, company, "00X", PolicyConstants.UPDATE))
				tableAuth = Constants.NODE_ATTR_UPDATE;
//		}

		ProductWriter pw = new ProductWriter(pm, im);

		TreeMap<String, ArrayList<PlanBuffer>> map = new TreeMap<String, ArrayList<PlanBuffer>>();
		PlanBuffer planBuffer = null;

		for (int i = 0; i < al.size(); i++) {
			String productCode = ((String) al.get(i));
			String pp = productCode.substring(0, 1);

			if (installBean.isProductInstalled(productCode.substring(0, 1))) {
				String display = productCode + " Product";
				if ( productCode.equalsIgnoreCase("NP") )
					display = "NP Product";
				else if ( productCode.startsWith("H") )
					display = "H* Product";
				planBuffer = pw.getPlanListStream(lazyType, env, company, productCode, wipConn, viewChanges);

				if (map.containsKey(pp)) {
					map.get(pp).add(planBuffer);
				} else {
					ArrayList<PlanBuffer> list = new ArrayList<PlanBuffer>();
					list.add(planBuffer);
					map.put(pp, list);
				}
			}
		}

		writeProducts(env,company,treeStream, map,prefix,wipConn,tableAuth);
		if (includeOrphans)
			writeOrphans(env, company, prefix, wipConn, treeStream, pm, im);
		return treeStream.toString();
	}

	private ArrayList<String> getProductsList(String company, Connection wipConn)
	        throws Exception, SQLException {

		String query = "SELECT DISTINCT CONCAT(PRODUCT_PREFIX ,PRODUCT_SUFFIX) "
		        + "FROM SESSION.MERGEDX where company_code='" + company + "'";

		ArrayList<String> al = new ArrayList<String>();

		ResultSet rs = null;
		Statement stmt = null;
		try {
			stmt = wipConn.createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next())
				al.add(rs.getString(1));
		} catch (Exception e) {
			throw e;
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		}
		return al;
	}

	private synchronized void writeProducts(String env,String company,StringBuffer companyTree, 
				TreeMap<String, ArrayList<PlanBuffer>> map,String reqPrefix,Connection wipConn,int folderAuth)
	        throws Exception {

		Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
		// T0103 - refactor plan key: check for zero length prefix
		if (reqPrefix==null || reqPrefix.length() == 0)
			reqPrefix= "*";
		
		ArrayList<PlanBuffer> list = null;
		if ( reqPrefix.equals("N") ) {
			// WMA-1209 put PDF last so any orphans are attached to it
			companyTree.append("999999\t2\t15\t").append(
					folderAuth).append("\tCommon Coverage\n");
			list = map.get("H");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getHcc());
				}
			}

			companyTree.append("999999\t2\t15\t").append(
					folderAuth).append("\tPremium Deposit Fund Plans\n");
			list = map.get("N");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getArch());
				}
			}
		} else {
			companyTree.append("999999\t2\t15\t").append(
					folderAuth).append("\tPremium Deposit Fund Plans\n");
			list = map.get("N");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getArch());
				}
			}
	
			companyTree.append("999999\t2\t15\t").append(
					folderAuth).append("\tCommon Coverage\n");
			list = map.get("H");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getHcc());
				}
			}
		}

		String base = "999999\t3\t8\t" + folderAuth
		        + "\tBase Plans\n";
		String rider = "999999\t3\t9\t" + folderAuth
		        + "\tRider Plans\n";
		String payout = "999999\t3\t25\t" + folderAuth
		        + "\tPayout Plans\n";

		String product = EnvironmentManager.getInstance().getEnvironment(env).getProductSystem();
		InstallConfigBean installBean = ProductManager.getInstance().getProduct(product).getInstallBean();
		
		if (installBean.isProductInstalled(Constants.ANNUITY_TABLE_PREFIX) && 
				(reqPrefix.equals("*")|| reqPrefix.equals(Constants.ANNUITY_TABLE_PREFIX)) ) {
			companyTree.append("999999\t2\t4\t").append(
					folderAuth).append("\tAnnuity\n");
			companyTree.append(base);
			list = map.get("A");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getBase());
				}
			}
			
			// CCCV-E501 - check v1mode, not productSystem
			ProductObject productObj = ProductManager.getInstance().getProduct(environment.getProductSystem());
			if (!productObj.getInstallBean().isV1mode()){
				companyTree.append(payout);
				list = map.get(Constants.ANNUITY_TABLE_PREFIX);
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {
						PlanBuffer pb = list.get(i);
						companyTree.append(pb.getPayoutPlans());
					}
				}
			}
		}

		if (installBean.isProductInstalled(Constants.UL_TABLE_PREFIX)&& 
				(reqPrefix.equals("*")|| reqPrefix.equals(Constants.UL_TABLE_PREFIX))) {
			companyTree.append("999999\t2\t5\t").append(
					folderAuth).append("\tUniversal Life\n");
			companyTree.append(base);
			list = map.get(Constants.UL_TABLE_PREFIX);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getBase());
				}
			}
			companyTree.append(rider);
			list = map.get("U");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getRider());
				}
			}
		}

		if (installBean.isProductInstalled(Constants.TRAD_TABLE_PREFIX)&& 
				(reqPrefix.equals("*")|| reqPrefix.equals(Constants.TRAD_TABLE_PREFIX))) {
			companyTree.append("999999\t2\t6\t").append(
					folderAuth).append("\tTraditional Life\n");
			companyTree.append(base);
			list = map.get(Constants.TRAD_TABLE_PREFIX);
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getBase());
				}
			}
			companyTree.append(rider);
			list = map.get("T");
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					PlanBuffer pb = list.get(i);
					companyTree.append(pb.getRider());
				}
			}
		}

	}

	private void writeOrphans(String env,String company,String productPrefix,
			Connection wipConn, StringBuffer treeStream,PlanMergeAssistent tx,
			IndexMergeAssistent txa) throws Exception{
		// Searching for Orphan Tables
		cleanNRecreateMergedView(env,company,productPrefix,wipConn,tx,txa);
		OrphanTreeWriter otw = new OrphanTreeWriter();
		otw.writeOrphans(env, company, productPrefix, wipConn,treeStream);
	}
	
	private void cleanNRecreateMergedView(String env,String company,String productPrefix,Connection wipConn,PlanMergeAssistent tx, IndexMergeAssistent txa) throws Exception{
		if (tx != null)
			tx.clean(wipConn);
		if (txa != null)
			txa.clean(wipConn);
		
		// refactor plan key
		PlanCriteriaTO planCriteria = new PlanCriteriaTO();
		planCriteria.setEnvironment(env);
		planCriteria.setCompanyCode(company);
		planCriteria.setProductPrefix(productPrefix);
		planCriteria.setViewChanges(true);
		new PlanMergeAssistent(wipConn, planCriteria);

		Vector<String> compCodesVector = new Vector<String>();
		compCodesVector.add(company);

		HashMap treeKey = new HashMap();
		treeKey.put("schema", env);
		treeKey.put("company_code", compCodesVector);
		treeKey.put("product_prefix", productPrefix);
		new IndexMergeAssistent(wipConn, treeKey, true,"with");
	}

}
