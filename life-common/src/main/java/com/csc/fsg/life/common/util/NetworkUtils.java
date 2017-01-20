package com.csc.fsg.life.common.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Network related utility class.
 * 
 * @author mchintal
 */
public class NetworkUtils {

	private static final Log log = LogFactory.getLog(NetworkUtils.class);
	private static String ipAddressNoDots = "";
	private static String ipAddress = "";
	private static String hostName = "";
	private static String canonicalHostName = "";

	static {
		initIpAddress();
	}

	/**
	 * Initializes the IP address.
	 */
	private static void initIpAddress() {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			ipAddress = addr.getHostAddress();
			hostName = addr.getHostName();
			canonicalHostName = addr.getCanonicalHostName();
			
			// To escape special char '.' use \\
			ipAddressNoDots = ipAddress.replaceAll("\\.", "");

			if (log.isDebugEnabled()) {
				log.debug("Ip Address:" + ipAddress);
				log.debug("Ip Address no dots:" + ipAddressNoDots);
				log.debug("Host Name:" + hostName);
				log.debug("Canonical Host Name:" + canonicalHostName);
			}
		} catch (UnknownHostException e) {
			log.error("Could not get the IP address of the local host: ", e);
		}
	}

	/**
	 * @return Returns the ipAddress.
	 */
	public static String getIpAddress() {

		return ipAddress;
	}

	/**
	 * @return Returns the ipAddress with out any Dots ('.').
	 */
	public static String getIpAddressNoDots() {

		return ipAddressNoDots;
	}

	/**
	 * @return Returns the hostName.
	 */
	public static String getHostName() {

		return hostName;
	}

	/**
	 * @return Returns the canonicalHostName.
	 */
	public static String getCanonicalHostName() {

		return canonicalHostName;
	}
}
