package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import org.joda.time.LocalDate;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.rest.model.tree.PlanKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class PlanKeySerializer
	extends StdSerializer<PlanKey>
{
	static private final long serialVersionUID = -4102826862275668709L;

	public PlanKeySerializer()
	{
		this(null);
	}

	public PlanKeySerializer(Class<PlanKey> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(PlanKey value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		gen.writeStartObject();

		String envId = value.getEnvId();
		if (StringUtils.hasText(envId))
			gen.writeStringField("envId", envId);

		String companyCode = value.getCompanyCode();
		if (StringUtils.hasText(companyCode))
			gen.writeStringField("companyCode", companyCode);

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

		String lineOfBusiness = value.getLineOfBusiness();
		if (StringUtils.hasText(lineOfBusiness))
			gen.writeStringField("lineOfBusiness", lineOfBusiness);

		LocalDate effectiveDate = value.getEffectiveDate();
		if (effectiveDate != null)
			gen.writeObjectField("effectiveDate", effectiveDate);

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