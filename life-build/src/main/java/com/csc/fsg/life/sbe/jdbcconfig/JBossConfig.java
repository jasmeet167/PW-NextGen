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

public class JBossConfig extends JDBCConfig {
	private JDBCConfigTask task;
	private File configFile;

	public JBossConfig(JDBCConfigTask task) {
		this.task = task;
		// Initialize need directory and file references
		configFile = new File(task.getDir().getAbsolutePath() + File.separator + "EarContent" + File.separator + "jboss-ds.xml");
		if (!configFile.getParentFile().exists()) {
			configFile = new File(task.getDir().getAbsolutePath() + File.separator + "src" + File.separator + "main" + File.separator + "application"
					+ File.separator + "jboss-ds.xml");
		}
	}

	public void build() {
		if (configFile.getParentFile().exists()) {
			if (task.getDatasources().size() > 0) {
				try {
					// Initialize conf file for JBoss data sources
					DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
					DocumentBuilder builder = factory.newDocumentBuilder();
					DOMImplementation impl = builder.getDOMImplementation();
					Document doc = impl.createDocument(null, "datasources", null);
					Element docElement = doc.getDocumentElement();

					// Data sources
					for (Iterator<DataSource> itt = task.getDatasources().iterator(); itt.hasNext();) {
						DataSource ds = itt.next();
						Element datasourceElement = createElement(doc, docElement, "local-tx-datasource");
						createTextElement(doc, datasourceElement, "jndi-name", ds.getName());
						createTextElement(doc, datasourceElement, "connection-url", "jdbc:db2://" + ds.getServername() + ":" + ds.getPortnumber() + "/"
								+ ds.getDatabasename());
						createTextElement(doc, datasourceElement, "driver-class", "com.ibm.db2.jcc.DB2Driver");
						createTextElement(doc, datasourceElement, "user-name", ds.getUserid());
						createTextElement(doc, datasourceElement, "password", ds.getPassword());
						createTextElement(doc, datasourceElement, "min-pool-size", "5");
						createTextElement(doc, datasourceElement, "max-pool-size", "20");

						Element metadataElement = createElement(doc, datasourceElement, "metadata");
						createTextElement(doc, metadataElement, "type-mapping", "DB2");
					}

					writeDoc(doc, configFile);
				} catch (Exception e) {
					throw new BuildException("Error creating JBoss jdbc config", e);
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
