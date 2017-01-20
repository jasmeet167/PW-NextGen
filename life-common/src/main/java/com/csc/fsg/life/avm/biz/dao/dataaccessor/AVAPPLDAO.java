package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVAPPLModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Data access class for table AVAPPL.
 */
public class AVAPPLDAO extends DataAccessor { 


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
        AVAPPLModel aAVAPPLModel = ( AVAPPLModel ) aDAOModel; 
        if (!aAVAPPLModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String insertValues = ".AVAPPL (APPL_KEY, APPL_ID, APPL_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP) VALUES (?, ?, ?, ?, ?, ?) ";
        String sql = "INSERT INTO " + connSchema + insertValues;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPPLModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPPLModel.getApplKey()); 

            if (aAVAPPLModel.getApplId() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVAPPLModel.getApplId()); 

            if (aAVAPPLModel.getApplDesc() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVAPPLModel.getApplDesc()); 

            if (aAVAPPLModel.getLstChangedBy() == null) 
                prepStmt.setNull(4, Types.VARCHAR);
            else 
                prepStmt.setString(4, aAVAPPLModel.getLstChangedBy()); 

            if (aAVAPPLModel.getLstChangeDate() == null) 
                prepStmt.setNull(5, Types.DATE);
            else 
                prepStmt.setDate(5, aAVAPPLModel.getLstChangeDate()); 

            prepStmt.setTimestamp(6, new Timestamp(System.currentTimeMillis())); 

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
        AVAPPLModel aAVAPPLModel = ( AVAPPLModel ) aDAOModel; 
        if (!aAVAPPLModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        if (hasChangedSinceLastRead(aConnection, aAVAPPLModel.getDbCopy(), aString)) 
			throw new SQLException("Cannot Update Obsolete Data.");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String updateSets = ".AVAPPL SET" + 
                                " APPL_ID = ? , " + 
                                " APPL_DESC = ? , " + 
                                " LST_CHANGED_BY = ? , " + 
                                " LST_CHANGE_DATE = ? , " + 
                                " TIME_STAMP = ?  " + 
                                 "WHERE APPL_KEY = ? ";
        String sql = "UPDATE " + connSchema + updateSets;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPPLModel.getApplId() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPPLModel.getApplId()); 

            if (aAVAPPLModel.getApplDesc() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVAPPLModel.getApplDesc()); 

            if (aAVAPPLModel.getLstChangedBy() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVAPPLModel.getLstChangedBy()); 

            if (aAVAPPLModel.getLstChangeDate() == null) 
                prepStmt.setNull(4, Types.DATE);
            else 
                prepStmt.setDate(4, aAVAPPLModel.getLstChangeDate()); 

            prepStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis())); 

            if (aAVAPPLModel.getApplKey() == null) 
                prepStmt.setNull(6, Types.VARCHAR);
            else 
                prepStmt.setString(6, aAVAPPLModel.getApplKey()); 

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
        AVAPPLModel aAVAPPLModel = (AVAPPLModel)aDAOModel;
        if (!aAVAPPLModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVAPPL ";
        String deleteWhere = "WHERE APPL_KEY = ? ";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPPLModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPPLModel.getApplKey()); 

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
    public DAOModelCollection<AVAPPLModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPPLModel aAVAPPLModel = (AVAPPLModel)aDAOModel;
        String selectColumns = "SELECT APPL_KEY, APPL_ID, APPL_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVAPPL ";
        String selectWhere = "WHERE APPL_KEY = ? ";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY APPL_ID";
        ResultSet rs = null;
        DAOModelCollection<AVAPPLModel> list = new DAOModelCollection<AVAPPLModel>(aAVAPPLModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPPLModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPPLModel.getApplKey()); 

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVAPPLModel model = new AVAPPLModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setApplId(DAOUtils.getString(rs.getString("APPL_ID")));
                model.setApplDesc(DAOUtils.getString(rs.getString("APPL_DESC")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVAPPLModel)model.createCopy());
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
    public DAOModelCollection<AVAPPLModel> getList(Connection aConnection, String aString)
      throws SQLException
    { 
        String selectColumns = "SELECT APPL_KEY, APPL_ID, APPL_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVAPPL ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY APPL_ID";
        ResultSet rs = null;
        DAOModelCollection<AVAPPLModel> list = new DAOModelCollection<AVAPPLModel>(new AVAPPLModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVAPPLModel model = new AVAPPLModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setApplId(DAOUtils.getString(rs.getString("APPL_ID")));
                model.setApplDesc(DAOUtils.getString(rs.getString("APPL_DESC")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVAPPLModel)model.createCopy());
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
    public DAOModelCollection<AVAPPLModel> getKeyList(Connection aConnection, String aString)
      throws SQLException
    { 
        String selectColumns = "SELECT APPL_KEY FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVAPPL ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVAPPLModel> list = new DAOModelCollection<AVAPPLModel>(new AVAPPLModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVAPPLModel model = new AVAPPLModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setDbCopy((AVAPPLModel)model.createCopy());
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
	public AVAPPLModel getCloneModel(DAOModel aDAOModel, String aString){ 
        AVAPPLModel oldAVAPPLModel = ( AVAPPLModel ) aDAOModel; 
        AVAPPLModel aAVAPPLModel = ( AVAPPLModel ) oldAVAPPLModel.createCopy();
        aAVAPPLModel.setApplKey(null);
        return aAVAPPLModel;
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
        AVAPPLModel aAVAPPLModel = ( AVAPPLModel ) aDAOModel; 
        String selectColumns = "SELECT  COUNT(*)  FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVAPPL ";
        String selectWhere = "WHERE APPL_KEY = ?  AND " +
                                   " TIME_STAMP = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPPLModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPPLModel.getApplKey()); 

            if (aAVAPPLModel.getTimeStamp() == null) 
                prepStmt.setNull(2, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(2, aAVAPPLModel.getTimeStamp()); 

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
