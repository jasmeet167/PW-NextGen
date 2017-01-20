package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVRNGModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.dataaccessor.DataAccessor;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Data access class for table AVRNG.
 */
public class AVRNGDAO extends DataAccessor { 


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
        AVRNGModel aAVRNGModel = ( AVRNGModel ) aDAOModel; 
        if (!aAVRNGModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String insertValues = ".AVRNG (FIELD_KEY, ALLRNG_KEY, MIN_VALUE, MAX_VALUE, ALLRNG_PROJECT, ALLRNG_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP, CACHEIND) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        String sql = "INSERT INTO " + connSchema + insertValues;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVRNGModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVRNGModel.getFieldKey()); 

            if (aAVRNGModel.getAllrngKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVRNGModel.getAllrngKey()); 

            if (aAVRNGModel.getMinValue() == null) 
                prepStmt.setDouble(3, DAOUtils.getDefaultDouble());
            else 
                prepStmt.setDouble(3, aAVRNGModel.getMinValue().doubleValue()); 

            if (aAVRNGModel.getMaxValue() == null) 
                prepStmt.setDouble(4, DAOUtils.getDefaultDouble());
            else 
                prepStmt.setDouble(4, aAVRNGModel.getMaxValue().doubleValue()); 

            if (aAVRNGModel.getAllrngProject() == null) 
                prepStmt.setNull(5, Types.VARCHAR);
            else 
                prepStmt.setString(5, aAVRNGModel.getAllrngProject()); 

            if (aAVRNGModel.getAllrngStatus() == null) 
                prepStmt.setNull(6, Types.VARCHAR);
            else 
                prepStmt.setString(6, aAVRNGModel.getAllrngStatus()); 

            if (aAVRNGModel.getLstChangedBy() == null) 
                prepStmt.setNull(7, Types.VARCHAR);
            else 
                prepStmt.setString(7, aAVRNGModel.getLstChangedBy()); 

            if (aAVRNGModel.getLstChangeDate() == null) 
                prepStmt.setNull(8, Types.DATE);
            else 
                prepStmt.setDate(8, aAVRNGModel.getLstChangeDate()); 

            prepStmt.setTimestamp(9, new Timestamp(System.currentTimeMillis())); 

            if (aAVRNGModel.getCacheind() == null) 
                prepStmt.setLong(10, DAOUtils.getDefaultLong());
            else 
                prepStmt.setLong(10, aAVRNGModel.getCacheind().longValue()); 

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
        AVRNGModel aAVRNGModel = ( AVRNGModel ) aDAOModel; 
        if (!aAVRNGModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        if (hasChangedSinceLastRead(aConnection, aAVRNGModel.getDbCopy(), aString)) 
			throw new SQLException("Cannot Update Obsolete Data.");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String updateSets = ".AVRNG SET" + 
                                " MIN_VALUE = ? , " + 
                                " MAX_VALUE = ? , " + 
                                " ALLRNG_PROJECT = ? , " + 
                                " ALLRNG_STATUS = ? , " + 
                                " LST_CHANGED_BY = ? , " + 
                                " LST_CHANGE_DATE = ? , " + 
                                " TIME_STAMP = ? , " + 
                                " CACHEIND = ?  " + 
                                 "WHERE FIELD_KEY = ? AND " +
                                       "ALLRNG_KEY = ? ";
        String sql = "UPDATE " + connSchema + updateSets;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVRNGModel.getMinValue() == null) 
                prepStmt.setDouble(1, DAOUtils.getDefaultDouble());
            else 
                prepStmt.setDouble(1, aAVRNGModel.getMinValue().doubleValue()); 

            if (aAVRNGModel.getMaxValue() == null) 
                prepStmt.setDouble(2, DAOUtils.getDefaultDouble());
            else 
                prepStmt.setDouble(2, aAVRNGModel.getMaxValue().doubleValue()); 

            if (aAVRNGModel.getAllrngProject() == null) 
                prepStmt.setNull(3, Types.VARCHAR);
            else 
                prepStmt.setString(3, aAVRNGModel.getAllrngProject()); 

            if (aAVRNGModel.getAllrngStatus() == null) 
                prepStmt.setNull(4, Types.VARCHAR);
            else 
                prepStmt.setString(4, aAVRNGModel.getAllrngStatus()); 

            if (aAVRNGModel.getLstChangedBy() == null) 
                prepStmt.setNull(5, Types.VARCHAR);
            else 
                prepStmt.setString(5, aAVRNGModel.getLstChangedBy()); 

            if (aAVRNGModel.getLstChangeDate() == null) 
                prepStmt.setNull(6, Types.DATE);
            else 
                prepStmt.setDate(6, aAVRNGModel.getLstChangeDate()); 

            prepStmt.setTimestamp(7, new Timestamp(System.currentTimeMillis())); 

            if (aAVRNGModel.getCacheind() == null) 
                prepStmt.setLong(8, DAOUtils.getDefaultLong());
            else 
                prepStmt.setLong(8, aAVRNGModel.getCacheind().longValue()); 

            if (aAVRNGModel.getFieldKey() == null) 
                prepStmt.setNull(9, Types.VARCHAR);
            else 
                prepStmt.setString(9, aAVRNGModel.getFieldKey()); 

            if (aAVRNGModel.getAllrngKey() == null) 
                prepStmt.setNull(10, Types.VARCHAR);
            else 
                prepStmt.setString(10, aAVRNGModel.getAllrngKey()); 

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
        AVRNGModel aAVRNGModel = (AVRNGModel)aDAOModel;
        if (!aAVRNGModel.isPrimaryKeysNotNull()) 
			throw new SQLException("Primary key field(s) has null value(s)");
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVRNG ";
        String deleteWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLRNG_KEY = ?";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVRNGModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVRNGModel.getFieldKey()); 

            if (aAVRNGModel.getAllrngKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVRNGModel.getAllrngKey()); 

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
    public DAOModelCollection<AVRNGModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVRNGModel aAVRNGModel = (AVRNGModel)aDAOModel;
        String selectColumns = "SELECT FIELD_KEY, ALLRNG_KEY, MIN_VALUE, MAX_VALUE, ALLRNG_PROJECT, ALLRNG_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP, CACHEIND FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVRNG ";
        String selectWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLRNG_KEY = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVRNGModel> list = new DAOModelCollection<AVRNGModel>(aAVRNGModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVRNGModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVRNGModel.getFieldKey()); 

            if (aAVRNGModel.getAllrngKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVRNGModel.getAllrngKey()); 

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVRNGModel model = new AVRNGModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setAllrngKey(DAOUtils.getString(rs.getString("ALLRNG_KEY")));
                model.setMinValue(DAOUtils.getDouble(rs.getDouble("MIN_VALUE")));
                model.setMaxValue(DAOUtils.getDouble(rs.getDouble("MAX_VALUE")));
                model.setAllrngProject(DAOUtils.getString(rs.getString("ALLRNG_PROJECT")));
                model.setAllrngStatus(DAOUtils.getString(rs.getString("ALLRNG_STATUS")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setCacheind(DAOUtils.getLong(rs.getLong("CACHEIND")));
                model.setDbCopy((AVRNGModel)model.createCopy());
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
    public DAOModelCollection<AVRNGModel> getList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT FIELD_KEY, ALLRNG_KEY, MIN_VALUE, MAX_VALUE, ALLRNG_PROJECT, ALLRNG_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP, CACHEIND FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVRNG ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVRNGModel> list = new DAOModelCollection<AVRNGModel>(new AVRNGModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVRNGModel model = new AVRNGModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setAllrngKey(DAOUtils.getString(rs.getString("ALLRNG_KEY")));
                model.setMinValue(DAOUtils.getDouble(rs.getDouble("MIN_VALUE")));
                model.setMaxValue(DAOUtils.getDouble(rs.getDouble("MAX_VALUE")));
                model.setAllrngProject(DAOUtils.getString(rs.getString("ALLRNG_PROJECT")));
                model.setAllrngStatus(DAOUtils.getString(rs.getString("ALLRNG_STATUS")));
                model.setLstChangedBy(DAOUtils.getString(rs.getString("LST_CHANGED_BY")));
                model.setLstChangeDate(rs.getDate("LST_CHANGE_DATE"));
                model.setTimeStamp(rs.getTimestamp("TIME_STAMP"));
                model.setCacheind(DAOUtils.getLong(rs.getLong("CACHEIND")));
                model.setDbCopy((AVRNGModel)model.createCopy());
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
    public DAOModelCollection<AVRNGModel> getKeyList(Connection aConnection, String aString)
      throws SQLException 
    { 
        String selectColumns = "SELECT FIELD_KEY, ALLRNG_KEY FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVRNG ";
        String selectWhere = "WHERE 1 = 1";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        DAOModelCollection<AVRNGModel> list = new DAOModelCollection<AVRNGModel>(new AVRNGModel().getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVRNGModel model = new AVRNGModel();
                model.setModelState(DAOModel.READ_FROM_DB);
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setAllrngKey(DAOUtils.getString(rs.getString("ALLRNG_KEY")));
                model.setDbCopy((AVRNGModel)model.createCopy());
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
    public AVRNGModel getCloneModel(DAOModel aDAOModel, String aString){ 
        AVRNGModel oldAVRNGModel = ( AVRNGModel ) aDAOModel; 
        AVRNGModel aAVRNGModel = ( AVRNGModel ) oldAVRNGModel.createCopy();
        aAVRNGModel.setFieldKey(null);
        aAVRNGModel.setAllrngKey(null);
        return aAVRNGModel;
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
        AVRNGModel aAVRNGModel = ( AVRNGModel ) aDAOModel; 
        String selectColumns = "SELECT  COUNT(*)  FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVRNG ";
        String selectWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLRNG_KEY = ? AND " +
                                   " TIME_STAMP = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        ResultSet rs = null;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVRNGModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVRNGModel.getFieldKey()); 

            if (aAVRNGModel.getAllrngKey() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVRNGModel.getAllrngKey()); 

            if (aAVRNGModel.getTimeStamp() == null) 
                prepStmt.setNull(3, Types.TIMESTAMP);
            else 
                prepStmt.setTimestamp(3, aAVRNGModel.getTimeStamp()); 

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
