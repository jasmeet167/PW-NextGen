package com.csc.fsg.life.biz.service;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.csc.fsg.life.biz.bo.CommArea;
import com.csc.fsg.life.biz.bo.XgCommArea;
import com.csc.fsg.life.biz.copyobject.CopyObject;
import com.csc.fsg.life.biz.copyobject.XgCommAreaCopyObject;
import com.csc.fsg.life.context.UserContext;

/* Modifications: T0150, T0175 */

/**
 * Basic implementation of a ServiceManagerConnection that utilizes HTTP as
 * the communication protocol.
 */
public class HTTPConnection 
	implements ServiceManagerConnection
{
    /** Logger for the HTTPConnection class. */
    protected static final Log log = LogFactory.getLog("com.csc.fsg");
    
    private HttpURLConnection httpconn = null;
    private OutputStream outputStream = null;
    private InputStream inputStream = null;

    private String program = "OTXXMLT";
    private String address;
    private String user;
    private String pwd;
    private String db;

	/** Maximum password length. */
    protected static final int PASSWORD_LENGTH = 32;
	/** Maximum user id length. */
    protected static final int USER_ID_LENGTH = 32;
	/** Maximum database name length. */
    protected static final int DATABASE_LENGTH = 8;
	/** Maximum program name length. */
    protected static final int PROGRAM_LENGTH = 8;

    /**
     * Use an HttpURLConnection to create a connection with the backend 
     * system and send the marshaled communication area copybook.
     */
    public CommArea sendEvent(CommArea commAreaInput)
            throws ServiceManagerException
    {
        XgCommArea commAreaOutput = null;
        UserContext userContext = ((CopyObject)commAreaInput).getUserContext();
        
        try {
            // establish backend connection
            connect();

            // send the comm area image
            byte[] data = ((CopyObject)commAreaInput).toBytes();
            send(data);
            
            // receive the response
            byte[] result = receive(data.length);
            commAreaOutput = new XgCommAreaCopyObject(userContext, result);
                       
            log.debug("Send Complete");

        } catch (Exception e) {
            log.error("Error occurred with HTTP connection", e);
            throw new ServiceManagerException(e.getMessage());
        } finally {
            // close connection
            try {
                disconnect();
            } catch (IOException e) {
                log.error("Error in disconnect from HTTP Connection: " + e.getMessage());
            }
        }

        return commAreaOutput;
    }

    /**
     * Establishes a connection to the Http server.
     * 
     * @throws IOException
     */
    public void connect()
            throws IOException
    {
        // Ensure we are not already connected.
        if (httpconn != null)
            throw new IOException("Already connected");

        // Open a connection.
        log.info("Attempting to connect to: " + address);
        URL url = new URL(address);
        URLConnection conn = url.openConnection();
        httpconn = (HttpURLConnection) conn;
        log.info(" ... connected");

        // Set the connection properties
        httpconn.setRequestProperty("Content-Type", "text/plain");
        // ??? Does the content-length need to be set properly?
        httpconn.setRequestProperty("Content-Length", "" + (1024 * 10));
        httpconn.setDoOutput(true);
        httpconn.setDoInput(true);
        httpconn.setRequestMethod("POST");

        // Get the output stream.
        outputStream = httpconn.getOutputStream();
    }

    /**
     * Write data to the OutputStream.
     * 
     * @param data
     * @throws IOException
     */
    public void send(byte[] data)
            throws IOException
    {
        // make sure we have data to send
        if (data == null || data.length == 0) 
            return;
        
        // ensure we are connected
        if (httpconn == null)
            throw new IOException("send() called when not connected");

        log.info("Sending data over HttpConnection ...");

        outputStream.write(data, 0, data.length);
        outputStream.flush();
    }

    /**
     * Read data from the InputStream.
     * 
     * @throws IOException
     */
    public String receive()
        throws IOException
    {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        
        // Ensure we are connected.
        if (httpconn == null)
            throw new IOException("receive() called when Not connected");

        // Receive the response.
        log.info("Receiving data from Http Connection ...");
        do {
            byte[] data = new byte[1024];
            int bytesReceived = receive(data, data.length);
            if (bytesReceived <= 0)
                break;
            stream.write(data, 0, bytesReceived);
        } while (true);
        
        return stream.toString();
    }

	/**
     * Returns the input stream for the connection.
     * 
     * @return the input stream for the connection.
     */
    protected InputStream getInputStream()
    {
        try {
            if (inputStream == null)
                inputStream = 
                    new DataInputStream(new BufferedInputStream(httpconn.getInputStream()));
        } catch (IOException e) {
            log.error("Error accessing HTTP input stream: " + e.getMessage());
        }

        return inputStream;
    }

    /**
     * Read a given number of bytes from the InputStream.
     * 
     * @param data          buffer used for data read
     * @param bytesToRead   number of bytes to read
     * @throws IOException
     */
    protected int receive(byte[] data, int bytesToRead)
            throws IOException
    {
        int bytesReceived = 0;
        InputStream inputStream = getInputStream();
        int i = 0;
        do {
            i = inputStream.read(data, bytesReceived, bytesToRead - bytesReceived);
            if (i >= 0)
                bytesReceived += i;
        } while (bytesReceived < bytesToRead && i != -1);

        return bytesReceived;
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
    	
    	InputStream inputStream = null;
    	try {
            inputStream = getInputStream();
            inputStream.read(result);

            return result;
        }
        finally {
            if (inputStream != null)
                inputStream.close();
        }
    }

    /**
     * Disconnect from the HTTP server.
     */
    public void disconnect()
            throws IOException
    {
        if (httpconn != null)
            httpconn.disconnect();

        httpconn = null;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error(" **ERROR** Couldn't close output stream in HttpConnection: " + e.getMessage());
            }
            outputStream = null;
        }

        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(" **ERROR** Couldn't close input stream in HttpConnection: " + e.getMessage());
            }
            inputStream = null;
        }
    }

    /**
     * Ensure we are disconnected on garbage collection.
     */
    @Override
    public void finalize()
    {
        try {
            disconnect();
        } catch (Exception ex) {
        	log.error("HTTP unable to disconnect: " + ex.getMessage());
        }
    }

	/**
     * Sets the Address.
     * 
     * @param address The new Address value.
	 */
    public void setAddress(String address)
    {
        this.address = address;
    }

	/**
     * Sets the Program name to call on the server.
     * 
     * @param program The New Program value.
	 */
    public void setProgram(String program)
    {
        this.program = program;
    }

	/**
     * Sets the Db name for the server to use.
     * 
     * @param db The new Db value.
	 */
    public void setDb(String db)
    {
        this.db = db;
    }
    

	/**
     * Sets the user name for the server to use.
     * 
     * @param user The new user value.
	 */
    public void setUser(String user)
    {
        this.user = user;
    }
    
	/**
     * Sets the pwd name for the server to use.
     * 
     * @param pwd The new pwd value.
	 */
    public void setPwd(String pwd)
    {
        this.pwd = pwd;
    }    

    @Override
    public String toString()
    {
        StringBuffer buf = new StringBuffer();
        
        buf.append("program: ");
        buf.append(program);
        buf.append("address: ");
        buf.append(address);
        buf.append("user: ");
        buf.append(user);
        buf.append("pwd: ");
        buf.append(pwd);
        buf.append("db: ");
        buf.append(db);
        
        return buf.toString();
    }
}
