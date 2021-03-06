package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

import com.csc.fsg.life.rest.model.ErrorModel;

public class NotFoundException
	extends VerboseRestServiceException
{
	static private final long serialVersionUID = -4580137477303021141L;

	static public final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

	private ErrorModel errorModel = null;

	public NotFoundException(ErrorModel errorModel)
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
