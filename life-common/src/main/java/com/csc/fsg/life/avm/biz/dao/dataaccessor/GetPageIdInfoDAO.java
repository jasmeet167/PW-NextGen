package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.csc.fsg.life.avm.biz.dao.model.GetPageIdInfoModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Custom SQL data access class.
 */ 
public class GetPageIdInfoDAO { 


    /**
	 * Returns rows in the table. The SQL for the access is
	 * <code> SELECT T2.APPL_KEY, T2.ENVRNMNT_KEY, T1.PAGE_KEY, T1.PAGE_ID, T1.PAGE_DESC FROM AVAEP AS "+schemaName+".T2 AS T2, AVPAGE AS "+schemaName+".T1 AS T1 WHERE T2.APPL_KEY= T1.APPL_KEY AND T2.PAGE_KEY= T1.PAGE_KEY AND T2.APPL_KEY=? </code>.
	 * 
	 * @param aConnection The database connection object.
	 * @param aArrayList The parameter values to be used in the prepared
	 *            statement.
	 * @param aString The database schema.
	 * @return rows in the table.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */ 
    public DAOModelCollection<GetPageIdInfoModel> getList(Connection aConnection, ArrayList<?> aArrayList, String aString)
      throws SQLException 
    { 
        String schemaName = aString;
        DAOUtils.validString(schemaName);
        ResultSet rs = null;
        DAOModelCollection<GetPageIdInfoModel> list = new DAOModelCollection<GetPageIdInfoModel>(new GetPageIdInfoModel().getModelName());
        if (aArrayList == null) 
        	throw new SQLException ( "Parameter List null." );
        int paramsize = aArrayList.size();
        if (paramsize != 1) 
        	throw new SQLException ( "Parameters expected are not sent properly." );
        
        String sql = " 		SELECT T2.APPL_KEY, T2.ENVRNMNT_KEY, T1.PAGE_KEY, T1.PAGE_ID, T1.PAGE_DESC 		FROM "+schemaName+".AVAEP AS T2, "+schemaName+".AVPAGE AS T1 		WHERE T2.APPL_KEY= T1.APPL_KEY 		AND T2.PAGE_KEY= T1.PAGE_KEY 		AND T2.APPL_KEY=? 	 ";
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            for (int i=1; i<=paramsize; i++) {
                prepStmt.setObject(i, aArrayList.get(i-1));
            }
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                GetPageIdInfoModel model = new GetPageIdInfoModel();
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setPageKey(DAOUtils.getString(rs.getString("PAGE_KEY")));
                model.setPageId(DAOUtils.getString(rs.getString("PAGE_ID")));
                model.setPageDesc(DAOUtils.getString(rs.getString("PAGE_DESC")));
                model.setModelState ( DAOModel.READ_FROM_DB );
                model.setDbCopy ((GetPageIdInfoModel)model.createCopy() );
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