package com.csc.fsg.life.tools.xml.simple;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.w3c.dom.Element;

import com.csc.fsg.life.common.util.DateHelper;

/* Modifications: T0179 */

/**
 * Utility class that can be used to encode an Object to XML and vice versa.
 */
@SuppressWarnings("unchecked")
public class SimpleXMLEncoder
{
	private Collection<String> supportedAttributeTypes;

	/**
	 * Constructor.
	 */
	public SimpleXMLEncoder()
	{
		supportedAttributeTypes = new ArrayList<String>();
		supportedAttributeTypes.add(Integer.class.getName());
		supportedAttributeTypes.add(Long.class.getName());
		supportedAttributeTypes.add(Double.class.getName());
		supportedAttributeTypes.add(Date.class.getName());
		supportedAttributeTypes.add(Timestamp.class.getName());
		supportedAttributeTypes.add(String.class.getName());
		supportedAttributeTypes.add(Boolean.class.getName());
	}

	/**
	 * Build a String representation of an XML element based on the given
	 * object. The following steps are taken to generate the element:
	 * 
	 * <ul>
	 * <li>Create the XML element name by taking the class name and removing
	 * the package prefix.</li>
	 * <li>For each applicable property in the given class, add an XML
	 * attribute to the newly created element. A property is deemed applicable,
	 * if there is a publicly exposed accessor or "getter" method, the method
	 * does not accept any input parameters, and it returns one of the supported
	 * types. The value of the XML attribute is the result of invoking the
	 * "getter" method.</li>
	 * </ul>
	 * 
	 * Sample:
	 * <P>
	 * &lt;MyClass field1="abc" field2="123"/&gt;
	 * 
	 * @param bean
	 *            The object to serialize to XML format.
	 * 
	 * @return String XML String representation of bean.
	 */
	public String buildXML(Object bean)
	{
		if (bean == null)
			return null;

		// Build name of the element to be constructed
		Class modelClass = bean.getClass();
		String className = modelClass.getName();
		String elementName = className.substring(className.lastIndexOf(".") + 1);

		// Build the Element object, and set its attributes to reflect
		// properties of model
		TextXMLBuilder xmlBuilder = new TextXMLBuilder(elementName);
		buildModelAttributes(bean, xmlBuilder);

		// Return the XML-format text representation of bean
		return xmlBuilder.getXMLText();
	}

	/**
	 * Build an XML element based on the given object. The following steps are
	 * taken to generate the element:
	 * 
	 * <ul>
	 * <li>Create the XML element name using the fully-qualified class name.</li>
	 * <li>For each applicable property in the given class, add an XML
	 * attribute to the newly created element. A property is deemed applicable,
	 * if there is a publicly exposed accessor or "getter" method, the method
	 * does not accept any input parameters, and it returns one of the supported
	 * types. The value of the XML attribute is the result of invoking the
	 * "getter" method.</li>
	 * </ul>
	 * 
	 * @param bean
	 *            The object to serialize to XML format.
	 * 
	 * @param elementFactory
	 *            Provides access to the new Element's Document object. It is
	 *            used only to create the new instance of Element.
	 * 
	 * @return Element The representation of bean.
	 */
	public Element buildXML(Object bean, SimpleXMLElementFactory elementFactory)
	{
		if (bean == null || elementFactory == null)
			return null;

		// Build name of the element to be constructed
		Class modelClass = bean.getClass();
		String className = modelClass.getName();

		// Build the Element object, and set its attributes to reflect
		// properties of model
		DomXMLBuilder xmlBuilder = new DomXMLBuilder(elementFactory, className);
		buildModelAttributes(bean, xmlBuilder);

		// Return the new Element representation of bean
		return xmlBuilder.getElement();
	}

	/**
	 * Iterate through all methods in bean, and use the applicable ones to build
	 * bean's XML representation using the supplied builder object.
	 * 
	 * @param bean
	 *            The object to serialize to XML format.
	 * 
	 * @param builder
	 *            This object is used to accumulate values attributes
	 *            corresponding to bean's properties.
	 */
	private void buildModelAttributes(Object bean, XMLBuilder builder)
	{
		// Iterate through all methods
		Method[] methods = bean.getClass().getMethods();
		for (int i = 0, n = methods.length; i < n; i++) {
			Method method = methods[i];
			if (isGetterApplicable(method)) {
				// By convention, bean property name matches name of the the
				// method without the "get" prefix.
				String fieldName = lowercaseFirstChar(method.getName().substring(3));
				try {
					// Get the value returned by the current method
					Object result = method.invoke(bean, null);
					if (result != null) {
						String stringValue = buildStringValue(result);
						builder.addAttribute(fieldName, stringValue);
					}
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * This method builds an Object based off of the given XML Element. It does
	 * this by interpreting tag name of the supplied element as a
	 * fully-qualified class name to be used to create a new instance of Object.
	 * It will then read each attribute that exists on the Element, and for each
	 * applicable attribute, call the corresponding "setter" method to populate
	 * the newly instantiated object.
	 * 
	 * @param element
	 *            XML Element used to instantiate object.
	 * 
	 * @return Object New instance of the class indicated by element, populated
	 *         with data from the given Element.
	 */
	public Object buildObject(Element element)
		throws SimpleXMLException
	{
		// Create the model object
		Object bean = getBeanInstance(element);

		// Iterate through all methods
		Method[] methods = bean.getClass().getMethods();
		for (int i = 0, n = methods.length; i < n; i++) {
			// By convention, bean property name matches name of the the
			// method without the "set" prefix.
			Method method = methods[i];
			String fieldName = lowercaseFirstChar(method.getName().substring(3));

			if (element.hasAttribute(fieldName) && isSetterApplicable(method)) {
				try {
					// Set value using the current method
					String fieldValue = element.getAttribute(fieldName);
					Object typedValue = buildTypedValue(fieldValue, method.getParameterTypes()[0]);
					Object[] input = new Object[1];
					input[0] = typedValue;
					method.invoke(bean, input);
				}
				catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return bean;
	}

	/**
	 * Instantiate an Object, using the given element's tag name as the
	 * fully-qualified class name.
	 * 
	 * @param element
	 *            The XML Element, which supplies the class name to instantiate.
	 * 
	 * @return Object The newly-created instance of an object indicated by
	 *         element.
	 */
	private Object getBeanInstance(Element element)
		throws SimpleXMLException
	{
		Object bean = null;
		String className = element.getTagName();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();

		try {
			Class beanClass = Class.forName(className, true, loader);
			bean = beanClass.newInstance();

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new SimpleXMLException(e.getMessage());
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
			throw new SimpleXMLException(e.getMessage());
		}
		catch (InstantiationException e) {
			e.printStackTrace();
			throw new SimpleXMLException(e.getMessage());
		}

		return bean;
	}

	/**
	 * Make the first character of the given string lowercase.
	 * 
	 * @param s
	 *            String to make lowercase.
	 * 
	 * @return String New String that starts with a lowercase character.
	 */
	private String lowercaseFirstChar(String s)
	{
		StringBuffer sb = new StringBuffer(s);
		if (sb.length() > 0) {
			sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		}

		return sb.toString();
	}

	/**
	 * Return a flag indicating whether or not the supplied method should be
	 * used to create an attribute of a new XML Element.
	 * 
	 * @param method
	 *            The method, which could potentially be used to create an
	 *            attribute.
	 * 
	 * @return boolean
	 */
	private boolean isGetterApplicable(Method method)
	{
		String methodName = method.getName();

		// Interested only in "getters"
		if (!methodName.startsWith("get"))
			return false;

		// Interested only in methods with no input parameters
		if (method.getParameterTypes().length != 0)
			return false;

		// Interested only in methods, which return one of types supported by
		// this XML encoder.
		Class returnType = method.getReturnType();
		return (supportedAttributeTypes.contains(returnType.getName()));
	}

	/**
	 * Return a flag indicating whether or not the supplied method should be
	 * used to set value of a property corresponding to an XML Element's
	 * attribute.
	 * 
	 * @param method
	 *            The method, which could potentially be used to set value of a
	 *            property.
	 * 
	 * @return boolean
	 */
	private boolean isSetterApplicable(Method method)
	{
		String methodName = method.getName();

		// Interested only in "setters"
		if (!methodName.startsWith("set"))
			return false;

		// Interested only in methods with void return type
		String returnTypeName = method.getReturnType().getName();
		if (!returnTypeName.equals("void"))
			return false;

		// Interested only in methods with one input paramter
		Class[] parameterTypes = method.getParameterTypes();
		if (parameterTypes.length != 1)
			return false;

		// Interested only in methods, which accept as parameter one of types
		// supported by this XML encoder.
		return supportedAttributeTypes.contains(parameterTypes[0].getName());
	}

	/**
	 * Build a String representation of the supplied value.
	 */
	private String buildStringValue(Object value)
	{
		if ((value instanceof Date) && !(value instanceof Timestamp)) {
			Date dateValue = (Date) value;
			return DateHelper.toFormattedISOShortString(dateValue);
		}
		else {
			return String.valueOf(value);
		}
	}

	/**
	 * Build an object of type indicated by valueType, whose value represents
	 * the given stringValue.
	 */
	private Object buildTypedValue(String stringValue, Class valueType)
	{
		String typeName = valueType.getName();

		if (typeName.equals(Integer.class.getName()))
			return Integer.valueOf(stringValue);
		else if (typeName.equals(Long.class.getName()))
			return Long.valueOf(stringValue);
		else if (typeName.equals(Double.class.getName()))
			return Double.valueOf(stringValue);
		else if (typeName.equals(Date.class.getName()))
			return DateHelper.parseDate(stringValue);
		else if (typeName.equals(Timestamp.class.getName()))
			return Timestamp.valueOf(stringValue);
		else
			return stringValue;
	}

	/**
	 * This interface is implemented by classes, which are be used to build XML
	 * representation of an object for each method buildXML.
	 */
	private interface XMLBuilder
	{
		/**
		 * Add attrribute defined by the supplied name and value, to the XML
		 * represenation of an object.
		 */
		public void addAttribute(String name, String value);
	}

	/**
	 * This implementation of XMLBuilder is used to create an XML text
	 * representation of an object.
	 */
	private static class TextXMLBuilder
		implements XMLBuilder
	{
		StringBuffer buf;

		/**
		 * Constructor.
		 */
		private TextXMLBuilder(String elementName)
		{
			buf = new StringBuffer();
			buf.append("<" + elementName);
		}

		/**
		 * Add attribute defined by the supplied name and value, to the XML
		 * Representation of an object.
		 */
		public void addAttribute(String name, String value)
		{
			buf.append(" ");
			buf.append(name);
			buf.append("=\"");
			buf.append(value.trim());
			buf.append("\"");
		}

		/**
		 * Return the XML string produced by adding attribute values based on
		 * properties of an object.
		 */
		public String getXMLText()
		{
			buf.append("/>");
			return buf.toString();
		}
	}

	/**
	 * This implementation of XMLBuilder is used to create an XML DOM Element
	 * representation of an object.
	 */
	private static class DomXMLBuilder
		implements XMLBuilder
	{
		Element element;

		/**
		 * Constructor.
		 */
		private DomXMLBuilder(SimpleXMLElementFactory elementFactory, String name)
		{
			element = elementFactory.createElement(name);
		}

		/**
		 * Add attribute defined by the supplied name and value, to the XML
		 * Representation of an object.
		 */
		public void addAttribute(String name, String value)
		{
			element.setAttribute(name, value);
		}

		/**
		 * Return the Element produced by adding attribute values based on
		 * properties of an object.
		 */
		public Element getElement()
		{
			return element;
		}
	}
}
