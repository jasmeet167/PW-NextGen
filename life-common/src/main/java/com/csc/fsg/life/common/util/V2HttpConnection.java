package com.csc.fsg.life.common.util;

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

/* Modifications: T0175 */

/**
   A HTTP Connection to Net Express.
**/
public class V2HttpConnection
{
	private static Log logger = LogFactory.getLog(V2HttpConnection.class);

	private   HttpURLConnection httpconn = null;
	private   OutputStream outputStream = null;
	private   InputStream inputStream = null;

	private   String m_program;
	private   String m_address;
	private   String m_user;
	private   String m_pwd;
	private   String m_db;

	/** Maximum password length. **/
	protected static final int PASSWORD_LENGTH = 32;
	/** Maximum user id length. **/
	protected static final int USER_ID_LENGTH = 32;
	/** Maximum database id length. **/
	protected static final int DATABASE_LENGTH = 8;
	/** Maximum program name length. **/
	protected static final int PROGRAM_LENGTH = 8;


	/**
	   Configure with specific program and database.
	**/
	public V2HttpConnection(String prog,
							String address,
							String user, String pwd,
							String db)
	{
		m_program = prog;
		m_address = address;
		m_user = user;
		m_pwd = pwd;
		m_db = db;
	}

	@Override
	public String toString()
	{
		StringBuffer buf = new StringBuffer();
		buf.append("program: ");
		buf.append(m_program);
		buf.append("address: ");
		buf.append(m_address);
		buf.append("user: ");
		buf.append(m_user);
		buf.append("pwd: ");
		buf.append(m_pwd);
		buf.append("db: ");
		buf.append(m_db);
		return buf.toString();
	}
	
	/**
	   Connect to the HTTP server.
	**/
	public void connect()
		throws IOException
	{
		// Ensure we are not already connected.
		if (httpconn != null)
			throw new IOException("Already connected");

		// Open a connection.
		logger.info("Attempting to connect to: " + m_address);
		URL url = new URL(m_address);
		URLConnection conn = url.openConnection();
		httpconn = (HttpURLConnection) conn;
		logger.info(" ... connected");

		// Set the connection properties
		httpconn.setRequestProperty ( "Content-Type", "text/plain" );
		// ??? Does the content-length need to be set properly?
		httpconn.setRequestProperty ( "Content-Length", "" + (1024 * 10) );
		httpconn.setDoOutput(true);
		httpconn.setDoInput(true);
		httpconn.setRequestMethod("POST");

		// Get the output stream.
		outputStream = httpconn.getOutputStream();
	}

	/**
	   Send a byte array of data over teh connection.
	**/
	public void send(byte[] data)
		throws IOException
	{
		if (data != null && data.length > 0)
		{
			// Ensure we are connected.
			if (httpconn == null)
				throw new IOException("send() called when Not connected");

			logger.info("Sending data over HttpConnection ...");

			String userIdPwd =
				padStringRight(m_user, " ", USER_ID_LENGTH) +
				padStringRight(m_pwd, " ", PASSWORD_LENGTH) +
				padStringRight(m_db, " ", DATABASE_LENGTH) +
				padStringRight(m_program, " ", PROGRAM_LENGTH);

			byte[] upBytes = userIdPwd.getBytes();
			outputStream.write(upBytes, 0, upBytes.length);
			outputStream.write(data, 0, data.length);
			outputStream.flush();
		}
	}

	/**
	   Pad a string to the right with the specified pad character.
	**/
	private String padStringRight(String s, String padChar, int length)
	{
		if (s.length() >= length)
			return s;

		// pad the string
		StringBuffer paddedString = new StringBuffer(s);
		for (int i = paddedString.length(); i < length; i++) {
			paddedString.append(padChar);
		}

		return paddedString.toString();
	}

	/**
	   Recieve a byte array from the connection.
	**/
	public void receive(ByteArrayOutputStream stream)
		throws IOException
	{
		// Ensure we are connected.
		if (httpconn == null)
			throw new IOException("receive() called when Not connected");

		// Receive the response.
		logger.info("Receiving data from Http Connection ...");
		do
		{
			byte[] data = new byte[1024];
			int bytesReceived = receive(data, data.length);
			logger.info("received " + bytesReceived);
			if (bytesReceived <= 0)
				break;
			stream.write(data, 0, bytesReceived);
		}
		while(true);
	}

	/**
	   Get the connection input stream.
	**/
	protected InputStream getInputStream()
	{
		try
		{
			if (inputStream == null)
				inputStream = new DataInputStream(new BufferedInputStream(httpconn.getInputStream()));
		}
		catch (IOException e)
		{
			logger.error("Error getting HTTP input stream: " + e.getMessage());
		}

		return inputStream;
	}

	/**
	   Receive the specified number of bytes from the connection.
	**/
	protected int receive(byte[] data, int bytesToRead)
		throws IOException
	{
		int bytesReceived = 0;
		InputStream inputStream = getInputStream();
		int i = 0;
		do
		{
			i = inputStream.read(data, bytesReceived, bytesToRead-bytesReceived);
			if (i >= 0)
				bytesReceived += i;
		}
		while (bytesReceived < bytesToRead && i != -1);

		return bytesReceived;
	}

	/**
	   Disconnect from the HTTP server.
	**/
	public void disconnect()
		throws IOException
	{
		if (httpconn != null)
			httpconn.disconnect();

		httpconn = null;
		if (outputStream != null)
		{
			try
			{
				outputStream.close();
			}
			catch(IOException ioe)
			{
				logger.error(" **ERROR** Couldn't close output stream in HttpConnection.");
			}
			outputStream = null;
		}

		if (inputStream != null)
		{
			try
			{
				inputStream.close();
			}
			catch(IOException  ioe)
			{
				logger.error(" **ERROR** Couldn't close input stream in HttpConnection.");
			}
			inputStream = null;
		}
	}

	/**
	   Ensure we are disconnected on garbage collection.
	**/
	@Override
	public void finalize()
	{
		try
		{
			disconnect();
		}
		catch (Exception ex)
		{
        	logger.error("HTTP unable to disconnect: " + ex.getMessage());
		}
	}


}
