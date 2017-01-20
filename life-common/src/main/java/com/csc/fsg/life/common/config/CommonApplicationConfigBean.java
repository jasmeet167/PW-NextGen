package com.csc.fsg.life.common.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* Modifications: T0120, ENH1019, ENH1172, ENH1220 */

/**
   Common configuration bean for application that use environments.
 */
public class CommonApplicationConfigBean {
	
	private Map<String, MyBatisApplicationEnvironmentBean> environments = new TreeMap<>();		
    protected List<String> supportedProducts = new ArrayList<String>();
    protected boolean useCompanyCodeAsAttrInSecurity = false;
    protected boolean useEnvIdAsAttrInSecurity = false;
	protected boolean isMaskingEnabled = false;
	private String defaultCompanyCode = null;
    protected Map<String, String> errorOverrides;

	
	private static CommonApplicationConfigBean applicationConfigBean = null;
    
    
    
    /**
     * Typically called by an IOC container such as Spring.  Set
     * the instance variable to allow access to this class as a
     * singleton through the getInstance method.
     * 
     * @see #getInstance()
     */
    public CommonApplicationConfigBean()
    {
        applicationConfigBean = this;
    }
    
    public static CommonApplicationConfigBean getInstance()
    {
        return applicationConfigBean;
    }
    
	/**
	 * Returns the Environments.
	 * 
	 * @return the Environments value.
	 * @see #setEnvironments
	 */
	public Map<String, MyBatisApplicationEnvironmentBean> getEnvironments() {
		return environments;
	}

	/**
	 * Sets the Environments.
	 * 
	 * @param environments the new Environments value.
	 * @see #getEnvironments
	 */
	public void setEnvironments(Map<String, MyBatisApplicationEnvironmentBean> environments) {
		this.environments = environments;
	}

	/**
	 * Gets an environment by id.
	 * 
	 * @param key the ID for the desired environment.
	 * @return the environment or null if not present.
	 */
	public ApplicationEnvironmentBean getEnvironment(String key)
	{
		return (ApplicationEnvironmentBean)environments.get(key);
	}

	
	public List<String> getSupportedProducts()
	{
	    return supportedProducts;
	}

	public void setSupportedProducts(List<String> supportedProducts)
	{
	    this.supportedProducts = supportedProducts;
	}
	
	/**
	 * Get error overrides
	 */
	public Map<String, String> getErrorOverrides()
	{
	    return errorOverrides;
	}

	/**
	 * Set error overrides
	 * @param errorOverrides
	 */
	public void setErrorOverrides(Map<String, String> errorOverrides)
	{
	    this.errorOverrides = errorOverrides;
	}

	/**
	 * Get Error Override flag for particular system 
	 * @param key
	 * @return errorOverride
	 */
	public String getErrorOverride(String key)
	{
		String eo = errorOverrides.get(key);
		if (eo != null && 
				(eo.equalsIgnoreCase("Y") || eo.equalsIgnoreCase("N"))) {
			return eo;
		}
		return null;
	}
	
	public boolean isProductSupported(String product)
	{
	    return true;
	}

    public boolean isUseCompanyCodeAsAttrInSecurity() {
        return useCompanyCodeAsAttrInSecurity;
    }

    public void setUseCompanyCodeAsAttrInSecurity(
            boolean useCompanyCodeAsAttrInSecurity) {
        this.useCompanyCodeAsAttrInSecurity = useCompanyCodeAsAttrInSecurity;
    }

    public boolean isUseEnvIdAsAttrInSecurity() {
        return useEnvIdAsAttrInSecurity;
    }

    public void setUseEnvIdAsAttrInSecurity(boolean useEnvIdAsAttrInSecurity) {
        this.useEnvIdAsAttrInSecurity = useEnvIdAsAttrInSecurity;
    }

	public void setDefaultCompanyCode(String defaultCompanyCode) {
		this.defaultCompanyCode = defaultCompanyCode;
	}

	public String getDefaultCompanyCode() {
		return defaultCompanyCode;
	}
	
	public boolean isMaskingEnabled() {
		return isMaskingEnabled;
	}

	public void setMaskingEnabled(boolean isMaskingEnabled) {
		this.isMaskingEnabled = isMaskingEnabled;
	}

	/**
	 * This method must be overridden only for those applications, in which
	 * fixed environment can be configured to be used for authorization purposes
	 * at all times.
	 */
	public String getAuthorizationEnvironment()
	{
		return null;
	}
}