package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.ChangesOnlyFilterData;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.DateSelectItem;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface SearchService
{
	@SecuredMethod
	public List<CommonSelectItem> getEnvironments(RestServiceParam param);

	@SecuredMethod
	public List<CommonSelectItem> getPlanCommonValues(RestServiceParam param, PlanSearchInput searchInput);

	@SecuredMethod
	public List<DateSelectItem> getPlanEffectiveDates(RestServiceParam param, PlanSearchInput searchInput);

	@SecuredMethod
	public List<String> getProjects(RestServiceParam param, String environment);

	@SecuredMethod
	public List<CommonSelectItem> getTables(RestServiceParam param, String environment, String company);

	@SecuredMethod
	public List<CommonSelectItem> getEnvironmentsWithChanges(RestServiceParam param);

	@SecuredMethod
	public ChangesOnlyFilterData getChangesOnlyFilterValues(RestServiceParam param, String envId);
}
