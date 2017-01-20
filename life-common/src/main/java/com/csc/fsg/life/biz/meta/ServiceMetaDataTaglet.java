package com.csc.fsg.life.biz.meta;

import java.util.Map;


/**
   Simple taglet used to put the commons attribute ServiceMetaData 
   into the javadoc as a table.
**/
public class ServiceMetaDataTaglet
	extends AbstractMetaDataTaglet
{

    /**
     * Register this specified Taglet.
     * @param tagletMap  the map to register this tag to.
     */
    public static void register(Map<String, AbstractMetaDataTaglet> tagletMap) 
	{
		register(tagletMap, new ServiceMetaDataTaglet());
	}

	/**
	   Returns the commons attribute tag name.
	**/
	public String getAttributeName()
	{
		return "ServiceMetaData";
	}

	/**
	   Returns the title to be used for the Javadoc section.
	**/
	public String getTagTitle()
	{
		return "Service Meta Data";
	}

	/**
	   Translates an attribute name to a display version to be used in the java doc.
	   @param name The attribute name
	   @return The translated name. 
	**/
	public String translate(String name)
	{
		if (name.equalsIgnoreCase("functionalId"))
			return "Functional Identifier";
		else if (name.equalsIgnoreCase("eventType"))
			return "Event Type";
		else
			return name;
	}
}
