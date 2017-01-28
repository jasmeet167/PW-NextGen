package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.ApplyFilterData;
import com.csc.fsg.life.rest.model.AuditFilterData;
import com.csc.fsg.life.rest.model.ChangesFilterData;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.DateSelectItem;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.model.PromoteFilterData;
import com.csc.fsg.life.rest.model.SummarySearchInput;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface SearchService
{
	@SecuredMethod
	public List<CommonSelectItem> getCommonEnvironments(RestServiceParam param);

	@SecuredMethod
	public List<CommonSelectItem> getPlanCommonValues(RestServiceParam param, PlanSearchInput searchInput);

	@SecuredMethod
	public List<DateSelectItem> getPlanEffDates(RestServiceParam param, PlanSearchInput searchInput);

	@SecuredMethod
	public List<String> getPlanProjects(RestServiceParam param, String environment);

	@SecuredMethod
	public List<CommonSelectItem> getPlanTables(RestServiceParam param, String environment, String company);

	@SecuredMethod
	public List<CommonSelectItem> getChangesEnvironments(RestServiceParam param);

	@SecuredMethod
	public ChangesFilterData getChangesFilterValues(RestServiceParam param, String envId);

	@SecuredMethod
	public ApplyFilterData getApplyFilterValues(RestServiceParam param, String envId);

	@SecuredMethod
	public AuditFilterData getAuditFilterValues(RestServiceParam param, String filterAspect, String envId);

	@SecuredMethod
	public PromoteFilterData getPromoteFilterValues(RestServiceParam param, String sourceEnvId, String targetEnvId);

	@SecuredMethod
	public List<CommonSelectItem> getSummaryCommonValues(RestServiceParam param, SummarySearchInput searchInput);

	@SecuredMethod
	public List<DateSelectItem> getSummaryEffDates(RestServiceParam param, SummarySearchInput searchInput);
}
