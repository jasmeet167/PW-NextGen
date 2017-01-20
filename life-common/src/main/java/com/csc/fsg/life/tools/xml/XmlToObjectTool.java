package com.csc.fsg.life.tools.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.csc.fsg.life.common.util.DateHelper;
import com.csc.fsg.life.common.util.TagHelper;

/* Modifications: T0140, T0175 */

/**
 * Class to turn an XML Document into a object.
 */
public class XmlToObjectTool extends XmlTool {

	/** The Constant JAXP_SCHEMA_LANGUAGE. */
	static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

	/** The Constant W3C_XML_SCHEMA. */
	static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";

	/** The custom class loader. */
	private ClassLoader customClassLoader = null;

	/** The keyfields map. */
	private Map<String, List<String>> keyfieldsMap = new HashMap<String, List<String>>();

	/**
	 * Constructors.
	 * 
	 * @param keysMap the keys map
	 */
	public XmlToObjectTool(Map<String, List<String>> keysMap) {

		keyfieldsMap = keysMap;
	}

	/**
	 * The Constructor.
	 */
	public XmlToObjectTool() {

	}

	/**
	 * Sets the custom class loader.
	 * 
	 * @param loader the classloader
	 */
	public void setCustomClassLoader(ClassLoader loader) {

		customClassLoader = loader;
	}

	/**
	 * Builds an object from an XML document.
	 * 
	 * @param file the file
	 * @return the object
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException the IO exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws SAXException the SAX exception
	 * @throws InstantiationException the instantiation exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Object buildObject(String file) throws SAXException, IOException,
			ParserConfigurationException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {

		return buildObject(file, null);
	}

	/**
	 * Builds an object from an XML document. If an object is also passed, it
	 * overlays corresponding fields from the XML document in the original
	 * object and returns that object.
	 * 
	 * @param obj the obj
	 * @param file the file
	 * @return the object
	 * @throws IllegalAccessException the illegal access exception
	 * @throws IOException the IO exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws SAXException the SAX exception
	 * @throws InstantiationException the instantiation exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Object buildObject(String file, Object obj) throws SAXException,
			IOException, ParserConfigurationException, ClassNotFoundException,
			InstantiationException, IllegalAccessException,
			InvocationTargetException {

		// Create a xerces parser.
		// Specifically use xerces so we can set the features for validation.
		org.apache.xerces.parsers.DOMParser parser = new org.apache.xerces.parsers.DOMParser();
		// parser.setFeature( "http://xml.org/sax/features/validation", true);
		parser.setFeature("http://apache.org/xml/features/validation/schema",
				true);

		FileInputStream iStream = null;
		try {
			iStream = new FileInputStream(file);
			InputSource input = new InputSource(iStream);
			parser.parse(input);
			Document document = parser.getDocument();

			return buildNode(document, "", obj);
		}
		finally {
			if (iStream != null)
				iStream.close();
		}
	}

	/**
	 * Main method. Override to preserve backwars compatibility as this method
	 * is used in other projects.
	 * 
	 * @param node the node
	 * @param indent the indent
	 * @return the object
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws InstantiationException the instantiation exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Object buildNode(Node node, String indent)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		return buildNode(node, indent, null);
	}

	/**
	 * Main method. Transforms the specified XML Document into an object.
	 * 
	 * @param node the node to build.
	 * @param indent the indent
	 * @param obj the obj
	 * @return the object
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws InstantiationException the instantiation exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Object buildNode(Node node, String indent, Object obj)
			throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, InvocationTargetException {

		int type = node.getNodeType();
		boolean oldObject = false;

		if (obj != null)
			oldObject = true;

		log(indent + "processing node: " + node.getNodeName());
		indent += "  ";

		switch (type) {
		// handle the document element
		case Node.DOCUMENT_NODE: {
			return buildNode(((Document) node).getDocumentElement(), indent,
					obj);
		}

			// handle element with attributes
		case Node.ELEMENT_NODE: {
			// Build the object for this element. To do this get the class
			// attribute
			// to know what class to build.
			Element elem = (Element) node;
			NodeList children = node.getChildNodes();
			String elemName = elem.getLocalName();
			if (elemName == null)
				elemName = elem.getTagName();
			String className = elem.getAttribute("class");
			if ((className == null) || (className.trim().length() == 0)) {
				// If there isn't a class attribute this may be a simple
				// type
				// To verify that it is a simple type, the type will be the
				// last
				// part of the element name.
				try {
					return buildSimpleElement(elem, elemName);
				} catch (ClassNotFoundException cfe) {
					; // Fall through to the throw clause below.
				}

				throw new IllegalStateException("Element: '" + elemName + "' "
						+ "is missing a class attribute required for "
						+ "rebuilding the object, and isn't a simple type.");
			}
			if (oldObject) 
			{
				// If the names aren't equal and this isn't a proxy class then put
				// out a warning.
				String currentClassName = obj.getClass().getName();
				if (!currentClassName.equals(className) && !Proxy.isProxyClass(obj.getClass())) 
				{
					logger.warn("Element: '" + elemName + "' " +
								"has a passed class that conflicts with the " +
								"element's class attribute (" + 
								currentClassName + " != " + className + ").");
				}
			}

			// Special case an array slightly.
			Class clz = null;
			ClassUtil classUtil = null;
			Collection<Object> collection = null;
			boolean isCollection = false;
			boolean isArray = false;
			if (className.startsWith("Array.")) {
				// Get the array class.
				className = className.substring("Array.".length());
				ClassUtil arrayClassUtil = new ClassUtil(className);
				clz = arrayClassUtil.getCoreClass();

				if (!oldObject) {
					// Count the children to determine the array size.
					int arrayLen = 0;
					if (children != null) {
						int len = children.getLength();
						for (int i = 0; i < len; i++) {
							Node nextChildNode = children.item(i);
							if (nextChildNode instanceof Element)
								arrayLen++;
						}
					}
					obj = Array.newInstance(clz, arrayLen);
				}

				isCollection = true;
				isArray = true;
			} else {
				if (oldObject)
					clz = obj.getClass();
				else
					clz = ClassUtil.forName(className, customClassLoader);
				classUtil = new ClassUtil(clz);

				if (!oldObject)
					obj = clz.newInstance();

				// Determine if this element represents a collection.
				if (classUtil.isCollection()) {
					isCollection = true;
					collection = (Collection<Object>) obj;
				}
			}

			// get attributes
			NamedNodeMap attrs = node.getAttributes();
			for (int i = attrs.getLength() - 1; i >= 0; i--) {
				Attr attr = (Attr) attrs.item(i);
				String attrName = attr.getLocalName();
				String prefix = attr.getPrefix();
				// If the attrname is null, use the getNodeName and parse
				// the attribute name here if necessary.
				if (attrName == null) {
					attrName = attr.getNodeName();
					int idx = attrName.indexOf(":");
					if (idx > 0) {
						if (prefix == null)
							prefix = attrName.substring(0, idx);
						attrName = attrName.substring(idx + 1);
					}
				}
				// Skip namespace declaration and standard schema
				// attributes.
				if (prefix != null)
					if (prefix.equals("xsi") || prefix.equals("xmlns"))
						continue;
				if ((attrName == null) || attrName.equals("xmlns"))
					continue;

				// Skip the class attribute.
				if (attrName.equals("class"))
					continue;

				// Skip the object operation attribute.
				if (attrName.equals("objectOperation"))
					continue;

				String methodName = "set" + attrName;

				if (classUtil == null)
					throw new IllegalStateException("Unable to load class for element: '" + elemName
							+ "' " + "with attribute: '" + attrName + "' ");

				// Get the appropriate method from the class.
				Method method = classUtil.getSetMethod(methodName);
				if (method == null)
					throw new IllegalStateException("Element: '" + elemName
							+ "' " + "with attribute: '" + attrName + "' "
							+ "does not have a matching set method.");

				String attrValue = attr.getNodeValue();

				// Turn the value into the appropriate arguement type.
				Class argTypes[] = method.getParameterTypes();
				Object arg = buildArguement(argTypes[0], attrValue);
				/*
				 * We may want to overlay an existing date on a change to null
				 * value. Moving this into buildArguement for the case of an
				 * unrecognized argument actually reduces that routine's
				 * existing functionality (it passes the arg back unchanged).
				 * So, for the moment, this edit is being removed entirely. if
				 * (arg == null) throw new IllegalArgumentException("Attribute: '" +
				 * attrName + "' " + "on element: '" + elemName + "' " + "has an
				 * invalid format for it's type.");
				 */
				Object args[] = new Object[1];
				args[0] = arg;

				// Call the method to set the value.
				log(indent + "Invoking method: " + methodName);
				method.invoke(obj, args);
			}

			// get children
			if (children != null) {
				int len = children.getLength();
				
				if (len > 0 && classUtil == null)
					throw new IllegalStateException("Unable to load class for element: '"
							+ elemName + "' ");

				int childCount = -1;
				for (int i = 0; i < len; i++) {
					Node nextChildNode = children.item(i);
					String nextChildName = nextChildNode.getNodeName();
					String setterMethodName = "set" + nextChildName;
					String getterMethodName = "get" + nextChildName;
					Method setterMethod = classUtil.getSetMethod(setterMethodName);
					Method getterMethod = classUtil.getGetMethod(getterMethodName);
					
					// Build the child object.
					Object childObj = null;

					// If there is an existing object OR
					// We are not in a collection there is no setter method
					// Then call the getter to get the object.
					if (oldObject
							|| ((isCollection == false) && (setterMethod == null))) {
						if (getterMethod != null) {
							Object args[] = new Object[0];
							log(indent + "Invoking method: " + getterMethodName);
							childObj = getterMethod.invoke(obj, args);
						}
					}
					Object nextChildObj = buildNode(nextChildNode, indent,
							childObj);
					log(indent + "Done");
					if (nextChildObj == null)
						continue;

					// Increment real, element, child count.
					childCount++;

					// If we are in a collection, it should be prefixed with
					// the
					// element name.
					if (isCollection) {
						if (isArray) {
							log(indent + "Adding object to Array");
							Array.set(obj, childCount, nextChildObj);
						} else {
							if (oldObject) {
								log(indent
										+ "Adding object to Collection ... matching");
								matchObject(obj, nextChildObj, nextChildNode,
										collection, indent);
							} else {
								log(indent + "Adding object to Collection");
								collection.add(nextChildObj);
							}
						}
					} else {
						// Get the appropriate method from the class.
						if (setterMethod == null) {
							// If no setter method, only throw the exception
							// if
							// the child defining the setter is not a List
							if (!(nextChildObj instanceof List)) {
								throw new IllegalStateException(
										"Element: '"
												+ elemName
												+ "' "
												+ "with child element: '"
												+ nextChildName
												+ "' "
												+ "does not have a matching set method.");
							}
						} else {
							// Invoke the method.
							Object args[] = new Object[1];
							args[0] = nextChildObj;

							// Call the method to set the value.
							log(indent + "Invoking method: " + setterMethodName);
							setterMethod.invoke(obj, args);
						}
					}
				}
			}

			break;
		}

		default: {
			break;
		}
		}

		return obj;
	}

	/**
	 * Builds a simple type for an element.
	 * 
	 * @param elem the element object
	 * @param elemName the element name
	 * @return the object
	 * @throws ClassNotFoundException the class not found exception
	 */
	private Object buildSimpleElement(Element elem, String elemName)
			throws ClassNotFoundException {

		Object obj = null;

		int idx = elemName.lastIndexOf(".");
		if (idx > 0) {
			// Get the class for the simple type.
			String typeName = elemName.substring(idx + 1);
			ClassUtil simpleTypeClassUtil = new ClassUtil(typeName);
			Class simpleType = simpleTypeClassUtil.getCoreClass();

			// Next get the value for the simple type.
			String val = TagHelper.getText(elem);

			// Build the object for the simple type and return.
			obj = buildArguement(simpleType, val);
		}

		// If the object wasn't built throw an exception.
		if (obj == null)
			throw new IllegalArgumentException("Element: '" + elemName + "' "
					+ "has an invalid format for it's type.");
		return obj;
	}

	/**
	 * Turn the specified arguement into the class type.
	 * 
	 * @param argType the argument type
	 * @param argValue the argument value
	 * @return the argValue formatted into an object or null if the value was
	 *         invalid for the specified argType.
	 */
	private Object buildArguement(Class argType, String argValue) {

		if (argType.equals(Calendar.class)
				|| argType.equals(java.util.Date.class)
				|| argType.equals(java.sql.Date.class))
			if (argValue.trim().length() == 0)
				return null;

		if (argType.equals(Calendar.class)) {
			Date d = DateHelper.parseDate(argValue);
			Calendar c = new GregorianCalendar();
			c.setTime(d);
			return c;
		}

		else if (argType.equals(java.util.Date.class))
			return DateHelper.parseDate(argValue);

		else if (argType.equals(java.sql.Date.class))
			return java.sql.Date.valueOf(argValue);

		else if (argType.equals(Short.class) || argType.equals(Short.TYPE))
			return new Short(argValue);

		else if (argType.equals(Integer.class) || argType.equals(Integer.TYPE))
			return Integer.valueOf(argValue);

		else if (argType.equals(Long.class) || argType.equals(Long.TYPE))
			return Long.valueOf(argValue);

		else if (argType.equals(Boolean.class) || argType.equals(Boolean.TYPE))
			return Boolean.valueOf(argValue);

		else if (argType.equals(Float.class) || argType.equals(Float.TYPE))
			return new Float(argValue);

		else if (argType.equals(Double.class) || argType.equals(Double.TYPE))
			return Double.valueOf(argValue);

		return argValue;
	}

	/**
	 * Match object.
	 * 
	 * @param obj the obj
	 * @param nextChildObj the next child obj
	 * @param nextChildNode the next child node
	 * @param collection the collection
	 * @param indent the indent
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 */
	private void matchObject(Object obj, Object nextChildObj,
			Node nextChildNode, Collection<Object> collection, String indent)
			throws IllegalAccessException, InvocationTargetException {

		boolean match = false;
		Element e = (Element) nextChildNode;
		List keys = keyfieldsMap.get(nextChildObj.getClass().getName());

		// Iterate over the existing collection and try to find a match based
		// on the key field(s) - If no key field identified don't bother to
		// iterate.
		Object collObject = null;
		if (keys != null) {
			for (Iterator<Object> it = collection.iterator(); it.hasNext() && !match;) {
				boolean innerMatch = true;

				// Get the next object in the colection.
				collObject = it.next();

				ClassUtil classUtil = new ClassUtil(nextChildObj.getClass());

				// Check all of the keys.
				for (Iterator itk = keys.iterator(); itk.hasNext()
						&& innerMatch;) {
					String attr = (String) itk.next();
					String methodName = "get" + attr;
					Method method = classUtil.getGetMethod(methodName);
					Object objKeyValue = null;
					if (method != null) {
						Object args[] = new Object[0];
						log(indent + "Invoking method: " + methodName);
						objKeyValue = method.invoke(collObject, args);
					}

					if (!equals(objKeyValue, e.getAttribute(attr)))
						innerMatch = false;
				}

				if (innerMatch)
					match = true;
			}
		}

		// If we found a match in the collection, then replace,
		// otherwise add.
		if (match) {
			collection.remove(collObject);
			if (!e.getAttribute("objectOperation").equalsIgnoreCase("delete"))
				collection.add(nextChildObj);
		}
		if (!match)
			collection.add(nextChildObj);
	}

	/**
	 * Equals.
	 * 
	 * @param objKeyValue the object key value
	 * @param attribute the attribute
	 * @return true, if equals
	 */
	private boolean equals(Object objKeyValue, String attribute) {

		if (objKeyValue == null || attribute == null)
			return false;

		if (objKeyValue instanceof String)
			return objKeyValue.equals(attribute);

		Object xmlKeyValue = coerce(attribute, objKeyValue);

		return objKeyValue.equals(xmlKeyValue);
	}

	/**
	 * Coerce.
	 * 
	 * @param attribute the attribute
	 * @param objKeyValue the obj key value
	 * @return the object
	 */
	private Object coerce(String attribute, Object objKeyValue) {

		try {
			Constructor ctor = objKeyValue.getClass().getConstructor(
					new Class[] { String.class });

			return ctor.newInstance(new String[] { attribute });
		} catch (Exception e) {
			// The XML value cannot be converted to the JBO attribute type.

			// Workaround for JDK 1.3.1
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));

			throw new RuntimeException(sw.toString());
		}
	}
}
