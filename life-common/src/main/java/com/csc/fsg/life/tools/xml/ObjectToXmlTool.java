package com.csc.fsg.life.tools.xml;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

import com.csc.fsg.life.common.util.DateHelper;
import com.csc.fsg.life.common.util.ObjectFieldRef;
import com.csc.fsg.life.common.util.TagHelper;

/**
 * Class to turn an object into an XML Document.
 */
@SuppressWarnings("unchecked")
public class ObjectToXmlTool extends XmlTool {

	/**
	 * Map of object/field key to a list of more specific classes. For example
	 * if a java class holds onto a list all we can infer from reflection is
	 * that it contains a list of type Object. But this map can identify the
	 * real type(s) that the list contains. Map of ObjectFieldRef -> List (of
	 * class names as strings)
	 */
	private Map<ObjectFieldRef, List<Class>> abstractMap = new HashMap<ObjectFieldRef, List<Class>>();

	/** Set of object/field keys to suppress. */
	private Set<ObjectFieldRef> skippedSet = new HashSet<ObjectFieldRef>();

    private boolean normalize = true;

	/**
	 * The Constructor.
	 *
	 * @param abstractClassDetails the abstract class details
	 * @param skippedDetails the skipped details
	 */
	public ObjectToXmlTool(Map<ObjectFieldRef, List<Class>> abstractClassDetails, Set<ObjectFieldRef> skippedDetails) {

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
	 * Main method. Transforms the specified object into a XML Document.
	 *
	 * @param obj the object
	 * @param ns the xml name space
	 * @return the document
	 * @throws IllegalAccessException the illegal access exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Document buildDocument(Object obj, String ns)
			throws ParserConfigurationException, ClassNotFoundException,
			IllegalAccessException, InvocationTargetException {

		// Build doc from body ...
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.newDocument();

		// Build the root element
		Element root = (Element) buildNode(doc, ns, obj, null, null, "");
		doc.appendChild(root);

		// Return the built document.
		return doc;
	}

	/**
	 * Recursive method which builds an elment from an object.
	 *
	 * @param doc The document this element should be created for.
	 * @param ns The namespace URI for this node.
	 * @param obj The object to create an element form.
	 * @param parent The parent obj. May be null for top level.
	 * @param methodName The method name that returned this object. Note this
	 *        will be null for the top-level.
	 * @param indent The indentation to use for debugging.
	 * @return the node
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public Node buildNode(Document doc, String ns, Object obj, Object parent,
			String methodName, String indent) throws ClassNotFoundException,
			IllegalAccessException, InvocationTargetException {

		// If no data in this field then bail.
		if (obj == null)
			return null;

		Class clz = obj.getClass();
		ClassUtil classUtil = new ClassUtil(clz);
		log(indent + "Building element for class: " + clz.getName());
		indent += "  ";

		// Determine if in a collection or an array.
		String elemName = classUtil.getName();
		boolean isCollection = false;
		boolean isArray = false;
		boolean isAbstract = false;
		if (parent != null) {
			Class parentClz = parent.getClass();

			if (parent instanceof Collection) {
				isCollection = true;
			} else if (parentClz.isArray()) {
				isArray = true;

				// If this is an array of simple types
				// we must change the class name to the simple
				// type from the object wrapper.
				ClassUtil parentClassUtil = new ClassUtil(parentClz);
				Class arrayClass = parentClassUtil.getArrayClass();
				ClassUtil arrayClassUtil = new ClassUtil(arrayClass);
				elemName = arrayClassUtil.getName();
			}

			// If the object isn't a collection, then we need to
			// check if it is an abstract class that is returned b/c
			// then we will need to qualify the element name.
			else if (!(obj instanceof Collection)) {
				// Check for concrete classes, if present
				ObjectFieldRef methodRef = new ObjectFieldRef(
						parentClz.getName(), methodName);
				List<Class> concreteClasses = getConcreteClasses(methodRef, clz);
				if (concreteClasses != null) {
					log(indent + "Found on class '" + parentClz.getName()
							+ "' " + "method '" + methodName + "' " + "class '"
							+ clz.getName() + "' " + concreteClasses.size()
							+ " concrete classes.");
					isAbstract = true;
				}
			}
		}

		// Objects other than arrays and collections
		// use the method name as the element name.
		if (isCollection || isArray) {
			elemName = methodName + "." + elemName;
		} else if (isAbstract) {
			elemName = methodName + "." + classUtil.getName();
		} else if (methodName != null) {
			elemName = methodName;
		}

		// If this is a simple type create an attribute or element
		// and return.
		if (classUtil.isSimpleType()) {
			String attrValueStr = getValue(obj);

			if (isCollection || isArray) {
				// Cretae the element.
				Element elem = doc.createElement(elemName);

				// Set value as content of element.
				Text data = doc.createTextNode(attrValueStr);
				elem.appendChild(data);

				return elem;
			} else {
				Attr attr = doc.createAttribute(methodName);
				attr.setValue(attrValueStr);
				return attr;
			}
		}

		// else if this is a collection then ...
		else if (obj instanceof Collection) {
			log(indent + "Creating collection element for: " + methodName);

			// Create the collection element.
			Element collectionElem = doc.createElement(methodName);

			// Create a child element for each item in the collection.
			Collection coll = (Collection) obj;
			for (Iterator itt = coll.iterator(); itt.hasNext();) {
				Object nextObj = itt.next();
				Element nextChild = (Element) buildNode(doc, null, nextObj,
						obj, methodName, indent);
				collectionElem.appendChild(nextChild);
			}

			return collectionElem;
		}

		// TODO: Add an else clause in here to handle maps.

		// Else if this is an array ...
		else if (clz.isArray()) {
			// Bail on multi-dimension arrays - TODO
			int depth = classUtil.getArrayDepth();
			if (depth > 1)
				throw new UnsupportedOperationException(
						"Multi-Dimension arrays not yet supported.");

			// Create the array element.
			Element collectionElem = doc.createElement(methodName);

			// Iterate over the elements of the array.
			int arrayLen = Array.getLength(obj);
			for (int i = 0; i < arrayLen; i++) {
				Object arrayObj = Array.get(obj, i);
				Element nextChild = (Element) buildNode(doc, null, arrayObj,
						obj, methodName, indent);
				collectionElem.appendChild(nextChild);
			}

			return collectionElem;
		}

		// Else is is a simple complex type so ...
		else {
			// Cretae the element.
			Element elem = null;
			if (ns != null)
				elem = doc.createElementNS(ns, elemName);
			else
				elem = doc.createElement(elemName);

			// Create the attributes and child elements.
			ObjectToXmlMethodVisitor visitor = new ObjectToXmlMethodVisitor(
					clz, skippedSet, this, doc, elem, obj, indent);
			visitor.visit();

			return elem;
		}
	}

	/**
	 * Calls the specified methdod to get the resulting value for the specified
	 * instance.
	 *
	 * @param value the value
	 * @return the value
	 */
	public String getValue(Object value) {

		if (value instanceof Calendar)
			return DateHelper.toFormattedISOShortString((Calendar) value);
		else if (value instanceof Date)
			return DateHelper.toFormattedISOShortString((Date) value);

        if( isNormalize())
            return TagHelper.normalize(value.toString());
        return value.toString();
	}

    public boolean isNormalize() {
        return normalize;
    }

    public void setNormalize(boolean normalize) {
        this.normalize = normalize;
    }
}
