package com.csc.fsg.life.security.authorization.resourceidentity;

import com.csc.fsg.life.security.authorization.bo.AuthorityCacheModel;

/* Modifications: ENH1220 */

public class AclIdentityFactory
{
	public AclIdentity buildFromAuthorityCacheModel(AuthorityCacheModel model)
	{
		switch (model.getResourceType()) {
			case UseCaseAclIdentity.RESOURCE_TYPE:
				return new UseCaseAclIdentity(model);
			case FieldAclIdentity.RESOURCE_TYPE:
				return new FieldAclIdentity(model);
			case ButtonAclIdentity.RESOURCE_TYPE:
				return new ButtonAclIdentity(model);
			case MaskingAclIdentity.RESOURCE_TYPE:
				return new MaskingAclIdentity(model);
			case ServiceMethodAclIdentity.RESOURCE_TYPE:
				return new ServiceMethodAclIdentity(model);
			case SpecialOpAclIdentity.RESOURCE_TYPE:
				return new SpecialOpAclIdentity(model);
			default:
				throw new IllegalArgumentException("Invalid resource type: " + model.getResourceType());
		}
	}
}
