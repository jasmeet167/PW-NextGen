package com.csc.fsg.life.rest.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTOBase;
import com.csc.fsg.life.pw.web.actions.table.ITableManager;
import com.csc.fsg.life.pw.web.actions.table.TableManagerFactory;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.rest.exception.BadRequestException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.TableModel;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class BusinessRulesTableServiceImpl
	extends RestServiceImpl
	implements BusinessRulesTableService
{
	static private final String FILTER_TYPE_PLAN = "plan";

	public BusinessRulesTableServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.BusinessRulesTableService");
	}

	public TableModel getBusinessRulesTable(RestServiceParam param, boolean viewChanges, String productPrefix, String tableName, String tableSubset,
											TreeNodePlanKey planKey, List<String> projects)
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

			if (!StringUtils.hasText(companyCode)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_company"));
				throw new BadRequestException(model);
			}

			// For reference, see method process() in class
			// com.csc.fsg.life.pw.web.actions.TableFilterAction in the old
			// Product Wizard.
			String viewType = viewChanges ? "with" : "without";
			ITableManager tm = TableManagerFactory.getTableManager(FILTER_TYPE_PLAN, viewType);

			HashMap<String, String> req = new HashMap<>();
			req.put("filter", FILTER_TYPE_PLAN);
			req.put("environment", envId);
			req.put("company_code", companyCode);
			req.put("product_prefix", productPrefix);
			req.put("subset", tableSubset);
			req.put("table", tableName);
			// TODO: PROJECTS
			if (planKey != null)
				req.put("plan_key", buildStringPlanKey(param, planKey));

			StringBuffer payload = tm.getData(req, 0);
			System.out.println(payload);

			return null;
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

	private String buildStringPlanKey(RestServiceParam param, TreeNodePlanKey planKey)
	{
		if (planKey == null)
			return null;

		HashMap<String, String> keyMap = new HashMap<>();
		keyMap.put(PlanTOBase.ENVIRONMENT_KEY, param.getEnvId());
		keyMap.put(PlanTOBase.COMPANY_CODE_KEY, param.getCompanyCode());
		keyMap.put(PlanTOBase.PRODUCT_PREFIX_KEY, planKey.getProductPrefix());
		keyMap.put(PlanTOBase.PRODUCT_SUFFIX_KEY, planKey.getProductSuffix());
		keyMap.put(PlanTOBase.PLAN_CODE_KEY, planKey.getPlanCode());
		keyMap.put(PlanTOBase.ISSUE_STATE_KEY, planKey.getIssueState());
		keyMap.put(PlanTOBase.LINE_OF_BUSINESS_KEY, planKey.getLob());
		keyMap.put(PlanTOBase.EFFECTIVE_DATE_KEY, planKey.getEffDate().toString());
		keyMap.put(PlanTOBase.PLAN_TYPE_KEY, planKey.getPlanType());

		PlanTO planTo = new PlanTO(keyMap);
		return planTo.toString();
	}
}
