package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.ApplyFilterData;
import com.csc.fsg.life.rest.model.AuditFilterData;
import com.csc.fsg.life.rest.model.ChangesFilterData;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.model.PromoteFilterData;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.model.SummarySearchInput;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface SearchService
{
	@SecuredMethod
	public List<SelectItem> getCommonEnvironments(RestServiceParam param);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<SelectItem> getPlanCommonValues(RestServiceParam param, PlanSearchInput searchInput);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<SelectItem> getPlanEffDates(RestServiceParam param, PlanSearchInput searchInput);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<SelectItem> getPlanProjects(RestServiceParam param);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<SelectItem> getPlanTables(RestServiceParam param);

	@SecuredMethod
	public List<SelectItem> getChangesEnvironments(RestServiceParam param);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public ChangesFilterData getChangesFilterValues(RestServiceParam param);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public ApplyFilterData getApplyFilterValues(RestServiceParam param);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public AuditFilterData getAuditFilterValues(RestServiceParam param, String filterAspect);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public PromoteFilterData getPromoteFilterValues(RestServiceParam param);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<SelectItem> getSummaryCommonValues(RestServiceParam param, SummarySearchInput searchInput);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<SelectItem> getSummaryEffDates(RestServiceParam param, SummarySearchInput searchInput);
}
