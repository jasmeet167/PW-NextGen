package com.csc.fsg.life.rest.serialization;

import java.io.IOException;

import org.springframework.util.StringUtils;

import com.csc.fsg.life.rest.model.TreeNodeAttributes;
import com.csc.fsg.life.rest.model.TreeNodeData;
import com.csc.fsg.life.rest.model.TreeNodeData.LazyTypeEnum;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TreeNodeDataSerializer
	extends StdSerializer<TreeNodeData>
{
	static private final long serialVersionUID = 2885007969547027736L;

	public TreeNodeDataSerializer()
	{
		this(null);
	}

	public TreeNodeDataSerializer(Class<TreeNodeData> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(TreeNodeData value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		gen.writeStartObject();

		Boolean isLazyNode = value.getLazyNode();
		if (isLazyNode != null)
			gen.writeBooleanField("lazyNode", isLazyNode.booleanValue());

		LazyTypeEnum lazyType = value.getLazyType();
		if (lazyType != null)
			gen.writeStringField("lazyType", lazyType.toString());

		String name = value.getName();
		if (StringUtils.hasText(name))
			gen.writeStringField("name", name);

		String tableId = value.getTableId();
		if (StringUtils.hasText(tableId))
			gen.writeStringField("tableId", tableId);

		String projectName = value.getProjectName();
		if (StringUtils.hasText(projectName))
			gen.writeStringField("projectName", projectName);

		String packageId = value.getPackageId();
		if (StringUtils.hasText(packageId))
			gen.writeStringField("packageId", packageId);

		TreeNodePlanKey planKey = value.getPlanKey();
		if (planKey != null && !isEmpty(planKey))
			gen.writeObjectField("planKey", planKey);

		TreeNodeAttributes attributes = value.getAttributes();
		if (attributes != null && !isEmpty(attributes))
			gen.writeObjectField("attributes", attributes);

		gen.writeEndObject();
	}

	static public boolean isEmptyData(TreeNodeData data)
	{
		if (data == null)
			return true;

		if (data.getLazyNode() != null)
			return false;
		if (data.getLazyType() != null)
			return false;
		if (StringUtils.hasText(data.getName()))
			return false;
		if (StringUtils.hasText(data.getTableId()))
			return false;
		if (StringUtils.hasText(data.getProjectName()))
			return false;
		if (StringUtils.hasText(data.getPackageId()))
			return false;

		TreeNodePlanKey planKey = data.getPlanKey();
		if (planKey != null && !isEmpty(planKey))
			return false;

		TreeNodeAttributes attributes = data.getAttributes();
		if (attributes != null && !isEmpty(attributes))
			return false;

		return true;
	}

	static private boolean isEmpty(TreeNodePlanKey planKey)
	{
		if (planKey == null)
			return true;

		if (StringUtils.hasText(planKey.getProductPrefix()))
			return false;
		if (StringUtils.hasText(planKey.getProductSuffix()))
			return false;
		if (StringUtils.hasText(planKey.getPlanCode()))
			return false;
		if (StringUtils.hasText(planKey.getIssueState()))
			return false;
		if (StringUtils.hasText(planKey.getLob()))
			return false;
		if (planKey.getEffDate() != null)
			return false;
		if (StringUtils.hasText(planKey.getPlanType()))
			return false;
		if (StringUtils.hasText(planKey.getTablePtrId()))
			return false;
		if (StringUtils.hasText(planKey.getTablePtrVar()))
			return false;
		if (StringUtils.hasText(planKey.getTablePtrSubset()))
			return false;

		return true;
	}

	static private boolean isEmpty(TreeNodeAttributes attributes)
	{
		if (attributes.getDisabled() != null)
			return false;
		if (attributes.getUpdateAllowed() != null)
			return false;

		return true;
	}
}
