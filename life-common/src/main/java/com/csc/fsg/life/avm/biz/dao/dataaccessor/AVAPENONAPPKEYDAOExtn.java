package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVAPENModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Extension access for table AVAPEN (application environment).
 */
public class AVAPENONAPPKEYDAOExtn extends AVAPENDAO { 


	/**
	 * Deletes a row in the AVAPEN (application environment) table.
	 * 
	 * @param aConnection The DB connection to the AVM database.
	 * @param aDAOModel The data for the row.
	 * @param aString The schema in the AVM database.
	 * @throws SQLException if there is an I/O problem with the SQL.
	 */
    public int delete(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPENModel aAVAPENModel = (AVAPENModel)aDAOModel;
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVAPEN ";
        String deleteWhere = "WHERE APPL_KEY = ? ";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPENModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPENModel.getApplKey()); 

            result = DAOUtils.getIntFromBoolean(prepStmt.execute(), prepStmt);
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

	/**
	 * Gets a list of rows from the AVAPEN (application environment) table for
	 * the specified application.
	 * 
	 * @param aConnection The DB connection to the AVM database.
	 * @param aDAOModel The data for the row.
	 * @param aString The schema in the AVM database.
	 * @throws SQLException if there is an I/O problem with the SQL.
	 */
    public DAOModelCollection<AVAPENModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVAPENModel aAVAPENModel = (AVAPENModel)aDAOModel;
        String selectColumns = "SELECT APPL_KEY, ENVRNMNT_KEY, DESCRIPTION, SUBSYSTEM, SCHEMA, FROM_PRMT_ENVRM, FROM_PRMT_DATE, FROM_PRMT_TIMESTAMP, FROM_PRMT_BY, FROM_PRMT_OPTION, FROM_PRMT_CHG_DATE, FROM_PRMT_CHG_PROJ, FROM_EXPORT_FILE, TO_PRMT_ENVRM, TO_PRMT_DATE, TO_PRMT_TIMESTAMP, TO_PRMT_BY, TO_PRMT_OPTION, TO_PRMT_CHG_DATE, TO_PRMT_CHG_PROJ, TO_EXPORT_FILE, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVAPEN ";
        String selectWhere = "WHERE APPL_KEY = ? ";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY APPL_KEY";
        ResultSet rs = null;
        DAOModelCollection<AVAPENModel> list = new DAOModelCollection<AVAPENModel>(aAVAPENModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVAPENModel.getApplKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVAPENModel.getApplKey()); 

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
}
