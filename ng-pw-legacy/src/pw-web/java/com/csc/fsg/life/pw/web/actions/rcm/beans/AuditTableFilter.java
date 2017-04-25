/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.rcm.beans;

import javax.sql.DataSource;

import com.csc.fsg.life.pw.web.io.WIPTableFilter;

/* Modifications: ENH874 */

/**
 * Class AuditTableFilter
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class AuditTableFilter extends WIPTableFilter {

	private java.sql.Date startDate;

	private java.sql.Date endDate;
	
	private boolean extAudit;
	
	private DataSource extAuditDataSource;
	
	// ENH874 - new members to support paging
	private String sortString = null;
	private String[] sortColumns = null;
	private String[] sortOrder = null; 
	private int pageNumber = 0;
	private int numPages = 0;
	private int numRows = 0;

	/**
	 * AuditTableFilter constructor comment.
	 */

	public AuditTableFilter() {

		super();

		startDate = null;
		endDate = null;
	}

	/**
	 * Insert the method's description here. Creation date: (05/13/2001 9:50:00
	 * AM)
	 * 
	 * @return
	 */

	public java.sql.Date getEndDate() {
		return endDate;
	}

	/**
	 * Insert the method's description here. Creation date: (05/13/2001 9:50:00
	 * AM)
	 * 
	 * @return
	 */

	public java.sql.Date getStartDate() {
		return startDate;
	}

	/**
	 * Insert the method's description here. Creation date: (05/13/2001 9:50:00
	 * AM)
	 * 
	 * @param endDate
	 */

	public void setEndDate(java.sql.Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * Insert the method's description here. Creation date: (05/13/2001 9:50:00
	 * AM)
	 * 
	 * @param startDate
	 */

	public void setStartDate(java.sql.Date startDate) {
		this.startDate = startDate;
	}
	
	public boolean isExtAudit() {
		return extAudit;
	}
	
	public void setExtAudit(boolean extAudit) {
		this.extAudit = extAudit;
	}
	public DataSource getExtAuditDataSource() {
		return extAuditDataSource;
	}
	public void setExtAuditDataSource(DataSource extAuditDataSource) {
		this.extAuditDataSource = extAuditDataSource;
	}
	
	// ENH874 - new methods to support paging
	public String getSortString() {
		return sortString;
	}
	
	public void setSortString(String sortString) {
		this.sortString = sortString;

		// parse the SQL order by string into columns and orders
		// E.g.  "PROJECT_NAME ASC, TIME_STAMP ASC"
		String[] sortTokens = getSortString().split(",");
		sortColumns = new String[sortTokens.length];
		sortOrder = new String[sortTokens.length];
		for (int i=0; i < sortTokens.length; i++) {
			String[] tokens = sortTokens[i].split(" ");
			sortColumns[i] = tokens[0];
			sortOrder[i] = tokens[1];
		}
	}
	
	public String[] getSortColumns() {
		return sortColumns;
	}
	
	public String[] getSortOrder() {
		return sortOrder;
	}
	
	public int getPageNumber() {
		return pageNumber;
	}
	
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	
	public int getNumPages() {
		return numPages;
	}
	
	public void setNumPages(int numPages) {
		this.numPages = numPages;
	}
	
	public int getNumRows() {
		return numRows;
	}
	
	public void setNumRows(int numRows) {
		this.numRows = numRows;
	}
}

