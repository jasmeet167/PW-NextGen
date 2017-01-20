package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

import com.csc.fsg.life.rest.model.ErrorModel;

public class UnexpectedException
	extends VerboseRestServiceException
{
	static private final long serialVersionUID = -3200162802498998964L;

	static public HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

	private ErrorModel errorModel = null;

	public UnexpectedException(ErrorModel errorModel)
	{
		super(errorModel.getMessage());
		this.errorModel = errorModel;
	}

	public HttpStatus getHttpStatus()
	{
		return HTTP_STATUS;
	}

	protected ErrorModel getErrorModel()
	{
		return errorModel;
	}
}
