package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

import com.csc.fsg.life.rest.model.ErrorModel;

public class ForbiddenException
	extends VerboseRestServiceException
{
	static private final long serialVersionUID = -9138358580023396542L;

	static public final HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

	private ErrorModel errorModel = null;

	public ForbiddenException()
	{
		this(null);
	}

	public ForbiddenException(ErrorModel errorModel)
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
