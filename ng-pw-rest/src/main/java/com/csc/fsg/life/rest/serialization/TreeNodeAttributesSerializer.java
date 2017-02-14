package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import com.csc.fsg.life.rest.model.tree.TreeNodeAttributes;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TreeNodeAttributesSerializer
	extends StdSerializer<TreeNodeAttributes>
{
	static private final long serialVersionUID = -4102826862275668709L;

	public TreeNodeAttributesSerializer()
	{
		this(null);
	}

	public TreeNodeAttributesSerializer(Class<TreeNodeAttributes> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(TreeNodeAttributes value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		gen.writeStartObject();
		gen.writeBooleanField("disabled", value.isDisabled());
		gen.writeBooleanField("inquiryAllowed", value.isInquiryAllowed());
		gen.writeBooleanField("updateAllowed", value.isUpdateAllowed());
		gen.writeEndObject();
	}
}
