package com.csc.fsg.life.common.util;

import org.springframework.util.StringUtils;

/* Modifications: T0158 */

/**
   Simple class that represents a reference to a
   field in a a class.
**/
public class ObjectFieldRef
{
	private String m_objectFieldName = "";
	private String m_objectFieldClass = "";
	private String m_objectFieldCastClass = "";

	/**
	   Creates an ObjectFieldRef. 
	**/
	public ObjectFieldRef(String objectFieldName,String objectFieldClass)
	{
		m_objectFieldName 	= objectFieldName;
		m_objectFieldClass 	= objectFieldClass;		
	}

	/**
	 * Creates an ObjectFieldRef.
	 */
	public ObjectFieldRef(String objectFieldName, String objectFieldClass, String objectFieldCastClass)
	{
		this(objectFieldName, objectFieldClass);
		m_objectFieldCastClass = objectFieldCastClass;
	}

	/**
	   The object field.
	   @return the field name.
	**/
	public String getObjectFieldName()
	{
		return m_objectFieldName;
	}
	
	/**
	   Returns the bean getter name for this object field ref.
	**/
	public String getObjectFieldGetter()
	{
		String name = m_objectFieldName;
		name = Character.toUpperCase(name.charAt(0)) + name.substring(1);
		return "get" + name;
	}

	/**
	   The class the field reference is on.
	   @return the class name.
	**/
	public String getObjectFieldClass()
	{
		return m_objectFieldClass;
	}
	
	public boolean hasObjectFieldCastClass()
	{
		return StringUtils.hasText(m_objectFieldCastClass);
	}

	public String getObjectFieldCastClass()
	{
		return m_objectFieldCastClass;
	}

	@Override
	public String toString()
	{
		String stringRepresentation = "";
		if (hasObjectFieldCastClass())
			stringRepresentation += "(" + m_objectFieldCastClass + ") ";

		stringRepresentation += m_objectFieldClass + "." + m_objectFieldName;
		return stringRepresentation;
	}

	public int hashCode()
	{
		return (m_objectFieldClass + m_objectFieldName).hashCode();
	}

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		
		if (obj instanceof ObjectFieldRef) {
			ObjectFieldRef obj2 = (ObjectFieldRef)obj;
			if (m_objectFieldClass.equals(obj2.getObjectFieldClass()) &&
				m_objectFieldName.equals(obj2.getObjectFieldName()))
				return true;
		}
		return false;
	}
}
