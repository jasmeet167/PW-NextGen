package com.csc.fsg.life.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTOBase;
import com.csc.fsg.life.pw.web.actions.tree.TreeWriter;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.rest.exception.BadRequestException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNode.TypeEnum;
import com.csc.fsg.life.rest.model.TreeNodeData;
import com.csc.fsg.life.rest.model.TreeNodeData.LazyTypeEnum;
import com.csc.fsg.life.rest.model.tree.Node;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class BusinessRuleTreeServiceImpl
	extends RestServiceImpl
	implements BusinessRuleTreeService
{
	static private final String STYLE_FOLDER = "tn-1";
	static private final String STYLE_LEAF = "tn-2";

	static private final String ICON_FOLDER_OPEN = "fa-folder-open";
	static private final String ICON_FOLDER_CLOSED = "fa-folder";
	static private final String ICON_LEAF = "fa-cube";

	public BusinessRuleTreeServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.BusinessRuleTreeService");
	}

	public TreeNode getBusinessRuleTreeCore(RestServiceParam param, String productCode)
	{
		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();

			boolean isGoodEnvironment = false;
			if (StringUtils.hasText(envId)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(envId) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			if (!StringUtils.hasText(companyCode)) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_company"));
				throw new BadRequestException(model);
			}

			Environment environment = EnvironmentManager.getInstance().getEnvironment(envId);
			String companyName = environment.getAssistent().getCompanyCodesAndNames().get(companyCode);
			if (companyName == null)
				companyName = companyCode;

			TreeNode node = new TreeNode();
			node.setType(TypeEnum.C);
			node.setLabel(companyName);
			node.setStyleClass(STYLE_FOLDER);
			node.setExpanded(Boolean.TRUE);
			setFolderIcons(node);
			node.setChildren(getStarterNodes(productCode));

			return node;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	private List<TreeNode> getStarterNodes(String productCode)
	{
		List<TreeNode> starterNodes = new LinkedList<>();

		TreeNode common = new TreeNode();
		common.setType(TypeEnum.CTF);
		common.setLabel(getMessage("common_folder_label"));
		common.setStyleClass(STYLE_FOLDER);
		setFolderIcons(common);
		TreeNodeData commonData = new TreeNodeData();
		commonData.setLazyNode(Boolean.TRUE);
		commonData.setLazyType(LazyTypeEnum.C);
		common.setData(commonData);
		starterNodes.add(common);

		TreeNode pdf = new TreeNode();
		pdf.setType(TypeEnum.PDF);
		pdf.setLabel(getMessage("pdf_folder_label"));
		pdf.setStyleClass(STYLE_FOLDER);
		setFolderIcons(pdf);
		TreeNodeData pdfData = new TreeNodeData();
		pdfData.setLazyNode(Boolean.TRUE);
		pdfData.setLazyType(LazyTypeEnum.PDF);
		pdf.setData(pdfData);
		starterNodes.add(pdf);

		TreeNode commonCoverage = new TreeNode();
		commonCoverage.setType(TypeEnum.PDF);
		commonCoverage.setLabel(getMessage("common_cov_folder_label"));
		commonCoverage.setStyleClass(STYLE_FOLDER);
		setFolderIcons(commonCoverage);
		TreeNodeData commonCovData = new TreeNodeData();
		commonCovData.setLazyNode(Boolean.TRUE);
		commonCovData.setLazyType(LazyTypeEnum.CC);
		commonCoverage.setData(commonCovData);
		starterNodes.add(commonCoverage);

		if (productCode.startsWith("A")) {
			TreeNode ann = new TreeNode();
			starterNodes.add(ann);

			ann.setType(TypeEnum.AF);
			ann.setLabel(getMessage("annuity_folder_label"));
			ann.setStyleClass(STYLE_FOLDER);
			setFolderIcons(ann);
			ann.setChildren(getAnnuityStarterNodes(productCode));
		}
		else if (productCode.startsWith("U")) {
			TreeNode ul = new TreeNode();
			starterNodes.add(ul);

			ul.setType(TypeEnum.UF);
			ul.setLabel(getMessage("ul_folder_label"));
			ul.setStyleClass(STYLE_FOLDER);
			setFolderIcons(ul);
			ul.setChildren(getUlStarterNodes());
		}
		else if (productCode.startsWith("T")) {
			TreeNode trad = new TreeNode();
			starterNodes.add(trad);

			trad.setType(TypeEnum.TF);
			trad.setLabel(getMessage("trad_folder_label"));
			trad.setStyleClass(STYLE_FOLDER);
			setFolderIcons(trad);
			trad.setChildren(getTradStarterNodes());
		}

		return starterNodes;
	}

	private List<TreeNode> getAnnuityStarterNodes(String productCode)
	{
		List<TreeNode> starterNodes = new LinkedList<>();

		TreeNode basePlanFolder = new TreeNode();
		basePlanFolder.setType(TypeEnum.PF);
		basePlanFolder.setLabel(getMessage("base_plans_folder_label"));
		basePlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData basePlanData = new TreeNodeData();
		basePlanData.setLazyNode(Boolean.TRUE);
		basePlanData.setLazyType(LazyTypeEnum.AB);
		basePlanFolder.setData(basePlanData);
		setFolderIcons(basePlanFolder);
		starterNodes.add(basePlanFolder);
		if (!productCode.matches("A[01234\\*]"))
			basePlanFolder.setLeaf(Boolean.TRUE);

		TreeNode payoutPlanFolder = new TreeNode();
		payoutPlanFolder.setType(TypeEnum.PPF);
		payoutPlanFolder.setLabel(getMessage("payout_plans_folder_label"));
		payoutPlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData payoutPlanData = new TreeNodeData();
		payoutPlanData.setLazyNode(Boolean.TRUE);
		payoutPlanData.setLazyType(LazyTypeEnum.AP);
		payoutPlanFolder.setData(payoutPlanData);
		setFolderIcons(payoutPlanFolder);
		starterNodes.add(payoutPlanFolder);
		if (!productCode.matches("A[5\\*]"))
			payoutPlanFolder.setLeaf(Boolean.TRUE);

		return starterNodes;
	}

	private List<TreeNode> getUlStarterNodes()
	{
		List<TreeNode> starterNodes = new LinkedList<>();

		TreeNode basePlanFolder = new TreeNode();
		basePlanFolder.setType(TypeEnum.PF);
		basePlanFolder.setLabel(getMessage("base_plans_folder_label"));
		basePlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData basePlanData = new TreeNodeData();
		basePlanData.setLazyNode(Boolean.TRUE);
		basePlanData.setLazyType(LazyTypeEnum.UB);
		basePlanFolder.setData(basePlanData);
		setFolderIcons(basePlanFolder);
		starterNodes.add(basePlanFolder);

		TreeNode riderPlanFolder = new TreeNode();
		riderPlanFolder.setType(TypeEnum.RF);
		riderPlanFolder.setLabel(getMessage("rider_plans_folder_label"));
		riderPlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData riderPlanData = new TreeNodeData();
		riderPlanData.setLazyNode(Boolean.TRUE);
		riderPlanData.setLazyType(LazyTypeEnum.UR);
		riderPlanFolder.setData(riderPlanData);
		setFolderIcons(riderPlanFolder);
		starterNodes.add(riderPlanFolder);

		return starterNodes;
	}

	private List<TreeNode> getTradStarterNodes()
	{
		List<TreeNode> starterNodes = new LinkedList<>();

		TreeNode basePlanFolder = new TreeNode();
		basePlanFolder.setType(TypeEnum.PF);
		basePlanFolder.setLabel(getMessage("base_plans_folder_label"));
		basePlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData basePlanData = new TreeNodeData();
		basePlanData.setLazyNode(Boolean.TRUE);
		basePlanData.setLazyType(LazyTypeEnum.TB);
		basePlanFolder.setData(basePlanData);
		setFolderIcons(basePlanFolder);
		starterNodes.add(basePlanFolder);

		TreeNode riderPlanFolder = new TreeNode();
		riderPlanFolder.setType(TypeEnum.RF);
		riderPlanFolder.setLabel(getMessage("rider_plans_folder_label"));
		riderPlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData riderPlanData = new TreeNodeData();
		riderPlanData.setLazyNode(Boolean.TRUE);
		riderPlanData.setLazyType(LazyTypeEnum.TR);
		riderPlanFolder.setData(riderPlanData);
		setFolderIcons(riderPlanFolder);
		starterNodes.add(riderPlanFolder);

		return starterNodes;
	}

	public List<TreeNode> getBusinessRulesTree(RestServiceParam param, BusinessRuleTreeSearchInput input)
	{
		try {
			String envId = param.getEnvId();

			boolean isGoodEnvironment = false;
			if (StringUtils.hasText(envId)) {
				Map<String, Environment> environments = EnvironmentManager.getInstance().getEnvironments();
				if (environments.get(envId) != null)
					isGoodEnvironment = true;
			}
			if (!isGoodEnvironment) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_environment"));
				throw new BadRequestException(model);
			}

			if (!StringUtils.hasText(param.getCompanyCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_company"));
				throw new BadRequestException(model);
			}

			if (!StringUtils.hasText(input.getProductCode())) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_product"));
				throw new BadRequestException(model);
			}

			HashMap<String, String> keyValues = buildKeyValues(param, input);
			PlanCriteriaTO planCriteria = new PlanCriteriaTO(keyValues);
			planCriteria.setLoadNP(true);

			Vector<String> compCodesVector = new Vector<>();
			compCodesVector.add(planCriteria.getCompanyCode());

			List<PlanCriteriaTO> list = Arrays.asList(planCriteria);
			boolean includeOrphans = input.areOrphansIncluded();
			String payload = TreeWriter.getStream(list, compCodesVector, includeOrphans);
			BufferedReader reader = new BufferedReader(new StringReader(payload));
			Node root = new Node();
			processTreeNode(reader, root, new TreeNodeContainer(), param.getEnvId());

			List<Node> treeNodes = root.getChildren().get(0).getChildren();
			List<TreeNode> transformedNodes = transformToDeclaredTypes(treeNodes);
			for (TreeNode transformedNode : transformedNodes) {
				transformedNode.setExpanded(Boolean.TRUE);
				setFolderIcons(transformedNode);
			}

			return transformedNodes;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
	}

	private HashMap<String, String> buildKeyValues(RestServiceParam param, BusinessRuleTreeSearchInput input)
	{
		HashMap<String, String> keyValues = new HashMap<>();

		keyValues.put(PlanCriteriaTO.MERGED_VIEW_KEY, Boolean.valueOf(input.isAreChangesIncluded()).toString());
		keyValues.put(PlanTOBase.ENVIRONMENT_KEY, param.getEnvId());

		String companyCode = param.getCompanyCode();
		if (StringUtils.hasText(companyCode))
			keyValues.put(PlanTOBase.COMPANY_CODE_KEY, companyCode);

		String productCode = input.getProductCode();
		if (StringUtils.hasText(productCode)) {
			keyValues.put(PlanTOBase.PRODUCT_CODE_KEY, productCode);
			keyValues.put(PlanTOBase.PRODUCT_PREFIX_KEY, productCode.substring(0, 1));
			if (productCode.length() > 1)
				keyValues.put(PlanTOBase.PRODUCT_SUFFIX_KEY, productCode.substring(1));
		}

		String planCode = input.getPlanCode();
		if (StringUtils.hasText(planCode))
			keyValues.put(PlanTOBase.PLAN_CODE_KEY, planCode);

		String issueState = input.getIssueState();
		if (StringUtils.hasText(issueState))
			keyValues.put(PlanTOBase.ISSUE_STATE_KEY, issueState);

		String lob = input.getLob();
		if (StringUtils.hasText(lob))
			keyValues.put(PlanTOBase.LINE_OF_BUSINESS_KEY, lob);

		Date effDate = input.getEffDate();
		if (effDate != null)
			keyValues.put(PlanTOBase.EFFECTIVE_DATE_KEY, effDate.toString());

		return keyValues;
	}

	private void processTreeNode(BufferedReader reader, Node parentNode, TreeNodeContainer pushBack, String envId)
		throws IOException
	{
		// For reference, see method private void processTreeNode(CscTreeNode parentNode) in class CscTree
		int parentLevel = parentNode.getLevel();
		Node node = null;
		Node prevNode = null;

		while ((node = getNextTreeNode(reader, pushBack, envId)) != null) {
			if (node.getLevel() == (parentLevel + 1)) {
				prevNode = node;
				parentNode.addChild(node);
				continue;
			}

			pushBack.setNode(node);
			if (node.getLevel() <= parentLevel)
				return;
			else
				processTreeNode(reader, prevNode, pushBack, envId);
		}
	}

	private Node getNextTreeNode(BufferedReader reader, TreeNodeContainer pushBack, String envId)
		throws IOException
	{
		Node node = null;
		String s;

		if (!pushBack.isEmpty()) {
			node = pushBack.getNode();
			pushBack.clear();
			return node;
		}

		while ((s = reader.readLine()) != null) {
			if (s.length() == 0) {
				continue;
			}
			else {
				node = new Node(envId, s);
				break;
			}
		}

		return node;
	}

	static private class TreeNodeContainer
	{
		private Node node = null;

		private Node getNode()
		{
			return node;
		}

		private void setNode(Node node)
		{
			this.node = node;
		}

		private boolean isEmpty()
		{
			return node == null;
		}

		private void clear()
		{
			node = null;
		}
	}

	private List<TreeNode> transformToDeclaredTypes(List<Node> existingNodes)
	{
		List<TreeNode> transformedNodes = new LinkedList<>();

		for (Node existingNode : existingNodes) {
			TreeNode transformedNode = new TreeNode();
			transformedNodes.add(transformedNode);
			transformedNode.setType(existingNode.getType());
			transformedNode.setLabel(existingNode.getDisplay());
			transformedNode.setData(existingNode.getData());

			List<TreeNode> transformedChildren = transformToDeclaredTypes(existingNode.getChildren());
			transformedNode.getChildren().addAll(transformedChildren);

			switch (existingNode.getType()) {
				case AF:		// ANNUITIY_FOLDER
				case UF:		// UNIV_LIFE_FOLDER
				case TF:		// TRADITIONAL_FOLDER
				case PDF:		// PDFPLAN_FOLDER
				case OF:		// ORPHAN_FOLDER
				case CTF:		// COMMON_TABLE_FOLDER
				case PF:		// PLAN_FOLDER
				case RF:		// RIDER_FOLDER
				case PPF: {		// PAYOUTPLAN_FOLDER
					transformedNode.setStyleClass(STYLE_FOLDER);
					setFolderIcons(transformedNode);
					break;
				}
				default: {
					if (transformedChildren.isEmpty()) {
						transformedNode.setStyleClass(STYLE_LEAF);
						transformedNode.setIcon(ICON_LEAF);
					}
					else {
						transformedNode.setStyleClass(STYLE_FOLDER);
						setFolderIcons(transformedNode);
					}
					break;
				}
			}
		}

		return transformedNodes;
	}

	private void setFolderIcons(TreeNode node)
	{
		node.setIcon(null);
		node.setExpandedIcon(ICON_FOLDER_OPEN);
		node.setCollapsedIcon(ICON_FOLDER_CLOSED);
	}
}
