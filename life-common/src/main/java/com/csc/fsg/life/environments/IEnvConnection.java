package com.csc.fsg.life.environments;

/**
   Interface for a connection.
 */
public interface IEnvConnection
{
	public String toString();

	/**
	   Returns the character set this connection supports.
	   @return the character set this connection supports.
	   @see #setCharset
	**/
	public String getCharset();

	/**
	   The character set this connection supports.
	   @param c The character set for this connection.
	   @see #getCharset
	**/
	public void setCharset(String c);

	/**
	   Return true if the charset is ASCII.
	   @return true if the character set ASCII.
	**/
	public boolean isASCII();

	/**
	   Return true if the charset is EBCIDIC.
	   @return true if the character set EBCIDIC.
	**/
	public boolean isEBCIDIC();
}
