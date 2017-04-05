package com.csc.fsg.life.rest.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException
	extends QuietRestServiceException
{
	private static final long serialVersionUID = 2132126582671458675L;

	static public final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;

	public UnauthorizedException()
	{
		super();
	}

	@Override
	public HttpStatus getHttpStatus()
	{
		return HTTP_STATUS;
	}
}
