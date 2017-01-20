package com.csc.fsg.life.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
   Class that manages Timer object by keys and thread. 
   A user can then define a timer by a key identifier and by thread
   in order to time performance of the application running in a web environment.
   In addition, this class supports adding name/value attributes to the time that 
   will be recorded with each timing.
   <p>
   This class is thread safe.
*/
@SuppressWarnings("unchecked")
public class TimerManager
{
    /** Logger for the TimerManager class. */
    private static Log log = LogFactory.getLog("com.csc.fsg.life.common.util.TimerManager");

	/** Constant in Map for display attributes. **/
	private static final String ATTR_MAP = "AttrMap";

	/**
	   A Map is stored per-thread.  The map contains key (String) -> Timer 
	   information.
	**/
	private static ThreadLocal timers = new ThreadLocal()  {
			protected synchronized Object initialValue() 
			{
				return new HashMap();
			}
		};

	/**
	   Start a timer for the key identifier in this thread.
	   @param key The key for this timer.
	**/
	public static void startTimer(String key)
	{
		((Map<String, Timer>)timers.get()).put(key, new Timer());
	}

    /**
     * Returns true if the timer is started.
     * @param key The key for this timer.
     * @return true if timer with given key is started
     */
    public static boolean isTimerStarted(String key)
    {
        return ((Map)timers.get()).get(key) != null;
    }

	/**
	   Access to a thread-local property related to timings.
	**/
    public static Object getProperty(String key)
	{
		return ((Map)timers.get()).get(key);
	}

	/**
	   Set a thread-local property related to timings.
	**/
	public static void setProperty(String key, Object value)
	{
		((Map<String, Object>)timers.get()).put(key, value);
	}

	/**
	   Clear all timers and properties for this thread.
	**/
	public static void clearAll()
	{
		Map<String,Object> threadMap = (Map<String, Object>)timers.get();
		threadMap.clear();
	}

	/**
	   Ends the timer for the key in this thread. Always logs the 
	   time to the performance log.
	   @param key The key for this timer.
	   @return the time taken in milliseconds.  -1 if the timer wasn't started.
	**/
	public static long endTimer(String key)
	{
		Map<String,Object> threadMap = (Map<String, Object>)timers.get();
		Timer t = (Timer)threadMap.get(key);
		if (t != null) {
			long timeTaken = t.timeTaken();

			if (isEnabled()) {
				String timeStr = buildTimeString(timeTaken);
				String attrMsg = buildAttrMsg((List)threadMap.get(ATTR_MAP));
				if (attrMsg.length() > 0)
					attrMsg = attrMsg + " " ;
				log.info(attrMsg + key + timeStr);
			}
			
            ((Map)timers.get()).remove(key);
            
			return timeTaken;
		}
		return -1;
	}

	private static String buildTimeString(long timeTaken) { return (": " + timeTaken); }

	/**
	   This can be used to log a time when an external resource is involved. 
	   This was added to be able to log page time from the client.
	   @param message Starting message to display.
	   @param time The external time that was recorded.
	**/
	public static void logTime(String message, long time)
	{
		if (isEnabled()) {
			String timeStr = buildTimeString(time);
			Map<String,Object> threadMap = (Map<String, Object>)timers.get();
			String attrMsg = buildAttrMsg((List)threadMap.get(ATTR_MAP));
			if (attrMsg.length() > 0)
				attrMsg = attrMsg + " " ;
			log.info(attrMsg + message + timeStr);
		}
	}

	/**
	   Returns true if performance timing is on.
	   @return true if performance timing logging is on.
	**/
	public static boolean isEnabled()
	{
		return log.isInfoEnabled();
	}

	private static String buildAttrMsg(List attrs)
	{
		if (attrs == null) return "";
		StringBuffer buf = new StringBuffer();
		for(Iterator itt = attrs.iterator(); itt.hasNext();) {
			Pair p = (Pair)itt.next();
			buf.append(p.name);
			buf.append(": ");
			buf.append(p.value);
			if (itt.hasNext())
				buf.append(" ");
		}
		return buf.toString();
	}

	/**
	   Log attribute instead of adding to each future log message, just log it once
	   without a time.
	**/
	public static void logAttribute(String name, String value)
	{
		// Bail write away if we are not logging timing.s
		if (!isEnabled()) return;

		Map<String,Object> threadMap = (Map<String, Object>)timers.get();
		String attrMsg = buildAttrMsg((List)threadMap.get(ATTR_MAP));
		if (attrMsg.length() > 0)
			attrMsg = attrMsg + " ";
		log.info(attrMsg + name + ": " + value);
	}

	/**
	   Add an attribute to be used when logging performance.
	**/
	public static void addAttribute(String name, String value)
	{
		// Bail write away if we are not logging timing.s
		if (!isEnabled()) return;

		Map<String,Object> threadMap = (Map<String, Object>)timers.get();
		List<Pair> attrs = (List<Pair>) threadMap.get(ATTR_MAP);
		if (attrs == null) {
			attrs = new ArrayList<Pair>();
			threadMap.put(ATTR_MAP, attrs);
		}
		Pair p = new Pair();
		p.name = name;
		p.value = value;
		attrs.add(p);
	}

	/**
	   Returns the list of attributes current for this thread.
	**/
	public static List getAttributes()
	{
		Map<String,Object> threadMap = (Map<String, Object>)timers.get();
		List attrs = (List) threadMap.get(ATTR_MAP);
		return attrs;
	}

	/**
	   Sets the list of attributes to the specified list.
	**/
	public static void setAttributes(List attrs)
	{
		Map<String,Object> threadMap = (Map<String, Object>)timers.get();
		threadMap.put(ATTR_MAP, attrs);
	}

	/**
	   Clear all attributes for this thread.
	**/
	public static void clearAttributes()
	{
		Map<String,Object> threadMap = (Map<String, Object>)timers.get();
		threadMap.put(ATTR_MAP, new ArrayList());
	}
	
	/**
	   Inner class used to hold the name/value pairs in the list.
	**/
	private static class Pair
	{
		private String name;
		private String value;
	}
}
