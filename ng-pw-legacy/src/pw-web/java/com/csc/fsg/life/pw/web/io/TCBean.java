package com.csc.fsg.life.pw.web.io;

import java.sql.Connection;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.web.avm.AVKey;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;

/* Modifications: T0114, ENH915, ENH1057, ENH1053 */

public class TCBean {
	private Log _log = PWServerLogManager.getLog(TCBean.class.getPackage().getName());
	
	private static TCBean tcObject = null;
	
	public static TCBean getInstance() {
		if (tcObject==null)
			tcObject = new TCBean();
		return tcObject;
	}
	
	private TCBean() {
		tcObject = this;
	}
	
	private List<TCTarget> targetList = null;
	private List<TCDynamicTarget> dynamicTargetList = null;

	/** Send null if field is not defined in constraints as empty lists can exist*/
	public  Hashtable<String, String> getAV(AVKey key,PlanTO plan,Connection conn,StringBuffer sourceName) 
		throws Exception 
	{
		Iterator<TCTarget> iter = targetList.iterator();
		List<TCTarget> matchedTargetSet = new ArrayList<TCTarget>();
		while (iter.hasNext()){
			TCTarget target = iter.next();
			if (target.getTable().equals(key.getTable()) && target.getColumn().equals(key.getFieldName()))
				matchedTargetSet.add(target);
		}
		
		if (matchedTargetSet.isEmpty()){
			_log.debug("Field not defined in Table Constraints "+key.toString());
			return null;
		} else if (matchedTargetSet.size()==1){
			_log.debug("Returning List for "+key.toString());
			sourceName.append(matchedTargetSet.get(0).getSource().getFromTable());
			return new Hashtable<String, String>(matchedTargetSet.get(0).getAVList(key.getEnvironment(), key.getCompany(), plan, conn));
		} else{
			_log.debug("The lists are product specific.. ");
			String pp = key.getProduct();
			// ENH915 - First see if there is a prefix/suffix match
			if (plan != null) {
				String suffix = plan.getProductSuffix().trim();
				for (TCTarget target : matchedTargetSet) {
					if ( target.getProductPrefix().equals(pp) && suffix.equals(target.getProductSuffix()) ) {
						sourceName.append(target.getSource().getFromTable());
						return new Hashtable<String, String>(target.getAVList(key.getEnvironment(), key.getCompany(), plan, conn));
					}
				}
			}
			
			// Now check just for prefix
			for (TCTarget target : matchedTargetSet) {
				if (target.getProductPrefix().equals(pp) && (target.getProductSuffix() == null) ) {
					sourceName.append(target.getSource().getFromTable());
					return new Hashtable<String, String>(target.getAVList(key.getEnvironment(), key.getCompany(), plan, conn));
				}
			}
			_log.debug("Product Prefix not matched.. "+key.toString());
			return null;
		}
	}

	public List<String[]> getDynamicAV(AVKey key, PlanTO plan, Connection conn, String controlColumn)
	{
		List<TCDynamicTarget> matchedTargetList = new ArrayList<TCDynamicTarget>();

		for (TCDynamicTarget dynamicTarget : dynamicTargetList)
			if (dynamicTarget.getTable().equals(key.getTable()) 
			 && dynamicTarget.getControlColumn().equals(controlColumn)
			 && dynamicTarget.getColumn().equals(key.getFieldName()))
				matchedTargetList.add(dynamicTarget);

		if (matchedTargetList.isEmpty()) {
			_log.debug("Field not defined in Table Constraints " + key.toString());
			return null;
		}
		else if (matchedTargetList.size() == 1) {
			_log.debug("Returning List for " + key.toString());
			TCDynamicTarget dynamicTarget = matchedTargetList.get(0);
			return dynamicTarget.getDynamicAvList(key.getEnvironment(), key.getCompany(), plan, conn);
		}
		else {
			String pp = key.getProduct();

			// First see if there is a prefix/suffix match
			if (plan != null) {
				String suffix = plan.getProductSuffix().trim();
				for (TCDynamicTarget dynamicTarget : matchedTargetList) {
					if (dynamicTarget.getProductPrefix().equals(pp) 
					 && suffix.equals(dynamicTarget.getProductSuffix()))
						return dynamicTarget.getDynamicAvList(key.getEnvironment(), key.getCompany(), plan, conn);
				}
			}

			// Now check just for prefix
			for (TCDynamicTarget dynamicTarget : matchedTargetList) {
				if (dynamicTarget.getProductPrefix().equals(pp) 
				&& (dynamicTarget.getProductSuffix() == null))
					return dynamicTarget.getDynamicAvList(key.getEnvironment(), key.getCompany(), plan, conn);
			}

			_log.debug("Product Prefix not matched.. " + key.toString());
			return null;
		}
	}

	public List<TCTarget> getTargetList() {
		return targetList;
	}

	public void setTargetList(List<TCTarget> targetList) {
		this.targetList = targetList;
	}
	
	public void setDynamicTargetList(List<TCDynamicTarget> dynamicTargetList)
	{
		this.dynamicTargetList = dynamicTargetList;
	}
	
	public void clearCache(String envId,String company,String table){
		Iterator<TCTarget> iter = targetList.iterator();
		while (iter.hasNext()){
			TCTarget target = iter.next();
			if (target.getSource().getFromTable().equals(table.trim()))
				target.getSource().clearCache(envId.trim(), company.trim());
		}

		for (TCDynamicTarget dynamicTarget : dynamicTargetList)
			if (dynamicTarget.isUsingTable(table.trim()))
				dynamicTarget.clearCache(envId.trim(), company.trim());
	}
	
	public void clearCache(Set<String> appliedTables){
		Iterator<String> iter = appliedTables.iterator();
		while (iter.hasNext()){
			String data[]  = iter.next().split("\t");
			clearCache(data[0], data[1],data[2]);
		}
	}
}
