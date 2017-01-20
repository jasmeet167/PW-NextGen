package com.csc.fsg.life.biz.bo;

import java.io.Serializable;

import com.csc.fsg.life.avm.AllowableValuesHelper;
import com.csc.fsg.life.avm.RulesManagerAvmImpl;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.rules.IRulesManager;
import com.csc.fsg.life.tools.xml.simple.SimpleXMLEncoder;

/**
 * Base class for all simple business objects.
 */
public class BaseBusinessObject implements Serializable
{
	/** User context for business object. */
    private UserContext userContext;

    /** Indicates whether this object was already initialized. */
    private boolean initialized = false;
    
    /** Rules Manager for accessing AVM. */
	private IRulesManager rulesManager;
	
    /** Holds the initial state of the object in xml form */
    private String initialState = null;

	/**
	   Create a new BaseBusinessObject instance.
	**/
	public BaseBusinessObject()
    {
		rulesManager = RulesManagerAvmImpl.getInstance();
    }
    
	/**
	   Initializes the business object.
	   @param userContext the user context object.
	   @throws BusinessObjectException Thrown for an initialization error.
	**/
    public void init(UserContext userContext) throws BusinessObjectException
    {
        setUserContext(userContext);
    }

	/**
	   Initializes the business object.  Additional parameter used to force re-initalization.
	   @param userContext the user context object.
	   @param isForced pass true to force re-initalization.
	   @throws BusinessObjectException Thrown for an initialization error.
	**/
    public void init(UserContext userContext, boolean isForced) 
    	throws BusinessObjectException
    {
		if (isForced)
			initialized = false;

    	init(userContext);
    }


	/** 
		Get the User state information object. 
		@return the User state information object. 
	**/
	public UserContext getUserContext() 
    { 
        return userContext; 
    }
	
	/** 
		Set the User state information object. 
		@param c The user state information object. 
	**/
	public void setUserContext(UserContext c) 
    { 
        userContext = c;
    }

	/**
	   Returns true if this object has been initialized.
	   @return true if this object has been initialized.
	**/
	public boolean isInitialized() 
	{
		return initialized;
	}
    

    /**
     * Get the default String value from allowable values.
     * 
     * @param attributeName the name of the field
     * @param objClass      the class/interface the field belongs to
     * @return              the default value
     */
    @SuppressWarnings("unchecked")
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
    @SuppressWarnings("unchecked")
	protected Long getDefaultLong(String attributeName, Class objClass) 
    {
        return AllowableValuesHelper.getDefaultValueLong(rulesManager, userContext, objClass, attributeName);
    }

    public void setInitialState(String initialState)
    {
        this.initialState = initialState;
    }
      
    /**
     * If this object has an initialState, then it was retrieved from
     * a datasource, as opposed to instantiated as a new object.
     */
    public boolean isReadFromDB()
    {
        return initialState != null;
    }
    
    /**
     * Returns true if the state of the object has been changed since
     * its original state was created.
     */
    public boolean hasChanged()
    {
        if (initialState == null)
            return false;
        
        // serialize current state
        SimpleXMLEncoder encoder = new SimpleXMLEncoder();
        String currentState = encoder.buildXML(this);
        return !initialState.equals(currentState);
    }
}