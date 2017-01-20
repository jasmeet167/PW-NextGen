
package com.csc.fsg.life.pw.io;

import com.csc.fsg.life.pw.environment.EnvironmentManager;

public class RowManager {

	private String envId = null;

	public RowManager(String envId) {
		this.envId = envId;
	}

	public Row getBlankRow(String tableId) throws Exception {

		TableDescriptor td = EnvironmentManager.getInstance()
						.getEnvironment(envId).getTableDescMgr()
						.getTableDescriptor(tableId);

		if (td == null) {
			throw new Exception("Table Descriptor for table id " + tableId
			        + " is not found ");
		}

		Row row = td.getNewRowObject();
		row.setEnvId(envId);
		row.setTableDescriptor(td);
		return row;
	}
}
