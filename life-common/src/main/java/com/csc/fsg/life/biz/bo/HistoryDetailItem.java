package com.csc.fsg.life.biz.bo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.csc.fsg.life.common.util.NumberHelper;

/* Modifications: CCCV-E923 */

public class HistoryDetailItem
{
	private String fieldName = "";
	private String displayName;
	private String value = "";


	public HistoryDetailItem(String name, boolean v)
	{
		fieldName = name;
		value = (v ? "Y" : "N");
	}

	public HistoryDetailItem(String name, Object v)
	{
		fieldName = name;
		if (v != null) {
			if (v instanceof Boolean) {
			    Boolean val = (Boolean)v;
			    value = val.booleanValue() ? "Y" : "N";
			} else if (v instanceof Double) {
				value = NumberHelper.formatNumber((Double)v);
			} else {
			    value = v.toString();
			}
		}
	}

	/**
	 * Builds a list of history detail items for the specified list.
	 */
	@SuppressWarnings("unchecked")
	public static List<HistoryDetailItem> buildList(String name, List l)
	{
		List<HistoryDetailItem> items = new ArrayList<HistoryDetailItem>();
		int count = 1;
		if ((l == null) || (l.size() == 0)) {
			String emptyItemName = name + "[1]";
			items.add(new HistoryDetailItem(emptyItemName, ""));
		}
		else {
			for(Iterator itt = l.iterator(); itt.hasNext(); count++) {
				Object nextItem = itt.next();
				String nextItemName = name + "[" + NumberHelper.zeroFill("" + count, 3) + "]";
				if (nextItem instanceof com.csc.fsg.life.biz.copyobject.CopyObject)
					items.addAll(((com.csc.fsg.life.biz.copyobject.CopyObject)nextItem).toDetailList(nextItemName));
				else
					items.add(new HistoryDetailItem(nextItemName, nextItem));
			}
		}
		return items;
	}

	public String getFieldName() { return fieldName; }
	public void setFieldName(String v) { fieldName = v; }
	
	public String getDisplayName() 
	{
		if (displayName == null) 
			return fieldName;
		return displayName; 
	}
	public void setDisplayName(String v) { displayName = v; }

	public String getValue() { return value; }
	public void setValue(String v) { value = v; }
}
