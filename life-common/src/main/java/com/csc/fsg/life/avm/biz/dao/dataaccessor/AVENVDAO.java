package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVENVModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Data access class for table AVENV.
 */
public class AVENVDAO extends DataAccessor { 


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
        AVENVModel aAVENVModel = ( AVENVModel ) aDAOModel; 
        if (!aAVENVModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String insertValues = ".AVENV (ENVRNMNT_KEY, ENVRNMNT_ID, ENVRNMNT_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP) VALUES (?, ?, ?, ?, ?, ?) ";
        String sql = "INSERT INTO " + connSchema + insertValues;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVENVModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVENVModel.getEnvrnmntKey()); 

            if (aAVENVModel.getEnvrnmntId() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVENVModel.getEnvrnmntId()); 

            if (aAVENVModel.getEnvrnmntDesc() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVENVModel.getEnvrnmntDesc()); 

            if (aAVENVModel.getLstChangedBy() == null) 
                prepStmt.setNull(4, Types.VARCHAR);
            else 
                prepStmt.setString(4, aAVENVModel.getLstChangedBy()); 

            if (aAVENVModel.getLstChangeDate() == null) 
                prepStmt.setNull(5, Types.DATE);
            else 
                prepStmt.setDate(5, aAVENVModel.getLstChangeDate()); 

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
        AVENVModel aAVENVModel = ( AVENVModel ) aDAOModel; 
        if (!aAVENVModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        if (hasChangedSinceLastRead(aConnection, aAVENVModel.getDbCopy(), aString)) 
			throw new SQLException("Cannot Update Obsolete Data.");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String updateSets = ".AVENV SET" + 
                                " ENVRNMNT_ID = ? , " + 
                                " ENVRNMNT_DESC = ? , " + 
                                " LST_CHANGED_BY = ? , " + 
                                " LST_CHANGE_DATE = ? , " + 
                                " TIME_STAMP = ?  " + 
                                 "WHERE ENVRNMNT_KEY = ? ";
        String sql = "UPDATE " + connSchema + updateSets;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVENVModel.getEnvrnmntId() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVENVModel.getEnvrnmntId()); 

            if (aAVENVModel.getEnvrnmntDesc() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVENVModel.getEnvrnmntDesc()); 

            if (aAVENVModel.getLstChangedBy() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVENVModel.getLstChangedBy()); 

            if (aAVENVModel.getLstChangeDate() == null) 
                prepStmt.setNull(4, Types.DATE);
            else 
                prepStmt.setDate(4, aAVENVModel.getLstChangeDate()); 

            prepStmt.setTimestamp(5, new Timestamp(System.currentTimeMillis())); 

            if (aAVENVModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(6, Types.VARCHAR);
            else 
                prepStmt.setString(6, aAVENVModel.getEnvrnmntKey()); 

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
        AVENVModel aAVENVModel = (AVENVModel)aDAOModel;
        if (!aAVENVModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVENV ";
        String deleteWhere = "WHERE ENVRNMNT_KEY = ? ";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVENVModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVENVModel.getEnvrnmntKey()); 

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
    public DAOModelCollection<AVENVModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVENVModel aAVENVModel = (AVENVModel)aDAOModel;
        String selectColumns = "SELECT ENVRNMNT_KEY, ENVRNMNT_ID, ENVRNMNT_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVENV ";
        String selectWhere = "WHERE ENVRNMNT_KEY = ? ";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY ENVRNMNT_ID";
        ResultSet rs = null;
        DAOModelCollection<AVENVModel> list = new DAOModelCollection<AVENVModel>(aAVENVModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVENVModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVENVModel.getEnvrnmntKey()); 

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVENVModel model = new AVENVModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setEnvrnmntId(DAOUtils.getString(rs.getString("ENVRNMNT_ID")));
                model.setEnvrnmntDesc(DAOUtils.getString(rs.getString("ENVRNMNT_DESC")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVENVModel)model.createCopy());
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
    public DAOModelCollection<AVENVModel> getList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT ENVRNMNT_KEY, ENVRNMNT_ID, ENVRNMNT_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVENV ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY ENVRNMNT_ID";
        ResultSet rs = null;
        DAOModelCollection<AVENVModel> list = new DAOModelCollection<AVENVModel>(new AVENVModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVENVModel model = new AVENVModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setEnvrnmntId(DAOUtils.getString(rs.getString("ENVRNMNT_ID")));
                model.setEnvrnmntDesc(DAOUtils.getString(rs.getString("ENVRNMNT_DESC")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVENVModel)model.createCopy());
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
    public DAOModelCollection<AVENVModel> getKeyList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT ENVRNMNT_KEY FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVENV ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVENVModel> list = new DAOModelCollection<AVENVModel>(new AVENVModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVENVModel model = new AVENVModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setDbCopy((AVENVModel)model.createCopy());
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
    public AVENVModel getCloneModel(DAOModel aDAOModel, String aString){ 
        AVENVModel oldAVENVModel = ( AVENVModel ) aDAOModel; 
        AVENVModel aAVENVModel = ( AVENVModel ) oldAVENVModel.createCopy();
        aAVENVModel.setEnvrnmntKey(null);
        return aAVENVModel;
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
        AVENVModel aAVENVModel = ( AVENVModel ) aDAOModel; 
        String selectColumns = "SELECT  COUNT(*)  FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVENV ";
        String selectWhere = "WHERE ENVRNMNT_KEY = ?  AND " +
                                   " TIME_STAMP = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVENVModel.getEnvrnmntKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVENVModel.getEnvrnmntKey()); 

            if (aAVENVModel.getTimeStamp() == null) 
                prepStmt.setNull(2, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(2, aAVENVModel.getTimeStamp()); 

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
