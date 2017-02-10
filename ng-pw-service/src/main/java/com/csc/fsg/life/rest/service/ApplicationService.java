package com.csc.fsg.life.rest.service;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.AboutApplication;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface ApplicationService
{
	@SecuredMethod
	AboutApplication getInfoAboutApplication(RestServiceParam param);
}
