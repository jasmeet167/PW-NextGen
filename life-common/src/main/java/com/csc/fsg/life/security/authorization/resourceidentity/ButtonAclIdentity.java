package com.csc.fsg.life.security.authorization.resourceidentity;

import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;

/* Modifications: ENH1220 */

public class ButtonAclIdentity
	extends AclIdentity
{
	private static final long serialVersionUID = -3290480349850508848L;

	public static final String RESOURCE_TYPE = "B";

	private String useCaseId = null;
	private String buttonName = null;

	public static ButtonAclIdentity getInstance(UserContext userContext, String useCaseId, String buttonName)
	{
		String environment = buildEnvironmentName(userContext);
		String companyCode = buildCompanyCode(userContext);
		return new ButtonAclIdentity(null, environment, companyCode, useCaseId, buttonName);
	}

	public static ButtonAclIdentity getInstance(String environment, String companyCode, String useCaseId, String buttonName)
	{
		return new ButtonAclIdentity(null, environment, companyCode, useCaseId, buttonName);
	}

	public static ButtonAclIdentity getInstance(Long id, String environment, String companyCode, String useCaseId, String buttonName)
	{
		return new ButtonAclIdentity(id, environment, companyCode, useCaseId, buttonName);
	}

	private ButtonAclIdentity(Long id, String environment, String companyCode, String useCaseId, String buttonName)
	{
		super(id, environment, companyCode);
		this.useCaseId = useCaseId;
		this.buttonName = buttonName;
	}

	protected ButtonAclIdentity(AuthorityCacheModel model)
	{
		super(model.getId(), model.getEnvironment(), model.getCompanyCode());
		this.useCaseId = model.getUseCaseId();
		this.buttonName = model.getButtonName();
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
		buf.append(buttonName);
		buf.append('\t');

		return new AuthorityKey(buf.toString());
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		buildStringValue(builder);
		builder.append("useCaseId", useCaseId);
		builder.append("buttonName", buttonName);
		return builder.toString();
	}
}
