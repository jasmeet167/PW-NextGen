/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.io;

/**
 * This interface allows the receiver to act upon a WIPRows object in a limited
 * fashion. Creation date: (10/16/2001 11:59:01 AM)
 * 
 * @author: Carl Ericksen
 */

/* Modifications T0091 */

public interface IWIPRows {

	/**
	 * This method validates the type of object passed and adds it to itself.
	 * Valid subtypes are CommonWIPRow, PlanWIPRow, IndexWIPRow, and AuditRow.
	 * If not valid subtype, false will be returned with an
	 * InvalidArgumentException. Creation date: (10/16/2001 12:01:44 PM)
	 * 
	 * @return boolean
	 * @param wipRow
	 *            java.lang.Object
	 */

	// T0091 - make add specific to WIPRow
	public boolean add(WIPRow wipRow);

	/**
	 * This method returns an instance of the WIPTableFilter used to construct
	 * this collection
	 * 
	 * @return
	 */

	public WIPTableFilter getRequestFilter();
}
