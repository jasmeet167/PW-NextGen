/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

/**
 * Class PWIOException
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class PWIOException extends Exception {

	public static final String DUPLICATE_KEY = "Record with the same primary key already exists";

	public static final String INVALID_NUMBER_OF_COLUMNS = "Invalid Number of columns";

	public static final String RECORD_DOES_NOT_EXIST = "Record does not exists";

	public static final String RECORD_LOCKED_FOR_PROMOTION = "Record locked for promotion";

	public static final String COULD_NOT_CREATE_CONNECTION = "Could not create database connection";

	// c2s-table parsing related errors
	public static final String INVALID_TABLE_DESCRIPTOR_LINE = "Invalid table descriptor line in input stream";

	public static final String INVALID_WIP_DESCRIPTOR_LINE = "Invalid wip descriptor line in input stream";

	public static final String OLD_CONCAT_KEY_MISSING = "Old Concat Key missing in input stream";

	public static final String ORIGINAL_DATA_MISSING = "Original data missing in input stream";

	public static final String COMMIT_TRANSACTION_FAILED = "Commit failed";

	public static final String ROLLBACK_TRANSACTION_FAILED = "Rollback failed";

	public static final String PROMOTION_IN_PROGRESS = "Promotion in progress. Concurrent promotions not allowed";

	public static final String EXCLUDE_FROM_PROMOTION_FAILED = "Exclude from promotion failed";

	public static final String INVALID_INPUT_PARAMS = "Invalid input parameters";

	public static final String INVALID_ENVIRONMENT_SCHEMA = "Invalid Environment Schema";

	public static final String COULD_NOT_LOCK_PROMOTION_ROWS = "Could not lock all required rows for promotion";

	public static final String INSERT_USER_FAILED = "Insert user failed";

	public static final String UPDATE_USER_FAILED = "Update user failed";

	public static final String DELETE_USER_FAILED = "Delete user failed";

	public static final String DUPLICATE_USER_AUTHORITY = "Duplicate user authority";

	public static final String SELECT_USER_PROFILE_FAILED = "Select user profile failed";

	public static final String SELECT_USER_AUTHORITY_FAILED = "Select user authority failed";

	public static final String INSERT_RECORD_FAILED = "Insert record failed";

	public static final String UPDATE_RECORD_FAILED = "Update record failed";

	public static final String DELETE_RECORD_FAILED = "Delete record failed";

	public static final String ZERO_RECORDS_UPDATED = "Zero records updated";

	public static final String INVALID_FILTER = "Invalid filter";

	public static final String DELETE_PROMOTION_ROWS_FAILED = "Delete promotion locked rows failed";

	public static final String DELETE_PROMOTION_ROW_FAILED = "Delete promotion locked row failed";

	public static final String UNKNOWN_ERROR = "Unknown Error";

	public static final String DB_FAILURE = "Unknown database failure";

	/**
	 * Constructor PWIOException
	 */

	public PWIOException() {
		super();
	}

	/**
	 * Constructor PWIOException
	 * 
	 * @param message
	 */

	public PWIOException(String message) {
		super(message);
	}
}
