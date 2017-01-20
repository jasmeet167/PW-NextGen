package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.csc.fsg.life.avm.api.AccessKeys;
import com.csc.fsg.life.avm.biz.dao.model.AVVALCacheCleanModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/* Modifications: T0140 */

/**
 * Custom SQL data access class.
 */ 
public class AVVALCacheCleanDAO { 


    /**
	 * Returns rows in the table. The sql for the access is
	 * <code> SELECT DISTINCT(T2.FIELD_KEY) FROM "+schemaName+".AVVAL AS T2, "+schemaName+".AVALFLD AS T1 WHERE T2.FIELD_KEY = T1.FIELD_KEY AND T1.APPL_KEY IN ('AVAPPLALL', ?) AND T1.ENVRNMNT_KEY IN ('AVENVBASE', ?) AND T2.TIME_STAMP > ? 	</code>.
	 * 
	 * @param aConnection The database connection object.
	 * @param aArrayList The parameter values to be used in the prepared
	 *            statement.
	 * @param aString The database schema.
	 * @return rows in the table.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */ 
    public DAOModelCollection<AVVALCacheCleanModel> getList(Connection aConnection, ArrayList<?> aArrayList, String aString)
      throws SQLException 
    { 
        String schemaName = aString;
        DAOUtils.validString(schemaName);
        ResultSet rs = null;
        DAOModelCollection<AVVALCacheCleanModel> list = new DAOModelCollection<AVVALCacheCleanModel>(new AVVALCacheCleanModel().getModelName());
        if ( aArrayList == null  ) 
        	throw new SQLException ( "Parameter List null." );
        int paramsize = aArrayList.size();
		if ( paramsize != 3 ) 
			throw new SQLException ( "Parameters expected are not sent properly." );

		AVMCacheCleanSQLhelper avmCacheCleanSQLhelper	= new AVMCacheCleanSQLhelper(aArrayList);
		String applicationString = avmCacheCleanSQLhelper.getApplicationKey();
		String cacheIndString = avmCacheCleanSQLhelper.getCacheIndVal();
        String prepEnvironmentString = avmCacheCleanSQLhelper.getPrepEnvironmentParamString();
		List<?> prepEnvironmentList = avmCacheCleanSQLhelper.getPrepEnvironmentList();

        String sql = 
			"SELECT DISTINCT(" + schemaName + ".AVVAL.FIELD_KEY) " + 
			"FROM " + schemaName + ".AVVAL, " + schemaName + ".AVALFLD " + 
			"WHERE " + schemaName + ".AVVAL.FIELD_KEY = " + schemaName + ".AVALFLD.FIELD_KEY " + 
			" AND "+schemaName+".AVALFLD.APPL_KEY IN (?,?) " + 
			" AND "+schemaName+".AVALFLD.ENVRNMNT_KEY IN (?"+prepEnvironmentString+") " + 
			" AND "+schemaName+".AVVAL.CACHEIND > ? ";

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
                AVVALCacheCleanModel model = new AVVALCacheCleanModel();
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setModelState ( DAOModel.READ_FROM_DB );
                model.setDbCopy ((AVVALCacheCleanModel)model.createCopy() );
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
