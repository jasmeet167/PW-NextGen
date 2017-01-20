package com.csc.fsg.life.tools.xml;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.csc.fsg.life.common.util.ObjectFieldRef;

// TODO: Auto-generated Javadoc
/**
 * This class manages (de)serializing objects to/from XML. Furthermore, it will
 * produce a schema for a given class to support the XML it will create for an
 * instance of that class.
 */
@SuppressWarnings("unchecked")
public class ClassToSchemaTool extends XmlTool {

	/** Map of custom types already built in this schema. */
	private Map<String, Boolean> customTypeMap = new HashMap<String, Boolean>();

	/**
	 * Map of object/field key to a list of more specific classes. For example
	 * if a java class holds onto a list all we can infer from reflection is
	 * that it contains a list of type Object. But this map can identify the
	 * real type(s) that the list contains. Map of ObjectFieldRef -> List (of
	 * class names as strings)
	 */
	private Map<ObjectFieldRef, List<Class>> abstractMap = new HashMap<ObjectFieldRef, List<Class>>();

	/** Set of object/field keys to suppress in schema building. */
	private Set<ObjectFieldRef> skippedSet = new HashSet<ObjectFieldRef>();

	/**
	 * The Constructor.
	 * 
	 * @param abstractClassDetails A map which
	 * @param skippedDetails the skipped details
	 */
	public ClassToSchemaTool(Map<ObjectFieldRef, List<Class>> abstractClassDetails, Set<ObjectFieldRef> skippedDetails) {

		if (abstractClassDetails != null)
			abstractMap = abstractClassDetails;
		if (skippedDetails != null)
			skippedSet = skippedDetails;
	}

	/**
	 * Gets the concrete class for an abstract type or colection from the map
	 * for the specified method.
	 * 
	 * @param methodRef the method ref
	 * @param clz the class
	 * @return the concrete classes
	 * @throws ClassNotFoundException the class not found exception
	 */
	public List<Class> getConcreteClasses(ObjectFieldRef methodRef, Class clz)
			throws ClassNotFoundException {

		List<Class> classesFound = abstractMap.get(methodRef);
		if (classesFound == null) {
			// Check for array type, if so return the array class
			// in a list.
			if (clz.isArray()) {
				ClassUtil classUtil = new ClassUtil(clz);
				Class arrayClass = classUtil.getArrayClass();
				classesFound = new ArrayList<Class>();
				classesFound.add(arrayClass);
			}
		}

		return classesFound;
	}

	/**
	 * Gets the schema.
	 * 
	 * @param className The class to generate a schema for.
	 * @return the schema
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public String getSchema(String className) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException {

		// get class object for specified class
		Class clz = Class.forName(className);
		return getSchema(clz);
	}

	/**
	 * Gets the schema.
	 * 
	 * @param clz the class
	 * @return the schema
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public String getSchema(Class clz) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException {

		StringBuffer schema = new StringBuffer();

		// TODO: Handle array classes at the top-level
		if (clz.isArray())
			throw new UnsupportedOperationException(
					"Array classes are handled as return types, "
							+ "but not at the top-level.  Please wrap your "
							+ "array in a class.");

		schema.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		schema.append("<xsd:schema xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">\n\n");

		// Put out the root element
		ClassUtil classUtil = new ClassUtil(clz);
		schema.append("<xsd:element name=\"" + classUtil.getName() + "\" "
				+ "type=\"" + classUtil.getTypeName() + "\"/>\n\n");

		// Clear custom type map.
		customTypeMap = new HashMap<String, Boolean>();

		String clzSchema = _getSchema(clz, "");
		if (clzSchema == null)
			throw new IllegalStateException("Schema for class: "
					+ clz.getName() + " is invalid - it has no getters.");
		schema.append(clzSchema);

		schema.append("</xsd:schema>\n");

		return schema.toString();
	}

	/**
	 * Gets the schema complex type for this class and any referenced classes
	 * recursively. Returns the empty string if this is a simple type or if this
	 * is a class whose complex type has already been built. Returns null if
	 * this is a complex type with no detail (i.e. no elements or attributes).
	 * 
	 * @param clz the class
	 * @param indent the indent
	 * @return the string
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	protected String _getSchema(Class clz, String indent)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException {

		log(indent + "Getting schema for class: " + clz.getName());
		indent = indent + "  ";

		StringBuffer schema = new StringBuffer();

		// If this is a base type then bail.
		ClassUtil classUtil = new ClassUtil(clz);
		if (classUtil.isSimpleType()) {
			log(indent + "Bailing on simple type: " + clz.getName());
			return "";
		}

		// Sanity check that we don't reach an array here.
		if (clz.isArray())
			throw new IllegalStateException(
					"Arrays should have been stopped before here.");

		// Check to see if already built and if so bail.
		String typeName = classUtil.getComplexTypeName();
		String typeKey = clz.getName();
		if (isCustomTypeBuilt(typeKey)) {
			log(indent + "Bailing b/c already built: " + clz.getName());
			return "";
		}

		StringBuffer schemaElements = new StringBuffer();
		StringBuffer schemaAttrs = new StringBuffer();

		schema.append("<xsd:complexType name=\"" + typeName + "\">\n");

		// Visit methods in this class.
		ClassToSchemaMethodVisitor visitor = new ClassToSchemaMethodVisitor(
				clz, skippedSet, schema, schemaElements, schemaAttrs, this,
				indent);
		visitor.visit();

		if (schemaElements.length() > 0) {
			schema.append("  <xsd:sequence>\n");
			schema.append(schemaElements.toString());
			schema.append("  </xsd:sequence>\n");
		}

		schema.append(buildClassAttribute(clz));
		schema.append(buildAttribute("objectOperation", "xsd:string", ""));
		schema.append(schemaAttrs.toString());

		schema.append("</xsd:complexType>\n\n");

		// Return null if no content for this class.
		if (visitor.getCount() == 0) {
			log(indent + "Bailing b/c no getters.");

			// Remove it from the custom type map
			// b/c the type is invalid.
			customTypeMap.remove(typeKey);

			return null;
		}

		return schema.toString();
	}

	/**
	 * Check to see if the custom type was built and if so return true, if not
	 * add it and return false.
	 * 
	 * @param typeKey the type key
	 * @return true, if is custom type built
	 */
	public boolean isCustomTypeBuilt(String typeKey) {

		if (customTypeMap.containsKey(typeKey)) {
			return true;
		}

		customTypeMap.put(typeKey, Boolean.TRUE);
		return false;
	}

	/**
	 * Builds a classes reference for this methodName's return class. and also
	 * build and referenced complex types as necessary.
	 * 
	 * @param returnClass The class the method can return.
	 * @param methodName The method name.
	 * @param schema The schema buffer used to add any referenenced complex
	 *        types that are built.
	 * @param forceToElement Should this be automatically made an element.
	 * @param elementOptions the element options
	 * @param result The resulting method reference built.
	 * @param indent An indent used for debugging messages.
	 * @return true if an element ref was built, false for an attribute.
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	boolean buildClass(Class returnClass, String methodName,
			StringBuffer schema, boolean forceToElement, String elementOptions,
			StringBuffer result, String indent) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException {

		// Determine if this is a complex class.
		ClassUtil classUtil = new ClassUtil(returnClass);
		boolean complexType = (!classUtil.isSimpleType());

		// If this is a complex type ...
		if (complexType || forceToElement) {
			boolean buildElement = true;
			if (complexType) {
				// Built the classes schema.
				String returnTypeBuilt = _getSchema(returnClass, indent);

				// If the class has no member then the return type will be
				// null so don't add.
				if (returnTypeBuilt != null)
					schema.insert(0, returnTypeBuilt);
				else
					buildElement = false;
			}

			if (buildElement) {
				result.append(buildElementRef(methodName,
						classUtil.getTypeName(), elementOptions, "    "));
			}

			return true;
		}
		// Else it is a simple type so add an attribute.
		else {
			result.append(buildAttribute(methodName, classUtil.getTypeName(),
					""));
			return false;
		}
	}

	/**
	 * Builds the array class attribute.
	 * 
	 * @param clz the class
	 * @return the string
	 */
	public String buildArrayClassAttribute(Class clz) {

		String name = clz.getName();
		return buildAttribute("class", "xsd:string", "fixed=\"Array." + name
				+ "\"");
	}

	/**
	 * Builds the class attribute.
	 * 
	 * @param clz the clz
	 * @return the string
	 */
	public String buildClassAttribute(Class clz) {

		String name = clz.getName();
		return buildAttribute("class", "xsd:string", "fixed=\"" + name + "\"");
	}

}
