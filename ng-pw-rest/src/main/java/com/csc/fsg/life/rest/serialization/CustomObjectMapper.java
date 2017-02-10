package com.csc.fsg.life.rest.serialization;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class CustomObjectMapper
	extends ObjectMapper
{
	static private final long serialVersionUID = -4512243051257311710L;

	public CustomObjectMapper()
	{
		SimpleModule module = new SimpleModule("JSONModule", new Version(2, 0, 0, null, null, null));
		module.addSerializer(LocalDate.class, new LocalDateSerializer());
		module.addDeserializer(LocalDate.class, new LocalDateDeserializer());
		module.addSerializer(DateTime.class, new DateTimeSerializer());
		module.addDeserializer(DateTime.class, new DateTimeDeserializer());
		registerModule(module);
	}
}
