/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL
 * SERVICES GROUP. IT MAY NOT BE COPIED IN WHOLE OR
 * IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF
 * CSC FINANCIAL SERVICES GROUP.
 */
package com.csc.fsg.life.common.util;

import java.util.ArrayList;

/* Modifications: T0140 */

/**
   A pool for StringBuffer objects.
 */
public final class StringBufferPool 
{
	private final static int INITIAL_CAPACITY = 48;
	private final static int DEFAULT_SIZE = 256;

	private static StringBufferPool _instance = null;
	private static volatile int _refCount = 0;

	private ArrayList<Object> _v = new ArrayList<Object>(INITIAL_CAPACITY);

	/**
	 * Get the singleton pool instance.
	 * @return  The singleton pool instance.
	 */
	public synchronized static StringBufferPool getInstance() {

		if (_instance == null) {
			_instance = new StringBufferPool();
		}

		return _instance;
	}

	/**
	 * Check out an entry from the pool.
	 * @return Get an available StringBuffer object to use.
	 */
	public synchronized StringBuffer getEntry() {
		return new StringBuffer(256);
	}

	/**
	 * Release a string buffer object back to the pool.
	 * @param sb The string buffer to release.
	 * @return true on success, false on failure.
	 */
	public synchronized boolean releaseEntry(StringBuffer sb) {
		return true;
	}

	/**
	 * Returns the current number of StringBuffer objects in the pool.
	 * @return The current number of StringBuffer objects in the pool.
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
												+ " resources leaked from StringBufferPool");
			}
		} finally {
			super.finalize();
		}
	}

	private StringBufferPool() {

		for (int i = 0; i < INITIAL_CAPACITY; i++) {
			addEntry(createEntry());
		}
	}

	private boolean addEntry(Object obj) {
		return _v.add(obj);
	}

	private StringBuffer createEntry() {
		return new StringBuffer(DEFAULT_SIZE);
	}
}
