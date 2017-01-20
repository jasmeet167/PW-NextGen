package com.csc.fsg.life.security.authorization.bo;

import com.csc.fsg.life.biz.bo.BaseBusinessObject;
import com.csc.fsg.life.biz.bo.BusinessObjectException;
import com.csc.fsg.life.context.UserContext;

/* Modifications: ENH1220 */

/**
 * Data transfer class that represents data used to load Authority cache
 */
public class AuthorityCacheModelImpl
	extends BaseBusinessObject
	implements AuthorityCacheModel
{
	private static final long serialVersionUID = 7783623779633771586L;

	private Long id;
	private String resourceType;
	private String environment;
	private String companyCode;
	private String useCaseId;
	private String fullBoName;
	private String buttonName;
	private String maskClass;
	private String tableId;
	private String className;
	private String methodSignature;
	private String specialOperationType;

	/**
	 * Default constructor
	 */
	public AuthorityCacheModelImpl()
	{
		super();
	}

	/**
	 * Creates a new AuthorityModelImpl object as a clone of the specified
	 * model.
	 * 
	 * @param model
	 *        the model object to copy.
	 */
	public AuthorityCacheModelImpl(AuthorityCacheModel model)
	{
		id = model.getId();
		resourceType = model.getResourceType();
		environment = model.getEnvironment();
		companyCode = model.getCompanyCode();
		useCaseId = model.getUseCaseId();
		fullBoName = model.getFullBoName();
		buttonName = model.getButtonName();
		tableId = model.getTableId();
		className = model.getClassName();
		methodSignature = model.getMethodSignature();
		specialOperationType = model.getSpecialOperationType();
		maskClass = model.getMaskClass();
	}

	/**
	 * Creates this object and initializes all fields and sub-objects with
	 * default values. In addition, initializes the user context.
	 * 
	 * @param userContext
	 *        the user context.
	 * @throws BusinessObjectException
	 *         If there is an initialization problem.
	 */
	@Override
	public void init(UserContext userContext)
		throws BusinessObjectException
	{
		if (isInitialized())
			return;

		setUserContext(userContext);

		// Initialize all fields to their default values from AVM.
	}

	/**
	 * Sets a new value to the id property.
	 * 
	 * @param value
	 *        New value for id property.
	 * @see #getId
	 */
	public void setId(Long value)
	{
		this.id = value;
	}

	/**
	 * Returns the current value of the id property.
	 * 
	 * @return The value of the id property.
	 * @see #setId
	 */
	public Long getId()
	{
		return this.id;
	}

	/**
	 * Sets a new value to the resourceType property.
	 * 
	 * @param value
	 *        New value for resourceType property.
	 * @see #getResourceType
	 */
	public void setResourceType(String value)
	{
		this.resourceType = value;
	}

	/**
	 * Returns the current value of the resourceType property.
	 * 
	 * @return The value of the resourceType property.
	 * @see #setResourceType
	 */
	public String getResourceType()
	{
		return this.resourceType;
	}

	/**
	 * Sets a new value to the environment property.
	 * 
	 * @param value
	 *        New value for environment property.
	 * @see #getEnvironment
	 */
	public void setEnvironment(String value)
	{
		this.environment = value;
	}

	/**
	 * Returns the current value of the environment property.
	 * 
	 * @return The value of the environment property.
	 * @see #setEnvironment
	 */
	public String getEnvironment()
	{
		return this.environment;
	}

	/**
	 * Sets a new value to the companyCode property.
	 * 
	 * @param value
	 *        New value for companyCode property.
	 * @see #getCompanyCode
	 */
	public void setCompanyCode(String value)
	{
		this.companyCode = value;
	}

	/**
	 * Returns the current value of the companyCode property.
	 * 
	 * @return The value of the companyCode property.
	 * @see #setCompanyCode
	 */
	public String getCompanyCode()
	{
		return this.companyCode;
	}

	/**
	 * Sets a new value to the useCaseId property.
	 * 
	 * @param value
	 *        New value for useCaseId property.
	 * @see #getUseCaseId
	 */
	public void setUseCaseId(String value)
	{
		this.useCaseId = value;

	}

	/**
	 * Returns the current value of the useCaseId property.
	 * 
	 * @return The value of the useCaseId property.
	 * @see #setUseCaseId
	 */
	public String getUseCaseId()
	{
		return useCaseId;
	}

	/**
	 * Sets a new value to the fullBoName property.
	 * 
	 * @param value
	 *        New value for fullBoName property.
	 * @see #getFullBoName
	 */
	public void setFullBoName(String value)
	{
		this.fullBoName = value;
	}

	/**
	 * Returns the current value of the fullBoName property.
	 * 
	 * @return The value of the fullBoName property.
	 * @see #setFullBoName
	 */
	public String getFullBoName()
	{
		return fullBoName;
	}

	/**
	 * Sets a new value to the buttonName property.
	 * 
	 * @param value
	 *        New value for buttonName property.
	 * @see #getButtonName
	 */
	public void setButtonName(String value)
	{
		this.buttonName = value;
	}

	/**
	 * Returns the current value of the buttonName property.
	 * 
	 * @return The value of the buttonName property.
	 * @see #setButtonName
	 */
	public String getButtonName()
	{
		return buttonName;
	}

	/**
	 * Sets a new value to the maskClass property.
	 * 
	 * @param value
	 *        New value for maskClass property.
	 * @see #getMaskClass()
	 */
	public void setMaskClass(String value)
	{
		this.maskClass = value;
	}

	/**
	 * Returns the current value of the maskClass property.
	 * 
	 * @return The value of the maskClass property.
	 * @see #setMaskClass(String)
	 */
	public String getMaskClass()
	{
		return maskClass;
	}

	/**
	 * Sets a new value to the tableId property.
	 * 
	 * @param value
	 *        New value for tableId property.
	 * @see #getTableId
	 */
	public void setTableId(String value)
	{
		this.tableId = value;
	}

	/**
	 * Returns the current value of the tableId property.
	 * 
	 * @return The value of the tableId property.
	 * @see #setTableId
	 */
	public String getTableId()
	{
		return tableId;
	}

	/**
	 * Sets a new value to the className property.
	 * 
	 * @param value
	 *        New value for className property.
	 * @see #getClassName
	 */
	public void setClassName(String value)
	{
		this.className = value;
	}

	/**
	 * Returns the current value of the className property.
	 * 
	 * @return The value of the className property.
	 * @see #setClassName
	 */
	public String getClassName()
	{
		return className;
	}

	/**
	 * Sets a new value to the methodSignature property.
	 * 
	 * @param value
	 *        New value for methodSignature property.
	 * @see #getMethodSignature
	 */
	public void setMethodSignature(String value)
	{
		this.methodSignature = value;
	}

	/**
	 * Returns the current value of the methodSignature property.
	 * 
	 * @return The value of the methodSignature property.
	 * @see #setMethodSignature
	 */
	public String getMethodSignature()
	{
		return methodSignature;
	}

	/**
	 * Sets a new value to the specialOperationType property.
	 * 
	 * @param value
	 *        New value for specialOperationType property.
	 * @see #getSpecialOperationType()
	 */
	public void setSpecialOperationType(String value)
	{
		this.specialOperationType = value;
	}

	/**
	 * Sets a new value to the specialOperationType property.
	 * 
	 * @param value
	 *        New value for specialOperationType property.
	 * @see #setSpecialOperationType(String)
	 */
	public String getSpecialOperationType()
	{
		return specialOperationType;
	}

	/**
	 * Dumps model class to a string.
	 */
	@Override
	public String toString()
	{
		StringBuilder buf = new StringBuilder();
		buf.append("\nid: ");
		buf.append(id);
		buf.append("\nresourceType: ");
		buf.append(resourceType);
		buf.append("\nenvironment: ");
		buf.append(environment);
		buf.append("\ncompanyCode: ");
		buf.append(companyCode);
		buf.append("\npageId: ");
		buf.append(useCaseId);
		buf.append("\nfullBoName: ");
		buf.append(fullBoName);
		buf.append("\nbuttonName: ");
		buf.append(buttonName);
		buf.append("\nmaskClass: ");
		buf.append(maskClass);
		buf.append("\ntableId: ");
		buf.append(tableId);
		buf.append("\nclassName: ");
		buf.append(className);
		buf.append("\nmethodSignature: ");
		buf.append(methodSignature);
		buf.append("\nspecialOperationType: ");
		buf.append(specialOperationType);
		return buf.toString();
	}
}
