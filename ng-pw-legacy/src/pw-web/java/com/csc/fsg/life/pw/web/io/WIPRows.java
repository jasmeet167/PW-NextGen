/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.io.BufferedReader;
import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.pw.common.rules.SpecialHandling;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.util.*;
//import com.csc.fsg.life.pw.web.actions.rcm.beans.*;
//import com.csc.fsg.life.pw.web.actions.table.MergedDataAssistent;
//import com.csc.fsg.life.pw.web.actions.tree.IndexMergeAssistent;
//import com.csc.fsg.life.pw.web.actions.tree.PlanMergeAssistent;
import com.csc.fsg.life.pw.web.environment.*;
//import com.csc.fsg.life.pw.web.io.pointermaintenance.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.*;

/* Modifications: ENH761, CCCV-E199, ENH875, T0103, T0091, T0093, T0094, ENH961,T0113, ENH629, ENH915, CCCV-E768, T0129, ENH1043, ENH1063-05 */
// ENH961 - throw InfoException

/**
 * This class abstracts the WIP database. Data may be retrieved from the WIP and
 * written to the WIP using provided methods. This class contains instances of
 * WIPRow objects, which are subclassed into objects that represent a particular
 * WIP table. Methods on the WIPRow class and its subclasses assist the WIPRows
 * class fulfill specific requests. Creation date: (10/09/2001 10:56:12 AM)
 * 
 * @author: Carl Ericksen
 */

// T0091 - extend ArrayList<WIPRow>, not just ArrayList
public class WIPRows extends ArrayList<WIPRow> implements IWIPRows {

	private static Log _log = PWServerLogManager.getLog(WIPRows.class
			.getPackage().getName());

	private PreparedStatement indexInsertStmt;

	private PreparedStatement planInsertStmt;

	private PreparedStatement commonInsertStmt;

	private PreparedStatement indexUpdateStmt;

	private PreparedStatement planUpdateStmt;

	private PreparedStatement commonUpdateStmt;

	private PreparedStatement indexDeleteStmt;

	private PreparedStatement planDeleteStmt;

	private PreparedStatement commonDeleteStmt;

	private String[] insertSQL = null;

	private String[] updateSQL = null;

	private String[] deleteSQL = null;

	//private static final String promotionMsg = "Apply Changes/Promotion is in progress for this environment and project. Please try again later...";

	private transient WIPTableFilter requestFilter = null;

	//private T51FieldsGenerator specialHandle051;
	
	// T0093 - ppm relationship containers
	private Set<String> associatedSubsets = new HashSet<String>();
	private Set<String> disassociatedSubsets = new HashSet<String>();
	
	private Set<String> planAssociatedSubsets = new HashSet<String>();
	private Set<String> planDisassociatedSubsets = new HashSet<String>();
	
	private boolean fromPALoader = false;
	
	public boolean nextInd = false;

	public boolean prevInd = false;

	/**
	 * No arguments constructor
	 */

	public WIPRows() {
		super();
	}

	/**
	 * The constructor accepts a buffered stream in the vxx-c2s-table.txt
	 * format. It replaces the obsolesced WIPSaveAssistent. It will parse the
	 * stream and populate itself with WIPRow objects. Once constructed, it is
	 * the responsibility of the consumer to call one the save() methods to
	 * write the data to the WIP. Creation date: (10/09/2001 10:59:53 AM)
	 * 
	 * @param c2sTableStream
	 */

	public WIPRows(BufferedReader c2sTableStream) throws Exception {

		super(100);
		init();
		C2sTableProcessor c2s = new C2sTableProcessor();
		c2s.process(c2sTableStream, this);
		super.trimToSize();
	}

	/**
	 * Create collection of a single wip row
	 * 
	 * @param c
	 *            java.util.Collection
	 * @throws WIPRowsException
	 */

	public WIPRows(WIPRow wipRow)  {

		add(wipRow);
	}

	/**
	 * This constructor may be used to instantiate a WIPRows object from another
	 * type of collection. All objects within the collection you are sending
	 * must be of type WIPRow or an exception will be thrown.
	 * 
	 * @param c
	 *            java.util.Collection
	 * @throws WIPRowsException
	 */

	public WIPRows(Collection<WIPRow> c)  {

		super(c);
		init();
		Iterator<WIPRow> i = c.iterator();

		while (i.hasNext()) {
			WIPRow wipRow = i.next();
		
		}
	}

	/**
	 * This constructor queries the WIP based on the passed filter parameters to
	 * populate itself. If requested, it will also update WIP rows to lock them
	 * for promotion, based on the passed filter. Creation date: (10/09/2001
	 * 11:07:59 AM)
	 * 
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @param orderBy
	 *            String NOTE: Pass null (if no order by required),
	 * @param lockRowsForPromotion
	 *            boolean
	 * @throws WIPRowsException
	 */

	public WIPRows(WIPTableFilter filter, String orderBy,
			boolean lockRowsForPromotion) throws Exception {

		init();
		requestFilter = filter;
		Connection connection = null;
		HashMap<Integer, String> sqls = null;
		Statement stmt = null;
		ResultSet rs = null;
		//
		try {
			
//			if (filter instanceof AuditTableFilter ){
//				AuditTableFilter auditFilter = (AuditTableFilter)filter;
//				Environment environment = EnvironmentManager. getInstance().getEnvironment(filter.getEnvironment());
			//	if (environment.isRemoteAudit())
				//	connection = DBConnMgr.getInstance().getConnection(filter.getEnvironment(),DBConnMgr.BUSINESS_RULES);
			//	else
//					connection = DBConnMgr.getInstance().getConnection(filter.getEnvironment(),DBConnMgr.APPL);
//				
//				
//			}else{
				connection = DBConnMgr.getInstance().getConnection(filter.getEnvironment(),DBConnMgr.APPL);
//			}
		
			stmt = connection.createStatement();
			if (lockRowsForPromotion) {
				lockRows(connection);
			}
			sqls = WIPRow.getSelectSQL(requestFilter, orderBy);

			Set<Map.Entry<Integer, String>> entries = sqls.entrySet();
			for ( Map.Entry<Integer, String> entry : entries ) {
				Integer wipTable = entry.getKey();
				String sql = entry.getValue();

				if (sql == null) {
					throw new WIPRowsException("SQL is missing");
				}
				sql = sql.trim();
				
				rs = SqlPW.query(SqlPW.SQL_WIP_ROWS, stmt, sql);
				WIPRowFactory.createWIPRow(rs, this, wipTable.intValue());
				Utils.closeResultSet(rs);
			}
		
		} finally {
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(connection);
		}
	}

//	public static int getAuditRowCount(AuditTableFilter auditFilter,
//			String orderBy)  throws Exception{
//
//		Connection connection = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		DBConnMgr conMgr = null;
//		Environment environment = EnvironmentManager. getInstance().getEnvironment(auditFilter.getEnvironment());
//		try {
//			String countSql = AuditRow.buildRowCountSQL(environment,auditFilter, "");
//			conMgr = DBConnMgr.getInstance();
		//	if (environment.isRemoteAudit())
			//	connection = conMgr.getConnection(auditFilter.getEnvironment(),DBConnMgr.BUSINESS_RULES);
		//	else
//				connection = conMgr.getConnection(auditFilter.getEnvironment(),DBConnMgr.APPL);
//
//			if (countSql == null) {
//				throw new WIPRowsException("SQL is missing");
//			}
//			countSql = countSql.trim();
//			stmt = connection.createStatement();
//
//			rs = SqlPW.query(SqlPW.SQL_WIP_ROWS, stmt, countSql);
//
//			if (rs.next())
//				return rs.getInt(1);
//
//		
//		} finally {
//			Utils.closeResultSet(rs);
//			Utils.closeStatement(stmt);
//			conMgr.releaseConnection(connection);
//		}
//		return -1;
//	}

	public void lockRowsForPromotion(WIPTableFilter filter)
			throws Exception {

		init();
		Connection connection = null;
		requestFilter = filter;

		try {
			connection = DBConnMgr.getInstance().getConnection(filter.getEnvironment(),DBConnMgr.APPL);
			lockRows(connection);
		} finally {
			DBConnMgr.getInstance().releaseConnection(connection);
		}
	}

	/**
	 * Constructor WIPRows
	 * 
	 * @param wipConn
	 * @param filter
	 * @param orderBy
	 * @param lockRowsForPromotion
	 * @throws WIPRowsException
	 */

	public WIPRows(Connection wipConn, WIPTableFilter filter, String orderBy,
			boolean lockRowsForPromotion) throws Exception {

		init();
		requestFilter = filter;
		HashMap<Integer, String> sqls = null;
		Statement stmt = null;
		ResultSet rs = null;
		Connection remoteAuditConn = null;
		try {
			if (lockRowsForPromotion) {
				lockRows(wipConn);
			}

			sqls = WIPRow.getSelectSQL(requestFilter, orderBy);

			stmt = wipConn.createStatement();
			Set<Map.Entry<Integer, String>> entries = sqls.entrySet();
			for ( Map.Entry<Integer, String> entry : entries ) {
				Integer wipTable = entry.getKey();
				String sql = entry.getValue();

				if (sql == null) {
					throw new WIPRowsException("SQL is missing");
				}
				sql = sql.trim();
				rs = stmt.executeQuery(sql);
				WIPRowFactory.createWIPRow(rs, this, wipTable.intValue());
			}
	
		} finally {

			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(remoteAuditConn);
		}
	}

	/**
	 * This override method validates the type of object passed and adds it to
	 * itself. Valid subtypes are CommonWIPRow, PlanWIPRow, IndexWIPRow, and
	 * AuditRow. Creation date: (10/09/2001 11:43:49 AM)
	 * 
	 * @return boolean
	 * @param wipRow
	 *            Object
	 */

	// T0091 - make add specific to WIPRow
	public boolean add(WIPRow wipRow) {
		return super.add(wipRow);
		
	}

	/**
	 * This method queries the WIP for a given schema looking for distinct
	 * values of the passed itemName. The valid itemNames are defined in
	 * WIPProperties.java If only WIP records with errors are to be searched,
	 * set withErrors to true Creation date: (10/09/2001 12:55:22 PM)
	 * 
	 * @return java.util.ArrayList
	 * @param envSchema
	 *            java.lang.String
	 * @param itemName
	 *            java.lang.String
	 * @param withErrors
	 *            boolean
	 * @throws WIPRowsException
	 */

	public static ArrayList<String> getDistinctItemsFromWIP(Environment environment,
			String itemName, boolean withErrors) throws Exception {

		ArrayList<String> distinctItems = null;
		distinctItems = WIPRow.getDistinctItemsFromWIP(environment, itemName,
					withErrors);
	
		return distinctItems;
	}

	/**
	 * This static method unlocks rows on the WIP based on a filter
	 * 
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @throws WIPRowsException
	 */

	public static void unlockRows(WIPTableFilter filter) throws Exception {

//		if (filter instanceof AuditTableFilter)
//			throw new WIPRowsException(
//					"AuditTableFilter invalid in this context");

		executeUpdateSQL(filter.getEnvironment(),WIPRow.getLockSQL(filter));
	}

	/**
	 * This static method deletes rows from the WIP based on a filter
	 * 
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @throws WIPRowsException
	 */

	public static void deleteRows(Connection conn,WIPTableFilter filter) throws Exception {

//		if (filter instanceof AuditTableFilter)
//			throw new WIPRowsException(
//					"AuditTableFilter invalid in this context");

		// ENH875 - remove any error log rows
		Set errDbSeq = new HashSet();
		boolean oldWithErrors = filter.isWithErrors();
		filter.setWithErrors(true);
		WIPRows wipRows = new WIPRows(filter, null, false);
		Iterator it = wipRows.iterator();
		while ( it.hasNext() ) {
			WIPRow wipRow = (WIPRow) it.next();
			errDbSeq.add(wipRow.getDbSequence() + "");
		}
		if (!errDbSeq.isEmpty()) {
			deleteErrorRecords(filter.getEnvironment(), errDbSeq);
		}
		filter.setWithErrors(oldWithErrors);

		executeUpdateSQL(conn,filter.getEnvironment(),WIPRow.getDeleteSQL(filter));
	}
	
	public static void deleteRows(WIPTableFilter filter) throws Exception {
		Connection connection = null;
		try {
			connection = DBConnMgr.getInstance().getConnection(filter.getEnvironment(),DBConnMgr.APPL);
			deleteRows(connection,filter);
		} finally {
			DBConnMgr.getInstance().releaseConnection(connection);
		}
		
	}

	// ENH875 - remove any error log rows
	private static void deleteErrorRecords(String envId, Set errDbSeq) {
		Environment env = EnvironmentManager.getInstance().getEnvironment(envId);
		StringBuffer sql = new StringBuffer("Delete from "
				+ env.getApplSchema() + ".ERROR WHERE ERROR_SEQUENCE IN (");
		Iterator iter = errDbSeq.iterator();
		while (iter.hasNext()) {
			sql.append(" '" + iter.next() + "' ");
			if (iter.hasNext())
				sql.append(", ");
		}
		sql.append(" )");

		Connection conn = null;
		Statement stmt = null;
		try {
			conn = DBConnMgr.getInstance().getConnection(env.getId(),DBConnMgr.APPL);
			stmt = conn.createStatement();
			stmt.execute(sql.toString());
		} catch (Exception e) {
			_log.error("Exception while deleting error rows from Error Log: "
					+ e.getMessage());
		} finally {
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(conn);
		
		}
	}

	/**
	 * This static method will destroy the passed instance of WIPRows and
	 * release the WIPRow objects it contains
	 * 
	 * @param wiprows
	 */

	public static void destroy(WIPRows wiprows) {
	}

	/**
	 * This method converts each instance of AuditRow it contains to the
	 * appropriate subclass of WIPRow and saves everything to the appropriate
	 * WIP using Batch Update SQL syntax. If it fails for any reason, no updates
	 * will be made and a WIPRowsException will be thrown. Creation date:
	 * (10/09/2001 2:12:07 PM)
	 * 
	 * @param newPackageId
	 * @param newTargetEnv
	 * @param newPromoteUserId
	 * @param lockRows
	 * @throws WIPRowsException
	 */

	public void saveAuditToWIPAsBatch(String newPackageId, String newTargetEnv,
			String newPromoteUserId, boolean lockRows) throws Exception {

//		if ((newTargetEnv == null) || (newPromoteUserId == null)) {
//			throw new WIPRowsException("One or more arguments missing");
//		}
//
//		AuditRow auditRow = null;
//
//		for (int i = 0; i < size(); i++) {
//			auditRow = (AuditRow) get(i);
//			remove(i);
//			add(i, auditRow.transformToWIPRow(newPackageId, newTargetEnv,
//					newPromoteUserId, lockRows));
//		}
//		saveToWIPAsBatch();
	}

	/**
	 * This method
	 * 
	 * @return
	 * @throws WIPRowsException
	 */
	public String saveToWIP() throws Exception {
		Connection connection = null;
		try {
			String envId = (get(0)).getEnvironment();
			connection = DBConnMgr.getInstance().getConnection(envId,DBConnMgr.APPL);
			// T0093 - all or nothing now
			connection.setAutoCommit(false);
			return saveToWIP(connection);
		} catch (WIPRowsException wre) {
			try {
				connection.rollback();
			} catch (SQLException se) {
				// IGNORE
			}
			throw wre;
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException se) {
				// IGNORE
			}
			throw WrapperException.wrap(e);
		} finally {
			try {
				if (connection != null) {
					connection.commit();
					DBConnMgr.getInstance().releaseConnection(connection);
				}
			} catch (Exception e) {
				// IGNORE
			}
		}

	}

	// CCCV-E199 - call gatherSubsets and createNewOrphans
	public String saveToWIP(Connection connection) throws WIPRowsException {

		// rtsConnection connection = null;
//		StringBuffer errors = null;
//		StringBuffer results = null;
//		String errorMessage = null;
		String outString = null;
//		int errorCount = 0;
//
//		long startTime = SqlPW.logStart("saveToWIP");
//
//		PlanMergeAssistent pma = null;
//		IndexMergeAssistent ima = null;
//		String mergeKey = "";
//
//		try {
//			errors = new StringBuffer();
//			results = new StringBuffer();
//
//			if (size() == 0)
//				throw new WIPRowsException("No rows to save");
//
//			WIPRow wipRow = get(0);
//			Vector<String> projects = new Vector<String>(1);
//
//			String prjName = wipRow.getProjectName().toUpperCase();
//			wipRow.setProjectName(prjName);
//
//			_log.info("Saving " + size() + " row(s) to WIP for project " + prjName.trim());
//			
//			projects.add(wipRow.getProjectName());
//			Environment environment = EnvironmentManager.getInstance().getEnvironment(wipRow.getEnvironment());
//			if ( !fromPALoader ) {
//				String error = WIPRow.getPromotionAlreadyInProgressMessage(environment, projects, true);
//				if ( (error != null) && (requestFilter == null || !requestFilter.isLocked()) ) {
//					throw new WIPRowsException(error);
//				}
//			}
//
//			// ENH915, ENH629 - call WIPRowsValidator.validate
//			new WIPRowsValidator(this, connection).validate(results);
//			if ( results.length() > 0 )
//				return results.toString();
//
//			String ddlName = wipRow.getDdlName();
//			
//			if ( !fromPALoader ) {
//				if (ddlName.equals("TW66X")){ 
//					StringBuffer w66message = new StringBuffer();
//					new TW66XValidator().validate(this, connection,w66message,(wipRow.getWipActionAttrMask() & Constants.TABLE_ATTR_BUS_RULE_VIEW) != 0);
//					if (w66message.length()>0)
//						return w66message.toString();
//				}
//				
//				if (ddlName.equals("TW64X")){ 
//					StringBuffer w64message = new StringBuffer();
//					new TW64XValidator().validate(this, connection,w64message,(wipRow.getWipActionAttrMask() & Constants.TABLE_ATTR_BUS_RULE_VIEW) != 0);
//					if (w64message.length()>0)
//						return w64message.toString();
//				}
//			}
//			
//			// SHARED SUBSET CHECK
//			isSharedSubset(wipRow,connection);
//
//			// SPECIAL HANDLE GENRATION OF ROWS IN CASE TABLE IS T051X1 or TW51X
//			
//			if (ddlName.equals("T051X1") || ddlName.equals("TW69X1")) { 
//				new T51FieldsGenerator().generateNewWIPRows(this, connection,ddlName);
//				if ( fromPALoader ) {
//					if ( size() > 0 ) {
//						for (WIPRow tempWipRow : this) {
//							tempWipRow.setPromotionLock("L");
//						}
//					}
//				}
//			}
//	
//			// NORMAL SAVE
//			setPreparedStatements(wipRow.getEnvironment());
//			Hashtable<String, WIPRow> ockHash = new Hashtable<String, WIPRow>();
//			Set<String> insertedSubsets = new HashSet<String>();
//			Set<String> deletedSubsets = new HashSet<String>();
//			for (int rowIndex = 0; rowIndex < this.size(); rowIndex++) {
//				WIPRow tempWipRow = get(rowIndex);
//				prjName = wipRow.getProjectName().toUpperCase();
//				tempWipRow.setProjectName(prjName);
//
//				if (tempWipRow.getProjectName().trim().length() == 0) {
//					throw new WIPRowsException("Blank Project");
//				}
//				if (tempWipRow.getProductPrefix().equalsIgnoreCase("N")) {
//					tempWipRow.setProductPrefix(getProductPrefix(tempWipRow
//							.getDdlName()));
//				}
//
//				boolean saveResult = false;
//				if (tempWipRow instanceof CommonWIPRow) {
//					if (tempWipRow.getDdlName().equals("T001X")
//							&& tempWipRow.getOperation().equals(
//									Constants.INSERT_OPERATION))
//						validateDuplicateCompany(tempWipRow);
//					
//					// TT wma SPR 6579 - pass wipConn
//					gatherSubsets(connection, insertedSubsets, deletedSubsets, tempWipRow);
//					
//					boolean ppmFlag = true;
//					if ((rowIndex + 1) < this.size()) {
//						WIPRow nextRow = this.get(rowIndex + 1);
//						ppmFlag = isPPMNecessary(wipRow, nextRow);
//						if (!ppmFlag) {
//							ockHash.put(tempWipRow.getOldConcatKey(),
//									tempWipRow);
//						}
//					}
//					WIPRow deletedWipRow = ockHash.get(tempWipRow
//							.getOldConcatKey());
//					saveResult = processCommonWIPRowSave(
//							(CommonWIPRow) deletedWipRow,
//							(CommonWIPRow) tempWipRow, ppmFlag, connection,
//							results);
//				} else if (tempWipRow instanceof PlanWIPRow) {
//					// T0093 - Create merge assistents only once for all PlanChangesPreProcessor uses
//					// (unless product prefix changes)
//					if ( fromPALoader ) 
//						tempWipRow.setPromotionLock("L");
//					if ( !mergeKey.equals(tempWipRow.getProductPrefix()) ) {
//						mergeKey = tempWipRow.getProductPrefix();
//						if ( pma != null ) {
//							pma.clean(connection);
//							pma = null;
//						}
//						PlanCriteriaTO mergePlanCriteria = new PlanCriteriaTO();
//						mergePlanCriteria.setEnvironment(tempWipRow.getEnvironment());
//						mergePlanCriteria.setCompanyCode(tempWipRow.getCompanyCode());
//						mergePlanCriteria.setProductPrefix(tempWipRow.getProductPrefix());
//						mergePlanCriteria.setViewChanges(true);	
//						pma = new PlanMergeAssistent(connection, mergePlanCriteria);
//					
//						if ( ima != null ) {
//							ima.clean(connection);
//							ima = null;
//						}
//						HashMap<String, String> treeKey = new HashMap<String, String>(3);
//						treeKey.put("schema", tempWipRow.getEnvironment());
//						treeKey.put("company_code", tempWipRow.getCompanyCode());
//						treeKey.put("product_prefix", tempWipRow.getProductPrefix());
//						ima = new IndexMergeAssistent(connection, treeKey, false, "with");
//					}
//					
//					saveResult = processPlanWIPRowSave((PlanWIPRow) tempWipRow,
//							connection, results);
//				} else if (tempWipRow instanceof IndexWIPRow) {
//					saveResult = processIndexWIPRowSave(
//							(IndexWIPRow) tempWipRow, connection, results);
//				}
//				if (!saveResult)
//					errorCount++;
//				// truncate error return at 20 messages
//				if ( errorCount >= 20 ) {
//					buildReturnError(results, " ", new Exception("more..."));
//					break;
//				}
//			}
//
//			if ( pma != null ) {
//				pma.clean(connection);
//				pma = null;
//			}
//			if ( ima != null ) {
//				ima.clean(connection);
//				ima = null;
//			}
//
//			// T0093 - handle relationships found by ppm preprocessor
//			CommonWIPPostProcessor ppm = new CommonWIPPostProcessor(connection, wipRow.getProjectName(), wipRow.getChangeUserId(), fromPALoader);
//			ppm.handleRelationships(associatedSubsets, disassociatedSubsets);
//			
//			PlanWIPPostProcessor planPPM = new PlanWIPPostProcessor(connection, wipRow.getProjectName(), wipRow.getChangeUserId(), fromPALoader);
//			planPPM.handleRelationships(planAssociatedSubsets, planDisassociatedSubsets);
//			
//			createNewOrphans(insertedSubsets, wipRow.getProjectName(), wipRow.getChangeUserId(), connection);
//			int mask = Integer.parseInt(wipRow.getAttrMask());
//			boolean allowFullDelete = (mask & Constants.TABLE_ATTR_ALLOW_FULL_DELETE) != 0;
//			deleteOldOrphans(deletedSubsets, wipRow.getProjectName(), wipRow.getChangeUserId(), connection, allowFullDelete);
//			
//		} catch (WIPRowsException e) {
//			errorCount = -1;
//			errorMessage = e.getMessage();
//			throw e;
//		} catch (Exception e) {
//			if (e.getMessage()!=null && e.getMessage().trim().equals(Constants.SHARED_SUBSET_MSG)) {
//				errorCount = -2;
//				errorMessage = Constants.SHARED_SUBSET_MSG;
//			} else {
//				errorCount = -1;
//				errorMessage = e.getMessage();
//				throw WrapperException.wrap(e);
//			}
//		} finally {
//			try {
//				if ( pma != null )
//					pma.clean(connection);
//				if ( ima != null )
//					ima.clean(connection);
//
//				// T0093 - all or nothing now
//				//if ( errorCount != 0 )
//					//connection.rollback();
//				
//				closeStatements();
//			} catch (Exception e) {
//				errorCount = -1;
//				errorMessage = e.getMessage();
//				throw WrapperException.wrap(e);
//			}
//			results.insert(0, errorCount + Constants.STAB + errorMessage
//					+ Constants.SEOL);
//			outString = results.toString();
//		
//			SqlPW.logFinish("saveToWIP", startTime);
//			if (_log.isDebugEnabled())
//				_log.debug("Save Errors/Results=" + outString);
//
//		}
		return outString;
	}

	private void validateDuplicateCompany(WIPRow tempWipRow) throws Exception {
		Hashtable<String, String> _companyCodesAndNames = null;
		_companyCodesAndNames = EnvironmentManager
				.getInstance()
				.getEnvironment(tempWipRow.getEnvironment()).getAssistent()
				.getCompanyCodesAndNames();
		if (_companyCodesAndNames.containsKey(tempWipRow.getNewConcatKey()
				.trim())) {
			throw new WIPRowsException("Company with Company code "
					+ tempWipRow.getNewConcatKey().trim() + " already exists");
		}
	}

	// CCCV-E199 - collect updated and (potentially) deleted subsets
	private void gatherSubsets(Connection wipConn, Set<String> insertedSubsets, Set<String> deletedSubsets, WIPRow wipRow)
			throws Exception {
		if ( !SpecialHandling.getInstance().isPlanRelatedSubsetTable(wipRow.getDdlName()) ) {
			return;
		}

		String operation = wipRow.getOperation();
		String key = wipRow.getEnvironment() + Constants.STAB
			+ wipRow.getCompanyCode() + Constants.STAB
			+ wipRow.getProductPrefix() + Constants.STAB
			+ wipRow.getTableId() + Constants.STAB
			+ wipRow.getSubsetName();
		
		Row oldrow = null;
		String oldkey = null;
		if ( isDelete(wipRow.getWipActionAttrMask()) || isUpdate(wipRow.getWipActionAttrMask() ) ) {
			CommonWIPRow commonRow = getRowFromCommonWIP(wipConn, wipRow.getOldConcatKey(), wipRow);
			if ( commonRow != null ) {
				oldrow = (new RowManager(wipRow.getEnvironment())).getBlankRow(wipRow.getTableId());
				oldrow.setColumns(commonRow.getKeyAndDataConcatenated(false));
				
				oldkey = wipRow.getEnvironment() + Constants.STAB
					+ wipRow.getCompanyCode() + Constants.STAB
					+ wipRow.getProductPrefix() + Constants.STAB
					+ wipRow.getTableId() + Constants.STAB
					+ oldrow.getTableSubset();
				operation = commonRow.getOperation();
			}
		} else if ( isInsert(wipRow.getWipActionAttrMask()) ) {
			oldrow = (new RowManager(wipRow.getEnvironment())).getBlankRow(wipRow.getTableId());
			oldrow.setColumns(wipRow.getKeyAndDataConcatenated(false));
			
			oldkey = wipRow.getEnvironment() + Constants.STAB
				+ wipRow.getCompanyCode() + Constants.STAB
				+ wipRow.getProductPrefix() + Constants.STAB
				+ wipRow.getTableId() + Constants.STAB
				+ oldrow.getTableSubset();
		}
		
		if ( isInsert(wipRow.getWipActionAttrMask()) ) {
			if ( operation.equals(Constants.INSERT_OPERATION) ) {
				insertedSubsets.add(key);
			} else if ( operation.equals(Constants.DELETE_OPERATION) ) {
				deletedSubsets.add(key);
			} else if ( operation.equals(Constants.UPDATE_OPERATION) ) {
				if ( oldrow == null ) {
					insertedSubsets.add(key);
				} else if ( !oldrow.getTableSubset().trim().equals(wipRow.getSubsetName().trim()) ) {
					insertedSubsets.add(key);
					deletedSubsets.add(oldkey);
				}
			}
		} else if ( isDelete(wipRow.getWipActionAttrMask()) ) {
			if ( operation.equals(Constants.INSERT_OPERATION) ) {
				deletedSubsets.add(key);
			} else if ( operation.equals(Constants.DELETE_OPERATION) ) {
				// can this happen?
				insertedSubsets.add(key);
			} else if ( operation.equals(Constants.UPDATE_OPERATION) ) {
				if ( oldrow == null ) {
					deletedSubsets.add(key);
				} else if ( !oldrow.getTableSubset().trim().equals(wipRow.getSubsetName().trim()) ) {
					insertedSubsets.add(oldkey);
					deletedSubsets.add(key);
				}
			}
		} else if ( isUpdate(wipRow.getWipActionAttrMask()) ) {
			if ( !oldrow.getTableSubset().trim().equals(wipRow.getSubsetName().trim()) ) {
				if ( operation.equals(Constants.INSERT_OPERATION) ) {
					// can this happen?
					insertedSubsets.add(key);
					deletedSubsets.add(oldkey);
				} else if ( operation.equals(Constants.DELETE_OPERATION) ) {
					// can this happen?
					insertedSubsets.add(oldkey);
					deletedSubsets.add(key);
				} else if ( operation.equals(Constants.UPDATE_OPERATION) ) {
					insertedSubsets.add(oldkey);
					deletedSubsets.add(key);
				}
			}
		}
	}
		
	// CCCV-E199 - find existing common wip row
	protected CommonWIPRow getRowFromCommonWIP(Connection wipConn, String oldConcatKey, WIPRow wipRow) throws Exception {

		WIPTableFilter wipFilter = new WIPTableFilter();

		wipFilter.setEnvironment(wipRow.getEnvironment());
		wipFilter.setCompanyCode(wipRow.getCompanyCode());
		wipFilter.setProductPrefix(wipRow.getProductPrefix());
		wipFilter.setOldConcatKey(oldConcatKey);
		Vector tableIds = new Vector();

		tableIds.addElement(wipRow.getTableId());
		wipFilter.setTableIds(tableIds);
		CommonWIPRow commonRow = null;

		
		WIPRows wipRows = new WIPRows(wipConn, wipFilter, null, false);
		if (wipRows.size() != 0)
			commonRow = (CommonWIPRow) wipRows.get(0);
		
		return commonRow;
	}
	
	// CCCV-E199 - create new orphans if required
	private void createNewOrphans(Set<String> insertedSubsets, String projectName,
			String userId, Connection wipConn) throws Exception
	{
		
//			WIPRows indexWIPRows = new WIPRows();
//			Iterator<String> it = insertedSubsets.iterator();
//			while ( it.hasNext() ) {
//				String key = it.next();
//				String[] tokens = key.split(Constants.STAB);
//				String envId = tokens[0];
//				String companyCode = tokens[1];
//				String productPrefix = tokens[2];
//				String tableId = tokens[3];
//				String subset = tokens[4];
//				
//				Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
//				WhereUsedAssistent wua = new WhereUsedAssistent(environment);
//				// refactor plan key
//				PlanCriteriaTO plan = new PlanCriteriaTO();
//				plan.setEnvironment(envId);
//				plan.setCompanyCode(companyCode);
//				plan.setProductPrefix(productPrefix);
//				plan.setTablePtrId(tableId);
//				plan.setTablePtrSubset(subset);
//				plan.setViewChanges(true);
//				//boolean subsetPointedTo = wua.isSubsetPointedTo(companyCode, tableId, subset, productPrefix, "with");
//				boolean subsetPointedTo = wua.isSubsetPointedTo(wipConn, plan);
//				if ( !subsetPointedTo ) {
//					boolean subsetExists = subsetHasRows(environment.getId(),
//					        companyCode, productPrefix, tableId, subset, wipConn);
//					if ( subsetExists ) {
//						// create orphan
//						// T0093 use IndexWIPAssistant						
//						IndexWIPAssistant iwa = new IndexWIPAssistant(envId, companyCode, productPrefix,
//								projectName, userId.toUpperCase(), wipConn);
//						WIPRows tempRows = iwa.createOrphan(tableId, subset, false);
//						indexWIPRows.addAll(tempRows);
//					}
//				}
//			}
//
//			if ( indexWIPRows.size() > 0 ) {
//				if ( fromPALoader ) {
//					for (WIPRow wipRow : indexWIPRows) {
//					 	wipRow.setPromotionLock("L");
//					}
//				}
//				indexWIPRows.saveToWIPAsBatch(wipConn);
//			}
		
	}
	
	// CCCV-E199 - delete obsolete orphans if required
	private void deleteOldOrphans(Set<String> deletedSubsets, String projectName,
			String userId, Connection wipConn, boolean allowFullDelete) throws Exception
	{
//		
//			WIPRows indexWIPRows = new WIPRows();
//			Iterator<String> it = deletedSubsets.iterator();
//			while ( it.hasNext() ) {
//				String key = it.next();
//				String[] tokens = key.split(Constants.STAB);
//				String envId = tokens[0];
//				String companyCode = tokens[1];
//				String productPrefix = tokens[2];
//				String tableId = tokens[3];
//				String subset = tokens[4];
//				
//				Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
//
//				boolean subsetExists = subsetHasRows(environment.getId(),
//				        companyCode, productPrefix, tableId, subset, wipConn);
//				if ( !subsetExists ) {
//					WhereUsedAssistent wua = new WhereUsedAssistent(environment);
//					// refactor plan key
//					PlanCriteriaTO plan = new PlanCriteriaTO();
//					plan.setEnvironment(envId);
//					plan.setCompanyCode(companyCode);
//					plan.setProductPrefix(productPrefix);
//					plan.setTablePtrId(tableId);
//					plan.setTablePtrSubset(subset);
//					plan.setViewChanges(true);
//					//boolean orphanExists = wua.isSubsetPointedToAsOrphan(companyCode, tableId, subset, productPrefix, "with");
//					boolean subsetPointedTo = wua.isSubsetPointedTo(wipConn, plan);
//					boolean orphanExists = wua.isSubsetPointedToAsOrphan(wipConn, plan);
//					// T0093 - don't allow complete delete of subset pointed to by plan
//					if ( subsetPointedTo && !orphanExists ) {
//						// ENH1043 - conditionally allow complete delete
//						if ( !allowFullDelete ) {
//							String tableName = environment.getTableDescMgr().getDDLName(tableId);
//							throw new InfoException("<html>" + tableName + " table subset '" + subset.trim() + "' is pointed to by a plan or by another subset,"
//									+ "<br>and cannot be entirely deleted."
//									+ "<br><br>" + Constants.DELETE_OVERRIDE_MSG + "</html>");
//						}
//					} else if ( orphanExists ) {
//						// T0093 use IndexWIPAssistant						
//						IndexWIPAssistant iwa = new IndexWIPAssistant(envId, companyCode, productPrefix,
//								projectName, userId.toUpperCase(), wipConn);
//						WIPRows tempRows = iwa.deleteOrphan(tableId, subset);
//						indexWIPRows.addAll(tempRows);
//					}
//				}
//			}
//			if ( indexWIPRows.size() > 0 ) {
//				if ( fromPALoader ) {
//					for (WIPRow wipRow : indexWIPRows) {
//					 	wipRow.setPromotionLock("L");
//					}
//				}
//				indexWIPRows.saveToWIPAsBatch(wipConn);
//			}
//		
	}
	
	// CCCV-E199 - does subset have rows in WIP
//	private boolean isSubsetInWIP(String envId, String companyCode, String productPrefix,
//			String tableId,	String subset) throws Exception {
//		WIPTableFilter wtf = new WIPTableFilter();
//		wtf.setEnvironment(envId);
//		wtf.setCompanyCode(companyCode);
//		wtf.setProductPrefix(productPrefix);
//		wtf.setSubsetName(subset);
//		Vector<String> tableIds = new Vector<String>();
//		tableIds.add(tableId);
//		wtf.setTableIds(tableIds);
//		WIPRows wipRows = new WIPRows(wtf, null, false);
//		Iterator it = wipRows.iterator();
//		while ( it.hasNext() ) {
//			WIPRow row = (WIPRow) it.next();
//			if ( row.getOperation().equals(Constants.INSERT_OPERATION) ||
//					row.getOperation().equals(Constants.UPDATE_OPERATION) ) {
//				return true;
//			}
//		}
//		return false; 
//	}

	// CCCV-E199 - return true if subset has rows in BR or in WIP
	private boolean subsetHasRows(String envId, String companyCode, String productPrefix,
			String tableId,	String subset, Connection wipConn) throws Exception {
		Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
		TableRowCounter trc = new TableRowCounter(environment);
		// TT wma SPR 6579 - use trc.subsetExists
		return trc.subsetExists(wipConn, tableId, companyCode, productPrefix, subset, null);
	}
	
//	// CCCV-E199 - utility used when creating concat key
//	private String padSpaces(String data, int length) throws Exception {
//
//		StringBuffer sb = new StringBuffer(data);
//		int initialLength = sb.length();
//
//		if (initialLength < length) {
//			for (int i = 0; i < (length - initialLength); i++)
//				sb.append(" ");
//		}
//
//		return sb.toString();
//	}
	
	private boolean processCommonWIPRowSave(CommonWIPRow deletedRow,
			CommonWIPRow tempWipRow, boolean ppmRequired, Connection wipConn,
			StringBuffer results) {
		boolean status = false;
//		try {
//			int attr_mask = tempWipRow.getWipActionAttrMask();
//			boolean insert = isInsert(attr_mask);
//			boolean update = isUpdate(attr_mask);
//			boolean delete = isDelete(attr_mask);
//
//			if (insert) {
//				initializeCommonInsertStmt(wipConn);
//				tempWipRow.save(commonInsertStmt, Constants.INSERT_OPERATION,
//						this);
//			} else if (update) {
//				initializeCommonUpdateStmt(wipConn);
//				tempWipRow.save(commonUpdateStmt, Constants.UPDATE_OPERATION,
//						this);
//			} else if (delete) {
//				initializeCommonDeleteStmt(wipConn);
//				tempWipRow.save(commonDeleteStmt, Constants.DELETE_OPERATION,
//						this);
//			} else {
//				throw new WIPRowsException("Invalid WIPAction");
//			}
//
//			if (ppmRequired) {
//				// T0093 - ppm now handled by CommonWIPPreProcessor
//				//PointerChangesPreProcessor ppm = new PointerChangesPreProcessor(
//				//		tempWipRow, deletedRow, wipConn);
//				CommonWIPPreProcessor ppm = new CommonWIPPreProcessor(tempWipRow,
//						associatedSubsets, disassociatedSubsets, wipConn);
//				ppm.process();
//			}
//			status = true;
//			buildReturnString(tempWipRow, results);
//		} catch (Exception e) {
//			buildReturnError(results, tempWipRow.getRowIndex(), e); // SPR2318
//			// don't throw an error: just build results for user
//			// throw WrapperException.wrap(e);
//
//		}
		return status;
	}

	private boolean processPlanWIPRowSave(PlanWIPRow tempWipRow,
			Connection wipConn, StringBuffer results) {
		boolean status = false;
//		try {
//			int attr_mask = tempWipRow.getWipActionAttrMask();
//			boolean insert = isInsert(attr_mask);
//			boolean update = isUpdate(attr_mask);
//			boolean delete = isDelete(attr_mask);
//
//			if (insert) {
//				initializePlanInsertStmt(wipConn);
//				tempWipRow.save(planInsertStmt, Constants.INSERT_OPERATION,
//						this);
//			} else if (update) {
//				initializePlanUpdateStmt(wipConn);
//				tempWipRow.save(planUpdateStmt, Constants.UPDATE_OPERATION,
//						this);
//			} else if (delete) {
//				initializePlanDeleteStmt(wipConn);
//				tempWipRow.save(planDeleteStmt, Constants.DELETE_OPERATION,
//						this);
//			} else {
//				throw new WIPRowsException("Invalid WIPAction");
//			}
//			PlanWIPPreProcessor ppm = new PlanWIPPreProcessor(tempWipRow,
//					planAssociatedSubsets, planDisassociatedSubsets);
//			ppm.process();
//			status = true;
//			buildReturnString(tempWipRow, results);
//		} catch (Exception e) {
//			buildReturnError(results, tempWipRow.getRowIndex(), e); // SPR2318
//			// don't throw an error: just build results for user
//			// throw WrapperException.wrap(e);
//		}
		return status;
	}

	private boolean processIndexWIPRowSave(IndexWIPRow tempWipRow,
			Connection wipConn, StringBuffer results) {
		boolean status = false;
		try {
			int attr_mask = tempWipRow.getWipActionAttrMask();
			boolean insert = isInsert(attr_mask);
			boolean update = isUpdate(attr_mask);
			boolean delete = isDelete(attr_mask);

			if (insert) {
				initializeIndexInsertStmt(wipConn);
				tempWipRow.save(indexInsertStmt, Constants.INSERT_OPERATION,
						this);
			} else if (update) {
				initializeIndexUpdateStmt(wipConn);
				tempWipRow.save(indexUpdateStmt, Constants.UPDATE_OPERATION,
						this);
			} else if (delete) {
				initializeIndexDeleteStmt(wipConn);
				tempWipRow.save(indexDeleteStmt, Constants.DELETE_OPERATION,
						this);
			} else {
				throw new WIPRowsException("Invalid WIPAction");
			}
			status = true;
			buildReturnString(tempWipRow, results);
		} catch (Exception e) {
			buildReturnError(results, tempWipRow.getRowIndex(), e); // SPR2318
			// don't throw an error: just build results for user
			// throw WrapperException.wrap(e);
		}
		return status;
	}

	/**
	 * @param wipRow
	 * @param nextRow
	 * @return
	 */
	private boolean isPPMNecessary(WIPRow wipRow, WIPRow nextRow) {
		if (!isDelete(wipRow.getWipActionAttrMask()))
			return true;
		if (!isInsert(nextRow.getWipActionAttrMask()))
			return true;
		if (!(wipRow.getOldConcatKey().equals(nextRow.getOldConcatKey())))
			return true;
		if (nextRow.getOperation().equals("UPDATE"))
			return false;
		return true;
	}

	public void saveToWIPAsBatch() throws Exception {
//		new WipRowsBatchSave().saveToWIPAsBatch(this);
//		Connection connection = null;
//		try {
//	
//			String envId = ((WIPRow)get(0)).getEnvironment();
//			connection = DBConnMgr.getInstance().getConnection(envId,DBConnMgr.APPL);
//			connection.setAutoCommit(false);
//			boolean ok = saveToWIPAsBatch(connection);
//			if (ok) {
//				connection.commit();
//			} else {
//				connection.rollback();
//			}
//
//		} catch (WIPRowsException wre) {
//			try {
//				connection.rollback();
//			} catch (SQLException se) {
//			}
//			throw wre;
//		} catch (Exception e) {
//			try {
//				connection.rollback();
//			} catch (SQLException se) {
//			}
//			throw WrapperException.wrap(e);
//		} finally {
//			try {
//				if (connection != null) {
//					DBConnMgr.getInstance().releaseConnection(connection);
//				}
//			} catch (Exception e) {
//			}
//		}

	}

	/**
	 * This method will save the WIPRows it contains to the appropriate WIP
	 * using Batch Update SQL syntax it builds with the help of SQL building
	 * methods on the WIPRow. If it fails for any reason, no updates will be
	 * made and a WIPRowsException will be thrown. NOTE: Plan Pointer
	 * Maintenance will NOT be invoked. Creation date: (10/09/2001 2:18:03 PM)
	 * 
	 * @throws WIPRowsException
	 */

	public void saveToWIPAsBatch(Connection connection)
			throws Exception {
		
//		new WipRowsBatchSave().saveToWIPAsBatch(this,connection);

//		if (size() == 0) {
//			throw new WIPRowsException("No rows to save");
//		}
//
//
//		int attr_mask = 0;
//
//		long startTime = SqlPW.logStart("saveToWIPAsBatch");
//		try {
//
//			setPreparedStatements(((WIPRow) get(0)).getEnvironment());
//
//			for (int i = 0; i < size(); i++) {
//				WIPRow wipRow = (WIPRow) get(i);
//				String prjName = wipRow.getProjectName().toUpperCase();
//				wipRow.setProjectName(prjName);
//
//				if (wipRow.getProjectName().trim().length() == 0) {
//					throw new WIPRowsException("Blank Project");
//				}
//
//				if (wipRow.getProductPrefix().equalsIgnoreCase("N")) {
//					wipRow.setProductPrefix(getProductPrefix(wipRow
//							.getDdlName()));
//				}
//				// wipRow.setProductPrefix(SpecialHandling.getInstance().getProductPrefix(wipRow.getDdlName()));
//
//				if (wipRow instanceof PlanWIPRow) {
//					PlanWIPRow planWipRow = (PlanWIPRow) wipRow;
//
//					attr_mask = planWipRow.getWipActionAttrMask();
//
//					if (isInsert(attr_mask)) {
//						initializePlanInsertStmt(connection);
//						planWipRow.setValuesForInsert(planInsertStmt, this);
//						planInsertStmt.addBatch();
//					} else if (isUpdate(attr_mask)) {
//						initializePlanUpdateStmt(connection);
//						planWipRow.setValuesForUpdate(planUpdateStmt,
//								requestFilter);
//						planUpdateStmt.addBatch();
//					} else if (isDelete(attr_mask)) {
//						initializePlanDeleteStmt(connection);
//						planWipRow.setValuesForDelete(planDeleteStmt,
//								requestFilter);
//						planDeleteStmt.addBatch();
//					} else {
//						// log.error("INVALID ACTION!!!!!!!");
//					}
//
//				} else if (wipRow instanceof IndexWIPRow) {
//					IndexWIPRow indexWipRow = (IndexWIPRow) wipRow;
//
//					attr_mask = indexWipRow.getWipActionAttrMask();
//
//					if (isInsert(attr_mask)) {
//						initializeIndexInsertStmt(connection);
//						indexWipRow.setValuesForInsert(indexInsertStmt, this);
//						indexInsertStmt.addBatch();
//					} else if (isUpdate(attr_mask)) {
//						initializeIndexUpdateStmt(connection);
//						indexWipRow.setValuesForUpdate(indexUpdateStmt,
//								requestFilter);
//						indexUpdateStmt.addBatch();
//					} else if (isDelete(attr_mask)) {
//						initializeIndexDeleteStmt(connection);
//						indexWipRow.setValuesForDelete(indexDeleteStmt,
//								requestFilter);
//						indexDeleteStmt.addBatch();
//					} else {
//						// log.error("INVALID ACTION!!!!!!!");
//					}
//				} else if (wipRow instanceof CommonWIPRow) {
//					CommonWIPRow commonWipRow = (CommonWIPRow) wipRow;
//
//					attr_mask = commonWipRow.getWipActionAttrMask();
//
//					if (isInsert(attr_mask)) {
//						initializeCommonInsertStmt(connection);
//						commonWipRow.setValuesForInsert(commonInsertStmt, this);
//						commonInsertStmt.addBatch();
//					} else if (isUpdate(attr_mask)) {
//						initializeCommonUpdateStmt(connection);
//						commonWipRow.setValuesForUpdate(commonUpdateStmt,
//								requestFilter);
//						commonUpdateStmt.addBatch();
//					} else if (isDelete(attr_mask)) {
//						initializeCommonDeleteStmt(connection);
//						commonWipRow.setValuesForDelete(commonDeleteStmt,
//								requestFilter);
//						commonDeleteStmt.addBatch();
//					} else {
//						// log.error("INVALID ACTION!!!!!!!");
//					}
//				}
//			}
//			// execute batch here
//			return executeStmts();
//
//		} catch (SQLException e) {
//			String message = "";
//
//			if (e.getMessage().indexOf("SQL0803N") != -1) {
//				message = Constants.DUPLICATE_ROW_MSG;
//			} else if (e.getMessage().indexOf("SQL0100W") != -1) {
//				message = Constants.ROW_NOT_FOUND_MSG;
//			} else {
//				message = e.getMessage();
//			}
//			throw WrapperException.wrap(e);
//		} catch (Exception e) {
//			throw WrapperException.wrap(e);
//		} finally {
//			try {
//				closeStatements();
//				// rts Maintain connection accross all SQL inserts
//				// rtsif (connection != null) {
//				// rts dbconn.releaseConnection(connection);
//				// rts}
//			} catch (Exception e) {
//				throw WrapperException
//						.wrap(e);
//			}
//			SqlPW.logFinish("saveToWIPAsBatch", startTime);
//		}
	}

	public WIPTableFilter getRequestFilter() {
		return requestFilter;
	}

	private void init() {
		return;
	}

	/**
	 * This method will control the creation of the various PreparedStatements
	 * needed Creation date: (10/10/2001 5:28:17 PM)
	 */

	private void setPreparedStatements(String envId) throws Exception {

	
			insertSQL = WIPRow.getSaveSQL(envId,Constants.INSERT_OPERATION);
			updateSQL = WIPRow.getSaveSQL(envId,Constants.UPDATE_OPERATION);
			deleteSQL = WIPRow.getSaveSQL(envId,Constants.DELETE_OPERATION);

	
	}

	private void initializeCommonDeleteStmt(Connection conn)
			throws SQLException {

		if (commonDeleteStmt == null) {
			commonDeleteStmt = conn.prepareStatement(deleteSQL[0]);
		}
	}

	private void initializeCommonInsertStmt(Connection conn)
			throws SQLException {

		if (commonInsertStmt == null) {
			commonInsertStmt = conn.prepareStatement(insertSQL[0]);
		}
	}

	private void initializeCommonUpdateStmt(Connection conn)
			throws SQLException {

		if (commonUpdateStmt == null) {
			commonUpdateStmt = conn.prepareStatement(updateSQL[0]);
		}
	}

	private void initializeIndexDeleteStmt(Connection conn) throws SQLException {

		if (indexDeleteStmt == null) {
			indexDeleteStmt = conn.prepareStatement(deleteSQL[2]);
		}
	}

	private void initializeIndexInsertStmt(Connection conn) throws SQLException {

		if (indexInsertStmt == null) {
			indexInsertStmt = conn.prepareStatement(insertSQL[2]);
		}
	}

	private void initializeIndexUpdateStmt(Connection conn) throws SQLException {

		if (indexUpdateStmt == null) {
			indexUpdateStmt = conn.prepareStatement(updateSQL[2]);
		}
	}

	private void initializePlanDeleteStmt(Connection conn) throws SQLException {

		if (planDeleteStmt == null) {
			planDeleteStmt = conn.prepareStatement(deleteSQL[1]);
		}
	}

	private void initializePlanInsertStmt(Connection conn) throws SQLException {

		if (planInsertStmt == null) {
			planInsertStmt = conn.prepareStatement(insertSQL[1]);
		}
	}

	private void initializePlanUpdateStmt(Connection conn) throws SQLException {

		if (planUpdateStmt == null) {
			planUpdateStmt = conn.prepareStatement(updateSQL[1]);
		}
	}

	private void buildReturnString(WIPRow wipRow, StringBuffer results) {

		results.append(Constants.SUCCESSFUL_SAVE + Constants.STAB);
		results.append(wipRow.getRowIndex() + Constants.STAB);
		// ENH961 - pass oldData back to client
		results.append(wipRow.getOldConcatKey() + Constants.STAB);
		results.append(wipRow.getOldData() + Constants.SEOL);
	}

	private void buildReturnError(StringBuffer results, String rowIndex,
			Exception e) {

		results.append(Constants.ERRORED_SAVE + Constants.STAB);
		results.append(rowIndex + Constants.STAB);
		// remove any newlines in message.  Newline is message delimiter
		String msg = e.getMessage();
		if ( msg != null )
			msg = msg.replace('\n', ' ');
		results.append(msg + Constants.SEOL);
	}

	/**
	 * This method closes any prepared statements Creation date: (10/09/2001
	 * 4:54:30 PM)
	 */

	private void closeStatements() {
		// throws WIPRowsException {

		Utils.closeStatement(commonDeleteStmt);
		Utils.closeStatement(commonInsertStmt);
		Utils.closeStatement(commonUpdateStmt);

		Utils.closeStatement(planDeleteStmt);
		Utils.closeStatement(planInsertStmt);
		Utils.closeStatement(planUpdateStmt);

		Utils.closeStatement(indexDeleteStmt);
		Utils.closeStatement(indexInsertStmt);
		Utils.closeStatement(indexUpdateStmt);
	}

	/**
	 * This method executes the appropriate prepared statement Creation date:
	 * (10/28/2001 4:54:30 PM)
	 */

//	private boolean executeStmts() throws SQLException {
//
//		boolean status = true;
//		int[] execCount = null;
//
//		try {
//			if (commonDeleteStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_COMMON_DEL,
//						commonDeleteStmt);
//			}
//			if (commonInsertStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_COMMON_INS,
//						commonInsertStmt);
//			}
//			if (commonUpdateStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_COMMON_UPD,
//						commonUpdateStmt);
//			}
//			if (planDeleteStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_PLAN_DEL, planDeleteStmt);
//			}
//			if (planInsertStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_PLAN_INS, planInsertStmt);
//			}
//			if (planUpdateStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_PLAN_UPD, planUpdateStmt);
//			}
//			if (indexDeleteStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_INDEX_DEL,
//						indexDeleteStmt);
//			}
//			if (indexInsertStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_INDEX_INS,
//						indexInsertStmt);
//			}
//			if (indexUpdateStmt != null) {
//				execCount = SqlPW.batch(SqlPW.SQL_WIP_INDEX_UPD,
//						indexUpdateStmt);
//			}
//
//		} catch (SQLException e) {
//			status = false;
//			throw e;
//		}
//		return status;
//	}

	/**
	 * This method locks rows in the WIP for a promotion, given the passed
	 * filter, and updates the filter content to reflect the locked status.
	 * Creation date: (10/25/2001 3:45:17 PM)
	 * 
	 * @param filter
	 *            com.csc.fsg.life.pw.web.io.WIPTableFilter
	 * @exception java.sql.SQLException
	 *                The exception description.
	 */

	private void lockRows(Connection connection) throws Exception {

		// check if a promotion is already in progress for this environment
		String envId = requestFilter.getEnvironment();
		Vector<String> projects = requestFilter.getProjectNames();

		if ((envId == null) || (envId.length() == 0)) {
			throw new WIPRowsException(PWIOException.INVALID_ENVIRONMENT_SCHEMA);
		}

		String error = WIPRow
			.getPromotionAlreadyInProgressMessage(EnvironmentManager.getInstance().getEnvironment(envId), projects, true);
		if ( error != null ) {
			throw new WIPRowsException(error);
		}

		// lock rows based on passed filter
		HashMap<Integer, String> sqls = null;
		Statement stmt = null;
		int updateCount = 0;

		try {
			sqls = WIPRow.getLockSQL(requestFilter);

			Set<Map.Entry<Integer, String>> entries = sqls.entrySet();
			for ( Map.Entry<Integer, String> entry : entries ) {
				Integer wipTable = entry.getKey();
				String sql = entry.getValue();

				if (sql == null) {
					throw new WIPRowsException("SQL is missing");
				}
				sql = sql.trim();
				stmt = connection.createStatement();
				updateCount = SqlPW.update(SqlPW.SQL_PROMOTE_LOCK, stmt, sql);
			}
			requestFilter.setLocked(true);
	
		} finally {
			Utils.closeStatement(stmt);
		}
	}

	/**
	 * This static method executes the generated update SQL
	 */

	private static void executeUpdateSQL(String envId,HashMap<Integer, String> sqls) throws WIPRowsException,Exception {

		Connection connection = null;
		try {
			connection = DBConnMgr.getInstance().getConnection(envId,DBConnMgr.APPL);
			executeUpdateSQL(connection,envId,sqls);
		} finally {
			DBConnMgr.getInstance().releaseConnection(connection);
		}
	}
	
	
	private static void executeUpdateSQL(Connection connection, String envId,HashMap<Integer, String> sqls) throws WIPRowsException,Exception {

		Statement stmt = null;
		try {
			Set<Map.Entry<Integer, String>> entries = sqls.entrySet();
			for ( Map.Entry<Integer, String> entry : entries ) {
				Integer wipTable = entry.getKey();
				String sql = entry.getValue();

				if (sql == null) {
					throw new WIPRowsException("SQL is missing");
				}
				sql = sql.trim();
				stmt = connection.createStatement();
				int updateCount = SqlPW.update(SqlPW.WIP_ROWS_UPDATE, stmt, sql);
			}
		
		} finally {
			Utils.closeStatement(stmt);
		}

	}

	private boolean isUpdate(int attr_mask) {
		return ((attr_mask & Constants.TABLE_ATTR_UPDATE) != 0);
	}

	private boolean isDelete(int attr_mask) {
		return ((attr_mask & Constants.TABLE_ATTR_DELETE) != 0);
	}

	private boolean isInsert(int attr_mask) {
		return ((attr_mask & Constants.TABLE_ATTR_INSERT) != 0);
	}

	/**
	 * This method will check if the subset is shared among multiple Plans. If
	 * true, and the TABLE_ATTR_SHARED override is not set, it will throw an
	 * exception.
	 */

	private void isSharedSubset(WIPRow wipRow,Connection conn) throws NumberFormatException,
			Exception {

//		boolean busRuleView = (wipRow.getWipActionAttrMask() & Constants.TABLE_ATTR_BUS_RULE_VIEW) != 0;
//		if (!busRuleView) {
//			// REQUEST NOT FROM BUSINESS RULE SEARCH
//			return;
//		}
//
//		if (!SpecialHandling.getInstance().isPlanRelatedSubsetTable(
//				wipRow.getDdlName())) {
//			return;
//		}
//
//		if ((Integer.parseInt(wipRow.getAttrMask()) & Constants.TABLE_ATTR_SHARED) == 0) {
//
//			if (wipRow instanceof CommonWIPRow) {
//	
//
//				if (SpecialHandling.getInstance().isCommonTable(wipRow.getDdlName())) 
//				{
//				} else 
//				{
//					WhereUsedAssistent wua = new WhereUsedAssistent(EnvironmentManager.getInstance().getEnvironment(wipRow.getEnvironment()));
//					// refactor plan key.
//					PlanCriteriaTO planCriteria = new PlanCriteriaTO();
//					planCriteria.setEnvironment(wipRow.getEnvironment());
//					planCriteria.setCompanyCode(wipRow.getCompanyCode());
//					planCriteria.setProductPrefix(wipRow.getProductPrefix());
//					planCriteria.setTablePtrId(wipRow.getTableId());
//					planCriteria.setTablePtrSubset(wipRow.getSubsetName());
//					planCriteria.setViewChanges(true);
//					boolean shared = wua.isSharedSubset(conn,planCriteria);
//					/*
//					boolean shared = wua.isSharedSubset(wipRow.getCompanyCode(), wipRow
//							.getTableId(), wipRow.getSubsetName(), wipRow
//							.getProductPrefix(), "WITH");
//					*/
//					if ( shared )
//						throw new InfoException(Constants.SHARED_SUBSET_MSG);
//				}
//			}
//		}
	}

	private String getProductPrefix(String ddlName) {

		String pPrx = "N";
		SpecialHandling sph = SpecialHandling.getInstance();

		if (sph.isCommonTable(ddlName)) {
			pPrx = "C";
		} else {
			String pp = sph.getProductPrefix(ddlName);

			if (pp != null) {
				pPrx = pp;
			}
		}
		return pPrx;
	}

	/**
	 * @return
	 */
	public boolean isNextInd() {
		return nextInd;
	}

	/**
	 * @return
	 */
	public boolean isPrevInd() {
		return prevInd;
	}

	/**
	 * @param b
	 */
	public void setNextInd(boolean b) {
		nextInd = b;
	}

	/**
	 * @param b
	 */
	public void setPrevInd(boolean b) {
		prevInd = b;
	}

	// DBSeq duplicate-Fix

	private static Vector<String> dbseqDuplicateCheck = new Vector<String>();

	long wipRowCounter = 0;

	public synchronized long getDBSequenceNumber() {
		// Maintain the size of the vector as 1000
		if (dbseqDuplicateCheck.size() > 1000)
			dbseqDuplicateCheck.removeElementAt(0);
		++wipRowCounter;
		if (wipRowCounter > 100)
			wipRowCounter = 0;

		String s = Long.valueOf(System.currentTimeMillis()).toString()
				+ wipRowCounter;

		// loop until you get a unique number
		while (s.length() > 16 || dbseqDuplicateCheck.contains(s)) {
			s = Long.valueOf(System.currentTimeMillis()).toString() + wipRowCounter;
		}

		dbseqDuplicateCheck.addElement(s);
		Long dbSeq = new Long(s);
		return dbSeq.longValue();
	}
	
	public void setFromPALoader(boolean fromPALoader) {
		this.fromPALoader = fromPALoader;
	}
	
	public boolean isFromPALoader() {
		return fromPALoader;
	}
}
