package com.csc.fsg.life.rest.model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csc.fsg.life.pw.client.tree.CscTreeNode;
import com.csc.fsg.life.pw.common.util.Constants;

public class TreeNode
	implements Serializable
{
	static private final long serialVersionUID = 3104285458626875161L;

	public enum NodeType {
		DISPLAY, PACKAGE, PROJECT, COMPANY, ANNUITIY_FOLDER, UNIV_LIFE_FOLDER, TRADITIONAL_FOLDER, COMMON_TABLE_FOLDER, PLAN_FOLDER, RIDER_FOLDER, PLAN, RIDER, TABLE, COMMON_TABLE, TABLE_SUBSET, PDFPLAN_FOLDER, ORPHAN_FOLDER, COMMON_FOLDER, ORPHAN_TABLE_SUBSET, PAYOUT_PLAN, PAYOUTPLAN_FOLDER, ORPHAN_GROUP
	}

	private String environmentId = null;
	private int nodeId = 0;
	private short level = 0;
	private NodeType type = null;
	private TreeNodeAttributes attributes = TreeNodeAttributes.defaultInstance();
	private String display = null;
	private String companyCode = null;

	private String tableId = null;
	private String packageId = null;
	private String projectName = null;
	private String name = null;
	
	
	private PlanKey planKey = new PlanKey();
	private List<TreeNode> children = new ArrayList<>();

	public TreeNode()
	{
		level = -1;
	}

	public TreeNode(String environmentId, String payload)
	{
		this.environmentId = environmentId;

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
				planKey.setValues(Arrays.copyOfRange(payloadComponents, i++, payloadComponents.length));
				planKey.setEnvironmentId(environmentId);
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
				planKey.setValues(payloadComponents[i++].split("\\|"));
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

	private NodeType getNodeType(short type)
	{
		switch (type) {
			case CscTreeNode.NODE_DISPLAY:
				return NodeType.DISPLAY;
			case CscTreeNode.NODE_PACKAGE:
				return NodeType.PACKAGE;
			case CscTreeNode.NODE_PROJECT:
				return NodeType.PROJECT;
			case CscTreeNode.NODE_COMPANY:
				return NodeType.COMPANY;
			case CscTreeNode.NODE_ANNUITIY_FOLDER:
				return NodeType.ANNUITIY_FOLDER;
			case CscTreeNode.NODE_UNIV_LIFE_FOLDER:
				return NodeType.UNIV_LIFE_FOLDER;
			case CscTreeNode.NODE_TRADITIONAL_FOLDER:
				return NodeType.TRADITIONAL_FOLDER;
			case CscTreeNode.NODE_COMMON_TABLE_FOLDER:
				return NodeType.COMMON_TABLE_FOLDER;
			case CscTreeNode.NODE_PLAN_FOLDER:
				return NodeType.PLAN_FOLDER;
			case CscTreeNode.NODE_RIDER_FOLDER:
				return NodeType.RIDER_FOLDER;
			case CscTreeNode.NODE_PLAN:
				return NodeType.PLAN;
			case CscTreeNode.NODE_RIDER:
				return NodeType.RIDER;
			case CscTreeNode.NODE_TABLE:
				return NodeType.TABLE;
			case CscTreeNode.NODE_COMMON_TABLE:
				return NodeType.COMMON_TABLE;
			case CscTreeNode.NODE_TABLE_SUBSET:
				return NodeType.TABLE_SUBSET;
			case CscTreeNode.NODE_PDFPLAN_FOLDER:
				return NodeType.PDFPLAN_FOLDER;
			case CscTreeNode.NODE_ORPHAN_FOLDER:
				return NodeType.ORPHAN_FOLDER;
			case CscTreeNode.NODE_COMMON_FOLDER:
				return NodeType.COMMON_FOLDER;
			case CscTreeNode.NODE_ORPHAN_TABLE_SUBSET:
				return NodeType.ORPHAN_TABLE_SUBSET;
			case CscTreeNode.NODE_PAYOUT_PLAN:
				return NodeType.PAYOUT_PLAN;
			case CscTreeNode.NODE_PAYOUTPLAN_FOLDER:
				return NodeType.PAYOUTPLAN_FOLDER;
			case CscTreeNode.NODE_ORPHAN_GROUP:
				return NodeType.ORPHAN_GROUP;
			default:
				throw new IllegalArgumentException("Invalid Node Type detected: " + type);
		}
	}

	private TreeNodeAttributes parseNodeAttributes(int flags)
	{
		boolean isDisabled = (flags & CscTreeNode.ATTR_DISABLED) != 0;
		boolean isInquiryAllowed = (flags & CscTreeNode.ATTR_INQUIRY) != 0;
		boolean isUpdateAllowed = (flags & CscTreeNode.ATTR_UPDATE) != 0;

		return new TreeNodeAttributes(isDisabled, isInquiryAllowed, isUpdateAllowed);
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

	public void addChild(TreeNode child)
	{
		children.add(child);
	}

	public String getEnvironmentId()
	{
		if (type == NodeType.COMPANY) 
			return environmentId;
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

	public NodeType getType()
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

	public PlanKey getPlanKey()
	{
		return planKey;
	}

	public List<TreeNode> getChildren()
	{
		return children;
	}
}
