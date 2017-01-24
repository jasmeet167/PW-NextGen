package com.csc.fsg.life.pw.web.avm;

import java.util.*;

import com.csc.fsg.life.pw.web.utils.HighValueHandler;

/* Modifications: T0091, HAVMENH  */

public class AVUtils {

	public static Hashtable<String, String> getIssueStateNames(String environment,
	        String company, String product, String table, String[] issueStates)  throws Exception{

		AVKey avKey = new AVKey();
		avKey.setEnvironment(environment);
		avKey.setCompany(company);
		avKey.setProduct(product);
		avKey.setTable(null);
		avKey.setFieldName("STATE_CODE");
		avKey.setKey(true);
		
		Hashtable<String, String> codeNValues = AVManager.getAllowableValues(avKey);
		
		String key = null;
		Hashtable<String, String> convertedIssueStatesWithNames = new Hashtable<String, String>();
		for (String issueState : issueStates) {
			key = issueState;

			// if (key.startsWith(Constants.HIGHVALUES_1_BYTE)) {
			if (HighValueHandler.isAllAsterisks(key)) {
				// key = key.replace(Constants.HIGHVALUES_1_BYTE.charAt(0),
				// '*');
				convertedIssueStatesWithNames.put(key, "HIGHVALUES");
			} else {

				if (codeNValues != null && !codeNValues.isEmpty()) {
					String stateName = codeNValues.get(key);
					if (stateName != null)
						convertedIssueStatesWithNames.put(key, stateName);
					else
						convertedIssueStatesWithNames.put(key, key);
				} else {
					convertedIssueStatesWithNames.put(key, key);
				}
			}
		}
		return convertedIssueStatesWithNames;
	}

	public static Hashtable<String, String> getProductNames(String env, String company,
	        ArrayList<String> productCodes) throws Exception{
		Hashtable<String, String> productCodesAndNames = new Hashtable<String, String>();
		Iterator<String> iter = productCodes.iterator();

		AVKey avKey = new AVKey();
		avKey.setEnvironment(env);
		avKey.setCompany(company);
		avKey.setFieldName("PRODUCT_CODE");
		avKey.setKey(true);
		Hashtable<String, String> codeNValues = AVManager.getAllowableValues(avKey);
	
		String key = null;
		String productName = null;

		while (iter.hasNext()) {
			key = iter.next();

			if (codeNValues != null && !codeNValues.isEmpty()) {
				productName = codeNValues.get(key);

				if (productName != null)
					productCodesAndNames.put(key, productName);
				else
					productCodesAndNames.put(key, key);
			} else {
				productCodesAndNames.put(key, key);
			}
		}

		return productCodesAndNames;
	}

}
