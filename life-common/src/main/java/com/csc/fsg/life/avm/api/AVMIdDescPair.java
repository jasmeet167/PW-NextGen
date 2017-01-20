package com.csc.fsg.life.avm.api;

/**
   A tuple of an ID and a description.
**/
public class AVMIdDescPair {
	private String idVal;
	private String description;

	/** Create a ID/Description tuple.
		@param idVal The ID for the pair.
		@param description The description for the pair.
	**/
	public AVMIdDescPair(String idVal, String description){
		if(idVal != null ){
			this.idVal = idVal.trim();
		}
		if(description != null){
			this.description = description.trim();
		}
	}
	
	/**
	   Returns the description of the pair.
	   @return the description of the pair.
	   @see #setDescription
	**/
	public String getDescription() {
		return description;
	}

	/**
	   Sets the description of the pair.
	   @param description the description of the pair.
	   @see #getDescription
	**/
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	   Returns the ID of the pair.
	   @return the ID of the pair.
	   @see #setIdVal
	**/
	public String getIdVal() {
		return idVal;
	}

	/**
	   Sets the ID of the pair.
	   @param idVal the ID of the pair.
	   @see #getIdVal
	**/
	public void setIdVal(String idVal) {
		this.idVal = idVal;
	}
	
	/**
	   Returns a debug version of the object.
	**/
	public String toString(){
		return idVal+"~"+description;
	}
}
