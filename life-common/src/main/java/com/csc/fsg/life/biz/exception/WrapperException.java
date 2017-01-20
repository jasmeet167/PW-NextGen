package com.csc.fsg.life.biz.exception;


/* Modifications: WMABASEIXI-4941 */

/* com.csc.fsg.life.biz.exception.WrapperException and com.csc.fsg.life.exceptions.WrapperException
 * are exactly the same except one method missing this class which is creating a stack overflow issue.
 * 
 * Redirecting all calls to this class to com.csc.fsg.life.exceptions.WrapperException until all the 
 * references are removed to this class-this class can be removed after that.
 * 
 */
public class WrapperException extends com.csc.fsg.life.exceptions.WrapperException
{
	protected WrapperException(Throwable t, String msgCode, Object[] msgParts) {
		super(t, msgCode, msgParts);
	}
 
}
