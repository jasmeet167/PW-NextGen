package com.csc.fsg.life.biz.copyobject;

/* Modifications: T0150 */

public class InvalidUnsignedNumericValueException
	extends CopyObjectException
{
	private static final long serialVersionUID = -948250248257157531L;

	/**
	 * Construct a new instance of this exception.
	 */
	public InvalidUnsignedNumericValueException(String msg)
	{
		super(msg);
	}
}
