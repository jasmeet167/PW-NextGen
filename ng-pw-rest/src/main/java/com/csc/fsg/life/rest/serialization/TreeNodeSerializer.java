package com.csc.fsg.life.rest.serialization;

import java.io.IOException;
import java.util.List;

import org.springframework.util.StringUtils;

import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNode.TypeEnum;
import com.csc.fsg.life.rest.model.TreeNodeData;
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

		TypeEnum type = value.getType();
		if (type != null)
			gen.writeStringField("type", type.toString());

		String label = value.getLabel();
		if (StringUtils.hasText(label))
			gen.writeStringField("label", label);

		String styleClass = value.getStyleClass();
		if (StringUtils.hasText(styleClass))
			gen.writeStringField("styleClass", styleClass);

		String icon = value.getIcon();
		if (StringUtils.hasText(icon))
			gen.writeStringField("icon", icon);

		String expandedIcon = value.getExpandedIcon();
		if (StringUtils.hasText(expandedIcon))
			gen.writeStringField("expandedIcon", expandedIcon);

		String collapsedIcon = value.getCollapsedIcon();
		if (StringUtils.hasText(collapsedIcon))
			gen.writeStringField("collapsedIcon", collapsedIcon);

		Boolean isLeaf = value.getLeaf();
		if (isLeaf != null)
			gen.writeBooleanField("leaf", isLeaf.booleanValue());

		Boolean isExpanded = value.getExpanded();
		if (isExpanded != null)
			gen.writeBooleanField("expanded", isExpanded.booleanValue());

		TreeNodeData data = value.getData();
		if (!TreeNodeDataSerializer.isEmptyData(data))
			gen.writeObjectField("data", data);

		List<TreeNode> children = value.getChildren();
		if (!children.isEmpty())
			gen.writeObjectField("children", children);

		gen.writeEndObject();
	}
}
