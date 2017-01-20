package com.csc.fsg.life.common.util;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
   Singleton pool class for HashMap objects.
 */
public final class HashMapPool {

    // logger 
    private static Log log = LogFactory.getLog(HashMapPool.class);

	// Class variables
	private final static boolean DEBUG = false;
	private final static int INITIAL_CAPACITY = 32;

	private static HashMapPool _instance = null;
	private static volatile int _refCount = 0;

	// Instance variables

	private ArrayList<Object> _v = new ArrayList<Object>(INITIAL_CAPACITY);

	/**
	 * Returns the singleton pool instance.
	 * @return The singleton pool instance.
	 */
	public synchronized static HashMapPool getInstance() {

		if (_instance == null) {
			_instance = new HashMapPool();
		}

		return _instance;
	}

	/**
	 * Get an available HashMap object to use.
	 * @return Get an available HashMap object to use.
	 */
	@SuppressWarnings("unchecked")
	public synchronized HashMap getEntry() {

		HashMap obj;

		_refCount++;

		try {
			obj = (HashMap) _v.remove(_v.size() - 1);
		} catch (ArrayIndexOutOfBoundsException e) {
			obj = createEntry();
		}

		return obj;
	}

	/**
	 * Release a hash map back to the pool.
	 * @param hm The hash map to release.
	 * @return true on success, false on failure.
	 */
	@SuppressWarnings("unchecked")
	public synchronized boolean releaseEntry(HashMap hm) {

		hm.clear();

		boolean fOk = _v.add(hm);

		_refCount--;

		if (DEBUG) {
			try {
				check(hm);
			} catch (ResourceLeakException e) {
				log.error("Resource Leak Error: " + e.getMessage());
			}
		}

		return fOk;
	}

	/**
	 * Returns the count of HashMap object currently in the pool.
	 * @return Count of HashMap object currently in the pool.
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
												+ " resources leaked from HashMapPool");
			}
		} finally {
			super.finalize();
		}
	}

	private synchronized void check(Object obj)
		throws ResourceLeakException {

		if (_refCount < 0) {
			throw new ResourceLeakException("HashMapPool negative refCount for object "
											+ obj.hashCode());
		}
	}

	private HashMapPool() {

		for (int i = 0; i < INITIAL_CAPACITY; i++) {
			addEntry(createEntry());
		}
	}

	private boolean addEntry(Object obj) {
		return _v.add(obj);
	}

	@SuppressWarnings("unchecked")
	private HashMap createEntry() {
		return new HashMap(89);
	}
}
