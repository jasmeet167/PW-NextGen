package com.csc.fsg.life.biz.meta;

import java.util.Map;

/* Modifications: WMA-1559 */

/**
   Simple taglet used to put the commons attribute MetaData 
   into the javadoc as a table.
**/
public class MetaDataTaglet
	extends AbstractMetaDataTaglet
{

    /**
     * Register this specified Taglet.
     * @param tagletMap  the map to register this tag to.
     */
    public static void register(Map<String, AbstractMetaDataTaglet> tagletMap) 
	{
		register(tagletMap, new MetaDataTaglet());
	}

	/**
	   Returns the commons attribute tag name.
	**/
	public String getAttributeName()
	{
		return "MetaData";
	}

	/**
	   Returns the title to be used for the Javadoc section.
	**/
	public String getTagTitle()
	{
		return "Property Meta Data";
	}

	/**
	   Translates an attribute name to a display version to be used in the java doc.
	   @param name The attribute name
	   @return The translated name. 
	**/
	public String translate(String name)
	{
		if (name.equalsIgnoreCase("size"))
			return "Size";
		else if (name.equalsIgnoreCase("fieldSequence"))
			return "FieldSequence";
		else if (name.equalsIgnoreCase("required"))
			return "Required";
		else if (name.equalsIgnoreCase("scale"))
			return "Scale";
		else if (name.equalsIgnoreCase("avref"))
			return "Allowable Value Reference";
		else
			return name;
	}
}
