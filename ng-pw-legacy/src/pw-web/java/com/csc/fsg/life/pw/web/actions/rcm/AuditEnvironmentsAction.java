package com.csc.fsg.life.pw.web.actions.rcm;


import java.sql.*;
import java.util.*;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.avm.api.*;
import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.pw.common.util.*;
import com.csc.fsg.life.pw.web.actions.rcm.beans.ExternalAuditBean;
import com.csc.fsg.life.pw.web.config.ProductManager;
import com.csc.fsg.life.pw.web.controller.*;
import com.csc.fsg.life.pw.web.log.PWServerLogManager;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;


public class AuditEnvironmentsAction extends BaseAction {

	public static final String EXT_AUDIT_PREFIX = Constants.EXT_AUDIT_PREFIX;
	
	public void process(IRequest reqObj, IResponse out) throws Exception {	
//		StringBuffer sb = getEnvironmentsList(reqObj.getUser().getProductSelected());
//		out.print(sb.toString());
	}

	private StringBuffer getEnvironmentsList(String productKey) throws Exception {
		
		Log _log = PWServerLogManager.getLog(AuditEnvironmentsAction.class.getPackage().getName());
		StringBuffer sb = new StringBuffer();
		
		Connection auditConn = null;
		Statement stmt = null;
		ResultSet rs = null;
				
		try {
			
			ExternalAuditBean extBean = ProductManager.getInstance().getProduct(productKey).getExtAuditBean();
			if (extBean==null)
				return sb;
			
			AVMConfigBean avmBean = ProductManager.getInstance().getProduct(productKey).getAvmBean();
			AVMManager avManager = AVMManager.getInstance(avmBean);
			
			List envs = avManager.getEnvironmentsForAppID(avmBean.getApplicationId());
			
			HashMap envMap = new HashMap();
			for (Iterator envItr = envs.iterator(); envItr.hasNext();) {
				AVMIdDescPair env = (AVMIdDescPair) envItr.next();
				envMap.put(env.getIdVal().trim(),env.getDescription().trim());
			}
						
			auditConn = extBean.getDataSource().getConnection();
			stmt = auditConn.createStatement();
			rs = stmt.executeQuery("Select DISTINCT(ENVIRONMENT) from "+ extBean.getSchema()+ ".AUDIT");
			
			while (rs.next()){
				String env = rs.getString(1).trim();
				String	avDesc = (String)envMap.get(env);
				if (avDesc==null)
					avDesc = env;
				sb.append(EXT_AUDIT_PREFIX+env+"\t"+avDesc+"("+extBean.getEnvPrefix()+")" +"\n");
			}
			
			_log.debug("Ext Audit environments "+sb.toString());
			
		
		}finally{
			Utils.closeResultSet(rs);
			Utils.closeStatement(stmt);
			DBConnMgr.getInstance().releaseConnection(auditConn);
		}
		return sb;
	}
	
}
