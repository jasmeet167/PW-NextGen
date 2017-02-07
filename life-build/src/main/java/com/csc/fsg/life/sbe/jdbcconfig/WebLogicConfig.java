package com.csc.fsg.life.sbe.jdbcconfig;

import java.io.File;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.tools.ant.BuildException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.csc.fsg.life.sbe.common.util.SBEAntUtil;
import com.csc.fsg.life.sbe.taskdefs.JDBCConfigTask;

public class WebLogicConfig extends JDBCConfig {
	private JDBCConfigTask task;
	private File configFileDir;

	public WebLogicConfig(JDBCConfigTask task) {
		this.task = task;
		// Initialize need directory and file references
		configFileDir = new File(task.getDir().getAbsolutePath() + File.separator + "EarContent" + File.separator + "weblogic-ds");
		if (!configFileDir.getParentFile().exists()) {
			configFileDir = new File(task.getDir().getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "application"
					+ File.separator + "weblogic-ds");
		}
	}

	public void build() {
		File configFile = null;
		if (configFileDir.getParentFile().exists()) {
			if (task.getDatasources().size() > 0) {
				if (!configFileDir.exists()) {
					// Create dir for config files if not found
					configFileDir.mkdir();
				}

				Document doc = null;
				try {
					for (Iterator<DataSource> itt = task.getDatasources().iterator(); itt.hasNext();) {
						DataSource ds = itt.next();
						// Initialize conf file for WebLogic data source
						configFile = new File(configFileDir.getAbsolutePath() + File.separator + ds.getName() + "-jdbc.xml");
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						DOMImplementation impl = builder.getDOMImplementation();

						doc = impl.createDocument(null, "jdbc-data-source", null);
						Element docElement = doc.getDocumentElement();
						docElement.setAttribute("xmlns", "http://www.bea.com/ns/weblogic/jdbc-data-source");
						docElement.setAttribute("xmlns:sec", "http://www.bea.com/ns/weblogic/90/security");
						docElement.setAttribute("xmlns:wls", "http://www.bea.com/ns/weblogic/90/security/wls");
						docElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
						docElement.setAttribute("xsi:schemaLocation",
								"http://www.bea.com/ns/weblogic/jdbc-data-source http://www.bea.com/ns/weblogic/jdbc-data-source/1.0/jdbc-data-source.xsd");

						createTextElement(doc, docElement, "name", ds.getName());
						// jdbc driver params
						Element jdbcdriverparamsElement = createElement(doc, docElement, "jdbc-driver-params");
						createTextElement(doc, jdbcdriverparamsElement, "url", "jdbc:db2://" + ds.getServername() + ":" + ds.getPortnumber() + "/"
								+ ds.getDatabasename());
						createTextElement(doc, jdbcdriverparamsElement, "driver-name", "com.ibm.db2.jcc.DB2Driver");
						Element propertiesElement = createElement(doc, jdbcdriverparamsElement, "properties");
						createPropertyElement(doc, propertiesElement, "user", ds.getUserid());
						createPropertyElement(doc, propertiesElement, "password", ds.getPassword());

						// jdbc connection pool params
						Element jdbcconnectionpoolparamsElement = doc.createElement("jdbc-connection-pool-params");
						createTextElement(doc, jdbcconnectionpoolparamsElement, "test-connections-on-reserve", "true");
						createTextElement(doc, jdbcconnectionpoolparamsElement, "test-table-name", "SQL SELECT COUNT(*) FROM SYSIBM.SYSTABLES");
						docElement.appendChild(jdbcconnectionpoolparamsElement);

						// jdbc data source params
						Element jdbcdatasoruceparamsElement = doc.createElement("jdbc-data-source-params");
						createTextElement(doc, jdbcdatasoruceparamsElement, "jndi-name", ds.getJndiname());
						createTextElement(doc, jdbcdatasoruceparamsElement, "scope", "Application");
						docElement.appendChild(jdbcdatasoruceparamsElement);

						writeDoc(doc, configFile);
					}
				} catch (Exception e) {
					throw new BuildException("Error creating WebLogic jdbc config", e);
				}
			} else {
				clean();
			}
		}
	}

	private void createPropertyElement(Document doc, Element parentElement, String name, String value) {
		Element propertyElement = doc.createElement("property");
		createTextElement(doc, propertyElement, "name", name);
		createTextElement(doc, propertyElement, "value", value);
		parentElement.appendChild(propertyElement);
	}

	public void clean() {
		SBEAntUtil.deleteDir(task, configFileDir, true, false);
	}
}
