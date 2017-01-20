package com.csc.fsg.life.tools.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import com.csc.fsg.life.common.util.ObjectFieldRef;

/* Modifications: T0140 */

/**
 * The Class ObjectToXmlMethodVisitor.
 */
public class ObjectToXmlMethodVisitor extends MethodVisitor {

	private ObjectToXmlTool tool;
	private Document doc;
	private Element elem;
	private Object obj;
	private String indent;

	/**
	 * Buils a method visitor with a class.
	 * 
	 * @param clz the class
	 * @param skippedSet the skipped set
	 * @param tool the ObjectToXMLTool
	 * @param doc the document
	 * @param elem the element object
	 * @param obj the object
	 * @param indent the indent
	 */
	public ObjectToXmlMethodVisitor(Class clz, Set<ObjectFieldRef> skippedSet,
			ObjectToXmlTool tool, Document doc, Element elem, Object obj,
			String indent) {

		super(clz, skippedSet);
		this.tool = tool;
		this.doc = doc;
		this.elem = elem;
		this.obj = obj;
		this.indent = indent;
	}

	/**
	 * Visit method.
	 * 
	 * @param m the method
	 * @param methodName the method name
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void visitMethod(Method m, String methodName)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException 
	{
		log(indent + "Method Name: " + m.getName());
		// Call the method to get the value.
		Object attrValue = "";
		try {
			attrValue = m.invoke(obj, null);
		} catch (Throwable e) {
			System.out.println(e.getMessage() + " Error occured invoking "
					+ m.getName() + " on " + clz.getName());
		}

		// Build the next child and add.
		Node child = tool.buildNode(doc, null, attrValue, obj, methodName,
				indent);
		if (child != null)
			if (child instanceof Attr)
				elem.setAttributeNode((Attr) child);
			else
				elem.appendChild((Element) child);
	}
}
