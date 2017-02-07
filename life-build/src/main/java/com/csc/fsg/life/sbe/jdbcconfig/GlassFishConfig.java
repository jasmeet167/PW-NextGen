package com.csc.fsg.life.sbe.jdbcconfig;

import java.io.File;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.tools.ant.BuildException;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import com.csc.fsg.life.sbe.common.util.SBEAntUtil;
import com.csc.fsg.life.sbe.taskdefs.JDBCConfigTask;

public class GlassFishConfig extends JDBCConfig {
	private JDBCConfigTask task;
	private File configFile;

	public GlassFishConfig(JDBCConfigTask task) {
		this.task = task;
		// Initialize need directory and file references
		configFile = new File(task.getDir().getAbsolutePath() + File.separator + "WebContent" + File.separator + "WEB-INF" + File.separator
				+ "sun-resources.xml");
		if (!configFile.getParentFile().exists()) {
			configFile = new File(task.getDir().getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp"
					+ File.separator + "WEB-INF" + File.separator + "sun-resources.xml");
		}
	}

	public void build() {
		if (configFile.getParentFile().exists()) {
			if (task.getDatasources().size() > 0) {
				try {
					// Initialize conf file for GlassFish data sources
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					DOMImplementation impl = builder.getDOMImplementation();

					DocumentType docType = impl.createDocumentType("resources",
							"-//Sun Microsystems, Inc.//DTD Application Server 9.0 Resource Definitions //EN",
							"http://www.sun.com/software/appserver/dtds/sun-resources_1_3.dtd");
					Document doc = impl.createDocument(null, "resources", docType);
					Element docElement = doc.getDocumentElement();

					// Data sources
					for (Iterator<DataSource> itt = task.getDatasources().iterator(); itt.hasNext();) {
						DataSource ds = itt.next();
						Element connectionPoolElement = createElement(doc, docElement, "jdbc-connection-pool");
						connectionPoolElement.setAttribute("name", ds.getName() + "_pool");
						connectionPoolElement.setAttribute("res-type", "javax.sql.ConnectionPoolDataSource");
						connectionPoolElement.setAttribute("datasource-classname", "com.ibm.db2.jcc.DB2ConnectionPoolDataSource");

						createPropertyElement(doc, connectionPoolElement, "portNumber", ds.getPortnumber());
						createPropertyElement(doc, connectionPoolElement, "databaseName", ds.getDatabasename());
						createPropertyElement(doc, connectionPoolElement, "networkProtocol", "db2");
						createPropertyElement(doc, connectionPoolElement, "serverName", ds.getServername());
						createPropertyElement(doc, connectionPoolElement, "user", ds.getUserid());
						createPropertyElement(doc, connectionPoolElement, "password", ds.getPassword());
						createPropertyElement(doc, connectionPoolElement, "driverType", "4");

						Element jdbcResourceElement = createElement(doc, docElement, "jdbc-resource");
						jdbcResourceElement.setAttribute("jndi-name", ds.getJndiname());
						jdbcResourceElement.setAttribute("pool-name", ds.getName() + "_pool");
					}
					writeDoc(doc, configFile);
				} catch (Exception e) {
					throw new BuildException("Error creating GlassFish jdbc config", e);
				}
			} else {
				clean();
			}
		}
	}

	private void createPropertyElement(Document doc, Element parentElement, String name, String value) {
		Element propertyElement = createElement(doc, parentElement, "property");
		propertyElement.setAttribute("name", name);
		propertyElement.setAttribute("value", value);
	}

	public void clean() {
		SBEAntUtil.deleteFile(task, configFile, true, false);
	}
}
