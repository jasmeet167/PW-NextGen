package com.csc.fsg.life.biz.service;

/* Modifications: ENH1220 */

public class DuplicateKeyException
	extends BusinessServiceException
{
	private static final long serialVersionUID = -7769902263863567248L;

	public DuplicateKeyException()
	{
		super();
	}

	public DuplicateKeyException(String msg)
	{
		super(msg);
	}
}
