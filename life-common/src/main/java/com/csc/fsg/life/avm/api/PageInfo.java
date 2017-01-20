package com.csc.fsg.life.avm.api;

import java.util.Hashtable;

/**
 * Bean to hold a page description and a Hash table of
 * field names/descriptions.
 */
public class PageInfo {
	private String _pageDesc;
	private Hashtable<String, String> _fieldInfo = new Hashtable<String, String>();

	/**
	   Returns the page description.
	   @return the page description.
	   @see #setPageDesc
	 **/
	public String getPageDesc()
	{
		return _pageDesc;
	}
	
	/**
	   Sets the page description.
	   @param desc the page description.
	   @see #getPageDesc
	 **/
	public void setPageDesc(String desc)
	{
		_pageDesc = desc;
	}
	
	/**
	   Returns the field information for this page.
	   @return the field information for this page.
	   @see #setFieldInfo
	 **/
	public Hashtable<String, String> getFieldInfo()
	{
		return _fieldInfo;
	}
	
	/**
	   Sets the field information for this page.
	   @param ht the field information for this page.
	   @see #getFieldInfo
	 **/
	public void setFieldInfo(Hashtable<String, String> ht)
	{
		_fieldInfo = ht;
	}
}
