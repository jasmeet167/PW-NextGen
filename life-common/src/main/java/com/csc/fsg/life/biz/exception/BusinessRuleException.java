package com.csc.fsg.life.biz.exception;

/**
   Bussiness Object exception related to rules.
*/
public class BusinessRuleException extends BusinessException {

	/**
	   Creates an empty BusinessRuleException object.
	 */
	public BusinessRuleException() {
		super();
	}

	/**
	   Creates a BusinessRuleException object with a structured BusinessMessage object.
	   @param message The message object.
	 */
	public BusinessRuleException(BusinessMessage message) {
		super(message);
	}

	/**
	   Creates a BusinessRuleException from a string message.
	   @param msg The message.
	 */
	public BusinessRuleException(String msg) {
		super(msg);
	}

	/**
	   Creates a BusinessRuleException from another exception.
	   @param e the exception.
	 */
	public BusinessRuleException(Exception e) {
		super(e);
	}

}
