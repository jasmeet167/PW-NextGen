package com.csc.fsg.life.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* Modifications: ENH911 */
/**
 * Utility class to help with reflection related functionality.
 */
@SuppressWarnings("unchecked")
public class ReflectionUtils
{
    /**
     * Gets the method representing the "getter" for the given attribute.
     * 
     * @param cls       The class the attribute is a member of
     * @param attribute The attribute to get "getter" method for.
     * @return the "getter" method.
     */
    public static Method getAttributeGetter(Class cls, String attribute)
    {
        try {
            String methodName = "get" + attribute.substring(0, 1).toUpperCase() + 
                                        attribute.substring(1, attribute.length());
            
            return cls.getMethod(methodName, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Gets the method representing the "setter" for the given attribute.
     * 
     * @param cls       The class the attribute is a member of
     * @param attribute The attribute to get "setter" method for.
     * @return the "setter" method.
     */
    public static Method getAttributeSetter(Class cls, String attribute)
    {
        try {
            Class type = getAttributeType(cls, attribute);
            
            String methodName = "set" + attribute.substring(0, 1).toUpperCase() + 
                                        attribute.substring(1, attribute.length());
            return cls.getMethod(methodName, new Class[]{type});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Gets the attribute's type by getting the return type of the 
     * public accessor ("getter") method.
     * 
     * @param obj       Class the method is a member of
     * @param attribute Name of the attribute the accessor is for
     * @return  the return type
     */
    public static Class getAttributeType(Class cls, String attribute)
    {
        try {
            Method method = getAttributeGetter(cls, attribute);
            return method.getReturnType();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Get the value of the attribute with the given name by calling the
     * public accessor/getter method.
     * @param obj       object that contains the given field
     * @param attribute name of the attribute
     * @return result of the invocation of "getter" method
     * @throws Exception
     */
    public static Object getAttributeValue(Object obj, String attribute)
        throws Exception
    {
        Method method = getAttributeGetter(obj.getClass(), attribute);
        return method.invoke(obj, new Object[]{});        
    }
    
    /**
     * Invoke the method with the given name and return the result.
     * @param obj             object that contains the given method
     * @param methodName      name of the field
     * @param parameterTypes  array of method parameter types
     * @param parameterValues array of method parameter values
     * @return result of the invocation of the method
     * @throws Exception
     */
    public static Object invokeMethod(Object obj, 
                                      String methodName,
                                      Class[] parameterTypes, 
                                      Object[] parameterValues)
        throws Exception
    {
        Method method = obj.getClass().getMethod(methodName, parameterTypes);
        return method.invoke(obj, parameterValues);
    }
    
    /**
     * Returns true if the given method is public.
     */
    public static boolean isPublic(Method method)
    {
        int modifiers = method.getModifiers();
        return Modifier.isPublic(modifiers);
    }
    
    /**
     * Returns true if the given field is public.
     */
    public static boolean isPublic(Field field)
    {
        int modifiers = field.getModifiers();
        return Modifier.isPublic(modifiers);
    }
    
    /**
     * Returns true if the given method is final.
     */
    public static boolean isFinal(Method method)
    {
        int modifiers = method.getModifiers();
        return Modifier.isFinal(modifiers);
    }
    
    /**
     * Returns the generic return type for a given method.
     */
    public static Type[] getGenericReturnType(Method method)
    {
    	Type returnType = method.getGenericReturnType();

    	if (returnType instanceof ParameterizedType) {
    	    ParameterizedType type = (ParameterizedType) returnType;
    	    return type.getActualTypeArguments();
    	}
    	
    	return null;
    }
}
