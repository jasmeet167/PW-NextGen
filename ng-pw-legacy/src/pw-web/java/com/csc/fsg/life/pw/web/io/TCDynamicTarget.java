package com.csc.fsg.life.pw.web.io;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;

/* Modifications: ENH1053 */

abstract public class TCDynamicTarget
{
	private static Log log = PWServerLogManager.getLog(TCDynamicTarget.class.getPackage().getName());

	private String productPrefix = null;
	private String productSuffix = null;
	private String table = null;
	private String controlColumn = null;
	private String column = null;

	private Map<String, List<String[]>> avCache = new HashMap<String, List<String[]>>();

	public void setProductPrefix(String productPrefix)
	{
		this.productPrefix = productPrefix;
	}

	public String getProductPrefix()
	{
		return productPrefix;
	}

	public void setProductSuffix(String productSuffix)
	{
		this.productSuffix = productSuffix;
	}

	public String getProductSuffix()
	{
		return productSuffix;
	}

	public void setTable(String table)
	{
		this.table = table;
	}

	public String getTable()
	{
		return table;
	}

	public void setControlColumn(String controlColumn)
	{
		this.controlColumn = controlColumn;
	}

	public String getControlColumn()
	{
		return controlColumn;
	}

	public void setColumn(String column)
	{
		this.column = column;
	}

	public String getColumn()
	{
		return column;
	}

	public boolean isDynamicTarget()
	{
		return false;
	}

	protected boolean isInCache(String envId, String company, PlanTO plan, boolean applyPlanFilter)
	{
		String cacheKey = buildCacheKey(envId, company, plan, applyPlanFilter);
		return avCache.containsKey(cacheKey);
	}

	protected List<String[]> fetchFromCache(String envId, String company, PlanTO plan, boolean applyPlanFilter)
	{
		String cacheKey = buildCacheKey(envId, company, plan, applyPlanFilter);
		List<String[]> avList = avCache.get(cacheKey);

		if (avList == null)
			log.debug("List not found in Cache. Query the table. Key is " + cacheKey);
		else
			log.debug("Retrieved a list from cache. Key is " + cacheKey);

		return avList;
	}

	protected void putInCache(String envId, String company, PlanTO plan, List<String[]> avList)
	{
		String cacheKey = buildCacheKey(envId, company, plan, true);
		log.debug("Caching list for " + cacheKey);
		avCache.put(cacheKey, avList);
	}

	protected void clearCache(String envId, String company)
	{
		String partialCacheKey = buildCacheKey(envId, company, null, false);
		List<String> matchingKeys = new ArrayList<String>();

		for (String key : avCache.keySet())
			if (key.startsWith(partialCacheKey))
				matchingKeys.add(key);

		avCache.keySet().removeAll(matchingKeys);
	}

	private String buildCacheKey(String envId, String company, PlanTO plan, boolean applyPlanFilter)
	{
		if (applyPlanFilter)
			if (plan == null)
				throw new IllegalArgumentException("Plan is required when plan filter is set on the list");

		StringBuilder buf = new StringBuilder();
		buf.append(envId);
		buf.append('\t');
		buf.append(company);

		if (plan != null) {
			buf.append('\t');
			buf.append(plan.getPlanKey("\t"));
		}

		return buf.toString();
	}

	abstract public boolean isUsingTable(String tableName);

	abstract public List<String[]> getDynamicAvList(String envId, String company, PlanTO plan, Connection conn);
}
