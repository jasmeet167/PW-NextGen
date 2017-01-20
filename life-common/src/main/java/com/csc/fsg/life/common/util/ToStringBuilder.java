package com.csc.fsg.life.common.util;


/**
 * A simple replacement for the more complex version in commons-lang.
 * Used to help construct the description returned by an object's
 * <code>toString()</code> method.
 */
public class ToStringBuilder {
	private StringBuffer buffer = new StringBuffer();

	private int attributeCount;
	private String indent = "  ";

	public ToStringBuilder(Object target) {
		appendClassName(target);
		appendHashCode(target);
	}

	private void appendHashCode(Object target) {
		buffer.append('@');
		buffer.append(Integer.toHexString(target.hashCode()));
	}

	private void appendClassName(Object target) {
		String className = target.getClass().getName();
		int lastdotx = className.lastIndexOf('.');

		buffer.append(className.substring(lastdotx + 1));
	}

	/**
	 * Returns the final assembled string. This may only be invoked once, after
	 * all attributes have been appended.
	 */
	public String toString() {
		if (attributeCount > 0)
			buffer.append(']');

		String result = buffer.toString();

		buffer = null;

		return result;
	}

	public void append(String attributeName, boolean value) {
		append(attributeName, String.valueOf(value));
	}

	public void append(String attributeName, int value) {
		append(attributeName, String.valueOf(value));
	}

	public void append(String attributeName, Object value) {
		append(attributeName, String.valueOf(value));
	}
	
	public void append(String attributeName, String value) {
		if (attributeCount++ == 0)
			buffer.append('[');

		buffer.append("\n");
		buffer.append(indent);
		buffer.append(attributeName);
		buffer.append('=');
		buffer.append(value);
	}
}