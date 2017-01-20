package com.csc.fsg.life.rest.exception;

abstract public class VerboseRestServiceException
	extends RestServiceException
{
	private static final long serialVersionUID = 738522723976391770L;

	public VerboseRestServiceException(String message)
	{
		super(message);
	}
}
