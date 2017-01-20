package com.csc.fsg.life.openam.config;

import java.io.Serializable;
import java.util.Map;

import org.springframework.util.StringUtils;

public class SecurityManagementConfig
	implements Serializable
{
	static private final long serialVersionUID = -950750032842982939L;

	private String securityManagementUrl = null;
	private String sessionTokenCookieName = null;
	private String realm = null;
	private String policySetName = null;
	private Map<String, EndpointApiVersion> apiVersions = null;

	public void setSecurityManagementUrl(String securityManagementRealmUrl)
	{
		this.securityManagementUrl = securityManagementRealmUrl;
	}

	public String getSecurityManagementUrl()
	{
		return securityManagementUrl;
	}

	public String getSessionTokenCookieName()
	{
		return sessionTokenCookieName;
	}

	public void setSessionTokenCookieName(String sessionTokenCookieName)
	{
		this.sessionTokenCookieName = sessionTokenCookieName;
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
