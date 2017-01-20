package com.csc.fsg.life.security.authorization.resourceidentity;

import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;

/* Modifications: ENH1220 */

public class UseCaseAclIdentity
	extends AclIdentity
{
	private static final long serialVersionUID = -5550378023069383226L;

	public static final String RESOURCE_TYPE = "U";

	private String useCaseId = null;

	public static UseCaseAclIdentity getInstance(UserContext userContext, String useCaseId)
	{
		String environment = buildEnvironmentName(userContext);
		String companyCode = buildCompanyCode(userContext);
		return new UseCaseAclIdentity(null, environment, companyCode, useCaseId);
	}

	public static UseCaseAclIdentity getInstance(Long id, String environment, String companyCode, String useCaseId)
	{
		return new UseCaseAclIdentity(id, environment, companyCode, useCaseId);
	}

	private UseCaseAclIdentity(Long id, String environment, String companyCode, String useCaseId)
	{
		super(id, environment, companyCode);
		this.useCaseId = useCaseId;
	}

	protected UseCaseAclIdentity(AuthorityCacheModel model)
	{
		super(model.getId(), model.getEnvironment(), model.getCompanyCode());
		this.useCaseId = model.getUseCaseId();
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

		buf.append(useCaseId);
		buf.append('\t');

		return new AuthorityKey(buf.toString());
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		buildStringValue(builder);
		builder.append("useCaseId", useCaseId);
		return builder.toString();
	}
}
