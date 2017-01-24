/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.util.*;
import java.util.Map.*;

import com.csc.fsg.life.pw.web.environment.*;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.web.avm.AVUtils;
import com.csc.fsg.life.pw.web.utils.HighValueHandler;

/* Modifications: T0103, T0091 ,T0120*/

public class PlanFilterSpecContext extends WizObject {

	private Environment environment;
	// refactor plan key.
	/*
	private Hashtable _companyCodesAndNames;
	private String _productCodes[];
	private String _issueStateCodes[];
	private String _lineOfBusiness[];
	private String _planEffectiveDate[];
	private String _planCodes[];
	private boolean _mergedView = false;
	*/
	
	public PlanFilterSpecContext(String envId) throws Exception {
		
		environment = EnvironmentManager.getInstance().getEnvironment(envId);
	}

	private Hashtable<String, String> convertHighValues(Hashtable planKeyHashTmp) {
		Hashtable<String, String> planKeyHash = new Hashtable<String, String>();
		Iterator iterator = planKeyHashTmp.entrySet().iterator();
		Entry entry;
		String key;
		for (;iterator.hasNext();) {
		    entry = (Entry) iterator.next();
		    key = (String) entry.getKey();
			if (HighValueHandler.isAllAsterisks(key.trim()))
				planKeyHash.put(key.trim(), "HIGHVALUES");
			else
				planKeyHash.put(key, (String) entry.getValue());
		}
		return planKeyHash;
	}

	private Hashtable<String, String> convertHighValues(String[] planKeyArray) {
		Hashtable<String, String> planKeyHash = new Hashtable<String, String>();
		String key;
		for (String element : planKeyArray) {
			key = element;
			if (HighValueHandler.isAllAsterisks(key))
				planKeyHash.put(key, "HIGHVALUES");
			else
				planKeyHash.put(key, key);
		}
		return planKeyHash;
	}

	public String formatToKeyNamePairs(TreeMap<String, String> planKeyTree) {
		StringBuffer sb = new StringBuffer();
		Iterator iterator = planKeyTree.entrySet().iterator();
		Entry entry;
		String key;
		String value;
		for (;iterator.hasNext();) {
		    entry = (Entry) iterator.next();
		    key = (String) entry.getKey();
		    value = (String) entry.getValue();
		    if (value == null)
		    	value = key;
			sb.append(key + "-" + value + "\t" + key + "\n");
		}
		return sb.toString();
	}

	public Hashtable<String, String> getCompanyCodes(PlanCriteriaTO planCriteria) throws Exception {
		Hashtable<String, String> planKeyHash = environment.getAssistent().getCompanyCodesAndNames();
		return planKeyHash;
		//return formatToKeyNamePairs(new TreeMap<String, String>(planKeyHash));
	}

	public Map<String, String> getEffectiveDates(PlanCriteriaTO planCriteria) throws Exception {
		String[] planKeyArray = WizUtils.asStringArray(
				environment.getAssistent().getNextPlanKey(planCriteria));
		
		Hashtable<String, String> planKeyHash = new Hashtable<String, String>();
		String key;
		for (String element : planKeyArray) {
			key = element;
			planKeyHash.put(key, key);
		}
		return new TreeMap<String, String>(planKeyHash);
	}

	public Map<String, String> getIssueStates(PlanCriteriaTO planCriteria) throws Exception {
		String envId = planCriteria.getEnvironment();
		String[] planKeyArray = WizUtils.asStringArray(
				environment.getAssistent().getNextPlanKey(planCriteria));
		
		Hashtable planKeyHash = AVUtils.getIssueStateNames(envId,planCriteria.getCompanyCode(),planCriteria.getProductCode(),"T000X",planKeyArray);
		
		return new TreeMap<String, String>(convertHighValues(planKeyHash));
	}

	public Map<String, String>  getLinesOfBusiness(PlanCriteriaTO planCriteria) throws Exception {
		String[] planKeyArray = WizUtils.asStringArray(
				environment.getAssistent().getNextPlanKey(planCriteria));
		
		return new TreeMap<String, String>(convertHighValues(planKeyArray));
	}

	public Map<String, String> getPlanCodes(PlanCriteriaTO planCriteria) throws Exception {
		String[] planKeyArray = WizUtils.asStringArray(
				environment.getAssistent().getNextPlanKey(planCriteria));
		
		return new TreeMap<String, String>(convertHighValues(planKeyArray));
	}

	public Map<String, String> getProductCodes(PlanCriteriaTO planCriteria) throws Exception {
		String envId = planCriteria.getEnvironment();
		String[] planKeyArray = WizUtils.asStringArray(
				environment.getAssistent().getProductCodes(planCriteria));
	
		ArrayList<String> planKeyList = new ArrayList<String>();
		for (String element : planKeyArray)
			planKeyList.add(element);
		Hashtable<String, String> planKeyHash = AVUtils.getProductNames(envId,planCriteria.getCompanyCode(),planKeyList);
	
		return new TreeMap<String, String>(planKeyHash);
	}

	// refactor plan key.
	/*
//	public String[] get_companyCodes() {
//
//		Object[] objectArray = _companyCodesAndNames.keySet().toArray();
//		String stringArray[] = new String[objectArray.length];
//		for (int i = 0; i < stringArray.length - 1; i++) 
//			stringArray[i] = (String) objectArray[i];
//		return stringArray;
//	}

	public Hashtable get_companyCodesAndNames() {
		return _companyCodesAndNames;
	}

	public void initalize_companyCodesAndNames() throws Exception {
		_companyCodesAndNames = environment.getAssistent().getCompanyCodesAndNames();
	}

	public void initalize_issueStateCodes(String companyCodes[],
	        String productCodes[], String planCodes[]) throws Exception {

		_issueStateCodes = WizUtils.asStringArray(environment.getAssistent()
		        .getIssueStates(companyCodes[0], productCodes[0], planCodes[0],
		                _mergedView));
	}

	public void initalize_lineOfBusiness(String companyCodes[],
	        String productCodes[], String planCodes[], String issueStates[])
	        throws Exception {

		_lineOfBusiness = WizUtils.asStringArray(environment.getAssistent()
		        .getLinesOfBusiness(companyCodes[0], productCodes[0],
		                planCodes[0], issueStates[0], _mergedView));
	}

	public void initalize_planCodes(String companyCodes[],
	        String productCodes[]) throws Exception {

		_planCodes = WizUtils.asStringArray(environment.getAssistent().getPlanCodes(
		                companyCodes[0], productCodes[0], _mergedView));
	}

	public void initalize_planEffectiveDate(String companyCodes[],
	        String productCodes[], String planCodes[], String issueStates[],
	        String lob[]) throws Exception {

		_planEffectiveDate = WizUtils.asStringArray(environment.getAssistent()
		        .getPlanEffectiveDate(companyCodes[0], productCodes[0],
		                planCodes[0], issueStates[0], lob[0], _mergedView));
	}

	public void initalize_productCodes(String companyCodes[]) {

		try {
			_productCodes = WizUtils.asStringArray(environment.getAssistent()
			        .getProducts(companyCodes[0], _mergedView));
		} catch (Exception e) {
			throw WrapperException.wrap(e);
		}
	}

	public void setMergedView(boolean mergedView) {
		_mergedView = mergedView;
	}

	public String[] get_issueStateCodes(String[] company, String[] product,
	        String[] plancode) throws Exception {

		if ((company != null) && (product != null) && (plancode != null)) {
			initalize_issueStateCodes(company, product, plancode);
		}
		return _issueStateCodes;
	}

	public String[] get_lineOfBusiness(String[] company, String[] product,
	        String[] plancode, String[] issuestate) throws Exception {

		if ((company != null) && (product != null) && (plancode != null)
		        && (issuestate != null)) {
			initalize_lineOfBusiness(company, product, plancode, issuestate);
		}

		return _lineOfBusiness;
	}

	public String[] get_planCodes(String[] company, String[] product)
	        throws Exception {

		if ((company != null) && (product != null)) {
			initalize_planCodes(company, product);
		}
		return _planCodes;
	}

	public String[] get_planEffectiveDate(String[] company, String[] product,
	        String[] plancode, String[] issuestate, String[] lob)
	        throws Exception {

		if ((company != null) && (product != null) && (plancode != null)
		        && (issuestate != null) && (lob != null)) {
			initalize_planEffectiveDate(company, product, plancode, issuestate,
			        lob);
		}
		return _planEffectiveDate;
	}

	public String[] get_productCodes(String[] company) {

		if (company != null) {
			initalize_productCodes(company);
		}

		return _productCodes;
	}

	public Hashtable getAllLOBCodes() throws Exception {
		return environment.getAssistent().getLOBCodesAndNames(null);
	}
	*/
	

	

}
