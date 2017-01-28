package com.csc.fsg.life.rest.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.common.config.MyBatisApplicationEnvironmentBean;
import com.csc.fsg.life.pw.client.mdi.AuditErrorFilter;
import com.csc.fsg.life.pw.client.util.RCMClientUtilities;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTOBase;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.common.util.WIPProperties;
import com.csc.fsg.life.pw.web.actions.rcm.beans.PackageBean;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.EntireTableFilterContext;
import com.csc.fsg.life.pw.web.io.PlanFilterSpecContext;
import com.csc.fsg.life.pw.web.io.SummaryFilterSpecContext;
import com.csc.fsg.life.pw.web.io.WIPRows;
import com.csc.fsg.life.rest.exception.BadRequestException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.ApplyFilterData;
import com.csc.fsg.life.rest.model.AuditFilterData;
import com.csc.fsg.life.rest.model.ChangesFilterData;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.DateSelectItem;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.ErrorModelFactory;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.model.PromoteFilterData;
import com.csc.fsg.life.rest.model.SummarySearchInput;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class SearchServiceImpl
	extends RestServiceImpl
	implements SearchService
{
	@Autowired
	protected ErrorModelFactory errorModelFactory = null;

	public SearchServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.SearchService");
	}

	public List<CommonSelectItem> getCommonEnvironments(RestServiceParam param)
	{
		try {
			// TODO: +++ Security

			CommonApplicationConfigBean pwConfig = getBean(CommonApplicationConfigBean.class);
			Map<String, MyBatisApplicationEnvironmentBean> environmentMap = pwConfig.getEnvironments();

			List<CommonSelectItem> envList = new ArrayList<>();
			for (MyBatisApplicationEnvironmentBean envBean : environmentMap.values()) {
				CommonSelectItem env = new CommonSelectItem();
				envList.add(env);
				env.setCoreValue(envBean.getName());
				env.setDisplayValue(envBean.getDisplayName());
			}

			return envList;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public List<CommonSelectItem> getPlanCommonValues(RestServiceParam param, PlanSearchInput searchInput)
	{
		try {
			// TODO: +++ Security

			String env = searchInput.getEnvId();
			boolean isGoodEnvironment = false;

			if (StringUtils.hasText(env)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(env) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildPlanCriteria(searchInput);
			PlanFilterSpecContext context = new PlanFilterSpecContext(env);
			Map<String, String> commonValuesMap = null;

			if (!StringUtils.hasText(searchInput.getCompanyCode()))
				commonValuesMap = context.getCompanyCodes(planCriteria);
			else if (!StringUtils.hasText(searchInput.getProductCode()))
				commonValuesMap = context.getProductCodes(planCriteria);
			else if (!StringUtils.hasText(searchInput.getPlanCode()))
				commonValuesMap = context.getPlanCodes(planCriteria);
			else if (!StringUtils.hasText(searchInput.getIssueState()))
				commonValuesMap = context.getIssueStates(planCriteria);
			else if (!StringUtils.hasText(searchInput.getLob()))
				commonValuesMap = context.getLinesOfBusiness(planCriteria);
			else {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("unknown_search_type"));
				throw new BadRequestException(model);
			}

			List<CommonSelectItem> commonValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : commonValuesMap.entrySet()) {
				CommonSelectItem item = new CommonSelectItem();
				commonValues.add(item);
				item.setCoreValue(entry.getKey());
				item.setDisplayValue(entry.getValue());
			}

			return commonValues;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public List<DateSelectItem> getPlanEffDates(RestServiceParam param, PlanSearchInput searchInput)
	{
		try {
			// TODO: +++ Security

			String env = searchInput.getEnvId();
			boolean isGoodEnvironment = false;

			if (StringUtils.hasText(env)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(env) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildPlanCriteria(searchInput);
			PlanFilterSpecContext context = new PlanFilterSpecContext(env);
			Map<String, String> dateValuesMap = null;

			if (!StringUtils.hasText(searchInput.getCompanyCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_company"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getProductCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_product"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getPlanCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_plan"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getIssueState())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_state"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getLob())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_lob"));
				throw new BadRequestException(model);
			}
			else {
				dateValuesMap = context.getEffectiveDates(planCriteria);
			}

			List<DateSelectItem> dateValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : dateValuesMap.entrySet()) {
				DateSelectItem item = new DateSelectItem();
				dateValues.add(item);

				Date date = Date.valueOf(entry.getKey());
				LocalDate localDate = new LocalDate(date.getTime());
				item.setCoreValue(localDate);
				item.setDisplayValue(entry.getValue());
			}

			return dateValues;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	private PlanCriteriaTO buildPlanCriteria(PlanSearchInput searchInput)
	{
		HashMap<String, Object> planKeys = new HashMap<>();
		if (searchInput.isViewChangesEffective() == null)
			planKeys.put(PlanCriteriaTO.MERGED_VIEW_KEY, Boolean.FALSE.toString());
		else
			planKeys.put(PlanCriteriaTO.MERGED_VIEW_KEY, searchInput.isViewChangesEffective().toString());

		planKeys.put(PlanTOBase.ENVIRONMENT_KEY, searchInput.getEnvId());
		planKeys.put(PlanTOBase.COMPANY_CODE_KEY, searchInput.getCompanyCode());

		String productCode = searchInput.getProductCode();
		planKeys.put(PlanTOBase.PRODUCT_CODE_KEY, productCode);

		if (StringUtils.hasText(productCode)) {
			planKeys.put(PlanTOBase.PRODUCT_PREFIX_KEY, productCode.substring(0, 1));
			if (productCode.length() > 1)
				planKeys.put(PlanTOBase.PRODUCT_SUFFIX_KEY, productCode.substring(1, 2));
		}

		planKeys.put(PlanTOBase.PLAN_CODE_KEY, searchInput.getPlanCode());
		planKeys.put(PlanTOBase.ISSUE_STATE_KEY, searchInput.getIssueState());
		planKeys.put(PlanTOBase.LINE_OF_BUSINESS_KEY, searchInput.getLob());

		PlanCriteriaTO planCriteria = new PlanCriteriaTO(planKeys);
		return planCriteria;
	}

	public List<String> getPlanProjects(RestServiceParam param, String envId)
	{
		try {
			// TODO: +++ Security

			WIPProperties wipProps = WIPProperties.getInstance();
			Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
			if (env == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			List<String> rawProjects = WIPRows.getDistinctItemsFromWIP(env, wipProps.getProjectName(), false);
			List<String> projects = new ArrayList<>();
			if (rawProjects != null)
				for (String rawProject : rawProjects)
					if (rawProject != null)
						projects.add(rawProject.trim());

			return projects;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public List<CommonSelectItem> getPlanTables(RestServiceParam param, String envId, String companyCode)
	{
		try {
			// TODO: +++ Security

			Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
			Environment env = environments.get(envId);

			if (env == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			EntireTableFilterContext entireTableFilterContext = new EntireTableFilterContext(env, companyCode, null);
			Map<String, String> tables = new TreeMap<>(entireTableFilterContext.get_tableDDLNames());
			String key = null;

			List<CommonSelectItem> tableList = new ArrayList<>();
			Set<Map.Entry<String, String>> entries = tables.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				key = entry.getKey();
				CommonSelectItem rule = new CommonSelectItem();
				tableList.add(rule);
				rule.setCoreValue(key);
				rule.setDisplayValue(key + "-" + entry.getValue());
			}

			return tableList;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public List<CommonSelectItem> getChangesEnvironments(RestServiceParam param)
	{
		try {
			// For reference, see method fetchEnvironments() in class
			// com.csc.fsg.life.pw.client.mdi.ChangesOnly in the old
			// Product Wizard.

			// Only environemnts the user is authorized to access will be returned
			List<CommonSelectItem> allEnvironments = getCommonEnvironments(param);
			String refEnvId = allEnvironments.get(0).getCoreValue();
			Environment refEnvironment = EnvironmentManager.getInstance().getEnvironment(refEnvId);

			Map<String, CommonSelectItem> envMap = new HashMap<>();
			for (CommonSelectItem environment : allEnvironments)
				envMap.put(environment.getCoreValue(), environment);

			PackageBean packageHelper = new PackageBean(refEnvironment, null, null);
			packageHelper.setSource(Constants.WIP);
			packageHelper.setProcess(Constants.GET_ENVIRONMENTS);
			packageHelper.setChangesOnly(null);

			String valueString = packageHelper.getInitialValues();
			String[] values = valueString.split("\t");

			List<CommonSelectItem> environmentsWithChange = new ArrayList<>();
			for (String value : values) {
				if (value != null) {
					String trimmedValue = value.trim();
					CommonSelectItem env = envMap.get(trimmedValue);
					if (env != null)
						environmentsWithChange.add(env);
				}
			}

			return environmentsWithChange;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public ChangesFilterData getChangesFilterValues(RestServiceParam param, String envId)
	{
		try {
			// For reference, see method fillLists(String) in class
			// com.csc.fsg.life.pw.client.mdi.ChangesOnly in the old
			// Product Wizard.

			// TODO: +++ Security

			Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
			if (env == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PackageBean packageHelper = new PackageBean(env, null, null);
			packageHelper.setSource(Constants.WIP);
			packageHelper.setProcess(Constants.GET_FILTER_INFO);
			packageHelper.setChangesOnly("ChangesOnly");

			String valueString = packageHelper.getInitialValues();
			String[] values = valueString.split("\t");
			ChangesFilterData filterData = new ChangesFilterData();

			Set<String> projectSet = new HashSet<>();
			Set<String> userSet = new HashSet<>();

			for (String value : values) {
				int index = -1;

				if ((index = value.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
					String project = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (project.length() > 0)
						projectSet.add(project);
				}

				if ((index = value.indexOf(RCMClientUtilities.TAB_DELIM)) != -1) {
					String displayValue = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (displayValue.length() > 0) {
						String coreValue = displayValue.substring(0, displayValue.indexOf("-")).trim();
						CommonSelectItem item = new CommonSelectItem();
						item.setCoreValue(coreValue);
						item.setDisplayValue(displayValue);
						filterData.addBusinessRuleTablesItem(item);
					}
				}

				if ((index = value.indexOf(RCMClientUtilities.USR_DELIM)) != -1) {
					String user = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (user.length() > 0)
						userSet.add(user);
				}
			}

			filterData.setProjects(new ArrayList<>(projectSet));
			filterData.setUsers(new ArrayList<>(userSet));
			return filterData;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public ApplyFilterData getApplyFilterValues(RestServiceParam param, String envId)
	{
		try {
			// For reference, see method populateFilters() in class
			// com.csc.fsg.life.pw.client.mdi.PackageFilter in the old
			// Product Wizard.

			// TODO: +++ Security

			Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
			if (env == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PackageBean packageHelper = new PackageBean(env, null, null);
			packageHelper.setSource(Constants.WIP);
			packageHelper.setProcess(Constants.GET_FILTER_INFO);
			packageHelper.setChangesOnly(null);

			String valueString = packageHelper.getInitialValues();
			String[] values = valueString.split("\t");
			ApplyFilterData filterData = new ApplyFilterData();

			Set<String> packageSet = new HashSet<>();
			Set<String> projectSet = new HashSet<>();

			for (String value : values) {
				int index = -1;

				if ((index = value.indexOf(RCMClientUtilities.PKG_DELIM)) != -1) {
					String pkg = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (pkg.length() > 0)
						packageSet.add(pkg);
				}

				if ((index = value.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
					String project = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (project.length() > 0)
						projectSet.add(project);
				}
			}

			filterData.setPackages(new ArrayList<>(packageSet));
			filterData.setProjects(new ArrayList<>(projectSet));
			return filterData;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public AuditFilterData getAuditFilterValues(RestServiceParam param, String filterAspect, String envId)
	{
		try {
			// For reference, see method populateFilters() in class
			// com.csc.fsg.life.pw.client.mdi.AuditErrorFilter in the old
			// Product Wizard.

			// TODO: +++ Security

			if (!"A".equals(filterAspect) && !"E".equals(filterAspect)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_filter_aspect"));
				throw new BadRequestException(model);
			}

			Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
			if (env == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PackageBean packageHelper = new PackageBean(env, null, null);

			if ("A".equals(filterAspect))
				packageHelper.setSource(Constants.AUDIT_VIEW);
			else
				packageHelper.setSource(AuditErrorFilter.ERROR);

			packageHelper.setProcess(Constants.GET_FILTER_INFO);
			packageHelper.setChangesOnly(null);
			String valueString = packageHelper.getInitialValues();
			String[] values = valueString.split("\t");
			AuditFilterData filterData = new AuditFilterData();

			Set<String> packageSet = new HashSet<>();
			Set<String> projectSet = new HashSet<>();
			Set<String> userSet = new HashSet<>();

			for (String value : values) {
				int index = -1;

				if ((index = value.indexOf(RCMClientUtilities.PKG_DELIM)) != -1) {
					String pkg = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (pkg.length() > 0)
						packageSet.add(pkg);
				}

				if ((index = value.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
					String project = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (project.length() > 0)
						projectSet.add(project);
				}

				if ((index = value.indexOf(RCMClientUtilities.TAB_DELIM)) != -1) {
					String displayValue = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (displayValue.length() > 0) {
						String coreValue = displayValue.substring(0, displayValue.indexOf("-")).trim();
						CommonSelectItem item = new CommonSelectItem();
						item.setCoreValue(coreValue);
						item.setDisplayValue(displayValue);
						filterData.addBusinessRuleTablesItem(item);
					}
				}

				if ((index = value.indexOf(RCMClientUtilities.USR_DELIM)) != -1) {
					String user = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (user.length() > 0)
						userSet.add(user);
				}
			}

			filterData.setPackages(new ArrayList<>(packageSet));
			filterData.setProjects(new ArrayList<>(projectSet));
			filterData.setUsers(new ArrayList<>(userSet));
			return filterData;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public PromoteFilterData getPromoteFilterValues(RestServiceParam param, String sourceEnvId, String targetEnvId)
	{
		try {
			// For reference, see method populateFilters() in class
			// com.csc.fsg.life.pw.client.mdi.AuditErrorFilter in the old
			// Product Wizard.

			// TODO: +++ Security

			Environment sourceEnv = EnvironmentManager.getInstance().getEnvironment(sourceEnvId);
			Environment targetEnv = EnvironmentManager.getInstance().getEnvironment(targetEnvId);
			if (sourceEnv == null || targetEnv == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			if (sourceEnvId.trim().equalsIgnoreCase(targetEnvId.trim())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("env_not_unique"));
				throw new BadRequestException(model);
			}

			PackageBean packageHelper = new PackageBean(sourceEnv, targetEnv, null);
			packageHelper.setSource(Constants.AUDIT);
			packageHelper.setProcess(Constants.GET_FILTER_INFO);
			packageHelper.setChangesOnly(null);
			String valueString = packageHelper.getInitialValues();
			String[] values = valueString.split("\t");
			PromoteFilterData filterData = new PromoteFilterData();

			Set<String> packageSet = new HashSet<>();
			Set<String> projectSet = new HashSet<>();

			for (String value : values) {
				int index = -1;

				if ((index = value.indexOf(RCMClientUtilities.PKG_DELIM)) != -1) {
					String pkg = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (pkg.length() > 0)
						packageSet.add(pkg);
				}

				if ((index = value.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
					String project = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (project.length() > 0)
						projectSet.add(project);
				}
			}

			filterData.setPackages(new ArrayList<>(packageSet));
			filterData.setProjects(new ArrayList<>(projectSet));
			return filterData;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public List<CommonSelectItem> getSummaryCommonValues(RestServiceParam param, SummarySearchInput searchInput)
	{
		try {
			// For reference, see method getNextPlanKey() in class
			// com.csc.fsg.life.pw.client.mdi.SummaryFilter in the old
			// Product Wizard.

			// TODO: +++ Security

			String filterAspect = searchInput.getFilterAspect();
			if (!"C".equals(filterAspect) && !"S".equals(filterAspect)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_filter_aspect"));
				throw new BadRequestException(model);
			}

			String env = searchInput.getEnvId();
			boolean isGoodEnvironment = false;

			if (StringUtils.hasText(env)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(env) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildSummaryCriteria(searchInput);
			boolean isBrccFilter = "C".equals(searchInput.getFilterAspect());
			SummaryFilterSpecContext context = new SummaryFilterSpecContext(isBrccFilter, planCriteria.getEnvironment());
			Map<String, String> commonValuesMap = null;

			if (!StringUtils.hasText(searchInput.getCompanyCode()))
				commonValuesMap = context.getCompanyCodes(planCriteria);
			else if (!StringUtils.hasText(searchInput.getProductCode()))
				commonValuesMap = context.getProductCodes(planCriteria);
			else if (!StringUtils.hasText(searchInput.getPlanCode()))
				commonValuesMap = context.getPlanCodes(planCriteria);
			else if (!StringUtils.hasText(searchInput.getIssueState()))
				commonValuesMap = context.getIssueStates(planCriteria);
			else if (!StringUtils.hasText(searchInput.getLob()))
				commonValuesMap = context.getLinesOfBusiness(planCriteria);
			else {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("unknown_search_type"));
				throw new BadRequestException(model);
			}

			List<CommonSelectItem> commonValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : commonValuesMap.entrySet()) {
				CommonSelectItem item = new CommonSelectItem();
				commonValues.add(item);
				item.setCoreValue(entry.getKey());
				item.setDisplayValue(entry.getValue());
			}

			return commonValues;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	public List<DateSelectItem> getSummaryEffDates(RestServiceParam param, SummarySearchInput searchInput)
	{
		try {
			// For reference, see method getNextPlanKey() in class
			// com.csc.fsg.life.pw.client.mdi.SummaryFilter in the old
			// Product Wizard.

			// TODO: +++ Security

			String filterAspect = searchInput.getFilterAspect();
			if (!"C".equals(filterAspect) && !"S".equals(filterAspect)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_filter_aspect"));
				throw new BadRequestException(model);
			}

			String env = searchInput.getEnvId();
			boolean isGoodEnvironment = false;

			if (StringUtils.hasText(env)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(env) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildSummaryCriteria(searchInput);
			boolean isBrccFilter = "C".equals(searchInput.getFilterAspect());
			SummaryFilterSpecContext context = new SummaryFilterSpecContext(isBrccFilter, planCriteria.getEnvironment());
			Map<String, String> dateValuesMap = null;

			if (!StringUtils.hasText(searchInput.getCompanyCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_company"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getProductCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_product"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getPlanCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_plan"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getIssueState())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_state"));
				throw new BadRequestException(model);
			}
			else if (!StringUtils.hasText(searchInput.getLob())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_lob"));
				throw new BadRequestException(model);
			}
			else {
				dateValuesMap = context.getEffectiveDates(planCriteria);
			}

			List<DateSelectItem> dateValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : dateValuesMap.entrySet()) {
				DateSelectItem item = new DateSelectItem();
				dateValues.add(item);

				Date date = Date.valueOf(entry.getKey());
				LocalDate localDate = new LocalDate(date.getTime());
				item.setCoreValue(localDate);
				item.setDisplayValue(entry.getValue());
			}

			return dateValues;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	private PlanCriteriaTO buildSummaryCriteria(SummarySearchInput searchInput)
	{
		HashMap<String, Object> planKeys = new HashMap<>();

		planKeys.put(PlanTOBase.ENVIRONMENT_KEY, searchInput.getEnvId());
		planKeys.put(PlanTOBase.COMPANY_CODE_KEY, searchInput.getCompanyCode());

		String productCode = searchInput.getProductCode();
		planKeys.put(PlanTOBase.PRODUCT_CODE_KEY, productCode);

		if (StringUtils.hasText(productCode)) {
			planKeys.put(PlanTOBase.PRODUCT_PREFIX_KEY, productCode.substring(0, 1));
			if (productCode.length() > 1)
				planKeys.put(PlanTOBase.PRODUCT_SUFFIX_KEY, productCode.substring(1, 2));
		}

		planKeys.put(PlanTOBase.PLAN_CODE_KEY, searchInput.getPlanCode());
		planKeys.put(PlanTOBase.ISSUE_STATE_KEY, searchInput.getIssueState());
		planKeys.put(PlanTOBase.LINE_OF_BUSINESS_KEY, searchInput.getLob());

		PlanCriteriaTO planCriteria = new PlanCriteriaTO(planKeys);
		return planCriteria;
	}
}
