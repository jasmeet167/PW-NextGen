package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateTimeSerializer
	extends StdSerializer<DateTime>
{
	static private final long serialVersionUID = -44013028563086920L;

	public DateTimeSerializer()
	{
		this(null);
	}

	public DateTimeSerializer(Class<DateTime> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(DateTime value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		DateTimeFormatter formatter = ISODateTimeFormat.dateTime();
		gen.writeString(formatter.print(value));
	}
}
