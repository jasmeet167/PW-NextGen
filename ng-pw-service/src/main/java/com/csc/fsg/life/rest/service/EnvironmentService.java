package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.model.Company;
import com.csc.fsg.life.rest.model.Environment;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface EnvironmentService
{
	public List<Environment> getEnvironments(RestServiceParam param);

	public List<Company> getEnvironmentCompanies(RestServiceParam param, String env);
}
