package com.csc.fsg.life.convert;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
   Pool of string buffer objects.
**/
public final class StringBufferPool
{
    /** logger for apache commons logging. **/
    private static Log log = LogFactory.getLog(StringBufferPool.class);

    // Class variables
    private final static boolean DEBUG = false;
    private final static int INITIAL_CAPACITY = 48;
    private final static int DEFAULT_SIZE = 256;
    private static StringBufferPool _instance = null;
    private static volatile int _refCount = 0;

    // Instance variables
    private ArrayList<Object> _v = new ArrayList<Object>(INITIAL_CAPACITY);

	/**
	   Get the singleton pool. 
	**/
    public synchronized static StringBufferPool getInstance()
    {
        if (_instance == null)
            _instance = new StringBufferPool();

        return _instance;
    }

	/**
	   Get a entry from the pool. 
	**/
    public synchronized StringBuffer getEntry()
    {
        StringBuffer obj;
        _refCount++;
        try {
            obj = (StringBuffer) _v.remove(_v.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            obj = createEntry();

        }
        return obj;
    }

	/**
	   release a string buffer back to the pool.
	**/
    public synchronized boolean releaseEntry(StringBuffer sb)
    {
        sb.setLength(0);
        boolean fOk = _v.add(sb);
        _refCount--;
        if (DEBUG) {
            try {
                check(sb);
            } catch (Exception e) {
				log.error("Resource Leak Error: " + e.getMessage());
            }
        }
        return fOk;
    }

	/**
	   Returns the number of string buffers checked out of the pool currently.
	**/
    public int refCount()
    {
        return _refCount;
    }

	/**
	   Throw an exception on garbage collection when there 
	   are un-released string buffers.
	**/
    public void finalize()
            throws Throwable
    {
        try {
            if (_refCount != 0)
                throw new Exception(_refCount + " resources leaked from StringBufferPool");
        } finally {
            super.finalize();
        }
    }

    private synchronized void check(Object obj)
            throws Exception
    {
        if (_refCount < 0)
            throw new Exception("StringBufferPool negative refCount for object " + obj.hashCode());
    }

    private StringBufferPool()
    {
        for (int i = 0; i < INITIAL_CAPACITY; i++)
            addEntry(createEntry());
    }

    private boolean addEntry(Object obj)
    {
        return _v.add(obj);
    }

    private StringBuffer createEntry()
    {
        return new StringBuffer(DEFAULT_SIZE);
    }
}
