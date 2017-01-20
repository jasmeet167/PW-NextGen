package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

import com.csc.fsg.life.rest.model.ErrorModel;

abstract public class RestServiceException
	extends RuntimeException
{
	private static final long serialVersionUID = -52997031908373813L;

	public RestServiceException()
	{
		super();
	}

	public RestServiceException(String message)
	{
		super(message);
	}

	public String getErrorCode()
	{
		return String.valueOf(getHttpStatus().value());
	}

	abstract public HttpStatus getHttpStatus();

	abstract protected ErrorModel getErrorModel();
}
