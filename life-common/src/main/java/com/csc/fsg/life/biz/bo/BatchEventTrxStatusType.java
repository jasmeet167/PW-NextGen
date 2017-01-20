package com.csc.fsg.life.biz.bo;

/* Modifications: ENH988 */

/**
 * Enumeration of all possible states of a transaction participating in a Batch
 * Event.
 */
public enum BatchEventTrxStatusType
{
	INITIAL, SUCCESSFUL, FAILED, CANCELLED, SKIPPED
}
