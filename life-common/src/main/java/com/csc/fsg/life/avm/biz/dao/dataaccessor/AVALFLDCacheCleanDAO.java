package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.csc.fsg.life.avm.api.AccessKeys;
import com.csc.fsg.life.avm.biz.dao.model.AVALFLDCacheCleanModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/* Modifications: T0140 */

public class AVALFLDCacheCleanDAO { 

    public DAOModelCollection<AVALFLDCacheCleanModel> getList(Connection aConnection, ArrayList<?> aArrayList, String aString)
        throws SQLException 
    { 
        String schemaName = aString;
        DAOUtils.validString(schemaName);
        ResultSet rs = null;
        String applicationString = "";
        String cacheIndString =	"";
        DAOModelCollection<AVALFLDCacheCleanModel> list = new DAOModelCollection<AVALFLDCacheCleanModel>(new AVALFLDCacheCleanModel().getModelName());
        if (aArrayList == null) 
        	throw new SQLException("Parameter List null.");
        
        int paramsize = aArrayList.size();
        if (paramsize != 3) 
        	throw new SQLException("Parameters expected are not sent properly.");
        
		AVMCacheCleanSQLhelper avmCacheCleanSQLhelper = new AVMCacheCleanSQLhelper(aArrayList);
		applicationString = avmCacheCleanSQLhelper.getApplicationKey();
		cacheIndString = avmCacheCleanSQLhelper.getCacheIndVal();
        String prepEnvironmentString = avmCacheCleanSQLhelper.getPrepEnvironmentParamString();
		List<?> prepEnvironmentList = avmCacheCleanSQLhelper.getPrepEnvironmentList();

        String sql = 
			"SELECT DISTINCT(T1.FIELD_KEY) " + 
			  "FROM "+schemaName+".AVALFLD AS T1 " + 
			 "WHERE T1.APPL_KEY IN (?, ?) " + 
			   "AND T1.ENVRNMNT_KEY IN (?" + prepEnvironmentString + " ) " + 
			   "AND T1.CACHEIND > ? ";
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
		int nextParam = 1;
		prepStmt.setString(nextParam, "AVAPPLALL");
		nextParam++;
		prepStmt.setString(nextParam, applicationString);
		nextParam++;
		prepStmt.setString(nextParam, AccessKeys.AllEnvKey);
		nextParam++;
		for(Iterator<?> itt = prepEnvironmentList.iterator(); itt.hasNext(); ) {
			prepStmt.setString(nextParam, (String)itt.next());
			nextParam++;			
		}
		prepStmt.setString(nextParam, cacheIndString);
		nextParam++;			

        try {
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                AVALFLDCacheCleanModel model = new AVALFLDCacheCleanModel();
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setModelState( DAOModel.READ_FROM_DB );
                model.setDbCopy((AVALFLDCacheCleanModel)model.createCopy() );
                list.add(model);
            }
        } 
        finally {
            if (rs != null)
                rs.close(); 
            if (prepStmt != null)
				prepStmt.close();
        }
        return list;
    } 
}
