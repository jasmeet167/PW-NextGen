package com.csc.fsg.life.environments;


/**
   Base class for different connections.
**/
public abstract class AbstractConnection
	implements IEnvConnection
{
	/** Constant for EBCIDIC encoding **/
	public static final String EBCIDIC = "EBCIDIC";
	/** Constant for ASCII encoding **/
	public static final String ASCII = "ASCII";
	
	private String charset = EBCIDIC;
	
	/**
	   Returns the character set this connection supports.
	   @return the character set this connection supports.
	   @see #setCharset
	**/
	public String getCharset() { return charset; }

	/**
	   The character set this connection supports.
	   @param c The character set for this connection.
	   @see #getCharset
	**/
	public void setCharset(String c)
	{
		if (c != null)
			charset = c;
	}
	
	/**
	   Return true if the charset is ASCII.
	   @return true if the character set ASCII.
	**/
	public boolean isASCII() { return charset.equalsIgnoreCase(ASCII); }

	/**
	   Return true if the charset is EBCIDIC.
	   @return true if the character set EBCIDIC.
	**/
	public boolean isEBCIDIC() { return charset.equalsIgnoreCase(EBCIDIC); }
}
