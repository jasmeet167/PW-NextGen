package com.csc.fsg.life.security.authorization.common;

import java.io.Serializable;

/* Modifications: ENH1220 */

public class AuthorityKey
	implements Serializable, Comparable<AuthorityKey>
{
	private static final long serialVersionUID = -895987504709767311L;

	final private String key;

	public AuthorityKey(String key)
	{
		this.key = key;
	}

	@Override
	public int hashCode()
	{
		return this.toString().hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof AuthorityKey))
			return false;

		return this.toString().equals(obj.toString());
	}

	public int compareTo(AuthorityKey obj)
	{
		return this.toString().compareTo(obj.toString());
	}

	@Override
	public String toString()
	{
		if (key == null)
			return "";
		else
			return key;
	}
}
