package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVVALModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

public class AVVALFKASSortedDisplayDAOExtn extends AVVALDAO { 


    public int delete(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException
              
    { 
        AVVALModel aAVVALModel = (AVVALModel)aDAOModel;
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String deleteTable = ".AVVAL ";
        String deleteWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLVAL_STATUS = ?";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVVALModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVVALModel.getFieldKey()); 

            if (aAVVALModel.getAllvalStatus() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVVALModel.getAllvalStatus()); 

            result = DAOUtils.getIntFromBoolean(prepStmt.execute(), prepStmt);
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

    public DAOModelCollection<AVVALModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException
              
    { 
        AVVALModel aAVVALModel = (AVVALModel)aDAOModel;
        String selectColumns = "SELECT FIELD_KEY, ALLVAL_KEY, CORE_VALUE, DISPLAY_VALUE, ALLVAL_PROJECT, ALLVAL_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
        DAOUtils.validString(connSchema);
        String selectTable = ".AVVAL ";
        String selectWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLVAL_STATUS = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY DISPLAY_VALUE";
        ResultSet rs = null;
        DAOModelCollection<AVVALModel> list = new DAOModelCollection<AVVALModel>(aAVVALModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVVALModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVVALModel.getFieldKey()); 

            if (aAVVALModel.getAllvalStatus() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVVALModel.getAllvalStatus()); 

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
}
