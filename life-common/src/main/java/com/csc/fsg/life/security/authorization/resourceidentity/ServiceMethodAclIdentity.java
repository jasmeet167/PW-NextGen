package com.csc.fsg.life.security.authorization.resourceidentity;

import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;

/* Modifications: ENH1220 */

public class ServiceMethodAclIdentity
	extends AclIdentity
{
	private static final long serialVersionUID = -4593190211844600164L;

	public static final String RESOURCE_TYPE = "S";

	private String className = null;
	private String methodSignature = null;

	public static ServiceMethodAclIdentity getInstance(UserContext userContext, String className, String methodSignature)
	{
		String environment = buildEnvironmentName(userContext);
		String companyCode = buildCompanyCode(userContext);
		return new ServiceMethodAclIdentity(null, environment, companyCode, className, methodSignature);
	}

	public static ServiceMethodAclIdentity getInstance(Long id, String environment, String companyCode, String className, String methodSignature)
	{
		return new ServiceMethodAclIdentity(id, environment, companyCode, className, methodSignature);
	}

	private ServiceMethodAclIdentity(Long id, String environment, String companyCode, String className, String methodSignature)
	{
		super(id, environment, companyCode);
		this.className = className;
		this.methodSignature = methodSignature;
	}

	protected ServiceMethodAclIdentity(AuthorityCacheModel model)
	{
		super(model.getId(), model.getEnvironment(), model.getCompanyCode());
		this.className = model.getClassName();
		this.methodSignature = model.getMethodSignature();
	}

	@Override
	public String getResourceType()
	{
		return RESOURCE_TYPE;
	}

	@Override
	public AuthorityKey getAuthorityKey()
	{
		StringBuilder buf = new StringBuilder();
		buildAuthorityKey(buf);

		buf.append(className);
		buf.append('\t');
		buf.append(methodSignature);
		buf.append('\t');

		return new AuthorityKey(buf.toString());
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		buildStringValue(builder);
		builder.append("className", className);
		builder.append("methodSignature", methodSignature);
		return builder.toString();
	}
}
