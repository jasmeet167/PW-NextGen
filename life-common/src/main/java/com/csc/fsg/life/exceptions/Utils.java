package com.csc.fsg.life.exceptions;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
   Generic utility class.
 */
public class Utils
{
    private Utils()
    {
        // This is a static class, not to be instantiated.
    }

	/**
	   Splits a string into a list of tokens.  The specified delimiter
	   character is used to split into tokens.  It also handles 
	   tokens that are quoted where it will ignore the delimiter 
	   inside a quoted string.
	**/
    public static List<String> splitQuoted(String str, char delimiter)
    {
        List<String> tokens = new ArrayList<String>();

        if (isBlank(str))
            return tokens;

        StringBuffer sb = new StringBuffer(str);

        int start = 0, ch;
        boolean quoted = false;

        for (int i = 0; i < sb.length(); i++)
        {
            ch = sb.charAt(i);

            if (ch == '"')
            {
                quoted = !quoted;
                continue;
            }

            if (quoted)
                continue;

            if (ch == delimiter)
            {
                tokens.add(sb.substring(start, i).trim());
                start = i + 1;
            }
        }

        tokens.add(sb.substring(start).trim());

        return tokens;
    }

	/**
	   Returns true if the string is null or contains
	   only whitespace, false otherwise.
	   @param value The string to check.
	   @return true for a blank string.
	**/
    public static boolean isBlank(String value)
    {
        if (value == null || value.trim().length() == 0)
            return true;

        return false;
    }

    /**
	   Creates, loads and returns the properties from the URL provided.
	   @param url The url to load properties from.
	   @throws WrapperException When unable to load the properties.
     */
    public static Properties loadProperties(URL url)
    {
        try
        {
            Properties props = new Properties();

            props.load(url.openStream());

            return props;
        }
        catch (Exception e)
        {
            throw WrapperException.wrap(e, Message.CANNOT_LOAD_PROPERTIES, url);
        }
    }
}
