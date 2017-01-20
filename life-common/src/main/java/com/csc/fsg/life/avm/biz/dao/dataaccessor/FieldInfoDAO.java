package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.csc.fsg.life.avm.api.AccessKeys;
import com.csc.fsg.life.avm.biz.dao.model.FieldInfoModel;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Data access for field data for all fields in the given application,
 * environment for all pages.
 */
public class FieldInfoDAO { 

	/**
	 * getList: return a collection of FieldInfo objects representing all fields
	 * in all pages associated with the given application and environment.  Includes
	 * the wildcard page (AVPAGEALL).
	 * 
	 * @param aConnection
	 * @param aArrayList - must contain 2 items: application key and environment key
	 * @param aString - the schema name
	 * @throws SQLException
	 */
    public DAOModelCollection<FieldInfoModel> getList(Connection aConnection, ArrayList<String> aArrayList, String aString)
      throws SQLException 
    { 
        String schemaName = aString;
        DAOUtils.validString(schemaName);
        ResultSet rs = null;
        DAOModelCollection<FieldInfoModel> list = new DAOModelCollection<FieldInfoModel>(new FieldInfoModel().getModelName());
        if (aArrayList == null) 
        	throw new SQLException ( "Parameter List null." );
        int paramsize = aArrayList.size();
        if (paramsize != 2) 
        	throw new SQLException ( "Parameters expected are not sent properly." );

		// Insert constants for all in list.
		aArrayList.add(0, "AVAPPLALL");
		aArrayList.add(2, AccessKeys.AllEnvKey);
        aArrayList.add(4, "D"); // Not in the deleted status.
		
        
        // 4 table join selecting all fields in all pages associated with the given application
        // and environment or the default application and environment.
        String sql = 
			"	SELECT T1.APPL_KEY, T1.ENVRNMNT_KEY, T1.PAGE_KEY, T1.COMPANY_KEY, T1.PRODUCT"
        	+ ", T1.TRX_CODE, T1.FIELD_KEY, T2.FIELD_NAME, T2.FIELD_DESC, T2.FIELD_TYPE"
			+ ", T2.LIST_DEFAULT, T2.RANGE_DEFAULT"
			+ ", T4.PAGE_ID, T4.PAGE_DESC"
			+ "	FROM "+schemaName+".AVALFLD AS T1, "+schemaName+".AVFLD AS T2"
			+ ", "+schemaName+".AVAEP AS T3, "+schemaName+".AVPAGE AS T4"
			+ "	WHERE T1.FIELD_KEY = T2.FIELD_KEY     AND T1.APPL_KEY IN (?, ?)"
			+ " AND T1.ENVRNMNT_KEY IN (?, ?)"
			+ " AND T1.FIELD_STATUS <> ?"
			+ " AND T1.APPL_KEY = T3.APPL_KEY AND T1.PAGE_KEY= T3.PAGE_KEY"
			+ " AND T1.APPL_KEY = T4.APPL_KEY AND T1.PAGE_KEY= T4.PAGE_KEY"
			+ " ORDER BY T1.PAGE_KEY";
        
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            for (int i=1; i<=aArrayList.size(); i++) {
                prepStmt.setObject(i, aArrayList.get(i-1));
            }
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                FieldInfoModel model = new FieldInfoModel();
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setPageKey(DAOUtils.getString(rs.getString("PAGE_KEY")));
                model.setPageId(DAOUtils.getString(rs.getString("PAGE_ID")));
                model.setPageDesc(DAOUtils.getString(rs.getString("PAGE_DESC")));
                model.setCompanyKey(DAOUtils.getString(rs.getString("COMPANY_KEY")));
                model.setProduct(DAOUtils.getString(rs.getString("PRODUCT")));
                model.setTrxCode(DAOUtils.getString(rs.getString("TRX_CODE")));
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setFieldName(DAOUtils.getString(rs.getString("FIELD_NAME")));
                model.setFieldDesc(DAOUtils.getString(rs.getString("FIELD_DESC")));
                model.setFieldType(DAOUtils.getString(rs.getString("FIELD_TYPE")));
                model.setListDefault(DAOUtils.getString(rs.getString("LIST_DEFAULT")));
                model.setRangeDefault(DAOUtils.getString(rs.getString("RANGE_DEFAULT")));
                model.setModelState ( DAOModel.READ_FROM_DB );
                model.setDbCopy ((FieldInfoModel)model.createCopy() );
                list.add(model);
            }
        } finally {
            if (rs != null)
                rs.close(); 
            if (prepStmt != null)
                prepStmt.close();
        }

		// Insert constants for all in list.
		aArrayList.add(AccessKeys.AllPageKey);

        // now 3 table join to get all fields in the wild card page associated with the given application
        // and environment or the default application and environment.
        sql = "	SELECT DISTINCT T1.APPL_KEY, T1.ENVRNMNT_KEY, T1.PAGE_KEY, T1.COMPANY_KEY, T1.PRODUCT"
        	+ ", T1.TRX_CODE, T1.FIELD_KEY, T2.FIELD_NAME, T2.FIELD_DESC, T2.FIELD_TYPE"
			+ ", T2.LIST_DEFAULT, T2.RANGE_DEFAULT"
			+ "	FROM "+schemaName+".AVALFLD AS T1, "+schemaName+".AVFLD AS T2"
			+ ", "+schemaName+".AVAEP AS T3"
			+ "	WHERE T1.FIELD_KEY = T2.FIELD_KEY     AND T1.APPL_KEY IN (?, ?)"
			+ " AND T1.ENVRNMNT_KEY IN (?, ?)"
			+ " AND T1.FIELD_STATUS <> ?"
			+ " AND T1.APPL_KEY = T3.APPL_KEY AND T1.PAGE_KEY = ?"
			+ " ORDER BY T1.FIELD_KEY";
        
        prepStmt = aConnection.prepareStatement(sql);
        try {
            for (int i=1; i<=aArrayList.size(); i++) {
                prepStmt.setObject(i, aArrayList.get(i-1));
            }
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                FieldInfoModel model = new FieldInfoModel();
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setPageKey(DAOUtils.getString(rs.getString("PAGE_KEY")));
                model.setPageId("ALL");
                model.setPageDesc("All Pages");
                model.setCompanyKey(DAOUtils.getString(rs.getString("COMPANY_KEY")));
                model.setProduct(DAOUtils.getString(rs.getString("PRODUCT")));
                model.setTrxCode(DAOUtils.getString(rs.getString("TRX_CODE")));
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setFieldName(DAOUtils.getString(rs.getString("FIELD_NAME")));
                model.setFieldDesc(DAOUtils.getString(rs.getString("FIELD_DESC")));
                model.setFieldType(DAOUtils.getString(rs.getString("FIELD_TYPE")));
                model.setListDefault(DAOUtils.getString(rs.getString("LIST_DEFAULT")));
                model.setRangeDefault(DAOUtils.getString(rs.getString("RANGE_DEFAULT")));
                model.setModelState ( DAOModel.READ_FROM_DB );
                model.setDbCopy ((FieldInfoModel)model.createCopy() );
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
