package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.csc.fsg.life.avm.biz.dao.model.AVRNGModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

public class AVRNGFKASDAOExtn extends AVRNGDAO { 


    public int delete(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException 
    { 
        AVRNGModel aAVRNGModel = (AVRNGModel)aDAOModel;
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String deleteTable = ".AVRNG ";
        String deleteWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLRNG_STATUS = ?";
        String sql = "DELETE FROM " + connSchema + deleteTable + deleteWhere;
        int result = 0;
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVRNGModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVRNGModel.getFieldKey()); 
    
            if (aAVRNGModel.getAllrngStatus() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVRNGModel.getAllrngStatus()); 
    
            result = DAOUtils.getIntFromBoolean(prepStmt.execute(), prepStmt);
        } finally {
            if (prepStmt != null)
                prepStmt.close();
        }
        return result;
    } 

    public DAOModelCollection<AVRNGModel> getList(Connection aConnection, DAOModel aDAOModel, String aString)
      throws SQLException
    { 
        AVRNGModel aAVRNGModel = (AVRNGModel)aDAOModel;
        String selectColumns = "SELECT FIELD_KEY, ALLRNG_KEY, MIN_VALUE, MAX_VALUE, ALLRNG_PROJECT, ALLRNG_STATUS, LST_CHANGED_BY, LST_CHANGE_DATE, TIME_STAMP FROM ";
        String connSchema = aString;
		DAOUtils.validString(connSchema);
        String selectTable = ".AVRNG ";
        String selectWhere = "WHERE FIELD_KEY = ?  AND " +
                                   "ALLRNG_STATUS = ?";
        String sql = selectColumns + connSchema + selectTable + selectWhere;
        sql += " ORDER BY MIN_VALUE, MAX_VALUE";
        ResultSet rs = null;
        DAOModelCollection<AVRNGModel> list = new DAOModelCollection<AVRNGModel>(aAVRNGModel.getModelName());
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            if (aAVRNGModel.getFieldKey() == null) 
                prepStmt.setNull(1, Types.VARCHAR);
            else 
                prepStmt.setString(1, aAVRNGModel.getFieldKey()); 
    
            if (aAVRNGModel.getAllrngStatus() == null) 
                prepStmt.setNull(2, Types.VARCHAR);
            else 
                prepStmt.setString(2, aAVRNGModel.getAllrngStatus()); 
    
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
}
