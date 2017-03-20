package com.csc.fsg.life.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTOBase;
import com.csc.fsg.life.pw.web.actions.tree.TreeWriter;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.rest.exception.BadRequestException;
import com.csc.fsg.life.rest.exception.ForbiddenException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.PlanSearchInput;
import com.csc.fsg.life.rest.model.SelectItem;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.tree.Node;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class BusinessRuleTreeServiceImpl
	extends RestServiceImpl
	implements BusinessRuleTreeService
{
	@Autowired
	private SearchService searchService = null;

	public BusinessRuleTreeServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.BusinessRuleTreeService");
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
			if (StringUtils.hasText(planCriteria.getCompanyCode())) {
				compCodesVector.add(planCriteria.getCompanyCode());
			}
			else {
				Vector<String> companyCodes = getCompanyCodes(param);
				// Empty contents of companyCodes indicates that the user is not
				// authorized to view information associated with any company:
				if (companyCodes.isEmpty())
					throw new ForbiddenException();

				compCodesVector.addAll(companyCodes);
			}

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

	private Vector<String> getCompanyCodes(RestServiceParam param)
	{
		PlanSearchInput searchInput = new PlanSearchInput();
		List<SelectItem> companyList = searchService.getPlanCommonValues(param, searchInput);

		Vector<String> compCodesVector = new Vector<>();
		for (SelectItem company : companyList)
			compCodesVector.add((String) company.getValue());

		return compCodesVector;
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
		List<TreeNode> transformedNodes = new ArrayList<>();

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
					transformedNode.setStyleClass("tn-1");
					setFolderIcons(transformedNode);
					break;
				}
				default: {
					if (transformedChildren.isEmpty()) {
						transformedNode.setStyleClass("tn-2");
						transformedNode.setIcon("fa-cube");
					}
					else {
						transformedNode.setStyleClass("tn-1");
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
		node.setExpandedIcon("fa-folder-open");
		node.setCollapsedIcon("fa-folder");
	}
}
