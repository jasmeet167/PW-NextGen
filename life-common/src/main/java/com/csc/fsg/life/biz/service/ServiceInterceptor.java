package com.csc.fsg.life.biz.service;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.csc.fsg.life.availability.AvailabilityManager;
import com.csc.fsg.life.biz.meta.MetaDataHelper;
import com.csc.fsg.life.biz.meta.ServiceMetaData;
import com.csc.fsg.life.common.config.MyBatisApplicationEnvironmentBean;
import com.csc.fsg.life.context.UserContext;

/* Modifications: ENHT0085, ENH988, T0135, T0150, T0153, ENH1220, T0199 */

/**
 * This class will intercept all method calls on all services.
 */
public class ServiceInterceptor 
	implements MethodInterceptor,Serializable 
{
	/** Logger for the SecurityInterceptor class. */
	protected static final Log log = LogFactory.getLog("com.csc.fsg");

	public ServiceInterceptor()
	{
		log.info("Initializing ServiceInterceptor...");
	}

	/**
	 * Called when a method on a BusinessService is invoked.  Performs the following: 
	 * 
	 * <ul>
     *   <li>Security check to verify that the user is authorized to perform the operation
     *   <li>Validation
     *   <li>Database transaction demarcation, if necessary
     * </ul>
	 */
	public Object invoke(MethodInvocation methodInvocation) 
	    throws Throwable
	{
	    Object result = null;
	       
		// get the details of this method invocation
		Method method = methodInvocation.getMethod();
		Class<?> clz = method.getDeclaringClass();

		// get ServiceParam from the method arguments
		Object[] parametersType = methodInvocation.getArguments();
		ServiceParam param = null;
		if (parametersType != null) {
			for (int i = 0; i < parametersType.length; i++) {
				Object argument = parametersType[i];
				if (argument instanceof ServiceParam) {
					param = (ServiceParam) argument;
					break;
				}
			}
		}

		// access the MetaData of the method that is being invoked
		ServiceMetaData methodMetaData = MetaDataHelper.getServiceMetaData(clz,	method);

		// perform check of required system availability
		checkAvailability(param, methodMetaData);

	    // perform any service validations, if necessary
	    validate(param, methodMetaData);
	    
		// wrap the invocation in a database transaction, if necessary
	    TransactionInfo transactionInfo = startTransaction(param, methodMetaData);

		try {
			result = methodInvocation.proceed();
		} catch (Exception e) {
			rollbackTransaction(transactionInfo);
			
			throw e;
		}

		commitTransaction(transactionInfo);
		
		return result;
	}

	/**
     * If the given method meta data specifies that a database transaction is necessary,
     * create a transaction.
     * @param param            service parameter
     * @param methodMetaData   meta data pertaining to the method being invoked
     * @return the TransactionInfo object containing the transaction information
     */
	private TransactionInfo startTransaction(ServiceParam param, ServiceMetaData methodMetaData)
	{
        if (!isTransactionMetaDataFound(methodMetaData))
            return null;
        
		if (!isTransactionApplicable(param))
			return null;

	    UserContext userContext = param.getUserContext();
        MyBatisApplicationEnvironmentBean appEnvBean = 
            (MyBatisApplicationEnvironmentBean) userContext.getApplicationEnvironment();

        DataSourceTransactionManager transactionManager = 
            new DataSourceTransactionManager(appEnvBean.getJndiDataSource());
        DefaultTransactionDefinition tranDefinition = new DefaultTransactionDefinition();
        tranDefinition.setIsolationLevel(methodMetaData.isolationLevel());
        
        TransactionStatus transactionStatus = transactionManager.getTransaction(tranDefinition);
        
        return new TransactionInfo(transactionManager, transactionStatus);
	}
	
	protected boolean isTransactionApplicable(ServiceParam param)
	{
		return true;
	}

	/**
     * @param metaData   Metadata pertaining to the method being invoked
     * @return A flag indicating whether any transaction-related directives 
     *         have been found in the given metaData.  
	 */
	private boolean isTransactionMetaDataFound(ServiceMetaData metaData)
	{
		if (metaData == null)
			return false;
		
		return (metaData.transaction() || metaData.isolationLevel() >= 0);
	}
	
	/**
     * Commit the database transaction based on the given transaction information.
     * @param transactionInfo information pertaining to the current transaction
     */
    private void commitTransaction(TransactionInfo transactionInfo)
    {       
        if (transactionInfo != null) {
            log.info("Committing transaction");
            DataSourceTransactionManager transactionManager = transactionInfo.getTransactionManager();
            transactionManager.commit(transactionInfo.getTransactionStatus());
        }
    }
    
	/**
     * Rollback the database transaction based on the given transaction information.
     * @param transactionInfo information pertaining to the current transaction
     */
	private void rollbackTransaction(TransactionInfo transactionInfo)
	{
	    if (transactionInfo != null) {
	        log.info("Rolling back transaction");
    	    DataSourceTransactionManager transactionManager = transactionInfo.getTransactionManager();
    	    transactionManager.rollback(transactionInfo.getTransactionStatus());
	    }
	}
	
	/**
	 * If the given method meta data specifies that validation is necessary,
	 * invoke the validator contained on the service.
	 * @param param            service parameter
	 * @param methodMetaData   meta data pertaining to the method being invoked
	 * @throws ServiceValidationException
	 */
    private void validate(ServiceParam param, ServiceMetaData methodMetaData)
        throws ServiceValidationException
    {       
        // if validate, invoke the validator
        if (methodMetaData != null && methodMetaData.validate()) {
            Service service = (Service) AopContext.currentProxy();
            ServiceValidator validator = service.getValidator();
            validator.validate(param);
        }
    }

	/**
	 * If the supplied instance of <code>metaData</code> indicates that a
	 * specific level of availability is required, then verify that it is
	 * currently in effect. If no sufficient availability is detected, throw
	 * an exception.
	 * 
	 * @param param
	 *        Encapsulation of run-time parameters
	 * @param metaData
	 *        Metadata describing the method about to be invoked
	 * @exception BusinessServiceException
	 */
	private void checkAvailability(ServiceParam param, ServiceMetaData metaData)
		throws BusinessServiceException
	{
		if (metaData != null && param != null) {
			if (metaData.isUpdateAvailabilityRequired()) {
				UserContext userContext = param.getUserContext();
				AvailabilityManager availabilityManager = userContext.getAvailabilityManager();
				availabilityManager.checkDatabaseUpdateAvailability(userContext);
			}
		}
	}

    /**
     * Container class for transaction related information.
     */
    protected class TransactionInfo 
    {    
        private TransactionStatus transactionStatus = null;
        private DataSourceTransactionManager transactionManager = null;
        
        public TransactionInfo(DataSourceTransactionManager transactionManager, 
                               TransactionStatus transactionStatus)
        {
            this.transactionManager = transactionManager;
            this.transactionStatus = transactionStatus;
        }

        public TransactionStatus getTransactionStatus()
        {
            return transactionStatus;
        }

        public DataSourceTransactionManager getTransactionManager()
        {
            return transactionManager;
        }        
    }
}
