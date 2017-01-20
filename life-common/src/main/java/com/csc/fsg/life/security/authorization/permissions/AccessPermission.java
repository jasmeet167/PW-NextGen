package com.csc.fsg.life.security.authorization.permissions;

import org.springframework.security.acls.domain.AbstractPermission;

/* Modifications: ENH1220 */

public class AccessPermission
	extends AbstractPermission
{
	private static final long serialVersionUID = 4671157998992747859L;

	public static final AccessPermission GENERAL = new AccessPermission(1 << 15, 'G', "GENERAL");				// 32768
	public static final AccessPermission INQUIRY = new AccessPermission(1 << 16, 'I', "INQUIRY");				// 65536
	public static final AccessPermission UPDATE = new AccessPermission(1 << 17, 'U', "UPDATE"); 				// 131072
	public static final AccessPermission SETUP = new AccessPermission(1 << 18, 'S', "SETUP");					// 262144

	private String businessEventType = null;

	protected AccessPermission(int mask, char code, String businessEventType)
	{
		super(mask, code);
		this.businessEventType = businessEventType;
	}

	public boolean isMatching(String businessEventType)
	{
		return this.businessEventType.equalsIgnoreCase(businessEventType);
	}

	public boolean isPartiallyMatching(String businessEventType)
	{
		return businessEventType != null
			&& businessEventType.toUpperCase().indexOf(this.businessEventType) >= 0;
	}

	public String getBusinessEventType()
	{
		return businessEventType;
	}
}
