package com.csc.fsg.life.pw.common.transferobjects;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.csc.fsg.life.pw.common.stream.MapStream;
import com.csc.fsg.life.pw.common.stream.ListStream;

/* Modifications: T0103, T0091, T0129, WMA-54 */
// T0103 - new class added for refactor plan key

public class PlanCriteriaTO extends PlanRowTO {

	public static final String PROJECT_NAME = "PROJECT_NAME";
	public static final String PROJECTS = "PROJECT_NAMES";
	public static final String USER_NAME = "USER_NAME";

	public static final String PLAN_CRITERIA_STREAM_KEY = "plan_criteria_stream";
	public static final String PLAN_CRITERIA_NEW_STREAM_KEY = "plan_criteria_stream_new";
	public static final String PLAN_CRITERIA_OLD_STREAM_KEY = "plan_criteria_stream_old";

	public static final String PROJECT_NAME_KEY = PROJECT_NAME.toLowerCase();
	public static final String PROJECTS_KEY = PROJECTS.toLowerCase();
	public static final String USER_NAME_KEY = USER_NAME.toLowerCase();
	
	public static final String MERGED_VIEW_KEY = "merged_view";
	public static final String FOR_NEW_KEY = "for_new";

	protected static int PLAN_FIELDS = PlanRowTO.PLAN_FIELDS + 4;

	private String productCode = "";
	private String projectName = "";
	private List<String> projects = new ArrayList<String>();
	private String userName = "";
	private boolean viewChanges = false;
	private boolean forNew = false;
	private boolean loadNP = false;
	
	public PlanCriteriaTO() {
		super();
	}
	
	public PlanCriteriaTO(PlanCriteriaTO planCriteria) {
		this(planCriteria.toHashMap());
	}
	public PlanCriteriaTO(PlanTO plan) {
		this(plan.toHashMap());
	}
	public PlanCriteriaTO(PlanRowTO planRow) {
		this(planRow.toHashMap());
	}
	public PlanCriteriaTO(HashMap hashMap) {
		super(hashMap);
		setProductCode(getMapValue(hashMap, PRODUCT_CODE_KEY));
		setProjectName(getMapValue(hashMap, PROJECT_NAME_KEY));
		setUserName(getMapValue(hashMap, USER_NAME_KEY));
		setViewChanges(Boolean.valueOf(getMapValue(hashMap, MERGED_VIEW_KEY)).booleanValue());
		setForNew(Boolean.valueOf(getMapValue(hashMap, FOR_NEW_KEY)).booleanValue());
		
		String projectsStr = getMapValue(hashMap, PROJECTS_KEY);
		if ( projectsStr.length() > 0 ) {
			ListStream ls = new ListStream();
			ls.fromStream(projectsStr);
			setProjects(ls.getList());
		}
	}
	public PlanCriteriaTO(MapStream stream) {
		this(new HashMap<String, Object>(stream.getMap()));
	}

	public HashMap<String, String> toHashMap() {
		HashMap<String, String> hashMap = super.toHashMap();
		setMapValue(hashMap, PROJECT_NAME_KEY, getProjectName());
		setMapValue(hashMap, USER_NAME_KEY, getUserName());
		// only add true values.
		if (isViewChanges())
			setMapValue(hashMap, MERGED_VIEW_KEY, Boolean.valueOf(viewChanges).toString());
		if (isForNew())
			setMapValue(hashMap, FOR_NEW_KEY, Boolean.valueOf(forNew).toString());
		
		if ( getProjects().size() > 0 ) {
			ListStream ls = new ListStream(getProjects());
			setMapValue(hashMap, PROJECTS_KEY, ls.toStream());
		}
		return hashMap;
	}
	
	public String getPlanCriteriaKey(String delimiter) {
		StringBuffer sb = new StringBuffer();
		sb.append(getTokenValue(getCompanyCode()) + delimiter);
		sb.append(getTokenValue(getProductCode()) + delimiter);
		sb.append(getTokenValue(getPlanCode()) + delimiter);
		sb.append(getTokenValue(getIssueState()) + delimiter);
		sb.append(getTokenValue(getLineOfBusiness()) + delimiter);
		sb.append(getTokenValue(getEffectiveDate()) + delimiter);
		sb.append(getTokenValue(getPlanType()));
		return sb.toString();
	}
	
	public String getProductPrefix(){
		String productPrefix = super.getProductPrefix();
		if (productPrefix.equals("")) {
			String productCode = getProductCode();
			if (!productCode.equals("")) {
				productPrefix = productCode.substring(0, 1);
			}
		}
		return productPrefix;
	}

	public String getProductSuffix(){
		String productSuffix = super.getProductSuffix();
		if (productSuffix.equals("")) {
			String productCode = getProductCode();
			if (!productCode.equals("")) {
				productSuffix = productCode.substring(1, 2);
			}
		}
		return productSuffix;
	}

	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = getFieldValue(productCode);
	}

	public boolean isViewChanges() {
		return viewChanges;
	}

	public void setViewChanges(boolean viewChanges) {
		this.viewChanges = viewChanges;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = getFieldValue(projectName);
	}
	
	public List<String> getProjects() {
		return projects;
	}
	
	public void setProjects(List<String> projects) {
		this.projects = projects;
	}
	
	public String getProjectsStr() {
		// TT wmA SPR 6328 - return "None" for empty list
		if ( getProjects().size() == 0 )
			return "None";
		StringBuffer sb = new StringBuffer();
		for (int i=0; i < projects.size(); i++) {
			sb.append(projects.get(i).trim());
			if ( i < (projects.size()-1) )
				sb.append("\t");
		}
		return sb.toString();
	}
	
	public void setProjects(String projectsStr) {
		String[] tokens = projectsStr.split("\t");
		List<String> projectList = new ArrayList<String>();
		for ( String project : tokens ) {
			projectList.add(project);
		}
		setProjects(projectList);
	}

	public boolean isForNew() {
		return forNew;
	}

	public void setForNew(boolean forNew) {
		this.forNew = forNew;
	}

	public boolean isLoadNP() {
		return loadNP;
	}

	public void setLoadNP(boolean loadNP) {
		this.loadNP = loadNP;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = getFieldValue(userName);
	}
	
	public boolean equals(Object obj) {
		if ( obj == null )
			return false;
		return toString().equals(obj.toString());
	}
	
	public int hashCode() {
		return toString().hashCode();
	}
}

