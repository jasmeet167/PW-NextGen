package com.csc.fsg.life.rest.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.csc.fsg.life.rest.exception.ForbiddenException;
import com.csc.fsg.life.rest.exception.NotFoundException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.ApplyFilterData;
import com.csc.fsg.life.rest.model.AuditFilterData;
import com.csc.fsg.life.rest.model.ChangesFilterData;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.model.PromoteFilterData;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.model.SummarySearchInput;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class SearchServiceImpl
	extends RestServiceImpl
	implements SearchService
{
	@Autowired
	private SecurityService securityService = null;

	public SearchServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.SearchService");
	}

	public List<SelectItem> getCommonEnvironments(RestServiceParam param)
	{
		try {
			CommonApplicationConfigBean pwConfig = getBean(CommonApplicationConfigBean.class);
			Map<String, MyBatisApplicationEnvironmentBean> environmentMap = pwConfig.getEnvironments();

			if (environmentMap.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

			List<SelectItem> envList = new ArrayList<>();
			for (MyBatisApplicationEnvironmentBean envBean : environmentMap.values()) {
				SelectItem env = new SelectItem();
				envList.add(env);
				env.setValue(envBean.getName());
				env.setLabel(envBean.getDisplayName());
			}

			String authToken = param.getAuthToken();
			List<SelectItem> response = securityService.filterAuthorizedEnvironments(authToken, envList);
			if (response.isEmpty()) {
				if (response.isEmpty()) {
					HttpStatus status = ForbiddenException.HTTP_STATUS;
					ErrorModel model = errorModelFactory.newErrorModel(status, getMessage("no_env_auth"));
					throw new ForbiddenException(model);
				}
			}

			return response;
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

	public List<SelectItem> getPlanCommonValues(RestServiceParam param, PlanSearchInput searchInput)
	{
		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();

			boolean isGoodEnvironment = false;
			if (StringUtils.hasText(envId)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(envId) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildPlanCriteria(envId, companyCode, searchInput);
			PlanFilterSpecContext context = new PlanFilterSpecContext(envId);
			Map<String, String> commonValuesMap = null;

			boolean isCoreValueShown = true;
			boolean isCompanyCodesSearch = false;
			if (!StringUtils.hasText(param.getCompanyCode())) {
				isCompanyCodesSearch = true;
				commonValuesMap = context.getCompanyCodes(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getProductCode())) {
				commonValuesMap = context.getProductCodes(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getPlanCode())) {
				isCoreValueShown = false;
				commonValuesMap = context.getPlanCodes(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getIssueState())) {
				commonValuesMap = context.getIssueStates(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getLob())) {
				commonValuesMap = context.getLinesOfBusiness(planCriteria);
			}
			else {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("unknown_search_type"));
				throw new BadRequestException(model);
			}

			if (commonValuesMap.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

			List<SelectItem> commonValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : commonValuesMap.entrySet()) {
				SelectItem item = new SelectItem();
				commonValues.add(item);
				item.setValue(entry.getKey());

				if (isCoreValueShown)
					item.setLabel(entry.getKey() + '-' + entry.getValue());
				else
					item.setLabel(entry.getValue());
			}

			if (isCompanyCodesSearch) {
				String authToken = param.getAuthToken();
				List<SelectItem> response = securityService.filterAuthorizedCompanies(authToken, envId, commonValues);
				if (response.isEmpty()) {
					HttpStatus status = ForbiddenException.HTTP_STATUS;
					ErrorModel model = errorModelFactory.newErrorModel(status, getMessage("no_company_auth"));
					throw new ForbiddenException(model);
				}

				return response;
			}
			else {
				return commonValues;
			}
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

	public List<SelectItem> getPlanEffDates(RestServiceParam param, PlanSearchInput searchInput)
	{
		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();

			boolean isGoodEnvironment = false;
			if (StringUtils.hasText(envId)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(envId) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildPlanCriteria(envId, companyCode, searchInput);
			PlanFilterSpecContext context = new PlanFilterSpecContext(envId);
			Map<String, String> dateValuesMap = null;

			if (!StringUtils.hasText(param.getCompanyCode())) {
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

			List<SelectItem> dateValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : dateValuesMap.entrySet()) {
				SelectItem item = new SelectItem();
				dateValues.add(item);

				Date date = Date.valueOf(entry.getKey());
				LocalDate localDate = new LocalDate(date.getTime());
				item.setValue(localDate);
				item.setLabel(entry.getValue());
			}

			if (dateValues.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
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

	public List<SelectItem> getPlanProjects(RestServiceParam param)
	{
		try {
			String envId = param.getEnvId();

			WIPProperties wipProps = WIPProperties.getInstance();
			Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
			if (env == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			List<String> rawProjects = WIPRows.getDistinctItemsFromWIP(env, wipProps.getProjectName(), false);
			List<SelectItem> projects = new ArrayList<>();
			if (rawProjects != null) {
				for (String rawProject : rawProjects) {
					if (rawProject != null) {
						String trimProject = rawProject.trim();
						SelectItem item = new SelectItem();
						item.setValue(trimProject);
						item.setLabel(trimProject);
						projects.add(item);
					}
				}
			}

			if (projects.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

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

	public List<SelectItem> getPlanTables(RestServiceParam param)
	{
		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();

			Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
			Environment env = environments.get(envId);

			if (env == null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			EntireTableFilterContext entireTableFilterContext = new EntireTableFilterContext(env, companyCode, null);
			Map<String, String> tables = entireTableFilterContext.get_tableDDLNames();

			if (tables.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

			List<SelectItem> allTables = new ArrayList<>();
			String key = null;

			Set<Map.Entry<String, String>> entries = tables.entrySet();
			for (Map.Entry<String, String> entry : entries) {
				key = entry.getKey();
				SelectItem rule = new SelectItem();
				allTables.add(rule);
				rule.setValue(key);
				rule.setLabel(key + "-" + entry.getValue());
			}

			String authToken = param.getAuthToken();
			List<SelectItem> response = securityService.filterAuthorizedTables(authToken, envId, companyCode, allTables);
			if (response.isEmpty())
				throw new ForbiddenException();

			return response;
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

	public List<SelectItem> getChangesEnvironments(RestServiceParam param)
	{
		try {
			// For reference, see method fetchEnvironments() in class
			// com.csc.fsg.life.pw.client.mdi.ChangesOnly in the old
			// Product Wizard.

			// Only environemnts the user is authorized to access will be returned
			List<SelectItem> allEnvironments = getCommonEnvironments(param);
			Object refEnvId = allEnvironments.get(0).getValue();
			Environment refEnvironment = EnvironmentManager.getInstance().getEnvironment((String) refEnvId);

			Map<Object, SelectItem> envMap = new HashMap<>();
			for (SelectItem environment : allEnvironments)
				envMap.put(environment.getValue(), environment);

			PackageBean packageHelper = new PackageBean(refEnvironment, null, null);
			packageHelper.setSource(Constants.WIP);
			packageHelper.setProcess(Constants.GET_ENVIRONMENTS);
			packageHelper.setChangesOnly(null);

			String valueString = packageHelper.getInitialValues();
			String[] values = valueString.split("\t");

			List<SelectItem> envList = new ArrayList<>();
			for (String value : values) {
				if (value != null) {
					String trimmedValue = value.trim();
					SelectItem env = envMap.get(trimmedValue);
					if (env != null)
						envList.add(env);
				}
			}

			if (envList.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
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

	public ChangesFilterData getChangesFilterValues(RestServiceParam param)
	{
		try {
			// For reference, see method fillLists(String) in class
			// com.csc.fsg.life.pw.client.mdi.ChangesOnly in the old
			// Product Wizard.

			String envId = param.getEnvId();

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
						String coreValue = displayValue;
						int delimiterIdx = displayValue.indexOf("-");
						if (delimiterIdx > 0)
							coreValue = displayValue.substring(0, displayValue.indexOf("-")).trim();

						SelectItem item = new SelectItem();
						item.setValue(coreValue);
						item.setLabel(displayValue);
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

			if (filterData.getProjects().isEmpty()
			 && filterData.getBusinessRuleTables().isEmpty()
			 && filterData.getUsers().isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

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

	public ApplyFilterData getApplyFilterValues(RestServiceParam param)
	{
		try {
			// For reference, see method populateFilters() in class
			// com.csc.fsg.life.pw.client.mdi.PackageFilter in the old
			// Product Wizard.

			String envId = param.getEnvId();

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

			Set<String> projectSet = new HashSet<>();

			for (String value : values) {
				int index = -1;

				if ((index = value.indexOf(RCMClientUtilities.PKG_DELIM)) != -1) {
					String displayValue = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (displayValue.length() > 0) {
						String coreValue = displayValue;
						int delimiterIdx = displayValue.indexOf("-");
						if (delimiterIdx > 0)
							coreValue = displayValue.substring(0, displayValue.indexOf("-")).trim();

						SelectItem item = new SelectItem();
						item.setValue(coreValue);
						item.setLabel(displayValue);
						filterData.addPackagesItem(item);
					}
				}

				if ((index = value.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
					String project = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (project.length() > 0)
						projectSet.add(project);
				}
			}

			filterData.setProjects(new ArrayList<>(projectSet));

			if (filterData.getPackages().isEmpty()
			 && filterData.getProjects().isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

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

	public AuditFilterData getAuditFilterValues(RestServiceParam param, String filterAspect)
	{
		try {
			// For reference, see method populateFilters() in class
			// com.csc.fsg.life.pw.client.mdi.AuditErrorFilter in the old
			// Product Wizard.

			String envId = param.getEnvId();

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

			Set<String> projectSet = new HashSet<>();
			Set<String> userSet = new HashSet<>();

			for (String value : values) {
				int index = -1;

				if ((index = value.indexOf(RCMClientUtilities.PKG_DELIM)) != -1) {
					String displayValue = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (displayValue.length() > 0) {
						String coreValue = displayValue;
						int delimiterIdx = displayValue.indexOf("-");
						if (delimiterIdx > 0)
							coreValue = displayValue.substring(0, displayValue.indexOf("-")).trim();

						SelectItem item = new SelectItem();
						item.setValue(coreValue);
						item.setLabel(displayValue);
						filterData.addPackagesItem(item);
					}
				}

				if ((index = value.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
					String project = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (project.length() > 0)
						projectSet.add(project);
				}

				if ((index = value.indexOf(RCMClientUtilities.TAB_DELIM)) != -1) {
					String displayValue = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (displayValue.length() > 0) {
						String coreValue = displayValue;
						int delimiterIdx = displayValue.indexOf("-");
						if (delimiterIdx > 0)
							coreValue = displayValue.substring(0, displayValue.indexOf("-")).trim();

						SelectItem item = new SelectItem();
						item.setValue(coreValue);
						item.setLabel(displayValue);
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

			if (filterData.getPackages().isEmpty()
			 && filterData.getProjects().isEmpty()
			 && filterData.getBusinessRuleTables().isEmpty()
			 && filterData.getUsers().isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

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

	public PromoteFilterData getPromoteFilterValues(RestServiceParam param)
	{
		try {
			// For reference, see method populateFilters() in class
			// com.csc.fsg.life.pw.client.mdi.AuditErrorFilter in the old
			// Product Wizard.

			String sourceEnvId = param.getSourceEnvId();
			String targetEnvId = param.getTargetEnvId();

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

			Set<String> projectSet = new HashSet<>();

			for (String value : values) {
				int index = -1;

				if ((index = value.indexOf(RCMClientUtilities.PKG_DELIM)) != -1) {
					String displayValue = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (displayValue.length() > 0) {
						String coreValue = displayValue;
						int delimiterIdx = displayValue.indexOf("-");
						if (delimiterIdx > 0)
							coreValue = displayValue.substring(0, displayValue.indexOf("-")).trim();

						SelectItem item = new SelectItem();
						item.setValue(coreValue);
						item.setLabel(displayValue);
						filterData.addPackagesItem(item);
					}
				}

				if ((index = value.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
					String project = value.substring(index + RCMClientUtilities.DELIM_LENGTH).trim();
					if (project.length() > 0)
						projectSet.add(project);
				}
			}

			filterData.setProjects(new ArrayList<>(projectSet));

			if (filterData.getPackages().isEmpty()
			 && filterData.getProjects().isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

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

	public List<SelectItem> getSummaryCommonValues(RestServiceParam param, SummarySearchInput searchInput)
	{
		try {
			// For reference, see method getNextPlanKey() in class
			// com.csc.fsg.life.pw.client.mdi.SummaryFilter in the old
			// Product Wizard.

			String filterAspect = searchInput.getFilterAspect();
			if (!"C".equals(filterAspect) && !"S".equals(filterAspect)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_filter_aspect"));
				throw new BadRequestException(model);
			}

			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();

			boolean isGoodEnvironment = false;
			if (StringUtils.hasText(envId)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(envId) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildSummaryCriteria(envId, companyCode, searchInput);
			boolean isBrccFilter = "C".equals(searchInput.getFilterAspect());
			SummaryFilterSpecContext context = new SummaryFilterSpecContext(isBrccFilter, planCriteria.getEnvironment());
			Map<String, String> commonValuesMap = null;

			boolean isCoreValueShown = true;
			boolean isCompanyCodesSearch = false;
			if (!StringUtils.hasText(param.getCompanyCode())) {
				isCompanyCodesSearch = true;
				commonValuesMap = context.getCompanyCodes(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getProductCode())) {
				commonValuesMap = context.getProductCodes(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getPlanCode())) {
				isCoreValueShown = false;
				commonValuesMap = context.getPlanCodes(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getIssueState())) {
				commonValuesMap = context.getIssueStates(planCriteria);
			}
			else if (!StringUtils.hasText(searchInput.getLob())) {
				commonValuesMap = context.getLinesOfBusiness(planCriteria);
			}
			else {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("unknown_search_type"));
				throw new BadRequestException(model);
			}

			if (commonValuesMap.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
			}

			List<SelectItem> commonValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : commonValuesMap.entrySet()) {
				SelectItem item = new SelectItem();
				commonValues.add(item);
				item.setValue(entry.getKey());

				if (isCoreValueShown)
					item.setLabel(entry.getKey() + '-' + entry.getValue());
				else
					item.setLabel(entry.getValue());
			}

			if (isCompanyCodesSearch) {
				String authToken = param.getAuthToken();
				List<SelectItem> response = securityService.filterAuthorizedCompanies(authToken, envId, commonValues);
				if (response.isEmpty())
					throw new ForbiddenException();

				return response;
			}
			else {
				return commonValues;
			}
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

	public List<SelectItem> getSummaryEffDates(RestServiceParam param, SummarySearchInput searchInput)
	{
		try {
			// For reference, see method getNextPlanKey() in class
			// com.csc.fsg.life.pw.client.mdi.SummaryFilter in the old
			// Product Wizard.

			String filterAspect = searchInput.getFilterAspect();
			if (!"C".equals(filterAspect) && !"S".equals(filterAspect)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_filter_aspect"));
				throw new BadRequestException(model);
			}

			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();

			boolean isGoodEnvironment = false;
			if (StringUtils.hasText(envId)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(envId) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildSummaryCriteria(envId, companyCode, searchInput);
			boolean isBrccFilter = "C".equals(searchInput.getFilterAspect());
			SummaryFilterSpecContext context = new SummaryFilterSpecContext(isBrccFilter, planCriteria.getEnvironment());
			Map<String, String> dateValuesMap = null;

			if (!StringUtils.hasText(param.getCompanyCode())) {
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

			List<SelectItem> dateValues = new ArrayList<>();
			for (Map.Entry<String, String> entry : dateValuesMap.entrySet()) {
				SelectItem item = new SelectItem();
				dateValues.add(item);

				Date date = Date.valueOf(entry.getKey());
				LocalDate localDate = new LocalDate(date.getTime());
				item.setValue(localDate);
				item.setLabel(entry.getValue());
			}

			if (dateValues.isEmpty()) {
				HttpStatus status = NotFoundException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("no_matching_data"));
				throw new NotFoundException(model);
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

	private PlanCriteriaTO buildPlanCriteria(String envId, String companyCode, PlanSearchInput searchInput)
	{
		HashMap<String, Object> planKeys = new HashMap<>();
		if (searchInput.areChangesIncluded() == null)
			planKeys.put(PlanCriteriaTO.MERGED_VIEW_KEY, Boolean.FALSE.toString());
		else
			planKeys.put(PlanCriteriaTO.MERGED_VIEW_KEY, searchInput.areChangesIncluded().toString());

		planKeys.put(PlanTOBase.ENVIRONMENT_KEY, envId);
		planKeys.put(PlanTOBase.COMPANY_CODE_KEY, companyCode);

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

	private PlanCriteriaTO buildSummaryCriteria(String envId, String companyCode, SummarySearchInput searchInput)
	{
		HashMap<String, Object> planKeys = new HashMap<>();

		planKeys.put(PlanTOBase.ENVIRONMENT_KEY, envId);
		planKeys.put(PlanTOBase.COMPANY_CODE_KEY, companyCode);

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
