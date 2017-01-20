package com.csc.fsg.life.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This utility is a type of ArrayList which provides methods to parse a String into an arrayList.
 */
@SuppressWarnings("unchecked")
public class StringParserList 
	extends ArrayList 
{
	private String s;
	private String delim = "\t";

	/**
	   Create an empty version of this utility object.
	**/
	public StringParserList() {
	}

	/** 
		Create an instance of this object with a starting string. 
		@param s The string.
	**/
	public StringParserList(String s) {
		this.s = s;
	}

	/** 
		Create an instance of this object with a starting string 
		and delimiter.
		@param s The string.
		@param delim The delimiter used to break the string into tokens.
	**/
	public StringParserList(String s, String delim) {
		this.s = s;
		this.delim = delim;
	}

	/**
	   Method unstring parses the supplied s and adds it to itself using the supplied delimiter.
	   If no delimiter is supplied, tab will be default.
	 */
	public void unstring() throws Exception {
	
		int start = 0, end = 0;

		if (s == null) {
			throw new Exception("String not supplied");
		}
		while (true) { 
			end = s.indexOf(delim, start);
			if (end == -1) {
				this.add(s.substring(start));
				break;
			}
			this.add(s.substring(start, end));
			start = ++end;
		}
	}

	/**
	   Adds each line in the read to the list.
	   @param br a reader with lines of text to be put in a list.
	**/
	public void unstring(BufferedReader br) throws IOException {
		while (true) {
			String line = br.readLine();
			if (line != null) {
				this.add(line);
			} else {
				break;
			}
		}
	}

	/**
	 * Returns the delim.
	 * @return the delimiter.
	 */
	public String getDelim() {
		return delim;
	}

	/**
	 * Returns the string
	 * @return the string.
	 */
	public String getLine() {
		return s;
	}

	/**
	 * Sets the delimiter for breaking up the string.
	 * @param delim The delim to set
	 */
	public void setDelim(String delim) {
		this.delim = delim;
	}

	/**
	 * Sets the s.
	 * @param s The s to set
	 */
	public void setString(String s) {
		this.s = s;
	}

}
