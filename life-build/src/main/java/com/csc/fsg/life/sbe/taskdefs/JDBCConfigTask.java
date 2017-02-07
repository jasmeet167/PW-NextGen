package com.csc.fsg.life.sbe.taskdefs;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import com.csc.fsg.life.sbe.jdbcconfig.DataSource;
import com.csc.fsg.life.sbe.jdbcconfig.GlassFishConfig;
import com.csc.fsg.life.sbe.jdbcconfig.JBossConfig;
import com.csc.fsg.life.sbe.jdbcconfig.TomcatConfig;
import com.csc.fsg.life.sbe.jdbcconfig.WebLogicConfig;
import com.csc.fsg.life.sbe.jdbcconfig.WebSphereConfig;

public class JDBCConfigTask extends Task {
	public enum Action {
		build, clean
	};

	private Action action = Action.build;
	private File dir;
	private List<DataSource> datasources = new ArrayList<DataSource>();

	@Override
	public void execute() throws BuildException {
		super.execute();

		TomcatConfig tomcatConfig = new TomcatConfig(this);
		JBossConfig jbossConfig = new JBossConfig(this);
		GlassFishConfig glassFishConfig = new GlassFishConfig(this);
		WebLogicConfig webLogicConfig = new WebLogicConfig(this);
		WebSphereConfig websphereConfig = new WebSphereConfig(this);

		if (action == Action.build) {
			tomcatConfig.build();
			jbossConfig.build();
			glassFishConfig.build();
			webLogicConfig.build();
			websphereConfig.build();
		} else {
			tomcatConfig.clean();
			jbossConfig.clean();
			glassFishConfig.clean();
			webLogicConfig.clean();
			websphereConfig.clean();
		}
	}

	public void printConfig() {
		System.out.println("dir: " + dir);

		// print out datasource information
		System.out.println("Datasoruces:");
		for (Iterator<DataSource> itt = datasources.iterator(); itt.hasNext();) {
			System.out.print(itt.next());
		}
	}

	@Override
	public void log(String msg) {
		super.log(msg, Project.MSG_WARN);
	}

	public List<DataSource> getDatasources() {
		return datasources;
	}

	public void addDatasource(DataSource ds) {
		datasources.add(ds);
	}

	public void addPaul(DataSource ds) {
		datasources.add(ds);
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public File getDir() {
		return dir;
	}

	public void setDir(File dir) {
		this.dir = dir;
	}
}
