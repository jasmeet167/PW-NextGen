package com.csc.fsg.life.avm.environments;

import javax.sql.DataSource;

/* Modifications: T0140, T0143, T0175 */

/** 
	This class holds the application and environment key for avm.
 */
public class AVMConfigBean 
{
	private boolean isEnabled = true;
	private String applicationKey;
	private String environmentKey;
	private String applicationId;
	private String environmentId;
	private String status;
	private String delay;
	private String period;
	private String avmServerlink;
	private DataSource dataSource;
	private String accessCacheStatus;
	private String schema;
	private String secPoliciesUri;
	private String importExportLocation;
	private boolean isPageDataUtilized = false;

	/**
	 * @return Current value of the property <code>enabled</code>.
	 */
	public boolean isEnabled()
	{
		return isEnabled;
	}

	/**
	 * Set new value of the property <code>enabled</code>.
	 * 
	 * @param isEnabled
	 *        New value of the property <code>enabled</code>.
	 */
	public void setEnabled(boolean isEnabled)
	{
		this.isEnabled = isEnabled;
	}

	/**
	   Return the application key.
	   @return The application as a key.
	   @see #setApplicationKey
	 */
	public String getApplicationKey() {
		return applicationKey;
	}

	/**
	   Return the environment as a key.
	   @return The environment as a key.
	   @see #setEnvironmentKey
	 */
	public String getEnvironmentKey() {
		return environmentKey;
	}

	/**
	   Sets the application key.
	   @param string The application key
	   @see #getApplicationKey
	 */
	public void setApplicationKey(String string) {
		applicationKey = string;
	}

	/**
	   Sets the environment key.
	    @param string The environment key.
		@see #getEnvironmentKey
	 */
	public void setEnvironmentKey(String string) {
		environmentKey = string;
	}

	/**
	   Access time to delay (in milliseconds) before first cache check.
	   @return The delay for the cache process.
	   @see #setDelay
	 */
	public String getDelay() {
		return delay;
	}

	/**
	   Returns the access interval (in milliseconds) to trigger Cache process.
	   @return the access interval (in milliseconds) to trigger Cache process.
	   @see #setPeriod
	**/
	public String getPeriod() {
		return period;
	}

	/**
	   Returns the AVM cache status.  ON for on or OFF for off.
	   @return the current AVM cache status.
	   @see #setStatus
	 */
	public String getStatus() {
		return status;
	}

	/**
	   Sets the access time to delay (in milliseconds) before first cache check.
	   @param string The delay for the cache process.
	   @see #getDelay
	 */
	public void setDelay(String string) {
		delay = string;
	}

	/**
	   Sets the access interval (in milliseconds) to trigger Cache process.
	   @param string the access interval (in milliseconds) to trigger Cache process.
	   @see #getPeriod
	 */
	public void setPeriod(String string) {
		period = string;
	}

	/**
	   The AVM cache status.  Set to ON for on or OFF for off.
	   @param string the new AVM cache status.
	   @see #getStatus
	 */
	public void setStatus(String string) {
		status = string;
	}

	/**
	   Returns the link to the AVM UI.
	   @return The link to the AVM UI.
	   @see #setAvmServerlink
	 */
	public String getAvmServerlink() {
		return avmServerlink;
	}

	/**
	   Sets the link to the AVM UI.
	   @param string The link to the AVM UI.
	   @see #getAvmServerlink
	 */
	public void setAvmServerlink(String string) {
		avmServerlink = string;
	}

	/**
	   Returns the applicationId.
	   @return Returns the applicationId.
	   @see #setApplicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	   The application ID to set.
	   @param applicationId The applicationId to set.
	   @see #getApplicationId
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	   Returns the environmentId.
	   @return Returns the environmentId.
	   @see #setEnvironmentId
	 */
	public String getEnvironmentId() {
		return environmentId;
	}

	/**
	   Sets the environment Id.
	   @param environmentId The environmentId to set.
	   @see #getEnvironmentId
	*/
	public void setEnvironmentId(String environmentId) {
		this.environmentId = environmentId;
	}

	/**
	   Get the data source used to access the AVM database.
	   @return the data source used to access the AVM database.
	   @see #setDataSource
	**/
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 **/
	public String getAccessCacheStatus() {
		return accessCacheStatus;
	}

	/**
	 **/
	public void setAccessCacheStatus(String accessCacheStatus) {
		this.accessCacheStatus = accessCacheStatus;
	}

	/**
	   Returns the scheme used in the AVM database.
	   @return the scheme used in the AVM database.
	   @see #setSchema
	**/
	public String getSchema() {
		return schema;
	}

	/**
	   Sets the scheme used in the AVM database.
	   @param schema the scheme used in the AVM database.
	   @see #getSchema
	**/
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	   Sets the data source used to access the AVM database.
	   @param dataSource the data source used to access the AVM database.
	   @see #getDataSource
	**/
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 **/
	public String getSecPoliciesUri() {
		return secPoliciesUri;
	}

	/**
	 **/
	public void setSecPoliciesUri(String secPoliciesUri) {
		this.secPoliciesUri = secPoliciesUri;
	}

	/**
	   Returns the location on the server to import/export AVM files.
	   @return The location on the server to import/export AVM files.
	   @see #setImportExportLocation
	**/
	public String getImportExportLocation() {
		return importExportLocation;
	}

	/**
	   Sets the location on the server to import/export AVM files.
	   @param importExportLocation The location on the server to import/export AVM files.
	   @see #getImportExportLocation
	**/
	public void setImportExportLocation(String importExportLocation) {
		this.importExportLocation = importExportLocation;
	}

	/**
	 * @return Current value of the property <code>pageDataUtilized</code>.
	 */
	public boolean isPageDataUtilized()
	{
		return isPageDataUtilized;
	}

	/**
	 * Set new value of the property <code>pageDataUtilized</code>.
	 * 
	 * @param isPageDataUtilized
	 *        New value of the property <code>pageDataUtilized</code>.
	 */
	public void setPageDataUtilized(boolean isPageDataUtilized)
	{
		this.isPageDataUtilized = isPageDataUtilized;
	}
}
