package com.csc.fsg.life.tools;

public class FieldMetaData {
    
	/** The datatype for this field (ie. text, currency, percent, etc) */
	private String datatype = null;
	
    /** Is the field required? */
    private boolean required = false;

    /** Allowable Values reference */
    private String avref = null;   

    public FieldMetaData()
    {
    }

    public FieldMetaData(String datatype, String isRequired, String avref) {
        this.datatype = datatype;
    	if (isRequired != null && isRequired.equalsIgnoreCase("true"))
            required = true;
        this.avref = avref;
    }
	
    public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

	public String getDatatype() {
		return datatype;
	}
	
    public String getAvref() {
        return avref;
    }

    public void setAvref(String avref) {
        this.avref = avref;
    }
    
    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }
}