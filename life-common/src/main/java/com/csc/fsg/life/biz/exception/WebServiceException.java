package com.csc.fsg.life.biz.exception;


public class  WebServiceException
	extends BusinessException
{

	private String mapName = "";
	
	/**
	 * Constructor for  WebServiceException
	 */
	public WebServiceException(String mapName, Throwable cause)
	{
		super(cause);
		this.mapName = mapName;
	}

	/**
	 * Constructor for  WebServiceException
	 */
	public  WebServiceException(String mapName)
	{
		super();
		this.mapName = mapName;
	}
	
	public String getMap() { return mapName; }
	
}

