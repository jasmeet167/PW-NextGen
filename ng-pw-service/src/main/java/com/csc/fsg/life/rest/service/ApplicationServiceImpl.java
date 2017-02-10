package com.csc.fsg.life.rest.service;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csc.fsg.life.common.util.BuildProperties;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.AboutApplication;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.ErrorModelFactory;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class ApplicationServiceImpl
	extends RestServiceImpl
	implements ApplicationService
{
	@Autowired
	private ErrorModelFactory errorModelFactory = null;

	public AboutApplication getInfoAboutApplication(RestServiceParam param)
	{
		try {
			BuildProperties properties = BuildProperties.getInstance();
	
			AboutApplication about = new AboutApplication();
			about.setAppDesignator(properties.getAppDesignator());
			about.setAppVersion(properties.getAppVersion());
	
			DateTime timestamp = null;
			String timeString = properties.getBuildTimestamp();
			if (timeString.indexOf('T') >= 0) {
				timestamp = new DateTime(timeString);
			}
			else {
				String[] timeComponents = timeString.split("\\-");
				timestamp = new DateTime(Integer.valueOf(timeComponents[0]).intValue(),
										 Integer.valueOf(timeComponents[1]).intValue(),
										 Integer.valueOf(timeComponents[2]).intValue(),
										 Integer.valueOf(timeComponents[3]).intValue(),
										 Integer.valueOf(timeComponents[4]).intValue(),
										 Integer.valueOf(timeComponents[5]).intValue());
			}
	
			about.setBuildTimestamp(timestamp);
			return about;
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
