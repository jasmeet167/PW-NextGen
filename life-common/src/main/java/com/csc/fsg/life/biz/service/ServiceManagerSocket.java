package com.csc.fsg.life.biz.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.bo.CommArea;
import com.csc.fsg.life.biz.copyobject.CopyObject;
import com.csc.fsg.life.biz.copyobject.XgCommAreaCopyObject;
import com.csc.fsg.life.context.UserContext;

/* Modifications: ENH863, V-E233, P0157, T0150, T0175 */

/**
 *  ServiceManagerSocket is a convenience wrapper around a
 *  basic Socket.  It simplifies the communication with a CICS
 *  backend system.
 */
public class ServiceManagerSocket
{
    /** logger for the ServiceManagerSocket class. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");
    
    private Socket socket = null;
    private InputStream inputStream = null;
    private OutputStream outputStream = null;
	protected UserContext userContext;
    
    /**
     * Build a service manager socket object.
     * 
     * @param host The host to send events to.
     * @param port The port to send events to.
     * @param timeout How long in milliseconds before a timeout.
     * @param userContext The user context.
	 */
    public ServiceManagerSocket(String host, int port, int timeout,	UserContext userContext)
            throws IOException
    {
        socket = new Socket(host, port);
        socket.setSoTimeout(timeout);
		this.userContext = userContext;
    }

	/**
     * Returns the socket.
     * 
     * @return the socket.
	**/
    public Socket getSocket()
    {
        return socket;
    }
    
    /**
     * Creates an OutputStream if it was not already created.
     */
    protected OutputStream getOutputStream()
    {
        try {
            if (outputStream == null)
                outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
        } catch (IOException e) {
			log.error("Error accessing socket output stream: " + e.getMessage());
        }

        return outputStream;
    }

    /**
     * Creates an InputStream if it was not already created.
     */
    protected InputStream getInputStream()
    {
        try {
            if (inputStream == null)
                inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        } catch (IOException e) {
			log.error("Error accessing socket input stream: " + e.getMessage());
        }

        return inputStream;
    }

    /**
     * Utilizes a Socket to send a message to the CICS backend
     * system to signify that there are records waiting to be
     * processed in XGREPOS.
     * 
     * @throws Exception
     */
    public CommArea sendEvent(CommArea commAreaInput)
    	throws Exception
    { 
        // send event data through socket
        byte[] eventData = ((CopyObject)commAreaInput).toBytes();
        log.info("Sending Event with length of " + eventData.length);
        
		send(eventData);
		byte[] result = receive(eventData.length);
        
		// inflate comm area using the response
        CommArea commAreaOutput = new XgCommAreaCopyObject(userContext, result);
        return commAreaOutput;
    }

    /**
     * Write data to the OutputStream.
     * 
     * @param data
     * @throws IOException
     */
    protected void send(byte[] data)
            throws IOException
    {
        OutputStream outputStream = getOutputStream();
        outputStream.write(data);
        outputStream.flush();
    }

    /**
     * Read a given number of bytes from the InputStream.
     * 
     * @param data          buffer used for data read
     * @param bytesToRead   number of bytes to read
     * @throws IOException
     */
    protected void receive(byte[] data, int bytesToRead)
            throws IOException
    {
        InputStream inputStream = getInputStream();

        int bytesReceived = 0;
        int i = 0;
        do {
            i = inputStream.read(data, bytesReceived, bytesToRead - bytesReceived);

            if (i >= 0)

                bytesReceived += i;

        } while (bytesReceived < bytesToRead && i != -1);

    }

    /**
     * Read data from the InputStream.
     * 
     * @throws IOException
     */
    protected byte[] receive(int numBytes)
            throws IOException
    {
    	byte[] result = new byte[numBytes];
    	
    	InputStream inputStream = getInputStream();
    	try {
    		inputStream.read(result);
    	}
    	finally {
    		inputStream.close();
    	}

        return result;
    }

    /**
     * Disconnect from the backend by closing the socket.
     */
    public void disconnect()
    {
		if (socket != null)	{
			try {
				if (!socket.isClosed())
					socket.close();
			} catch(IOException e) {
				log.error("Error closing socket: " + e.getMessage());
			}
			socket = null;
		}
    }

    /**
     * Make sure we close the socket.
     */
    @Override
    protected void finalize()
            throws Throwable
    {
        try {
            disconnect();
        } finally {
            super.finalize();
        }
    }
}