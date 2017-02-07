/*
 * Created on Jan 24, 2005
 * 
 * TODO To change the template for this generated file go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
package com.csc.fsg.life.sbe.common.util;

import java.io.File;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.taskdefs.Ant;
import org.apache.tools.ant.taskdefs.Delete;
import org.apache.tools.ant.taskdefs.Mkdir;
import org.apache.tools.ant.taskdefs.Move;
import org.apache.tools.ant.util.FileUtils;

public class SBEAntUtil {
	public static final FileUtils fileUtils = FileUtils.newFileUtils();
	public final static String sep = System.getProperty("file.separator");

	static public void ant(Task task, File antFile, File dir, String targets) {
		Ant ant = new Ant();
		if (task != null) {
			ant.bindToOwner(task);
		} else {
			ant.setProject(new Project());
			ant.setTaskName("Ant");
		}

		ant.init();
		ant.setDir(dir);
		ant.setAntfile(antFile.getAbsolutePath());
		ant.setTarget(targets);
		ant.addPropertyset(null);
		ant.execute();
	}

	static public void deleteDir(Task task, File dir) {
		deleteDir(task, dir, false, true);
	}

	static public void deleteDir(Task task, File dir, boolean quiet, boolean failonerror) {
		delete(task, dir, null, quiet, failonerror);
	}

	static public void deleteFile(Task task, File file, boolean quiet, boolean failonerror) {
		delete(task, null, file, quiet, failonerror);
	}

	static private void delete(Task task, File dir, File file, boolean quiet, boolean failonerror) {
		Delete delete = new Delete();
		if (task != null) {
			delete.setProject(task.getProject());
			delete.setTaskName(task.getTaskName());
		} else {
			delete.setProject(new Project());
			delete.setTaskName("Delete");
		}
		delete.init();
		delete.setQuiet(quiet);
		if (dir != null)
			delete.setDir(dir);
		if (file != null)
			delete.setFile(file);
		delete.setFailOnError(failonerror);
		delete.execute();
	}

	static public void mkDir(Task task, File dir) {
		Mkdir mkdir = new Mkdir();
		if (task != null) {
			mkdir.setProject(task.getProject());
			mkdir.setTaskName(task.getTaskName());
		} else {
			mkdir.setProject(new Project());
			mkdir.setTaskName("Delete");
		}
		mkdir.init();

		mkdir.setDir(dir);
		mkdir.execute();
	}

	static public void mkFileDir(Task task, File file) {
		mkDir(task, file.getParentFile());
	}

	static public void moveDir(Task task, File dir, File toDir) {
		moveDir(task, dir, toDir, true);
	}

	static public void moveDir(Task task, File dir, File toDir, boolean failonerror) {

		Move move = new Move();
		if (task != null) {
			move.setProject(task.getProject());
			move.setTaskName(task.getTaskName());
		} else {
			move.setProject(new Project());
			move.setTaskName("Move");
		}
		move.init();
		move.setFile(dir);
		move.setTodir(toDir);
		move.setPreserveLastModified(true);
		move.setFailOnError(failonerror);
		move.execute();
	}
}