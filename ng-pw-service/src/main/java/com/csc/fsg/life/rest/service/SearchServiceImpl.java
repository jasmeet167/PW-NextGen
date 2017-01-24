package com.csc.fsg.life.rest.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.common.config.MyBatisApplicationEnvironmentBean;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTOBase;
import com.csc.fsg.life.pw.web.io.PlanFilterSpecContext;
import com.csc.fsg.life.rest.exception.BadRequestException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.CommonSelectItem;
import com.csc.fsg.life.rest.model.DateSelectItem;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.ErrorModelFactory;
import com.csc.fsg.life.rest.model.PlanSearchInput;
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

	public List<CommonSelectItem> getEnvironments(RestServiceParam param)
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
			String env = searchInput.getEnvironment();
			if (!StringUtils.hasText(env)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			PlanCriteriaTO planCriteria = buildPlanCriteria(searchInput);
			PlanFilterSpecContext context = new PlanFilterSpecContext(env);
			Map<String, String> commonValuesMap = null;

			// TODO: +++ Security
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
			else if (searchInput.getEffDate() != null) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("eff_date_not_allowed"));
				throw new BadRequestException(model);
			}
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

	public List<DateSelectItem> getPlanDateValues(RestServiceParam param, PlanSearchInput searchInput)
	{
		try {
			String env = searchInput.getEnvironment();
			if (!StringUtils.hasText(env)) {
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
			else if (searchInput.getEffDate() == null) {
				dateValuesMap = context.getEffectiveDates(planCriteria);
			}
			else {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("unknown_search_type"));
				throw new BadRequestException(model);
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
		planKeys.put(PlanTOBase.ENVIRONMENT_KEY, searchInput.getEnvironment());
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
