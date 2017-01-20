package com.csc.fsg.life.avm.api;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.avm.biz.dao.dataaccessor.AVCACHEDAO;
import com.csc.fsg.life.avm.biz.dao.model.AVCACHEModel;
import com.csc.fsg.life.avm.environments.AVMConfigBean;

/* Modifications: T0140 */

/**
 * Object used to access the cache indicator. The cache indicator is a long
 * number that is incremented each time an update is made to an allowable value.
 * The change is tagged with that indicator.
 */
public class CacheIndMgr 
{
    private static Log log = LogFactory.getLog(CacheIndMgr.class);

	/**
	 * Returns the current cache indicator from the DB.
	 * 
	 * @param avmConfigBean Used to get the schema and DB connection.
	 * @return the current cache indicator from the DB.
	 */
    public static synchronized Long getCacheIndVal(AVMConfigBean avmConfigBean)
	{
        Connection conn = null;
        Long cacheIndVal= null;
        try {
            AVCACHEDAO aVCACHEDAO = new AVCACHEDAO();
            AVCACHEModel aVCACHEModel = new AVCACHEModel();
            aVCACHEModel.setApplKey(AccessKeys.AvmApplKey);

            String schema = avmConfigBean.getSchema();
            conn = avmConfigBean.getDataSource().getConnection();
            List<AVCACHEModel> cACHEINDList = aVCACHEDAO.getList(conn, aVCACHEModel, schema);
            if (cACHEINDList.size() > 0)
                aVCACHEModel = cACHEINDList.get(0);
            
            cacheIndVal = aVCACHEModel.getCacheind();
        } catch(Exception e) {
		   log.error("Error getting cache indicator value: " + e);
        } finally {
            try {
                if (conn != null)
                	conn.close();
            } catch(SQLException cmExp) {
                log.error("Error closing connection: " + cmExp.getMessage());
            }
        }
        
        return cacheIndVal;
    }

    /**
	 * Gets and updates the cache indicator in the DB.
	 * 
	 * @param conn   The DB connection to access AVM database.
	 * @param schema The DB schema.
	 * @return the new cache indicator.
	 */
    public static synchronized Long getAndUpdateCacheIndVal(Connection conn, String schema) 
    {
        long cacheIndVal = 0;
        try {
            AVCACHEDAO aVCACHEDAO = new AVCACHEDAO();
            AVCACHEModel aVCACHEModel = new AVCACHEModel();
            aVCACHEModel.setApplKey(AccessKeys.AvmApplKey);            
            List<AVCACHEModel> cACHEINDList = aVCACHEDAO.getList(conn, aVCACHEModel, schema);
            if (cACHEINDList.size() > 0)
                aVCACHEModel = cACHEINDList.get(0);
            
            if (aVCACHEModel != null ) {
            	if (aVCACHEModel.getCacheind() != null) {
            		cacheIndVal = aVCACHEModel.getCacheind().longValue();
                    if (cacheIndVal >= (Long.MAX_VALUE - 2))
                    	cacheIndVal = 0;
                    else
                    	cacheIndVal += 2;
                    
                	aVCACHEModel.setCacheind(Long.valueOf(cacheIndVal));
            	}
            	else
            		aVCACHEModel.setCacheind(cacheIndVal);

            	aVCACHEDAO.update(conn, aVCACHEModel, schema);
            }
        } catch(Exception e) {
            log.error("Error updating cache indicator value: " + e);
        }
        
        return Long.valueOf(cacheIndVal);
    }
}