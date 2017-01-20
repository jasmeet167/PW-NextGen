package com.csc.fsg.life.common.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: T0175 */

public class StreamHelper
{
    private static final Log log = LogFactory.getLog("com.csc.fsg");

    /**
	 * A utility method, which provides generic functionality to safely close
	 * the provided instance of <code>InputStream</code>.
	 */
	public static void safeClose(InputStream iStream)
	{
		if (iStream != null) {
			try {
				iStream.close();
			}
			catch (IOException e) {
				log.error(e);
			}
		}
	}
}
