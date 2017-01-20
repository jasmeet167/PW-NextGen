package com.csc.fsg.life.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* Modifications: T0140, ENH835, T0150 */

/**
   A helper class that is used to manipulate XML tags and attributes.
 */
@SuppressWarnings("unchecked")
public class TagHelper
{
	private static Log logger = LogFactory.getLog(TagHelper.class);

	/**
	   Get the text content of an element.
	   @param e The element.
	   @return the content as a string.
	**/
	static public String getText(Element e)
	{
		return getText(e, false);
	}

	/**
	   Get the text content of an element.
	   @param e The element.
	   @param recurse pass true to recurse into sub-elements.
	   @return the content as a string.
	**/
	static public String getText(Element e, boolean recurse)
	{
		NodeList children = e.getChildNodes();
		StringBuffer value = new StringBuffer();
		for(int j=0; j<children.getLength(); j++) {
			Node child = children.item(j);
			if(child.getNodeType() == Node.TEXT_NODE)
				value.append(child.getNodeValue());
			else if (child.getNodeType() == Node.ELEMENT_NODE)
				value.append(getText((Element)child, true));
		}
		return normalize(value.toString().trim());
	}

	// Static map of any entites found in the normalized
	// strings.  Really, just used for debugging.
	static private Map<String, String> m_entities = new HashMap<String, String>();

	/**
	   Normalizes the given string.  This converts unsafe ascii characters into entities.
	   @param s the string to normalize.
	   @return The normailzed string.
	**/
	static public String normalize(String s)
	{
		StringBuffer str = new StringBuffer();

		final String goodChars =
			"!#$%'()*+,-./:;=?@[\\]^_`{|}~";

		int len = (s != null) ? s.length() : 0;
		for ( int i = 0; i < len; i++ ) {
			char ch = s.charAt(i);

			// If known good characters then add and continue.
			if (Character.isLetterOrDigit(ch) ||
				Character.isWhitespace(ch) ||
				(goodChars.indexOf(ch) >= 0) )
			{
				str.append(ch);
				continue;
			}

			switch ( ch ) {
			case '<': {
				str.append("&lt;");
				break;
			}
			case '>': {
				str.append("&gt;");
				break;
			}
			case '&': {
				str.append("&amp;");
				break;
			}
			case '"': {
				str.append("&quot;");
				break;
			}

			default:
				{
					// Non-printable characters.
					if ((ch == 163) || (ch == 174) ||
						((ch >= 8211) && (ch <= 8226)))
					{
						String hexStr = Integer.toHexString(ch);
						String ent = "&#x" + hexStr + ";";
						// CAC: Just ax them for now.
						//str.append(ent);
						if (m_entities.get(hexStr) == null)
						{
							m_entities.put(hexStr, ent);

							// printing is used for debugging.
							logger.debug(ch + " " +
										 (int)ch + " " +
										 Integer.toHexString(ch) + " -> " +
										 ent);
						}
					}
					else
					{
						String msg = "Bad char in normalize: " + ch + " " +
							(int)ch + " " +
							Integer.toHexString(ch) + " '" +
							s + "'";
						logger.error(msg);
						throw new Error(msg);
					}
				}
			}
		}

		String retStr = str.toString();

		// Uncomment to help with debugging.
		//if (retStr.equals(s) == false)
		// {
		//   logger.debug("Old: '" + s + "'");
		//   logger.debug("New: '" + retStr + "'");
		//}

		return retStr;
	}


	/**
	   Constant used to help put JSP out inside of start tags.
	**/
	static public final String VERBATIM_ATTR = "__VERBATIM__";

	/**
	   Gets the starting tag of an element as a string.
	   @param e The element to get a tag name from.
	   @return the start tag.
	**/
	static public String getElementStart(Element e)
	{ return getElementStart(e.getTagName(), null, false); }

	/**
	   Gets the starting tag of an element as a string useing the 
	   map for attributes.
	   @param e The element to get a tag name from.
	   @param attrs A map of attributes to put on the tag.
	   @param empty Should this be created as an empty tag?
	   @return the start tag.
	**/
	static public String getElementStart(Element e, Map attrs, boolean empty)
	{ return getElementStart(e.getTagName(), attrs, empty); }

	/**
	   Gets the starting tag of an element as a string.
	   @param tagName The tag name.
	   @param attrs A map of attributes to put on the tag.
	   @param empty Should this be created as an empty tag?
	   @return the start tag.
	**/
	static public String getElementStart(String tagName, Map attrs, boolean empty)
	{ return getElementStart(tagName, attrs, empty, null); }

	/**
	   Gets the starting tag of an element as a string.
	   @param tagName The tag name.
	   @param attrs A map of attributes to put on the tag.
	   @param empty Should this be created as an empty tag?
	   @param fieldNameMap a map used to translate tagName to alias names.
	   @return the start tag.
	**/
	static public String getElementStart(String tagName, Map attrs, boolean empty, Map fieldNameMap)
	{
		String tn = mapTagName(tagName, fieldNameMap);

		StringBuffer buf = new StringBuffer(87);
		buf.append("<");
		buf.append(tn);

		if (attrs != null)
		{
			Set entries = attrs.entrySet();
			for(Iterator itt = entries.iterator(); itt.hasNext();)
			{
				Map.Entry n = (Map.Entry)itt.next();
				String key = (String)n.getKey();
				String value = (String)n.getValue();
				buf.append(" ");
				if (key.equals(VERBATIM_ATTR) == false)
				{
					buf.append(key);
					buf.append("=\"");
					buf.append(value);
					buf.append("\"");
				}
				else
				{
					buf.append(value);
				}
			}
		}

		if (empty)
			buf.append("/>");
		else
			buf.append(">");

		return buf.toString();
	}

	/**
	   Gets the ending tag of an element.
	**/
	static public String getElementEnd(Element e)
	{ return getElementEnd(e.getTagName()); }

	/**
	   Gets the ending tag of an element as a string.
	   @param e The element to get a tag name from.
	   @param fieldNameMap a map used to translate tagName to alias names.
	   @return the end tag.
	**/
	static public String getElementEnd(Element e, Map fieldNameMap)
	{ return getElementEnd(e.getTagName(), fieldNameMap); }

	/**
	   Gets the ending tag of an element as a string.
	   @param tagName The tag name.
	**/
	static public String getElementEnd(String tagName)
	{ return getElementEnd(tagName, null); }

	/**
	   Gets the ending tag of an element as a string.
	   @param tagName The tag name.
	   @param fieldNameMap a map used to translate tagName to alias names.
	   @return the end tag.
	**/
	static public String getElementEnd(String tagName, Map fieldNameMap)
	{ return "</" + mapTagName(tagName, fieldNameMap) + ">";  }


	/**
	   Translates one tag name to another using the map.
	   @param tagName the tag name to translate.
	   @param fieldNameMap the map.
	   @return tagName if it isn't found in the map, otherwise the
	   translated name from the map.
	**/
	static private String mapTagName(String tagName, Map fieldNameMap)
	{
		if (fieldNameMap == null)
			return tagName;

		String tn = (String)fieldNameMap.get(tagName.toLowerCase());
		if (tn == null)
			return tagName;

		return tn;
	}

	/**
	   Writes an element sub-tree to a string.
	   CAC: Note this doesn't yet handle attributes.
	**/
	static public String toDom(Element e, String indent)
	{ return toDom(e, indent, null); }

	/**
	   Writes an element sub-tree to a string.
	   CAC: Note this doesn't yet handle attributes.
	**/
	static public String toDom(Element e, String indent, Map fieldNameMap)
	{
		StringBuffer buf = new StringBuffer(89);
		buf.append(indent + getElementStart(e.getTagName(), null, false, fieldNameMap));
		buf.append(toDomChildrenOnly(e, indent + "  ", fieldNameMap));
		buf.append(indent + getElementEnd(e, fieldNameMap));
		return buf.toString();
	}


	/**
	   Writes an element sub-tree to a string.  As opposed to toDom(), this
	   method only writes the contents of the element 'e', not tags for the
	   element 'e'.
	   CAC: Note this doesn't yet handle attributes.
	**/
	static public String toDomChildrenOnly(Element e, String indent)
	{ return toDomChildrenOnly(e, indent, null); }

	/**
	   Writes an element sub-tree to a string.  As opposed to toDom(), this
	   method only writes the contents of the element 'e', not tags for the
	   element 'e'.
	   CAC: Note this doesn't yet handle attributes.
	**/
	static public String toDomChildrenOnly(Element e, String indent, Map fieldNameMap)
	{
		StringBuffer buf = new StringBuffer(89);

		NodeList children = e.getChildNodes();
		for(int j=0; j<children.getLength(); j++)
		{
			Node child = children.item(j);
			if(child.getNodeType() == Node.TEXT_NODE)
			{
				buf.append(normalize(child.getNodeValue()));
				buf.append("\n");
			}
			else if (child.getNodeType() == Node.ELEMENT_NODE)
				buf.append(toDom((Element)child, indent + "  ", fieldNameMap));
		}

		return buf.toString();
	}


	/**
	   Gets a attribute value that is required.  If the attribute
	   is not specified then an Error is thrown.
	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @return the attributes value.
	 **/
	static public String getRequiredValue(Element e, String attr)
	{
		return getValue(e, attr, true);
	}

	/**
	   Gets a attribute value that is optional.  If the attribute
	   is not specified then null is returned.
	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @return the attributes value.
	 **/
	static public String getOptionalValue(Element e, String attr)
	{
		return getValue(e, attr, false);
	}
	
	
	static public String getOptionalStringValue(Element e, String attr)
	{
		String value= getValue(e, attr, false);
		if (value == null || value.equals("")|| value.trim().equalsIgnoreCase("N")|| value.trim().equalsIgnoreCase("n")) 
			return "N";
		else return value;
	}

	/**
	   Gets an integer attribute value.
	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @return the attributes value as a integer.
	**/
	static public int getRequiredInt(Element e, String attr)
	{
		return getValueInt(e, attr, true);
	}

	/**
	   Gets an integer attribute value that is optional.  If the attribute
	   is not specified then the default will be returned.
	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @param defaultValue the default value to return if the attribute is not present.
	   @return the attributes value as a integer.
	**/
	static public int getOptionalInt(Element e, String attr, int defaultValue)
	{
		try {
			return getValueInt(e, attr, false);
		}
		catch (Exception ex) {
			return defaultValue;
		}
	}

	/**
	   Gets a boolean attribute value that is optional.  If the attribute
	   is not specified then the default will be returned.
	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @param defaultVal the default value to return if the attribute is not present.
	   @return the attributes value as a boolean.
	**/
	static public boolean getOptionalBooleanValue(Element e,
												  String  attr,
												  boolean defaultVal)
	{
		String value = getValue(e, attr, false);
		if (value == null || value.equals("")) return defaultVal;
		return _getBoolean(value);
	}

	/**
	   Gets a boolean attribute that may be required.  If the required
	   parameter is true and the attribute isn't there an error will be
	   thrown. If the required parameter is false and the attribute isn't
	   there 'false' will be retured.

	   Note: If you don't need to require the attribute the use the
	         method: getOptionalBooleanValue() so you can specify the
			 default.

	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @param required true if this attribute is required.

	   @return the attributes value as a boolean.
	**/
	static public boolean getBooleanValue(Element e,
										  String  attr,
										  boolean required)
	{
		String value = getValue(e, attr, required);
		if (value == null) return false;
		return _getBoolean(value);
	}

	/**
	   Turns a string of into a boolean value.  A Y or T is considered true, 
	   any other value is considered false.
	   @return the boolean value for the string.
	**/
	static private boolean _getBoolean(String value)
	{
		value = value.toLowerCase();
		if (value.startsWith("y") || value.startsWith("t"))
			return true;

		return false;
	}


	/**
	   Returns the specified attribute from the element.
	   If the attribute is marked as required and not found then an Error is thrown.
	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @param required true if this attribute is required.
	   @return the attributes value.
	 **/
	static public String getValue(Element e,
								  String  attr,
								  boolean required)
	{
		String value = e.getAttribute(attr);
		if (required)
			if ((value == null) || (value.length() <= 0))
				throw new Error("Error: atribute: " + attr +
								" on tag: " + e.getTagName() +
								" has an empty value");
		return value;
	}

	/**
	   Returns the specified attribute from the element as an integer.
	   If the attribute is marked as required and not found then an Error is thrown.
	   @param e The element. 
	   @param attr The attribute to get the value from.
	   @param required true if this attribute is required.
	   @return the attributes value.
	**/
	static public int getValueInt(Element e,
								  String  attr,
								  boolean required)
	{
		return Integer.parseInt( getValue(e,attr,required));
	}
}
