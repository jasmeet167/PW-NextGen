package com.csc.fsg.life.avm.api;

import java.util.ArrayList;
import java.util.List;

import com.csc.fsg.life.avm.biz.dao.model.AVVALModel;

/**
 * List Type of Allowable Values.
 */
public class ListAllowableValues
    extends AllowableValues 
{
    /**
	 * Core values for ListAllowableValue object.
	 */
    private List<String> coreValues = new ArrayList<String>();
    
    /**
	 * Display values for ListAllowableValue object.
	 */
    private List<String> displayValues = new ArrayList<String>();
    
    /**
	 * Constructor for ListAllowableValues.
	 * 
	 * @param aVVALModelList List of AVVALModels defining List values.
	 */
    protected ListAllowableValues(List<AVVALModel> aVVALModelList) {
        for (int i=0; i<aVVALModelList.size(); i++) {
            AVVALModel aVVALModel = (AVVALModel)aVVALModelList.get(i);
            if (aVVALModel.getCoreValue().equals(AVMManager.AVMSpaceValue))
                coreValues.add(" ");
            else
                coreValues.add(aVVALModel.getCoreValue());
            if (aVVALModel.getDisplayValue().equals(AVMManager.AVMSpaceValue))
                displayValues.add(" ");
            else
                displayValues.add(aVVALModel.getDisplayValue());
        }
    }
    
    /**
	 * Access method for the coreValues property.
	 * 
	 * @return the current value of the coreValues property
	 */
    public List<String> getCoreValues() {
        return coreValues;
    }
    
    /**
	 * Access method for the displayValues property.
	 * 
	 * @return the current value of the displayValues property
	 */
    public List<String> getDisplayValues() {
        return displayValues;
    }
    
    public String toString()
    {
		StringBuffer buf = new StringBuffer();
    	buf.append("coreValues:");
		buf.append(coreValues);
		buf.append("displayValues:");
		buf.append(displayValues);
		return buf.toString();
    }
}
