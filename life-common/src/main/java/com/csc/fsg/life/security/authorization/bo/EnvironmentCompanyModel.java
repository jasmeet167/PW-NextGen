package com.csc.fsg.life.security.authorization.bo;

import com.csc.fsg.life.context.UserContextAware;

/* Modifications: ENH1220 */

public interface EnvironmentCompanyModel
	extends UserContextAware
{
	public String getEnvironment();

	public void setEnvironment(String environment);

	public String getCompanyCode();

	public void setCompanyCode(String companyCode);
}
