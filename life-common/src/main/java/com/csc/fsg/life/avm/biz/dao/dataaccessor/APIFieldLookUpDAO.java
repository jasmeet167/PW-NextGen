package com.csc.fsg.life.avm.biz.dao.dataaccessor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.csc.fsg.life.avm.api.AccessKeys;
import com.csc.fsg.life.avm.biz.dao.model.APIFieldLookUpModel;
import com.csc.fsg.life.avm.biz.dao.model.AllowableValuesField;
import com.csc.fsg.life.dao.dataaccessor.DAOUtils;
import com.csc.fsg.life.dao.model.DAOModel;
import com.csc.fsg.life.dao.model.DAOModelCollection;

/**
 * Custom SQL data access class.
 */ 
public class APIFieldLookUpDAO { 


    /**
	 * Returns rows in the table. The SQL for the access is
	 * <code> SELECT T1.APPL_KEY, T1.ENVRNMNT_KEY, T1.PAGE_KEY, T1.COMPANY_KEY, T1.PRODUCT, T1.TRX_CODE, T1.FIELD_KEY, T2.FIELD_TYPE, T2.LIST_DEFAULT, T2.RANGE_DEFAULT FROM "+schemaName+".AVALFLD AS T1, "+schemaName+".AVFLD AS T2 WHERE T1.FIELD_KEY=T2.FIELD_KEY AND T1.APPL_KEY IN ('AVAPPLALL', ?) AND T1.ENVRNMNT_KEY IN ('AVENVBASE', ?) AND T1.PAGE_KEY IN ('AVPAGEALL', ?) AND T1.COMPANY_KEY IN ('ALL', ?) AND T1.PRODUCT IN ('ALL', ?) AND T1.TRX_CODE IN ('ALL', ?) AND T1.FIELD_STATUS <> 'D' AND UPPER(T2.FIELD_NAME)=? ORDER BY T1.ENVRNMNT_KEY,  T1.COMPANY_KEY,  T1.PRODUCT,  T1.TRX_CODE 	</code>.
	 * 
	 * @param aConnection The database connection object.
	 * @param aArrayList The parameter values to be used in the prepared
	 *            statement.
	 * @param aString The database schema.
	 * @return rows in the table.
	 * @throws SQLException Thrown when an I/O problem occurs executing the SQL.
	 */ 
    public DAOModelCollection<APIFieldLookUpModel> getList(Connection aConnection, ArrayList<String> aArrayList, String aString)
      throws SQLException
    { 
        String schemaName = aString;
        DAOUtils.validString(schemaName);
        ResultSet rs = null;
        DAOModelCollection<APIFieldLookUpModel> list = new DAOModelCollection<APIFieldLookUpModel>(new APIFieldLookUpModel().getModelName());
        if ( aArrayList == null  ) throw new SQLException ( "Parameter List null." );
        int paramsize = aArrayList.size();
        if ( paramsize != 7 ) throw new SQLException ( "Parameters expected are not sent properly." );

		// Insert constants for all in list.
		aArrayList.add(0, "AVAPPLALL");
		aArrayList.add(2, AccessKeys.AllEnvKey);
		aArrayList.add(4, AccessKeys.AllPageKey);
		aArrayList.add(6, AccessKeys.AllCompanyKey);
		aArrayList.add(8, AccessKeys.AllProductKey);
		aArrayList.add(10, AccessKeys.AllTrxCodeKey);
        aArrayList.add(12, "D"); // Not in the deleted status.

        String sql = 
			" SELECT "+schemaName+".AVALFLD.APPL_KEY, " + 
			"        "+schemaName+".AVALFLD.ENVRNMNT_KEY, " + 
			"        "+schemaName+".AVALFLD.PAGE_KEY, " + 
			"        "+schemaName+".AVALFLD.COMPANY_KEY, " + 
			"        "+schemaName+".AVALFLD.PRODUCT, " + 
			"        "+schemaName+".AVALFLD.TRX_CODE, " + 
			"        "+schemaName+".AVALFLD.FIELD_KEY, " + 
			"        "+schemaName+".AVFLD.FIELD_TYPE, " + 
			"        "+schemaName+".AVFLD.LIST_DEFAULT, " + 
			"        "+schemaName+".AVFLD.RANGE_DEFAULT " + 
			"  FROM  "+schemaName+".AVALFLD, "+schemaName+".AVFLD " + 
			"  WHERE "+schemaName+".AVALFLD.FIELD_KEY="+schemaName+".AVFLD.FIELD_KEY " + 
			"    AND "+schemaName+".AVALFLD.APPL_KEY IN (?, ?) " + 
			"    AND "+schemaName+".AVALFLD.ENVRNMNT_KEY IN (?, ?) " + 
			"    AND "+schemaName+".AVALFLD.PAGE_KEY IN (?, ?) " + 
			"    AND "+schemaName+".AVALFLD.COMPANY_KEY IN (?, ?) " + 
			"    AND "+schemaName+".AVALFLD.PRODUCT IN (?, ?) " + 
			"    AND "+schemaName+".AVALFLD.TRX_CODE IN (?, ?) " + 
			"    AND "+schemaName+".AVALFLD.FIELD_STATUS <> ? " + 
			"    AND UPPER("+schemaName+".AVFLD.FIELD_NAME)=? " + 
			"  ORDER BY "+schemaName+".AVALFLD.ENVRNMNT_KEY, " + 
			"           "+schemaName+".AVALFLD.COMPANY_KEY, " + 
			"           "+schemaName+".AVALFLD.PRODUCT, " + 
			"           "+schemaName+".AVALFLD.TRX_CODE";
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
            for (int i=1; i<=aArrayList.size(); i++) {
                prepStmt.setObject(i, aArrayList.get(i-1));
            }
            rs = prepStmt.executeQuery();
            while(rs.next()) { 
                APIFieldLookUpModel model = new APIFieldLookUpModel();
                model.setApplKey(DAOUtils.getString(rs.getString("APPL_KEY")));
                model.setEnvrnmntKey(DAOUtils.getString(rs.getString("ENVRNMNT_KEY")));
                model.setPageKey(DAOUtils.getString(rs.getString("PAGE_KEY")));
                model.setCompanyKey(DAOUtils.getString(rs.getString("COMPANY_KEY")));
                model.setProduct(DAOUtils.getString(rs.getString("PRODUCT")));
                model.setTrxCode(DAOUtils.getString(rs.getString("TRX_CODE")));
                model.setFieldKey(DAOUtils.getString(rs.getString("FIELD_KEY")));
                model.setFieldType(DAOUtils.getString(rs.getString("FIELD_TYPE")));
                model.setListDefault(DAOUtils.getString(rs.getString("LIST_DEFAULT")));
                model.setRangeDefault(DAOUtils.getDouble(rs.getDouble("RANGE_DEFAULT")));
                model.setModelState ( DAOModel.READ_FROM_DB );
                model.setDbCopy ((APIFieldLookUpModel)model.createCopy() );
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
    
    public DAOModelCollection<AllowableValuesField> getListOfPossibleReferences(Connection aConnection, 
    													  						String applicationKey, 
    													  						String environmentKey, 
    													  						String fieldName, 
    													  						String aString)
      throws SQLException
    { 
        String schemaName = aString;
        DAOUtils.validString(schemaName);
        ResultSet rs = null;
        DAOModelCollection<AllowableValuesField> list = new DAOModelCollection<AllowableValuesField>(new AllowableValuesField().getModelName());
        
        String sql = 
			" SELECT "+schemaName+".AVFLD.FIELD_NAME, " + 
			"        "+schemaName+".AVFLD.FIELD_DESC" + 
			"  FROM  "+schemaName+".AVALFLD, "+schemaName+".AVFLD " + 
			"  WHERE "+schemaName+".AVALFLD.FIELD_KEY="+schemaName+".AVFLD.FIELD_KEY " + 
			"    AND "+schemaName+".AVALFLD.APPL_KEY IN ('AVAPPLALL', ?) " + 
			"    AND "+schemaName+".AVALFLD.ENVRNMNT_KEY IN ('" + AccessKeys.AllEnvKey + "', ?) " + 
			"    AND "+schemaName+".AVALFLD.FIELD_STATUS <> 'D' " + 
			"    AND UPPER("+schemaName+".AVFLD.FIELD_NAME) like ?" + 
			"  ORDER BY "+schemaName+".AVFLD.FIELD_NAME";
        PreparedStatement prepStmt = aConnection.prepareStatement(sql);
        try {
        	prepStmt.setObject(1, applicationKey);
        	prepStmt.setObject(2, environmentKey);
            prepStmt.setObject(3, fieldName.toUpperCase() + "%");

            rs = prepStmt.executeQuery();
            while(rs.next()) { 
            	AllowableValuesField model = new AllowableValuesField();
                model.setFieldName(DAOUtils.getString(rs.getString("FIELD_NAME")));
                model.setFieldDesc(DAOUtils.getString(rs.getString("FIELD_DESC")));
                model.setModelState (DAOModel.READ_FROM_DB);
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
