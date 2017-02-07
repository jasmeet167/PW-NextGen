package com.csc.fsg.life.sbe.jdbcconfig;

import java.io.File;
import java.util.Iterator;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.tools.ant.BuildException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.sbe.common.util.SBEAntUtil;
import com.csc.fsg.life.sbe.taskdefs.JDBCConfigTask;

public class WebSphereConfig extends JDBCConfig {
	private JDBCConfigTask task;
	private File ibmconfigDir;
	private File securityFile;
	private File deploymentFile;
	private File resourcesFile;
	private File variablesFile;
	private TreeMap<String, String> authDataEntries = new TreeMap<String, String>();;

	public WebSphereConfig(JDBCConfigTask task) {
		this.task = task;
		// Initialize need directory and file references
		ibmconfigDir = new File(task.getDir().getAbsolutePath() + File.separator + "EarContent" + File.separator + "META-INF" + File.separator + "ibmconfig");
		if (!ibmconfigDir.getParentFile().exists()) {
			ibmconfigDir = new File(task.getDir().getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "application"
					+ File.separator + "META-INF" + File.separator + "ibmconfig");
		}
		File defaultCellDir = new File(ibmconfigDir.getAbsolutePath() + File.separator + "cells" + File.separator + "defaultCell");
		securityFile = new File(defaultCellDir.getAbsolutePath() + File.separator + "security.xml");
		File defaultAppDir = new File(defaultCellDir.getAbsolutePath() + File.separator + "applications" + File.separator + "defaultApp" + File.separator
				+ "deployments" + File.separator + "defaultApp");
		deploymentFile = new File(defaultAppDir.getAbsolutePath() + File.separator + "deployment.xml");
		resourcesFile = new File(defaultAppDir.getAbsolutePath() + File.separator + "resources.xml");
		variablesFile = new File(defaultAppDir.getAbsolutePath() + File.separator + "variables.xml");
	}

	public void build() {
		if (ibmconfigDir.getParentFile().exists()) {
			if (task.getDatasources().size() > 0) {
				try {
					// Initialize resources file for WebSphere data sources
					SBEAntUtil.mkDir(task, resourcesFile.getParentFile());
					// Create new resources WebSphere configuration
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					DOMImplementation impl = builder.getDOMImplementation();

					Document resourcesDoc = impl.createDocument("http://www.omg.org/XMI", "xmi:XMI", null);
					Element resourcesDocElement = resourcesDoc.getDocumentElement();
					resourcesDocElement.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
					resourcesDocElement.setAttribute("xmlns:resources.j2c", "http://www.ibm.com/websphere/appserver/schemas/5.0/resources.j2c.xmi");
					resourcesDocElement.setAttribute("xmlns:resources.jdbc", "http://www.ibm.com/websphere/appserver/schemas/5.0/resources.jdbc.xmi");
					resourcesDocElement.setAttribute("xmi:version", "2.0");

					// Initialize security file for WebSphere data sources
					SBEAntUtil.mkDir(task, securityFile.getParentFile());
					Document securityDoc = null;
					securityDoc = impl.createDocument("http://www.omg.org/XMI", "security:Security", null);
					Element securityDocElement = securityDoc.getDocumentElement();
					securityDocElement.setAttribute("xmlns:xmi", "http://www.omg.org/XMI");
					securityDocElement.setAttribute("xmlns:security", "http://www.ibm.com/websphere/appserver/schemas/5.0/security.xmi");
					securityDocElement.setAttribute("xmi:version", "2.0");

					// J2C Resource Adapter
					Element j2CResourceAdapterElement = createElement(resourcesDoc, resourcesDocElement, "resources.j2c:J2CResourceAdapter");
					j2CResourceAdapterElement.setAttribute("xmi:id", "builtin_rra");
					j2CResourceAdapterElement.setAttribute("name", "WebSphere Relational Resource Adapter");
					j2CResourceAdapterElement.setAttribute("description", "Built-in Relational Resource Adapter for WebSphere Persistence");
					j2CResourceAdapterElement.setAttribute("archivePath", "${WAS_LIBS_DIR}/rsadapter.rar");
					createTextElement(resourcesDoc, j2CResourceAdapterElement, "classpath", "${WAS_LIBS_DIR}/rsadapter.rar");

					// CMP Connection Factory for each data source
					for (Iterator<DataSource> itt = task.getDatasources().iterator(); itt.hasNext();) {
						DataSource ds = itt.next();
						Element factoriesElement = createElement(resourcesDoc, j2CResourceAdapterElement, "factories");
						factoriesElement.setAttribute("xmi:type", "resources.jdbc:CMPConnectorFactory");
						factoriesElement.setAttribute("name", ds.getName() + "_CF");
						factoriesElement.setAttribute("authMechanismPreference", "BASIC_PASSWORD");
						String authDataAlais = ds.getUserid() + "-" + ds.getPassword();
						factoriesElement.setAttribute("authDataAlias", authDataAlais);
						// Add auth entry to security file if does not exist
						if (authDataEntries.get(authDataAlais) == null) {
							Element authDataEntriesElement = createElement(securityDoc, securityDocElement, "authDataEntries");
							authDataEntriesElement.setAttribute("alias", authDataAlais);
							authDataEntriesElement.setAttribute("userId", ds.getUserid());
							authDataEntriesElement.setAttribute("password", ds.getPassword());
							authDataEntries.put(authDataAlais, authDataAlais);
						}
					}

					// Deployment Descriptor
					Element deploymentDescriptorElement = createElement(resourcesDoc, j2CResourceAdapterElement, "deploymentDescriptor");
					deploymentDescriptorElement.setAttribute("displayName", "WS_RdbResourceAdapter");
					deploymentDescriptorElement.setAttribute("vendorName", "IBM");
					deploymentDescriptorElement.setAttribute("specVersion", "1.5");
					deploymentDescriptorElement.setAttribute("eisType", "RRA");
					deploymentDescriptorElement.setAttribute("version", "6.0");

					// Resource Adapter
					Element resourceAdapterElement = createElement(resourcesDoc, deploymentDescriptorElement, "resourceAdapter");
					resourceAdapterElement.setAttribute("transactionSupport", "NoTransaction");
					resourceAdapterElement.setAttribute("reauthenticationSupport", "false");
					resourceAdapterElement.setAttribute("resourceAdapterClass", "com.ibm.ws.rsadapter.spi.WSResourceAdapterImpl");

					// Out Bound Resource Adapter
					Element outboundResourceAdapterElement = createElement(resourcesDoc, resourceAdapterElement, "outboundResourceAdapter");
					outboundResourceAdapterElement.setAttribute("reauthenticationSupport", "false");
					outboundResourceAdapterElement.setAttribute("transactionSupport", "XATransaction");

					// Connection Definitions
					Element connectionDefinitionsElement = createElement(resourcesDoc, outboundResourceAdapterElement, "connectionDefinitions");
					connectionDefinitionsElement.setAttribute("managedConnectionFactoryClass", "com.ibm.ws.rsadapter.spi.WSManagedConnectionFactoryImpl");
					connectionDefinitionsElement.setAttribute("connectionFactoryInterface", "javax.resource.cci.ConnectionFactory");
					connectionDefinitionsElement.setAttribute("connectionFactoryImplClass", "com.ibm.ws.rsadapter.cci.WSRdbConnectionFactoryImpl");
					connectionDefinitionsElement.setAttribute("connectionInterface", "javax.resource.cci.Connection");
					connectionDefinitionsElement.setAttribute("connectionImplClass", "com.ibm.ws.rsadapter.cci.WSRdbConnectionImpl");

					// Config properties
					Element configPropertiesElement = createElement(resourcesDoc, connectionDefinitionsElement, "configProperties");
					configPropertiesElement.setAttribute("name", "ConnectionFactoryType");
					configPropertiesElement.setAttribute("type", "java.lang.Integer");
					configPropertiesElement.setAttribute("value", "2");

					// Authentication Mechanisms
					Element authenticationMechanismsElement = createElement(resourcesDoc, outboundResourceAdapterElement, "authenticationMechanisms");
					authenticationMechanismsElement.setAttribute("authenticationMechanismType", "BasicPassword");
					authenticationMechanismsElement.setAttribute("credentialInterface", "javax.resource.spi.security.PasswordCredential");

					// Connection Def Template Props
					createElement(resourcesDoc, j2CResourceAdapterElement, "connectionDefTemplateProps");

					// JDBC Providers
					Element jdbcProviderElement = createElement(resourcesDoc, resourcesDocElement, "resources.jdbc:JDBCProvider");
					jdbcProviderElement.setAttribute("name", "DB2 Universal JDBC Driver Provider");
					jdbcProviderElement.setAttribute("description", "DB2 Universal JDBC Driver Provider");
					jdbcProviderElement.setAttribute("providerType", "DB2 Universal JDBC Driver Provider");
					jdbcProviderElement.setAttribute("implementationClassName", "com.ibm.db2.jcc.DB2ConnectionPoolDataSource");
					createTextElement(resourcesDoc, jdbcProviderElement, "classpath", "${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc.jar");
					createTextElement(resourcesDoc, jdbcProviderElement, "classpath", "${UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cu.jar");
					createTextElement(resourcesDoc, jdbcProviderElement, "classpath", "${DB2UNIVERSAL_JDBC_DRIVER_PATH}/db2jcc_license_cisuz.jar");
					createTextElement(resourcesDoc, jdbcProviderElement, "nativepath", "${DB2UNIVERSAL_JDBC_DRIVER_NATIVEPATH}");

					// Data source factories
					for (Iterator<DataSource> itt = task.getDatasources().iterator(); itt.hasNext();) {
						DataSource ds = itt.next();
						// Add required data source
						Element factoriesElement = createElement(resourcesDoc, jdbcProviderElement, "factories");
						factoriesElement.setAttribute("xmi:type", "resources.jdbc:DataSource");
						factoriesElement.setAttribute("name", ds.getName());
						factoriesElement.setAttribute("jndiName", ds.getJndiname());
						factoriesElement.setAttribute("description", ds.getDatabasename() + " on " + ds.getServername() + ":" + ds.getPortnumber());
						factoriesElement.setAttribute("authDataAlias", ds.getUserid() + "-" + ds.getPassword());
						factoriesElement.setAttribute("relationalResourceAdapter", "builtin_rra");
						factoriesElement.setAttribute("statementCacheSize", "10");
						factoriesElement.setAttribute("datasourceHelperClassname", "com.ibm.websphere.rsadapter.DB2UniversalDataStoreHelper");
						// Data source property Set
						Element propertySetElement = createElement(resourcesDoc, factoriesElement, "propertySet");
						// Data source properties
						createResourcePropertyElement(resourcesDoc, propertySetElement, "databaseName", "java.lang.String", ds.getDatabasename(), "true");
						createResourcePropertyElement(resourcesDoc, propertySetElement, "driverType", "java.lang.Integer", "4", "true");
						createResourcePropertyElement(resourcesDoc, propertySetElement, "serverName", "java.lang.String", ds.getServername(), "false");
						createResourcePropertyElement(resourcesDoc, propertySetElement, "portNumber", "java.lang.Integer", ds.getPortnumber(), "false");
						// Additional data source properties
						boolean resultSetHoldabilityPropertyFound = false;
						for (Iterator<ResourceProperty> itt1 = ds.getResourceproperties().iterator(); itt1.hasNext();) {
							ResourceProperty rp = itt1.next();
							if (rp.getName().equalsIgnoreCase("resultSetHoldability")) {
								resultSetHoldabilityPropertyFound = true;
							}
							createResourcePropertyElement(resourcesDoc, propertySetElement, rp.getName(), rp.getType(), rp.getValue(), "false");
						}
						// resultSetHoldability default to 2
						if (!resultSetHoldabilityPropertyFound) {
							createResourcePropertyElement(resourcesDoc, propertySetElement, "resultSetHoldability", "java.lang.Integer", "2", "false");
						}
						// Data source connection pool
						Element connectionPoolElement = createElement(resourcesDoc, factoriesElement, "connectionPool");
						connectionPoolElement.setAttribute("connectionTimeout", "180");
						connectionPoolElement.setAttribute("maxConnections", "10");
						connectionPoolElement.setAttribute("minConnections", "1");
						connectionPoolElement.setAttribute("reapTime", "180");
						connectionPoolElement.setAttribute("unusedTimeout", "1800");
						connectionPoolElement.setAttribute("agedTimeout", "0");
						connectionPoolElement.setAttribute("purgePolicy", "EntirePool");
					}
					// Write resources file
					writeDoc(resourcesDoc, resourcesFile);
					// Write security file
					writeDoc(securityDoc, securityFile);
				} catch (Exception e) {
					throw new BuildException("Error creating WebSphere jdbc config", e);
				}
			} else {
				// Clean if no data sources
				clean();
			}
		}
	}

	private void createResourcePropertyElement(Document doc, Element parentElement, String name, String type, String value, String required) {
		Element resourcePropertyElement = doc.createElement("resourceProperties");
		resourcePropertyElement.setAttribute("name", name);
		resourcePropertyElement.setAttribute("type", type);
		resourcePropertyElement.setAttribute("value", value);
		resourcePropertyElement.setAttribute("required", required);
		parentElement.appendChild(resourcePropertyElement);
	}

	public void clean() {
		SBEAntUtil.deleteFile(task, resourcesFile, true, false);
		SBEAntUtil.deleteFile(task, securityFile, true, false);
	}
}
