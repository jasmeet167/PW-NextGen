package com.csc.fsg.life.pw.io;

import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.transferobjects.*;
import com.csc.fsg.life.pw.util.Utils;
//import com.csc.fsg.life.pw.actions.wizards.PlanRowRetriever;
import com.csc.fsg.life.pw.avm.*;
import com.csc.fsg.life.pw.environment.*;
import com.csc.fsg.life.pw.log.PWServerLogManager;
import com.csc.fsg.life.pw.util.*;

/* Modifications: T0114, WMABASEIXI-2052, WMABASEIXI-2404, CCCV-E768, WMA-1373 */
// WMABASEIXI-2404 - added translationTable different than fromTable

public class TCSource {
	
	private static Log _log = PWServerLogManager.getLog(TCSource.class.getPackage().getName());
	
	private String selectColumns = null;
	private String fromTable = null;
	private String onCondition = null;
	private String translationTable = null;
	private String translationJoin = null;
	private String translationCol = null;
	private boolean applyPlanFilter = false;
	// WMA-1373: conditions on TC grandparent (e.g. T009X FUND_TYPE = 'L')
	private String translationJoinCond = null;
		
	private Map<String , Map<String, String>> av = new TreeMap<String , Map<String, String>>();
	
	public Map<String, String> getAVList(String envId, String company,PlanTO plan,Connection srcConn) throws Exception{
		
		String key = envId+"\t"+company;
		
		if (applyPlanFilter){
			 if (plan==null)
				 throw new Exception("Plan is required when plan filter is set on the list");
		 
			key = key+"\t"+plan.getPlanKey("\t");
		}
		
		Map<String, String> list = av.get(key);
		
		if (list==null){
			_log.debug("List not found in Cache. Query the table.Key is "+key);
			list =  new TreeMap<String , String>();
			populateAV(srcConn,plan,list,envId,company);
			_log.debug("Caching list for "+key);
			av.put(key, list);
		}else{
			_log.debug("Retrieved the list from cache.Key is "+key);
		}
		
		return list;
	}
	
	private void populateAV(Connection srcConn,PlanTO plan,Map<String, String> list,String envId,String company) throws Exception{
		
		// WMABASEIXI-2052 - if no source rows in subset, return empty AV list
		String tableSubset = null;
		if (applyPlanFilter && !plan.isEmpty()) {
			tableSubset = getTableSubset(plan);
			if ( tableSubset == null )
				return;
		}
		
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		
		String[] cols = selectColumns.split(",");
		
		boolean withDisplayVal = (cols.length==1 && translationCol!=null && translationCol.trim().length()>0);
		
		StringBuffer query = new StringBuffer();
		
		if ( withDisplayVal && (translationTable != null) ) {
			
			query.append("Select "+environment.getSchema()+"."+fromTable+ "." + selectColumns);
			query.append(", "+environment.getSchema()+"."+translationTable + "." + translationCol);
			
			query.append(" from "+environment.getSchema()+"."+fromTable);
			query.append(", " + environment.getSchema() + "." + translationTable);
			
			query.append(" where " +environment.getSchema()+"."+fromTable + ".company_code = '"+company+"' ");
			query.append(" and " +environment.getSchema()+"."+ translationTable + ".company_code = '"+company+"' ");
			
			if (applyPlanFilter && !plan.isEmpty())
				query.append(" AND " +environment.getSchema()+"."+ fromTable + ".table_subset = '"+ tableSubset +"' ");
					
			if (onCondition!=null && onCondition.trim().length()>0)
				query.append(" AND ( "+onCondition+") ");
			
			query.append(" and " +environment.getSchema()+"."+ fromTable + "." + selectColumns);
			query.append(" = " + environment.getSchema()+"."+translationTable + "." + translationJoin); 
			if (translationJoinCond!=null && translationJoinCond.trim().length()>0)
				query.append(" AND ("+ translationJoinCond + ")");
		} else {
			query.append("Select "+selectColumns);
			
			if (withDisplayVal)
				query.append(", "+translationCol);
			
			query.append(" from "+environment.getSchema()+"."+fromTable);
			
			query.append(" where company_code = '"+company+"' ");
			
			if (applyPlanFilter && !plan.isEmpty())
				query.append(" AND table_subset = '"+ tableSubset +"' ");
					
			if (onCondition!=null && onCondition.trim().length()>0)
				query.append(" AND ( "+onCondition+") ");
		}
		
		_log.debug(query.toString());
		
		Statement stmt = null;
		ResultSet rs = null;
		//boolean closeConn = false;
		try{
				
			stmt = srcConn.createStatement();
						
			rs = stmt.executeQuery(query.toString());
			
			if (withDisplayVal){
				while (rs.next()){
					String code = HighValueHandler.convertHVToAsterisk(rs.getString(1),envId).trim();
					if (code.length() == 0)	code = " ";
					String trans = rs.getString(2);
					if (isFundNumberColumn(cols[0])) 
						code = Utils.rightJustify(code, 8);
					list.put(code, trans);
				}
			}else{
				while (rs.next()){
					for (int i = 0; i < cols.length; i++) {
						String code = HighValueHandler.convertHVToAsterisk(rs.getString(i+1),envId).trim();
						if (code.length() == 0)	code = " ";
						if (isFundNumberColumn(cols[i]) )
							code = Utils.rightJustify(code, 8);
						list.put(code, code.trim());
					}
				}
			}
			
			traslateStateCodes(cols,list,envId,company);
			
			
		}finally{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
		//	if (closeConn)
			//	DBConnMgr.getInstance().releaseConnection(srcConn);
		}
	
	}
	
	
	private String getTableSubset(PlanTO plan) throws Exception{
	
		PlanCriteriaTO pcto = new PlanCriteriaTO(plan);
		pcto.setTablePtrId(TableDescriptorManager.getTableId(fromTable));
		pcto.setViewChanges(false);
		
		Environment environment = EnvironmentManager.getInstance().getEnvironment(plan.getEnvironment());
//		ArrayList<PlanRowTO> subsetRows = new PlanRowRetriever(environment).getPlanSubsetsFor(pcto);
//		// WMABASEIXI-2052 - if no source rows, return null
//		if ( subsetRows.size() > 0 )
//			return subsetRows.get(0).getTablePtrSubset();
		return null;
	}

	

	public void clearCache(String envId,String company){
		String cachekey = envId+"\t"+company;
		Iterator<String> iter = av.keySet().iterator();
		while (iter.hasNext()){
			String key = iter.next();
			if (key.startsWith(cachekey)){
				iter.remove();
				_log.debug("Cleared cache. Key "+cachekey+"  "+fromTable);
			}
		}
	}

	public String getSelectColumns() {
		return selectColumns;
	}

	public void setSelectColumns(String selectColumns) {
		this.selectColumns = selectColumns;
	}

	public String getFromTable() {
		return fromTable;
	}

	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}

	public String getOnCondition() {
		return onCondition;
	}

	public void setOnCondition(String onCondition) {
		this.onCondition = onCondition;
	}

	public String getTranslationCol() {
		return translationCol;
	}

	public void setTranslationCol(String translationCol) {
		this.translationCol = translationCol;
	}

	public String getTranslationTable() {
		return translationTable;
	}

	public void setTranslationTable(String translationTable) {
		this.translationTable = translationTable;
	}

	public String getTranslationJoin() {
		return translationJoin;
	}

	public void setTranslationJoin(String translationJoin) {
		this.translationJoin = translationJoin;
	}
	
	// WMABASE1101-14: conditions on TC grandparent (e.g. T009X FUND_TYPE = 'L')
	public String getTranslationJoinCond() {
		return translationJoinCond;
	}

	public void setTranslationJoinCond(String translationJoinCond) {
		this.translationJoinCond = translationJoinCond;
	}

	public boolean isApplyPlanFilter() {
		return applyPlanFilter;
	}

	public void setApplyPlanFilter(boolean applyPlanFilter) {
		this.applyPlanFilter = applyPlanFilter;
	}
	
	private boolean isFundNumberColumn(String col){
		return  ( col.startsWith("FUND_NUMBER") || col.endsWith("FUND_NUMBER"));
	}
	
	private void traslateStateCodes(String cols[],Map<String, String> map,String envId,String company) throws Exception{
		if (cols.length==1 && cols[0].equals("STATE_CODE")){
			
			Hashtable<String, String> stateCodes = AVManager.getAllowableValues(new AVKey(envId, company, null, null, null, null, cols[0]));
			
			for(Map.Entry<String,String> entry : stateCodes.entrySet()){
			  String key = entry.getKey();
			  if (map.containsKey(key)){
				  String text = stateCodes.get(key);
				  if (text!=null)
					  map.put(key,text);
			  }
			}
		}
		
	}
}
