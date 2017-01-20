package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.csc.fsg.life.dao.dataaccessor.DAOUtils;

/* Modifications: T0140 */

/**
   Helper class used to create comma separated list of applications,
   and environments.
 */
public class AVMCacheCleanSQLhelper
{
    private String applicationKey      = "";
    private String multipleEnvsKey     = "";  
    private String cacheIndVal         = "";  
    private List<String> prepEnvironmentList = new ArrayList<String>();
	
	/**
	   Creates the helper class off a list of parameters.  The list contains the 
	   following parameters:
	   <OL><LI>Application key</LI>
	   <LI>Environment list as string </LI>
	   <LI> cache indicator as Long </LI>
	   </OL>
	**/
	public AVMCacheCleanSQLhelper(ArrayList<?> aArrayList)
	{
		try{
			aArrayList 		= (ArrayList<?>)aArrayList.clone();
			applicationKey	= (String)aArrayList.get(0);
			multipleEnvsKey = (String)aArrayList.get(1);
			cacheIndVal		=  ((Long)aArrayList.get(2)).toString();

			DAOUtils.validString(applicationKey);
			DAOUtils.validString(cacheIndVal);
			
			StringTokenizer stz = new StringTokenizer(multipleEnvsKey,",");
			while(stz.hasMoreTokens()) {
				// Get the next token and remove quotes if present.
				String tkn = stz.nextToken();
				tkn = tkn.trim();
				if (tkn.startsWith("'"))
					tkn = tkn.substring(1, tkn.length()-1);

				// Validate the token for invalid SQL characters.
				DAOUtils.validString(tkn);

				// Build the string.
				prepEnvironmentList.add(tkn);
			}
			
		}catch(Exception exp){
			throw (new RuntimeException(exp.getMessage()));	
		}

	}

	/**
	   Gets the MultipleEnvsKey.
	   @return The MultipleEnvsKey value.
	   @see #setMultipleEnvsKey
	 */
	public String getMultipleEnvsKey() {
		return multipleEnvsKey;
	}

	/**
	   Gets the PrepEnvironment list.  List of environments to use.
	   @return The PrepEnvironmentString value.
	   @see #setPrepEnvironmentList
	 */
	public List<String> getPrepEnvironmentList() {
		return prepEnvironmentList;
	}

	/**
	   Returns a parameter string with replacements for each environment.
	   @return  a parameter string with replacements for each environment.
	**/
	public String getPrepEnvironmentParamString()
	{
		StringBuffer prepEnvironmentString = new StringBuffer();
		for(int i = 0; i < prepEnvironmentList.size(); i++)
			prepEnvironmentString.append(", ? ");
		return prepEnvironmentString.toString();
	}

	/**
	   Sets the MultipleEnvsKey.
	   @param string The new MultipleEnvsKey value.
	   @see #getMultipleEnvsKey
	**/
	public void setMultipleEnvsKey(String string) {
		multipleEnvsKey = string;
	}

	/**
	   Sets the PrepEnvironmentList.
	   @param list The new PrepEnvironmentList value.
	   @see #getPrepEnvironmentList
	**/
	public void setPrepEnvironmentList(List<String> list) {
		prepEnvironmentList = list;
	}

	/**
	   Gets the ApplicationKey.
	   @return The ApplicationKey value.
	   @see #setApplicationKey
	*/
	public String getApplicationKey() {
		return applicationKey;
	}

	/**
	   Gets the CacheIndVal.
	   @return The CacheIndVal value.
	   @see #setCacheIndVal
	 */
	public String getCacheIndVal() {
		return cacheIndVal;
	}

	/**
	   Sets the ApplicationKey.
	   @param string The new ApplicationKey value.
	   @see #getApplicationKey
	**/
	public void setApplicationKey(String string) {
		applicationKey = string;
	}

	/**
	   Sets the CacheIndVal.
	   @param string The new CacheIndVal value.
	   @see #getCacheIndVal
	**/
	public void setCacheIndVal(String string) {
		cacheIndVal = string;
	}
}	

