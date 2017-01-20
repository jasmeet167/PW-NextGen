package com.csc.fsg.life.tools.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.csc.fsg.life.common.util.ObjectFieldRef;

/**
 * The Class MethodVisitor.
 */
@SuppressWarnings("unchecked")
abstract public class MethodVisitor {

	/** The class. */
	protected Class clz;

	/** The skipped set. */
	protected Set skippedSet;

	/**
	 * Buils a method visitor with a class.
	 * 
	 * @param clz the class
	 * @param skippedSet the skipped set
	 */
	public MethodVisitor(Class clz, Set skippedSet) {

		this.clz = clz;
		this.skippedSet = skippedSet;
	}

	/**
	 * Implemnt this to do the work when a good method is found.
	 * 
	 * @param m the method object
	 * @param methodName the method name
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	abstract public void visitMethod(Method m, String methodName)
			throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException;

	/**
	 * Visit the public methods of the class.
	 * 
	 * @throws IllegalAccessException the illegal access exception
	 * @throws InvocationTargetException the invocation target exception
	 * @throws ClassNotFoundException the class not found exception
	 */
	public void visit() throws ClassNotFoundException, IllegalAccessException,
			InvocationTargetException {

		// First determine all applicable methods.
		List<Method> validMethods = new ArrayList<Method>();
		Method methods[] = clz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method nextMethod = methods[i];
			String methodName = nextMethod.getName();

			// Ensure the method is public.
			int mode = nextMethod.getModifiers();
			if (Modifier.isPublic(mode) == false)
				continue;

			// Always skip the getClass() method
			if (methodName.equals("getClass"))
				continue;

			// If the method has any parameter then bail.
			Class params[] = nextMethod.getParameterTypes();
			if (params.length > 0)
				continue;

			// If the method doesn't start with get or is
			// then bail
			methodName = getMethodId(methodName);
			if (methodName == null)
				continue;

			// Check to see if the class this method is declared on should be
			// skipped.
			Class declaringClass = nextMethod.getDeclaringClass();
			ObjectFieldRef classRef = new ObjectFieldRef(clz.getName(),
					declaringClass.getName());
			if (skippedSet.contains(classRef))
				continue;
			// If it doesn't contain the class, check for the wildcard.
			classRef = new ObjectFieldRef("*", declaringClass.getName());
			if (skippedSet.contains(classRef))
				continue;

			// Check to see if this method should be skipped.
			ObjectFieldRef methodRef = new ObjectFieldRef(clz.getName(),
					methodName);
			if (skippedSet.contains(methodRef))
				continue;
			// Check to see if it is skipped b/c of the declaring class.
			methodRef = new ObjectFieldRef(declaringClass.getName(), methodName);
			if (skippedSet.contains(methodRef))
				continue;
			// Check to see if the method id is skipped globally
			methodRef = new ObjectFieldRef("*", methodName);
			if (skippedSet.contains(methodRef))
				continue;

			// Check for a void return type and warn ...
			Class returnClass = nextMethod.getReturnType();
			if (returnClass == Void.TYPE) {
				System.out.println("Skipping method with void return type: "
						+ methodName + " from class: " + clz.getName()
						+ " declaring class: " + declaringClass.getName());
				continue;
			}

			// Add this method to the list.
			validMethods.add(nextMethod);
		}

		// Sort the methods alphabetically.
		Collections.sort(validMethods, new MethodComparator());

		// Visit the methods
		for (Iterator<Method> itt = validMethods.iterator(); itt.hasNext();) {
			Method nextMethod = itt.next();
			String methodName = getMethodId(nextMethod);

			// Perform the visitor specific work.
			visitMethod(nextMethod, methodName);
		}
	}

	/**
	 * The Class MethodComparator.
	 */
	public class MethodComparator implements Comparator {

		/**
		 * Compare.
		 * 
		 * @param o1 the object1
		 * @param o2 the object2
		 * @return the int
		 */
		public int compare(Object o1, Object o2) {

			Method m1 = (Method) o1;
			Method m2 = (Method) o2;

			return getMethodId(m1).compareTo(getMethodId(m2));
		}
	}

	/**
	 * Gets the method id.
	 * 
	 * @param m the method
	 * @return the method id
	 */
	protected String getMethodId(Method m) {

		return getMethodId(m.getName());
	}

	/**
	 * Gets the method id.
	 * 
	 * @param methodName the method name
	 * @return the method id
	 */
	protected String getMethodId(String methodName) {

		if (methodName.startsWith("get")) {
			return methodName.substring(3);
		} else if (methodName.startsWith("is")) {
			return methodName.substring(2);
		}

		return null;
	}

	/**
	 * Log.
	 * 
	 * @param msg the message
	 */
	public void log(String msg) {

		if (XmlTool.DEBUG)
			System.out.println(msg);
	}
}
