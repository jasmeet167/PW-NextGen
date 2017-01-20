package com.csc.fsg.life.tools.xml.simple;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 * This interface facilitates access to the Element creation functionality of an
 * XML DOM Document, while hiding methods used for manipulation of structure or
 * contents of Document.
 */
public interface SimpleXMLElementFactory
{
	/**
	 * Create an element of the type specified.
	 * 
	 * @see Document#createElement(String) createElement
	 */
	public Element createElement(String tagName);
}
