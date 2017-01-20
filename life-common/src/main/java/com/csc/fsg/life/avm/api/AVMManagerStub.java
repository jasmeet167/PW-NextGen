package com.csc.fsg.life.avm.api;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import com.csc.fsg.life.avm.biz.dao.model.AVVALModel;
import com.csc.fsg.life.avm.biz.dao.model.AllowableValuesField;
import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.avm.lib.exceptions.AVMException;

/* Modifications: T0143 */

/**
 * This is the stub class used when AVM access is turned off. it provides a
 * concrete subclass of <code>AVMManager</code>, which fulfills syntactic
 * contract imposed by the superclass, but does no work, and always returns null
 * and/or empty values.
 */
public class AVMManagerStub
	extends AVMManager
{
	protected AVMManagerStub(AVMConfigBean bean)
	{
		super(bean);
	}

	@Override
	public AVMCacheHandler getAVMCacheHandler()
	{
		String appKey = avmConfigBean.getApplicationKey();
		String envKey = avmConfigBean.getEnvironmentKey();
		return new AVMCacheHandler(appKey, envKey, false, avmConfigBean);
	}

	@Override
	public IFieldAllowableValues getFieldAllowableValues(AccessKeys accessKeys, int sortingStyle)
		throws AVMException
	{
		return new IFieldAllowableValues() {
			public String getFieldType()
			{
				return null;
			}

			public AllowableValues getAllowableValues()
			{
				return new ListAllowableValues(new ArrayList<AVVALModel>());
			}

			public String getFieldDefault()
			{
				return null;
			}
		};
	}

	@Override
	public List<AllowableValuesField> getListOfPossibleReferences(String fieldName)
		throws AVMException
	{
		return new ArrayList<AllowableValuesField>();
	}

	@Override
	public Hashtable<String, PageInfo> getPageInfo(String applId, String envrnmntId)
		throws AVMException
	{
		return new Hashtable<String, PageInfo>();
	}

	@Override
	protected String getKeyEquivalent(AccessKeys aKeys, String idVal, int maskVal)
		throws AVMException
	{
		return null;
	}

	@Override
	public ArrayList<AVMIdDescPair> getEnvironmentsForAppID(String appId)
	{
		return new ArrayList<AVMIdDescPair>();
	}

	@Override
	public ArrayList<AVMIdDescPair> getEnvironmentsForAppKey(String appKey)
	{
		return new ArrayList<AVMIdDescPair>();
	}

	@Override
	public ArrayList<AVMIdDescPair> getCompaniesForAppID(String appId)
	{
		return new ArrayList<AVMIdDescPair>();
	}

	@Override
	public Hashtable<String, Object> getPolicyAdminHash()
	{
		return new Hashtable<String, Object>();
	}

	@Override
	public Object getPolicyAdminForAppl(String applName)
	{
		return null;
	}

	@Override
	public void setPolicyAdminHash(Hashtable<String, Object> policyAdminHash)
	{
	}

	@Override
	public void setPolicyAdminForAppl(String applName, Object polcyAdmin)
	{
	}
}
