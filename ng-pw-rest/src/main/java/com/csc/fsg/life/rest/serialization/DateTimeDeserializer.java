package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class DateTimeDeserializer
	extends StdDeserializer<DateTime>
{
	static private final long serialVersionUID = 4307273411196245477L;

	public DateTimeDeserializer()
	{
		this(null);
	}

	public DateTimeDeserializer(Class<DateTime> clz)
	{
		super(clz);
	}

	@Override
	public DateTime deserialize(JsonParser p, DeserializationContext ctxt)
		throws IOException, JsonProcessingException
	{
		ObjectCodec oc = p.getCodec();
		JsonNode node = oc.readTree(p);
		String serializedValue = node.textValue();

		DateTime date = new DateTime(serializedValue);
		return date;
	}
}
