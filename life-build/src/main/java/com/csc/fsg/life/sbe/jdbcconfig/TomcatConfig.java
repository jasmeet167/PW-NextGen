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

/* Modifications: T0196 */

public class TomcatConfig extends JDBCConfig {
	private JDBCConfigTask task;
	private File configFile;

	public TomcatConfig(JDBCConfigTask task) {
		this.task = task;
		// Initialize need directory and file references
		configFile = new File(task.getDir().getAbsolutePath() + File.separator + "WebContent" + File.separator + "META-INF" + File.separator + "context.xml");
		if (!configFile.getParentFile().exists()) {
			configFile = new File(task.getDir().getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "webapp"
					+ File.separator + "META-INF" + File.separator + "context.xml");
		}
	}

	public void build() {
		if (configFile.getParentFile().exists()) {
			if (task.getDatasources().size() > 0) {
				try {
					// Initialize conf file for Tomcat data sources
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					DOMImplementation impl = builder.getDOMImplementation();
					Document doc = impl.createDocument(null, "Context", null);
					Element docElement = doc.getDocumentElement();

					// Resources
					for (Iterator<DataSource> itt = task.getDatasources().iterator(); itt.hasNext();) {
						DataSource ds = itt.next();
						Element resourceElement = createElement(doc, docElement, "Resource");
						resourceElement.setAttribute("name", ds.getJndiname());
						resourceElement.setAttribute("url", "jdbc:db2://" + ds.getServername() + ":" + ds.getPortnumber() + "/" + ds.getDatabasename());
						resourceElement.setAttribute("username", ds.getUserid());
						resourceElement.setAttribute("password", ds.getPassword());
						resourceElement.setAttribute("driverClassName", "com.ibm.db2.jcc.DB2Driver");
						resourceElement.setAttribute("validationQuery", "SELECT * FROM SYSIBM.SYSDUMMY1");
						resourceElement.setAttribute("type", "javax.sql.DataSource");
						resourceElement.setAttribute("auth", "Container");
						resourceElement.setAttribute("maxTotal", "100");
						resourceElement.setAttribute("maxIdle", "30");
						resourceElement.setAttribute("maxWaitMillis", "10000");
					}
					writeDoc(doc, configFile, false);
				} catch (Exception e) {
					throw new BuildException("Error creating Tomcat jdbc config", e);
				}
			} else {
				// Clean if no data sources
				clean();
			}
		}
	}

	public void clean() {
		SBEAntUtil.deleteFile(task, configFile, true, false);
	}
}
