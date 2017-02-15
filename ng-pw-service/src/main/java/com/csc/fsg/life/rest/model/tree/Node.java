package com.csc.fsg.life.rest.model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csc.fsg.life.pw.client.tree.CscTreeNode;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.rest.model.TreeNode.TypeEnum;
import com.csc.fsg.life.rest.model.TreeNodeAttributes;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;

public class Node
	implements Serializable
{
	static private final long serialVersionUID = 3104285458626875161L;

	private String envId = null;
	private int nodeId = 0;
	private short level = 0;
	private TypeEnum type = null;
	private TreeNodeAttributes attributes = new TreeNodeAttributes();
	private String display = null;
	private String companyCode = null;

	private String tableId = null;
	private String packageId = null;
	private String projectName = null;
	private String name = null;
	private TreeNodePlanKey planKey = new TreeNodePlanKey();
	private List<Node> children = new ArrayList<>();

	public Node()
	{
		level = -1;
	}

	public Node(String envId, String payload)
	{
		this.envId = envId;

		String[] payloadComponents = payload.split("\\t");
		int i = 0;

		nodeId = Integer.parseInt(payloadComponents[i++]);
		level = Short.parseShort(payloadComponents[i++]);
		type = getNodeType(Short.parseShort(payloadComponents[i++]));
		attributes = parseNodeAttributes(Integer.parseInt(payloadComponents[i++]));
		display = payloadComponents[i];

		if (payloadComponents.length > (i + 1))
			i++;

		switch (type) {
			case DISPLAY: {
				break;
			}

			case PACKAGE: {
				packageId = payloadComponents[i++];
				break;
			}

			case PROJECT: {
				projectName = payloadComponents[i++];
				break;
			}

			case COMPANY: {
				planKey.setCompanyCode(payloadComponents[i++]);
				break;
			}

			case ANNUITIY_FOLDER:
			case UNIV_LIFE_FOLDER:
			case TRADITIONAL_FOLDER:
			case PDFPLAN_FOLDER: {
				String s = payloadComponents[i++];
				planKey.setProductPrefix(s.substring(0, 1));
				planKey.setProductSuffix(" ");
				setTableId(Constants.TABLE_ZERO_ID);
				name = Constants.TABLE_ZERO_NAME;
				break;
			}

			case ORPHAN_FOLDER: {
				String s = payloadComponents[i++];
				planKey.setProductPrefix(s.substring(0, 1));
				planKey.setProductSuffix(s.substring(1, 2));
				break;
			}

			case COMMON_TABLE_FOLDER: {
				i++;
				break;
			}

			case PLAN_FOLDER:
			case RIDER_FOLDER:
			case PAYOUTPLAN_FOLDER: {
				String s = payloadComponents[i++];
				String planType = s.substring(0, 1);
				planKey.setPlanType(planType);
				break;
			}

			case PLAN:
			case RIDER:
			case PAYOUT_PLAN: {
				PlanKeyPropertiesBuilder.buildValues(planKey, Arrays.copyOfRange(payloadComponents, i++, payloadComponents.length));
				planKey.setEnvId(envId);
				setTableId(Constants.TABLE_ZERO_ID);
				name = Constants.TABLE_ZERO_NAME;
				tableId = Constants.TABLE_ZERO_ID;
				break;
			}

			case COMMON_FOLDER:
			case COMMON_TABLE:
			case TABLE: {
				name = payloadComponents[i++];
				setTableId(payloadComponents[i++]);
				break;
			}

			case TABLE_SUBSET: {
				name = payloadComponents[i++];
				setTableId(payloadComponents[i++]);
				setTableVariance(payloadComponents[i++]);
				setTableSubset(payloadComponents[i++]);

				i++;
				PlanKeyPropertiesBuilder.buildValues(planKey, payloadComponents[i++].split("\\|"));
				break;
			}

			case ORPHAN_TABLE_SUBSET: {
				name = payloadComponents[i++];
				setTableId(payloadComponents[i++]);
				setTableVariance(payloadComponents[i++]);
				setTableSubset(payloadComponents[i++]);
				companyCode = (payloadComponents[i++]);
				setProductPrefix(payloadComponents[i++]);
				setProductSuffix("*");
				break;
			}

			case ORPHAN_GROUP: {
				setCompanyCode(payloadComponents[i++]);
				setProductPrefix(payloadComponents[i++].substring(0, 1));
				name = payloadComponents[i++];
				tableId = payloadComponents[i++];
				break;
			}
		}
	}

	private TypeEnum getNodeType(short type)
	{
		switch (type) {
			case CscTreeNode.NODE_DISPLAY:
				return TypeEnum.DISPLAY;
			case CscTreeNode.NODE_PACKAGE:
				return TypeEnum.PACKAGE;
			case CscTreeNode.NODE_PROJECT:
				return TypeEnum.PROJECT;
			case CscTreeNode.NODE_COMPANY:
				return TypeEnum.COMPANY;
			case CscTreeNode.NODE_ANNUITIY_FOLDER:
				return TypeEnum.ANNUITIY_FOLDER;
			case CscTreeNode.NODE_UNIV_LIFE_FOLDER:
				return TypeEnum.UNIV_LIFE_FOLDER;
			case CscTreeNode.NODE_TRADITIONAL_FOLDER:
				return TypeEnum.TRADITIONAL_FOLDER;
			case CscTreeNode.NODE_COMMON_TABLE_FOLDER:
				return TypeEnum.COMMON_TABLE_FOLDER;
			case CscTreeNode.NODE_PLAN_FOLDER:
				return TypeEnum.PLAN_FOLDER;
			case CscTreeNode.NODE_RIDER_FOLDER:
				return TypeEnum.RIDER_FOLDER;
			case CscTreeNode.NODE_PLAN:
				return TypeEnum.PLAN;
			case CscTreeNode.NODE_RIDER:
				return TypeEnum.RIDER;
			case CscTreeNode.NODE_TABLE:
				return TypeEnum.TABLE;
			case CscTreeNode.NODE_COMMON_TABLE:
				return TypeEnum.COMMON_TABLE;
			case CscTreeNode.NODE_TABLE_SUBSET:
				return TypeEnum.TABLE_SUBSET;
			case CscTreeNode.NODE_PDFPLAN_FOLDER:
				return TypeEnum.PDFPLAN_FOLDER;
			case CscTreeNode.NODE_ORPHAN_FOLDER:
				return TypeEnum.ORPHAN_FOLDER;
			case CscTreeNode.NODE_COMMON_FOLDER:
				return TypeEnum.COMMON_FOLDER;
			case CscTreeNode.NODE_ORPHAN_TABLE_SUBSET:
				return TypeEnum.ORPHAN_TABLE_SUBSET;
			case CscTreeNode.NODE_PAYOUT_PLAN:
				return TypeEnum.PAYOUT_PLAN;
			case CscTreeNode.NODE_PAYOUTPLAN_FOLDER:
				return TypeEnum.PAYOUTPLAN_FOLDER;
			case CscTreeNode.NODE_ORPHAN_GROUP:
				return TypeEnum.ORPHAN_GROUP;
			default:
				throw new IllegalArgumentException("Invalid Node Type detected: " + type);
		}
	}

	private TreeNodeAttributes parseNodeAttributes(int flags)
	{
		boolean isDisabled = (flags & CscTreeNode.ATTR_DISABLED) != 0;
		boolean isInquiryAllowed = (flags & CscTreeNode.ATTR_INQUIRY) != 0;
		boolean isUpdateAllowed = (flags & CscTreeNode.ATTR_UPDATE) != 0;

		TreeNodeAttributes attributes = new TreeNodeAttributes();
		attributes.setDisabled(Boolean.valueOf(isDisabled));
		attributes.setInquiryAllowed(Boolean.valueOf(isInquiryAllowed));
		attributes.setUpdateAllowed(Boolean.valueOf(isUpdateAllowed));

		return attributes;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
		if (!tableId.equals(Constants.TABLE_ZERO_ID)) {
			planKey.setTablePtrId(tableId);
		}
	}

	public void setCompanyCode(String cmpnyCode)
	{
		planKey.setCompanyCode(cmpnyCode);
	}

	public void setTableVariance(String s)
	{
		planKey.setTablePtrVar(s);
	}

	public void setTableSubset(String s)
	{
		planKey.setTablePtrSubset(s);
	}

	public void setProductPrefix(String s)
	{
		planKey.setProductPrefix(s);
	}

	public void setProductSuffix(String s)
	{
		planKey.setProductSuffix(s);
	}

	public void addChild(Node child)
	{
		children.add(child);
	}

	public String getEnvId()
	{
		if (type == TypeEnum.COMPANY)
			return envId;
		else
			return null;
	}

	public int getNodeId()
	{
		return nodeId;
	}

	public short getLevel()
	{
		return level;
	}

	public TypeEnum getType()
	{
		return type;
	}

	public TreeNodeAttributes getAttributes()
	{
		return attributes;
	}

	public String getDisplay()
	{
		return display;
	}

	public String getCompanyCode()
	{
		return companyCode;
	}

	public String getTableId()
	{
		return tableId;
	}

	public String getPackageId()
	{
		return packageId;
	}

	public String getProjectName()
	{
		return projectName;
	}

	public String getName()
	{
		return name;
	}

	public TreeNodePlanKey getPlanKey()
	{
		return planKey;
	}

	public List<Node> getChildren()
	{
		return children;
	}
}
