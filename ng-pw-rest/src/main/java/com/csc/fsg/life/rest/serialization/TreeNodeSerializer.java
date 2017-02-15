package com.csc.fsg.life.rest.serialization;

import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;

import com.csc.fsg.life.rest.model.tree.PlanKey;
import com.csc.fsg.life.rest.model.tree.TreeNode;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class TreeNodeSerializer
	extends StdSerializer<TreeNode>
{
	static private final long serialVersionUID = -4102826862275668709L;

	public TreeNodeSerializer()
	{
		this(null);
	}

	public TreeNodeSerializer(Class<TreeNode> clz)
	{
		super(clz);
	}

	@Override
	public void serialize(TreeNode value, JsonGenerator gen, SerializerProvider provider)
		throws IOException, JsonProcessingException
	{
		gen.writeStartObject();
		gen.writeStringField("type", value.getType().toString());

		String display = value.getDisplay();
		if (StringUtils.hasText(display))
			gen.writeStringField("display", display);

		String envId = value.getEnvId();
		if (StringUtils.hasText(envId))
			gen.writeStringField("envId", envId);

		String companyCode = value.getCompanyCode();
		if (StringUtils.hasText(companyCode))
			gen.writeStringField("companyCode", companyCode);

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

		PlanKey planKey = value.getPlanKey();
		if (planKey != null && !planKey.isEmpty())
			gen.writeObjectField("planKey", planKey);

		gen.writeObjectField("attributes", value.getAttributes());

		List<TreeNode> children = value.getChildren();
		if (!children.isEmpty())
			gen.writeObjectField("children", children);

		gen.writeEndObject();
	}
}
