package com.csc.fsg.life.biz.dao;

import org.springframework.dao.DataAccessException;

/* Modifications: WMA-1638 */

public class DuplicateMessageKeyException
	extends DataAccessException
{
	private static final long serialVersionUID = -2743362641879445106L;

	public DuplicateMessageKeyException(String msg)
	{
		super(msg);
	}

	public DuplicateMessageKeyException(String msg, Throwable cause)
	{
		super(msg, cause);
	}
}
