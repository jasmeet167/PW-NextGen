package com.csc.fsg.life.context;

import java.io.Serializable;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import javax.security.auth.login.LoginException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.authentication.jaas.JaasGrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.csc.fsg.life.availability.AvailabilityManager;
import com.csc.fsg.life.avm.api.AccessKeys;
import com.csc.fsg.life.biz.service.ErrorMessageManager;
import com.csc.fsg.life.biz.service.ServiceHistoryItem;
import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;
import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.common.config.EnvDbInfoBean;
import com.csc.fsg.life.security.LoginListener;
import com.csc.fsg.life.security.SecurityManager;
import com.csc.fsg.life.security.authentication.UserGroupPrincipal;

/* Modifications: ENHT0085, ENH858, P0158, T0140, T0153, T0106, P0200, T0175, T0127, ENH1220 */

/**
 * Holds user state information. Each application will likely need to extend
 * this class to hold project specific data.
 */
public class UserContext
	implements Serializable
{
	private static final long serialVersionUID = -6032880664152715422L;

    /** Class Logger from Apache Commons Logging. */
	protected static Log log = LogFactory.getLog(UserContext.class);
	
	/** key to store this object in the session under */
	public static final String SESSION_KEY = "csc.fsg.UserContext";

    /** user id of the current user */
    private String userId = null;
    
    /** encrypted user id of the current user */
    private String userIdE = null;
    
    /** encrypted user pwd of the current user */
    private char[] userPwdE = null;
    
	/** locale associated with the current user */
	private Locale locale;
	
    /** allowable values keys */
	private String companyCode = AccessKeys.AllCompanyKey;
	private String productCode = AccessKeys.AllProductKey;
	private String pageId = AccessKeys.AllPageKey;
	private String trxCode = AccessKeys.AllTrxCodeKey;

	/** Default setting for validation of allowable values */
	private boolean validateValues = true;
    
    /** environment selected by the current user */
    protected String applicationEnvironmentName = null;
    protected String applicationEnvironmentDisplayName = null;
	private String dataDb = "";
	private String ruleDb = "";
	private boolean debugAllowed = false;
	private String logSwitch = "";
	private String logDestination = "F";
	private String logFormat = "S";
	private String logDetail = "";
	
	/** This fields are only used for AVM application. */
	private String avmApplicationKey;
	private String avmEnvironmentKey;
	
	/** if the app supports using diff avm environment for diff region , then 
		this variable holds the current avm environment the user is logged into */
	private String currentAvmEnvironment;
    
	/**name of logged in environment, used in case of archived db */
	private String loggedInEnvironment;
	
	 
	/** 
	 * List of service history items.  All services requested on this context.
	 */
	private List<ServiceHistoryItem> serviceHistory = new ArrayList<ServiceHistoryItem>();

	/** Class to create error messages for errors */
	private ErrorMessageManager errorMessageManager;

	/* versioning info */
	private String copybookVersion = "";
    
	/**
	 * Gets the UserId.
	 * 
	 * @return The UserId value.
	 * @see #setUserId
	 */
    public String getUserId() {
        return userId;
    }

	/**
	 * Gets the user id. If necessary truncates it to 8 characters.
	 * 
	 * @return The user Id as 8 characters.
	 */
	public String getUserId8() {
		if (userId == null) return userId;
		if (userId.length() > 8)
			return userId.substring(0, 8);
		else
			return userId;
	}

	/**
	 * Gets the user id. If necessary truncates it to 12 characters.
	 * 
	 * @return The user Id as 12 characters.
	 */
	public String getUserId12() {
		if (userId == null) return userId;
		if (userId.length() > 12)
			return userId.substring(0, 12);
		else
			return userId;
	}
	
	/**
	 * Return the user id of length no more than 16 characters.
	 * 
	 * @return The user Id as a string no more than 16 characters long.
	 */
	public String getUserId16()
	{
		if (userId == null)
			return userId;

		if (userId.length() > 16)
			return userId.substring(0, 16);
		else
			return userId;
	}

	/**
	 * Gets the user id. If necessary truncates it to 20 characters.
	 * 
	 * @return The user Id as 20 characters.
	 */
	public String getUserId20() {
		if (userId == null) return userId;
		if (userId.length() > 20)
			return userId.substring(0, 20);
		else
			return userId;
	}

	/**
	 * Sets the userId.
	 * 
	 * @param userId The new userId value.
	 * @see #getUserId
	 */
    public void setUserId(String userId) {
        this.userId = userId;
    }	

    /**
	 * Gets the userIdE.
	 * 
	 * @return The userIdE value.
	 * @see #setUserIdE
	 */
    public String getUserIdE() {
        return userIdE;
    }

    /**
	 * Sets the encrypted value of userId.
	 * 
	 * @param userIdE The new userIdE value.
	 * @see #getUserIdE
	 */
     public void setUserIdE(String userIdE) {
         this.userIdE = userIdE;
     }  

    /**
	 * Gets the encrypted value of userPwdE.
	 * 
	 * @return The userPwdE value.
	 * @see #setUserPwdE
	 */
    public char[] getUserPwdE() {
        return userPwdE.clone();
    }

    /**
	 * Sets the userPwdE.
	 * 
	 * @param userPwdE The new userPwdE value.
	 * @see #getUserPwdE
	 */
    public void setUserPwdE(char[] userPwdE) {
    	if (userPwdE == null)
    		this.userPwdE = new char[0];
    	else
    		this.userPwdE = userPwdE.clone();
    }   
    
	/**
	 * Gets the locale.
	 * 
	 * @return Locale The Locale to be used for the current User. If no locale
	 *         has been set, this method will return the default Locale for the
	 *         Java machine, in which this class is executing.
	 */
    public Locale getLocale() {
    	if (locale == null)
    		return Locale.getDefault();
    	else
    		return locale;
    }

	/**
	 * Sets the locale.
	 * 
	 * @param locale The new Locale value.
	 */
    public void setLocale(Locale locale) {
    	this.locale = locale;
    }
    
	/**
	 * Resets the current AVM key fields to the defaults.
	 */
	public void resetAVMKeys()
	{
		companyCode = AccessKeys.AllCompanyKey;
		productCode = AccessKeys.AllProductKey;
		pageId = AccessKeys.AllPageKey;
		trxCode = AccessKeys.AllTrxCodeKey;
	}
	
	/**
	 * Current company code used by this user.
	 * 
	 * @return the current company code used by this user.
	 * @see #setCompanyCode
	 */
	public String getCompanyCode() {
		return companyCode;
	}
	
	/**
	 * Return true if a specific company code is set and false if not.
	 * 
	 * @return true if a specific company code is set and false if not.
	 */
	public boolean hasCompanyCode() {
		return (companyCode != null 
			&& !companyCode.equals(AccessKeys.AllCompanyKey));
	}

	/**
	 * Sets the CompanyCode.
	 * 
	 * @param companyCode The new CompanyCode value.
	 * @see #getCompanyCode
	 */
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	/**
	 * Current page/screen/table being used by this user.
	 * 
	 * @return The current value of the page id.
	 * @see #setPageId
	 */
	public String getPageId() {
		return pageId;
	}

	/**
	 * Sets the PageId.
	 * 
	 * @param pageId The new PageId value.
	 * @see #getPageId
	 */
	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

	/**
	 * Current product code being used by this user.
	 * 
	 * @return The current product code being used by this user.
	 */
	public String getProductCode() {
		return productCode;
	}

	/**
	 * Return true if the product code is the default of All products.
	 * 
	 * @return true if the product code is the default of All products.
	 */
	public boolean isProductCodeDefault()
	{
		return (productCode.equals(AccessKeys.AllProductKey));
	}

	/**
	 * Sets the ProductCode.
	 * 
	 * @param productCode	The new ProductCode value.
	 * @see #getProductCode
	 */
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	/**
	 * Current transaction being executed by this user.
	 * 
	 * @return The current transaction being executed by this user.
	 * @see #setTrxCode
	 */
	public String getTrxCode() {
		return trxCode;
	}

	/**
	 * Return true if the trx code is the default of ALL trx codes.
	 * 
	 * @return true if the trx code is the default of ALL trx codes.
	 */
	public boolean isTrxCodeDefault()
	{
		return (trxCode.equals(AccessKeys.AllTrxCodeKey));
	}

	/**
	 * Sets the TrxCode.
	 * 
	 * @param trxCode	The new TrxCode value.
	 * @see #getTrxCode
	 */
	public void setTrxCode(String trxCode) {
		this.trxCode = trxCode;
	}

	/**
	 * Sets the data DB ID selected in the context.
	 * 
	 * @param db	the new data DB ID selected in the context.
	 * @see #getDataDb
	 */
	public void setDataDb(String db)
	{
		dataDb = db;
	}

	/**
	 * Sets the RuleDb.
	 * 
	 * @param db	The new RuleDb value.
	 * @see #getRuleDb
	 */
	public void setRuleDb(String db)
	{
		ruleDb = db;
	}
	
    /**
	 * Sets the name of the current ApplicationEnvironment.
	 * 
	 * @param applicationEnvironmentName The new applicationEnvironmentName value.
	 * @see #getApplicationEnvironmentName
	 */
    public void setApplicationEnvironmentName(String applicationEnvironmentName)
    {
        this.applicationEnvironmentName = applicationEnvironmentName;
    }
    
    /**
	 * Gets the name of the current ApplicationEnvironment.
	 * 
	 * @return the name of the current ApplicationEnvironment
	 * @see #setApplicationEnvironmentName
	 */
    public String getApplicationEnvironmentName()
    {
        return applicationEnvironmentName;
    }
    
    /**
     * Helper method to get the ApplicationEnvironment for the
     * current user.
     */
    public ApplicationEnvironmentBean getApplicationEnvironment()
    {
        CommonApplicationConfigBean appConfigBean = CommonApplicationConfigBean.getInstance();
        return appConfigBean.getEnvironment(applicationEnvironmentName);
    }
    
    /**
     * Set the application environment to use for the current user.  Also,
     * build the necessary objects needed for any data access.
     * 
     * @param applicationEnvironmentBean
     */
    public void setApplicationEnvironmentBean(ApplicationEnvironmentBean applicationEnvironmentBean)
    {
    	// do not reinitialize the environment, unless the given environment is different than the current one
    	if (applicationEnvironmentName != null && applicationEnvironmentName.equals(applicationEnvironmentBean.getName()))
    		return;
    	
    	applicationEnvironmentName = applicationEnvironmentBean.getName();
    	applicationEnvironmentDisplayName = applicationEnvironmentBean.getDisplayName();
    	debugAllowed = applicationEnvironmentBean.getDebugAllowed();
    	logSwitch = applicationEnvironmentBean.getDefaultLogSwitch();
        
        // Set the selected data and rule DB's to the default
        EnvDbInfoBean dataInfo = getApplicationEnvironment().getDefaultEnvDbInfo(EnvDbInfoBean.TYPE_DATA);
        EnvDbInfoBean ruleInfo = getApplicationEnvironment().getDefaultEnvDbInfo(EnvDbInfoBean.TYPE_RULE);
        dataDb = dataInfo.getName();
		if (ruleInfo != null)
			ruleDb = ruleInfo.getName();
    }

	/**
	 * Helper method to get the current data file DB.
	 */
	public EnvDbInfoBean getDataDb()
	{
		return (EnvDbInfoBean)getApplicationEnvironment().getDbByName(dataDb, EnvDbInfoBean.TYPE_DATA);
	}
	
	/**
	 * Helper method to get the current rule file DB.
	 */
	public EnvDbInfoBean getRuleDb()
	{
		return (EnvDbInfoBean)getApplicationEnvironment().getDbByName(ruleDb, EnvDbInfoBean.TYPE_RULE);
	}

	public boolean getDebugAllowed()
	{
		return debugAllowed;
	}

	public void setLogSwitch(String value)
	{
		if (value == null)
			logSwitch = "";
		else
			logSwitch = value;
	}
	
	public String getLogSwitch()
	{
		return logSwitch;
	}
	
	public void setLogDestination(String value)
	{
		if (value == null)
			logDestination = "";
		else
			logDestination = value;
	}
	
	public String getLogDestination()
	{
		return logDestination;
	}

	public void setLogFormat(String value)
	{
		if (value == null)
			logFormat = "S";
		else
			logFormat = value;
	}
	
	public String getLogFormat()
	{
		return logFormat;
	}

	public void setLogDetail(String value)
	{
		if (value == null)
			logDetail = "";
		else
			logDetail = value;
	}
	
	public String getLogDetail()
	{
		return logDetail;
	}

	/**
	 * Get file code string to send to COBOL server.
	 */
	public String getFileCodes()
	{
		EnvDbInfoBean dataDb = getDataDb();
		String adminCode = dataDb.getName();
		EnvDbInfoBean ruleDb = getRuleDb();
		String busRuleCode = ruleDb.getName();
		return adminCode + busRuleCode + busRuleCode + adminCode + adminCode + adminCode;
	}

	/**
	 * Get the user history for service access in this context.
	 * 
	 * @return the user history for service access in this context.
	 */
	public List<ServiceHistoryItem> getServiceHistory() 
	{
		return Collections.unmodifiableList(serviceHistory);
	}
    
	/**
	 * Add a new history item to the service history list.
	 * 
	 * @param item the new item to add to history.
	 */
	public void addHistoryItem(ServiceHistoryItem item)
	{
		serviceHistory.add(item);
	}

	/**
	 * Sets the ErrorMessageManager.
	 * 
	 * @param errorMessageManager The new ErrorMessageManager value.
	 * @see #getErrorMessageManager
	 */
	public void setErrorMessageManager(ErrorMessageManager errorMessageManager)
	{
		this.errorMessageManager = errorMessageManager;
	}

	/**
	 * Gets the error message manager.
	 * 
	 * @return the error message manager.
	 * @see #setErrorMessageManager
	 */
	public ErrorMessageManager getErrorMessageManager()
	{
		return errorMessageManager;
	}
	
    /**
	 * Dump details about user context.
	 */
    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        buf.append("companyCode: ");
        buf.append(companyCode);
        buf.append(" productCode: ");
        buf.append(productCode);
        buf.append(" pageId: ");
        buf.append(pageId);
        buf.append(" trxCode: ");
        buf.append(trxCode);
        buf.append(" environment: ");
        buf.append(getApplicationEnvironment().getDisplayName());
        return buf.toString();
    }

	/**
	 * The AVM application key.
	 * 
	 * @return the avmApplicationId
	 */
	public String getAvmApplicationKey() {
		return avmApplicationKey;
	}

	/**
	 * The AVM Application Id
	 * 
	 * @param avmApplicationId the avmApplicationId to set
	 */
	public void setAvmApplicationKey(String avmApplicationId) {
		this.avmApplicationKey = avmApplicationId;
	}

	/**
	 * The AVM Environment ID.
	 * 
	 * @return the environmentId
	 */
	public String getAvmEnvironmentKey() {
		return avmEnvironmentKey;
	}

	/**
	 * The AVM Environment ID.
	 * 
	 * @param avmEnvironmentId the environmentId to set
	 */
	public void setAvmEnvironmentKey(String avmEnvironmentId) {
		this.avmEnvironmentKey = avmEnvironmentId;
	}
    
	/**
	 * Default flag for allowable value validation. If set to true then objects
	 * will validate their fields for valid allowable values. If false they will
	 * not.
	 */
    public boolean getValidateValues()
    {
        return validateValues;
    }

	/**
	 * Default flag for allowable value validation. If set to true then objects
	 * will validate their fields for valid allowable values. If false they will
	 * not.
	 */
    public void setValidateValues(boolean validateValues)
    {
        this.validateValues = validateValues;
    }

	/**
	 * Call this to login.
	 */
	public void login(String userId, char[] userPwd, LoginListener loginListener)
		throws GeneralSecurityException
	{
		SecurityManager securityManager = getSecurityManager();
		Authentication authentication = securityManager.login(userId, userPwd, loginListener);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Successful login, set user id.
		setUserId(userId);
		setUserIdE(encrypt(userId));
		setUserPwdE(encrypt(userPwd));

		loginListener.onUserContextInit(this);
	}

	public void logout()
	{
		try {
			SecurityManager securityManager = getSecurityManager();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			securityManager.logout(authentication);
		}
		catch (LoginException e) {
			e.printStackTrace();
		}
	}

	public SecurityManager getSecurityManager() {
		// Implement in subcass to return the right security manager
		return null;
	}
	
	/**
	 * Default implementation of the read property
	 * <code>availabilityManager</code>. It is intended to be overridden in a
	 * subclass.
	 * 
	 * @return The empty default implementation of
	 *         <code>AvailabilityManager</code>.
	 */
	@SuppressWarnings("serial")
	public AvailabilityManager getAvailabilityManager()
	{
		return new AvailabilityManager() {
			public void checkDatabaseUpdateAvailability(UserContext userContext)
			{
				// Default no-op implementation
			}
		};
	}

	/**
	 * Returns true if this context is logged in.
	 * 
	 * @return true if this context is logged in.
	 */
	public boolean isLoggedIn()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null
			&& authentication.isAuthenticated();
	}

	public Collection<? extends GrantedAuthority> getGrantedGroupAuthorities()
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> grantedAuthorities = authentication.getAuthorities();
		Collection<GrantedAuthority> grantedGroupAuthorities = new ArrayList<>();

		for (GrantedAuthority grantedAuthority : grantedAuthorities) {
			if (grantedAuthority instanceof JaasGrantedAuthority) {
				JaasGrantedAuthority jaasGrantedAuthority = (JaasGrantedAuthority) grantedAuthority;
				Principal principal = jaasGrantedAuthority.getPrincipal();
				if (principal instanceof UserGroupPrincipal)
					grantedGroupAuthorities.add(grantedAuthority);
			}
		}

		return grantedGroupAuthorities;
	}

	/**
	 * if the app supports using diff avm environment for diff region, then this
	 * variable holds the current avm environment the user is logged into.
	 * 
	 * @return the currentAvmEnvironment
	 */
	public String getCurrentAvmEnvironment() {
		return currentAvmEnvironment;
	}

	/**
	 * if the app supports using diff avm environment for diff region, then this
	 * variable holds the current avm environment the user is logged into.
	 * 
	 * @param currentAvmEnvironment the currentAvmEnvironment to set
	 */
	public void setCurrentAvmEnvironment(String currentAvmEnvironment) {
		this.currentAvmEnvironment = currentAvmEnvironment;
	}
    
	/**
	 * Obfuscate the input String in a format used by this class 
	 * to store sensitive data.
	 * 
	 * @param inString	the input string to be encrypted.
	 * 
	 * @return String	the encrypted equivalent of the input text.
	 */
	private String encrypt(String inString) {
		char[] cArray = inString.toCharArray();
		return new String(encrypt(cArray));
	}

	/**
	 * Obfuscate the input character array in a format used by this class to
	 * store sensitive data.
	 * 
	 * @param chArray
	 *        the input string to be encrypted.
	 * 
	 * @return char[] The input character array with encrypted contents.
	 */
	public char[] encrypt(char[] chArray)
	{
		for (int i = 0; i < chArray.length; i++) {
			char ch = chArray[i];
			if (ch >= 'a' && ch <= 'm')
				ch += 13;
			else if (ch >= 'n' && ch <= 'z')
				ch -= 13;
			else if (ch >= 'A' && ch <= 'M')
				ch += 13;
			else if (ch >= 'A' && ch <= 'Z')
				ch -= 13;
			chArray[i] = ch;
		}

		return chArray;
	}

	/**
	 * @return the loggedInEnvironment
	 */
	public String getLoggedInEnvironment() {
		return loggedInEnvironment;
	}

	/**
	 * @param loggedInEnvironment the loggedInEnvironment to set
	 */
	public void setLoggedInEnvironment(String loggedInEnvironment) {
		this.loggedInEnvironment = loggedInEnvironment;
	}

	/**
	 * @return the applicationEnvironmentDisplayName
	 */
	public String getApplicationEnvironmentDisplayName() {
		return applicationEnvironmentDisplayName;
	}

	/**
	 * @param applicationEnvironmentDisplayName the applicationEnvironmentDisplayName to set
	 */
	public void setApplicationEnvironmentDisplayName(
			String applicationEnvironmentDisplayName) {
		this.applicationEnvironmentDisplayName = applicationEnvironmentDisplayName;
	}

	/**
	 * Get copybook versioning info
	 * @return String
	 */
	public String getCopybookVersion() {
		return copybookVersion;
	}
	
	/**
	 * Set copybook versioning info
	 * @param version
	 */
	public void setCopybookVersion(String copybookVersion) {
		this.copybookVersion = copybookVersion == null ? "" : copybookVersion;
	}
}
