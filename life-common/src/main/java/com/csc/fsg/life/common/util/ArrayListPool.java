/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL
 * SERVICES GROUP. IT MAY NOT BE COPIED IN WHOLE OR
 * IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF
 * CSC FINANCIAL SERVICES GROUP.
 */
package com.csc.fsg.life.common.util;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Pool of ArrayList objects.
 */
@SuppressWarnings("unchecked")
public final class ArrayListPool {

    /** logger **/
    private static Log log = LogFactory.getLog(HashMapPool.class);

	// Class variables
	private final static boolean DEBUG = false;
	private final static int INITIAL_CAPACITY = 32;

	private static ArrayListPool _instance = null;
	private static volatile int _refCount = 0;

	// Instance variables

	private ArrayList<Object> _v = new ArrayList<Object>(INITIAL_CAPACITY);

	/**
	 * Gets the singleton pool.
	 * @return The singleton pool instance.
	 */
	public synchronized static ArrayListPool getInstance() {

		if (_instance == null) {
			_instance = new ArrayListPool();
		}

		return _instance;
	}

	/**
	 * Checks out an entry from the pool.
	 * @return Get an available ArrayList object to use.
	 */
	public synchronized ArrayList getEntry() {

		ArrayList obj;

		_refCount++;

		try {
			obj = (ArrayList) _v.remove(_v.size() - 1);
		} catch (IndexOutOfBoundsException e) {
			obj = createEntry();
		}

		return obj;
	}

	/**
	 * Releases the specified ArrayList to the pool.
	 * @param al The array list to release.
	 * @return true on success, false on failure.
	 */
	public synchronized boolean releaseEntry(ArrayList al) {

		al.clear();

		boolean fOk = _v.add(al);

		_refCount--;

		if (DEBUG) {
			try {
				check(al);
			} catch (ResourceLeakException e) {
				log.error("Resource Leak Error: " + e.getMessage());
			}
		}

		return fOk;
	}

	/**
	 * Returns the current number of ArrayList objects in the pool.
	 * @return The current number of ArrayList objects in the pool.
	 */
	public int refCount() {
		return _refCount;
	}

	/**
	 * Ensure all pool references are returned on garbage collection or
	 * throws an exception.
	 *
	 * @throws Throwable
	 */
	public void finalize()
		throws Throwable {

		try {
			if (_refCount != 0) {
				throw new ResourceLeakException(_refCount
												+ " resources leaked from ArrayListPool");
			}
		} finally {
			super.finalize();
		}
	}

	private synchronized void check(Object obj)
		throws ResourceLeakException {

		if (_refCount < 0) {
			throw new ResourceLeakException("ArrayListPool negative refCount for object "
											+ obj.hashCode());
		}
	}

	private ArrayListPool() {

		for (int i = 0; i < INITIAL_CAPACITY; i++) {
			addEntry(createEntry());
		}
	}

	private boolean addEntry(Object obj) {
		return _v.add(obj);
	}

	private ArrayList createEntry() {
		return new ArrayList(89);
	}
}
