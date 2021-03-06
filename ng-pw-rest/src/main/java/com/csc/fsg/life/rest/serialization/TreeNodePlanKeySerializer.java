package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TreeNodePlanKeySerializer
	extends StdSerializer<TreeNodePlanKey>
{
	static private final long serialVersionUID = -4102826862275668709L;

	public TreeNodePlanKeySerializer()
	{
		this(null);
	}

	public TreeNodePlanKeySerializer(Class<TreeNodePlanKey> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(TreeNodePlanKey value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		gen.writeStartObject();

		String productPrefix = value.getProductPrefix();
		if (StringUtils.hasText(productPrefix))
			gen.writeStringField("productPrefix", productPrefix);

		String productSuffix = value.getProductSuffix();
		if (StringUtils.hasText(productSuffix))
			gen.writeStringField("productSuffix", productSuffix);

		String planCode = value.getPlanCode();
		if (StringUtils.hasText(planCode))
			gen.writeStringField("planCode", planCode);

		String issueState = value.getIssueState();
		if (StringUtils.hasText(issueState))
			gen.writeStringField("issueState", issueState);

		String lob = value.getLob();
		if (StringUtils.hasText(lob))
			gen.writeStringField("lob", lob);

		LocalDate effDate = value.getEffDate();
		if (effDate != null)
			gen.writeObjectField("effDate", effDate);

		String planType = value.getPlanType();
		if (StringUtils.hasText(planType))
			gen.writeStringField("planType", planType);

		String tablePtrId = value.getTablePtrId();
		if (StringUtils.hasText(tablePtrId))
			gen.writeStringField("tablePtrId", tablePtrId);

		String tablePtrVar = value.getTablePtrVar();
		if (StringUtils.hasText(tablePtrVar))
			gen.writeStringField("tablePtrVar", tablePtrVar);

		String tablePtrSubset = value.getTablePtrSubset();
		if (StringUtils.hasText(tablePtrSubset))
			gen.writeStringField("tablePtrSubset", tablePtrSubset);

		gen.writeEndObject();
	}
}
