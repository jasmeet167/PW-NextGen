/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.client.tree;

import java.awt.datatransfer.DataFlavor;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Set;

import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.commons.logging.Log;

//import com.csc.fsg.life.pw.client.PWClientLogManager;
//import com.csc.fsg.life.pw.client.ProductWizardApplet;
//import com.csc.fsg.life.pw.client.tree.dnd.DndDragSourceRules;
import com.csc.fsg.life.pw.common.*;
import com.csc.fsg.life.pw.common.rules.SpecialHandling;
import com.csc.fsg.life.pw.common.transferobjects.PlanRowTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.util.Constants;

/**
 * Class CscTreeNode
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

/* Modifications: ENH761, T0103, ENH961, WMABASEIXI-3750,T0120, WMABASEIXI-4515, WMABASEIXI-4699, WMABASEIXI-4702 */

public final class CscTreeNode extends DefaultMutableTreeNode implements Cloneable {

	public final static String TABLE_SUBSET = "table_subset";

	public final static String[] NODE_TYPES = { "environment", "package",
	        "project", "company", "annuity-folder", "universal-life-folder",
	        "traditional-folder", "common-table-folder", "plan-folder",
	        "rider-folder", "plan", "rider", "table", "common-table",
	        "table-subset", "pdf-plan-folder", "orphan", "common-folder",
	        "orphan-node",
	        "pointed-by-orphan", // WMA TT SPR#2
	        "dummy", "dummy", "dummy", "dummy", "payout-plan",
	        "payout-plan-folder" ,"orphan-group"};

	public final static int ATTR_DELETE_PLAN_OR_RIDER = 0x0001;

	public final static int ATTR_INSERT_TABLE_SUBSET = 0x0002;

	public final static int ATTR_DELETE_TABLE_SUBSET = 0x0004;

	public final static int ATTR_CLONE_PLAN_OR_RIDER = 0x0008;

	public final static int ATTR_READ_ONLY = 0x0010;

	public final static int ATTR_SHARED = 0x0020;

	public final static int ATTR_HAS_HELP_URL = 0x0040;

	public final static int ATTR_RENAMED_DISPLAY_VALUE = 0x0080;

	public final static int ATTR_HAS_AUDIT_INFO = 0x0100;

	public final static int ATTR_IS_NEWLY_ADDED = 0x0200;

	public final static int ATTR_UNSHARE = 0xFFDF;

	public final static int ATTR_INQUIRY = 0x1000;

	public final static int ATTR_UPDATE = 0x2000;

	// WMABASEIXI-4515
	public final static int ATTR_DISABLED = 0x4000;

	public final static short NODE_DISPLAY = 0;

	public final static short NODE_PACKAGE = 1;

	public final static short NODE_PROJECT = 2;

	public final static short NODE_COMPANY = 3;

	public final static short NODE_ANNUITIY_FOLDER = 4;

	public final static short NODE_UNIV_LIFE_FOLDER = 5;

	public final static short NODE_TRADITIONAL_FOLDER = 6;

	public final static short NODE_COMMON_TABLE_FOLDER = 7;

	public final static short NODE_PLAN_FOLDER = 8;

	public final static short NODE_RIDER_FOLDER = 9;

	public final static short NODE_PLAN = 10;

	public final static short NODE_RIDER = 11;

	public final static short NODE_TABLE = 12;

	public final static short NODE_COMMON_TABLE = 13;

	public final static short NODE_TABLE_SUBSET = 14;

	public final static short NODE_PDFPLAN_FOLDER = 15;

	public final static short NODE_ORPHAN_FOLDER = 16;

	public final static short NODE_COMMON_FOLDER = 17;
	
	public final static short NODE_ORPHAN_TABLE_SUBSET = 18;
	
	//public final static short NODE_POINTED_BY_ORPHAN = 19;

	public final static short NODE_PAYOUT_PLAN = 24; // WMA TT SPR#2

	public final static short NODE_PAYOUTPLAN_FOLDER = 25;
	
	public final static short NODE_ORPHAN_GROUP = 26;

//	private static Log _log = PWClientLogManager.getLog();

	public int _nodeId;

	public int _level;

	private short _type;

	private int _attributes;

	private boolean _dirty;

	private String _display;

	private String _name;

	private String _helpURL;

//	private String _subset;

	private String _oldSubset;

	// refactor plan key.
	private PlanRowTO planRow;
	/*
	private String _companyCode;

	private String _projectName;

	private String _planCode;

	private String _issueState;

	private String _lob;

	private String _effDate;
	public char _planType;
	private String _tableVariance;
	private String _subset;
	*/
	private String _tableId;
	
	private String _packageId;
	private String _projectName;


	//private String _planKey;
//	private char _delim;
//
//	private String _auditUserID;
//
//	private String _auditDate;
//
//	private String _auditProject;

	private String _exportSQL = "CLICK(select) TABLE NODE ";

	private boolean isClonedPlanFlag;

//	private CscTree _tree;

	/**
	 * Constructor CscTreeNode
	 */

//	public CscTreeNode(CscTree tree) {
//		super();
//		
//		_tree = tree;
//		planRow = new PlanRowTO();
//		planRow.setEnvironment(_tree.getEnvironment().getSchema());
//	}

	/**
	 * Constructor CscTreeNode
	 * 
	 * @param nodeInfo
	 * @param delim
	 * @throws IOException
	 */

//	public CscTreeNode(String nodeInfo, char delim, CscTree tree)
//	         {
//
//		super();
//
//		_tree = tree;
//	//	_delim = delim;
//
//		planRow = new PlanRowTO();
//		planRow.setEnvironment(_tree.getEnvironment().getSchema());
//		
//		_log.debug(nodeInfo);
//		int start, end;
//		String s;
//
//		end = nodeInfo.indexOf(delim);
//
//		_nodeId = Integer.parseInt(nodeInfo.substring(0, end));
//		start = ++end;
//
//		end = nodeInfo.indexOf(delim, start);
//		_level = Short.parseShort(nodeInfo.substring(start, end));
//		start = ++end;
//
//		end = nodeInfo.indexOf(delim, start);
//		_type = Short.parseShort(nodeInfo.substring(start, end));
//		start = ++end;
//
//		end = nodeInfo.indexOf(delim, start);
//		_attributes = Integer.parseInt(nodeInfo.substring(start, end));
//		start = ++end;
//
//		end = nodeInfo.indexOf(delim, start);
//
//		if (end != -1) {
//			_display = nodeInfo.substring(start, end);
//			start = ++end;
//		} else {
//			_display = nodeInfo.substring(start);
//		}
//
//		// if (_log != null) {
//		// _log.write(CscTree.LOG_INDEX, "_nodeId: " + _nodeId);
//		// _log.write(CscTree.LOG_INDEX, "_level: " + _level);
//		// _log.write(CscTree.LOG_INDEX,
//		// "_type: " + _type + " " + NODE_TYPES[_type]);
//		// _log.write(CscTree.LOG_INDEX,
//		// "_attributes: " + _attributes + " "
//		// + attrString(_attributes));
//		// _log.write(CscTree.LOG_INDEX, "_display: " + _display);
//		// }
//
//		// if( _type > 0 )
//		// _flavor = DndDragSourceRules.getDataFlavor( _type );
//
//		switch (_type) {
//
//			case NODE_PACKAGE:
//				_packageId = nodeInfo.substring(start);
//
//				_log.debug("_packageId: " + _packageId);
//
//				break;
//			case NODE_PROJECT:
//				_projectName = nodeInfo.substring(start);
//
//				_log.debug("_projectName: " + _projectName);
//
//				break;
//			case NODE_COMPANY:
//				planRow.setCompanyCode(nodeInfo.substring(start));
//				_log.debug("_companyCode: " + planRow.getCompanyCode());
//
//				break;
//			case NODE_ANNUITIY_FOLDER:
//			case NODE_UNIV_LIFE_FOLDER:
//			case NODE_TRADITIONAL_FOLDER:
//			case NODE_PDFPLAN_FOLDER:
//				s = nodeInfo.substring(start);
//				planRow.setProductPrefix(s.substring(0, 1));
//				planRow.setProductSuffix(" ");
//				setTableId(Constants.TABLE_ZERO_ID);
//				/*
//				_productPrefix = s.charAt(0);
//				// _productSuffix = s.charAt( 1 );
//				_productSuffix = ' ';
//				_tableId = Constants.TABLE_ZERO_ID;
//				*/
//				_name = Constants.TABLE_ZERO_NAME;
//				_log.debug("_product: " + planRow.getProductCode());
//
//				break;
//			case NODE_ORPHAN_FOLDER:
//			case NODE_COMMON_TABLE_FOLDER:
//				s = nodeInfo.substring(start);
//				planRow.setProductPrefix(s.substring(0, 1));
//				planRow.setProductSuffix(s.substring(1, 2));
//				/*
//				_productPrefix = s.charAt(0);
//				_productSuffix = s.charAt(1);
//				*/
//				_log.debug("_product: " + planRow.getProductCode());
//
//				break;
//			case NODE_PLAN_FOLDER:
//			case NODE_RIDER_FOLDER:
//			case NODE_PAYOUTPLAN_FOLDER: // WMA TT SPR#2
//				String planType = nodeInfo.substring(start, start + 1);
//				planRow.setPlanType(planType);
//				_log.debug("_planType: " + planType);
//
//				break;
//			case NODE_PLAN:
//			case NODE_RIDER:
//			case NODE_PAYOUT_PLAN: // WMA TT SPR#2
//				// refactor plan key.
//				planRow = new PlanRowTO(new PlanTO(nodeInfo.substring(start), "" + delim));
//				planRow.setEnvironment(_tree.getEnvironment().getSchema());
//				setTableId(Constants.TABLE_ZERO_ID);
//				/*
//				end = nodeInfo.indexOf(delim, start);
//				_companyCode = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				_productPrefix = nodeInfo.substring(start, end).charAt(0);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				_productSuffix = nodeInfo.substring(start, end).charAt(0);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				_planCode = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				_issueState = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				_lob = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				_effDate = nodeInfo.substring(start, end);
//				start = ++end;
//
//				_planType = nodeInfo.charAt(start);
//
//				_tableId = Constants.TABLE_ZERO_ID;
//				*/
//				_name = Constants.TABLE_ZERO_NAME;
//				_tableId = Constants.TABLE_ZERO_ID;
//
//					_log.debug("_companyCode: " + planRow.getCompanyCode());
//					_log.debug("_product: " + planRow.getProductCode());
//					_log.debug("_planType: " + planRow.getPlanType());
//					_log.debug("_planCode: " + planRow.getPlanCode());
//					_log.debug("_issueState: " + planRow.getIssueState());
//					_log.debug("_lob: " + planRow.getLineOfBusiness());
//					_log.debug("_effDate: " + planRow.getEffectiveDate());
//
//				break;
//			case NODE_COMMON_FOLDER:
//			case NODE_COMMON_TABLE:
//			case NODE_TABLE:
//				end = nodeInfo.indexOf(delim, start);
//				_name = nodeInfo.substring(start, end);
//				start = ++end;
//
//				setTableId(nodeInfo.substring(start));
//				_log.debug("_name: " + _name);
//				_log.debug("_tableId: " + getTableId());
//
//				break;
//			case NODE_TABLE_SUBSET:
//				end = nodeInfo.indexOf(delim, start);
//				_name = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				String tableId = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				String tableVar = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				String tableSubset = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				//String companyCode = nodeInfo.substring(start, end);
//				start = ++end;
//
//				end = nodeInfo.indexOf(delim, start);
//				// refactor plan key.
//				planRow = new PlanRowTO(new PlanTO(nodeInfo.substring(start, end), "|"));
//				setTableId(tableId);
//				setTableVariance(tableVar);
//				setTableSubset(tableSubset);
//				//_planKey = nodeInfo.substring(start, end);
//				start = ++end;
//				/*
//				if (_planKey.indexOf("|", 0) != -1) {
//					parsePlanKey(_planKey);
//				}
//				*/
//
//				if (nodeInfo.length() >= start + 1) {
//					String audit = nodeInfo.substring(start, start + 1);
//
//					if (audit.equals("T")) {
//						_attributes |= ATTR_HAS_AUDIT_INFO;
//					}
//				}
//
//				if (hasAuditInfo()) {
//					start = start + 2;
//
//					end = nodeInfo.indexOf(delim, start);
//					String _auditUserID = nodeInfo.substring(start, end);
//					start = ++end;
//
//					end = nodeInfo.indexOf(delim, start);
//					String _auditDate = nodeInfo.substring(start, end);
//					start = ++end;
//
//					// end = nodeInfo.indexOf( delim, start );
//					String _auditProject = nodeInfo.substring(start);
//					start = ++end;
//
//				}
//				_log.debug("_name: " + _name);
//				_log.debug("_tableId: " + getTableId());
//				_log.debug("_tableVariance: " + getTableVariance());
//				_log.debug("_subset: " + getTableSubset());
//				if ((_attributes & ATTR_HAS_HELP_URL) != 0) {
//					_log.debug("_helpURL: " + _helpURL);
//				}
//
//				break;
//			case NODE_ORPHAN_TABLE_SUBSET:
//			//case NODE_POINTED_BY_ORPHAN:	
//				end = nodeInfo.indexOf(delim, start);
//				_name = nodeInfo.substring(start, end);
//				start = ++end;
//				end = nodeInfo.indexOf(delim, start);
//				// refactor plan key.
//				planRow = new PlanRowTO();
//				setTableId(nodeInfo.substring(start, end));
//				start = ++end;
//				end = nodeInfo.indexOf(delim, start);
//				setTableVariance(nodeInfo.substring(start, end));
//				start = ++end;
//				end = nodeInfo.indexOf(delim, start);
//				setTableSubset(nodeInfo.substring(start, end));
//				start = ++end;
//				end = nodeInfo.indexOf(delim, start);
//				setCompanyCode(nodeInfo.substring(start, end));
//				start = ++end;
//				setProductPrefix(nodeInfo.substring(start).charAt(0));
//				setProductSuffix('*');
//				break;
//			case NODE_ORPHAN_GROUP:
//				end = nodeInfo.indexOf(delim, start);
//				// refactor plan key.
//				planRow = new PlanRowTO();
//				setCompanyCode(nodeInfo.substring(start, end));
//				start = ++end;
//				end = nodeInfo.indexOf(delim, start);
//				setProductPrefix(nodeInfo.substring(start, end).charAt(0));
//				start = ++end;
//				end = nodeInfo.indexOf(delim, start);
//				_name = nodeInfo.substring(start, end);
//				start = ++end;
//				_tableId = nodeInfo.substring(start);
//				break;
//		}
//
//		nodeInfo = null;
//	}

	/*
	public void parsePlanKey(String planKey) {

		int start, end;

		_log.debug("parsePlanKey: " + planKey);

		end = planKey.indexOf("|", 0);
		_companyCode = planKey.substring(0, end);
		start = ++end;

		end = planKey.indexOf("|", start);
		_productPrefix = planKey.substring(start, end).charAt(0);
		start = ++end;

		end = planKey.indexOf("|", start);
		_productSuffix = planKey.substring(start, end).charAt(0);
		start = ++end;

		end = planKey.indexOf("|", start);
		_planCode = planKey.substring(start, end);
		start = ++end;

		end = planKey.indexOf("|", start);
		_issueState = planKey.substring(start, end);
		start = ++end;

		end = planKey.indexOf("|", start);
		_lob = planKey.substring(start, end);
		start = ++end;

		end = planKey.indexOf("|", start);
		_effDate = planKey.substring(start, end);
		start = ++end;

		_planType = planKey.substring(start).charAt(0);

	}
	*/
	/**
	 * Method getFlavor
	 * 
	 * @return
	 */

//	public DataFlavor getFlavor() {
//		return DndDragSourceRules.getDataFlavor(_type);
//	}

	/**
	 * Method getParentPlanOrRider
	 * 
	 * @return
	 */

	public CscTreeNode getParentPlanOrRider() {

		CscTreeNode node = this;

		while (true) {
			node = (CscTreeNode) node.getParent();

			if (node == null) {
				break;
			}

			if (node.isPlan() || node.isRider() || node.isPayoutPlan()) { // WMA
				// TT
				// SPR#2
				return node;
			}
		}

		return null;
	}

	// TT wmA SPR 6162
	public CscTreeNode getProductParent() {

		CscTreeNode node = this;

		while (true) {
			node = (CscTreeNode) node.getParent();
			if (node == null)
				break;

			if (node.getProductPrefix() != null
					&& node.getProductPrefix().length() > 0 ) {
				return node;
			}
		}

		return null;
	}

	/**
	 * Method getParentNode
	 * 
	 * @param type
	 * @return
	 */

	public CscTreeNode getParentNode(int type) {

		CscTreeNode node = this;

		while (true) {
			node = (CscTreeNode) node.getParent();

			if (node == null) {
				break;
			}

			if (node.getType() == type) {
				return node;
			}
		}

		return null;
	}

	/**
	 * Method getProduct
	 * 
	 * @return
	 */

	public String getProduct() {
		return planRow.getProductCode();
		/*
		char prod[] = new char[2];

		prod[0] = _productPrefix;
		prod[1] = _productSuffix;

		String product = new String(prod);

		return product.trim();
		*/
	}

	/**
	 * Method getPackageId
	 * 
	 * @return
	 */

	public String getPackageId() {
		return _packageId;
	}

	/**
	 * Method getProjectName
	 * 
	 * @return
	 */

	public String getProjectName() {
		return _projectName;
	}

	// public String getCompanyCode() { return _companyCode; }

	/**
	 * Method getPlanType
	 * 
	 * @return
	 */

	public String getPlanType() {
		return planRow.getPlanType();
		//return String.valueOf(_planType);
	}

	/**
	 * Method getNodeId
	 * 
	 * @return
	 */

	public String getNodeId() {
		return String.valueOf(_nodeId);
	}

	/**
	 * Method setNodeId
	 * 
	 * @param nodeId
	 */

	public void setNodeId(String nodeId) {
		_nodeId = Integer.parseInt(nodeId);
	}

	/**
	 * Method setProductPrefix
	 * 
	 * @param c
	 */

	public void setProductPrefix(char c) {
		planRow.setProductPrefix("" + c);
		//_productPrefix = c;
	}

	/**
	 * Method setProductSuffix
	 * 
	 * @param c
	 */

	public void setProductSuffix(char c) {
		planRow.setProductSuffix("" + c);
		//_productSuffix = c;
	}

	/**
	 * Method setPlanCode
	 * 
	 * @param s
	 */

	public void setPlanCode(String s) {
		planRow.setPlanCode(s);
		//_planCode = s;
	}

	/**
	 * Method setPlanType
	 * 
	 * @param c
	 */

	public void setPlanType(char c) {
		planRow.setPlanType("" + c);
		//_planType = c;
	}

	/**
	 * Method setIssueState
	 * 
	 * @param s
	 */

	public void setIssueState(String s) {
		planRow.setIssueState(s);
		//_issueState = s;
	}

	/**
	 * Method setLOB
	 * 
	 * @param s
	 */

	public void setLineOfBusiness(String s) {
		planRow.setLineOfBusiness(s);
		//_lob = s;
	}

	/**
	 * Method setEffectiveDate
	 * 
	 * @param s
	 */

	public void setEffectiveDate(String s) {
		planRow.setEffectiveDate(s);
		//_effDate = s;
	}

	/**
	 * Method setTableVariance
	 * 
	 * @param s
	 */

	public void setTableVariance(String s) {
		planRow.setTablePtrVar(s);
		//_tableVariance = s;
	}

	/**
	 * Method getPlanCode
	 * 
	 * @return
	 */

	public String getPlanCode() {
		return planRow.getPlanCode();
		//return _planCode;
	}

	/**
	 * Method getIssueState
	 * 
	 * @return
	 */

	public String getIssueState() {
		return planRow.getIssueState();
		//return _issueState;
	}

	/**
	 * Method getLOB
	 * 
	 * @return
	 */

	public String getLineOfBusiness() {
		return planRow.getLineOfBusiness();
		//return _lob;
	}

	/**
	 * Method getEffectiveDate
	 * 
	 * @return
	 */

	public String getEffectiveDate() {
		return planRow.getEffectiveDate();
		//return _effDate;
	}

	/**
	 * Method getHelpUrl
	 * 
	 * @return
	 */

	public String getHelpUrl() {
		return _helpURL;
	}

	/**
	 * Method isAnyTable
	 * 
	 * @return
	 */

	public boolean isAnyTable() {
		return (_type == NODE_TABLE) || (_type == NODE_COMMON_TABLE)
		        || (_type == NODE_COMMON_FOLDER);
	}

	/**
	 * Method isTable
	 * 
	 * @return
	 */

	public boolean isTable() {
		return _type == NODE_TABLE;
	}

	/**
	 * Method isCommonTable
	 * 
	 * @return
	 */

	public boolean isCommonTable() {
	//	return (_type == NODE_COMMON_TABLE) || (_type == NODE_COMMON_FOLDER);
		return  (SpecialHandling.getInstance().isCommonTable(_name)); //WMABASEIXI-3758 
	}

	/**
	 * Method isPlan
	 * 
	 * @return
	 */

	public boolean isPlan() {
		return _type == NODE_PLAN;
	}

	/**
	 * Method isRider
	 * 
	 * @return
	 */

	public boolean isRider() {
		return _type == NODE_RIDER;
	}

	/**
	 * Method isPayoutPlan
	 * 
	 * @return
	 */

	public boolean isPayoutPlan() { // WMA TT SPR#2
		return _type == NODE_PAYOUT_PLAN;
	}

	/**
	 * Method isTableSubset
	 * 
	 * @return
	 */

	public boolean isTableSubset() {
		return _type == NODE_TABLE_SUBSET;
	}

	/**
	 * Method isOrphanFolder
	 * 
	 * @return
	 */

	public boolean isOrphanFolder() {
		return _type == NODE_ORPHAN_FOLDER;
	}

	/**
	 * Method isEnvironment
	 * 
	 * @return
	 */

	public boolean isEnvironment() {
		return _type == NODE_DISPLAY;
	}

	/**
	 * Method isPackage
	 * 
	 * @return
	 */

	public boolean isPackage() {
		return _type == NODE_PACKAGE;
	}

	/**
	 * Method isProject
	 * 
	 * @return
	 */

	public boolean isProject() {
		return _type == NODE_PROJECT;
	}

	/**
	 * Method isCompany
	 * 
	 * @return
	 */

	public boolean isCompany() {
		return _type == NODE_COMPANY;
	}

	/**
	 * Method isProductFolder
	 * 
	 * @return
	 */

	public boolean isProductFolder() {

		return ((_type == NODE_ANNUITIY_FOLDER)
		        || (_type == NODE_TRADITIONAL_FOLDER) || (_type == NODE_UNIV_LIFE_FOLDER));
	}

	/**
	 * Method isCommonTblFldr
	 * 
	 * @return
	 */

	public boolean isCommonTblFldr() {
		return _type == NODE_COMMON_TABLE_FOLDER || _type ==  NODE_COMMON_FOLDER;
	}

	/**
	 * Method isBasePlanFolder
	 * 
	 * @return
	 */

	public boolean isBasePlanFolder() {
		return _type == NODE_PLAN_FOLDER;
	}

	/**
	 * Method isRiderPlanFolder
	 * 
	 * @return
	 */

	public boolean isRiderPlanFolder() {
		return _type == NODE_RIDER_FOLDER;
	}

	/**
	 * Method isShared
	 * 
	 * @return
	 */

	public boolean isShared() {
		return (_attributes & ATTR_SHARED) != 0;
	}

	/**
	 * Method isReadOnly
	 * 
	 * @return
	 */

	public boolean isReadOnly() {
		return !hasUpdateAuthority();
//		return !_tree.getAppletParent().getUser().isPermitted(
//		        _tree.getEnvironment().getSchema(),
//		        _tree.getSelectedTableCompanyCode(), _tableId,
//		        PolicyConstants.UPDATE);
		// return (_attributes & ATTR_READ_ONLY) != 0;
	}

	/**
	 * Method isNewlyAdded
	 * 
	 * @return
	 */

	public boolean isNewlyAdded() {
		return (_attributes & ATTR_IS_NEWLY_ADDED) != 0;
	}

	/**
	 * Method isDirty
	 * 
	 * @return
	 */

	public boolean isDirty() {
		return _dirty;
	}

	private void setDirty(boolean b, int changeAttribute) {

		_dirty = b;
		_attributes |= changeAttribute;
	}

	/**
	 * Method getAttributes
	 * 
	 * @return
	 */

	public int getAttributes() {
		return _attributes;
	}

	/**
	 * Method getType
	 * 
	 * @return
	 */

	public short getType() {
		return _type;
	}

	/**
	 * Method getLevel
	 * 
	 * @return
	 */

	public int getLevel() {
		return _level;
	}

	/**
	 * Method getName
	 * 
	 * @return
	 */

	public String getName() {
		return _name;
	}

	/**
	 * Method setName
	 * 
	 * @param name
	 */

	public void setName(String name) {
		_name = name;
	}

	/**
	 * Method getTableId
	 * 
	 * @return
	 */

	public String getTableId() {
		return _tableId;
	}

	/**
	 * Method getTableVariance
	 * 
	 * @return
	 */

	public String getTableVariance() {
		return planRow.getTablePtrVar();
		//return _tableVariance;
	}

	/**
	 * Method getTableSubset
	 * 
	 * @return
	 */

	public String getTableSubset() {
		return planRow.getTablePtrSubset();
		//return _subset;
	}

	public String getOldTableSubset() {
		return _oldSubset;
	}

	public void setOldTableSubset(String oldSubset) {
		_oldSubset = oldSubset;
	}

	/**
	 * Method toString
	 * 
	 * @return
	 */

//	public String toString() {
//		return toString(_tree.isShowTableidSubsetSelected());
//	}

//	public String toDragString() {
//		if ( _type == NODE_PLAN || _type == NODE_PAYOUT_PLAN || _type == NODE_RIDER )
//			return toString(false);
//		return toString(true);
//	}

//	public String toString(boolean showTableidSubset) {
//		if(showTableidSubset  && !(_type == NODE_ANNUITIY_FOLDER
//				|| _type == NODE_UNIV_LIFE_FOLDER
//				|| _type == NODE_TRADITIONAL_FOLDER
//				|| _type == NODE_COMMON_TABLE_FOLDER
//				|| _type == NODE_PLAN_FOLDER
//				|| _type == NODE_RIDER_FOLDER
//				|| _type == NODE_PDFPLAN_FOLDER
//				|| _type == NODE_ORPHAN_FOLDER
//				|| _type == NODE_PAYOUTPLAN_FOLDER)){
//			String displayString = getName();
//			displayString = (displayString==null)?"":displayString.trim();
//			String subsetString = getTableSubset();
//			subsetString = (subsetString==null)?"":subsetString.trim();
//			String variationString = getTableVariance();
//			variationString = (variationString==null)?"":variationString.trim();
//			if(subsetString.length() > 0){
//				displayString += "~" + subsetString;
//				// WMABASEIXI-4702 - handle "Blank" variation
//				if ( !_tree.is_isApplyChangesFilter() && !_tree.is_isPromotionFilter() ) { 
//					if ( variationString.length() > 0 ) { 
//						displayString += "~" + variationString;
//					} else {
//						if ( showBlankVariation() )
//							displayString += "~Blank";
//					}
//				}
//			}
//			if(displayString.length()>0)
//				return displayString;
//		}
//		return _display;
//	}

	/**
	 * Method getDisplay
	 * 
	 * @return
	 */

	public String getDisplay() {
		return _display;
	}

	/**
	 * Method getTypeAsString
	 * 
	 * @return
	 */

	public String getTypeAsString() {
		return NODE_TYPES[_type];
	}

	/**
	 * Method getPlanKey
	 * 
	 * @return
	 */

	/*
	public String getPlanKey() {
		return _planKey;
	}
	*/
	/**
	 * Method hasAuditInfo
	 * 
	 * @return
	 */

	public boolean hasAuditInfo() {
		return (_attributes & ATTR_HAS_AUDIT_INFO) != 0;
	}

	/**
	 * Method hasUpdateAuthority
	 * 
	 * @return
	 */

	public boolean hasUpdateAuthority() {
		return (_attributes & ATTR_UPDATE) != 0;
	}
	
	// WMABASEIXI-4699
	public void setUpdateAuthority(boolean enable) {
		if ( enable )
			_attributes |= ATTR_UPDATE;
		else 
			_attributes &= ~ATTR_UPDATE;
	}

	/**
	 * Method hasManagerAuthority
	 * 
	 * @return
	 */

	// WMABASEIXI-4515
	public boolean isDisabled() {
		return (_attributes & ATTR_DISABLED) != 0;
	}

	/**
	 * Method hasInquiryAuthority
	 * 
	 * @return
	 */

	public boolean hasInquiryAuthority() {
		return (_attributes & ATTR_INQUIRY) != 0;
	}
	
	// WMABASEIXI-4699
	public void setInquiryAuthority(boolean enable) {
		if ( enable )
			_attributes |= ATTR_INQUIRY;
		else 
			_attributes &= ~ATTR_INQUIRY;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @return
	 */

	public String getProductPrefix() {
		return planRow.getProductPrefix();
		//return String.valueOf(_productPrefix);
	}

	/**
	 * Method getProductSuffix
	 * 
	 * @return
	 */

	public String getProductSuffix() {
		return planRow.getProductSuffix();
		//return String.valueOf(_productSuffix);
	}

	/**
	 * Method setProduct
	 * 
	 * @param prduct
	 */

	public void setProduct(String prduct) {
		planRow.setProductPrefix(prduct.substring(0, 1));
		planRow.setProductSuffix(prduct.substring(1, 2));
		/*
		_productPrefix = prduct.charAt(0);
		_productSuffix = prduct.charAt(1);
		*/
	}

	/**
	 * Method setTableSubset
	 * 
	 * @param s
	 */

	public void setTableSubset(String s) {
		planRow.setTablePtrSubset(s);
		//_subset = s;
	}

	/**
	 * Method setDisplayValue
	 * 
	 * @param s
	 */

	public void setDisplayValue(String s) {

		_display = s;
		setDirty(true, ATTR_RENAMED_DISPLAY_VALUE);
	}

	/**
	 * Method setLevel
	 * 
	 * @param newLevel
	 */

	public void setLevel(int newLevel) {
		_level = newLevel;
	}

	/**
	 * Method setType
	 * 
	 * @param type
	 */

	public void setType(short type) {
		_type = type;
	}

	/**
	 * Method setTableId
	 * 
	 * @param tableId
	 */

	public void setTableId(String tableId) {
		_tableId = tableId;
		if (!tableId.equals(Constants.TABLE_ZERO_ID)) {
			planRow.setTablePtrId(tableId);
		}
	}

	/*
	public void replacePlanKeyInfo(CscTreeNode replacerNode) {

		_companyCode = replacerNode.getCompanyCode();
		_productPrefix = replacerNode.getProductPrefix().charAt(0);
		_productSuffix = replacerNode.getProductSuffix().charAt(0);
		_planCode = replacerNode.getPlanCode();
		_issueState = replacerNode.getIssueState();
		_lob = replacerNode.getLOB();
		_effDate = replacerNode.getEffectiveDate();
		_planType = replacerNode.getPlanType().charAt(0);
		// SPR2132 start
		StringBuffer planKey = new StringBuffer(34);

		planKey.append(_companyCode + Constants.SPIPE);
		planKey.append(_productPrefix + Constants.SPIPE);
		planKey.append(_productSuffix + Constants.SPIPE);
		planKey.append(_planCode + Constants.SPIPE);
		planKey.append(_issueState + Constants.SPIPE);
		planKey.append(_lob + Constants.SPIPE);
		planKey.append(_effDate + Constants.SPIPE);
		planKey.append(_planType + Constants.SPIPE);
		setPlanKey(planKey.toString());
		planKey = null;
		// SPR2132 end
	}
	*/

	/**
	 * Method getPlanKeyInfo
	 * 
	 * @return
	 */

	public String getPlanKeyInfo() {
		// refactor plan key.
		return planRow.getPlanRowKey("|");
		/*
		StringBuffer sb = new StringBuffer(256);

		sb.append(_companyCode);
		sb.append("|");
		sb.append(_productPrefix);
		sb.append("|");
		sb.append(_productSuffix);
		sb.append("|");
		sb.append(_planCode);
		sb.append("|");
		sb.append(_issueState);
		sb.append("|");
		sb.append(_lob);
		sb.append("|");
		sb.append(_effDate);
		sb.append("|");
		sb.append(_planType);
		sb.append("|");
		sb.append(_tableId);
		sb.append("|");
		sb.append(_tableVariance);
		sb.append("|");
		sb.append(_subset);

		return sb.toString();
		*/
	}

	/**
	 * Method getNodePertainedInfo
	 * 
	 * @return
	 */

	public String getNodePertainedInfo() {

		StringBuffer sb = new StringBuffer(256);

		sb.append(_nodeId);
		sb.append("|");
		sb.append(_level);
		sb.append("|");
		sb.append(_type);
		sb.append("|");
		sb.append(_display);
		sb.append("|");
		sb.append(planRow.getTablePtrId());
		sb.append("|");
		sb.append(planRow.getTablePtrVar());
		sb.append("|");
		sb.append(planRow.getTablePtrSubset());

		return sb.toString();
	}

	/**
	 * Method getCompanyCode
	 * 
	 * @return
	 */

	public String getCompanyCode() {

		if ((_type == NODE_COMPANY) || (_type == NODE_RIDER)
		        || (_type == NODE_PAYOUT_PLAN) // WMA TT SPR#2
		        || (_type == NODE_PLAN) || (_type == NODE_TABLE_SUBSET)
				|| (_type == NODE_ORPHAN_TABLE_SUBSET)){// || (_type == NODE_POINTED_BY_ORPHAN)) {
			return planRow.getCompanyCode();
		} else if ((_type == NODE_PLAN_FOLDER) || (_type == NODE_RIDER_FOLDER)
		        || (_type == NODE_COMMON_TABLE_FOLDER)
		        || (_type == NODE_PAYOUTPLAN_FOLDER)
		        || (_type == NODE_COMMON_TABLE)
		        || (_type == NODE_COMMON_FOLDER)
		        || (_type == NODE_PDFPLAN_FOLDER)
		        || (_type == NODE_ORPHAN_FOLDER)) {
			CscTreeNode node = getParentNode(NODE_COMPANY);

			return node.planRow.getCompanyCode();
		}

		return null;
	}

	/**
	 * Method setCompanyCode
	 * 
	 * @param cmpnyCode
	 */

	public void setCompanyCode(String cmpnyCode) {
		planRow.setCompanyCode(cmpnyCode);
		//_companyCode = cmpnyCode;
	}

	/**
	 * Method setDisplay
	 * 
	 * @param displayTxt
	 */

	public void setDisplay(String displayTxt) {
		_display = displayTxt;
	}

	/**
	 * Method isClonedPlanOrRider
	 * 
	 * @return
	 */

	public boolean isClonedPlanOrRider() {
		return isClonedPlanFlag;
	}

	/**
	 * Method setClonedPlanOrRider
	 * 
	 * @param value
	 */

	public void setClonedPlanOrRider(boolean value) {
		isClonedPlanFlag = value;
	}

	/**
	 * Method setShared
	 * 
	 * @param shared
	 */

	public void setShared(boolean shared) {

		if (shared) {
			_attributes |= ATTR_SHARED;
		} else {
			_attributes &= ATTR_UNSHARE;
		}
	}

	/**
	 * Method setNewlyAdded
	 */

	public void setNewlyAdded() {
		_attributes |= ATTR_IS_NEWLY_ADDED;
	}

	// SPR2132 start

	/**
	 * Method setPlanKey
	 * 
	 * @param planKey
	 */

	/*
	public void setPlanKey(String planKey) {
		_planKey = planKey;
	}
	*/
	// SPR2132 end

	/**
	 * Method setExportSQL
	 * 
	 * @param sql
	 */

	public void setExportSQL(String sql) {
		_exportSQL = sql;
	}

	/**
	 * Method getExportSQL
	 * 
	 * @return
	 */

	public String getExportSQL() {
		return _exportSQL;
	}

	/**
	 * Method isAnyPlanFolder
	 * 
	 * @return
	 */

	public boolean isAnyPlanFolder() {
		if (_type == NODE_PLAN_FOLDER)
			return true;
		else if (_type == NODE_RIDER_FOLDER)
			return true;
		else if (_type == NODE_PDFPLAN_FOLDER)
			return true;
		else if (_type == NODE_PAYOUTPLAN_FOLDER) // WMA TT SPR# 99 & 100
			return true;
		else
			return false;
	}

	/*
	public String getPlanKey(String delimiter) {

		StringBuffer sb = new StringBuffer(256);

		sb.append(_companyCode);
		sb.append(delimiter);
		sb.append(_productPrefix);
		sb.append(delimiter);
		sb.append(_productSuffix);
		sb.append(delimiter);
		sb.append(_planCode);
		sb.append(delimiter);
		sb.append(_issueState);
		sb.append(delimiter);
		sb.append(_lob);
		sb.append(delimiter);
		sb.append(_effDate);

		return sb.toString();
	}
	*/

	// refactor plan key.
	/* @deprecated use getPlanRow().
	public PlanObject getPlanKeyObject() {
		PlanObject po = new PlanObject();

		po.setCompany(_companyCode);
		po.setProductCode(getProduct());
		po.setPlanCode(_planCode);
		po.setIssueState(_issueState);
		po.setLOB(_lob);
		po.setEffectiveDate(_effDate);
		po.setPlanType(getPlanType());
		return po;		
	}
	*/
	public PlanRowTO getPlanRow() {
		return planRow;
	}

	public void setPlanRow(PlanRowTO planRow) {
		this.planRow = planRow;
	}

	public void setPlanKey(PlanTO plan) {
		PlanRowTO newPlanRow = new PlanRowTO(plan);
		newPlanRow.setTablePtrId(planRow.getTablePtrId());
		newPlanRow.setTablePtrSubset(planRow.getTablePtrSubset());
		newPlanRow.setTablePtrVar(planRow.getTablePtrVar());
		this.planRow = newPlanRow;
	}
	
	public boolean isOrphanTableSubset() {
		return _type == NODE_ORPHAN_TABLE_SUBSET;
	}
	
//	public boolean isPointedByOrphan() {
//		return _type == NODE_POINTED_BY_ORPHAN;
//	}
	
	public boolean isOrphanGroup() {
		return _type == NODE_ORPHAN_GROUP;
	}

	public boolean isPlanNode(){
		return (isPlan() || isRider() || isPayoutPlan());
	}
	
	public boolean isLevel1Subset(){
		if (isTableSubset()){
			CscTreeNode parent = (CscTreeNode)getParent();
			return parent.isPlanNode();
		}
		return false;
	}
    
	/**
     * Overridden to copy planRow.
     *
     * @return	a copy of this node
     */
    public Object clone() {
    	CscTreeNode newNode = null;

   	    newNode = (CscTreeNode)super.clone();
   	    newNode.setPlanRow(new PlanRowTO(newNode.getPlanRow()));

    	return newNode;
    }
    
    // WMABASEIXI-3750 - for node cloning
//    public void setTree(CscTree tree) {
//    	_tree = tree;
//    }
	
	// WMABASEIXI-4702
//	public boolean showBlankVariation() {
//		boolean ret = false;
//		CscTreeNode parent = (CscTreeNode) getParent();
//		if ( parent != null ) {
//			Set<String> variations = ProductWizardApplet.getVariations(
//					getProductPrefix(), getPlanType(),
//					getTableId(), parent.getNodeId());
//			if ( variations.size() > 1 )
//				return true;
//		}
//		return ret;
//	}
	
	public boolean hasInvalidChild(){
		Enumeration<CscTreeNode> iter = children();
		while (iter.hasMoreElements()){
			CscTreeNode node = iter.nextElement();
			if (node.getNodeId().equals("111111"))
				return true;
		}
		return false;
	}
	
	public StringBuffer getInvalidNodesInfo(){
		StringBuffer buf = new StringBuffer();
		Enumeration<CscTreeNode> iter = children();
		while (iter.hasMoreElements()){
			CscTreeNode node = iter.nextElement();
			if (node.getNodeId().equals("111111")){
				buf.append(node.getDisplay()+" ("+node.getName()+"~"+node.getTableSubset());
				if (node.getTableVariance().trim().length()>0)
					buf.append("~"+node.getTableVariance());
				buf.append(")\n");
			}
		}
		return buf;
	}
	
	
}
