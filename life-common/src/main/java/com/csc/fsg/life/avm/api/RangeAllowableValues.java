package com.csc.fsg.life.avm.api;

import java.util.ArrayList;
import java.util.List;

import com.csc.fsg.life.avm.biz.dao.model.AVRNGModel;

/* Modifications: T0140 */

/**
 * Range Type of Allowable Values.
 */
public class RangeAllowableValues 
	extends AllowableValues 
{
	/**
	 * Minimum values for RangeAllowableValues object.
	 */
    private List<Double> minimumValues = new ArrayList<Double>();
   
	/**
	 * Maximum values for RangeAllowableValues object.
	 */
    private List<Double> maximumValues = new ArrayList<Double>();
   
	/**
	 * Constructor for RangeAllowableValues.
	 * 
	 * @param aVRNGModelList List of AVRNGModels defining Range values
	 */
   protected RangeAllowableValues(List<AVRNGModel> aVRNGModelList) 
   {
        for (int i=0; i<aVRNGModelList.size(); i++) {
            AVRNGModel aVRNGModel = (AVRNGModel)aVRNGModelList.get(i);
            minimumValues.add(aVRNGModel.getMinValue());
            maximumValues.add(aVRNGModel.getMaxValue());
        }
   }
   
	/**
	 * Access method for the minimumValues property.
	 * 
	 * @return the current value of the minimumValues property
	 */
	public List<Double> getMinimumValues() 
	{
		return minimumValues;    
	}
   
	/**
	 * Access method for the maximumValues property.
	 * 
	 * @return the current value of the maximumValues property
	 */
	public List<Double> getMaximumValues() 
	{
		return maximumValues;    
	}
   
	/**
	 * Helper method that always treats range as long and increment it by one.
	 * 
	 * @return the list of values in the range.
	 */
	public List<String> getRangeValues()
	{
   		List<String> allValuesInRange = new ArrayList<String>();
   		for (int i = 0; i < minimumValues.size(); i++) {
			Long minValue = Long.valueOf(((Double)minimumValues.get(i)).longValue());
			
			//get the maximum value for maximumValues list on same index
			Long maxValue = Long.valueOf(((Double)maximumValues.get(i)).longValue());
			
			// now create list of this range 
			for(int range=minValue.intValue(); range <= maxValue.intValue() && range < Integer.MAX_VALUE; range++) {
				allValuesInRange.add(Long.valueOf(range).toString());
			}
		}
   		return allValuesInRange;
	}
   
    public String toString()
    {
		StringBuffer buf = new StringBuffer();
		buf.append("minimumValues :");
		buf.append(minimumValues);
		buf.append("maximumValues :");
		buf.append(maximumValues);
		return buf.toString();
    }
}