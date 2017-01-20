package com.csc.fsg.life.avm.api;

import java.util.List;

import com.csc.fsg.life.avm.biz.dao.model.APIFieldLookUpModel;

/**
 * This object retains all allowable value information for a field.
 */
public class FieldAllowableValues 
    implements IFieldAllowableValues 
{
    /**
	 * Field Key the identifies field.
	 */
	private String fieldKey = "";
   
	/**
	 * Field Name that is used to identify field in application.
	 */
	private String fieldName = "";
   
	/**
	 * Identifies type of allowable values. Either L-List or R-Range.
	 */
	private String fieldType = "";
   
	/**
	 * Default value for the Field. If Field Type is List, this will be the core
	 * value. If Field Type is Range this will be the actual value to be used.
	 */
	private String fieldDefault = "";
   
	/**
	 * Allowable Values that have been defined for the Field.
	 */
	private AllowableValues allowableValues;
   
   
	/**
	 * Creates an allowable values object. Should only be created by the
	 * package. This class just builds the objects, the data is all provided by
	 * parameters so no DB access is needed.
	 * 
	 * @param accessKeys  The access keys used to get this fields data.
	 * @param apiFieldLookUpModel  model object with data.
	 * @param allowableValuesModelList  List object of the data for this field.
	 */
	@SuppressWarnings("unchecked")
	public FieldAllowableValues(AccessKeys          accessKeys,
								APIFieldLookUpModel apiFieldLookUpModel,
								List                allowableValuesModelList) 
	{
		fieldKey = apiFieldLookUpModel.getFieldKey();
		fieldName = accessKeys.getFieldName();
		fieldType = apiFieldLookUpModel.getFieldType();
		if (fieldType.equals("L")) {
			if (apiFieldLookUpModel.getListDefault() != null && apiFieldLookUpModel.getListDefault().equals(AVMManager.AVMSpaceValue))
				fieldDefault = " ";
			else
				fieldDefault = apiFieldLookUpModel.getListDefault();
			allowableValues = new ListAllowableValues(allowableValuesModelList);
		}
		else {
			fieldDefault = apiFieldLookUpModel.getRangeDefault().toString();
			allowableValues = new RangeAllowableValues(allowableValuesModelList);
		}
	}
   
	/**
	 * Access method for the fieldKey property.
	 * 
	 * @return the current value of the fieldKey property
	 */
	public String getFieldKey() 
	{
		return fieldKey;    
	}
	
	/**
	 * Access method for the fieldName property.
	 * 
	 * @return the current value of the fieldName property
	 */
	public String getFieldName() 
	{
      return fieldName;    
	}
   
	/**
	 * Access method for the fieldType property.
	 * 
	 * @return the current value of the fieldType property
	 */
	public String getFieldType() 
	{
		return fieldType;    
	}
   
	/**
	 * Access method for the fieldDefault property.
	 * 
	 * @return the current value of the fieldDefault property
	 */
	public String getFieldDefault() 
	{
		return fieldDefault;    
	}
   
	/**
	 * Access method for the AllowableValues property.
	 * 
	 * @return the current value of the AllowableValues property
	 */
	public AllowableValues getAllowableValues() 
	{
		return allowableValues;    
	}
}