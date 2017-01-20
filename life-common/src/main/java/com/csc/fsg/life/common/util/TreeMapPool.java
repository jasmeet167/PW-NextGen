/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL
 * SERVICES GROUP. IT MAY NOT BE COPIED IN WHOLE OR
 * IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF
 * CSC FINANCIAL SERVICES GROUP.
 */
package com.csc.fsg.life.common.util;

import java.util.ArrayList;
import java.util.TreeMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
   Pool of TreeMap objects.
 */
public final class TreeMapPool {

    // logger 
    private static Log log = LogFactory.getLog(TreeMapPool.class);

	// Class variables
	private final static boolean DEBUG = false;
	private final static int INITIAL_CAPACITY = 32;

	private static TreeMapPool _instance = null;
	private static volatile int _refCount = 0;

	// Instance variables

	private ArrayList<Object> _v = new ArrayList<Object>(INITIAL_CAPACITY);

	/**
	 * Get the singleton pool instance.
	 * @return  The singleton pool instance.
	 */
	public synchronized static TreeMapPool getInstance() {

		if (_instance == null) {
			_instance = new TreeMapPool();
		}

		return _instance;
	}

	/**
	 * Get a TreeMap entry from the pool.
	 * @return Get an available TreeMap object to use.
	 */
	@SuppressWarnings("unchecked")
	public synchronized TreeMap getEntry() 
	{
		TreeMap obj;
		_refCount++;
		try {
			obj = (TreeMap) _v.remove(_v.size() - 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			obj = createEntry();
		}

		return obj;
	}

	/**
	 * Release a TreeMap object back to the pool.
	 * @param tm The TreeMap object to release.
	 * @return true on success, false on failure.
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean releaseEntry(TreeMap tm) 
	{
		tm.clear();
		boolean fOk = _v.add(tm);
		_refCount--;
		if (DEBUG) {
			try {
				check(tm);
			} catch (ResourceLeakException e) {
				log.error("Resource leak: " + e.getMessage());
			}
		}

		return fOk;
	}

	/**
	 * Retruns the number of TreeMap objects currently in the pool.
	 * @return The number of TreeMap objects currently in the pool.
	 */
	public int refCount() {
		return _refCount;
	}

	/**
\	 * Ensure all pool references are returned on garbage collection or
	 * throws an exception.
	 *
	 * @throws Throwable
	 */
	public void finalize()
		throws Throwable {

		try {
			if (_refCount != 0) {
				throw new ResourceLeakException(_refCount
												+ " resources leaked from TreeMapPool");
			}
		} finally {
			super.finalize();
		}
	}

	private synchronized void check(Object obj)
		throws ResourceLeakException {

		if (_refCount < 0) {
			throw new ResourceLeakException("TreeMapPool negative refCount for object "
											+ obj.hashCode());
		}
	}

	private TreeMapPool() {

		for (int i = 0; i < INITIAL_CAPACITY; i++) {
			addEntry(createEntry());
		}
	}

	private boolean addEntry(Object obj) {
		return _v.add(obj);
	}

	@SuppressWarnings("unchecked")
	private TreeMap createEntry() {
		return new TreeMap();
	}
}
