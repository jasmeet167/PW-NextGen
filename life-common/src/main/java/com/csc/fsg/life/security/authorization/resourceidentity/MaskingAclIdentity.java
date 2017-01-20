package com.csc.fsg.life.security.authorization.resourceidentity;

import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;

/* Modifications: ENH1220 */

public class MaskingAclIdentity
	extends AclIdentity
{
	private static final long serialVersionUID = 6421320417328335172L;

	public static final String RESOURCE_TYPE = "M";

	private String maskClass = null;

	public static MaskingAclIdentity getInstance(UserContext userContext, String maskClass)
	{
		String environment = buildEnvironmentName(userContext);
		String companyCode = buildCompanyCode(userContext);
		return new MaskingAclIdentity(null, environment, companyCode, maskClass);
	}

	private MaskingAclIdentity(Long id, String environment, String companyCode, String maskClass)
	{
		super(id, environment, companyCode);
		this.maskClass = maskClass;
	}

	protected MaskingAclIdentity(AuthorityCacheModel model)
	{
		super(model.getId(), model.getEnvironment(), model.getCompanyCode());
		this.maskClass = model.getMaskClass();
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

		buf.append(maskClass);
		buf.append('\t');

		return new AuthorityKey(buf.toString());
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		buildStringValue(builder);
		builder.append("maskClass", maskClass);
		return builder.toString();
	}
}
