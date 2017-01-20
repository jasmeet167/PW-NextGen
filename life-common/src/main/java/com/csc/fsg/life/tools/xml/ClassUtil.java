package com.csc.fsg.life.tools.xml;

import java.lang.reflect.Method;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Nice helper methods give a particular class.
 */
@SuppressWarnings("unchecked")
public class ClassUtil {

	/** Map of Java objects to built in schema types. */
	private static Map classToSimpleTypeMap = new HashMap();

	/** The simple type to class map. */
	private static Map simpleTypeToClassMap = new HashMap();

	static {
		classToSimpleTypeMap.put(String.class, "string");
		classToSimpleTypeMap.put(Boolean.class, "boolean");
		classToSimpleTypeMap.put(Short.class, "short");
		classToSimpleTypeMap.put(Long.class, "long");
		classToSimpleTypeMap.put(Integer.class, "int");
		classToSimpleTypeMap.put(Float.class, "float");
		classToSimpleTypeMap.put(Double.class, "double");
		classToSimpleTypeMap.put(Date.class, "date");
		classToSimpleTypeMap.put(java.sql.Date.class, "date");
		classToSimpleTypeMap.put(Calendar.class, "date");
		classToSimpleTypeMap.put(URL.class, "anyURI");

		simpleTypeToClassMap.put("String", String.class);
		simpleTypeToClassMap.put("int", Integer.TYPE);
		simpleTypeToClassMap.put("Boolean", Boolean.class);
		simpleTypeToClassMap.put("short", Short.TYPE);
		simpleTypeToClassMap.put("Short", Short.class);
		simpleTypeToClassMap.put("long", Long.TYPE);
		simpleTypeToClassMap.put("Long", Long.class);
		simpleTypeToClassMap.put("int", Integer.TYPE);
		simpleTypeToClassMap.put("Integer", Integer.class);
		simpleTypeToClassMap.put("float", Float.TYPE);
		simpleTypeToClassMap.put("Float", Float.class);
		simpleTypeToClassMap.put("double", Double.TYPE);
		simpleTypeToClassMap.put("Double", Double.class);
		simpleTypeToClassMap.put("Date", Date.class);
		simpleTypeToClassMap.put("Calendar", Calendar.class);
		simpleTypeToClassMap.put("URL", URL.class);
	}

	private Class clz;
	private String className;

	/**
	 * A more robust Class.forName() method that trys a custom class loader
	 * first (if available).
	 * 
	 * @param className the class name
	 * @param loader the loader
	 * @return the class
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static Class forName(String className, ClassLoader loader)
			throws ClassNotFoundException {

		if (loader != null) {
			try {
				return Class.forName(className, true, loader);
			} catch (ClassNotFoundException cnfe) {
				; // Fall through to try default.
			}
		}

		return Class.forName(className);
	}

	/**
	 * Builds the helper class with the class to work on.
	 * 
	 * @param c the class
	 */
	public ClassUtil(Class c) {

		clz = c;
		className = clz.getName();
	}

	/**
	 * Build the helper class based on a class name as a string.
	 * 
	 * @param c the class
	 * @throws ClassNotFoundException the class not found exception
	 */
	public ClassUtil(String c) throws ClassNotFoundException {

		// First check simple types.
		clz = (Class)simpleTypeToClassMap.get(c);

		// If not a simple type check for fully qualified class name
		if (clz == null)
			clz = Class.forName(c);

		className = clz.getName();
	}

	/**
	 * Gets the class this object works off of.
	 * 
	 * @return the core class
	 */
	public Class getCoreClass() {

		return clz;
	}

	/**
	 * Determines if this class is a basic schema type.
	 * 
	 * @return true, if is simple type
	 */
	public boolean isSimpleType() {

		if (classToSimpleTypeMap.containsKey(clz))
			return true;

		if (clz.isPrimitive())
			return true;

		return false;
	}

	/**
	 * Gets the XML Schema type for a simple class.
	 * 
	 * @return the simple type name
	 */
	public String getSimpleTypeName() {

		String simpleType = (String) classToSimpleTypeMap.get(clz);

		if (simpleType == null) {
			if (clz == Boolean.TYPE)
				simpleType = "boolean";
			if (clz == Character.TYPE)
				simpleType = "string";
			if (clz == Byte.TYPE)
				simpleType = "byte";
			if (clz == Short.TYPE)
				simpleType = "short";
			if (clz == Integer.TYPE)
				simpleType = "int";
			if (clz == Long.TYPE)
				simpleType = "long";
			if (clz == Float.TYPE)
				simpleType = "float";
			if (clz == Double.TYPE)
				simpleType = "double";
		}

		if (simpleType == null)
			throw new IllegalStateException("Invalid class as simple type: "
					+ clz.getName());

		return "xsd:" + simpleType;
	}

	/**
	 * Gets the depth of this array class.
	 * 
	 * @return the array depth or 0 if not an array.
	 */
	public int getArrayDepth() {

		int i = 0;
		for (; i < className.length(); i++) {
			if (className.charAt(i) != '[')
				break;
		}

		return i;
	}

	/**
	 * Get the class of an array.
	 * 
	 * @return the Array class or null if this class isn't an array.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Class getArrayClass() throws ClassNotFoundException {

		if (clz.isArray() == false)
			return null;

		int i = 0;
		for (; i < className.length(); i++) {
			if (className.charAt(i) != '[')
				break;
		}

		// Get array type from the name and build if necessary.
		String typeName = className.substring(i);
		if (typeName.startsWith("L")) {
			// Create the complex type for this class if necessary.
			Class arrayClass = Class.forName(typeName.substring(1,
					typeName.length() - 1));
			return arrayClass;
		} else {
			// Convert simple type to name.
			if (typeName.equals("B"))
				return Boolean.TYPE;
			else if (typeName.equals("C"))
				return Character.TYPE;
			else if (typeName.equals("D"))
				return Double.TYPE;
			else if (typeName.equals("F"))
				return Float.TYPE;
			else if (typeName.equals("I"))
				return Integer.TYPE;
			else if (typeName.equals("J"))
				return Long.TYPE;
			else if (typeName.equals("S"))
				return Short.TYPE;
			else if (typeName.equals("Z"))
				return Boolean.TYPE;
			else
				throw new IllegalStateException("Invalid array type: "
						+ className);
		}
	}

	/**
	 * This method deals with turning the $ used in inner class names into a .
	 * so that it is valid in an XML identifier.
	 * 
	 * @return the fixed class name
	 */
	public String getFixedClassName() {

		return className.replace('$', '.');
	}

	/**
	 * This method returns the short name for a class (class name less the
	 * package name. It also fixed the class name so that inner class names will
	 * be returned in the form of class.innerclass
	 * 
	 * @return the short class name
	 */
	public String getShortClassName() {

		int idx = className.lastIndexOf(".");
		String name = getFixedClassName();
		if (idx > 0)
			name = name.substring(idx + 1);
		return name;
	}

	/**
	 * Gets the short class name for the specified class. If this is the array
	 * it return the array class plus "Array"
	 * 
	 * @return the name
	 * @throws ClassNotFoundException the class not found exception
	 */
	public String getName() throws ClassNotFoundException {

		if (clz.isArray()) {
			Class arrayClass = getArrayClass();
			ClassUtil classUtil = new ClassUtil(arrayClass);
			return classUtil.getShortClassName() + "Array";
		}
		return getShortClassName();
	}

	/**
	 * Gets the complex type name for the specified class. The complex type name
	 * is the short class name appeneded with the string "Type"
	 * 
	 * @return the complex type name
	 * @throws ClassNotFoundException the class not found exception
	 */
	public String getComplexTypeName() throws ClassNotFoundException {

		return getName() + "Type";
	}

	/**
	 * The type name is the XSD data type for simple types and the complex type
	 * name for complex types.
	 * 
	 * @return the type name
	 * @throws ClassNotFoundException the class not found exception
	 */
	public String getTypeName() throws ClassNotFoundException {

		if (isSimpleType())
			return getSimpleTypeName();
		else
			return getComplexTypeName();
	}

	/**
	 * Determins if this class implements the interface Collection.
	 * 
	 * @return true, if is collection
	 */
	public boolean isCollection() {

		// Check array first, it is a type of collection so
		// return true.
		if (clz.isArray())
			return true;

		if (!isSimpleType()) {
			Class interfaces[] = clz.getInterfaces();
			if (interfaces != null)
				for (int intCount = 0; intCount < interfaces.length; intCount++) {
					Class nextInterface = interfaces[intCount];
					// System.out.println("Interface: " +
					// nextInterface.getName());
					if (nextInterface.equals(Collection.class)
							|| nextInterface.equals(List.class))
						return true;
				}

			// If this is a class, all super classes must be checked also
			if (!clz.isInterface()) {
				Class superClz = clz.getSuperclass();
				if (superClz != null) {
					ClassUtil superClassUtil = new ClassUtil(superClz);
					return superClassUtil.isCollection();
				}
			}
		}
		return false;
	}

	/**
	 * Gets a method by name. This returns the first single arguement method
	 * with a void return type that it finds.
	 * 
	 * @param methodName The method name to find.
	 * @return The method found or null if none found.
	 */
	public Method getSetMethod(String methodName) {

		Method methods[] = clz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method nextMethod = methods[i];

			// First ensure for a name match.
			String nextMethodName = nextMethod.getName();
			if (!nextMethodName.equals(methodName))
				continue;

			// Next ensure for a void return type.
			Class returnClass = nextMethod.getReturnType();
			if (!returnClass.equals(Void.TYPE))
				continue;

			// Next ensure a single arguement.
			Class parms[] = nextMethod.getParameterTypes();
			if (parms.length != 1)
				continue;

			// This matches so bail.
			return nextMethod;
		}

		// No match.
		return null;
	}

	/**
	 * Gets a get method by name.
	 * 
	 * @param methodName the method name
	 * @return the get method
	 */
	public Method getGetMethod(String methodName) {

		Method methods[] = clz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method nextMethod = methods[i];

			// First ensure for a name match.
			String nextMethodName = nextMethod.getName();
			if (!nextMethodName.equals(methodName))
				continue;

			// Next ensure a no arguments.
			Class parms[] = nextMethod.getParameterTypes();
			if (parms.length != 0)
				continue;

			// This matches so bail.
			return nextMethod;
		}

		// No match.
		return null;
	}
}
