package com.csc.fsg.life.biz.util;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.csc.fsg.life.biz.exception.WrapperException;

/**
   Utility class for general helper methods.
 */
public class Utils
{
    private Utils()
    {
        // This is a static class, not to be instantiated.
    }

	/**
	   Splits a string into tokens using the delimiter passed in.  The string may 
	   contain double quotes to ignore the delimiter with a token.
	   @param str The string to split.
	   @param delimiter the delimiter character.
	   @return the list of tokens found.
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
	   Returns true if the string is null or empty.
	   @param value The string to check.
	   @return true if the string is null or empty.
	**/
    public static boolean isBlank(String value)
    {
        if (value == null || value.trim().length() == 0)
            return true;

        return false;
    }
    
    /**
     * Capitalize the first character of the given string.
     * @param str string to capitalize
     * @return
     */
    public static String capitalize(String str)
    {
        return changeFirstCharacterCase(str, true);
    }
    
    /**
     * Uncapitalize the first character of the given string.
     * @param str string to uncapitalize
     * @return
     */
    public static String uncapitalize(String str) 
    {
        return changeFirstCharacterCase(str, false);
    }
    
    private static String changeFirstCharacterCase(String str, boolean capitalize) 
    {
        if (str == null || str.length() == 0) {
            return str;
        }
        StringBuffer buf = new StringBuffer(str.length());
        if (capitalize) {
            buf.append(Character.toUpperCase(str.charAt(0)));
        }
        else {
            buf.append(Character.toLowerCase(str.charAt(0)));
        }
        buf.append(str.substring(1));
        return buf.toString();
    }

    /**
     * Creates, loads and returns the properties from the URL provided.
     * 
     * @throws WrapperException
     *             When unable to load the properties.
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
