package com.csc.fsg.life.security.authorization.bo;

import com.csc.fsg.life.biz.meta.MetaData;
import com.csc.fsg.life.context.UserContextAware;

/* Modifications: ENH1220 */

/**
 * Data transfer interface that represents data used to load Authority cache
 */
public interface AuthorityCacheModel
	extends UserContextAware
{
	/**
	 * Sets a new value to the id property.
	 * 
	 * @param value
	 *        New value for id property.
	 * @see #getId()
	 */
	public void setId(Long value);

	/**
	 * Returns the current value of the id property.
	 * 
	 * @return The value of the id property.
	 * @see #setId(Long)
	 */
	@MetaData(size = 20)
	public Long getId();

	/**
	 * Sets a new value to the resourceType property.
	 * 
	 * @param value
	 *        New value for resourceType property.
	 * @see #getResourceType()
	 */
	public void setResourceType(String value);

	/**
	 * Returns the current value of the resourceType property.
	 * 
	 * @return The value of the resourceType property.
	 * @see #setResourceType(String)
	 */
	@MetaData(size = 1)
	public String getResourceType();

	/**
	 * Sets a new value to the environment property.
	 * 
	 * @param value
	 *        New value for environment property.
	 * @see #getEnvironment()
	 */
	public void setEnvironment(String value);

	/**
	 * Returns the current value of the environment property.
	 * 
	 * @return The value of the environment property.
	 * @see #setEnvironment(String)
	 */
	@MetaData(size = 33)
	public String getEnvironment();

	/**
	 * Sets a new value to the companyCode property.
	 * 
	 * @param value
	 *        New value for companyCode property.
	 * @see #getCompanyCode()
	 */
	public void setCompanyCode(String value);

	/**
	 * Returns the current value of the companyCode property.
	 * 
	 * @return The value of the companyCode property.
	 * @see #setCompanyCode(String)
	 */
	@MetaData(size = 3)
	public String getCompanyCode();

	/**
	 * Sets a new value to the useCaseId property.
	 * 
	 * @param value
	 *        New value for useCaseId property.
	 * @see #getUseCaseId()
	 */
	public void setUseCaseId(String value);

	/**
	 * Returns the current value of the useCaseId property.
	 * 
	 * @return The value of the useCaseId property.
	 * @see #setUseCaseId(String)
	 */
	@MetaData(size = 200)
	public String getUseCaseId();

	/**
	 * Sets a new value to the fullBoName property.
	 * 
	 * @param value
	 *        New value for fullBoName property.
	 * @see #getFullBoName()
	 */
	public void setFullBoName(String value);

	/**
	 * Returns the current value of the fullBoName property.
	 * 
	 * @return The value of the fullBoName property.
	 * @see #setFullBoName(String)
	 */
	@MetaData(size = 300)
	public String getFullBoName();

	/**
	 * Sets a new value to the buttonName property.
	 * 
	 * @param value
	 *        New value for buttonName property.
	 * @see #getButtonName()
	 */
	public void setButtonName(String value);

	/**
	 * Returns the current value of the buttonName property.
	 * 
	 * @return The value of the buttonName property.
	 * @see #setButtonName(String)
	 */
	@MetaData(size = 200)
	public String getButtonName();

	/**
	 * Sets a new value to the maskClass property.
	 * 
	 * @param value
	 *        New value for maskClass property.
	 * @see #getMaskClass()
	 */
	public void setMaskClass(String value);

	/**
	 * Returns the current value of the maskClass property.
	 * 
	 * @return The value of the maskClass property.
	 * @see #setMaskClass(String)
	 */
	public String getMaskClass();

	/**
	 * Sets a new value to the tableId property.
	 * 
	 * @param value
	 *        New value for tableId property.
	 * @see #getTableId()
	 */
	public void setTableId(String value);

	/**
	 * Returns the current value of the tableId property.
	 * 
	 * @return The value of the tableId property.
	 * @see #setTableId(String)
	 */
	@MetaData(size = 25)
	public String getTableId();

	/**
	 * Sets a new value to the className property.
	 * 
	 * @param value
	 *        New value for className property.
	 * @see #getClassName()
	 */
	public void setClassName(String value);

	/**
	 * Returns the current value of the className property.
	 * 
	 * @return The value of the className property.
	 * @see #setClassName(String)
	 */
	@MetaData(size = 200)
	public String getClassName();

	/**
	 * Sets a new value to the methodSignature property.
	 * 
	 * @param value
	 *        New value for methodSignature property.
	 * @see #getMethodSignature()
	 */
	public void setMethodSignature(String value);

	/**
	 * Returns the current value of the methodSignature property.
	 * 
	 * @return The value of the methodSignature property.
	 * @see #setMethodSignature(String)
	 */
	@MetaData(size = 500)
	public String getMethodSignature();

	/**
	 * Sets a new value to the specialOpType property.
	 * 
	 * @param value
	 *        New value for specialOpType property.
	 * @see #getSpecialOperationType()
	 */
	public void setSpecialOperationType(String value);

	/**
	 * Sets a new value to the specialOpType property.
	 * 
	 * @param value
	 *        New value for specialOpType property.
	 * @see #setSpecialOperationType(String)
	 */
	@MetaData(size = 1)
	public String getSpecialOperationType();
}
