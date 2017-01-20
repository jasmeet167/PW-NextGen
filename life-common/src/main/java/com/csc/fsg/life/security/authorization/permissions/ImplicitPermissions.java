package com.csc.fsg.life.security.authorization.permissions;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.security.acls.model.Permission;
import org.springframework.security.core.GrantedAuthority;

/* Modifications: ENH1220 */

public class ImplicitPermissions
	implements Serializable
{
	private static final long serialVersionUID = -2877554566545470252L;

	private Map<String, Set<String>> impliedPermissionsMap = null;

	public void setPermissions(Map<String, Set<String>> permissionsMap)
	{
		if (this.impliedPermissionsMap == null)
			this.impliedPermissionsMap = permissionsMap;
		else
			throw new IllegalStateException("For security reasons, Implicit Permissions must be established exactly once, at application start");
	}

	public boolean hasImplicitAuthority(Collection<? extends GrantedAuthority> authorities, List<Permission> permissions)
	{
		if (impliedPermissionsMap == null || impliedPermissionsMap.isEmpty())
			return false;

		for (GrantedAuthority authority : authorities) {
			Set<String> impliedPermissions = impliedPermissionsMap.get(authority.getAuthority());
			if (impliedPermissions == null)
				continue;

			if (impliedPermissions.contains("*"))
				return true;

			for (Permission permission : permissions) {
				if (permission instanceof AccessPermission) {
					String businessEventType = ((AccessPermission) permission).getBusinessEventType();
					if (impliedPermissions.contains(businessEventType))
						return true;
				}
			}
		}

		return false;
	}
}
