package com.csc.fsg.life.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.csc.fsg.life.pw.web.actions.table.ITableManager;
import com.csc.fsg.life.pw.web.actions.table.NoSuchTableException;
import com.csc.fsg.life.pw.web.actions.table.TableManagerFactory;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.EntireTableFilterContext;
import com.csc.fsg.life.pw.web.io.Row;
import com.csc.fsg.life.pw.web.io.TableDescriptor;
import com.csc.fsg.life.pw.web.io.TableDescriptorManager;
import com.csc.fsg.life.rest.exception.NotFoundException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.BusinessRule;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.ErrorModelFactory;
import com.csc.fsg.life.rest.model.TableModel;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class BusinessRuleServiceImpl
	extends RestServiceImpl
	implements BusinessRuleService
{
	private Log log = LogFactory.getLog("com.csc.fsg");

	@Inject
	protected ErrorModelFactory errorModelFactory = null;

	public BusinessRuleServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.BusinessRuleServiceImpl");
	}

	public List<BusinessRule> getBusinessRules(RestServiceParam param, String env, String company)
	{
		try {
			env = env.trim().toUpperCase();
			company = company.trim().toUpperCase();

			Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
			Environment environment = environments.get(env);

			if (environment == null) {
				log.error(getMessage("environment_not_found_LONG", env));
				ErrorModel model = errorModelFactory.newErrorModel(NotFoundException.HTTP_STATUS);
				model.setMessage(getMessage("environment_not_found_SHORT", env));
				model.setFields(Arrays.asList(new String[] { "env" }));
				throw new NotFoundException(model);
			}
			else {
				EntireTableFilterContext entireTableFilterContext = new EntireTableFilterContext(environment, company, null);
				Map<String, String> tables = new TreeMap<>(entireTableFilterContext.get_tableDDLNames());
				String key = null;

				List<BusinessRule> rules = new ArrayList<>();
				Set<Map.Entry<String, String>> entries = tables.entrySet();
				for (Map.Entry<String, String> entry : entries) {
					key = entry.getKey();
					BusinessRule rule = new BusinessRule();
					rules.add(rule);
					rule.setTableId(key);
					rule.setTableName(key + "-" + entry.getValue());
				}

				return rules;
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

	public TableModel getBusinessRuleModel(RestServiceParam param, String rule, String env, String company)
	{
		final String filterType = "ENTIRE_TABLE";
		final String viewType = "without";
		final int highestAuthority = 0;

		try {
			rule = rule.trim().toUpperCase();
			env = env.trim().toUpperCase();
			company = company.trim().toUpperCase();

			Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
			Environment environment = environments.get(env);

			if (environment == null) {
				log.error(getMessage("environment_not_found_LONG", env));
				ErrorModel model = errorModelFactory.newErrorModel(NotFoundException.HTTP_STATUS);
				model.setMessage(getMessage("environment_not_found_SHORT", env));
				model.setFields(Arrays.asList(new String[] { "env" }));
				throw new NotFoundException(model);
			}
			else {
				HashMap<String, Object> filterParameters = new HashMap<>();
				filterParameters.put("environment", env);
				filterParameters.put("company_code", company);
				filterParameters.put("product_prefix", null);
				filterParameters.put("subset", null);
				filterParameters.put("table", rule);
				filterParameters.put("PAGING_MODE", null);
				filterParameters.put("PAGING_KEY", null);
				filterParameters.put("LOCATOR", null);
				filterParameters.put("userCondition", null);
				filterParameters.put("filter", filterType);

				TableModel tableModel = new TableModel();
				List<Object> rows = tableModel.getRows();
				ITableManager tm = TableManagerFactory.getTableManager(filterType, viewType);

				try {
					List<Row> fetchedRows = tm.getData(filterParameters, highestAuthority);
					for (Row row : fetchedRows)
						rows.add(row);
				}
				catch (NoSuchTableException e) {
					log.error(getMessage("no_such_table_LONG", rule));
					ErrorModel model = errorModelFactory.newErrorModel(NotFoundException.HTTP_STATUS);
					model.setMessage(getMessage("no_such_table_SHORT", rule));
					model.setFields(Arrays.asList("env"));
					throw new NotFoundException(model);
				}

				buildColumnHeaders(rule, tableModel.getColumnHeaders());
				return tableModel;
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

	private void buildColumnHeaders(String tableId, List<String> columnHeaders)
	{
		TableDescriptorManager descManager = TableDescriptorManager.getInstance(null);
		TableDescriptor descriptor = descManager.getTableDescriptor(TableDescriptorManager.getTableId(tableId));

		for (int i = 0, n = descriptor.getColumnDescriptors().size(); i < n; i++) {
			String columnHeader = descriptor.getColumn(i + 1).getDbColumnName();
			columnHeaders.add(columnHeader);
		}
	}
}
