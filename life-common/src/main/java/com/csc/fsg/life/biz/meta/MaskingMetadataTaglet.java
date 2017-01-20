package com.csc.fsg.life.biz.meta;

import java.util.Map;

/* Modifications: ENH1019 */

/**
 * A taglet used to put the Data Masking Metadata into the javadoc.
 */
public class MaskingMetadataTaglet
	extends AbstractMetaDataTaglet
{
	/**
	 * Register this specified Taglet.
	 * 
	 * @param tagletMap
	 *        The map to register this tag to.
	 */
	public static void register(Map<String, AbstractMetaDataTaglet> tagletMap)
	{
		register(tagletMap, new MetaDataTaglet());
	}

	/**
	 * Returns the tag name.
	 */
	@Override
	public String getAttributeName()
	{
		return "MaskingMetadata";
	}

	/**
	 * Returns the title to be used for the Javadoc section.
	 */
	@Override
	public String getTagTitle()
	{
		return "Data Masking Metadata";
	}

	/**
	 * Translates an attribute name to a display version to be used in the java
	 * doc.
	 * 
	 * @param name
	 *        The attribute name
	 * @return The translated name.
	 */
	@Override
	public String translate(String name)
	{
		if ("maskOption".equals(name))
			return "Masking Option";
		if ("maskDigits".equals(name))
			return "Mask Digits";
		else
			return name;
	}
}
