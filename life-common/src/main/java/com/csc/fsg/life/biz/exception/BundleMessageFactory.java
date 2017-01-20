package com.csc.fsg.life.biz.exception;

import java.util.Locale;
import java.util.ResourceBundle;

/* Modifications: ENHT0085, T0175 */

/**
 * This class is used to create an instance of a BundleMessage. It is
 * responsible for creation and supplying the applicable Resource Bundle, as
 * well as keeping a reference to the Locale associated with the current user.
 */
public class BundleMessageFactory
{
	private String bundleBaseName;
	

	/**
	 * Constructor.
	 */
	public BundleMessageFactory(String bundleBaseName)
	{
		this.bundleBaseName = bundleBaseName;
	}

	/**
	 * Load contents of the applicable resource bundle, using the default
	 * locale.
	 */
	public void loadBundle()
	{
		ResourceBundle bundle = ResourceBundle.getBundle(bundleBaseName);
		if (bundle == null || !bundle.getLocale().equals(Locale.getDefault()))
			bundle = ResourceBundle.getBundle(bundleBaseName);
	}

	/**
	 * Load contents of the applicable resource bundle, using the supplied
	 * locale.
	 */
	public void loadBundle(Locale locale)
	{
		if (locale == null)
			locale = Locale.getDefault();
		ResourceBundle bundle = ResourceBundle.getBundle(bundleBaseName);
		if (bundle == null || !bundle.getLocale().equals(locale))
			bundle = ResourceBundle.getBundle(bundleBaseName, locale);
	}

	/**
	 * Create a new instance of BundleMessage, which encapsulates the message
	 * indicated by messageKey.
	 * 
	 * @param messageKey
	 *            The key used to identify a message in the resource bundle.
	 * 
	 * @return BundleMessage The new object.
	 */
	public BundleMessage getBundleMessage(String messageKey)
	{
		// If the resource bundle has not been loaded yet, load it using the
		// default locale
		ResourceBundle bundle = ResourceBundle.getBundle(bundleBaseName);
		if (bundle == null)
			loadBundle();

		if (bundle == null) {
			BundleMessage message = new BundleMessage();
			message.setMessage("Resource not available");
			return message;
		}

		// Build and return a new instance of BundleMessage
		BundleMessage message = new BundleMessage(bundle, messageKey);
		return message;
	}

	/**
	 * Create a new instance of BundleMessage, which encapsulates the message
	 * indicated by messageKey.
	 * 
	 * @param messageKey
	 *            The key used to identify a message in the resource bundle.
	 * 
	 * @param param
	 *            Text associated with param is inserted at the location
	 *            indicated in the body of the message by a placeholder.
	 * 
	 * @return BundleMessage The new object.
	 */
	public BundleMessage getBundleMessage(String messageKey, Object param)
	{
		// If the resource bundle has not been loaded yet, load it using the
		// default locale
		ResourceBundle bundle = ResourceBundle.getBundle(bundleBaseName);
		if (bundle == null)
			loadBundle();

		if (bundle == null) {
			BundleMessage message = new BundleMessage();
			message.setMessage("Resource not available");
			return message;
		}

		// Build and return a new instance of BundleMessage
		BundleMessage message = new BundleMessage(bundle, messageKey, param);
		return message;
	}
}
