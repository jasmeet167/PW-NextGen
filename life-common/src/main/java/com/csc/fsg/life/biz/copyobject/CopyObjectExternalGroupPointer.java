package com.csc.fsg.life.biz.copyobject;

import java.io.Serializable;
import java.lang.reflect.Method;

/* Modifications: T0140 */

/**
 * An instance of this object can be used to uniquely identify an external group
 * within a copy object. This is done by combining two pieces of information:
 * <ul>
 * <li>Method, which, when applied to a specific instance of a copy object, will
 * return the external group.</li>
 * <li>Optionally, if the external group is a list, i.e. it corresponds to a
 * copybook item associated with the <code>OCCURRS</code> clause, the index
 * value pointing to a specific element in the list.</li>
 * </ul>
 */
public class CopyObjectExternalGroupPointer
	implements Serializable
{
	private static final long serialVersionUID = 8590934582768192475L;

	private String groupAccessMethodName = null;
	private Integer groupListElementIdx = null;

	/**
	 * Constructor.
	 */
	public CopyObjectExternalGroupPointer(Method groupAccessMethod)
	{
		this.groupAccessMethodName = groupAccessMethod.getName();
		this.groupListElementIdx = null;
	}

	/**
	 * Constructor.
	 */
	public CopyObjectExternalGroupPointer(Method groupAccessMethod, int groupListElementIdx)
	{
		this.groupAccessMethodName = groupAccessMethod.getName();
		this.groupListElementIdx = Integer.valueOf(groupListElementIdx);
	}

	/**
	 * @return A flag indicating whether the given <code>obj</code> is an
	 *         instance of <code>CopyObjectExternalGroupPointer</code>, pointing
	 *         to the same copy object external group as this object.
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;

		if (obj == null || !(obj instanceof CopyObjectExternalGroupPointer))
			return false;

		CopyObjectExternalGroupPointer pointer = (CopyObjectExternalGroupPointer) obj;
		return this.toString().equals(pointer.toString());
	}

	/**
	 * Since this class overrides the <code>equals()</code> method, a custom
	 * implementation of <code>hashCode()</code> is also provided.
	 * 
	 * @return Customized value of hash code.
	 */
	@Override
	public int hashCode()
	{
		return toString().hashCode();
	}

	/**
	 * @return A String representation of this object.
	 */
	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();

		buf.append(groupAccessMethodName);
		buf.append("(#");
		buf.append(groupListElementIdx);
		buf.append(')');

		return buf.toString();
	}
}
