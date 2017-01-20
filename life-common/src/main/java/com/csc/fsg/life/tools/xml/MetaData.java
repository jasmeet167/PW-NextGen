package com.csc.fsg.life.tools.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.csc.fsg.life.common.util.ObjectFieldRef;

/**
 * This class reads the class metat data from an input stream and populates the
 * concrete and skipped class maps.
 */
@SuppressWarnings("unchecked")
public class MetaData {

	/**
	 * The Constructor.
	 */
	public MetaData() {

	}

	/**
	 * Build maps for Object to XML Tool.
	 * 
	 * @param is the input stream
	 * @param abstractMap the abstract map
	 * @param skippedSet the skipped set
	 * @throws IOException the IO exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 */
	public void buildMaps(InputStream is, Map<ObjectFieldRef, List<Class>> abstractMap, Set<ObjectFieldRef> skippedSet)
			throws ParserConfigurationException, SAXException, IOException {

		buildMaps(is, abstractMap, skippedSet, null);
	}

	/**
	 * Build maps for XML to Object Tool. The three-parameter version of this
	 * method uses this one without creating the key map.
	 * 
	 * @param is the input stream
	 * @param abstractMap the abstract map
	 * @param skippedSet the skipped set
	 * @param keyMap the key map
	 * @throws IOException the IO exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 */
	public void buildMaps(InputStream is, Map<ObjectFieldRef, List<Class>> abstractMap, Set<ObjectFieldRef> skippedSet,
			Map<String, List<String>> keyMap) throws ParserConfigurationException, SAXException,
			IOException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document doc = builder.parse(is);

		NodeList fieldNodes = doc.getElementsByTagName("fieldref");
		for (int i = 0; i < fieldNodes.getLength(); i++) {
			Element fieldNode = (Element) fieldNodes.item(i);
			try {
				handleField(fieldNode, abstractMap, skippedSet);
			} catch (ClassNotFoundException e) {
				String classname = fieldNode.getAttribute("class");
				System.out.println("ClassNotFoundException for fieldref with class: "
						+ classname + " skipping ...");
			}
		}

		if (keyMap == null)
			return;

		NodeList keyNodes = doc.getElementsByTagName("classkeys");
		for (int i = 0; i < keyNodes.getLength(); i++) {
			Element keyNode = (Element) keyNodes.item(i);
			try {
				handleKey(keyNode, keyMap);
			} catch (ClassNotFoundException e) {
				String classname = keyNode.getAttribute("class");
				System.out.println("ClassNotFoundException for classkeys with class: "
						+ classname + " skipping ...");
			}
		}
	}

	/**
	 * Handle a single field node.
	 * 
	 * @param field the field element
	 * @param abstractMap the abstract map
	 * @param skippedSet the skipped set
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void handleField(Element field, Map<ObjectFieldRef, List<Class>> abstractMap, Set<ObjectFieldRef> skippedSet)
			throws ClassNotFoundException {

		String classname = field.getAttribute("class");
		String fieldname = field.getAttribute("field");
		String skipped = field.getAttribute("skipped");

		// Build a ref for this field.
		ObjectFieldRef ref = new ObjectFieldRef(classname, fieldname);

		// If the field is to be skipped, add it to the skipped set.
		if ((skipped != null) && (skipped.trim().length() > 0)
				&& (skipped.trim().equalsIgnoreCase("true"))) {
			skippedSet.add(ref);
		}

		// Else add it to the abstract map.
		else {
			// Build the concrete classes.
			List<Class> classes = new ArrayList<Class>();
			NodeList children = field.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				for (int i = 0; i < len; i++) {
					Node nextChildNode = children.item(i);
					if (nextChildNode instanceof Element) {
						Element child = (Element) nextChildNode;
						String concreteclass = child.getAttribute("class");
						if ((concreteclass != null)
								&& (concreteclass.trim().length() > 0)) {
							Class clz = Class.forName(concreteclass);
							classes.add(clz);
						}
						// TODO: Else, check for array class.
					}
				}
			}

			if (classes.size() == 0) {
				System.out.println("WARNING: ref "
						+ classname
						+ ", "
						+ fieldname
						+ "should specify skipped=true or concrete class ... ignoring.");
				return;
			}

			abstractMap.put(ref, classes);
		}
	}

	/**
	 * Handle a single key node.
	 * 
	 * @param field the Element field
	 * @param keyMap the key map
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void handleKey(Element field, Map<String, List<String>> keyMap)
			throws ClassNotFoundException {

		String classname = field.getAttribute("class");

		List<String> keys = new ArrayList<String>();
		NodeList children = field.getChildNodes();
		if (children != null) {
			int len = children.getLength();
			for (int i = 0; i < len; i++) {
				Node nextChildNode = children.item(i);
				if (nextChildNode instanceof Element) {
					Element child = (Element) nextChildNode;
					String fieldname = child.getAttribute("field");
					if ((fieldname != null) && (fieldname.trim().length() > 0)) {
						keys.add(fieldname);
					}
				}
			}
		}

		if (keys.size() == 0) {
			System.out.println("WARNING: classkeys " + classname
					+ "should specify at least one key field.");
			return;
		}

		keyMap.put(classname, keys);
	}
}
