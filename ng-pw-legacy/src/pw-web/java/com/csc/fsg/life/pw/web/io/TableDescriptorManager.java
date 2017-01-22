/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.io;

import java.util.Hashtable;

import com.csc.fsg.life.pw.common.util.Constants;


/* Modifications: T0091, CCCV-E501 */

public class TableDescriptorManager extends WizObject {

	public static TableDescriptorManager wmaInstance;
	public static TableDescriptorManager v1Instance;
	protected Hashtable<String, TableDescriptor> tableDescriptors;
	protected boolean isAnyV1ProdInstalled = false;
	
	// CCCV-E501 - removed reference to V1 table descriptor manager
	public static synchronized TableDescriptorManager getInstance(String productSystem) {
		if (wmaInstance==null){
			wmaInstance = new WMATableDescriptorManager();
		}
		return wmaInstance;
	}

	public static String getTableId(String ddlName) {
		return ddlName.substring(ddlName.length() - 4, ddlName.length() - 1);
	}

	public void addTableDescriptor(TableDescriptor tableDescriptor) {
		getTableDescriptors().put(tableDescriptor.getTableId(), tableDescriptor);
	}

	public String getDDLName(String tableId) {
		TableDescriptor descriptor = getTableDescriptor(tableId);
		if (descriptor == null) {
			// Tabledescriptor not loaded-Check install properties
			return null;
		} else {
			return descriptor.getDDLName();
		}
	}

	public TableDescriptor getTableDescriptor(String tableId) {
		return getTableDescriptors().get(tableId);
	}

	public Hashtable<String, TableDescriptor> getTableDescriptors() {
		return tableDescriptors;
	}

	public void setTableDescriptors(Hashtable<String, TableDescriptor> newTableDescriptors) {
		tableDescriptors = newTableDescriptors;
	}
	
}
