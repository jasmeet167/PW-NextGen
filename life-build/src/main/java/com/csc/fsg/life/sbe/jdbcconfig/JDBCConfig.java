package com.csc.fsg.life.sbe.jdbcconfig;

import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.sbe.common.util.XmlWriter;

public class JDBCConfig {
	protected void writeDoc(Document doc, File file, boolean xmlDeclaration) throws Exception {
		XmlWriter xmlWriter = new XmlWriter(doc, xmlDeclaration);
		xmlWriter.output(file);
	}

	protected void writeDoc(Document doc, File file) throws Exception {
		writeDoc(doc, file, true);
	}

	protected void createTextElement(Document doc, Element parentElement, String name, String value) {
		Element element = doc.createElement(name);
		element.setTextContent(value);
		parentElement.appendChild(element);
	}

	protected Element createElement(Document doc, Element parentElement, String name) {
		Element element = doc.createElement(name);
		parentElement.appendChild(element);
		return element;
	}
}
