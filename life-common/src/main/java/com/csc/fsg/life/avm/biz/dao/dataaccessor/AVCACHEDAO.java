package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVCACHEModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Data access class for table AVCACHE.
 */
public class AVCACHEDAO extends DataAccessor { 

	
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
        AVCACHEModel aAVCACHEModel = ( AVCACHEModel ) aDAOModel; 
        if (!aAVCACHEModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String insertValues = ".AVCACHE (APPL_KEY, CACHEIND, TIME_STAMP) VALUES (?, ?, ?) ";
        String sql = "INSERT INTO " + connSchema + insertValues;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVCACHEModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVCACHEModel.getApplKey()); 

            if (aAVCACHEModel.getCacheind() == null) 
                prepStmt.setLong(2, DAOUtils.getDefaultLong());
            else 
                prepStmt.setLong(2, aAVCACHEModel.getCacheind().longValue()); 

            prepStmt.setTimestamp(3, new Timestamp(System.currentTimeMillis())); 

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
        AVCACHEModel aAVCACHEModel = ( AVCACHEModel ) aDAOModel; 
        if (!aAVCACHEModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        if (hasChangedSinceLastRead(aConnection, aAVCACHEModel.getDbCopy(), aString)) 
			throw new SQLException("Cannot Update Obsolete Data.");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String updateSets = ".AVCACHE SET" + 
                                " CACHEIND = ? , " + 
                                " TIME_STAMP = ?  " + 
                                 "WHERE APPL_KEY = ? ";
        String sql = "UPDATE " + connSchema + updateSets;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVCACHEModel.getCacheind() == null) 
                prepStmt.setLong(1, DAOUtils.getDefaultLong());
            else 
                prepStmt.setLong(1, aAVCACHEModel.getCacheind().longValue()); 

            prepStmt.setTimestamp(2, new Timestamp(System.currentTimeMillis())); 

            if (aAVCACHEModel.getApplKey() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVCACHEModel.getApplKey()); 

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
        AVCACHEModel aAVCACHEModel = (AVCACHEModel)aDAOModel;
        if (!aAVCACHEModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVCACHE ";
        String deleteWhere = "WHERE APPL_KEY = ? ";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVCACHEModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVCACHEModel.getApplKey()); 

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
    public DAOModelCollection<AVCACHEModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVCACHEModel aAVCACHEModel = (AVCACHEModel)aDAOModel;
        String selectColumns = "SELECT APPL_KEY, CACHEIND, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVCACHE ";
        String selectWhere = "WHERE APPL_KEY = ? ";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVCACHEModel> list = new DAOModelCollection<AVCACHEModel>(aAVCACHEModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVCACHEModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVCACHEModel.getApplKey()); 

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVCACHEModel model = new AVCACHEModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setCacheind(DAOUtils.getLong(rs.getLong("CACHEIND")));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVCACHEModel)model.createCopy());
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
    public DAOModelCollection<AVCACHEModel> getList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT APPL_KEY, CACHEIND, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVCACHE ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVCACHEModel> list = new DAOModelCollection<AVCACHEModel>(new AVCACHEModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVCACHEModel model = new AVCACHEModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setCacheind(DAOUtils.getLong(rs.getLong("CACHEIND")));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setDbCopy((AVCACHEModel)model.createCopy());
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
    public DAOModelCollection<AVCACHEModel> getKeyList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT APPL_KEY FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVCACHE ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVCACHEModel> list = new DAOModelCollection<AVCACHEModel>(new AVCACHEModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVCACHEModel model = new AVCACHEModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setDbCopy((AVCACHEModel)model.createCopy());
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
    public AVCACHEModel getCloneModel(DAOModel aDAOModel, String aString){ 
        AVCACHEModel oldAVCACHEModel = ( AVCACHEModel ) aDAOModel; 
        AVCACHEModel aAVCACHEModel = ( AVCACHEModel ) oldAVCACHEModel.createCopy();
        aAVCACHEModel.setApplKey(null);
        return aAVCACHEModel;
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
        AVCACHEModel aAVCACHEModel = ( AVCACHEModel ) aDAOModel; 
        String selectColumns = "SELECT  COUNT(*)  FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVCACHE ";
        String selectWhere = "WHERE APPL_KEY = ?  AND " +
                                   " TIME_STAMP = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVCACHEModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVCACHEModel.getApplKey()); 

            if (aAVCACHEModel.getTimeStamp() == null) 
                prepStmt.setNull(2, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(2, aAVCACHEModel.getTimeStamp()); 

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
