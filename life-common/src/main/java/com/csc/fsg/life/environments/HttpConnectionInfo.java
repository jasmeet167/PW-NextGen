package com.csc.fsg.life.environments;


/**
   This class holds the connection parameter required for HTTP.
**/
public class HttpConnectionInfo
	extends AbstractConnection
{
	private String m_ipAddress = "";

	/**
	   Create an empty HttpConnectionInfo object.
	**/
	public HttpConnectionInfo()
	{
	}

	/**
	   Create an empty HttpConnectionInfo object from a URL.
	   @param address The URL to the connection.
	**/
	public HttpConnectionInfo(String address)
	{
		m_ipAddress = address;
	}
	
	/**
	   Returns the address.
	   @return The IP Address.
	   @see #setIpAddress
	**/
	public String getIpAddress()
	{
		return m_ipAddress;
	}

	/**
	   Sets the address.
	   @param val The IP Address.
	   @see #getIpAddress
	 **/
	public void setIpAddress(String val)
	{
		m_ipAddress = val;
	}

	public String toString()
	{
		StringBuffer sb = new StringBuffer("ConnInfo: Environment Protocol: ");
		sb.append("HTTP");
		sb.append(", IP Address ");
		sb.append(m_ipAddress);
		sb.append(", charset ");
		sb.append(getCharset());
		return sb.toString();
	}

}
