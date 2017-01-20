package com.csc.fsg.life.tools.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.csc.fsg.life.common.util.ObjectFieldRef;

/**
 * The Class ClassToSchemaMethodVisitor.
 */
@SuppressWarnings("unchecked")
public class ClassToSchemaMethodVisitor extends MethodVisitor {

	private ClassToSchemaTool tool;

	// Buffers for schema building.
	private StringBuffer schema;
	private StringBuffer schemaElements;
	private StringBuffer schemaAttrs;

	// Indent used in logging
	private String indent;

	// Method count that was used.
	private int count = 0;

	/**
	 * The Constructor.
	 * 
	 * @param clz the class
	 * @param skippedSet the skipped set
	 * @param schema the schema
	 * @param schemaElements the schema elements
	 * @param schemaAttrs the schema attrs
	 * @param tool the tool
	 * @param indent the indent
	 */
	public ClassToSchemaMethodVisitor(Class clz, Set<ObjectFieldRef> skippedSet,
			StringBuffer schema, StringBuffer schemaElements,
			StringBuffer schemaAttrs, ClassToSchemaTool tool, String indent) {

		super(clz, skippedSet);
		this.schema = schema;
		this.schemaElements = schemaElements;
		this.schemaAttrs = schemaAttrs;
		this.indent = indent;
		this.tool = tool;
	}

	/**
	 * Count of number of elements and attributes in this schem type. If 0 at
	 * end then don't write out anything.
	 * 
	 * @return the count
	 */
	public int getCount() {

		return count;
	}

	/**
	 * Visit a method on a class.
	 * 
	 * @param m the method
	 * @param methodName the method name
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void visitMethod(Method m, String methodName)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException {

		log(indent + "Method Name: " + m.getName());

		// Get the return class and check this method
		// for substitution.
		Class returnClass = m.getReturnType();

		// Get the concrete classes for this method call.
		ObjectFieldRef methodRef = new ObjectFieldRef(clz.getName(), methodName);
		List<Class> concreteClasses = tool.getConcreteClasses(methodRef, returnClass);
		log(indent + "Found on top-level class "
				+ ((concreteClasses == null) ? 0 : concreteClasses.size())
				+ " concrete classes.");
		// Try the declaring class if none found on the top-level class.
		if (concreteClasses == null) {
			Class declaringClass = m.getDeclaringClass();
			methodRef = new ObjectFieldRef(declaringClass.getName(), methodName);
			concreteClasses = tool.getConcreteClasses(methodRef, returnClass);
			log(indent + "Found on declaring class "
					+ ((concreteClasses == null) ? 0 : concreteClasses.size())
					+ " concrete classes.");
		}
		// If still not found, then check the wild card.
		if (concreteClasses == null) {
			methodRef = new ObjectFieldRef("*", methodName);
			concreteClasses = tool.getConcreteClasses(methodRef, returnClass);
			log(indent + "Found on wildcard "
					+ ((concreteClasses == null) ? 0 : concreteClasses.size())
					+ " concrete classes.");
		}
		// If still null, then just use the return class.
		if (concreteClasses == null) {
			concreteClasses = new ArrayList<Class>();
			concreteClasses.add(returnClass);
		}

		// If the return class is a collection, then the concrete classes
		// are possible elements of the collection.
		ClassUtil returnClassUtil = new ClassUtil(returnClass);
		boolean isCollection = returnClassUtil.isCollection();

		// Build the occurrence string; if it is a collection then it is
		// unbounded
		String maxStr = (isCollection ? "unbounded" : "1");
		String elementOptions = "minOccurs=\"0\" maxOccurs=\"" + maxStr + "\"";

		// Build each concrete classes schema and the wrapper option type.
		StringBuffer optionType = new StringBuffer();
		String elementSuffix = "Type";
		if (isCollection)
			elementSuffix = "ListType";
		String listElementName = methodName + elementSuffix;

		optionType.append("<xsd:complexType name=\"" + listElementName
				+ "\">\n");
		optionType.append("  <xsd:sequence "
				+ (isCollection ? elementOptions : "") + ">\n");
		optionType.append("    <xsd:choice>\n");
		for (Iterator<Class> itt = concreteClasses.iterator(); itt.hasNext();) {
			// Build the sub-type for each concrete class.
			Class nextConcreteClass = itt.next();
			ClassUtil nextConcreteClassUtil = new ClassUtil(nextConcreteClass);

			StringBuffer resultRef = new StringBuffer();

			// Build the element name. If there is more than one
			// type for a method we can't just use the method name
			// as the element name so build a suitable method name.
			String concreteClassElementName = methodName;
			if (isCollection || (concreteClasses.size() > 1))
				concreteClassElementName += ("." + nextConcreteClassUtil.getName());

			// Build the classes schema type if necessary and return
			// a reference to that class (as either element or attribute)
			// in the variable 'resultRef'
			boolean isElement = tool.buildClass(nextConcreteClass,
					concreteClassElementName, schema, isCollection,
					"minOccurs=\"0\" maxOccurs=\"1\"", resultRef, indent);

			// Add the ref to the appropriate palce.
			if (isCollection)
				optionType.append(resultRef);
			else if (isElement)
				schemaElements.append(resultRef);
			else
				schemaAttrs.append(resultRef);

			if (resultRef.length() > 0)
				count++;
		}

		optionType.append("    </xsd:choice>\n");
		optionType.append("  </xsd:sequence>\n");
		String complexTypeClass;
		if (returnClass.isArray())
			complexTypeClass = tool.buildArrayClassAttribute(returnClassUtil.getArrayClass());
		else
			complexTypeClass = tool.buildClassAttribute(ArrayList.class);
		optionType.append(complexTypeClass);
		optionType.append("</xsd:complexType>\n\n");

		if (isCollection) {
			// Check to see if this list type has already been built
			// and if so don't build another one.
			if (!tool.isCustomTypeBuilt(listElementName))
				schema.insert(0, optionType.toString());

			schemaElements.append(XmlTool.buildOptionalElementRef(methodName,
					listElementName, "    "));
		}
	}
}
