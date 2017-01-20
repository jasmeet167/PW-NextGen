package com.csc.fsg.life.security.authorization.resourceidentity;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import com.csc.fsg.life.avm.api.AccessKeys;
import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;

/* Modifications: ENH1220 */

/**
 * Base class extended by all ACL Domain Objects representing resources secured
 * using Spring framework's Domain Object Security (ACLs).
 */
abstract public class AclIdentity
	implements Serializable, Cloneable
{
	private static final long serialVersionUID = -4787894093232171706L;

	private static final String GENERIC_IND = "*";

	private Long id = null;
	private String environment = null;
	private String companyCode = null;

	static protected String buildEnvironmentName(UserContext userContext)
	{
		CommonApplicationConfigBean appConfig = CommonApplicationConfigBean.getInstance();
		String authorizationEnvironment = appConfig.getAuthorizationEnvironment();

		if (authorizationEnvironment != null)
			return authorizationEnvironment;
		else if (appConfig.isUseEnvIdAsAttrInSecurity())
			return userContext.getApplicationEnvironmentName();
		else
			return null;
	}

	static protected String buildCompanyCode(UserContext userContext)
	{
		CommonApplicationConfigBean appConfig = CommonApplicationConfigBean.getInstance();
		if (appConfig.isUseCompanyCodeAsAttrInSecurity()) {
			String companyCode = userContext.getCompanyCode();
			if (!AccessKeys.AllCompanyKey.equals(companyCode))
				return companyCode;
		}

		return null;
	}

	protected AclIdentity(Long id, String environment, String companyCode)
	{
		this.id = id;

		if (!StringUtils.hasText(environment))
			this.environment = GENERIC_IND;
		else
			this.environment = environment;

		if (!StringUtils.hasText(companyCode)
		 || AccessKeys.AllCompanyKey.equals(companyCode))
			this.companyCode = GENERIC_IND;
		else
			this.companyCode = companyCode;
	}

	/**
	 * This method controls the order of search of ACLs defined with wild cards,
	 * during evaluation of user's authorization to access the corresponding
	 * resource.
	 * <p>
	 * The following order is used:
	 * <ol>
	 * <li>Fully-qualified Environment and Company Code</li>
	 * <li>Fully-qualified Environment, followed by generic Company Code,
	 * indicated by the '*' character</li>
	 * <li>Generic Environment and Company Code, each value indicated by the '*'
	 * character</li>
	 * </ol>
	 */
	public final AclIdentity getMoreGenericClone()
	{
		AclIdentity clone = null;
		try {
			clone = (AclIdentity) this.clone();
			if (!clone.isFullyGeneric()) {
				if (!GENERIC_IND.equals(clone.getCompanyCode()))
					clone.companyCode = GENERIC_IND;
				else
					clone.environment = GENERIC_IND;
			}
		}
		catch (CloneNotSupportedException e) {
		}

		return clone;
	}

	/**
	 * Return a flag indicating whether both the {@code environment} and
	 * {@code companyCode} have generic values (i.e. are wild-cards).
	 * 
	 * @return {@code true} if both the {@code environment} and
	 *         {@code companyCode} are wild-cards, and {@code false} if one or
	 *         both have specific values.
	 */
	public final boolean isFullyGeneric()
	{
		return GENERIC_IND.equals(environment)
			&& GENERIC_IND.equals(companyCode);
	}

	abstract public String getResourceType();

	public final Long getId()
	{
		return id;
	}

	public final String getEnvironment()
	{
		return environment;
	}

	public final String getCompanyCode()
	{
		return companyCode;
	}

	abstract public AuthorityKey getAuthorityKey();

	protected void buildAuthorityKey(StringBuilder buf)
	{
		buf.append(getResourceType());
		buf.append('\t');
		buf.append(environment);
		buf.append('\t');
		buf.append(companyCode);
		buf.append('\t');
	}

	protected void buildStringValue(ToStringBuilder builder)
	{
		builder.append("resourceType", getResourceType());
		builder.append("id", id);
		builder.append("environment", environment);
		builder.append("companyCode", companyCode);
	}
}
