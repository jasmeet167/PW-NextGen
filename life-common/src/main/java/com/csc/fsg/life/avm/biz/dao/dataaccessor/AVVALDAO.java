package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVVALModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Data access class for table AVVAL.
 */
public class AVVALDAO extends DataAccessor { 


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
        AVVALModel aAVVALModel = ( AVVALModel ) aDAOModel; 
        if (!aAVVALModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String insertValues = ".AVVAL (FIELD_KEY, ALLVAL_KEY, CORE_VALUE, DISPLAY_VALUE, ALLVAL_PROJECT, ALLVAL_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP, CACHEIND) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String sql = "INSERT INTO " + connSchema + insertValues;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVVALModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVVALModel.getFieldKey()); 

            if (aAVVALModel.getAllvalKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVVALModel.getAllvalKey()); 

            if (aAVVALModel.getCoreValue() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVVALModel.getCoreValue()); 

            if (aAVVALModel.getDisplayValue() == null) 
                prepStmt.setNull(4, Types.VARCHAR);
            else 
                prepStmt.setString(4, aAVVALModel.getDisplayValue()); 

            if (aAVVALModel.getAllvalProject() == null) 
                prepStmt.setNull(5, Types.VARCHAR);
            else 
                prepStmt.setString(5, aAVVALModel.getAllvalProject()); 

            if (aAVVALModel.getAllvalStatus() == null) 
                prepStmt.setNull(6, Types.VARCHAR);
            else 
                prepStmt.setString(6, aAVVALModel.getAllvalStatus()); 

            if (aAVVALModel.getLstChangedBy() == null) 
                prepStmt.setNull(7, Types.VARCHAR);
            else 
                prepStmt.setString(7, aAVVALModel.getLstChangedBy()); 

            if (aAVVALModel.getLstChangeDate() == null) 
                prepStmt.setNull(8, Types.DATE);
            else 
                prepStmt.setDate(8, aAVVALModel.getLstChangeDate()); 

            prepStmt.setTimestamp(9, new Timestamp(System.currentTimeMillis())); 

            if (aAVVALModel.getCacheind() == null) 
                prepStmt.setLong(10, DAOUtils.getDefaultLong());
            else 
                prepStmt.setLong(10, aAVVALModel.getCacheind().longValue()); 

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
        AVVALModel aAVVALModel = ( AVVALModel ) aDAOModel; 
        if (!aAVVALModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        if (hasChangedSinceLastRead(aConnection, aAVVALModel.getDbCopy(), aString)) 
			throw new SQLException("Cannot Update Obsolete Data.");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String updateSets = ".AVVAL SET" + 
                                " CORE_VALUE = ? , " + 
                                " DISPLAY_VALUE = ? , " + 
                                " ALLVAL_PROJECT = ? , " + 
                                " ALLVAL_STATUS = ? , " + 
                                " LST_CHANGED_BY = ? , " + 
                                " LST_CHANGE_DATE = ? , " + 
                                " TIME_STAMP = ? , " + 
                                " CACHEIND = ?  " + 
                                 "WHERE FIELD_KEY = ? AND " +
                                       "ALLVAL_KEY = ? ";
        String sql = "UPDATE " + connSchema + updateSets;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVVALModel.getCoreValue() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVVALModel.getCoreValue()); 

            if (aAVVALModel.getDisplayValue() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVVALModel.getDisplayValue()); 

            if (aAVVALModel.getAllvalProject() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVVALModel.getAllvalProject()); 

            if (aAVVALModel.getAllvalStatus() == null) 
                prepStmt.setNull(4, Types.VARCHAR);
            else 
                prepStmt.setString(4, aAVVALModel.getAllvalStatus()); 

            if (aAVVALModel.getLstChangedBy() == null) 
                prepStmt.setNull(5, Types.VARCHAR);
            else 
                prepStmt.setString(5, aAVVALModel.getLstChangedBy()); 

            if (aAVVALModel.getLstChangeDate() == null) 
                prepStmt.setNull(6, Types.DATE);
            else 
                prepStmt.setDate(6, aAVVALModel.getLstChangeDate()); 

            prepStmt.setTimestamp(7, new Timestamp(System.currentTimeMillis())); 

            if (aAVVALModel.getCacheind() == null) 
                prepStmt.setLong(8, DAOUtils.getDefaultLong());
            else 
                prepStmt.setLong(8, aAVVALModel.getCacheind().longValue()); 

            if (aAVVALModel.getFieldKey() == null) 
                prepStmt.setNull(9, Types.VARCHAR);
            else 
                prepStmt.setString(9, aAVVALModel.getFieldKey()); 

            if (aAVVALModel.getAllvalKey() == null) 
                prepStmt.setNull(10, Types.VARCHAR);
            else 
                prepStmt.setString(10, aAVVALModel.getAllvalKey()); 

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
        AVVALModel aAVVALModel = (AVVALModel)aDAOModel;
        if (!aAVVALModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVVAL ";
        String deleteWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLVAL_KEY = ?";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVVALModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVVALModel.getFieldKey()); 

            if (aAVVALModel.getAllvalKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVVALModel.getAllvalKey()); 

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
    public DAOModelCollection<AVVALModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException
    { 
        AVVALModel aAVVALModel = (AVVALModel)aDAOModel;
        String selectColumns = "SELECT FIELD_KEY, ALLVAL_KEY, CORE_VALUE, DISPLAY_VALUE, ALLVAL_PROJECT, ALLVAL_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP, CACHEIND FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVVAL ";
        String selectWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLVAL_KEY = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVVALModel> list = new DAOModelCollection<AVVALModel>(aAVVALModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVVALModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVVALModel.getFieldKey()); 

            if (aAVVALModel.getAllvalKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVVALModel.getAllvalKey()); 

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVVALModel model = new AVVALModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setAllvalKey(DAOUtils.getString(rs.getString("ALLVAL_KEY")));
                model.setCoreValue(DAOUtils.getString(rs.getString("CORE_VALUE")));
                model.setDisplayValue(DAOUtils.getString(rs.getString("DISPLAY_VALUE")));
                model.setAllvalProject(DAOUtils.getString(rs.getString("ALLVAL_PROJECT")));
                model.setAllvalStatus(DAOUtils.getString(rs.getString("ALLVAL_STATUS")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setCacheind(DAOUtils.getLong(rs.getLong("CACHEIND")));
                model.setDbCopy((AVVALModel)model.createCopy());
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
    public DAOModelCollection<AVVALModel> getList(Connection aConnection, String aString)
      throws SQLException
    { 
        String selectColumns = "SELECT FIELD_KEY, ALLVAL_KEY, CORE_VALUE, DISPLAY_VALUE, ALLVAL_PROJECT, ALLVAL_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP, CACHEIND FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVVAL ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVVALModel> list = new DAOModelCollection<AVVALModel>(new AVVALModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVVALModel model = new AVVALModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setAllvalKey(DAOUtils.getString(rs.getString("ALLVAL_KEY")));
                model.setCoreValue(DAOUtils.getString(rs.getString("CORE_VALUE")));
                model.setDisplayValue(DAOUtils.getString(rs.getString("DISPLAY_VALUE")));
                model.setAllvalProject(DAOUtils.getString(rs.getString("ALLVAL_PROJECT")));
                model.setAllvalStatus(DAOUtils.getString(rs.getString("ALLVAL_STATUS")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setCacheind(DAOUtils.getLong(rs.getLong("CACHEIND")));
                model.setDbCopy((AVVALModel)model.createCopy());
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
    public DAOModelCollection<AVVALModel> getKeyList(Connection aConnection, String aString)
      throws SQLException
    { 
        String selectColumns = "SELECT FIELD_KEY, ALLVAL_KEY FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVVAL ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVVALModel> list = new DAOModelCollection<AVVALModel>(new AVVALModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVVALModel model = new AVVALModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setAllvalKey(DAOUtils.getString(rs.getString("ALLVAL_KEY")));
                model.setDbCopy((AVVALModel)model.createCopy());
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
    public AVVALModel getCloneModel(DAOModel aDAOModel, String aString){ 
        AVVALModel oldAVVALModel = ( AVVALModel ) aDAOModel; 
        AVVALModel aAVVALModel = ( AVVALModel ) oldAVVALModel.createCopy();
        aAVVALModel.setFieldKey(null);
        aAVVALModel.setAllvalKey(null);
        return aAVVALModel;
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
        AVVALModel aAVVALModel = ( AVVALModel ) aDAOModel; 
        String selectColumns = "SELECT  COUNT(*)  FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVVAL ";
        String selectWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLVAL_KEY = ? AND " +
                                   " TIME_STAMP = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVVALModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVVALModel.getFieldKey()); 

            if (aAVVALModel.getAllvalKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVVALModel.getAllvalKey()); 

            if (aAVVALModel.getTimeStamp() == null) 
                prepStmt.setNull(3, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(3, aAVVALModel.getTimeStamp()); 

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
