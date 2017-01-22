package com.csc.fsg.life.pw.common.stream;

import java.util.*;
import com.csc.fsg.life.pw.common.util.IOTokenizer;

/* Modifications: T0103, T0091 */
// T0103 - change to use IOTokenizer and change DELIM

/**
 * Communication Stream wrapper for a List. Serializes/De-serializes a list of
 * String.
 */
public class ListStream implements IPWStream {

	private static final String DELIM = "\n";

	private ArrayList<String> _list = new ArrayList<String>();

	public ListStream() {

	}

	public ListStream(List<String> list) {
		_list.addAll(list);
	}

	public List<String> getList() {
		return _list;
	}

	public void add(String s) {
		_list.add(s);
	}

	public String toStream() {
		StringBuffer stream = new StringBuffer();
		Iterator<String> it = _list.iterator();
		while (it.hasNext()) {
			String entry = it.next();
			stream.append(entry);
			if (it.hasNext())
				stream.append(DELIM);
		}
		return stream.toString();
	}

	public void fromStream(String stream) {
		_list.clear();
		IOTokenizer entries = new IOTokenizer(stream, DELIM);
		while (entries.hasMoreTokens()) {
			_list.add(entries.nextToken());
		}
	}

}
