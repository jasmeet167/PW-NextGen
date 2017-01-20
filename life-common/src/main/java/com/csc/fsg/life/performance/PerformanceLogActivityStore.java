package com.csc.fsg.life.performance;

/* Modifications: T0166 */

/**
 * When performance log activity is bound to an appropriate scope, it is stored
 * inside the container provided by this class, rather than directly. This
 * provides a simple mechanism to determine whether the activity has already
 * been instantiated in this scope.
 * <p>
 * The intended use of this class is as a managed bean in scope, which depends
 * on type of the application. For example, in a web application, this bean
 * should be placed in <code>request</code> scope.
 */
public class PerformanceLogActivityStore
{
	private PerformanceLogActivity activity = null;

	/**
	 * Return a flag indicating whether an instance of performance log activity
	 * has been bound to the current scope, in the container provided by this
	 * class.
	 * 
	 * @return <code>true</code> if the activity has already been created, and
	 *         <code>false</code> otherwise.
	 */
	public boolean isEmpty()
	{
		return activity == null;
	}

	/**
	 * Return the performance log activity stored in the container provided by
	 * this class.
	 * 
	 * @return The stored activity.
	 */
	public PerformanceLogActivity getActivity()
	{
		return activity;
	}

	public void setActivity(PerformanceLogActivity activity)
	{
		this.activity = activity;
	}

	public void reset()
	{
		this.activity = null;
	}
}
