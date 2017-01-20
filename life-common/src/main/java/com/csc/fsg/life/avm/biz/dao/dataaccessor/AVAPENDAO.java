package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

import com.csc.fsg.life.avm.api.AVMIdDescPair;
import com.csc.fsg.life.avm.biz.dao.model.AVAPENModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Data access class for table AVAPEN.
 */
public class AVAPENDAO extends DataAccessor { 


	/**
	 * Inserts a new row into the database table.
	 * 
	 * @param aConnection The database connection object.
	 * @param aDAOModel The model object to insert.
	 * @param aString The database schema.
	 * @return Returns the SQL result.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */
    public int insert(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPENModel aAVAPENModel = ( AVAPENModel ) aDAOModel; 
        if (!aAVAPENModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String insertValues = ".AVAPEN (APPL_KEY, ENVRNMNT_KEY, DESCRIPTION, SUBSYSTEM, SCHEMA, FROM_PRMT_ENVRM, FROM_PRMT_DATE, FROM_PRMT_TIMESTAMP, FROM_PRMT_BY, FROM_PRMT_OPTION, FROM_PRMT_CHG_DATE, FROM_PRMT_CHG_PROJ, FROM_EXPORT_FILE, TO_PRMT_ENVRM, TO_PRMT_DATE, TO_PRMT_TIMESTAMP, TO_PRMT_BY, TO_PRMT_OPTION, TO_PRMT_CHG_DATE, TO_PRMT_CHG_PROJ, TO_EXPORT_FILE, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String sql = "INSERT INTO " + connSchema + insertValues;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPENModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPENModel.getApplKey()); 

            if (aAVAPENModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVAPENModel.getEnvrnmntKey()); 

            if (aAVAPENModel.getDescription() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVAPENModel.getDescription()); 

            if (aAVAPENModel.getSubsystem() == null) 
                prepStmt.setNull(4, Types.VARCHAR);
            else 
                prepStmt.setString(4, aAVAPENModel.getSubsystem()); 

            if (aAVAPENModel.getSchema() == null) 
                prepStmt.setNull(5, Types.VARCHAR);
            else 
                prepStmt.setString(5, aAVAPENModel.getSchema()); 

            if (aAVAPENModel.getFromPrmtEnvrm() == null) 
                prepStmt.setNull(6, Types.VARCHAR);
            else 
                prepStmt.setString(6, aAVAPENModel.getFromPrmtEnvrm()); 

            if (aAVAPENModel.getFromPrmtDate() == null) 
                prepStmt.setNull(7, Types.DATE);
            else 
                prepStmt.setDate(7, aAVAPENModel.getFromPrmtDate()); 

            if (aAVAPENModel.getFromPrmtTimestamp() == null) 
                prepStmt.setNull(8, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(8, aAVAPENModel.getFromPrmtTimestamp()); 

            if (aAVAPENModel.getFromPrmtBy() == null) 
                prepStmt.setNull(9, Types.VARCHAR);
            else 
                prepStmt.setString(9, aAVAPENModel.getFromPrmtBy()); 

            if (aAVAPENModel.getFromPrmtOption() == null) 
                prepStmt.setNull(10, Types.VARCHAR);
            else 
                prepStmt.setString(10, aAVAPENModel.getFromPrmtOption()); 

            if (aAVAPENModel.getFromPrmtChgDate() == null) 
                prepStmt.setNull(11, Types.DATE);
            else 
                prepStmt.setDate(11, aAVAPENModel.getFromPrmtChgDate()); 

            if (aAVAPENModel.getFromPrmtChgProj() == null) 
                prepStmt.setNull(12, Types.VARCHAR);
            else 
                prepStmt.setString(12, aAVAPENModel.getFromPrmtChgProj()); 

            if (aAVAPENModel.getFromExportFile() == null) 
                prepStmt.setNull(13, Types.VARCHAR);
            else 
                prepStmt.setString(13, aAVAPENModel.getFromExportFile()); 

            if (aAVAPENModel.getToPrmtEnvrm() == null) 
                prepStmt.setNull(14, Types.VARCHAR);
            else 
                prepStmt.setString(14, aAVAPENModel.getToPrmtEnvrm()); 

            if (aAVAPENModel.getToPrmtDate() == null) 
                prepStmt.setNull(15, Types.DATE);
            else 
                prepStmt.setDate(15, aAVAPENModel.getToPrmtDate()); 

            if (aAVAPENModel.getToPrmtTimestamp() == null) 
                prepStmt.setNull(16, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(16, aAVAPENModel.getToPrmtTimestamp()); 

            if (aAVAPENModel.getToPrmtBy() == null) 
                prepStmt.setNull(17, Types.VARCHAR);
            else 
                prepStmt.setString(17, aAVAPENModel.getToPrmtBy()); 

            if (aAVAPENModel.getToPrmtOption() == null) 
                prepStmt.setNull(18, Types.VARCHAR);
            else 
                prepStmt.setString(18, aAVAPENModel.getToPrmtOption()); 

            if (aAVAPENModel.getToPrmtChgDate() == null) 
                prepStmt.setNull(19, Types.DATE);
            else 
                prepStmt.setDate(19, aAVAPENModel.getToPrmtChgDate()); 

            if (aAVAPENModel.getToPrmtChgProj() == null) 
                prepStmt.setNull(20, Types.VARCHAR);
            else 
                prepStmt.setString(20, aAVAPENModel.getToPrmtChgProj()); 

            if (aAVAPENModel.getToExportFile() == null) 
                prepStmt.setNull(21, Types.VARCHAR);
            else 
                prepStmt.setString(21, aAVAPENModel.getToExportFile()); 

            if (aAVAPENModel.getLstChangedBy() == null) 
                prepStmt.setNull(22, Types.VARCHAR);
            else 
                prepStmt.setString(22, aAVAPENModel.getLstChangedBy()); 

            if (aAVAPENModel.getLstChangeDate() == null) 
                prepStmt.setNull(23, Types.DATE);
            else 
                prepStmt.setDate(23, aAVAPENModel.getLstChangeDate()); 

            prepStmt.setTimestamp(24, new Timestamp(System.currentTimeMillis())); 

            result = DAOUtils.getIntFromBoolean(prepStmt.execute(), prepStmt);
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

	/**
	 * Updates a row in the database table.
	 * 
	 * @param aConnection The database connection object.
	 * @param aDAOModel The model object to update.
	 * @param aString The database schema.
	 * @return Returns the SQL result.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */
    public int update(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPENModel aAVAPENModel = ( AVAPENModel ) aDAOModel; 
        if (!aAVAPENModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        if (hasChangedSinceLastRead(aConnection, aAVAPENModel.getDbCopy(), aString)) 
			throw new SQLException("Cannot Update Obsolete Data.");
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String updateSets = ".AVAPEN SET" + 
                                " DESCRIPTION = ? , " + 
                                " SUBSYSTEM = ? , " + 
                                " SCHEMA = ? , " + 
                                " FROM_PRMT_ENVRM = ? , " + 
                                " FROM_PRMT_DATE = ? , " + 
                                " FROM_PRMT_TIMESTAMP = ? , " + 
                                " FROM_PRMT_BY = ? , " + 
                                " FROM_PRMT_OPTION = ? , " + 
                                " FROM_PRMT_CHG_DATE = ? , " + 
                                " FROM_PRMT_CHG_PROJ = ? , " + 
                                " FROM_EXPORT_FILE = ? , " + 
                                " TO_PRMT_ENVRM = ? , " + 
                                " TO_PRMT_DATE = ? , " + 
                                " TO_PRMT_TIMESTAMP = ? , " + 
                                " TO_PRMT_BY = ? , " + 
                                " TO_PRMT_OPTION = ? , " + 
                                " TO_PRMT_CHG_DATE = ? , " + 
                                " TO_PRMT_CHG_PROJ = ? , " + 
                                " TO_EXPORT_FILE = ? , " + 
                                " LST_CHANGED_BY = ? , " + 
                                " LST_CHANGE_DATE = ? , " + 
                                " TIME_STAMP = ?  " + 
                                 "WHERE APPL_KEY = ? AND " +
                                       "ENVRNMNT_KEY = ? ";
        String sql = "UPDATE " + connSchema + updateSets;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPENModel.getDescription() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPENModel.getDescription()); 

            if (aAVAPENModel.getSubsystem() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVAPENModel.getSubsystem()); 

            if (aAVAPENModel.getSchema() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVAPENModel.getSchema()); 

            if (aAVAPENModel.getFromPrmtEnvrm() == null) 
                prepStmt.setNull(4, Types.VARCHAR);
            else 
                prepStmt.setString(4, aAVAPENModel.getFromPrmtEnvrm()); 

            if (aAVAPENModel.getFromPrmtDate() == null) 
                prepStmt.setNull(5, Types.DATE);
            else 
                prepStmt.setDate(5, aAVAPENModel.getFromPrmtDate()); 

            if (aAVAPENModel.getFromPrmtTimestamp() == null) 
                prepStmt.setNull(6, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(6, aAVAPENModel.getFromPrmtTimestamp()); 

            if (aAVAPENModel.getFromPrmtBy() == null) 
                prepStmt.setNull(7, Types.VARCHAR);
            else 
                prepStmt.setString(7, aAVAPENModel.getFromPrmtBy()); 

            if (aAVAPENModel.getFromPrmtOption() == null) 
                prepStmt.setNull(8, Types.VARCHAR);
            else 
                prepStmt.setString(8, aAVAPENModel.getFromPrmtOption()); 

            if (aAVAPENModel.getFromPrmtChgDate() == null) 
                prepStmt.setNull(9, Types.DATE);
            else 
                prepStmt.setDate(9, aAVAPENModel.getFromPrmtChgDate()); 

            if (aAVAPENModel.getFromPrmtChgProj() == null) 
                prepStmt.setNull(10, Types.VARCHAR);
            else 
                prepStmt.setString(10, aAVAPENModel.getFromPrmtChgProj()); 

            if (aAVAPENModel.getFromExportFile() == null) 
                prepStmt.setNull(11, Types.VARCHAR);
            else 
                prepStmt.setString(11, aAVAPENModel.getFromExportFile()); 

            if (aAVAPENModel.getToPrmtEnvrm() == null) 
                prepStmt.setNull(12, Types.VARCHAR);
            else 
                prepStmt.setString(12, aAVAPENModel.getToPrmtEnvrm()); 

            if (aAVAPENModel.getToPrmtDate() == null) 
                prepStmt.setNull(13, Types.DATE);
            else 
                prepStmt.setDate(13, aAVAPENModel.getToPrmtDate()); 

            if (aAVAPENModel.getToPrmtTimestamp() == null) 
                prepStmt.setNull(14, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(14, aAVAPENModel.getToPrmtTimestamp()); 

            if (aAVAPENModel.getToPrmtBy() == null) 
                prepStmt.setNull(15, Types.VARCHAR);
            else 
                prepStmt.setString(15, aAVAPENModel.getToPrmtBy()); 

            if (aAVAPENModel.getToPrmtOption() == null) 
                prepStmt.setNull(16, Types.VARCHAR);
            else 
                prepStmt.setString(16, aAVAPENModel.getToPrmtOption()); 

            if (aAVAPENModel.getToPrmtChgDate() == null) 
                prepStmt.setNull(17, Types.DATE);
            else 
                prepStmt.setDate(17, aAVAPENModel.getToPrmtChgDate()); 

            if (aAVAPENModel.getToPrmtChgProj() == null) 
                prepStmt.setNull(18, Types.VARCHAR);
            else 
                prepStmt.setString(18, aAVAPENModel.getToPrmtChgProj()); 

            if (aAVAPENModel.getToExportFile() == null) 
                prepStmt.setNull(19, Types.VARCHAR);
            else 
                prepStmt.setString(19, aAVAPENModel.getToExportFile()); 

            if (aAVAPENModel.getLstChangedBy() == null) 
                prepStmt.setNull(20, Types.VARCHAR);
            else 
                prepStmt.setString(20, aAVAPENModel.getLstChangedBy()); 

            if (aAVAPENModel.getLstChangeDate() == null) 
                prepStmt.setNull(21, Types.DATE);
            else 
                prepStmt.setDate(21, aAVAPENModel.getLstChangeDate()); 

            prepStmt.setTimestamp(22, new Timestamp(System.currentTimeMillis())); 

            if (aAVAPENModel.getApplKey() == null) 
                prepStmt.setNull(23, Types.VARCHAR);
            else 
                prepStmt.setString(23, aAVAPENModel.getApplKey()); 

            if (aAVAPENModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(24, Types.VARCHAR);
            else 
                prepStmt.setString(24, aAVAPENModel.getEnvrnmntKey()); 

            result = prepStmt.executeUpdate();
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

	/**
	 * Deletes a row in the database table.
	 * 
	 * @param aConnection The database connection object.
	 * @param aDAOModel The model object to delete.
	 * @param aString The database schema.
	 * @return Returns the SQL result.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */
    public int delete(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPENModel aAVAPENModel = (AVAPENModel)aDAOModel;
        if (!aAVAPENModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String deleteTable = ".AVAPEN ";
        String deleteWhere = "WHERE APPL_KEY = ?  AND " +
                                   "ENVRNMNT_KEY = ?";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPENModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPENModel.getApplKey()); 

            if (aAVAPENModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVAPENModel.getEnvrnmntKey()); 

            result = DAOUtils.getIntFromBoolean(prepStmt.execute(), prepStmt);
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

	/**
	 * Returns row(s) from the DB that match the specified key fields in the
	 * model.
	 * 
	 * @param aConnection The database connection object.
	 * @param aDAOModel The model object to select.
	 * @param aString The database schema.
	 * @return row(s) from the DB that match the specified key fields in the
	 *         model.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */
    public DAOModelCollection<AVAPENModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPENModel aAVAPENModel = (AVAPENModel)aDAOModel;
        String selectColumns = "SELECT APPL_KEY, ENVRNMNT_KEY, DESCRIPTION, SUBSYSTEM, SCHEMA, FROM_PRMT_ENVRM, FROM_PRMT_DATE, FROM_PRMT_TIMESTAMP, FROM_PRMT_BY, FROM_PRMT_OPTION, FROM_PRMT_CHG_DATE, FROM_PRMT_CHG_PROJ, FROM_EXPORT_FILE, TO_PRMT_ENVRM, TO_PRMT_DATE, TO_PRMT_TIMESTAMP, TO_PRMT_BY, TO_PRMT_OPTION, TO_PRMT_CHG_DATE, TO_PRMT_CHG_PROJ, TO_EXPORT_FILE, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String selectTable = ".AVAPEN ";
        String selectWhere = "WHERE APPL_KEY = ?  AND " +
                                   "ENVRNMNT_KEY = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVAPENModel> list = new DAOModelCollection<AVAPENModel>(aAVAPENModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPENModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPENModel.getApplKey()); 

            if (aAVAPENModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVAPENModel.getEnvrnmntKey()); 

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVAPENModel model = new AVAPENModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setDescription(DAOUtils.getString(rs.getString("DESCRIPTION")));
                model.setSubsystem(DAOUtils.getString(rs.getString("SUBSYSTEM")));
                model.setSchema(DAOUtils.getString(rs.getString("SCHEMA")));
                model.setFromPrmtEnvrm(DAOUtils.getString(rs.getString("FROM_PRMT_ENVRM")));
                model.setFromPrmtDate(rs.getDate("FROM_PRMT_DATE"));
                model.setFromPrmtTimestamp(rs.getTimestamp("FROM_PRMT_TIMESTAMP"));
                model.setFromPrmtBy(DAOUtils.getString(rs.getString("FROM_PRMT_BY")));
                model.setFromPrmtOption(DAOUtils.getString(rs.getString("FROM_PRMT_OPTION")));
                model.setFromPrmtChgDate(rs.getDate("FROM_PRMT_CHG_DATE"));
                model.setFromPrmtChgProj(DAOUtils.getString(rs.getString("FROM_PRMT_CHG_PROJ")));
                model.setFromExportFile(DAOUtils.getString(rs.getString("FROM_EXPORT_FILE")));
                model.setToPrmtEnvrm(DAOUtils.getString(rs.getString("TO_PRMT_ENVRM")));
                model.setToPrmtDate(rs.getDate("TO_PRMT_DATE"));
                model.setToPrmtTimestamp(rs.getTimestamp("TO_PRMT_TIMESTAMP"));
                model.setToPrmtBy(DAOUtils.getString(rs.getString("TO_PRMT_BY")));
                model.setToPrmtOption(DAOUtils.getString(rs.getString("TO_PRMT_OPTION")));
                model.setToPrmtChgDate(rs.getDate("TO_PRMT_CHG_DATE"));
                model.setToPrmtChgProj(DAOUtils.getString(rs.getString("TO_PRMT_CHG_PROJ")));
                model.setToExportFile(DAOUtils.getString(rs.getString("TO_EXPORT_FILE")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVAPENModel)model.createCopy());
                list.add(model);
            }
        } finally {
            if (rs != null)
                rs.close(); 
            if (prepStmt != null)
                prepStmt.close();
        }
        return list;
    } 

    
	/**
	 * Returns all rows in the table.
	 * 
	 * @param aConnection The database connection object.
	 * @param aString The database schema.
	 * @return all rows in the table.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */
    public ArrayList<AVMIdDescPair> getEnvList(Connection aConnection, DAOModel aDAOModel, String aString)
		throws SQLException 
	{ 
      AVAPENModel aAVAPENModel = (AVAPENModel)aDAOModel;
      
      String connSchema = aString;
	  DAOUtils.validString(connSchema);
		
      String sql = "SELECT  T2.ENVRNMNT_ID, T2.ENVRNMNT_DESC FROM "+
      			  connSchema+".AVAPEN as T1 ,"+connSchema+".AVENV as T2 "+
      			  " WHERE T1.ENVRNMNT_KEY=T2.ENVRNMNT_KEY and T1.APPL_KEY =?";
      
      ResultSet rs = null;
      ArrayList<AVMIdDescPair> aList = new ArrayList<AVMIdDescPair>();
      PreparedStatement prepStmt = aConnection.prepareStatement(sql);
      try {
          if (aAVAPENModel.getApplKey() == null) 
              prepStmt.setNull(1, Types.VARCHAR);
          else 
              prepStmt.setString(1, aAVAPENModel.getApplKey()); 

          rs = prepStmt.executeQuery();
          while(rs.next()) {
        	  AVMIdDescPair avmIdDescPr = new AVMIdDescPair(rs.getString("ENVRNMNT_ID"), rs.getString("ENVRNMNT_DESC"));
        	  aList.add(avmIdDescPr);
          }
      } finally {
          if (rs != null)
              rs.close(); 
          if (prepStmt != null)
              prepStmt.close();
      }
      return aList;
  } 

    public ArrayList<AVMIdDescPair> getEnvListAsKeys(Connection aConnection, DAOModel aDAOModel, String aString)
		throws SQLException 
	{ 
      AVAPENModel aAVAPENModel = (AVAPENModel)aDAOModel;
      String connSchema = aString;
	  DAOUtils.validString(connSchema);

      String sql = "SELECT  T2.ENVRNMNT_KEY, T2.ENVRNMNT_DESC FROM "+
      			  connSchema+".AVAPEN as T1 ,"+connSchema+".AVENV as T2 "+
      			  " WHERE T1.ENVRNMNT_KEY=T2.ENVRNMNT_KEY and T1.APPL_KEY =?";

      ResultSet rs = null;
      ArrayList<AVMIdDescPair> aList = new ArrayList<AVMIdDescPair>();
	  
      PreparedStatement prepStmt = aConnection.prepareStatement(sql);
      try {
          if (aAVAPENModel.getApplKey() == null) 
              prepStmt.setNull(1, Types.VARCHAR);
          else 
              prepStmt.setString(1, aAVAPENModel.getApplKey()); 

          rs = prepStmt.executeQuery();
          while(rs.next()) {
        	  AVMIdDescPair avmIdDescPr = new AVMIdDescPair(rs.getString("ENVRNMNT_KEY"), rs.getString("ENVRNMNT_DESC"));
        	  aList.add(avmIdDescPr);
          }
      } finally {
          if (rs != null)
              rs.close(); 
          if (prepStmt != null)
              prepStmt.close();
      }
      return aList;
  } 

    

    /**
	 * Returns all rows in the table.
	 * 
	 * @param aConnection The database connection object.
	 * @param aString The database schema.
	 * @return all rows in the table.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */ 
    public DAOModelCollection<AVAPENModel> getList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT APPL_KEY, ENVRNMNT_KEY, DESCRIPTION, SUBSYSTEM, SCHEMA, FROM_PRMT_ENVRM, FROM_PRMT_DATE, FROM_PRMT_TIMESTAMP, FROM_PRMT_BY, FROM_PRMT_OPTION, FROM_PRMT_CHG_DATE, FROM_PRMT_CHG_PROJ, FROM_EXPORT_FILE, TO_PRMT_ENVRM, TO_PRMT_DATE, TO_PRMT_TIMESTAMP, TO_PRMT_BY, TO_PRMT_OPTION, TO_PRMT_CHG_DATE, TO_PRMT_CHG_PROJ, TO_EXPORT_FILE, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String selectTable = ".AVAPEN ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVAPENModel> list = new DAOModelCollection<AVAPENModel>(new AVAPENModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVAPENModel model = new AVAPENModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setDescription(DAOUtils.getString(rs.getString("DESCRIPTION")));
                model.setSubsystem(DAOUtils.getString(rs.getString("SUBSYSTEM")));
                model.setSchema(DAOUtils.getString(rs.getString("SCHEMA")));
                model.setFromPrmtEnvrm(DAOUtils.getString(rs.getString("FROM_PRMT_ENVRM")));
                model.setFromPrmtDate(rs.getDate("FROM_PRMT_DATE"));
                model.setFromPrmtTimestamp(rs.getTimestamp("FROM_PRMT_TIMESTAMP"));
                model.setFromPrmtBy(DAOUtils.getString(rs.getString("FROM_PRMT_BY")));
                model.setFromPrmtOption(DAOUtils.getString(rs.getString("FROM_PRMT_OPTION")));
                model.setFromPrmtChgDate(rs.getDate("FROM_PRMT_CHG_DATE"));
                model.setFromPrmtChgProj(DAOUtils.getString(rs.getString("FROM_PRMT_CHG_PROJ")));
                model.setFromExportFile(DAOUtils.getString(rs.getString("FROM_EXPORT_FILE")));
                model.setToPrmtEnvrm(DAOUtils.getString(rs.getString("TO_PRMT_ENVRM")));
                model.setToPrmtDate(rs.getDate("TO_PRMT_DATE"));
                model.setToPrmtTimestamp(rs.getTimestamp("TO_PRMT_TIMESTAMP"));
                model.setToPrmtBy(DAOUtils.getString(rs.getString("TO_PRMT_BY")));
                model.setToPrmtOption(DAOUtils.getString(rs.getString("TO_PRMT_OPTION")));
                model.setToPrmtChgDate(rs.getDate("TO_PRMT_CHG_DATE"));
                model.setToPrmtChgProj(DAOUtils.getString(rs.getString("TO_PRMT_CHG_PROJ")));
                model.setToExportFile(DAOUtils.getString(rs.getString("TO_EXPORT_FILE")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVAPENModel)model.createCopy());
                list.add(model);
            }
        } finally {
            if (rs != null)
                rs.close(); 
            if (prepStmt != null)
                prepStmt.close();
        }
        return list;
    } 

    /**
	 * Returns all rows in table, but just the key columns set in row objects.
	 * 
	 * @param aConnection The database connection object.
	 * @param aString The database schema.
	 * @return all rows in table, but just the key columns set in row objects.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */ 
    public DAOModelCollection<AVAPENModel> getKeyList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT APPL_KEY, ENVRNMNT_KEY FROM ";
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String selectTable = ".AVAPEN ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVAPENModel> list = new DAOModelCollection<AVAPENModel>(new AVAPENModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVAPENModel model = new AVAPENModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setDbCopy((AVAPENModel)model.createCopy());
                list.add(model);
            }
        } finally {
            if (rs != null)
                rs.close(); 
            if (prepStmt != null)
                prepStmt.close();
        }
        return list;
    } 

	/**
	 * Returns a clone of the specified model. All key fields are cleared.
	 * 
	 * @param aDAOModel The model object to clone.
	 * @param aString The database schema.
	 * @return a clone of the specified model. All key fields are cleared.
	 */
    public AVAPENModel getCloneModel(DAOModel aDAOModel, String aString){ 
        AVAPENModel oldAVAPENModel = ( AVAPENModel ) aDAOModel; 
        AVAPENModel aAVAPENModel = ( AVAPENModel ) oldAVAPENModel.createCopy();
        aAVAPENModel.setApplKey(null);
        aAVAPENModel.setEnvrnmntKey(null);
        return aAVAPENModel;
    } 

    /**
	 * Returns true if the row in the database that corresponds to the specified
	 * model has changed since it was last read from the dB.
	 * 
	 * @param aConnection The database connection object.
	 * @param aDAOModel The model object to compare.
	 * @param aString The database schema.
	 * @return true if the row in the database that corresponds to the specified
	 *         model has changed since it was last read from the dB.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */ 
    public boolean hasChangedSinceLastRead(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        boolean hasChanged = false; 
        AVAPENModel aAVAPENModel = ( AVAPENModel ) aDAOModel; 
        String selectColumns = "SELECT  COUNT(*)  FROM ";
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String selectTable = ".AVAPEN ";
        String selectWhere = "WHERE APPL_KEY = ?  AND " +
                                   "ENVRNMNT_KEY = ? AND " +
                                   " TIME_STAMP = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPENModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPENModel.getApplKey()); 

            if (aAVAPENModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVAPENModel.getEnvrnmntKey()); 

            if (aAVAPENModel.getTimeStamp() == null) 
                prepStmt.setNull(3, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(3, aAVAPENModel.getTimeStamp()); 

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
               if( rs.getInt(1) != 1 )  
                   hasChanged =  true;  
            }
        } finally {
            if (rs != null)
                rs.close(); 
            if (prepStmt != null)
                prepStmt.close();
        }
        return hasChanged;
    } 
}
