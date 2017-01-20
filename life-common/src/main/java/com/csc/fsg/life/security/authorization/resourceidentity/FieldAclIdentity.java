package com.csc.fsg.life.security.authorization.resourceidentity;

import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;

/* Modifications: ENH1220 */

public class FieldAclIdentity
	extends AclIdentity
{
	private static final long serialVersionUID = -8967963420179660525L;

	public static final String RESOURCE_TYPE = "F";

	private String useCaseId = null;
	private String fullBoName = null;

	public static FieldAclIdentity getInstance(UserContext userContext, String useCaseId, String fieldFullName)
	{
		String environment = buildEnvironmentName(userContext);
		String companyCode = buildCompanyCode(userContext);
		return new FieldAclIdentity(null, environment, companyCode, useCaseId, fieldFullName);
	}

	public static FieldAclIdentity getInstance(String environment, String companyCode, String useCaseId, String fieldFullName)
	{
		return new FieldAclIdentity(null, environment, companyCode, useCaseId, fieldFullName);
	}

	public static FieldAclIdentity getInstance(Long id, String environment, String companyCode, String useCaseId, String fieldFullName)
	{
		return new FieldAclIdentity(id, environment, companyCode, useCaseId, fieldFullName);
	}

	private FieldAclIdentity(Long id, String environment, String companyCode, String useCaseId, String fullBoName)
	{
		super(id, environment, companyCode);
		this.useCaseId = useCaseId;
		this.fullBoName = fullBoName;
	}

	protected FieldAclIdentity(AuthorityCacheModel model)
	{
		super(model.getId(), model.getEnvironment(), model.getCompanyCode());
		this.useCaseId = model.getUseCaseId();
		this.fullBoName = model.getFullBoName();
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
		buf.append(fullBoName);
		buf.append('\t');

		return new AuthorityKey(buf.toString());
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		buildStringValue(builder);
		builder.append("useCaseId", useCaseId);
		builder.append("fullBoName", fullBoName);
		return builder.toString();
	}
}
