package com.csc.fsg.life.pw.common.transferobjects;

import java.util.HashMap;
import java.util.StringTokenizer;

import com.csc.fsg.life.pw.common.stream.MapStream;

/* Modifications: T0103, T0091, T0129 */
// T0103 - new class added for refactor plan key

public class PlanTO extends PlanTOBase {
	
	public static final String PLAN_STREAM_KEY = "plan_stream";

	public PlanTO() {
		super();
	}
	public PlanTO(HashMap<String, String> hashMap) {
		setEnvironment(getMapValue(hashMap, ENVIRONMENT_KEY));
		setCompanyCode(getMapValue(hashMap, COMPANY_CODE_KEY));
		setProductPrefix(getMapValue(hashMap, PRODUCT_PREFIX_KEY));
		setProductSuffix(getMapValue(hashMap, PRODUCT_SUFFIX_KEY));
		setPlanCode(getMapValue(hashMap, PLAN_CODE_KEY));
		setIssueState(getMapValue(hashMap, ISSUE_STATE_KEY));
		setLineOfBusiness(getMapValue(hashMap, LINE_OF_BUSINESS_KEY));
		setEffectiveDate(getMapValue(hashMap, EFFECTIVE_DATE_KEY));
		setPlanType(getMapValue(hashMap, PLAN_TYPE_KEY));
		setPlanName(getMapValue(hashMap, PLAN_NAME_KEY));
	}
	public PlanTO(PlanTO plan) {
		this(plan.toHashMap());
	}
	public PlanTO(PlanRowTO planRow) {
		this(planRow.toHashMap());
	}
	public PlanTO(PlanCriteriaTO planCriteria) {
		this(planCriteria.toHashMap());
	}
	public PlanTO(MapStream stream) {
		this(new HashMap<String, String>(stream.getMap()));
	}
	public PlanTO(String stream, String delimiter) {
		this();
		if (!delimiter.equals("")) {
			StringTokenizer tokenizer = new StringTokenizer(stream, delimiter);
			setCompanyCode(tokenizer.nextToken());
			setProductPrefix(tokenizer.nextToken());
			setProductSuffix(tokenizer.nextToken());
			setPlanCode(tokenizer.nextToken());
			setIssueState(tokenizer.nextToken());
			setLineOfBusiness(tokenizer.nextToken());
			setEffectiveDate(tokenizer.nextToken());
			setPlanType(tokenizer.nextToken());
		}
		else {
			setCompanyCode(stream.substring(0, 3));
			setProductPrefix(stream.substring(3, 4));
			setProductSuffix(stream.substring(4, 5));
			setPlanCode(stream.substring(5, 11));
			setIssueState(stream.substring(11, 14));
			setLineOfBusiness(stream.substring(14, 17));
			setEffectiveDate(stream.substring(17, 27));
			setPlanType(stream.substring(27, 28));
		}
	}
	
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> hashMap = new HashMap<String, String>(getPLAN_FIELDS());
		setMapValue(hashMap, ENVIRONMENT_KEY, getEnvironment());
		setMapValue(hashMap, SCHEMA_KEY, getEnvironment());
		setMapValue(hashMap, COMPANY_CODE_KEY, getCompanyCode());
		setMapValue(hashMap, PRODUCT_CODE_KEY, getProductCode());
		setMapValue(hashMap, PRODUCT_PREFIX_KEY, getProductPrefix());
		setMapValue(hashMap, PRODUCT_SUFFIX_KEY, getProductSuffix());
		setMapValue(hashMap, PLAN_CODE_KEY, getPlanCode());
		setMapValue(hashMap, ISSUE_STATE_KEY, getIssueState());
		setMapValue(hashMap, LINE_OF_BUSINESS_KEY, getLineOfBusiness());
		setMapValue(hashMap, EFFECTIVE_DATE_KEY, getEffectiveDate());
		setMapValue(hashMap, PLAN_TYPE_KEY, getPlanType());
		setMapValue(hashMap, PLAN_NAME_KEY, getPlanName());
		return hashMap;
	}
	
	public String toString() {
		return getPlanKey("|");
	}

	public String toStream() {
		return new MapStream(toHashMap()).toStream();
	}

	public boolean equals(Object obj) {
		if ( !(obj instanceof PlanTO) )
			return false;
		PlanTO plan = (PlanTO) obj;
		return (
				getEnvironment().equals(plan.getEnvironment()) &&
				getCompanyCode().equals(plan.getCompanyCode()) &&
				getProductCode().equals(plan.getProductCode()) &&
				getPlanCode().equals(plan.getPlanCode()) &&
				getIssueState().equals(plan.getIssueState()) &&
				getLineOfBusiness().equals(plan.getLineOfBusiness()) &&
				getEffectiveDate().equals(plan.getEffectiveDate()) &&
				getPlanType().equals(plan.getPlanType())
				);
		}
	
	public int hashCode() {
		return toString().hashCode();
	}
	
	public String getPlanKey(String delimiter) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTokenValue(getCompanyCode()) + delimiter);
		sb.append(getTokenValue(getProductPrefix()) + delimiter);
		sb.append(getTokenValue(getProductSuffix()) + delimiter);
		sb.append(getTokenValue(getPlanCode()) + delimiter);
		sb.append(getTokenValue(getIssueState()) + delimiter);
		sb.append(getTokenValue(getLineOfBusiness()) + delimiter);
		sb.append(getTokenValue(getEffectiveDate()) + delimiter);
		sb.append(getTokenValue(getPlanType()));
		return sb.toString();
	}
	
	public String getPlanKeyForDisplay(String delimiter) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTokenValue(getCompanyCode()) + delimiter);
		sb.append(getTokenValue(getProductPrefix()));
		sb.append(getTokenValue(getProductSuffix()) + delimiter);
		sb.append(getTokenValue(getPlanCode()) + delimiter);
		sb.append(getTokenValue(getIssueState()) + delimiter);
		sb.append(getTokenValue(getLineOfBusiness()) + delimiter);
		sb.append(getTokenValue(getEffectiveDate()) + delimiter);
		sb.append(getTokenValue(getPlanType()));
		return sb.toString();
	}
	
	public String getFullPlanKey(String delimiter) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTokenValue(getEnvironment()) + delimiter);
		sb.append(getTokenValue(getCompanyCode()) + delimiter);
		sb.append(getTokenValue(getProductPrefix()) + delimiter);
		sb.append(getTokenValue(getProductSuffix()) + delimiter);
		sb.append(getTokenValue(getPlanCode()) + delimiter);
		sb.append(getTokenValue(getIssueState()) + delimiter);
		sb.append(getTokenValue(getLineOfBusiness()) + delimiter);
		sb.append(getTokenValue(getEffectiveDate()) + delimiter);
		sb.append(getTokenValue(getPlanType()));
		return sb.toString();
	}

	public boolean isEmpty(){
		return getPlanCode().trim().equals("");
	}
}

