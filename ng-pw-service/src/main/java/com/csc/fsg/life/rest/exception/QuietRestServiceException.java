package com.csc.fsg.life.rest.exception;

import com.csc.fsg.life.rest.model.ErrorModel;

abstract public class QuietRestServiceException
	extends RestServiceException
{
	private static final long serialVersionUID = -6873731241803759594L;

	@Override
	protected ErrorModel getErrorModel()
	{
		return null;
	}
}
