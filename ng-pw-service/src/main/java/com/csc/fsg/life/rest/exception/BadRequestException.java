package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

import com.csc.fsg.life.rest.model.ErrorModel;

public class BadRequestException
	extends VerboseRestServiceException
{
	static private final long serialVersionUID = -6887132179428237225L;

	static public final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

	private ErrorModel errorModel = null;

	public BadRequestException(ErrorModel errorModel)
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
