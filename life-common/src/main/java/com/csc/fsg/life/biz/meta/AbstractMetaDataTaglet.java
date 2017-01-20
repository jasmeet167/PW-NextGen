package com.csc.fsg.life.biz.meta;

import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

import com.sun.javadoc.Tag;
import com.sun.tools.doclets.Taglet;

/* Modifications: T0140 */

/**
   Abstract base class used to define apache commons attribute
   taglets.
**/
abstract public class AbstractMetaDataTaglet
	implements Taglet
{
	/**
	   Implement and return the commons attribute tag name.
	**/
	abstract public String getAttributeName();

	/**
	   Implement this and return the title to be used for the 
	   Javadoc section.
	**/
	abstract public String getTagTitle();


	/**
     * Return the name of this custom tag.
     */
    public String getName() 
	{
        return "@@" + getAttributeName();
    }

	/**
	   Translates an attribute name to a dipslay version to be used in the java doc.
	   @param name The attribute name
	   @return The translated name. 
	**/
	abstract public String translate(String name);
    
    /**
     * Will return true since <code>@@MetaData</code>
     * can be used in field documentation.
     * @return true since <code>@@MetaData</code>
     * can be used in field documentation.
     */
    public boolean inField() {
        return true;
    }

    /**
     * Will return false since <code>@@MetaData</code>
     * can not be used in constructor documentation.
     * @return false since <code>@@MetaData</code>
     * can not be used in constructor documentation.
     */
    public boolean inConstructor() {
        return true;
    }
    
    /**
     * Will return true since <code>@@MetaData</code>
     * can be used in method documentation.
     * @return true since <code>@@MetaData</code>
     * can be used in method documentation.
     */
    public boolean inMethod() {
        return true;
    }
    
    /**
     * Will return false since <code>@@MetaData</code>
     * can not be used in method documentation.
     * @return false since <code>@@MetaData</code>
     * can not be used in overview documentation.
     */
    public boolean inOverview() {
        return true;
    }

    /**
     * Will return false since <code>@@MetaData</code>
     * can not be used in package documentation.
     * @return false since <code>@@MetaData</code>
     * can not be used in package documentation.
     */
    public boolean inPackage() {
        return true;
    }

    /**
     * Will return true since <code>@@MetaData</code>
     * can be used in type documentation (classes or interfaces).
     * @return true since <code>@@MetaData</code>
     * can be used in type documentation and false
     * otherwise.
     */
    public boolean inType() {
        return true;
    }
    
    /**
     * Will return false since <code>@@MetaData</code>
     * is not an inline tag.
     * @return false since <code>@@MetaData</code>
     * is not an inline tag.
     */
    public boolean isInlineTag() {
        return false;
    }
    
    /**
     * Register the specified Taglet.
     * @param tagletMap  the map to register this tag to.
	 * @param tag The taglet to register.
     */
    public static void register(Map<String, AbstractMetaDataTaglet> tagletMap, AbstractMetaDataTaglet tag) 
	{
		String tagName = "@" + tag.getAttributeName();
		Taglet t = tagletMap.get(tagName);
		if (t != null) {
			tagletMap.remove(tagName);
		}
		tagletMap.put(tagName, tag);
    }

    /**
     * Given the <code>Tag</code> representation of this custom
     * tag, return its string representation.
     * @param tag   the <code>Tag</code> representation of this custom tag.
     */
    public String toString(Tag tag) 
	{
		Tag tags[] = new Tag[1];
		tags[0] = tag;
		return toString(tags);
    }
	
    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
     */
    public String toString(Tag[] tags) 
	{
        if (tags.length == 0) {
            return null;
        }

        StringBuffer result = new StringBuffer("\n<DT><B>" + getTagTitle() + "</B><DD>");
        result.append("<table cellpadding=2 cellspacing=0 border=1 width=200>");
        for (int i = 0; i < tags.length; i++) {
			result.append(displayTag(tags[i]));
        }
        result.append("</table></DD>\n");
        return result.toString();
    }

	/**
	   Renders a Tag into HTML table rows for the Javadoc.
	   @param tag The tag to render.
	   @return the string of HTML rendered.
	**/
	@SuppressWarnings("unchecked")
	private String displayTag(Tag tag)
	{
		StringBuffer buf = new StringBuffer();
		
		// Parse attributes into map.
		Map<String, String> attrMap = parseText(tag.text());

		for (Iterator itt = attrMap.entrySet().iterator(); itt.hasNext();) {
			Map.Entry nextEntry = (Map.Entry)itt.next();
			buf.append("<tr>");
			buf.append("<td>");
			buf.append(nextEntry.getKey());
			buf.append("</td>");
			buf.append("<td>");
			buf.append(nextEntry.getValue());
			buf.append("</td>");
			buf.append("</tr>");
		}

		return buf.toString();
	}

	/**
	   Parses apache commons attribute format from text into a map.
	   @param text The attribute text in teh form of
	   <CODE>(name1=value, name2=value, ...)</CODE>.
	   @return a map of name/value pairs.
	**/
	private Map<String, String> parseText(String text)
	{
		// Remove the starting and ending parenthesis.
		String work = text.trim();
		if (work.startsWith("("))
			work = work.substring(1);
		if (work.endsWith(")"))
			work = work.substring(0, (work.length() - 1));
		
		TreeMap<String, String> map = new TreeMap<String, String>();

		// Tokenize by attributes and add to the map.
		StringTokenizer tok = new StringTokenizer(work, ",");
		while(tok.hasMoreElements()) {
			String attr = tok.nextToken();
			attr = attr.trim();
			int idx = attr.indexOf("=");
			String name = "";
			String value = "";
			if (idx > -1) {
				name = attr.substring(0, idx);
				value = attr.substring(idx+1);
			}
			else
				name = attr;

			name = name.trim();
			value = value.trim();
			map.put(translate(name), value);
		}
		
		return map;
	}
}
