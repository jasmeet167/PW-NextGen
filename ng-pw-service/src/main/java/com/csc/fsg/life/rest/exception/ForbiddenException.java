package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException
	extends QuietRestServiceException
{
	static private final long serialVersionUID = -9138358580023396542L;

	static public HttpStatus HTTP_STATUS = HttpStatus.FORBIDDEN;

	public ForbiddenException()
	{
		super();
	}

	@Override
	public HttpStatus getHttpStatus()
	{
		return HTTP_STATUS;
	}
}
