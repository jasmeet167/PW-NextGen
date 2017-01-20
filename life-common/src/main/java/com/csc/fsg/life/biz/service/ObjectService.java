package com.csc.fsg.life.biz.service;

import org.w3c.dom.Document;

import com.csc.fsg.life.biz.exception.BusinessException;
import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;

/* Modifications: T0175 */

/**
 * Implement this interface to have your object service be used by the
 * web-services framework. The methods in this service interface are called in
 * the following order.
 *
 * <pre>
 * Object context = service.logon();
 * try {
 * 	service = service.preServie(context, document);
 * 	service.service(context);
 * } finally {
 * 	service.logoff(context);
 * }
 * </pre>
 */
public interface ObjectService {

	/**
	 * Constant for the map to use when the services system is not available for
	 * infrastructure reasons. Throw a WebServiceException with this as the map
	 * name.
	 */
	public static final String SYSTEM_NOT_AVAILABLE = "SystemNotAvailableError";

	/**
	 * Constant for the map to use when some required data is missing from the
	 * web service request. Throw a WebServiceException with this as the map
	 * name.
	 */
	public static final String REQUIRED_DATA_MISSING = "MissingElementError";

	/**
	 * Response map to use when back end returns an error.
	 */
	public static final String ERROR_RESPONSE = "ErrorResponse";
	/**
	 * Response map to use when vantage back end returns an error.
	 */
	public static final String VANTAGE_ERROR_RESPONSE = "VantageErrorResponse";
	/**
	 * Response map to use when no records found for the transaction using JDBC.
	 */
	public static final String OBJECT_NOT_FOUND_ERROR = "ObjectNotFoundError";
	/**
	 * Response map to use when back end returns an error and error copy object needs
	 * to be used instead of error copy book.
	 */
	public static final String COPYOBJECT_ERROR_RESPONSE = "CopyObjectErrorResponse";
	/**
	 * Default Response map to use when back end returns a success (when no
	 * response map is set).
	 */
	public static final String ACCORD_SUCCESS = "AccordSuccess";
	/**
	 * Allows the service to perform a login operation. The environment to login
	 * to is passed in. A context object may be returned. This context object
	 * will be passed to the other methods in this interface when called.
	 *
	 * @param env The environment to login to.
	 * @param userId The user to login.
	 * @param password The user's password.
	 *
	 * @return The context object for your service or null if not required.
	 *
	 * @throws BusinessException the business exception
	 */
	public Object logon(ApplicationEnvironmentBean env, String userId,
			char[] password) throws BusinessException;

	/**
	 * Allows the service to logoff.
	 *
	 * @param context The context object returned from the logon method.
	 *
	 * @throws BusinessException the business exception
	 */
	public void logoff(Object context) throws BusinessException;

	/**
	 * This method is called to perform the requested operation from the web
	 * services framework.
	 *
	 * @param context The context object returned from the logon method.
	 *
	 * @return the object
	 *
	 * @throws BusinessException the business exception
	 */
	public Object service(Object context) throws BusinessException;

	/**
	 * This method is called to perform any necessary precursor operations in
	 * the web services framework. The service provider will determine what, if
	 * any, action is required.
	 *
	 * @param context The context object returned from the logon method.
	 * @param doc The XML service request. This is typically used to get data
	 *            necessary to perform the pre-service.
	 *
	 * @return the object
	 *
	 * @throws BusinessException the business exception
	 */
	public Object preService(Object context, Document doc)
			throws BusinessException;

}
