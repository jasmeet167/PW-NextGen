package com.csc.fsg.life.configuration;

import com.csc.fsg.life.exceptions.BaseException;

/********************************************************
*  This class extends from BaseException and		
*  Base Exception is extending from java.lang.Exception 
*********************************************************/
public class ConfigException 
	extends BaseException 
{
	/**
	   Creates a ConfigException without a message.
	**/
	public ConfigException() {
		super();
	}

	/**
	   Creates a ConfigException with another exception.
	   @param  e Exceptino to get a message from.
	**/
	public ConfigException(Exception e) {
		super( "" + e );
	}

	/**
	   Creates a ConfigException with a message.
	   @param s Exception message.
	**/
	public ConfigException(String s) {
		super( s );
	}
}
