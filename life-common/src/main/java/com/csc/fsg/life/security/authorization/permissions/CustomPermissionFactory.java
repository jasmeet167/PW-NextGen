package com.csc.fsg.life.security.authorization.permissions;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.acls.domain.DefaultPermissionFactory;
import org.springframework.security.acls.model.Permission;

/* Modifications: ENH1220 */

public class CustomPermissionFactory
	extends DefaultPermissionFactory
{
	/**
	 * Register the {@code Permission} fields from the {@code BasePermission}
	 * class.
	 */
	public CustomPermissionFactory()
	{
		super();
	}

	/**
	 * Register the {@code Permission} fields from the supplied class.
	 */
	public CustomPermissionFactory(Class<? extends Permission> permissionClass)
	{
		super(permissionClass);
	}

	/**
	 * Register a map of named {@code Permission} instances.
	 * 
	 * @param namedPermissions
	 *        the map of {@code Permission}s, keyed by name.
	 */
	public CustomPermissionFactory(Map<String, ? extends Permission> namedPermissions)
	{
		super(namedPermissions);
	}

	/**
	 * Register a custom {@code Permission} class.
	 */
	@Value("${customPermissionClass}")
	private void registerCustomPermission(Class<? extends Permission> permissionClass)
	{
		registerPublicPermissions(permissionClass);
	}

	/**
	 * Register an optional additional custom {@code Permission} class.
	 */
	@Value("${additionalCustomPermissionClass}")
	private void registerAdditionalCustomPermissionClass(Class<? extends Permission> permissionClass)
	{
		if (permissionClass != null)
			registerPublicPermissions(permissionClass);
	}
}
