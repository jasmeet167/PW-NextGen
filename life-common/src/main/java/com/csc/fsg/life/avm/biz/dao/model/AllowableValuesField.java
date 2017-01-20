package com.csc.fsg.life.avm.biz.dao.model;

import com.csc.fsg.life.dao.model.DAOModel;

public class AllowableValuesField extends DAOModel {

	private String fieldName;
	private String fieldDesc;

    /**
	 * Returns the model name.
	 * 
	 * @return the model name.
	 **/
	public String getModelName() {
		return "com.csc.fsg.life.avm.biz.dao.model.AllowableValuesField";
	}
	
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldDesc(String fieldDesc) {
		this.fieldDesc = fieldDesc;
	}

	public String getFieldDesc() {
		return fieldDesc;
	} 
}