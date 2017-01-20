package com.csc.fsg.life.security.authorization.bo;

import com.csc.fsg.life.biz.bo.BaseBusinessObject;

/* Modifications: ENH1220 */

public class EnvironmentCompanyModelImpl
	extends BaseBusinessObject
	implements EnvironmentCompanyModel
{
	private static final long serialVersionUID = -6387137776292929016L;

	private String environment = null;
	private String companyCode = null;

	/**
	 * Default constructor
	 */
	public EnvironmentCompanyModelImpl()
	{
		super();
	}

	/**
	 * Creates a new EnvironmentCompanyModelImpl object as a clone of the
	 * specified model.
	 * 
	 * @param model
	 *        the model object to copy.
	 */
	public EnvironmentCompanyModelImpl(EnvironmentCompanyModel model)
	{
		environment = model.getEnvironment();
		companyCode = model.getCompanyCode();
	}

	public String getEnvironment()
	{
		return environment;
	}

	public void setEnvironment(String environment)
	{
		this.environment = environment;
	}

	public String getCompanyCode()
	{
		return companyCode;
	}

	public void setCompanyCode(String companyCode)
	{
		this.companyCode = companyCode;
	}
}
