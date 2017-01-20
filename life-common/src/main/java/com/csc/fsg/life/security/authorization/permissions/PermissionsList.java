package com.csc.fsg.life.security.authorization.permissions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.acls.model.Permission;

/* Modifications: ENH1220 */

public class PermissionsList
	extends ArrayList<Permission>
{
	private static final long serialVersionUID = 7685175368411329959L;

	public static final PermissionsList READ;
	public static final PermissionsList EXCLUSIVE_READ;
	public static final PermissionsList FULL;
	public static final PermissionsList SETUP;
	public static final PermissionsList GENERAL;

	static {
		// The READ list includes UPDATE, because the update capability
		// implies the inquiry capability, and thus its presence alone indicates
		// that inquiry authorization is to be granted
		List<Permission> readAccessList = Arrays.asList(new Permission[] { AccessPermission.INQUIRY, AccessPermission.UPDATE });
		READ = new PermissionsList(Collections.unmodifiableList(readAccessList));

		List<Permission> exclusiveReadAccessList = Arrays.asList(new Permission[] { AccessPermission.INQUIRY });
		EXCLUSIVE_READ = new PermissionsList(Collections.unmodifiableList(exclusiveReadAccessList));

		List<Permission> fullAccessList = Arrays.asList(new Permission[] { AccessPermission.UPDATE });
		FULL = new PermissionsList(Collections.unmodifiableList(fullAccessList));

		List<Permission> setupAccessList = Arrays.asList(new Permission[] { AccessPermission.SETUP });
		SETUP = new PermissionsList(Collections.unmodifiableList(setupAccessList));

		List<Permission> generalAccessList = Arrays.asList(new Permission[] { AccessPermission.GENERAL });
		GENERAL = new PermissionsList(Collections.unmodifiableList(generalAccessList));
	}

	public PermissionsList(Collection<Permission> permissions)
	{
		super(permissions);
	}

	public boolean isMatching(String businessEventType)
	{
		for (Permission permission : this)
			if (permission instanceof AccessPermission)
				if (((AccessPermission) permission).isMatching(businessEventType))
					return true;

		return false;
	}

	public boolean isPartiallyMatching(String businessEventType)
	{
		for (Permission permission : this)
			if (permission instanceof AccessPermission)
				if (((AccessPermission) permission).isPartiallyMatching(businessEventType))
					return true;

		return false;
	}
}
