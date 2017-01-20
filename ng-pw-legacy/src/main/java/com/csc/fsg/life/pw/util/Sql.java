/*
 * $#(c) This software contains trade secrets and confidential information,
 * which are proprietary to Computer Sciences Corporation. The use,
 * reproduction, distribution, or disclosure of this software, in whole or in
 * part, without the express written permission of Computer Sciences Corporation
 * is prohibited. This software is also an unpublished work protected under the
 * copyright laws of the United States of America and other countries. If this
 * software becomes published, the following notice shall apply: Copyright (c)
 * 1994 - 2003 Computer Sciences Corporation, all rights reserved. This software
 * may be modified only in accordance with the terms of the applicable license
 * agreement. Any modifications may result in the voiding of applicable
 * warranties and support services.
 */
package com.csc.fsg.life.pw.util;

public abstract class Sql {
	public final static String[] commErrorCodes = { "40003", "S1000" };

	public final static int SQL_INS_OTHER = 50,

	SQL_GET_OB8_LOB_CODES = 51, SQL_GET_OB8_LOB_TYPE = 52,
	        SQL_GET_OBD_STATE_COUNTY_INDS = 53,
	        SQL_GET_UNIQUE_APR_RECORD_FETCH = 54,
	        SQL_GET_UNIQUE_MPR_RECORD_FETCH = 55,
	        SQL_GET_CONTRACT_RECORDS_FETCH = 56,
	        SQL_GET_AGENT_ID_BY_LEGACY_ID_FETCH = 58,
	        SQL_GET_ACR_HIERARCHY_LEVEL = 57,
	        SQL_GET_MULTIPLE_FREQUENCY_IND = 59,
	        SQL_GET_APR_CLIENT_NUMBERS = 60, SQL_GET_ADDRESS_LIST = 61,
	        SQL_GET_CONTRACT35_LIST = 62, SQL_GET_ACR_BY_PROFILE = 63,
	        SQL_GET_ALL_AGREEMENTS_FETCH = 64, SQL_GET_CONVLOB_LIST_FETCH = 65,
	        SQL_GET_COMPANY_FETCH = 66, SQL_GET_SCHEMA_NAME = 67,

	        SQL_GET_OTHER = 100,

	        SQL_UPD_OTHER = 150,

	        SQL_DEL_OTHER = 200,

	        SQL_MAX_ID = 200,
	        /** MOD018 Dec 17, 2003,Manosh -- start * */
	        SQL_PREVBAL_CHKNUM = 201;
	/** MOD018 Dec 17, 2003,Manosh -- end * */
}