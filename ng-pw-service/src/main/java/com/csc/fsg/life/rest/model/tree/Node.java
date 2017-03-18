package com.csc.fsg.life.rest.model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.csc.fsg.life.pw.client.tree.CscTreeNode;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.rest.model.TreeNode.TypeEnum;
import com.csc.fsg.life.rest.model.TreeNodeAttributes;
import com.csc.fsg.life.rest.model.TreeNodeData;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;

public class Node
	implements Serializable
{
	static private final long serialVersionUID = 3104285458626875161L;

	private String envId = null;
	private String companyCode = null;
	private int nodeId = 0;
	private short level = 0;
	private TypeEnum type = null;
	private String display = null;

	private TreeNodeData data = new TreeNodeData();
	private TreeNodeAttributes attributes = new TreeNodeAttributes();
	private TreeNodePlanKey planKey = new TreeNodePlanKey();
	private List<Node> children = new ArrayList<>();

	public Node()
	{
		level = -1;
	}

	public Node(String envId, String payload)
	{
		data.setAttributes(attributes);
		data.setPlanKey(planKey);

		this.envId = envId;

		String[] payloadComponents = payload.split("\\t");
		int i = 0;

		nodeId = Integer.parseInt(payloadComponents[i++]);
		level = Short.parseShort(payloadComponents[i++]);
		type = getNodeType(Short.parseShort(payloadComponents[i++]));
		parseNodeAttributes(Integer.parseInt(payloadComponents[i++]));
		display = payloadComponents[i];

		if (payloadComponents.length > (i + 1))
			i++;

		switch (type) {
			case D: {		// DISPLAY
				break;
			}

			case PA: {		// PACKAGE
				data.setPackageId(payloadComponents[i++]);
				break;
			}

			case PR: {		// PROJECT
				data.setProjectName(payloadComponents[i++]);
				break;
			}

			case C: {		// COMPANY
				companyCode = payloadComponents[i++];
				break;
			}

			case AF:		// ANNUITIY_FOLDER
			case UF:		// UNIV_LIFE_FOLDER
			case TF:		// TRADITIONAL_FOLDER
			case PDF: {		// PDFPLAN_FOLDER
				String s = payloadComponents[i++];
				planKey.setProductPrefix(s.substring(0, 1));
				planKey.setProductSuffix(" ");
				setTableId(Constants.TABLE_ZERO_ID);
				data.setName(Constants.TABLE_ZERO_NAME);
				break;
			}

			case OF: {		// ORPHAN_FOLDER
				String s = payloadComponents[i++];
				if (s.length() > 0)
					planKey.setProductPrefix(s.substring(0, 1));
				if (s.length() > 1)
					planKey.setProductSuffix(s.substring(1, 2));
				break;
			}

			case CTF: {		// COMMON_TABLE_FOLDER
				i++;
				break;
			}

			case PF:		// PLAN_FOLDER
			case RF:		// RIDER_FOLDER
			case PPF: {		// PAYOUTPLAN_FOLDER
				String s = payloadComponents[i++];
				String planType = s.substring(0, 1);
				planKey.setPlanType(planType);
				break;
			}

			case P:			// PLAN
			case R:			// RIDER
			case PP: {		// PAYOUT_PLAN:
				PlanKeyPropertiesBuilder.buildValues(planKey, Arrays.copyOfRange(payloadComponents, i++, payloadComponents.length));
				setTableId(Constants.TABLE_ZERO_ID);
				data.setName(Constants.TABLE_ZERO_NAME);
				setTableId(Constants.TABLE_ZERO_ID);
				break;
			}

			case CF:		// COMMON_FOLDER
			case CT:		// COMMON_TABLE
			case T: {		// TABLE:
				data.setName(payloadComponents[i++]);
				setTableId(payloadComponents[i++]);
				break;
			}

			case TS: {		// TABLE_SUBSET
				data.setName(payloadComponents[i++]);
				setTableId(payloadComponents[i++]);
				planKey.setTablePtrVar(payloadComponents[i++]);
				planKey.setTablePtrSubset(payloadComponents[i++]);

				i++;
				PlanKeyPropertiesBuilder.buildValues(planKey, payloadComponents[i++].split("\\|"));
				break;
			}

			case OTS: {		// ORPHAN_TABLE_SUBSET
				data.setName(payloadComponents[i++]);
				setTableId(payloadComponents[i++]);
				planKey.setTablePtrVar(payloadComponents[i++]);
				planKey.setTablePtrSubset(payloadComponents[i++]);
				companyCode = (payloadComponents[i++]);
				planKey.setProductPrefix(payloadComponents[i++]);
				planKey.setProductSuffix("*");
				break;
			}

			case OG: {		// ORPHAN_GROUP
				companyCode = payloadComponents[i++];
				planKey.setProductPrefix(payloadComponents[i++].substring(0, 1));
				data.setName(payloadComponents[i++]);
				data.setTableId(payloadComponents[i++]);
				break;
			}
		}
	}

	private TypeEnum getNodeType(short type)
	{
		switch (type) {
			case CscTreeNode.NODE_DISPLAY:
				return TypeEnum.D;
			case CscTreeNode.NODE_PACKAGE:
				return TypeEnum.PA;
			case CscTreeNode.NODE_PROJECT:
				return TypeEnum.PR;
			case CscTreeNode.NODE_COMPANY:
				return TypeEnum.C;
			case CscTreeNode.NODE_ANNUITIY_FOLDER:
				return TypeEnum.AF;
			case CscTreeNode.NODE_UNIV_LIFE_FOLDER:
				return TypeEnum.UF;
			case CscTreeNode.NODE_TRADITIONAL_FOLDER:
				return TypeEnum.TF;
			case CscTreeNode.NODE_COMMON_TABLE_FOLDER:
				return TypeEnum.CTF;
			case CscTreeNode.NODE_PLAN_FOLDER:
				return TypeEnum.PF;
			case CscTreeNode.NODE_RIDER_FOLDER:
				return TypeEnum.RF;
			case CscTreeNode.NODE_PLAN:
				return TypeEnum.P;
			case CscTreeNode.NODE_RIDER:
				return TypeEnum.R;
			case CscTreeNode.NODE_TABLE:
				return TypeEnum.T;
			case CscTreeNode.NODE_COMMON_TABLE:
				return TypeEnum.CT;
			case CscTreeNode.NODE_TABLE_SUBSET:
				return TypeEnum.TS;
			case CscTreeNode.NODE_PDFPLAN_FOLDER:
				return TypeEnum.PDF;
			case CscTreeNode.NODE_ORPHAN_FOLDER:
				return TypeEnum.OF;
			case CscTreeNode.NODE_COMMON_FOLDER:
				return TypeEnum.CF;
			case CscTreeNode.NODE_ORPHAN_TABLE_SUBSET:
				return TypeEnum.OTS;
			case CscTreeNode.NODE_PAYOUT_PLAN:
				return TypeEnum.PP;
			case CscTreeNode.NODE_PAYOUTPLAN_FOLDER:
				return TypeEnum.PPF;
			case CscTreeNode.NODE_ORPHAN_GROUP:
				return TypeEnum.OG;
			default:
				throw new IllegalArgumentException("Invalid Node Type detected: " + type);
		}
	}

	private void parseNodeAttributes(int flags)
	{
		boolean isDisabled = (flags & CscTreeNode.ATTR_DISABLED) != 0;
		boolean isInquiryAllowed = (flags & CscTreeNode.ATTR_INQUIRY) != 0;
		boolean isUpdateAllowed = (flags & CscTreeNode.ATTR_UPDATE) != 0;

		attributes.setDisabled(Boolean.valueOf(isDisabled));
		attributes.setInquiryAllowed(Boolean.valueOf(isInquiryAllowed));
		attributes.setUpdateAllowed(Boolean.valueOf(isUpdateAllowed));
	}

	private void setTableId(String tableId)
	{
		data.setTableId(tableId);
		if (!tableId.equals(Constants.TABLE_ZERO_ID)) {
			planKey.setTablePtrId(tableId);
		}
	}

	public String getEnvId()
	{
		return envId;
	}

	public String getCompanyCode()
	{
		return companyCode;
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

	public String getDisplay()
	{
		return display;
	}

	public TreeNodeData getData()
	{
		return data;
	}

	public void addChild(Node child)
	{
		children.add(child);
	}

	public List<Node> getChildren()
	{
		return children;
	}
}
