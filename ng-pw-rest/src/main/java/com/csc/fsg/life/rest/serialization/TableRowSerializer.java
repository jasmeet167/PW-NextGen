package com.csc.fsg.life.rest.serialization;

import java.io.IOException;
import java.util.List;

import com.csc.fsg.life.rest.model.table.TableRow;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TableRowSerializer
	extends StdSerializer<TableRow>
{
	private static final long serialVersionUID = -5669308384687496697L;

	public TableRowSerializer()
	{
		this(null);
	}

	public TableRowSerializer(Class<TableRow> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(TableRow value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		gen.writeStartObject();
		gen.writeArrayFieldStart("cellValues");

		List<String> cellValues = value.getCellValues();
		for (String cellValue : cellValues)
			gen.writeString(cellValue.trim());

		gen.writeEndArray();
		gen.writeEndObject();
	}
}
