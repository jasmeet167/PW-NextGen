package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateDeserializer
	extends StdDeserializer<LocalDate>
{
	static private final long serialVersionUID = -3174883800314762410L;

	public LocalDateDeserializer()
	{
		this(null);
	}

	public LocalDateDeserializer(Class<LocalDate> clz)
	{
		super(clz);
	}

	@Override
	public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
		throws IOException, JsonProcessingException
	{
		ObjectCodec oc = p.getCodec();
		JsonNode node = oc.readTree(p);
		String serializedValue = node.textValue();

		LocalDate date = LocalDate.parse(serializedValue);
		return date;
	}
}
