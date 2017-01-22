package com.csc.fsg.life.rest.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;
import org.springframework.stereotype.Service;

import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.common.config.MyBatisApplicationEnvironmentBean;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.io.FieldNames;
import com.csc.fsg.life.pw.web.io.Row;
import com.csc.fsg.life.pw.web.io.TableDescriptor;
import com.csc.fsg.life.pw.web.io.TableFilter;
import com.csc.fsg.life.rest.exception.NotFoundException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.Company;
import com.csc.fsg.life.rest.model.Environment;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.ErrorModelFactory;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class EnvironmentServiceImpl
	extends RestServiceImpl
	implements EnvironmentService
{
	private Log log = LogFactory.getLog("com.csc.fsg");

	@Inject
	protected ErrorModelFactory errorModelFactory = null;

	public EnvironmentServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.EnvironmentServiceImpl");
	}

	public List<Environment> getEnvironments(RestServiceParam param)
	{
		try {
			CommonApplicationConfigBean pwConfig = getBean(CommonApplicationConfigBean.class);
			Map<String, MyBatisApplicationEnvironmentBean> environmentMap = pwConfig.getEnvironments();

			List<Environment> envList = new ArrayList<>();
			for (MyBatisApplicationEnvironmentBean envBean : environmentMap.values()) {
				Environment env = new Environment();
				envList.add(env);
				env.setId(envBean.getName());
				env.setDisplayName(envBean.getDisplayName());
			}

			PropertyComparator.sort(envList, new MutableSortDefinition("displayName", true, true));
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

	public List<Company> getEnvironmentCompanies(RestServiceParam param, String env)
	{
		try {
			env = env.trim().toUpperCase();

			Map<String, com.csc.fsg.life.pw.web.environment.Environment> environments = EnvironmentManager.getInstance().getEnvironments();
			com.csc.fsg.life.pw.web.environment.Environment environment = environments.get(env);

			if (environment == null) {
				log.error(getMessage("environment_not_found_LONG", env));
				ErrorModel model = errorModelFactory.newErrorModel(NotFoundException.HTTP_STATUS);
				model.setMessage(getMessage("environment_not_found_SHORT", env));
				model.setFields(Arrays.asList(new String[] { "env" }));
				throw new NotFoundException(model);
			}
			else {
				TableFilter filter = new TableFilter();
				filter.setTableName("T001X");
				TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(filter.getTableId());
				List<Row> rows = td.getTableRows(environment, filter);

				List<Company> companyList = new ArrayList<>();
				for (Row row : rows) {
					Company company = new Company();
					companyList.add(company);
					String companyCode = row.getCompanyCode();
					company.setCompanyCode(companyCode);
					company.setCompanyName(companyCode + "-" + row.getValueOf(FieldNames.T001X_COMPANY_NAME));
				}

				PropertyComparator.sort(companyList, new MutableSortDefinition("companyName", true, true));
				return companyList;
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
}
