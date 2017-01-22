/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.util;

import java.util.*;

/* Modifications ENH764, T0103, V-E190, V-E105, T0091, CCCV-E501, ENH961, ENH946, ENH1006, ENH1043 */
// CCCV-E501 - remove SYSTEM_VANTAGEONE

/**
 * This <EM>final</EM> class cannot be instantiated. It is a place-holder for
 * constants.
 * 
 * @author Peter Santoro
 */

public final class Constants {

	public static final ArrayList<String> NO_VALIDATION_ON_TABLE_COLS;

	static {
		NO_VALIDATION_ON_TABLE_COLS = new ArrayList<String>(Arrays.asList(new String[] {
		        "002~ERROR_MESSAGE", "002~LONG_MESSAGE","008~LOB_NAME", "009~FUND_NAME",
		        "045~ACTION_REQ_MSG" }));
	}

	public static final char HTAB = '\t';

	public static final char EOL = '\n';

	public static final char PIPE = '|';

	public static final String STAB = "\t";

	public static final String SPIPE = "|";

	public static final String SEOL = "\n";

	public static final String STILDE = "~";

	public final static String TABLE_SUBSET = "TABLE_SUBSET";

	public final static String COMPANY_CODE = "COMPANY_CODE";

	public final static String SECURITY_OTA_USER = "otaUser";

	public final static String TRAD_FILENAME = "TraditionalPlanSummary";

	public final static String UL_FILENAME = "UniversalLifePlanSummary";

	public final static String ANNUITY_FILENAME = "AnnuityPlanSummary";

	public final static String PAYOUT_FILENAME = "PayoutPlanSummary";

	public final static String ADVPLAN_FILEEXTN = ".rtf";

	public final static int LEVEL_ONE = 1;

	public final static int DEFAULT_HASHTABLE_SIZE = 89;

	public final static int TABLE_ATTR_INSERT = 0x0001;

	public final static int TABLE_ATTR_UPDATE = 0x0002;

	public final static int TABLE_ATTR_DELETE = 0x0004;

	public final static int TABLE_ATTR_ERROR = 0x0008;

	public final static int TABLE_ATTR_READ_ONLY = 0x0010;

	public final static int TABLE_ATTR_IS_BOUNDED = 0x0020;

	public final static int TABLE_ATTR_HAS_VALUES = 0x0040;

	public final static int TABLE_ATTR_HAS_AUDIT_INFO = 0x0080;

	public final static int TABLE_ATTR_HAS_HELP_URL = 0x0100;

	public final static int TABLE_ATTR_HIDDEN = 0x0200;

	public final static int TABLE_ATTR_REQUIRED = 0x0400;

	public final static int TABLE_ATTR_EXCLUDE = 0x0800;

	public final static int TABLE_ATTR_ORIGINFO = 0x1000;

	public final static int TABLE_ATTR_UNLOCK = 0x2000;

	public final static int TABLE_ATTR_PROMOTION = 0x4000;

	public final static int TABLE_ATTR_TARGET_INSERT = 0x8000;

	public final static int TABLE_ATTR_TARGET_UPDATE = 0x10000;

	public final static int TABLE_ATTR_TARGET_DELETE = 0x20000;

	public final static int TABLE_ATTR_DO_POINTER_MAINTENANCE = 0x40000;

	public final static int TABLE_ATTR_KEY = 0x80000;

	public final static int TABLE_ATTR_SHARED = 0x100000;

	public final static int TABLE_ATTR_BUS_RULE_VIEW = 0x200000;

	// WMABASEIXI-3501 - replace WIZAUTOCOMPLETE with NOAUTOCOMPLETE
	public final static int TABLE_ATTR_NOAUTOCOMPLETE = 0x400000;

	public final static int TABLE_ATTR_MIXED_CASE = 0x800000; // WMA TT SPR#

	// 536

	public final static int TABLE_ATTR_ALLOW_FULL_DELETE = 0x1000000;

	public final static int NODE_ATTR_DELETE_PLAN_OR_RIDER = 0x0001;

	public final static int NODE_ATTR_INSERT_TABLE_SUBSET = 0x0002;

	public final static int NODE_ATTR_DELETE_TABLE_SUBSET = 0x0004;

	public final static int NODE_ATTR_CLONE_PLAN_OR_RIDER = 0x0008;

	public final static int NODE_ATTR_READ_ONLY = 0x0010;

	public final static int NODE_ATTR_SHARED = 0x0020;

	public final static int NODE_ATTR_HAS_HELP_URL = 0x0040;

	public final static int NODE_ATTR_RENAMED_DISPLAY_VALUE = 0x0080;

	public final static int NODE_ATTR_HAS_AUDIT_INFO = 0x0100;

	public final static int NODE_ATTR_INQUIRY = 0x1000;

	public final static int NODE_ATTR_UPDATE = 0x2000;

	// WMABASEIXI-4515
	public final static int NODE_ATTR_DISABLED = 0x4000;

	public final static int NODE_DISPLAY = 0;

	public final static int NODE_PACKAGE = 1;

	public final static int NODE_PROJECT = 2;

	public final static int NODE_COMPANY = 3;

	public final static int NODE_ANNUITIY_FOLDER = 4;

	public final static int NODE_UNIV_LIFE_FOLDER = 5;

	public final static int NODE_TRADITIONAL_FOLDER = 6;

	public final static int NODE_COMMON_TABLE_FOLDER = 7;

	public final static int NODE_PLAN_FOLDER = 8;

	public final static int NODE_RIDER_FOLDER = 9;

	public final static int NODE_PLAN = 10;

	public final static int NODE_RIDER = 11;

	public final static int NODE_TABLE = 12;

	public final static int NODE_COMMON_TABLE = 13;

	public final static int NODE_TABLE_SUBSET = 14;

	public final static int NODE_PDF_PLANS_FOLDER = 15;

	public final static int NODE_COMMON_FOLDER = 17;

	public final static String DELETE_PLAN = "DELPLN";

	public final static String INSERT_OPERATION = "INSERT";

	public final static String UPDATE_OPERATION = "UPDATE";

	public final static String DELETE_OPERATION = "DELETE";

	public final static String UNLOCK_OPERATION = "UNLOCK";

	public final static String WIPACTION_INSERT = "1";

	public final static String WIPACTION_UPDATE = "2";

	public final static String WIPACTION_DELETE = "4";

	public final static String WIPACTION_UNLOCK = "8";

	public final static String LOCKED = "L";

	public final static String UNLOCKED = " ";

	public final static String AUDIT = "Audit";

	public final static String GET_ENVIRONMENTS = "Env";

	public final static String GET_FILTER_INFO = "Filter";

	public final static String WIP = "WIP";

	public final static String AUDIT_VIEW = "AUDIT_VIEW";

	public static final String ENTIRE_TABLE = "ENTIRE_TABLE";

	public static final String DIRECT_IMPORT = "DIRIMP";

	public static final String ENCODED_PREFIX = "!#";

	public static final int DELETE_NODE = 0;

	public static final int INSERT_NODE = 1;

	public static final String REPLACE_DROP = "R";

	public static final String INSERT_DROP = "I";

	// refactor plan key.
	// use PlanRowTO.PLAN_ROW_KEYS.
	//public static final String NO_OF_COLS_TABLE_ZERO = "11";
	public static final String TABLE_ZERO_NAME = "T000X";

	public static final String TABLE_ZERO_ID = "000";

	public static final String SUBSET_TABLE_NAME = "T000XA";

	public static final String SUBSET_TABLE_ID = "00X";

	public static final String TREE_ALL_ONES = "111111";

	public static final String IMPORT_SUBSETS = "SUBSETS";

	public static final String IMPORT_ENTIRE_TABLE = "ENTIRE_TABLE";

	public static final String IMPORT_TABLE25 = "TABLE25";

	public static final String IMPORT_TABLE60 = "TABLE60";

	public static final String IMPORT_TABLE26 = "TABLE26";

	public static final boolean PTR_MAINT_REQUIRED = true;

	public static final boolean PTR_MAINT_NOT_REQUIRED = false;

	public static final String ORPHAN = "orphan";

	public static final String ADOPT = "adopt";

	public static final String NOKEY = "NOKEY";

	public static final String NA = "N/A";

	public static final String PRODUCT_PREFIX = "PRODUCT_PREFIX";

	public static final String NO_PRODUCT_PREFIX = "N";

	public static final String COMBINED_TABLE_PREFIX = "N";

	public static final String COMMON_TABLE_PREFIX = "C";

	public static final String ANNUITY_TABLE_PREFIX = "A";

	public static final String TRAD_TABLE_PREFIX = "T";

	public static final String UL_TABLE_PREFIX = "U";

	public static final String HCC_TABLE_PREFIX = "H";

	public static final String WIZARD_ALL_PRODUCTS = "AL";

	public static final int COMMON_WIP = 1;

	public static final int PLAN_WIP = 2;

	public static final int INDEX_WIP = 3;

	public static final int AUDIT_LOG = 4;

	public static final String OLD_OPERATION = "1OLD";

	public static final String NEW_OPERATION = "2NEW";

	public static final String GUIDELINE_PTR = "T047X";

	public static final String MORTALITY = "T049X";

	public static final String MORTALITY_SUB = "T049X1";

	public static final String INTEREST_RATE = "T051X";

	public static final String INTEREST_RATE_SUB = "T051X1";
	
	public static final String RESERVES_PTR = "TW67X";

	public static final String RESERVES_MORTALITY = "TW68X";

	public static final String RESERVES_MORTALITY_SUB = "TW68X1";

	public static final String RESERVES_INTEREST_RATE = "TW69X";

	public static final String RESERVES_INTEREST_RATE_SUB = "TW69X1";

	public static final String SUCCESSFUL_SAVE = "S";

	public static final String ERRORED_SAVE = "E";

	public static final String SHARED_SUBSET_MSG = "WARNING: This subset is shared.";

	public static final String DUPLICATE_SUBSET_MSG = "The subset name you have entered already exists.";

	public static final String DUPLICATE_ROW_MSG = "The INSERT operation failed because a row with the same key is already present.";

	public static final String ROW_NOT_FOUND_MSG = "The UPDATE or DELETE operation failed because a matching row could not be found.";

	public static final String ROW_CONFLICT_MSG = "One or more of the saved rows conflicts with another project in the WIP. Please re-request the Tree View with \"All Projects\" selected.";

	public static final String PROJECT_CONFLICT_MSG = "The operation was aborted because of a conflict with Project: ";

	public static final String AUTHORIZATION_ERROR = "You do not have authorization to perform this task.";

	public static final int SERVER_SUCCESS_IND = 0;

	public static final int CLONE_SUBSET_SUCCESS_IND = +1;

	public static final int SERVER_EXCEPT_IND = -1;

	public static final int SHARED_SUBSET_IND = -2;

	public static final int DUPLICATE_SUBSET_IND = -3;

	public static final int DUPLICATE_COMPANY_IND = -4;

	public static final int RENAME_SUBSET_SUCCESS_IND = +2;

	public static final int CLONE_PLAN_SUCCESS_IND = +3;

	public static final int CLONE_PLAN_FAIL_IND = -5;

	public static final int CLONE_COMMON_TBL_SUCCESS_IND = +4;

	public static final char ANNUITY_PRODUCT_PREFIX = 'A';

	public static final char TRAD_PRODUCT_PREFIX = 'T';

	public static final char UNIV_PRODUCT_PREFIX = 'U';

	public static final String SECURITY_INQUIRY = "R";

	public static final String SECURITY_UPDATE = "U";

	public static final String SECURITY_MANAGER = "M";

	public static final String PAGING_NEXT = "Next";

	public static final String PAGING_NO_NEXT = "No Next";

	public static final String PAGING_PREV = "Previous";

	public static final String PAGING_NO_PREV = "No Previous";

	public static final String LOCATE = "Locate";

	public static final String NO_LOCATE = "No Locate";

	public static final String SQL_DUPLICATE_ERROR = "000803";

	public static final String SQL_NOT_FOUND_ERROR = "000100";

	public static final String SQL_BIND_ERROR = "000805";

	public static final String SQL_PROCEDURE_NOT_FOUND_ERROR = "000440";

	public static final String TAB = "\t";

	public static final String EMPTY_STRING = "";

	public static final int BATCH_UPDATE_SUCCESS = -2;

	public static final int BATCH_UPDATE_FAILURE = -3;

	public static final String DB_DATE_FORMAT = "yyyy-MM-dd";

	public static final String PROMOTION_SUCCESS = "SUCCESS";
	
	public static final String PROMOTION_SUCCESS_WITH_WARNING = "SUCCESS_WITH_WARNINGS";

	public static final String PROMOTION_FAILURE = "FAILURE";

	public static final String PROMOTION_FAILURE_DLG = "FAILURE_DLG";

	// ENH946 - added fund info defaults
	public static final String MAX_FUND_DIGITS = "8";

	public static final String MAX_FUND_NUMBER = "99999998";

	public static final String DEFAULT_FUND = "99999999";
	
	public static final String PAYOUT_PRODUCT = "A5";

	public static final String SYSTEM_WMA = "WMA";
	
	// ENH764
	public static final String REPLACE_VALUE_NOT_FOUND_MSG = "The entered value does not exist in the table.";	
	
	public static final String REPLACE_VALUE_NOT_IN_FILTER_MSG = "The entered value does not exist in the filtered rows.";	
	
	public static final String INC_RES = "INC_RES";
	
	public static final String EXT_AUDIT_PREFIX = "audit.";
	
	// ENH1006
	public static final String USER_FILTER_ERROR = "Error in user filter: ";
	public static final String NO_WIP_ROWS_MATCHING = "No WIP rows matching filter";
	
	// ENH1043
	public static final String DELETE_OVERRIDE_MSG = "Note: This edit may be overridden in Entire Table View. When importing, select"
		+ "<br>'Allow Full Delete of Referenced Subsets' in the Import window.";
	
	private Constants() { 
	}
}
