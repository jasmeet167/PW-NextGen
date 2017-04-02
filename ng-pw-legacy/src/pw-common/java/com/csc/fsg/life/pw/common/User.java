
package com.csc.fsg.life.pw.common;

import java.io.Serializable;
import java.util.*;

import com.csc.fsg.life.pw.common.util.Constants;

/* Modifications: T0091,T0120, WMABASEIXI-4515, WMABASEIXI-4584, T0129, ENH1043, ENH1063.06, T0106, T0175,
 *                WMA-1718, ENH1220, T0195 */
// WMABASEIXI-4515 - trim company names in resource keys


public class User implements PolicyConstants, Serializable {

//	private static Log logger = LogFactory.getLog(User.class);

	private Map<String, List<EnvironmentElement>> envMap = new TreeMap<String, List<EnvironmentElement>>();
//	private transient SecurityManager _securityManager;

//	private String _userId;
//	private Collection<? extends GrantedAuthority> _grantedAuthorities;

	private String appKey;
	private String productSelected;
	
	private static final String DELIM = "\t";
	private static final String END_OF_USER = "<END-OF-USER>";
	private static final String END_OF_ENV = "<END-OF-ENV>";
	private static final String END_OF_ENVS = "<END-OF-ENVS>";

//	public User(String userId, Collection<? extends GrantedAuthority> grantedAuthorities,
//				SecurityManager securityManager,
//				Set<EnvironmentElement> envs) {
//		_userId = userId;
//		_grantedAuthorities = grantedAuthorities;
//		_securityManager = securityManager;
//	}

	public String toTabbedString() 
	{
		StringBuffer sb = new StringBuffer();

//		sb.append(_userId).append(DELIM);
		sb.append(END_OF_USER);
		
		for (EnvironmentElement env : envMap.get(VIEW))
			sb.append(env.getEnvironmentName()).append(DELIM).append(env.getEnvironmentSchema()).append(DELIM).append("").append(END_OF_ENV);
		sb.append(END_OF_ENVS);
		
		for (EnvironmentElement env : envMap.get(UPDATE))
			sb.append(env.getEnvironmentName()).append(DELIM).append(env.getEnvironmentSchema()).append(DELIM).append("").append(END_OF_ENV);
		sb.append(END_OF_ENVS);
		
		for (EnvironmentElement env : envMap.get(UPDATE_ETV))
			sb.append(env.getEnvironmentName()).append(DELIM).append(env.getEnvironmentSchema()).append(DELIM).append("").append(END_OF_ENV);
		sb.append(END_OF_ENVS);
		
		for (EnvironmentElement env : envMap.get(AUDIT_PURGE))
			sb.append(env.getEnvironmentName()).append(DELIM).append(env.getEnvironmentSchema()).append(DELIM).append("").append(END_OF_ENV);
		sb.append(END_OF_ENVS);
		
		for (EnvironmentElement env : envMap.get(PROMOTE))
			sb.append(env.getEnvironmentName()).append(DELIM).append(env.getEnvironmentSchema()).append(DELIM).append("").append(END_OF_ENV);
		sb.append(END_OF_ENVS);
		
		return sb.toString();
	}
	
//	public User(String userStr)throws Exception {
//		String[] userParts = userStr.split(END_OF_USER);
//		_userId = userParts[0];
//		userParts = userParts[1].split(END_OF_ENVS);
//		buildEnvironments(userParts[0],VIEW);
//		buildEnvironments(userParts[1],UPDATE);
//		buildEnvironments(userParts[2],UPDATE_ETV);
//		buildEnvironments(userParts[3],AUDIT_PURGE);
//		buildEnvironments(userParts[4],PROMOTE);
//	}

//	private void buildEnvironments(String envs,String action) throws Exception{
//		List<EnvironmentElement> list = new ArrayList<EnvironmentElement>();
//		if ( envs.trim().length() > 0 ) {
//			String[] envParts = envs.split(END_OF_ENV);
//	
//			for (String envPart : envParts) 
//				list.add(buildEnvironment(envPart));
//		}
//		envMap.put(action, list);
//	}

//	private EnvironmentElement buildEnvironment(String envStr) throws Exception{
//		
//		if (envStr==null || envStr.trim().length()==0)
//			throw new Exception(" No Authorized Environments for "+getUserId());
//			
//		String[] envAttrs = envStr.split(DELIM);
//		String spSchema = null; 
//		if (envAttrs.length>2)
//			spSchema = envAttrs[2];
//		return new EnvironmentElement(0, envAttrs[0], envAttrs[1], null, null,
//				spSchema);
//	}

//	public void setSecurityManager(SecurityManager securityManager) {
//		_securityManager = securityManager;
//	}

//	public String getUserId() {
//		// WMABASEIXI-4584 - upper case user id
//		return _userId.toUpperCase();
//	}

	public char[] getPasswordE()
	{
		if (appKey == null)
			return null;

		String[] appKeyComponents = appKey.split("!\\$");
		if (appKeyComponents.length < 1 || appKeyComponents[0].length() < 2)
			return null;
		else
			return appKeyComponents[0].substring(1).trim().toCharArray();
	}

	static class EnvElementComparator implements java.util.Comparator<EnvironmentElement>, Serializable {
		public int compare(EnvironmentElement o1, EnvironmentElement o2) {
			EnvironmentElement ee1 = o1;
			EnvironmentElement ee2 = o2;
			return ee1.getEnvironmentName().compareTo(ee2.getEnvironmentName());
		}
	}

	public int getHighestAuthority() {
		return 0;
	}

	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getProductSelected() {
		return productSelected;
	}
	public void setProductSelected(String productSelected) {
		this.productSelected = productSelected;
	}
	public Map<String, List<EnvironmentElement>> getEnvMap() {
		return envMap;
	}

	public void setEnvMap(Map<String, List<EnvironmentElement>> envMap) {
		this.envMap = envMap;
	}
	
	public boolean hasEnvPermission(String envId, String action) {
		//temp fix
		if (envId.startsWith(Constants.EXT_AUDIT_PREFIX)){
			envId = envId.substring(Constants.EXT_AUDIT_PREFIX.length());
		}
		
		List<EnvironmentElement> envs = getEnvMap().get(action);
		if ( envs != null ) {
			for ( EnvironmentElement env : envs ) {
				if ( env.getSchema().equals(envId) )
					return true;
			}
		}
		return false;
	}
}
