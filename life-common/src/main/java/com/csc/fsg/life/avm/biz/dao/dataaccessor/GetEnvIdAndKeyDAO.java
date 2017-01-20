package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.csc.fsg.life.avm.biz.dao.model.GetEnvIdAndKeyModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Custom SQL data access class.
 */ 
public class GetEnvIdAndKeyDAO { 


    /**
	 * Returns rows in the table. The sql for the access is
	 * <code> SELECT T2.ENVRNMNT_ID, T2.ENVRNMNT_KEY 		 FROM "+schemaName+".AVAPEN AS T1, "+schemaName+".AVENV AS T2 WHERE T1.ENVRNMNT_KEY=T2.ENVRNMNT_KEY AND T1.APPL_KEY = ? </code>.
	 * 
	 * @param aConnection The database connection object.
	 * @param aArrayList The parameter values to be used in the prepared
	 *            statement.
	 * @param aString The database schema.
	 * @return rows in the table.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */ 
    public DAOModelCollection<GetEnvIdAndKeyModel> getList(Connection aConnection, ArrayList<String> aArrayList, String aString)
      throws SQLException 
    { 
        String schemaName = aString;
        DAOUtils.validString(schemaName);
        ResultSet rs = null;
        DAOModelCollection<GetEnvIdAndKeyModel> list = new DAOModelCollection<GetEnvIdAndKeyModel>(new GetEnvIdAndKeyModel().getModelName());
        if ( aArrayList == null  ) throw new SQLException ( "Parameter List null." );
        int paramsize = aArrayList.size();
        if ( paramsize != 1 ) throw new SQLException ( "Parameters expected are not sent properly." );
        
        String sql = " 		SELECT T1.ENVRNMNT_ID, T1.ENVRNMNT_KEY 		FROM "+schemaName+".AVAPEN AS T2 ,"+schemaName+".AVENV AS T1 		WHERE T2.ENVRNMNT_KEY=T1.ENVRNMNT_KEY 		AND T2.APPL_KEY = ? 	 ";
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            for (int i=1; i<=paramsize; i++) {
                prepStmt.setObject(i, aArrayList.get(i-1));
            }
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                GetEnvIdAndKeyModel model = new GetEnvIdAndKeyModel();
                model.setEnvrnmntId(DAOUtils.getString(rs.getString("ENVRNMNT_ID")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setModelState ( DAOModel.READ_FROM_DB );
                model.setDbCopy ((GetEnvIdAndKeyModel)model.createCopy() );
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