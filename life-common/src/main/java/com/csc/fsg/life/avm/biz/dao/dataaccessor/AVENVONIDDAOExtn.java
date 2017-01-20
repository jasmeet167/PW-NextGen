package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVENVModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Extension to AVENV access object to access by specific environments.
 */
public class AVENVONIDDAOExtn extends AVENVDAO { 


	/**
	 * Deletes a row in the AVENV table.
	 * 
	 * @param aConnection The DB connection to the AVM database.
	 * @param aDAOModel The data for the row.
	 * @param aString The schema in the AVM database.
	 * @throws SQLException if there is an I/O problem with the SQL.
	 */
    public int delete(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVENVModel aAVENVModel = (AVENVModel)aDAOModel;
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVENV ";
        String deleteWhere = "WHERE ENVRNMNT_ID = ? ";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVENVModel.getEnvrnmntId() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVENVModel.getEnvrnmntId()); 

            result = DAOUtils.getIntFromBoolean(prepStmt.execute(), prepStmt);
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

	/**
	 * Gets a list of rows from the AVENV table. The rows are for a specific
	 * environment.
	 * 
	 * @param aConnection The DB connection to the AVM database.
	 * @param aDAOModel The data for the row.
	 * @param aString The schema in the AVM database.
	 * @throws SQLException if there is an I/O problem with the SQL.
	 */
    public DAOModelCollection<AVENVModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVENVModel aAVENVModel = (AVENVModel)aDAOModel;
        String selectColumns = "SELECT ENVRNMNT_KEY, ENVRNMNT_ID, ENVRNMNT_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVENV ";
        String selectWhere = "WHERE ENVRNMNT_ID = ? ";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY ENVRNMNT_ID";
        ResultSet rs = null;
        DAOModelCollection<AVENVModel> list = new DAOModelCollection<AVENVModel>(aAVENVModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVENVModel.getEnvrnmntId() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVENVModel.getEnvrnmntId()); 

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
}
