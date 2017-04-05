package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

import com.csc.fsg.life.rest.model.ErrorModel;

public class UnexpectedException
	extends VerboseRestServiceException
{
	static private final long serialVersionUID = -3200162802498998964L;

	static public final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	private ErrorModel errorModel = null;

	public UnexpectedException(ErrorModel errorModel)
	{
		super(errorModel.getMessage());
		this.errorModel = errorModel;
	}

	@Override
	public HttpStatus getHttpStatus()
	{
		return HTTP_STATUS;
	}

	@Override
	protected ErrorModel getErrorModel()
	{
		return errorModel;
	}
}
