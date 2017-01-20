package com.csc.fsg.life.security.authorization.resourceidentity;

import java.util.Arrays;
import java.util.List;

import com.csc.fsg.life.common.util.ToStringBuilder;
import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;
import com.csc.fsg.life.security.authorization.common.AuthorityKey;

/* Modifications: ENH1220 */

public class SpecialOpAclIdentity
	extends AclIdentity
{
	private static final long serialVersionUID = -718383066293651451L;

	public static final String RESOURCE_TYPE = "O";
	public static final String COMPANY_SEARCH = "C";

	private static final List<String> ALL_OP_TYPES = Arrays.asList(new String[] { COMPANY_SEARCH });

	private String specialOpType = null;

	public static SpecialOpAclIdentity getCompanySearchSpecialOpInstance(String environment, String companyCode)
	{
		return new SpecialOpAclIdentity(null, environment, companyCode, COMPANY_SEARCH);
	}

	protected SpecialOpAclIdentity(Long id, String environment, String companyCode, String specialOpType)
	{
		super(id, environment, companyCode);
		this.specialOpType = specialOpType;

		if (!isValidSpecialOpType(specialOpType))
			throw new IllegalArgumentException("Invalid type of Special Operation Identity detected: " + specialOpType);
	}

	protected SpecialOpAclIdentity(AuthorityCacheModel model)
	{
		super(model.getId(), model.getEnvironment(), model.getCompanyCode());
		specialOpType = model.getSpecialOperationType();

		if (!isValidSpecialOpType(specialOpType))
			throw new IllegalArgumentException("Invalid type of Special Operation Identity detected: " + specialOpType);
	}

	protected boolean isValidSpecialOpType(String specialOpType)
	{
		return ALL_OP_TYPES.contains(specialOpType);
	}

	protected String getSpecialOpType()
	{
		return specialOpType;
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

		buf.append(specialOpType);
		buf.append('\t');

		return new AuthorityKey(buf.toString());
	}

	@Override
	public String toString()
	{
		ToStringBuilder builder = new ToStringBuilder(this);
		buildStringValue(builder);
		builder.append("specialOpType", specialOpType);
		return builder.toString();
	}
}
