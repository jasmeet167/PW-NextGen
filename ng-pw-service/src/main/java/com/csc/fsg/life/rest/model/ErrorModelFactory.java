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
		String errorCode = String.valueOf(httpStatus.value());
		return newErrorModel(SeverityEnum.ERROR, errorCode, null);
	}

	public ErrorModel newErrorModel(String errorCode)
	{
		return newErrorModel(SeverityEnum.ERROR, errorCode, null);
	}

	public ErrorModel newErrorModel(String errorCode, String message)
	{
		return newErrorModel(SeverityEnum.ERROR, errorCode, message);
	}

	public ErrorModel newErrorModel(SeverityEnum severity, String errorCode, String message)
	{
		ErrorModel model = new ErrorModel();
		model.setSeverity(severity);
		model.setErrorCode(errorCode);
		model.setMessage(StringUtils.hasText(message) ? message : defaultErrorMessage);
		return model;
	}
}
