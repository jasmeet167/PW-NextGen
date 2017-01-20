package com.csc.fsg.life.security.authentication;

import java.io.Serializable;
import java.security.Principal;

/* Modifications: ENHT0085, T0179 */

/**
 * Abstract class extending the JAAS princiapl to add a name field.
 */
public abstract class AbstractPrincipal
	implements Principal, Serializable
{
	private String _name;

	/**
	 * Constructor for this abstract class.
	 * 
	 * @param name
	 *        the name of the principal.
	 */
	protected AbstractPrincipal(String name)
	{
		_name = name;
	}

	/**
	 * Returns the Name.
	 * 
	 * @return the Name value.
	 */
	public String getName()
	{
		return _name;
	}

	/**
	 * Overrides the Object.equals() method.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof AbstractPrincipal))
			return false;

		AbstractPrincipal principal = (AbstractPrincipal) obj;

		return _name == null ? principal.getName() == null : _name.equals(principal.getName());
	}

	/**
	 * This method matches the overridden behavior of the <code>equals()</code>
	 * method; it guarantees that equal instances of this object will have the
	 * same hash codes.
	 * 
	 * @see #equals(Object)
	 */
	@Override
	public int hashCode()
	{
		if (_name == null) {
			// Always return the same prime number; this is appropriate, because
			// any two instances of this object with _name being null, are
			// considered equal
			return 0x1cfaa2db;
		}
		else {
			return _name.hashCode();
		}
	}
}
