package com.csc.fsg.life.biz.service;

import java.security.GeneralSecurityException;

import org.w3c.dom.Document;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.biz.exception.WebServiceException;
import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.security.EmptyLoginListener;
import com.csc.fsg.life.security.SecurityException;

/* Modifications: T0175, ENH1220 */

/**
   This class is used as the base class for all ObjectServices that 
   utilize the service manager framework.  This is not only those 
   that use the ServiceManager but all that use the ServiceParam and UserContext
   objects to hold state.
**/
abstract public class ServiceManagerObjectService 
	implements ObjectService
{

	/** The user context this service is executing under. **/
    private UserContext userContext;


	/**
	   Allows the service to perform a login operation.  
	   The environment to login to is passed in.  A context
	   object may be returned.  This context object will be passed to 
	   the other methods in this interface when called.
	   @param env The environment to login to.
	   @param userId The user to login.
	   @param password The <pre>user's</pre> password.
	   @return The context object for your service or null if not required.
	**/
    public Object logon(ApplicationEnvironmentBean env, String userId, char[] password) 
		throws BusinessException
	{
		// Build the user context.
		UserContext userContext = createUserContext(env);
        userContext.setApplicationEnvironmentBean(env);

		//Check LDAP
		try 
		{
			userContext.login(userId, password, new EmptyLoginListener());
		} 
		catch(GeneralSecurityException e)
		{
			throw new SecurityException(e);
		}

		// Create service parameter.
        ServiceParam param = new ServiceParam();
        param.setUserContext(userContext);

		// Return the ServiceParam as the context object.
        return param;
	}

	/**
	   Implement this method to create the application specific user context.
	   @param env The environment to login to.
	**/
	public UserContext createUserContext(ApplicationEnvironmentBean env)
	{
		return getUserContext();
	}


	/**
	   Allows the service to logoff.
	   @param context The context object returned from the logon method.
	**/
    public void logoff(Object context)
	{
		if (userContext != null)
			userContext.logout();
	}

    /**
       This method is called to perform the requested operation from the
       web services framework.  
	   @param context The context object returned from the logon method.
    **/
    public Object service(Object context)
        throws BusinessException
	{
		ServiceParam serviceParam = (ServiceParam)context;

		try
		{
			Object retObj = internalService(serviceParam);

			// Return the object to be mapped via the response map.
			return retObj;
		} 
		catch (ServiceManagerException e) 
		{
			throw new WebServiceException(SYSTEM_NOT_AVAILABLE, e);
		}
    }

	/**
	   The actual service implementation code goes in this method in the 
	   derived class.
	   @param serviceParam The service parameter state object for this service execution.
	**/
    abstract public Object internalService(ServiceParam serviceParam) 
		throws BusinessException;


    /**
       This method is called to perform any necessary precursor operations in the
       web services framework.  The service provider will determine what, if any,
       action is required.  
	   @param context The context object returned from the logon method.
       @param doc The xml service request.  This is typically used to get data 
	   necessary to perform the pre-service.
    **/
    public Object preService(Object context, Document doc)
        throws BusinessException
	{
        return null;
    }


	/**
	   Returns the user context.
	   @return the user context.
	   @see #setUserContext
	**/
    public UserContext getUserContext()
    {
        return userContext;
    }
    
	/**
	   Sets the UserContext.
	   @param userContext The new UserContext value.
	   @see #getUserContext
	 **/
    public void setUserContext(UserContext userContext)
    {
        this.userContext = userContext;
    }
    
	
}
