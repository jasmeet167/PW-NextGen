package com.csc.fsg.life.pw.stream;

/**
 * Interface supported by all PW Communication Stream classes
 */
public interface IPWStream {
	/**
	 * Serialize this object to a stream (String)
	 * 
	 * @return String representation of object
	 */
	public String toStream();

	/**
	 * De-serialize this object from the given stream
	 * 
	 * @param serialized
	 *            stream from previous toStream call
	 */
	public void fromStream(String stream);
}
