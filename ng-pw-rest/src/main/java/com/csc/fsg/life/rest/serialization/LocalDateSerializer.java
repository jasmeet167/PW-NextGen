package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LocalDateSerializer
	extends StdSerializer<LocalDate>
{
	static private final long serialVersionUID = -4102826862275668709L;

	public LocalDateSerializer()
	{
		this(null);
	}

	public LocalDateSerializer(Class<LocalDate> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		// gen.writeStartObject();
		// gen.writeStringField("fieldName", value);
		// gen.writeNumberField("fieldName", value);
		// gen.writeEndObject();

		gen.writeString(value.toString());
	}
}