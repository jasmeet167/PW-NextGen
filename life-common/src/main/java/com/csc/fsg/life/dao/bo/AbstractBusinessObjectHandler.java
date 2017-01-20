// Produced @ 2002-11-22 10:14:35.989
//
package com.csc.fsg.life.dao.bo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.dao.exceptions.BusinessObjectException;
import com.csc.fsg.life.dao.exceptions.ExceptionHandler;


/* $#(c)
 * This software contains trade secrets and confidential information,
 * which are proprietary to Computer Sciences Corporation. The use,
 * reproduction, distribution, or disclosure of this software, in whole
 * or in part, without the express written permission of Computer
 * Sciences Corporation is prohibited. This software is also an
 * unpublished work protected under the copyright laws of the United
 * States of America and other countries. If this software becomes
 * published, the following notice shall apply:
 *
 * Copyright (c) 1994 - 2002 Computer Sciences Corporation,
 * all rights reserved.
 *
 * This software may be modified only in accordance with the terms of the
 * applicable license agreement.  Any modifications may result in the
 * voiding of applicable warranties and support services.
 */

/* Modifications: T0140 */

 /**
  *	Abstract super class for all handlers.  It's purpose is to handle
  *	database connections as well as react appropriately to any exceptions
  *	that may be thrown.
  */
public abstract class AbstractBusinessObjectHandler
{
	private static Log logger = LogFactory.getLog(AbstractBusinessObjectHandler.class);

	/** The businesss object for this handler. **/
	protected AbstractBusinessObject bo;
	/** The command to execute in the handler. **/
	protected BusinessObjectCommand command;
	/** The helper object for managing exceptions. **/
	protected ExceptionHandler exceptionHandler;
    
	/**
	   Creates a handler. 
	**/
	public AbstractBusinessObjectHandler( AbstractBusinessObject abo, BusinessObjectCommand command )
	{
		super();

		bo = abo;
		this.command = command;
		
		// load an exception handler
		exceptionHandler = ExceptionHandler.getInstance();
	}

	/**
	   Called to handle a command. 
	   Wraps the {@link #handle(AbstractBusinessObject, BusinessObjectCommand, Connection)} 
	   method by creating a connection and ensuring the connection is closed.
	   @param dataSource the dataSource.
	   @throws BusinessObjectException If there is a business error with the command.
	   @throws SQLException If there is an I/O error with the command.
	**/
	public void handle(DataSource dataSource) throws BusinessObjectException, SQLException {
		
		Connection conn = null;

		try {
			conn = dataSource.getConnection();

			// turn auto-commit off
			conn.setAutoCommit(false);

			// call the concrete classes handle method
			handle(bo, command, conn);

			// since we are successful, commit the transaction
			conn.commit();
		} catch (SQLException e) {
			if (conn != null)
				conn.rollback();
			throw new SQLException(exceptionHandler.getReadableMessage(e));
		} catch (BusinessObjectException e) {
			if (conn != null)
				conn.rollback();
			throw new BusinessObjectException(exceptionHandler
					.getReadableMessage(e));
		} catch (Exception e) {
			if (conn != null)
				conn.rollback();
			throw new BusinessObjectException(exceptionHandler
					.getReadableMessage(e));
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (Exception cme) {
				logger.error("Connection close exception: " + cme.getMessage());
			}
		}

	}

	/**
	   This method must be defined in all handlers.
	   @param abo The business object.
	   @param command The command to execute.
	   @param conn The database connection.
	   @throws BusinessObjectException If there is a business error with the command.
	   @throws SQLException If there is an I/O error with the command.
	 */
	public abstract void handle(AbstractBusinessObject abo, BusinessObjectCommand command, Connection conn)
		throws BusinessObjectException, SQLException;

}
