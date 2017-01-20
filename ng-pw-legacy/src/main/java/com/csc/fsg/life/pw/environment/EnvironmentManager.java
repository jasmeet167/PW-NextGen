
package com.csc.fsg.life.pw.environment;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;
import com.csc.fsg.life.common.config.CommonApplicationConfigBean;
import com.csc.fsg.life.common.config.EnvDbInfoBean;
import com.csc.fsg.life.convert.ASCIIConverter;
import com.csc.fsg.life.pw.config.AppConfig;
import com.csc.fsg.life.pw.io.InfoException;
import com.csc.fsg.life.pw.io.StructureTableCache;
import com.csc.fsg.life.pw.log.PWServerLogManager;
import com.csc.fsg.life.security.SecurityManager;

/* Modifications: T0091 ,T0115, T0113, T0129, ENH1063-05, ENH1220, T0195 */
// ENH961 - use InfoException

public class EnvironmentManager {

	private static Log _log = PWServerLogManager.getLog(EnvironmentManager.class
	        .getPackage().getName());
	
	private Map<String, Environment> environments = new TreeMap<String, Environment>();		
	
    private SecurityManager securityManager = null;
    
    public EnvironmentManager()   {
    	_log.info("Creating Environment Manager");
    }
    
    public static EnvironmentManager getInstance() {
		return (EnvironmentManager) AppConfig.getContext().getBean("envManager");
    }
    
	public Map<String, Environment> getEnvironments() {
		return environments;
	}

	public void setEnvironments(Map<String, Environment> environments) {
		this.environments = environments;
	}

	public Environment getEnvironment(String key){
		if (key!=null)
			return environments.get(key.trim());
		return null;
	}
	
	public void setEnvironment(String key,Environment env){
		environments.put(key,env);
	}
	
	public SecurityManager getSecurityManager() {
		return securityManager;
	}
	
	public void setSecurityManager(SecurityManager securityManager) {
		this.securityManager = securityManager;
	}
	
	@PostConstruct
	public void init() {
		
		Map cenvs = CommonApplicationConfigBean.getInstance().getEnvironments();
		Iterator iter = cenvs.keySet().iterator();
		while (iter.hasNext()){
			ApplicationEnvironmentBean bean = null;
			try{
				bean = (ApplicationEnvironmentBean)cenvs.get(iter.next());
				String envId = bean.getName();
				String hv = bean.getHighValueEncoding();
				if (hv==null)
					throw new InfoException("HighValue Encoding is not set for "+envId);
				
				Environment env = new Environment();
				env.setId(envId);
				env.setDescription(bean.getDisplayName());
				env.setBrDatasource(bean.getJndiDataSource());
				env.setSchema(((EnvDbInfoBean)bean.getDefaultEnvDbInfo(EnvDbInfoBean.TYPE_RULE)).getSchema());
				env.setProps(AppConfig.getEnvProps(envId));
				env.setNexEnv(bean.getConverter() instanceof ASCIIConverter);
				env.setHighValueEncoding(bean.getHighValueEncoding());
				environments.put(envId, env);
				env.initialize();
				
			}catch (Exception e) {
				//continue loading other environments
				String message = "";
				if ( bean != null )
					message = "Environment " + bean.getName() + " (" + bean.getDisplayName() + ") failed to load";
				_log.error(message, e);
			}
		}
		StructureTableCache.getInstance();
	}
}
