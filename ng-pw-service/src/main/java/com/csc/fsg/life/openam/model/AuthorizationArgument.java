package com.csc.fsg.life.openam.model;

import java.io.Serializable;

import com.csc.fsg.life.rest.annotation.AuthorizationAction;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AuthorizationArgument
	implements Serializable
{
	static private final long serialVersionUID = 7788657869040777820L;

	static private final char SEPARATOR_CHAR = '\t';
	static private final String SEPARATOR_STRING = String.valueOf(new char[] { SEPARATOR_CHAR });

	private String resource = null;
	private AuthorizationAction action = null;

	public AuthorizationArgument(String resource, AuthorizationAction action)
	{
		this.resource = resource;
		this.action = action;
	}

	public String getResource()
	{
		return resource;
	}

	public AuthorizationAction getAction()
	{
		return action;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;

		if (obj instanceof AuthorizationArgument) {
			AuthorizationArgument that = (AuthorizationArgument) obj;
			return this.getCanonicalRepresentation().equals(that.getCanonicalRepresentation());
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		return getCanonicalRepresentation().hashCode();
	}

	public boolean isEmpty()
	{
		return getCanonicalRepresentation().equals(SEPARATOR_STRING);
	}

	public boolean isConsistent()
	{
		if (isEmpty())
			return true;

		String rep = getCanonicalRepresentation();
		return !rep.startsWith(SEPARATOR_STRING) && !rep.endsWith(SEPARATOR_STRING);
	}

	@Override
	public String toString()
	{
		return getCanonicalRepresentation().replace(SEPARATOR_CHAR, ':');
	}

	private String getCanonicalRepresentation()
	{
		StringBuilder buf = new StringBuilder();
		if (resource != null)
			buf.append(resource.trim());

		buf.append(SEPARATOR_CHAR);

		if (action != null)
			buf.append(action.toString());

		return buf.toString();
	}
}
