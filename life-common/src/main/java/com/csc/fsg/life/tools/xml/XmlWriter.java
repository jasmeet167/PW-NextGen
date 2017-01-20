package com.csc.fsg.life.tools.xml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* Modifications: T0106, T0175 */

/**
 * Formats a DOM document or input file for output
 */
public class XmlWriter {

    private static Log log = LogFactory.getLog("com.csc.fsg");

	private Document document = null;
	private StringBuffer sb = null;
	private int indent = 0;
	private String indentChar = "\t";
	private String lineEnd = System.getProperty("line.separator");
	private String m_rootElement = null;
	private String m_systemId = null;

	/** Constant for a tab indent. * */
	public static final String INDENT_TAB = "\t";
	/** Constat for a space indent. * */
	public static final String INDENT_SPACE = " ";

	/**
	 * Constructor for XmlWriter
	 */
	public XmlWriter() {

	}

	/**
	 * Constructor for XmlWriter
	 */
	public XmlWriter(Document doc) {

		this(doc, null, null);
	}

	/**
	 * Constructor for XmlWriter
	 */
	public XmlWriter(Document doc, String rootElement, String systemId) {

		m_rootElement = rootElement;
		m_systemId = systemId;
		this.document = doc;
	}

	/**
	 * Gets the value of a chosen attribute
	 * 
	 * @param elem Element
	 * @param str Attribute Name
	 * @return The desired attribute's value
	 */
	public String getTextFromLine(Node elem, String str) {

		String returnString = "";

		NamedNodeMap attrs = elem.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			if (attrs.item(i).getNodeName().equals(str)) {
				returnString = attrs.item(i).getNodeValue();
			}
		}

		return returnString;
	}

	/**
	 * Gets the NextSibling attribute of the XmlWriter object
	 * 
	 * @param node The node to get the next sibling for.
	 * @return The NextSibling value
	 */
	public Node getNextSibling(Node node) {

		return node.getNextSibling();
	}

	public void output(String outFile, String indentChar) {

		this.indentChar = indentChar;

		output(outFile);
	}

	/**
	 * Writes the current document out as an XML file
	 * 
	 * @param outFile Name of output file
	 */
	public void output(String outFile) {

		// verify parameters
		if (outFile == null) {
			log.info("You must specify an output file.");
			return;
		}

		BufferedWriter out = null;

		try {
			// rts: If you reenable these, make them debug only
			// log.debug("Reading document ... ");

			// print dom tree to buffer
			sb = new StringBuffer();
			printDOM(document, sb);

			// create output stream and write file
			out = new BufferedWriter(new FileWriter(outFile));
			out.write(sb.toString());

			// flush the buffer
			out.flush();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (out != null) {
				try {
					out.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Writes the current document out as String
	 */
	public String outputString() {

		StringBuffer docAsStr = new StringBuffer();
		printDOM(document, docAsStr);
		return docAsStr.toString();
	}

	/**
	 * Appends the needed amount of tabs for pretty output
	 * 
	 * @param indentBuf Output StringBuffer containing XML
	 */
	public void appendIndentation(StringBuffer indentBuf) {

		for (int i = 0; i < indent; i++) {
			indentBuf.append(indentChar);
		}
	}

	/**
	 * Create StringBuffer containing formatted XML
	 * 
	 * @param node Element to parse
	 * @param xmlBuf StringBuffer containing XML
	 */
	public void printDOM(Node node, StringBuffer xmlBuf) {

		int type = node.getNodeType();

		switch (type) {

		// handle the document element
		case Node.DOCUMENT_NODE: {
			// GML: should add xsl stylesheet that allows
			// comments through. Currently, when no stylesheet is defined, a
			// default stylesheet will be used which does not allow
			// comments.
			xmlBuf.append("<?xml version=\"1.0\"?>");
			xmlBuf.append(lineEnd);

			DocumentType docType = document.getDoctype();

			if (docType != null) {
				xmlBuf.append("<!DOCTYPE ");
				xmlBuf.append(docType.getName());
				if (docType.getSystemId() != null) {
					xmlBuf.append(" SYSTEM \"");
					xmlBuf.append(docType.getSystemId());
					xmlBuf.append("\"");
				}
				xmlBuf.append('>');
				xmlBuf.append(lineEnd);
			} else if (m_rootElement != null) {
				xmlBuf.append("<!DOCTYPE ");
				xmlBuf.append(m_rootElement);
				if (m_systemId != null) {
					xmlBuf.append(" SYSTEM \"");
					xmlBuf.append(m_systemId);
					xmlBuf.append("\"");
				}
				xmlBuf.append('>');
				xmlBuf.append(lineEnd);
			}

			// get children
			NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				for (int i = 0; i < len; i++) {
					printDOM(children.item(i), xmlBuf);
				}
			}

			break;
		}

			// handle element with attributes
		case Node.ELEMENT_NODE: {
			appendIndentation(xmlBuf);
			xmlBuf.append('<');
			indent++;

			xmlBuf.append(node.getNodeName());

			boolean endTag = false;
			if (!node.hasChildNodes()) {
				endTag = true;
			}

			// output namespace if found
			String uri = node.getNamespaceURI();
			Element docElement = node.getOwnerDocument().getDocumentElement();
			if (docElement.getNodeName().equals(node.getNodeName())
					&& uri != null) {
				if (node.getPrefix() == null) {
					xmlBuf.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
					xmlBuf.append(" xsi:noNamespaceSchemaLocation=\"");
					xmlBuf.append(uri);
					xmlBuf.append("\"");
				} else {
					xmlBuf.append(" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"");
					xmlBuf.append(" xsi:schemaLocation=\"");
					xmlBuf.append(uri);
					xmlBuf.append("\"");
				}
			}

			// get attributes
			NamedNodeMap attrs = node.getAttributes();
			for (int i = attrs.getLength() - 1; i >= 0; i--) {
				Node attr = attrs.item(i);
				String attrName = attr.getNodeName();
				String attrValue = attr.getNodeValue();
				if ((!attrName.startsWith("xmlns"))
						|| (attrValue.trim().length() > 0)) {
					xmlBuf.append(' ');
					xmlBuf.append(attrName);
					xmlBuf.append("=\"");
					xmlBuf.append(attrValue);
					xmlBuf.append("\"");
				}
			}

			if (endTag) {
				xmlBuf.append("/>");
				xmlBuf.append(lineEnd);
				indent--;
			} else {
				if (node.getFirstChild().getNodeType() == Node.TEXT_NODE
						&& node.getFirstChild().getNodeValue().trim().length() > 0) {
					xmlBuf.append('>');
				} else {
					xmlBuf.append('>');
					xmlBuf.append(lineEnd);
				}
			}

			// get children
			NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				for (int i = 0; i < len; i++) {
					printDOM(children.item(i), xmlBuf);
				}
			}
			break;
		}

			// handle entity reference nodes
		case Node.ENTITY_REFERENCE_NODE: {
			xmlBuf.append('&');
			xmlBuf.append(node.getNodeName());
			xmlBuf.append(';');
			break;
		}

			// handle cdata sections
		case Node.CDATA_SECTION_NODE: {
			appendIndentation(xmlBuf);

			xmlBuf.append("<![CDATA[");
			xmlBuf.append(node.getNodeValue());
			xmlBuf.append("]]>");
			xmlBuf.append(lineEnd);

			indent++;

			break;
		}

			// handle text
		case Node.TEXT_NODE: {
			if (node.getNodeValue().trim().length() > 0) {
				xmlBuf.append(node.getNodeValue());
			}

			break;
		}

			// handle processing instruction
		case Node.PROCESSING_INSTRUCTION_NODE: {
			// GML: see comment in DOCUMENT_NODE section
			if (log.isDebugEnabled()) {
				log.debug("Processing Instruction Name: "
					+ node.getNodeName());
				log.debug("Processing Instruction Value: "
					+ node.getNodeValue());
			}

			xmlBuf.append("<?");
			xmlBuf.append(node.getNodeName());
			String data = node.getNodeValue();
			if (data != null && data.length() > 0) {
				xmlBuf.append(' ');
				xmlBuf.append(data);
			}
			xmlBuf.append("?>");
			xmlBuf.append(lineEnd);

			// printDOM(node.getNextSibling(),xmlBuf);
			break;
		}
		}

		// handle end tags
		if (type == Node.ELEMENT_NODE) {
			if (node.getFirstChild() != null
					&& node.getFirstChild().getNodeType() == Node.TEXT_NODE
					&& node.getFirstChild().getNodeValue().trim().length() > 0) {
				indent--;
				xmlBuf.append("</");
				xmlBuf.append(node.getNodeName());
				xmlBuf.append('>');
				xmlBuf.append(lineEnd);
			} else if (node.hasChildNodes()) {
				indent--;
				appendIndentation(xmlBuf);
				xmlBuf.append("</");
				xmlBuf.append(node.getNodeName());
				xmlBuf.append('>');
				xmlBuf.append(lineEnd);
			}
		}
	}

	/**
	 * Sorts the dom tree
	 * 
	 * @param doc Description of Parameter
	 */
	public void sort(Document doc) {

	}
}
