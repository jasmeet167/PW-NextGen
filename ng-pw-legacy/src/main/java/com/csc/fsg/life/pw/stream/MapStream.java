package com.csc.fsg.life.pw.stream;

import java.util.*;
import com.csc.fsg.life.pw.util.IOTokenizer;

/* Modifications: T0103, T0091, T0120 */
// T0103 - change to use IOTokenizer, add stream constructor

/**
 * Communication Stream wrapper for a Map. Serializes/De-serializes a
 * Map(String, String).
 */
public class MapStream implements IPWStream {

	private static final String KVDELIM = "~";

	private static final String MAPDELIM = "\t";

	private TreeMap<String, String> _map = new TreeMap<String, String>();

	public MapStream() {

	}

	public MapStream(String stream) {
		this();
		fromStream(stream);
	}
	
	public MapStream(Map<String, String> map)
	{
		_map.putAll(map);
	}

	public Map<String, String> getMap() {
		return _map;
	}

	public void put(String key, String value) {
		_map.put(key, value);
	}

	public String toStream() {
		StringBuffer stream = new StringBuffer();
		Set maps = _map.entrySet();
		Iterator it = maps.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			stream.append(entry.getKey());
			stream.append(KVDELIM);
			stream.append(entry.getValue());
			if (it.hasNext())
				stream.append(MAPDELIM);
		}
		return stream.toString();
	}

	public void fromStream(String stream) {
		_map.clear();
		// T0120 - check for empty stream
		if ( stream.trim().length() > 0 ) {
			IOTokenizer maps = new IOTokenizer(stream, MAPDELIM);
			while (maps.hasMoreTokens()) {
				String map = maps.nextToken();
				IOTokenizer kv = new IOTokenizer(map, KVDELIM);
				_map.put(kv.nextToken(), kv.nextToken());
			}
		}
	}

}
