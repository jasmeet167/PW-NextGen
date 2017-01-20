package com.csc.fsg.life.pw.io;

import java.sql.Connection;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.transferobjects.PlanTO;
import com.csc.fsg.life.pw.avm.AVKey;
import com.csc.fsg.life.pw.log.PWServerLogManager;

/* Modifications: T0114, WMABASEIXI-2052, ENH915 */
// ENH915 - added product suffix

public class TCTarget {
	
	private static Log _log = PWServerLogManager.getLog(TCTarget.class.getPackage().getName());
	
	private String productPrefix = null;
	private String productSuffix = null;
	private String table = null;
	private String column = null;
	private TCSource source = null;
	private String addValues = null;
	private String removeValues = null;
	
	public Map<String, String> getAVList(String envId, String company,PlanTO plan,Connection srcConn) throws Exception{
		
		Map<String, String> av = source.getAVList(envId,company,plan,srcConn);
		
		// WMABASEIXI-2052 - only add/remove values if list is not empty
		if ( av.size() > 0 ) {
			if (addValues!=null && addValues.trim().length()>0){
				String[] pairs = addValues.split(",");
				for (int i = 0; i < pairs.length; i++) {
					if (pairs[i].indexOf("-")!=-1){
						String[] coreNDisplay = pairs[i].split("-");
						av.put(coreNDisplay[0], coreNDisplay[1]);
					}else{
						av.put(pairs[i],pairs[i]);
					}
				}
			}
			
			if (removeValues!=null && removeValues.trim().length()>0){
				String[] pairs = removeValues.split(",");
				for (int i = 0; i < pairs.length; i++) 
					av.remove(pairs[i]);
			}
		}
		
		return av;	
	}

	public String getProductPrefix() {
		return productPrefix;
	}


	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}

	public String getProductSuffix() {
		return productSuffix;
	}


	public void setProductSuffix(String productSuffix) {
		this.productSuffix = productSuffix;
	}


	public String getTable() {
		return table;
	}


	public void setTable(String table) {
		this.table = table;
	}


	public String getColumn() {
		return column;
	}


	public void setColumn(String column) {
		this.column = column;
	}


	public TCSource getSource() {
		return source;
	}


	public void setSource(TCSource source) {
		this.source = source;
	}


	public String getAddValues() {
		return addValues;
	}


	public void setAddValues(String addValues) {
		this.addValues = addValues;
	}


	public String getRemoveValues() {
		return removeValues;
	}


	public void setRemoveValues(String removeValues) {
		this.removeValues = removeValues;
	}

	
}
