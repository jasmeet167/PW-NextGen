package com.csc.fsg.life.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTOBase;
import com.csc.fsg.life.pw.web.actions.tree.TreeWriter;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.ErrorModelFactory;
import com.csc.fsg.life.rest.model.tree.TreeNode;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class BusinessRulesTreeSearchServiceImpl
	extends RestServiceImpl
	implements BusinessRulesTreeSearchService
{
	@Autowired
	private ErrorModelFactory errorModelFactory = null;

	public List<TreeNode> getBusinessRulesTree(RestServiceParam param, BusinessRuleTreeSearchInput input)
	{
		try {
			HashMap<String, String> keyValues = buildKeyValues(param, input);
			PlanCriteriaTO planCriteria = new PlanCriteriaTO(keyValues);
			planCriteria.setLoadNP(true);

			Vector<String> compCodesVector = new Vector<>();
			if (StringUtils.hasText(planCriteria.getCompanyCode()))
				compCodesVector.add(planCriteria.getCompanyCode());
			else
				; // TODO: +++ get list of all companies

			List<PlanCriteriaTO> list = Arrays.asList(planCriteria);
			boolean includeOrphans = input.areOrphansIncluded();
			String payload = TreeWriter.getStream(list, compCodesVector, includeOrphans);

			BufferedReader reader = new BufferedReader(new StringReader(payload));
			TreeNode root = new TreeNode();
			processTreeNode(reader, root, new TreeNodeContainer(), param.getEnvId());
			return root.getChildren().get(0).getChildren();
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

	private void processTreeNode(BufferedReader reader, TreeNode parentNode, TreeNodeContainer pushBack, String envId)
		throws IOException
	{
		// For reference, see method private void processTreeNode(CscTreeNode parentNode) in class CscTree
		int parentLevel = parentNode.getLevel();
		TreeNode node = null;
		TreeNode prevNode = null;

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

	private TreeNode getNextTreeNode(BufferedReader reader, TreeNodeContainer pushBack, String envId)
		throws IOException
	{
		TreeNode node = null;
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
				node = new TreeNode(envId, s);
				break;
			}
		}

		return node;
	}

	static private class TreeNodeContainer
	{
		private TreeNode node = null;

		private TreeNode getNode()
		{
			return node;
		}

		private void setNode(TreeNode node)
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
}
