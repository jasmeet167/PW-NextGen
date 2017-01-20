package com.csc.fsg.life.biz.meta;

import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.copyobject.CopyObject;
import com.csc.fsg.life.biz.copyobject.CopyObjectExternalGroupPointer;
import com.csc.fsg.life.biz.meta.ServiceMetaData.ServiceEventType;
import com.csc.fsg.life.biz.util.Utils;
import com.csc.fsg.life.common.util.ReflectionUtils;
import com.csc.fsg.life.context.UserContextAware;

/* Modifications: T0135, T0150 */

/**
 * Helper class to get meta data for a classes properties.
 */
public class MetaDataHelper
{
    /** logger for the MetaDataHelper class. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");
    
    /**
     * Get the <code>MetaData</code> for the given business object class and
     * attribute.  The meta data is housed on the "getter" method of the 
     * attribute within the interface.
     *  
     * @param objClass
     * @param attributeName
     * @return The MetaData object found or null if not found.
     */
	@SuppressWarnings("unchecked")
	public static MetaData getMetaData(Class objClass, String attributeName)
	{
		Method method = getGetMethod(objClass, attributeName);

		return method.getAnnotation(MetaData.class);
	}
	
	/**
	 * Find the field on the given class that has the specified offset.  In the process,
	 * build the unique id of the field and return it. 
	 */
	@SuppressWarnings("unchecked")
	public static String getAttributeForOffset(Class objClass, int offset, int startingOffset) 
	{
	    // 0 means there is no offset
	    if (offset == 0)
	        return null;
	    
	    log.debug("Looking for field at offset: " + offset);
	    
	    StringBuffer idBuffer = new StringBuffer();
	    
	    // remove the package prefix from the class name
	    String className = objClass.getName();
	    className = className.substring(className.lastIndexOf(".")+1);
	    
	    // remove the CopyObject suffix if necessary
	    if (className.indexOf("CopyObject") > 0)
	        className = className.substring(0, className.indexOf("CopyObject"));
	    
	    idBuffer.append(Utils.uncapitalize(className));
	    
	    // get all methods
	    Method[] methods = objClass.getDeclaredMethods();
	    for (int i = 0; i < methods.length; i++) {
	        Method method = methods[i];
	        
	        // only deal with public methods
	        if (!ReflectionUtils.isPublic(method))
	            continue;
	        
	        // only deal with "getters"
	        String methodName = method.getName();
	        if (!methodName.startsWith("get"))
	            continue;
	        
	        // is this method for an external group?
	        Class returnClass = method.getReturnType();
	        if (UserContextAware.class.isAssignableFrom(returnClass)) {
	            String externalGroupClassName = returnClass.getName();
	            externalGroupClassName = externalGroupClassName.substring(externalGroupClassName.lastIndexOf(".")+1);           
                
                MetaData meta = getMetaData(objClass, externalGroupClassName);
                if (meta != null) {
                    int fieldOffset = meta.offset();
                    int maxOffset = fieldOffset + meta.size();
                    
                    if (offset >= fieldOffset && offset <= maxOffset) {
                        log.debug("  Looking for offset in external group: " + externalGroupClassName);
                        String attr = getAttributeForOffset(returnClass, offset, fieldOffset);
                        if (attr != null) {
                            idBuffer.append(Utils.capitalize(attr));
                            return idBuffer.toString();
                        }
                    }
                }
	        }
	        
	        // get attribute name by removing "get" prefix
	        String attributeName = methodName.substring(3);
	        MetaData meta = getMetaData(objClass, attributeName);
	        if (meta != null) {
	            int fieldOffset = meta.offset();
                fieldOffset += startingOffset;
    	        
	            if (fieldOffset == offset) {
    	            idBuffer.append(attributeName);
    	            return idBuffer.toString();
    	        }
	        }
	    }
	    
	    return null;
	}

	/**
	 * Analyze the given <code>copyObjectClass</code>, and identify the lowest-level 
	 * copy object class, in which the field, whose location in the corresponding 
	 * copybook is indicated by the given <code>offset</code>, is declared.   
	 * <p>
	 * If the field is declared in the top-level copy object class, then return 
	 * <code>null</code>.  If, on the other hand, it is declared in a copy object class 
	 * representing an external group, then return a pointer, which can be used to access 
	 * this group, and, when applicable, index value required to identify the corresponding 
	 * element occurrence within this group.
	 * <p>
	 * The following assumptions are made in the process of discovery of this
	 * copy object class:
	 * <ul>
	 * <li>The @MetaData annotation is only associated with getter methods,
	 * which return values of business attributes.</li>
	 * <li>Getter methods for business attributes corresponding to fields, which
	 * have the OCCURRS clause in their applicable copybooks, always have return
	 * type, from which <code>List&lt;? extends 
	 * BusinessObject&gt;</code> is assignable.</li>
	 * <li>The process of searching for the applicable copy object is conducted
	 * to the depth of one level only. If at that time an external group copy
	 * object has not been identified, a <code>null</code> will be returned, 
	 * thus indicating that the field resides in the top-level copy object.</li>
	 * </ul>
	 */
	public static CopyObjectExternalGroupPointer getExternalGroupPointerForOffset(Class<? extends CopyObject> copyObjectClass, int offset)
	{
		Method[] methods = copyObjectClass.getMethods();

		for (Method method : methods) {
			Method interfaceMethod = getBeanMethod(copyObjectClass, method.getName(), true);
			if (interfaceMethod == null)
				continue;

			MetaData annotation = interfaceMethod.getAnnotation(MetaData.class);
			if (annotation == null)
				continue;

			int annotationSize = annotation.size();
			int annotationOffset = annotation.offset();
			int annotationOccurenceCount = annotation.occurrenceCount();
			int annotationEndLimitOffset = annotationOffset + annotationSize * annotationOccurenceCount;

			if (offset >= annotationOffset && offset < annotationEndLimitOffset) {
				if (annotationOccurenceCount == 1) {
					if (CopyObject.class.isAssignableFrom(method.getReturnType())) {
						// Offset matched to a field in an external group.
						return new CopyObjectExternalGroupPointer(method);
					}
					else {
						// Offset matched to a field in the top-level copy object.
						return null;
					}
				}
				else {
					// Offset matched to a field in an externally-occurring group.
					int occursIndex = (offset - annotationOffset) / annotationSize;
					return new CopyObjectExternalGroupPointer(method, occursIndex);
				}
			}
		}

		// If no match found, then assume that the field matching the given offset
		// is in the top-level copy object.
		return null;
	}

    /**
     * Get the <code>MetaData</code> for the given business service class and
     * method.  The meta data is housed on the method within the interface.
     *  
     * @param objClass
     * @param method
     * @return The ServiceMetaData object found or null if not found.
     */
    @SuppressWarnings("unchecked")
	public static ServiceMetaData getServiceMetaData(Class objClass, Method method)
    {
        // if no method is specified, get the meta data of the class
        if (method == null)
            return (ServiceMetaData)objClass.getAnnotation(ServiceMetaData.class);
        else
            return method.getAnnotation(ServiceMetaData.class);
    }

	/**
	 * This method facilitates disambiguation of <code>ServiceMetaData</code>
	 * event type. This is necessary due to the temporary presence of both
	 * <code>eventType</code>, and <code>serviceEventType</code> attributes,
	 * which both represent the same logical concept. Both are present during
	 * transition from String-typed <code>eventType</code> (deprecated) to
	 * <code>serviceEventType</code>.
	 * <p>
	 * Once the transition is complete, this method will have become obsolete,
	 * and is expected to be removed.
	 * 
	 * @return Textual representation of the given <code>metaData</code>
	 *         instance's event type.
	 * 
	 * @see com.csc.fsg.life.biz.meta.ServiceMetaData#eventType()
	 */
	public static String getServiceEventTypeAsString(ServiceMetaData metaData)
	{
		ServiceEventType eventType = metaData.serviceEventType();
		if (eventType == ServiceEventType.NONE)
			return metaData.eventType();
		else
			return eventType.toString();
	}

	/**
	 * Format a number according to the meta data.
	 * @param objClass The class the attribute is on.
	 * @param attributeName The attribute to format the value to.
	 * @param value The value to format.
	 * @return The numeric value formated as a string. 
	 */
	@SuppressWarnings("unchecked")
	static public String formatNumber(Class objClass, String attributeName, Number value)
	{
		// Get the metadata documenting this property
		// Bail on no metadata
		MetaData metaData = getMetaData(objClass, attributeName);
		if (metaData == null)
			return value.toString();

		// Build format mask, and format the value
		int len = metaData.size();
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < len; i++)
			buf.append('0');

		NumberFormat formatter = new DecimalFormat(buf.toString());
		return formatter.format(value.longValue());
	}


	/**
	 * Gets a getter interface method for an object's specified attribute.
	 * @param objClass The class to get the method from (or it's superclass)
	 * @param attributeName The attribute to get the method for.
	 * @return The Method object or null if not found.
	 */
	@SuppressWarnings("unchecked")
	public static Method getGetMethod(Class objClass, String attributeName) 
	{
		String methodName = "get" + attributeName.substring(0, 1).toUpperCase()
				+ attributeName.substring(1, attributeName.length());
		return getBeanMethod(objClass, methodName, true);
	}

	/**
	 * Gets a setter interface method for an object's specified attribute.
	 * @param objClass The class to get the method from (or it's superclass)
	 * @param attributeName The attribute to get the method for.
	 * @return The Method object or null if not found.
	 */
	@SuppressWarnings("unchecked")
	public static Method getSetMethod(Class objClass, String attributeName) 
	{
		String methodName = "set" + attributeName.substring(0, 1).toUpperCase()
				+ attributeName.substring(1, attributeName.length());
		return getBeanMethod(objClass, methodName, false);
	}
    
	/**
	 * Gets a "getter" interface method by name.  If the given class
     * is an interface, walk its methods until the name matches
     * the given methodName.  If the given class is not an interface,
     * get its interfaces and walk them until a method name match is
     * found.
	 * 
	 * @param obj
	 * @param methodName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Method getBeanMethod(Class objClass, String methodName, boolean getter)
	{
		// Typically, meta-data is defined on the interface.  So if
		// the class object passed in is NOT an interface then walk its
		// interfaces first.
		if (objClass.isInterface() == false) {
			Class interfaces[] = objClass.getInterfaces();
			for(int i = 0; i < interfaces.length; i++) {
				Method getMethod = getBeanMethod(interfaces[i], methodName, getter);
				if (getMethod != null)
					return getMethod;
			}
		} 
		
		// metadata is defined on the interface, so walk through methods on interface
		Method methods[] = objClass.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
            Method nextMethod = methods[i];
            // First ensure for a name match.
            String nextMethodName = nextMethod.getName();
            if (!nextMethodName.equals(methodName))
                continue;

            // Next ensure current number of parameters.
			// No parameters for getter, 1 for setter.
            Class parms[] = nextMethod.getParameterTypes();
			int len = 0;
			if (!getter)
				len = 1;
            if (parms.length != len)
                continue;

            // This matches so bail.
            return nextMethod;
        }

		if (objClass.getSuperclass() != null)
            return getBeanMethod(objClass.getSuperclass(), methodName, getter);

        // No match.
		return null;
	}	
}