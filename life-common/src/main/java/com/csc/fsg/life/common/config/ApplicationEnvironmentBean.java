package com.csc.fsg.life.common.config;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.biz.service.ServiceManager;
import com.csc.fsg.life.context.UserContext;
import com.csc.fsg.life.convert.Converter;
import com.csc.fsg.life.environments.IEnvConnection;

/* Modifications: ENH858, P0158, T0150, T0160, T0196 */

/**
 * This class represents a back-end environment, Configured via spring Context.
 */
public class ApplicationEnvironmentBean {

    /** Class Logger from Apache Commons Logging. */
    protected static final Log log = LogFactory.getLog(UserContext.class);
    
    /** Thread local variable containing the selected environment */
    private static ThreadLocal<ApplicationEnvironmentBean> environment = new ThreadLocal<ApplicationEnvironmentBean>();
    
	private String name;
	private String displayName;
	private DataSource jndiDataSource;
	private String dataBaseId;
	@SuppressWarnings("unchecked")
	private Map envDbInfo = new HashMap();
	private IEnvConnection connectionInfo;
	private String avmEnvironmentName;
	private boolean debugAllowed = false;
	private String defaultLogSwitch = "";
	private ApplicationEnvironmentBean archivedDbEnv;
	private boolean archivedEnv;
	private String highValueEncoding;
    
    /** Converter to use when dealing with data */
    private Converter converter;
    
	/**
	 * This is the character, which should be used to construct 
	 * keys for SELECT statements, which in the database associated 
	 * with this Application Environment will return rows with 
	 * corresponding columns containing high-value characters.<br>
	 * Note that this character is suitable only for inquiry purposes;
	 * in order to produce a high-value in a database table on 
	 * update/insert, this character would have to be subjected 
	 * to further transformation using applicable encoding.   
	 */
	private char highValueChar;
    
	private ServiceManager serviceManager;  
	

    public static ApplicationEnvironmentBean getEnvironment() {
        return environment.get();
    }
    
    public static void setEnvironment(ApplicationEnvironmentBean env) {
        environment.set(env);
    }
    
	/**
	 * Returns the DisplayName.
	 * 
	 * @return the DisplayName value.
	 * @see #setDisplayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * Sets the DisplayName.
	 * 
	 * @param displayName the new DisplayName value.
	 * @see #getDisplayName
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * Returns the EnvDbInfo.
	 * 
	 * @return the EnvDbInfo value.
	 * @see #setEnvDbInfo
	 */
	@SuppressWarnings("unchecked")
	public Map getEnvDbInfo() {
		return envDbInfo;
	}

	/**
	 * Sets the EnvDbInfo.
	 * 
	 * @param envDbInfo the new EnvDbInfo value.
	 * @see #getEnvDbInfo
	 */
	@SuppressWarnings("unchecked")
	public void setEnvDbInfo(Map envDbInfo) {
		this.envDbInfo = envDbInfo;
	}

	/**
	 * Returns the JndiDataSource.
	 * 
	 * @return the JndiDataSource value.
	 * @see #setJndiDataSource
	 */
	public DataSource getJndiDataSource() {
		return jndiDataSource;
	}

	/**
	 * Sets the JndiDataSource.
	 * 
	 * @param jndiDataSource the new JndiDataSource value.
	 * @see #getJndiDataSource
	 */
	public void setJndiDataSource(DataSource jndiDataSource) {
		this.jndiDataSource = jndiDataSource;

		if (this.jndiDataSource instanceof BasicDataSource) {
			BasicDataSource basicDS = (BasicDataSource) this.jndiDataSource;
			String validationQuery = basicDS.getValidationQuery();

			if (StringUtils.hasText(validationQuery)) {
				EnvDbInfoBean dbInfo = (EnvDbInfoBean) envDbInfo.get("data");
				if (dbInfo != null && dbInfo.getSchema() != null) {
					validationQuery = validationQuery.replaceAll("\\{schema\\}", dbInfo.getSchema());
					basicDS.setValidationQuery(validationQuery);
					basicDS.setTestOnBorrow(true);
				}
			}
		}
	}

	/**
	 * Returns the Name.
	 * 
	 * @return the Name value.
	 * @see #setName
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the Name.
	 * 
	 * @param name the new Name value.
	 * @see #getName
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the ConnectionInfo.
	 * 
	 * @return the ConnectionInfo value.
	 * @see #setConnectionInfo
	 */
	public IEnvConnection getConnectionInfo() {
		return connectionInfo;
	}

	/**
	 * Sets the ConnectionInfo.
	 * 
	 * @param connectionInfo the new ConnectionInfo value.
	 * @see #getConnectionInfo
	 */
	public void setConnectionInfo(IEnvConnection connectionInfo) {
		this.connectionInfo = connectionInfo;
	}

	/**
	 * Returns the DataBaseId.
	 * 
	 * @return the DataBaseId value.
	 * @see #setDataBaseId
	 */
	public String getDataBaseId() {
		return dataBaseId;
	}

	/**
	 * Sets the DataBaseId.
	 * 
	 * @param dataBaseId the new DataBaseId value.
	 * @see #getDataBaseId
	 */
	public void setDataBaseId(String dataBaseId) {
		this.dataBaseId = dataBaseId;
	}

	/**
	 * Returns the DebugAllowed.
	 * 
	 * @return the DebugAllowed value.
	 * @see #setDebugAllowed
	 */
	public boolean getDebugAllowed() {
		return debugAllowed;
	}

	/**
	 * Sets the DebugAllowed.
	 * 
	 * @param debugAllowed the new DebugAllowed value.
	 * @see #getDebugAllowed
	 */
	public void setDebugAllowed(boolean debugAllowed) {
		this.debugAllowed = debugAllowed;
	}

	/**
	 * Returns the DefaultLogSwitch.
	 * 
	 * @return the DefaultLogSwitch value.
	 * @see #setDefaultLogSwitch
	 */
	public String getDefaultLogSwitch() {
		return defaultLogSwitch;
	}
	
	/**
	 * Sets the DefaultLogSwitch.
	 * 
	 * @param defaultLogSwitch the new DefaultLogSwitch value.
	 * @see #getDefaultLogSwitch
	 */
	public void setDefaultLogSwitch(String defaultLogSwitch) {
		this.defaultLogSwitch = defaultLogSwitch;
	}
	
	/**
	 * Returns the AvmEnvironmentName.
	 * 
	 * @return the AvmEnvironmentName value.
	 * @see #setAvmEnvironmentName
	 **/
	public String getAvmEnvironmentName() {
		return avmEnvironmentName;
	}

	/**
	 * Sets the AvmEnvironmentName.
	 * 
	 * @param avmEnvironmentName the new AvmEnvironmentName value.
	 * @see #getAvmEnvironmentName
	 **/
	public void setAvmEnvironmentName(String avmEnvironmentName) {
		this.avmEnvironmentName = avmEnvironmentName;
	}
    
    /**
	 * Returns the Converter.
	 * 
	 * @return the Converter value.
	 * @see #setConverter
	 **/
    public Converter getConverter() {
        return converter;
    }

    /**
	 * Sets the Converter.
	 * 
	 * @param converter the new Converter value.
	 * @see #getConverter
	 **/
    public void setConverter(Converter converter) {
        this.converter = converter;
    }

	/**
	 * Return current value of the property <em>highValueChar</em>.
	 * 
	 * @return Current value of the property <em>highValueChar</em>.
     * @see #setHighValueChar
	 */
	public char getHighValueChar()
	{
		return highValueChar;
	}

	/**
	 * Set new value of the property <em>highValueChar</em>.
	 * 
	 * @param highValueChar
	 *        New value of the property <em>highValueChar</em>.
     * @see #getHighValueChar
	 */
	public void setHighValueChar(char highValueChar)
	{
		this.highValueChar = highValueChar;
	}
    
	/**
	 * Returns the ServiceManager.
	 * 
	 * @return the ServiceManager value.
	 * @see #setServiceManager
	 */
	public ServiceManager getServiceManager() {
		return serviceManager;
	}

	/**
	 * Sets the ServiceManager.
	 * 
	 * @param serviceManager the new ServiceManager value.
	 * @see #getServiceManager
	 */
	public void setServiceManager(ServiceManager serviceManager) {
		this.serviceManager = serviceManager;
	}
   
	/**
	 * Get the default DB of the specified type in this environment.
	 * 
	 * @param type the DB type to get the default for.
	 * @return the default DB of the specified type in this environment.
	 */
	@SuppressWarnings("unchecked")
	public EnvDbInfoBean getDefaultEnvDbInfo(String type)
    {
        EnvDbInfoBean defaultDbInfo = null;
        Iterator itt = envDbInfo.keySet().iterator();
        while (itt.hasNext()) {
            EnvDbInfoBean dbInfo = (EnvDbInfoBean)envDbInfo.get(itt.next());
            if (dbInfo.getTableType().equalsIgnoreCase(type) &&
                    dbInfo.isDefaultDb()) {
                defaultDbInfo = dbInfo;
                break;
            }
        }
        
        return defaultDbInfo;
    }

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();

		buf.append("displayName: " + displayName);

		return buf.toString();
	}
	
	/**
	 * Get the specified DB by name and type.
	 * 
	 * @param name The DB name. Internal name, not display name.
	 * @param type The DB type.
	 */
	@SuppressWarnings("unchecked")
	public EnvDbInfoBean getDbByName(String name, String type) {
		EnvDbInfoBean dbInfo = null;
		Iterator itt = envDbInfo.keySet().iterator();
		while (itt.hasNext()) {
			EnvDbInfoBean nextDbInfo = (EnvDbInfoBean) envDbInfo.get(itt.next());
			if (nextDbInfo.getTableType().equalsIgnoreCase(type)
					&& nextDbInfo.getName().equalsIgnoreCase(name)) {
				dbInfo = nextDbInfo;
				break;
			}
		}

		return dbInfo;
	}

	/**
	 * @return the archivedDbEnvName
	 */
	public ApplicationEnvironmentBean getArchivedDbEnv() {
		return archivedDbEnv;
	}

	/**
	 * @param archivedDbEnv the archivedDbEnv to set
	 */
	public void setArchivedDbEnv(ApplicationEnvironmentBean archivedDbEnv) {
		this.archivedDbEnv = archivedDbEnv;
	}

	/**
	 * @return the archivedEnv
	 */
	public boolean isArchivedEnv() {
		return archivedEnv;
	}

	/**
	 * @param archivedEnv the archivedEnv to set
	 */
	public void setArchivedEnv(boolean archivedEnv) {
		this.archivedEnv = archivedEnv;
	}

	/**
	 * @return high-value encoding
	 */
	public String getHighValueEncoding() {
		return highValueEncoding;
	}
	
	/**
	   Sets the high value encoding-used to convert high-values.
	   @param highValueEncoding
	   @see #getHighValueEncoding
	**/
	public void setHighValueEncoding(String highValueEncoding) {
		this.highValueEncoding = highValueEncoding;
	}	
}