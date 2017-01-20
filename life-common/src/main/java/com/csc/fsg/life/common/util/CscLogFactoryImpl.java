package com.csc.fsg.life.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.impl.LogFactoryImpl;


/**
  This class is used to override applications servers that
  customize apache commons logging.   Specifically, web sphere
  version 5+.  To use this class create a configuration file in 
  your web application lcoated at.
  <pre>
  webApplication/META-INF/services/org.apache.commons.logging.LogFactory
  </pre>
  The content of the file should be a single line with this classes
  fully qualified class name.
**/
public class CscLogFactoryImpl
	extends LogFactoryImpl
{
    /**
	   Override getInstance() to force to log4j.
	**/
	public Log getInstance(String name)
		throws LogConfigurationException 
	{
		if (logConstructor == null)
		{
			// Force to log4j
			setAttribute(LOG_PROPERTY, "org.apache.commons.logging.impl.Log4JLogger");
		}
		return super.getInstance(name);
	}
}
