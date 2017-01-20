package com.csc.fsg.life.common.util;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
   Singleton pool class for ObjectArrays.
 */
public final class ObjectArrayPool {

    // logger 
    private static Log log = LogFactory.getLog(ObjectArrayPool.class);

	// Class variables
	private final static boolean DEBUG = false;
	private final static int INITIAL_CAPACITY = 32;

	private static ObjectArrayPool _instance = null;
	private static volatile int _refCount = 0;

	// Instance variables

	private ArrayList<Object> _v = new ArrayList<Object>(INITIAL_CAPACITY);

	/**
	 * Get the singleton pool instance.
	 * @return  The singleton pool instance.
	 */
	public synchronized static ObjectArrayPool getInstance() {

		if (_instance == null) {
			_instance = new ObjectArrayPool();
		}

		return _instance;
	}

	/**
	 * Get an available ObjectArray object to use from the pool.
	 * @return Get an available ObjectArray object to use.
	 */
	public synchronized Object[] getEntry() {

		Object[] obj;

		_refCount++;

		try {
			obj = (Object[]) _v.remove(_v.size() - 1);
		} catch (IndexOutOfBoundsException e) {
			obj = createEntry();
		}

		return obj;
	}

	/**
	 * Release an entry back to the pool.
	 * @param arr The Object array to put back in the pool.
	 * @return true on success, false on failure.
	 */
	public synchronized boolean releaseEntry(Object[] arr) {

		arr[0] = null;

		boolean fOk = _v.add(arr);

		_refCount--;

		if (DEBUG) {
			try {
				check(arr);
			} catch (ResourceLeakException e) {
				log.error("Resource Leak Error: " + e.getMessage());
			}
		}

		return fOk;
	}

	/**
	 * Return the number of ObjectArray's in the pool.
	 * @return Number of ObjectArray's in the pool.
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
												+ " resources leaked from ObjectArrayPool");
			}
		} finally {
			super.finalize();
		}
	}

	private synchronized void check(Object obj)
		throws ResourceLeakException {

		if (_refCount < 0) {
			throw new ResourceLeakException("ObjectArrayPool negative refCount for object "
											+ obj.hashCode());
		}
	}

	private ObjectArrayPool() {

		for (int i = 0; i < INITIAL_CAPACITY; i++) {
			addEntry(createEntry());
		}
	}

	private boolean addEntry(Object obj) {
		return _v.add(obj);
	}

	private Object[] createEntry() {
		return new Object[1];
	}
}
