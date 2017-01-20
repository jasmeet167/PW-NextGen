/*
 * Created on Jul 1, 2004
 * This class holds the connection parameter required for TCPIP
 */
package com.csc.fsg.life.environments;

/**
   Represents a TCP/IP Connection.
**/
public class TCPIPConnectionInfo
	extends AbstractConnection
{
	private String ipAddress;
	private String ipPortNumber;
	private int    timeout = -1;

	/**
	   Create an empty object.
	**/
	public TCPIPConnectionInfo()
	{
	}

	/**
	   Create a TCP/IP connection for the specified address and port.
	   @param address The TCP/IP address.
	   @param port The port the connection is hosted on.
	 */
	public TCPIPConnectionInfo(String address, String port) {
		ipAddress = address;
		ipPortNumber = port;
	}

	/**
	   Returns the port the connection is hosted on.
	   @return the port the connection is hosted on.
	   @see #setIpPortNumber
	*/
	public String getIpPortNumber() {
		return ipPortNumber;
	}

	/**
	   Returns the IP address the connection is hosted on.
	   @return the IP address the connection is hosted on.
	   @see #setIpAddress
	*/
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	   Sets the timeout for a connection in ms.
	   @param t The ms for a timeout.
	   @see #getTimeout
	**/
	public void setTimeout(int t) {
		timeout = t;
	}
	
	/**
	   Returns the timeout for a connection in ms.
	   @return The ms for a timeout.
	   @see #setTimeout
	**/
	public int getTimeout() {
		return timeout;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("ConnInfo: Environment Protocol: ");
		sb.append("MVS_TCPIP");
		sb.append(", IP Address ");
		sb.append(ipAddress);
		sb.append(", IP Port ");
		sb.append(ipPortNumber);
		sb.append(", charset ");
		sb.append(getCharset());
		sb.append(", Timeout ");
		sb.append(timeout);
		return sb.toString();
	}

	/**
	   Sets the IP address the connection is hosted on.
	   @param ipAddress the IP address the connection is hosted on.
	   @see #getIpAddress
	*/
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	   Sets the port the connection is hosted on.
	   @param ipPortNumber the port the connection is hosted on.
	   @see #getIpPortNumber
	*/
	public void setIpPortNumber(String ipPortNumber) {
		this.ipPortNumber = ipPortNumber;
	}

}
