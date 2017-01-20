package com.csc.fsg.life.biz.copyobject;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.avm.AllowableValuesHelper;
import com.csc.fsg.life.avm.RulesManagerAvmImpl;
import com.csc.fsg.life.biz.bo.BusinessObjectException;
import com.csc.fsg.life.biz.bo.HistoryDetailItem;
import com.csc.fsg.life.biz.exception.BusinessMessage;
import com.csc.fsg.life.biz.meta.MetaDataHelper;
import com.csc.fsg.life.biz.service.ServiceValidationException;
import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;
import com.csc.fsg.life.common.util.DateHelper;
import com.csc.fsg.life.common.util.NumberHelper;
import com.csc.fsg.life.common.util.ReflectionUtils;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.convert.CICSConverter;
import com.csc.fsg.life.convert.Converter;
import com.csc.fsg.life.convert.FormatHelper;
import com.csc.fsg.life.dao.bo.IBusinessObject;
import com.csc.fsg.life.rules.IRulesManager;

/* Modifications: ENHT0085, T0140, T0150, T0175, ENH1019, WMA-1879 */

/**
 * Base class for all copy objects.  Copy objects are responsible
 * for building and maintaining a byte array that represents the
 * copybook data.  These responsibilities include:
 * 
 *  <ul>
 *      <li>Retrieving data from the byte array and converting 
 *          to the appropriate java type.
 *      <li>Validating the data length/type to ensure the copybook
 *          offsets are maintained.
 *      <li>Updating the byte array as values change.
 *      <li>Converting data to the format required by target
 *          system (ex. EBCDIC, ASCII, etc).
 *      <li>Getting default values from Allowable Values.
 *  </ul>
 */
public abstract class CopyObject 
    implements IBusinessObject, Serializable
{
    /** Standard apache commons logger. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");
    
    /** Array of bytes representing the copybook data. */
    protected byte[] bytes;
    
    /** Indicates whether this object was already initialized. */
    protected boolean initialized = false;
    
    /** Rules Manager for accessing AVM. */
	private IRulesManager rulesManager;
	
    /** User context. */
    private UserContext userContext;

    /** Data converter to convert data to/from host system. */
    private Converter converter;

    /**
     * Creates an empty copyobject.  This accesses the <code>RulesManager</code> 
     * and gets the converter for the given environment.
	 */
	public CopyObject()
	{
        rulesManager = RulesManagerAvmImpl.getInstance();
        
        // get the current environment, if available, and set the appropriate converter
        ApplicationEnvironmentBean environment = ApplicationEnvironmentBean.getEnvironment();
        if (environment != null && environment.getConverter() != null)
            converter = environment.getConverter();
        else
            converter = new CICSConverter();
	}
   
	/** 
	 * Get the User state information object. 
	 * 
	 * @return the User state information object. 
	 * @see #setUserContext
     */
	public UserContext getUserContext() 
    { 
        return userContext; 
    }
	
	/**
	 * Set the User state information object. 
	 * 
	 * @param c the User state information object. 
	 * @see #getUserContext
     */
	public void setUserContext(UserContext c) 
    { 
        userContext = c;
    }

    /**
     * Set the converter to use for data conversions.
     * 
     * @param converter the converter to use for data conversions.
     */
    public void setConverter(Converter converter) 
    {
        this.converter = converter;
    }

    /**
     * Initialization method for copy objects.
     * 
     * @param userContext The user context.
     * @throws CopyObjectException If initialization fails.
	 */
    public abstract void init(UserContext userContext) throws CopyObjectException;

	/**
	 * Initialization method for copy objects.  Additional flag that can be used to 
	 * force re-initialization.
	 * 
	 * @param userContext The user context.
	 * @param isForced true to force re-initialization.
	 * @throws BusinessObjectException If initialization fails.
	 */
    public void init(UserContext userContext, boolean isForced) 
		throws BusinessObjectException
	{
		if (isForced)
			initialized = false;
			
		init(userContext);
	}

    /**
     * Initialization method for copy objects.  This initializes the copy object 
     * off of the byte array.
     * 
     * @param userContext The user context.
     * @param b The byte array to initialize to.
     * @throws CopyObjectException If initialization fails.
	 */
    public abstract void init(UserContext userContext, byte[] b) throws CopyObjectException;

	/**
	 * Returns the name of the copybook.
	 * 
	 * @return the name of the copybook.
	 */
    public abstract String getCopybookName();

	/**
	 * Returns a bye array that represents the copybook.  This byte array is
	 * already converted to the appropriate format (ASCII, EBCIDIC, etc.) 
	 * as defined by the converter.
	 * 
	 * @return a bye array that represents the copybook. 
	 * @throws CopyObjectException If conversion fails.
	 */
    public abstract byte[] toBytes() throws CopyObjectException; 

	/**
	 * Returns a list of detail objects.  Each object in the list represents one 
	 * field in the copybook.
	 * 
	 * @param prefix A string to prefix field names with.
	 * @return a list of detail objects.
	 */
	public abstract List<HistoryDetailItem> toDetailList(String prefix);

	/**
	 * Call this to do object validation.  The base class does nothing for validation 
	 * by default.
	 * 
	 * @throws ServiceValidationException If validation fails.
	 */
    public void validate()
		throws ServiceValidationException
	{
	}

	/**
	 * Helper method used to validate an object.
	 */
	@SuppressWarnings("rawtypes")
	protected void validate(CopyObject co, ServiceValidationException sve)
	{
		if (co == null) 
			return;
		List l = new ArrayList();
		l.add(co);
		validate(l, sve);
	}

	/**
	 * Helper method used to validate a list of objects.  Each object will be validated
	 * and all errors will be returned.
	 * 
	 * @param objects The list of objects.
	 * @param e The exception that validation errors are added to.
	 */
	@SuppressWarnings("rawtypes")
	protected void validate(List objects, ServiceValidationException e)
	{
		if (objects == null) 
			return;
		for (Iterator itt = objects.iterator(); itt.hasNext();) {
			CopyObject co = (CopyObject) itt.next();
			try {
				if (co != null)
					co.validate();
			}
			catch(ServiceValidationException sve) {
				e.addMessages(sve.getMessages());
			}
		}
	}

	/**
	 * Validates the specified field and value for a valid allowable value.
	 * 
	 * @param field The field to validate.
	 * @param value The value to check on the field.
	 * @param sve The exception that validation errors are added to.
	 */
	public void validateField(String field, Object value, ServiceValidationException sve)
	{
		// Bail on null.
		if (value == null)
			return;

		Object v = value;

        // temporarily bypass Doubles
        if (value instanceof Double)
            return;
        
		// If this is a number, then format the number for allowable values.
		if (value instanceof Number)
			v = MetaDataHelper.formatNumber(getClass(), field, (Number)value);
		
		if (AllowableValuesHelper.validateValue(getUserContext(), getClass(), field, v) == false)
			addMessage(sve, field);
	}

	/**
	 * Adds an error message indicating an invalid allowable value
	 * 
	 * @param sve The exception that validation errors are added to.
	 * @param field The field name the error occurred on.
	 */
	public void addMessage(ServiceValidationException sve, String field)
	{
		sve.addMessage(new BusinessMessage(null, "Invalid value for field " + field, field));
	}

    /**
     * Gets the size of this copyobject's byte array.
     * 
     * @return the size of this copyobject's byte array.
     */
    public int getSize() {
        return bytes.length;
    }
    
    /**
     * Takes a string and right justifies it to the appropriate
     * length
     */
    public String rightJustify(String string, int length) {
        if (string == null)
            string = "";
        
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length - string.length(); i++) {
            sb.append(" ");
        }
        sb.append(string);
        
        return sb.toString();
    }
   
	/**
	   Pad a string with zeros.
	   @param data the string to pad.
	   @param scale the length to pad to.
	 **/
    public static String padTrailingZeros(String data, int scale) 
    {
    	// sanity check scale.
    	if (scale <= 0) 
    		return data;
    	
        int decimalIndex = data.indexOf(".");
        if (decimalIndex > -1) {
            scale = scale - (data.substring(decimalIndex+1)).length();
        }
        
        StringBuffer sb = new StringBuffer();
        
        sb.append( data );
        
        for (int i=0; i<scale; i++) {
            sb.append('0');
        }
		
		return sb.toString();
    }
    
    /**
     * Validate the given string to ensure that the length is correct.
     * 
     * @param fieldName     name of the field
     * @param length        length of the field
     * @param value         value to validate
     * @throws CopyObjectException
     */
    public void validateString(String fieldName, int length, String value)
        throws CopyObjectException
    {
        if (value != null
        &&  value.trim().length() > length)
            throw new CopyObjectException("String Length Validation of data for " + fieldName + " failed");
    }

	/**
	 * Validate the given character array to ensure that the length is correct.
	 * 
	 * @param fieldName
	 *        Name of the copy object property mapped to a copybook field name.
	 * @param length
	 *        Length of the field as declared in the copybook.
	 * @param value
	 *        Field value to validate.
	 * @exception CopyObjectException
	 */
	protected void validateCharArray(String fieldName, int length, char[] value)
		throws CopyObjectException
	{
		if (value != null 
		 && FormatHelper.trimCharArray(value).length > length)
			throw new CopyObjectException("Field Length Validation of data for " + fieldName + " failed");
	}

    /**
     * Validates that a field has a valid double within the precision limits specified.
     * 
     * @param fieldName the field to validate.
     * @param length the maximum length.
     * @param scale the maximum number of digits to the right of the decimal.
     * @param value The value to validate.
     */
    public void validateDouble(String fieldName, int length, int scale, Double value) 
        throws CopyObjectException
    {
    }

	/**
	 * If <code>value</code> is negative, then this method will verify that
	 * <code>fieldName</code> is a signed numeric field, and then invoke
	 * remaining validation logic in
	 * {@link #validateDouble(String, int, int, Double)}
	 * <p>
	 * If the field is unsigned, and negative <code>value</code> has been
	 * supplied by the caller, a
	 * <code>InvalidUnsignedNumericValueException</code> will be thrown.
	 * 
	 * @param fieldName
	 *        Name of the field to validate
	 * @param length
	 *        The maximum length of the field value
	 * @param scale
	 *        The maximum number of digits to the right of the decimal
	 * @param value
	 *        The value to validate
	 * @param cobolType
	 *        COBOL data type of the field's value
	 * @exception CopyObjectException
	 * 
	 * @see #validateDouble(String, int, int, Double)
	 */
	public void validateDouble(String fieldName, int length, int scale, Double value, int cobolType)
		throws CopyObjectException
	{
		if (value != null && value.doubleValue() < 0d)
			if (cobolType != Converter.SNUMERIC 
			 && cobolType != Converter.SBINARY 
			 && cobolType != Converter.SPACKED)
				throw new InvalidUnsignedNumericValueException("Validation of data for " 
						+ fieldName 
						+ " failed: Negative value not allowed");

		validateDouble(fieldName, length, scale, value);
	}

    /**
	 * Validates that a field has a valid value within the precision limits specified.
	 * 
	 * @param fieldName the field to validate.
	 * @param length the maximum length.
	 * @param scale the maximum number of digits to the right of the decimal.  
	 *        Should always be zero. here for consistency with the validateDouble method.
	 *        @param value The value to validate.
	 */
    public void validateLong(String fieldName, int length, int scale, Long value) 
        throws CopyObjectException
    {
    }

	/**
	 * If <code>value</code> is negative, then this method will verify that
	 * <code>fieldName</code> is a signed numeric field, and then invoke
	 * remaining validation logic in
	 * {@link #validateLong(String, int, int, Long)}
	 * <p>
	 * If the field is unsigned, and negative <code>value</code> has been
	 * supplied by the caller, a
	 * <code>InvalidUnsignedNumericValueException</code> will be thrown.
	 * 
	 * @param fieldName
	 *        Name of the field to validate
	 * @param length
	 *        The maximum length of the field value
	 * @param scale
	 *        The maximum number of digits to the right of the decimal
	 * @param value
	 *        The value to validate
	 * @param cobolType
	 *        COBOL data type of the field's value
	 * @exception CopyObjectException
	 * 
	 * @see #validateLong(String, int, int, Long)
	 */
    public void validateLong(String fieldName, int length, int scale, Long value, int cobolType)
		throws CopyObjectException
	{
		if (value != null && value.longValue() < 0L)
			if (cobolType != Converter.SNUMERIC 
			 && cobolType != Converter.SBINARY 
			 && cobolType != Converter.SPACKED)
				throw new InvalidUnsignedNumericValueException("Validation of data for " 
						+ fieldName 
						+ " failed: Negative value not allowed");

		validateLong(fieldName, length, scale, value);
	}

	/**
	 * Validates that a field has a valid date.
	 * @param fieldName the field to validate.
	 * @param length the maximum length.
	 * @param value The value to validate.
	 */
    public void validateDate(String fieldName, int length, Date value)
    	throws CopyObjectException
    {
	}
    
	protected String getDateString(Date date)
	{
		if (date == null)
			return "";
		else
			return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}

    /**
     * Get the default String value from allowable values.
     * 
     * @param attributeName the name of the field
     * @param objClass      the class/interface the field belongs to
     * @return              the default value
     */
    @SuppressWarnings("rawtypes")
	protected String getDefaultString(String attributeName, Class objClass)
	{
        return AllowableValuesHelper.getDefaultValueString(rulesManager, userContext, objClass, attributeName);
    }
    
    /**
     * Get the default Long value from allowable values.
     * 
     * @param attributeName the name of the field
     * @param objClass      the class/interface the field belongs to
     * @return              the default value
     */
    @SuppressWarnings("rawtypes")
	protected Long getDefaultLong(String attributeName, Class objClass) 
    {
        return AllowableValuesHelper.getDefaultValueLong(rulesManager, userContext, objClass, attributeName);
    }
    
    /**
     * Convert the data to the host system format.
     * 
     * @param datatype  the datatype of the field
     * @param length    the length of the field
     * @param scale     the scale of the field
     * @param data      the data to convert
     * @return          the data converted to the host system format
     */
    protected byte[] convertToHost(int datatype, int length, int scale, String data) 
    {
        if (converter == null)
            return data.getBytes();
        
        return converter.convertToHost(datatype, length, scale, data);
    }
    
	/**
	 * Converts provided <code>data</code> data to a byte array in the host
	 * system format.
	 * 
	 * @param datatype
	 *        Data type of the <code>data</code>.
	 * @param length
	 *        The target length of the field.
	 * @param data
	 *        The data to convert to the host format.
	 * @return byte[] The data converted to the host system format.
	 */
	protected byte[] convertToHost(int datatype, int length, char[] data)
	{
		if (converter == null) {
			// Convert character array to a byte array, using default
			// character set. The translation to bytes is equivalent to
			// that, which occurs in the in the method getBytes() of
			// java.lang.String
			CharBuffer cBuf = CharBuffer.wrap(data);
			ByteBuffer bBuf = Charset.defaultCharset().encode(cBuf);
			return bBuf.array();
		}

		return converter.convertToHost(datatype, length, data);
	}

    /**
     * Convert the data from the host system format.
     * 
     * @param datatype  the datatype of the field
     * @param length    the length of the field
     * @param scale     the scale of the field
     * @param data      the data to convert
     * @return          the data converted from the host system
     */
    protected String convertFromHost(int datatype, int length, int scale, byte[] data)
    {
        if (converter == null)
            return new String(data);
        
        return converter.convertFromHost(datatype, length, scale, data);
    }
    
    /**
     * Retrieve the bytes located between the given start and end
     * indexes in the copy object's byte array.
     * 
     * @param start the starting index of the data in the byte array
     * @param end   the ending index of the data in the byte array
     * @return      a new byte array
     */
    protected byte[] getBytes(int start, int end)
    {
    	// Sanity Check
    	if (end < 0) return null;
    	if (start < 0) return null;
    	if (end < start) return null;
    	
        int length = end - start;
        byte[] b = new byte[length];        
        System.arraycopy(bytes, start, b, 0, length);
        
        return b;
    }
    
    /**
     * Replace the bytes located between the given start and end
     * indexes in the copy object's byte array.
     * 
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param valBytes  the bytes to replace
     */
    protected void replaceBytes(int start, int end, byte[] valBytes)
    {
        int length = end - start;
        System.arraycopy(valBytes, 0, bytes, start, length);
    }
       
    /**
     * Build and return a String based on the data in the byte array located
     * between the given start and end indexes.
     * 
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param type		the converter type
     * @return a String object
     */
    protected String getString(int start, int end, int type)
    {
    	int length = end - start;
    	return convertFromHost(type, length, 0, getBytes(start, end)).trim();
    }
    
    /**
     * Replace the bytes in the byte array using the given String value.
     * 
     * @param name      the name of the String field
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param value     the String value
     * @param type      the converter type
     */
    protected void setString(String name, int start, int end, String value, int type)
		throws CopyObjectException
	{
    	int length = end - start;
    	validateString(name, length, value);
    	replaceBytes(start, end, convertToHost(type, length, 0, value));
	}
    
    /**
     * Build and return a Date based on the data in the byte array located
     * between the given start and end indexes.
     * 
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @return a Date object
     */
    protected Date getDate(int start, int end)
    {
        Date date = null;
        String date8 = convertFromHost(Converter.ALPHANUM, 8, 0, getBytes(start, end));
        if (date8.equals("00000000"))
            return date;
            
        try {
            date = DateHelper.parseDate9s(date8, "yyyyMMdd");
        } catch(Exception e) {
            log.error(e);
        }
        return date;
    }
    
    /**
     * Replace the bytes in the byte array using the given Date value.
     * 
     * @param name      the name of the date field
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param value     the Date value
     */
    protected void setDate(String name, int start, int end, Date value) 
        throws CopyObjectException
    {
        String date8String = "00000000";
        if (value != null) {
            validateDate(name, 8, value);
            date8String = DateHelper.toFormattedNumericString9s(value);
        }
        replaceBytes(start, end, convertToHost(Converter.ALPHANUM, 8, 0, date8String));
    }
    
    /**
     * Build and return a Long based on the data in the byte array located
     * between the given start and end indexes.
     * 
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param type		the converter type
     * @return a Long object
     */
    protected Long getLong(int start, int end, int type)
    {
    	int length = end - start;
    	return Long.valueOf(convertFromHost(type, length, 0, getBytes(start, end)));
    }
    
    /**
     * Replace the bytes in the byte array using the given Long value.
     * 
     * @param name      the name of the Long field
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param scale     the scale of the given value (most likely 0)
     * @param value     the Long value
     * @param type      the converter type
     */
    protected void setLong(String name, int start, int end, int scale, Long value, int type)
		throws CopyObjectException
	{
		int length = end - start;
		if (value == null)
	        value = Long.valueOf(0);
	    else
	        validateLong(name, length, scale, value, type);
	    replaceBytes(start, end, convertToHost(type, length, scale, value.toString()));
	}
    
    /**
     * Build and return a Double based on the data in the byte array located
     * between the given start and end indexes.
     * 
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param scale     the scale of the Double
     * @param type		the converter type
     * @return a Double object
     */
    protected Double getDouble(int start, int end, int scale, int type)
    {
    	int length = end - start;
    	return new Double(convertFromHost(type, length, scale, getBytes(start, end)));
    }
    
    /**
     * Replace the bytes in the byte array using the given Double value.
     * 
     * @param name      the name of the Double field
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param scale     the scale of the given value
     * @param value     the Double value
     * @param type      the converter type
     */
    protected void setDouble(String name, int start, int end, int scale, Double value, int type)
		throws CopyObjectException
	{
		int length = end - start;
		validateDouble(name, length, scale, value, type);
	    replaceBytes(start, end, convertToHost(type, length, scale, NumberHelper.formatNumberString(value, scale)));
	}
    
    /**
     * Replace the bytes in the byte array using the given boolean value.
     * 
     * @param name       the name of the boolean field
     * @param start      the starting index of the data in the byte array
     * @param end        the ending index of the data in the byte array
     * @param scale      the scale of the given value
     * @param value      the boolean value
     * @param trueValue  the String value that represents true.
     * @param falseValue the String value that represents false.
     * @param type       the converter type
     */
    protected void setBoolean(String name, int start, int end, int scale, boolean value, String trueValue, String falseValue, int type)
    	throws CopyObjectException
    {
    	int length = end - start;
    	if (value)
            replaceBytes(start, end, convertToHost(type, length, scale, trueValue));
        else
            replaceBytes(start, end, convertToHost(type, length, scale, falseValue));
    }
    
    /**
     * Build and return a boolean based on the data in the byte array located
     * between the given start and end indexes.
     * 
     * @param start     the starting index of the data in the byte array
     * @param end       the ending index of the data in the byte array
     * @param scale     the scale (should be zero)
     * @param trueValue the String value that represents true
     * @param type      the converter type
     * @return a boolean
     */
    protected boolean getBoolean(int start, int end, int scale, String trueValue, int type)
    {
    	int length = end - start;
        if (convertFromHost(type, length, scale, getBytes(start, end)).equals(trueValue))
            return true;
        return false;
    }
    
    /**
     * Add a detail item for the given field to the detail list.
     * 
     * @param prefix     value to prefix to the field name
     * @param fieldName  name of the field to create detail for
     * @param detailList list of detail items for the current object
     */
    protected void addDetail(String prefix, String fieldName, List<HistoryDetailItem> detailList)
    {
        try {
            Object value = ReflectionUtils.getAttributeValue(this, fieldName);
            detailList.add(new HistoryDetailItem(prefix + fieldName, value));
        } catch (Exception e) {
            log.error("History Detail: Error getting value for field " + fieldName);
            e.printStackTrace();
            // Ignore exception b/c we expect some fields to be invalid.
        }
    }
}