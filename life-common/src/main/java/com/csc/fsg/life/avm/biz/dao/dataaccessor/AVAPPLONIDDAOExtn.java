package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVAPPLModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Extends the data access for table AVAPPL.
 */
public class AVAPPLONIDDAOExtn extends AVAPPLDAO { 


	/**
	 * Deletes a row in the AVAPPL table for the specified application.
	 * 
	 * @param aConnection The DB connection to the AVM database.
	 * @param aDAOModel The data for the row.
	 * @param aString The schema in the AVM database.
	 * @throws SQLException if there is an I/O problem with the SQL.
	 */
    public int delete(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPPLModel aAVAPPLModel = (AVAPPLModel)aDAOModel;
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVAPPL ";
        String deleteWhere = "WHERE APPL_ID = ? ";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPPLModel.getApplId() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPPLModel.getApplId()); 

            result = DAOUtils.getIntFromBoolean(prepStmt.execute(), prepStmt);
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

	/**
	 * Gets a list of rows from the AVAPPL table for the specified application.
	 * 
	 * @param aConnection The DB connection to the AVM database.
	 * @param aDAOModel The data for the row.
	 * @param aString The schema in the AVM database.
	 * @throws SQLException if there is an I/O problem with the SQL.
	 */
    public DAOModelCollection<AVAPPLModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPPLModel aAVAPPLModel = (AVAPPLModel)aDAOModel;
        String selectColumns = "SELECT APPL_KEY, APPL_ID, APPL_DESC, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVAPPL ";
        String selectWhere = "WHERE APPL_ID = ? ";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY APPL_ID";
        ResultSet rs = null;
        DAOModelCollection<AVAPPLModel> list = new DAOModelCollection<AVAPPLModel>(aAVAPPLModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPPLModel.getApplId() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPPLModel.getApplId()); 

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
}
