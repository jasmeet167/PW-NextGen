package com.csc.fsg.life.tools.xml;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/* Modifications: T0150 */

/**
 * Base class for XML Tools. Holds utility functions.
 */
public class XmlTool 
{

    /** logger for the XmlTool class. **/
    protected static final Log logger = LogFactory.getLog("com.csc.fsg");

	/** Flag used for debugging. */
	static final boolean DEBUG = false;

	/**
	 * Used for debugging.
	 * 
	 * @param msg the message
	 */
	public void log(String msg) 
	{
		if (DEBUG)
			logger.info(msg);
	}

	/**
	 * Builds a string that is a schema reference to an element with an optional
	 * occurrence.
	 * 
	 * @param name the name
	 * @param type the type
	 * @param indent the indent
	 * @return the string
	 */
	static public String buildOptionalElementRef(String name, String type,
			String indent) {

		return buildElementRef(name, type, "minOccurs=\"0\" maxOccurs=\"1\"",
				indent);
	}

	/**
	 * Builds a string that is a schema reference to an element.
	 * 
	 * @param name the name
	 * @param type the type
	 * @param options the options
	 * @param indent the indent
	 * @return the string
	 */
	static public String buildElementRef(String name, String type,
			String options, String indent) {

		return indent + "<xsd:element name=\"" + name + "\" \n" + indent
				+ "             type=\"" + type + "\" " + options + " /> \n";
	}

	/**
	 * Builds a string that is an optional schema attribute.
	 * 
	 * @param name the name
	 * @param type the type
	 * @param otherAttrs the other attrs
	 * @return the string
	 */
	static public String buildAttribute(String name, String type,
			String otherAttrs) {

		return "  <xsd:attribute " + "name=\"" + name + "\" " + "type=\""
				+ type + "\" " + "use=\"optional\" " + otherAttrs + "/>\n";
	}
}
