package com.csc.fsg.life.rest.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.csc.fsg.life.rest.model.ErrorModel;

@ControllerAdvice
public class ExceptionHandlerAdvice
{
	@ExceptionHandler(VerboseRestServiceException.class)
	public ResponseEntity<ErrorModel> handleException(VerboseRestServiceException e)
	{
		return ResponseEntity.status(e.getHttpStatus()).body(e.getErrorModel());
	}

	@ExceptionHandler(QuietRestServiceException.class)
	public ResponseEntity<Void> handleException(QuietRestServiceException e)
	{
		return new ResponseEntity<>(e.getHttpStatus());
	}
}
