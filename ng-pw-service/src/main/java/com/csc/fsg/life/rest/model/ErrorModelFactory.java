package com.csc.fsg.life.rest.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.rest.model.ErrorModel.SeverityEnum;

@Component
public class ErrorModelFactory
{
	@Value("${defaultErrorMessage}")
	private String defaultErrorMessage = null;

	public ErrorModel newErrorModel(HttpStatus httpStatus)
	{
		return newErrorModel(httpStatus, SeverityEnum.ERROR, null);
	}

	public ErrorModel newErrorModel(HttpStatus httpStatus, String message)
	{
		return newErrorModel(httpStatus, SeverityEnum.ERROR, message);
	}

	private ErrorModel newErrorModel(HttpStatus httpStatus, SeverityEnum severity, String message)
	{
		ErrorModel model = new ErrorModel();
		model.setSeverity(severity);
		model.setErrorCode(String.valueOf(httpStatus.value()));

		if (StringUtils.hasText(message))
			model.setMessage(message);
		else
			model.setMessage(httpStatus.getReasonPhrase() + ": " + defaultErrorMessage);

		return model;
	}
}
