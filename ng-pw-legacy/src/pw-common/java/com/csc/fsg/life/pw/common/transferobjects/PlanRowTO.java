package com.csc.fsg.life.pw.common.transferobjects;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.StringTokenizer;

import com.csc.fsg.life.pw.common.stream.MapStream;
import com.csc.fsg.life.pw.common.util.WIPProperties;

/* Modifications: T0103, T0091, T0129 */
// T0103 - new class added for refactor plan key

public class PlanRowTO extends PlanTO implements Comparable<PlanRowTO> {

	public static final String PLAN_ROW_STREAM_KEY = "plan_row_stream";

	public static final String TABLE_PTR_ID_NAME = "TABLE_PTR_ID";
	public static final String TABLE_PTR_VAR_NAME = "TABLE_PTR_VAR";
	public static final String TABLE_PTR_SUBSET_NAME = "TABLE_PTR_SUBSET";

	public static final String TABLE_PTR_ID_KEY = TABLE_PTR_ID_NAME.toLowerCase();
	public static final String TABLE_PTR_VAR_KEY = TABLE_PTR_VAR_NAME.toLowerCase();
	public static final String TABLE_PTR_SUBSET_KEY = TABLE_PTR_SUBSET_NAME.toLowerCase();

	public static int PLAN_ROW_KEYS = PlanTO.PLAN_KEYS + 3;
	protected static int PLAN_FIELDS = PlanTO.PLAN_FIELDS + 3;
	
	private String tablePtrId = "";
	private String tablePtrVar = "";
	private String tablePtrSubset = "";
	
	private boolean inherited = false;
	
	public PlanRowTO() {
		super();
	}
	public PlanRowTO(HashMap<String, String> hashMap) {
		super(hashMap);
		setTablePtrId(getMapValue(hashMap, TABLE_PTR_ID_KEY));
		setTablePtrVar(getMapValue(hashMap, TABLE_PTR_VAR_KEY));
		setTablePtrSubset(getMapValue(hashMap, TABLE_PTR_SUBSET_KEY));
	}
	public PlanRowTO(PlanCriteriaTO planCriteria) {
		this(planCriteria.toHashMap());
	}
	public PlanRowTO(PlanRowTO planRow) {
		this(planRow.toHashMap());
	}
	public PlanRowTO(PlanTO planRow) {
		this(planRow.toHashMap());
	}
	public PlanRowTO(MapStream stream) {
		this(new HashMap<String, String>(stream.getMap()));
	}
	public PlanRowTO(ResultSet resultSet) throws Exception {
		this();
		// Reduce calls to getMetaData() by passing ResultSetMetaData
		ResultSetMetaData md = resultSet.getMetaData();
		setEnvironment(getResultSetValue(resultSet, ENVIRONMENT_NAME, md));
		setCompanyCode(getResultSetValue(resultSet, COMPANY_CODE_NAME, md));
		setProductPrefix(getResultSetValue(resultSet, PRODUCT_PREFIX_NAME, md));
		setProductSuffix(getResultSetValue(resultSet, PRODUCT_SUFFIX_NAME, md));
		setPlanCode(getResultSetValue(resultSet, PLAN_CODE_NAME, md));
		setIssueState(getResultSetValue(resultSet, ISSUE_STATE_NAME, md));
		setLineOfBusiness(getResultSetValue(resultSet, LINE_OF_BUSINESS_NAME, md));
		setEffectiveDate(getResultSetValue(resultSet, EFFECTIVE_DATE_NAME, md));
		setPlanType(getResultSetValue(resultSet, PLAN_TYPE_NAME, md));
		setTablePtrId(getResultSetValue(resultSet, TABLE_PTR_ID_NAME, md));
		setTablePtrVar(getResultSetValue(resultSet, TABLE_PTR_VAR_NAME, md));
		setTablePtrSubset(getResultSetValue(resultSet, TABLE_PTR_SUBSET_NAME, md));
	}
	public PlanRowTO(ResultSet resultSet, String env) throws Exception {
		this();
		// Reduce calls to getMetaData() by passing ResultSetMetaData
		ResultSetMetaData md = resultSet.getMetaData();
		setEnvironment(env);
		setCompanyCode(getResultSetValue(resultSet, COMPANY_CODE_NAME, md));
		setProductPrefix(getResultSetValue(resultSet, PRODUCT_PREFIX_NAME, md));
		setProductSuffix(getResultSetValue(resultSet, PRODUCT_SUFFIX_NAME, md));
		setPlanCode(getResultSetValue(resultSet, PLAN_CODE_NAME, md));
		setIssueState(getResultSetValue(resultSet, ISSUE_STATE_NAME, md));
		setLineOfBusiness(getResultSetValue(resultSet, LINE_OF_BUSINESS_NAME, md));
		setEffectiveDate(getResultSetValue(resultSet, EFFECTIVE_DATE_NAME, md));
		setPlanType(getResultSetValue(resultSet, PLAN_TYPE_NAME, md));
		setTablePtrId(getResultSetValue(resultSet, TABLE_PTR_ID_NAME, md));
		setTablePtrVar(getResultSetValue(resultSet, TABLE_PTR_VAR_NAME, md));
		setTablePtrSubset(getResultSetValue(resultSet, TABLE_PTR_SUBSET_NAME, md));
	}
	public PlanRowTO(ResultSet resultSet, WIPProperties wipProps) throws Exception {
		this();
		// Reduce calls to getMetaData() by passing ResultSetMetaData
		ResultSetMetaData md = resultSet.getMetaData();
		setCompanyCode(getResultSetValue(resultSet, wipProps.getOldCompanyCode(), md));
		setProductPrefix(getResultSetValue(resultSet, wipProps.getOldProductPrefix(), md));
		setProductSuffix(getResultSetValue(resultSet, wipProps.getOldProductSuffix(), md));
		setPlanCode(getResultSetValue(resultSet, wipProps.getOldPlanCode(), md));
		setIssueState(getResultSetValue(resultSet, wipProps.getOldIssueState(), md));
		setLineOfBusiness(getResultSetValue(resultSet, wipProps.getOldLOB(), md));
		setEffectiveDate(getResultSetValue(resultSet, wipProps.getOldEffectiveDate(), md));
		setPlanType(getResultSetValue(resultSet, wipProps.getOldPlanType(), md));
		setTablePtrId(getResultSetValue(resultSet, wipProps.getOldPtrId(), md));
		setTablePtrVar(getResultSetValue(resultSet, wipProps.getOldPtrVar(), md));
		setTablePtrSubset(getResultSetValue(resultSet, wipProps.getOldPtrSubset(), md));
	}

	public PlanRowTO(String stream, String delimiter) {
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
			setTablePtrId(tokenizer.nextToken());
			setTablePtrVar(tokenizer.nextToken());
			setTablePtrSubset(tokenizer.nextToken());
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
			setTablePtrId(stream.substring(28, 31));
			setTablePtrVar(stream.substring(31, 32));
			setTablePtrSubset(stream.substring(32, 48));
		}
	}
	
	protected String getResultSetValue(ResultSet resultSet, String name, ResultSetMetaData md) throws Exception {
		String value = "";
		/* Reduce calls to getMetaData() (which clones) by passing ResultSetMetaData
		for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
			if (resultSet.getMetaData().getColumnName(i).equals(name)) {
				value = (String) resultSet.getString(name);
				break;
			}
		}
		*/
		for (int i = 1; i <= md.getColumnCount(); i++) {
			if (md.getColumnName(i).equals(name)) {
				value = resultSet.getString(name);
				break;
			}
		}
		return value;
	}
	
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> hashMap = super.toHashMap();
		setMapValue(hashMap, PlanRowTO.TABLE_PTR_ID_KEY, getTablePtrId());
		setMapValue(hashMap, PlanRowTO.TABLE_PTR_VAR_KEY, getTablePtrVar());
		setMapValue(hashMap, PlanRowTO.TABLE_PTR_SUBSET_KEY, getTablePtrSubset());
		return hashMap;
	}

	public String toString() {
		return getPlanRowKey("|");
	}
	
	public String getPlanRowKey(String delimiter) {
		StringBuffer sb = new StringBuffer();
		sb.append(getPlanKey(delimiter) + delimiter);
		sb.append(getTokenValue(getTablePtrId()) + delimiter);
		sb.append(getTokenValue(getTablePtrVar()) + delimiter);
		sb.append(getTokenValue(getTablePtrSubset()));
		return sb.toString();
	}

	public String getPlanRowNameKey(String delimiter) {
		StringBuffer sb = new StringBuffer();
		sb.append(getPlanKey(delimiter) + delimiter);
		sb.append(getTokenValue(getPlanName()) + delimiter);
		sb.append(getTokenValue(getTablePtrId()) + delimiter);
		sb.append(getTokenValue(getTablePtrVar()) + delimiter);
		sb.append(getTokenValue(getTablePtrSubset()));
		return sb.toString();
	}

	public String getTablePtrId() {
		return tablePtrId;
	}

	public void setTablePtrId(String tablePtrId) {
		this.tablePtrId = getFieldValue(tablePtrId);
	}

	public String getTablePtrSubset() {
		return tablePtrSubset;
	}

	public void setTablePtrSubset(String tablePtrSubset) {
		this.tablePtrSubset = getFieldValue(tablePtrSubset);
	}

	public String getTablePtrVar() {
		return tablePtrVar;
	}

	public void setTablePtrVar(String tablePtrVar) {
		this.tablePtrVar = getFieldValue(tablePtrVar);
	}
	
	public boolean isInherited() {
		return inherited;
	}
	
	public void setInherited(boolean inherited) {
		this.inherited = inherited;
	}
	
	public boolean equals(Object obj) {
		if ( obj == null )
			return false;
		return toString().equals(obj.toString());
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
	
	public int compareTo(PlanRowTO other) {
		if ( other == null )
			return -1;
		return toString().compareTo(other.toString());
	}
}

