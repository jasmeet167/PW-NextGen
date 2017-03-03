package com.csc.fsg.life.openam.config;

import java.io.Serializable;
import java.util.Map;

import org.springframework.util.StringUtils;

public class PolicyDecisionPointConfig
	implements Serializable
{
	static private final long serialVersionUID = -950750032842982939L;

	private boolean isSecurityEnabled = true;
	private String securityManagementUrl = null;
	private String authTokenCookieName = null;
	private String realm = null;
	private String policySetName = null;
	private Map<String, EndpointApiVersion> apiVersions = null;

	public boolean isSecurityEnabled()
	{
		return isSecurityEnabled;
	}

	public void setSecurityEnabled(boolean isSecurityEnabled)
	{
		this.isSecurityEnabled = isSecurityEnabled;
	}

	public void setSecurityManagementUrl(String securityManagementRealmUrl)
	{
		this.securityManagementUrl = securityManagementRealmUrl;
	}

	public String getSecurityManagementUrl()
	{
		return securityManagementUrl;
	}

	public String getAuthTokenCookieName()
	{
		return authTokenCookieName;
	}

	public void setAuthTokenCookieName(String authTokenCookieName)
	{
		this.authTokenCookieName = authTokenCookieName;
	}

	public String getRealm()
	{
		return realm;
	}

	public void setRealm(String realm)
	{
		this.realm = realm;
	}

	public String getPolicySetName()
	{
		return policySetName;
	}

	public void setPolicySetName(String policySetName)
	{
		this.policySetName = policySetName;
	}

	public void setApiVersions(Map<String, EndpointApiVersion> apiVersions)
	{
		this.apiVersions = apiVersions;
	}

	public String getApiVersionHeaderForEndpoint(String endpoint)
	{
		if (apiVersions == null)
			return null;

		EndpointApiVersion version = apiVersions.get(endpoint);
		if (version == null)
			return null;

		StringBuilder buf = new StringBuilder();

		String protocolVersion = version.getProtocolVersion();
		if (StringUtils.hasText(protocolVersion)) {
			buf.append("protocol=");
			buf.append(protocolVersion);
		}

		String resourceVersion = version.getResourceVersion();
		if (StringUtils.hasText(resourceVersion)) {
			if (buf.length() > 0)
				buf.append(", ");
			buf.append("resource=");
			buf.append(resourceVersion);
		}

		return (buf.length() > 0) ? buf.toString() : null;
	}
}
