package com.csc.fsg.life.biz.service;

import java.net.SocketTimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.bo.CommArea;
import com.csc.fsg.life.biz.copyobject.CopyObject;
import com.csc.fsg.life.context.UserContext;

/* Modifications: ENH863, T0150 */

/**
 *  Basic implementation of a ServiceManagerConnection that
 *  utilizes TCP/IP as the communication protocol.
 */
public class TCPIPConnection implements ServiceManagerConnection
{
    /** Logger for the TCPIPConnection class. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");
    
    /** the ipAddress to use for this connection. */
    protected String ipAddress;
    
    /** The port to use for this connection. */
    protected int port;
    
    /** The transaction id. */
    protected String transactionId;
    
    /** The socket timeout. */
    protected int timeout = 30000;
    
    /**
     * Use a socket to create a connection with the backend system 
     * and send the marshaled communication area copybook.
     */
    public CommArea sendEvent(CommArea commAreaInput)
        throws ServiceManagerException
    {
        UserContext userContext = ((CopyObject) commAreaInput).getUserContext();
        
        CommArea commAreaOutput = null;       
		ServiceManagerSocket socket = null;
        try {
            // establish backend connection
            log.info("Connecting: (ip: " + ipAddress + ", port: " + port + ", trans: " + transactionId + ")");
			socket = new ServiceManagerSocket(ipAddress, port, timeout, userContext);
 
            // send the comm area image
            commAreaInput.setCicsTrx(transactionId);
            commAreaOutput = socket.sendEvent(commAreaInput);
            
            log.info("Send Complete");
            
        } catch(SocketTimeoutException e) {
        	log.info("Socket timed out.", e);
        	throw new ServiceManagerTimeoutException(e);
    	} catch(Exception e) {
            log.error("Could not get TCPIP connection", e);
            throw new ServiceManagerException(e.getMessage());
        } finally {
			if (socket != null)
				socket.disconnect();
		}
        
        return commAreaOutput;
    }

	/**
	 * Returns the IpAddress.
	 * 
	 * @return The IpAddress value.
	 * @see #setIpAddress
	 */
    public String getIpAddress()
    {
        return ipAddress;
    }

	/**
	 * Sets the IpAddress.
	 * 
	 * @param ipAddress The new IpAddress value.
	 * @see #getIpAddress
	 */
    public void setIpAddress(String ipAddress)
    {
        this.ipAddress = ipAddress;
    }

	/**
	 * Returns the Port.
	 * 
	 * @return The Port value.
	 * @see #setPort
	 */
    public int getPort()
    {
        return port;
    }

	/**
	 * Sets the Port.
	 * 
	 * @param port The new Port value.
	 * @see #getPort
	 */
    public void setPort(int port)
    {
        this.port = port;
    }

	/**
	 * Returns the TransactionId.
	 * 
	 * @return The TransactionId value.
	 * @see #setTransactionId
	 */
    public String getTransactionId()
    {
        return transactionId;
    }

	/**
	 * Sets the TransactionId.
	 * 
	 * @param transactionId The new TransactionId value.
	 * @see #getTransactionId
	 */
    public void setTransactionId(String transactionId)
    {
        this.transactionId = transactionId;
    }

	/**
	 * Returns the Timeout in miliseconds.
	 * 
	 * @return The Timeout value.
	 * @see #setTimeout
	 */
    public int getTimeout()
    {
        return timeout;
    }

	/**
	 * Sets the Timeout.
	 * 
	 * @param timeout The new Timeout value.
	 * @see #getTimeout
	 */
    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        
        sb.append("ip: " + ipAddress);
        sb.append(", port: " + port);
        sb.append(", transaction id: " + transactionId);
        sb.append(", timeout: " + timeout);
        
        return sb.toString();
    }
}