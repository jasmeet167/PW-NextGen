/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

import java.sql.Connection;
import java.util.*;

import com.csc.fsg.life.pw.common.PolicyConstants;
import com.csc.fsg.life.pw.common.User;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;
//import com.csc.fsg.life.pw.web.controller.PWTask;

/* Modifications: T0103, T0091 ,HAVMENH, ENH961 ,T0120, T0129 */
// ENH961 - set status in PWTask

/**
 * Class TreeWriter
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class TreeWriter {

	public static String getStream(List<PlanCriteriaTO> planCriteriaList, Vector<String> companyCodes,
			boolean includeOrphans /*, PWTask task,User user*/) throws Exception {
		StringBuffer treeStream = new StringBuffer();
		
		Connection mfConn = null;
		Connection wipConn = null;

		PlanMergeAssistent tx = null;
		IndexMergeAssistent txa = null;
		
		try {
			PlanCriteriaTO firstPlan = planCriteriaList.get(0);
			String envId = firstPlan.getEnvironment();
			mfConn = DBConnMgr.getInstance().getConnection(envId,DBConnMgr.BUSINESS_RULES);
			wipConn = DBConnMgr.getInstance().getConnection(envId,DBConnMgr.APPL);
			
//			task.setStatus(0, "Reading Plan Table T000X");
			tx = new PlanMergeAssistent(wipConn, planCriteriaList, true);
			String view;
			if (firstPlan.isViewChanges()) 
				view = "with";
			else
				view = "without";
//			task.setStatus(0, "Reading Subset Index Table T000XA");
			txa = new IndexMergeAssistent(wipConn, firstPlan.toHashMap(), firstPlan.isLoadNP(), view, true);
						
			
			int tableAuth = Constants.NODE_ATTR_INQUIRY;
//			if (user.isPermitted(envId, null, PolicyConstants.UPDATE))
//				tableAuth = Constants.NODE_ATTR_UPDATE;
			
			treeStream.append("999999\t");                           
			treeStream.append("0\t");                                
			treeStream.append("0\t");                                
			treeStream.append(tableAuth);   
			treeStream.append("\t");
			treeStream.append(envId);                               
			treeStream.append("\n");
			
			CompanyWriter cw = new CompanyWriter();
			// SPR 6477
			List<String> companyCodesList	= new ArrayList<String>();
			companyCodesList.addAll(companyCodes);
			Collections.sort(companyCodesList);
			for (int i = 0; i < companyCodesList.size(); i++) {
				String company = companyCodesList.get(i);
//				task.setStatus(0, "Processing " + company + " company");
				treeStream.append(
					cw.getStream(null, envId, company, firstPlan.getProductPrefix(),
							 	wipConn, mfConn, firstPlan.isViewChanges(),
							 	includeOrphans, tx, txa /*, task,user*/) );
			}
		} catch (Exception e) {
			throw e;
		}finally{
			
			if (tx!=null)
				tx.clean(wipConn);
			if (txa!=null)
				txa.clean(wipConn);
			
			DBConnMgr.getInstance().releaseConnection(wipConn);
			DBConnMgr.getInstance().releaseConnection(mfConn);

		}

	//	System.out.println(treeStream.toString());
		return treeStream.toString();
		
	}

	// refactor plan key
	/*
	public static String getStream(ArrayList keys, String view,boolean loadNP)	throws Exception {

		StringBuffer treeStream = new StringBuffer();

		HashMap filter = (HashMap) keys.get(0);

		Connection mfConn = null;
		Connection wipConn = null;

		PlanMergeAssistent tx = null;
		IndexMergeAssistent txa = null;

		try {

			String env = (String) filter.get("schema");
			Environment environment = EnvironmentManager.getInstance().getEnvironment(env);
			mfConn = DBConnMgr.getInstance().getConnection(env,DBConnMgr.BUSINESS_RULES);
			wipConn = DBConnMgr.getInstance().getConnection(env,
			        DBConnMgr.APPL);

			tx = new PlanMergeAssistent(wipConn, keys, loadNP, view);
			txa = new IndexMergeAssistent(wipConn, (HashMap) keys.get(0),
			        loadNP, view);

			treeStream.append("999999\t");
			treeStream.append("0\t");
			treeStream.append("0\t");
			treeStream.append(Constants.NODE_ATTR_MANAGER);
			treeStream.append("\t");
			treeStream.append(env);
			treeStream.append("\n");

			HashMap _treeKey = (HashMap) keys.get(0);
			ArrayList al = new ArrayList();

			Object o = _treeKey.get("company_code");
			Vector v = null;
			if (o instanceof String) {
				v = new Vector();
				v.add(o);
			} else if (o instanceof Vector) {
				v = (Vector) _treeKey.get("company_code");
			}

			al.addAll(v);

			boolean isWithChanges = view.equalsIgnoreCase("with");
			String prefix = (String) filter.get("product_prefix");
			HashMap map = (HashMap) keys.get(0);
			boolean includeOrphans = false;
			if (map.get("orphans")!=null)
			 includeOrphans = new Boolean(map.get("orphans").toString()).booleanValue();
			
			CompanyWriter cw = new CompanyWriter(env);

			for (int i = 0; i < al.size(); i++) {

				String company = (String) al.get(i);
				treeStream.append(cw.getStream(env, company, prefix, wipConn,
				        mfConn, isWithChanges,includeOrphans,tx,txa));
			}

		} catch (Throwable e) {
			throw WrapperException.wrap(e);
		} finally {

			if (tx != null)
				tx.clean(wipConn);
			if (txa != null)
				txa.clean(wipConn);

			DBConnMgr.getInstance().releaseConnection(wipConn);
			DBConnMgr.getInstance().releaseConnection(mfConn);

		}

		return treeStream.toString();
	}
	*/
}
