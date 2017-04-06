package com.csc.fsg.life.rest.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Connection;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTOBase;
import com.csc.fsg.life.pw.web.actions.tree.CommonTablesWriter;
import com.csc.fsg.life.pw.web.actions.tree.CompanyWriter;
import com.csc.fsg.life.pw.web.actions.tree.IndexMergeAssistent;
import com.csc.fsg.life.pw.web.actions.tree.OrphanTreeWriter;
import com.csc.fsg.life.pw.web.actions.tree.PlanMergeAssistent;
import com.csc.fsg.life.pw.web.actions.tree.ProductWriter;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;
import com.csc.fsg.life.pw.web.utils.DBConnMgr;
import com.csc.fsg.life.rest.exception.BadRequestException;
import com.csc.fsg.life.rest.exception.RestServiceException;
import com.csc.fsg.life.rest.exception.UnexpectedException;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.ErrorModel;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNode.TypeEnum;
import com.csc.fsg.life.rest.model.TreeNodeAttributes;
import com.csc.fsg.life.rest.model.TreeNodeData;
import com.csc.fsg.life.rest.model.TreeNodeData.LazyTypeEnum;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.model.tree.Node;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

@Service
public class BusinessRuleTreeServiceImpl
	extends RestServiceImpl
	implements BusinessRuleTreeService
{
	static private final String STYLE_FOLDER = "tn-1";
	static private final String STYLE_FOLDER_DISABLED = "tn-1-disabled";
	static private final String STYLE_LEAF = "tn-2";

	static private final String ICON_FOLDER_OPEN = "fa-folder-open";
	static private final String ICON_FOLDER_CLOSED = "fa-folder";
	static private final String ICON_LEAF = "fa-cube";

	@Autowired
	private SecurityService securityService = null;

	public BusinessRuleTreeServiceImpl()
	{
		super("com.csc.fsg.life.rest.service.BusinessRuleTreeService");
	}

	public List<TreeNode> getBusinessRuleTreeCore(RestServiceParam param, String productCode, boolean includeOrphans)
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
			node.setChildren(getStarterNodes(param, productCode, includeOrphans));

			List<TreeNode> treeCore = new LinkedList<>();
			treeCore.add(node);
			return treeCore;
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

	private List<TreeNode> getStarterNodes(RestServiceParam param, String productCode, boolean includeOrphans)
	{
		LinkedList<TreeNode> starterNodes = new LinkedList<>();

		TreeNode common = new TreeNode();
		common.setType(TypeEnum.CTF);
		common.setLabel(getMessage("common_folder_label"));
		common.setStyleClass(STYLE_FOLDER);
		common.setLeaf(Boolean.FALSE);
		setFolderIcons(common);
		TreeNodeData commonData = new TreeNodeData();
		commonData.setLazyNode(Boolean.TRUE);
		commonData.setLazyType(LazyTypeEnum.C);
		common.setData(commonData);
		starterNodes.add(common);

		boolean isAuthorized = isAuthorizedToViewSubsetIndex(param);

		TreeNode pdf = new TreeNode();
		pdf.setType(TypeEnum.PDF);
		pdf.setLabel(getMessage("pdf_folder_label"));
		pdf.setStyleClass(isAuthorized ? STYLE_FOLDER : STYLE_FOLDER_DISABLED);
		pdf.setLeaf(Boolean.FALSE);
		setFolderIcons(pdf);
		TreeNodeData pdfData = new TreeNodeData();
		pdfData.setLazyNode(Boolean.TRUE);
		pdfData.setLazyType(LazyTypeEnum.PDF);
		pdf.setData(pdfData);
		disableNode(pdf, !isAuthorized);

		TreeNode commonCoverage = new TreeNode();
		commonCoverage.setType(TypeEnum.PDF);
		commonCoverage.setLabel(getMessage("common_cov_folder_label"));
		commonCoverage.setStyleClass(isAuthorized ? STYLE_FOLDER : STYLE_FOLDER_DISABLED);
		commonCoverage.setLeaf(Boolean.FALSE);
		setFolderIcons(commonCoverage);
		TreeNodeData commonCovData = new TreeNodeData();
		commonCovData.setLazyNode(Boolean.TRUE);
		commonCovData.setLazyType(LazyTypeEnum.H);
		commonCoverage.setData(commonCovData);
		disableNode(commonCoverage, !isAuthorized);

		if (productCode.startsWith("N")) {
			starterNodes.add(commonCoverage);
			starterNodes.add(pdf);
		}
		else {
			starterNodes.add(pdf);
			starterNodes.add(commonCoverage);
		}

		if (productCode.startsWith("A")) {
			TreeNode ann = new TreeNode();
			starterNodes.add(ann);

			ann.setType(TypeEnum.AF);
			ann.setLabel(getMessage("annuity_folder_label"));
			ann.setStyleClass(isAuthorized ? STYLE_FOLDER : STYLE_FOLDER_DISABLED);
			setFolderIcons(ann);
			ann.setChildren(getAnnuityStarterNodes(productCode));
			disableNode(ann, !isAuthorized);
		}
		else if (productCode.startsWith("U")) {
			TreeNode ul = new TreeNode();
			starterNodes.add(ul);

			ul.setType(TypeEnum.UF);
			ul.setLabel(getMessage("ul_folder_label"));
			ul.setStyleClass(isAuthorized ? STYLE_FOLDER : STYLE_FOLDER_DISABLED);
			setFolderIcons(ul);
			ul.setChildren(getUlStarterNodes());
			disableNode(ul, !isAuthorized);
		}
		else if (productCode.startsWith("T")) {
			TreeNode trad = new TreeNode();
			starterNodes.add(trad);

			trad.setType(TypeEnum.TF);
			trad.setLabel(getMessage("trad_folder_label"));
			trad.setStyleClass(isAuthorized ? STYLE_FOLDER : STYLE_FOLDER_DISABLED);
			setFolderIcons(trad);
			trad.setChildren(getTradStarterNodes());
			disableNode(trad, !isAuthorized);
		}

		if (includeOrphans
		&& !productCode.startsWith("H")
		&& !productCode.startsWith("N")) {
			TreeNode orphanSubsetNode = getOrphanSubsetStarterNode();
			starterNodes.getLast().getChildren().add(orphanSubsetNode);
		}

		return starterNodes;
	}

	private boolean isAuthorizedToViewSubsetIndex(RestServiceParam param)
	{
		String envId = param.getEnvId();
		if (!StringUtils.hasText(envId))
			envId = "*";

		String companyCode = param.getCompanyCode();
		if (!StringUtils.hasText(companyCode))
			companyCode = "*";

		String resourceUrl = securityService.buildTableUrl(envId, companyCode, "T000XA");
		List<String> resources = Arrays.asList(resourceUrl);

		String authToken = param.getAuthToken();
		boolean isAuthorized = securityService.isAuthorized(authToken, resources, AuthorizationAction.VIEW);
		return isAuthorized;
	}

	private void disableNode(TreeNode node, boolean isDisabled)
	{
		TreeNodeData data = node.getData();
		if (data == null) {
			data = new TreeNodeData();
			node.setData(data);
		}

		TreeNodeAttributes attributes = data.getAttributes();
		if (attributes == null) {
			attributes = new TreeNodeAttributes();
			data.setAttributes(attributes);
		}

		attributes.setDisabled(Boolean.valueOf(isDisabled));
	}

	private List<TreeNode> getAnnuityStarterNodes(String productCode)
	{
		List<TreeNode> starterNodes = new LinkedList<>();

		TreeNode basePlanFolder = new TreeNode();
		starterNodes.add(basePlanFolder);
		basePlanFolder.setType(TypeEnum.PF);
		basePlanFolder.setLabel(getMessage("base_plans_folder_label"));
		basePlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData basePlanData = new TreeNodeData();
		basePlanData.setLazyNode(Boolean.TRUE);
		basePlanData.setLazyType(LazyTypeEnum.B);
		basePlanFolder.setData(basePlanData);
		setFolderIcons(basePlanFolder);
		if (productCode.matches("A[1234\\*]"))
			basePlanFolder.setLeaf(Boolean.FALSE);
		else
			basePlanFolder.setLeaf(Boolean.TRUE);

		TreeNode payoutPlanFolder = new TreeNode();
		starterNodes.add(payoutPlanFolder);
		payoutPlanFolder.setType(TypeEnum.PPF);
		payoutPlanFolder.setLabel(getMessage("payout_plans_folder_label"));
		payoutPlanFolder.setStyleClass(STYLE_FOLDER);
		TreeNodeData payoutPlanData = new TreeNodeData();
		payoutPlanData.setLazyNode(Boolean.TRUE);
		payoutPlanData.setLazyType(LazyTypeEnum.P);
		payoutPlanFolder.setData(payoutPlanData);
		setFolderIcons(payoutPlanFolder);
		if (productCode.matches("A[5\\*]"))
			payoutPlanFolder.setLeaf(Boolean.FALSE);
		else
			payoutPlanFolder.setLeaf(Boolean.TRUE);

		return starterNodes;
	}

	private List<TreeNode> getUlStarterNodes()
	{
		List<TreeNode> starterNodes = new LinkedList<>();

		TreeNode basePlanFolder = new TreeNode();
		starterNodes.add(basePlanFolder);
		basePlanFolder.setType(TypeEnum.PF);
		basePlanFolder.setLabel(getMessage("base_plans_folder_label"));
		basePlanFolder.setStyleClass(STYLE_FOLDER);
		basePlanFolder.setLeaf(Boolean.FALSE);
		TreeNodeData basePlanData = new TreeNodeData();
		basePlanData.setLazyNode(Boolean.TRUE);
		basePlanData.setLazyType(LazyTypeEnum.B);
		basePlanFolder.setData(basePlanData);
		setFolderIcons(basePlanFolder);

		TreeNode riderPlanFolder = new TreeNode();
		starterNodes.add(riderPlanFolder);
		riderPlanFolder.setType(TypeEnum.RF);
		riderPlanFolder.setLabel(getMessage("rider_plans_folder_label"));
		riderPlanFolder.setStyleClass(STYLE_FOLDER);
		riderPlanFolder.setLeaf(Boolean.FALSE);
		TreeNodeData riderPlanData = new TreeNodeData();
		riderPlanData.setLazyNode(Boolean.TRUE);
		riderPlanData.setLazyType(LazyTypeEnum.R);
		riderPlanFolder.setData(riderPlanData);
		setFolderIcons(riderPlanFolder);

		return starterNodes;
	}

	private List<TreeNode> getTradStarterNodes()
	{
		List<TreeNode> starterNodes = new LinkedList<>();

		TreeNode basePlanFolder = new TreeNode();
		starterNodes.add(basePlanFolder);
		basePlanFolder.setType(TypeEnum.PF);
		basePlanFolder.setLabel(getMessage("base_plans_folder_label"));
		basePlanFolder.setStyleClass(STYLE_FOLDER);
		basePlanFolder.setLeaf(Boolean.FALSE);
		TreeNodeData basePlanData = new TreeNodeData();
		basePlanData.setLazyNode(Boolean.TRUE);
		basePlanData.setLazyType(LazyTypeEnum.B);
		basePlanFolder.setData(basePlanData);
		setFolderIcons(basePlanFolder);

		TreeNode riderPlanFolder = new TreeNode();
		starterNodes.add(riderPlanFolder);
		riderPlanFolder.setType(TypeEnum.RF);
		riderPlanFolder.setLabel(getMessage("rider_plans_folder_label"));
		riderPlanFolder.setStyleClass(STYLE_FOLDER);
		riderPlanFolder.setLeaf(Boolean.FALSE);
		TreeNodeData riderPlanData = new TreeNodeData();
		riderPlanData.setLazyNode(Boolean.TRUE);
		riderPlanData.setLazyType(LazyTypeEnum.R);
		riderPlanFolder.setData(riderPlanData);
		setFolderIcons(riderPlanFolder);

		return starterNodes;
	}

	private TreeNode getOrphanSubsetStarterNode()
	{
		TreeNode orphans = new TreeNode();
		orphans.setType(TypeEnum.OF);
		orphans.setLabel(getMessage("orphan_subsets_folder_label"));
		orphans.setStyleClass(STYLE_FOLDER);
		orphans.setLeaf(Boolean.FALSE);
		setFolderIcons(orphans);

		TreeNodeData orphansData = new TreeNodeData();
		orphansData.setLazyNode(Boolean.TRUE);
		orphansData.setLazyType(LazyTypeEnum.O);
		orphans.setData(orphansData);

		return orphans;
	}

	public List<TreeNode> getBusinessRuleTreeCommonTables(RestServiceParam param, boolean includeChanges)
	{
		Connection brConn = null;
		Connection wipConn = null;

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

			brConn = DBConnMgr.getInstance().getConnection(envId, DBConnMgr.BUSINESS_RULES);
			wipConn = DBConnMgr.getInstance().getConnection(envId, DBConnMgr.APPL);

			List<TreeNode> commonTableList = getCommonTableList(brConn, wipConn, param, includeChanges);
			return commonTableList;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
		finally {
			if (brConn != null) {
				try {
					DBConnMgr.getInstance().releaseConnection(brConn);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (wipConn != null) {
				try {
					DBConnMgr.getInstance().releaseConnection(wipConn);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<TreeNode> getCommonTableList(Connection brConn, Connection wipConn, RestServiceParam param, boolean includeChanges)
		throws Exception
	{
		String envId = param.getEnvId();
		String companyCode = param.getCompanyCode();

		CommonTablesWriter commonTablesWriter = new CommonTablesWriter(envId, companyCode, includeChanges, brConn, wipConn);
		String payload = commonTablesWriter.getStream();

		BufferedReader reader = new BufferedReader(new StringReader(payload));
		Node root = new Node((short) 2);
		processTreeNode(reader, root, new TreeNodeContainer(), envId);

		List<Node> commonTableNodes = root.getChildren();
		List<TreeNode> commonTableTreeNodes = transformToDeclaredTypes(commonTableNodes, false);
		return commonTableTreeNodes;
	}

	public List<TreeNode> getBusinessRuleTreePlanList(RestServiceParam param, BusinessRuleTreeSearchInput searchInput)
	{
		Connection brConn = null;
		Connection wipConn = null;

		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();
			String productCode = searchInput.getProductCode();

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

			if (!StringUtils.hasText(productCode) || productCode.length() != 2) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_product"));
				throw new BadRequestException(model);
			}

			brConn = DBConnMgr.getInstance().getConnection(envId, DBConnMgr.BUSINESS_RULES);
			wipConn = DBConnMgr.getInstance().getConnection(envId, DBConnMgr.APPL);

			List<TreeNode> planList = getPlanList(brConn, wipConn, param, searchInput);
			if (searchInput.getIncludeOrphans()) {
				if ((productCode.startsWith("N") && searchInput.getLazyType() == LazyTypeEnum.PDF) 
				 || (productCode.startsWith("H") && searchInput.getLazyType() == LazyTypeEnum.H)) {
					TreeNode orphanSubsetNode = getOrphanSubsetStarterNode();
					planList.add(orphanSubsetNode);
				}
			}

			return planList;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
		finally {
			if (brConn != null) {
				try {
					DBConnMgr.getInstance().releaseConnection(brConn);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (wipConn != null) {
				try {
					DBConnMgr.getInstance().releaseConnection(wipConn);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<TreeNode> getPlanList(Connection brConn, Connection wipConn, RestServiceParam param, BusinessRuleTreeSearchInput searchInput)
		throws Exception
	{
		PlanMergeAssistent pmAssist = null;
		IndexMergeAssistent imAssist = null;

		try {
			LazyTypeEnum lazyType = searchInput.getLazyType();
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();
			String productCode = searchInput.getProductCode();
			String productPrefix = productCode.substring(0, 1);
			String productSuffix = productCode.substring(1);
			String planCode = searchInput.getPlanCode();
			String issueState = searchInput.getIssueState();
			String lob = searchInput.getLob();
			boolean viewChanges = searchInput.getViewChanges();

			String effDate = null;
			Date effectiveDate = searchInput.getEffDate();
			if (effectiveDate != null)
				effDate = effectiveDate.toString();

			HashMap<String, String> planCriteriaKey = new HashMap<>();
			planCriteriaKey.put(PlanTOBase.ENVIRONMENT_KEY, envId);
			planCriteriaKey.put(PlanTOBase.COMPANY_CODE_KEY, companyCode);
			planCriteriaKey.put(PlanTOBase.PRODUCT_CODE_KEY, productCode);
			planCriteriaKey.put(PlanTOBase.PRODUCT_PREFIX_KEY, productPrefix);
			planCriteriaKey.put(PlanTOBase.PRODUCT_SUFFIX_KEY, productSuffix);
			planCriteriaKey.put(PlanTOBase.PLAN_CODE_KEY, planCode);
			planCriteriaKey.put(PlanTOBase.ISSUE_STATE_KEY, issueState);
			planCriteriaKey.put(PlanTOBase.LINE_OF_BUSINESS_KEY, lob);
			planCriteriaKey.put(PlanTOBase.EFFECTIVE_DATE_KEY, effDate);
			planCriteriaKey.put(PlanCriteriaTO.MERGED_VIEW_KEY, String.valueOf(viewChanges));

			MergeAssistant assist = new MergeAssistant().init(wipConn, planCriteriaKey, viewChanges);
			pmAssist = assist.getPmAssistant();
			imAssist = assist.getImAssistant();
			String payload = new CompanyWriter().getStream(lazyType, envId, companyCode, productPrefix, wipConn, brConn,
														   viewChanges, false, pmAssist, imAssist);
			List<Node> planNodes = new LinkedList<>();
			String line = null;

			BufferedReader reader = new BufferedReader(new StringReader(payload));
			while ((line = reader.readLine()) != null) {
				if (!line.startsWith("0\t"))
					continue;

				Node node = new Node(envId, line);
				planNodes.add(node);
			}

			List<TreeNode> planList = transformToDeclaredTypes(planNodes, true);
			return planList;
		}
		finally {
			if (pmAssist != null)
				pmAssist.clean(wipConn);
			if (imAssist != null)
				imAssist.clean(wipConn);
		}
	}

	public List<TreeNode> getBusinessRuleTreePlanDetails(RestServiceParam param, TreeNodePlanKey planKey, boolean viewChanges)
	{
		Connection wipConn = null;

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

			wipConn = DBConnMgr.getInstance().getConnection(envId, DBConnMgr.APPL);
			List<TreeNode> planDetails = getPlanDetails(wipConn, param, viewChanges, planKey);
			return planDetails;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
		finally {
			if (wipConn != null) {
				try {
					DBConnMgr.getInstance().releaseConnection(wipConn);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<TreeNode> getPlanDetails(Connection wipConn, RestServiceParam param, boolean viewChanges, TreeNodePlanKey planKey)
		throws Exception
	{
		PlanMergeAssistent pmAssist = null;
		IndexMergeAssistent imAssist = null;

		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();
			String productPrefix = planKey.getProductPrefix();
			String productSuffix = planKey.getProductSuffix();

			HashMap<String, String> planCriteriaKey = new HashMap<>();
			planCriteriaKey.put(PlanTOBase.ENVIRONMENT_KEY, envId);
			planCriteriaKey.put(PlanTOBase.COMPANY_CODE_KEY, companyCode);
			planCriteriaKey.put(PlanTOBase.PRODUCT_CODE_KEY, productPrefix + productSuffix);
			planCriteriaKey.put(PlanTOBase.PRODUCT_PREFIX_KEY, productPrefix);
			planCriteriaKey.put(PlanTOBase.PRODUCT_SUFFIX_KEY, productSuffix);
			planCriteriaKey.put(PlanTOBase.PLAN_CODE_KEY, planKey.getPlanCode());
			planCriteriaKey.put(PlanTOBase.PLAN_TYPE_KEY, planKey.getPlanType());
			planCriteriaKey.put(PlanTOBase.ISSUE_STATE_KEY, planKey.getIssueState());
			planCriteriaKey.put(PlanTOBase.LINE_OF_BUSINESS_KEY, planKey.getLob());
			planCriteriaKey.put(PlanCriteriaTO.MERGED_VIEW_KEY, String.valueOf(viewChanges));
			LocalDate effDate = planKey.getEffDate();
			if (effDate != null)
				planCriteriaKey.put(PlanTOBase.EFFECTIVE_DATE_KEY, effDate.toString());

			MergeAssistant assist = new MergeAssistant().init(wipConn, planCriteriaKey, viewChanges);
			PlanCriteriaTO planCriteria = assist.getPlanCriteria();
			pmAssist = assist.getPmAssistant();
			imAssist = assist.getImAssistant();
			String payload = new ProductWriter(pmAssist, imAssist).getPlanDetailStream(wipConn, planCriteria);
			BufferedReader reader = new BufferedReader(new StringReader(payload));

			Node root = null;
			if ("H".equals(productPrefix) || "N".equals(productPrefix))
				root = new Node((short) 3);
			else
				root = new Node((short) 4);

			processTreeNode(reader, root, new TreeNodeContainer(), envId);

			List<Node> planDetails = root.getChildren();
			List<TreeNode> planDetailsTreeNodes = transformToDeclaredTypes(planDetails, false);
			return planDetailsTreeNodes;
		}
		finally {
			if (pmAssist != null)
				pmAssist.clean(wipConn);
			if (imAssist != null)
				imAssist.clean(wipConn);
		}
	}

	public List<TreeNode> getBusinessRuleTreeOrphanSubsets(RestServiceParam param, BusinessRuleTreeSearchInput searchInput)
	{
		Connection wipConn = null;

		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();
			String productCode = searchInput.getProductCode();

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

			if (!StringUtils.hasText(productCode) || productCode.length() != 2) {
				HttpStatus status = BadRequestException.HTTP_STATUS;
				ErrorModel model = errorModelFactory.newErrorModel(status, status.getReasonPhrase() + getMessage("missing_product"));
				throw new BadRequestException(model);
			}

			wipConn = DBConnMgr.getInstance().getConnection(envId, DBConnMgr.APPL);
			List<TreeNode> planDetails = getOrphans(wipConn, param, searchInput);
			return planDetails;
		}
		catch (RestServiceException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			ErrorModel model = errorModelFactory.newErrorModel(UnexpectedException.HTTP_STATUS);
			throw new UnexpectedException(model);
		}
		finally {
			if (wipConn != null) {
				try {
					DBConnMgr.getInstance().releaseConnection(wipConn);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	private List<TreeNode> getOrphans(Connection wipConn, RestServiceParam param, BusinessRuleTreeSearchInput searchInput)
		throws Exception
	{
		PlanMergeAssistent pmAssist = null;
		IndexMergeAssistent imAssist = null;

		try {
			String envId = param.getEnvId();
			String companyCode = param.getCompanyCode();
			String productCode = searchInput.getProductCode();
			String productPrefix = productCode.substring(0, 1);
			String productSuffix = productCode.substring(1);
			String planCode = searchInput.getPlanCode();
			String issueState = searchInput.getIssueState();
			String lob = searchInput.getLob();
			boolean viewChanges = searchInput.getViewChanges();

			String effDate = null;
			Date effectiveDate = searchInput.getEffDate();
			if (effectiveDate != null)
				effDate = effectiveDate.toString();

			HashMap<String, String> planCriteriaKey = new HashMap<>();
			planCriteriaKey.put(PlanTOBase.ENVIRONMENT_KEY, envId);
			planCriteriaKey.put(PlanTOBase.COMPANY_CODE_KEY, companyCode);
			planCriteriaKey.put(PlanTOBase.PRODUCT_CODE_KEY, productCode);
			planCriteriaKey.put(PlanTOBase.PRODUCT_PREFIX_KEY, productPrefix);
			planCriteriaKey.put(PlanTOBase.PRODUCT_SUFFIX_KEY, productSuffix);
			planCriteriaKey.put(PlanTOBase.PLAN_CODE_KEY, planCode);
			planCriteriaKey.put(PlanTOBase.ISSUE_STATE_KEY, issueState);
			planCriteriaKey.put(PlanTOBase.LINE_OF_BUSINESS_KEY, lob);
			planCriteriaKey.put(PlanTOBase.EFFECTIVE_DATE_KEY, effDate);
			planCriteriaKey.put(PlanCriteriaTO.MERGED_VIEW_KEY, String.valueOf(viewChanges));

			MergeAssistant assist = new MergeAssistant().init(wipConn, planCriteriaKey, viewChanges);
			pmAssist = assist.getPmAssistant();
			imAssist = assist.getImAssistant();

			OrphanTreeWriter orphanWriter = new OrphanTreeWriter();
			StringBuffer buf = new StringBuffer();
			orphanWriter.writeOrphans(envId, companyCode, productPrefix, wipConn, buf);
			String payload = buf.toString();

			BufferedReader reader = new BufferedReader(new StringReader(payload));
			Node root = new Node((short) 3);
			processTreeNode(reader, root, new TreeNodeContainer(), envId);

			List<Node> orphans = root.getChildren();
			List<TreeNode> orphansTreeNodes = transformToDeclaredTypes(orphans, false);
			return orphansTreeNodes;
		}
		finally {
			if (pmAssist != null)
				pmAssist.clean(wipConn);
			if (imAssist != null)
				imAssist.clean(wipConn);
		}
	}

	private void processTreeNode(BufferedReader reader, Node parentNode, TreeNodeContainer pushBack, String envId)
		throws IOException
	{
		// For reference, see method private void processTreeNode(CscTreeNode parentNode) in class CscTree
		short parentLevel = parentNode.getLevel();
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

	private List<TreeNode> transformToDeclaredTypes(List<Node> existingNodes, boolean isEachNodeLazy)
	{
		List<TreeNode> transformedNodes = new LinkedList<>();

		for (Node existingNode : existingNodes) {
			TreeNode transformedNode = new TreeNode();
			transformedNodes.add(transformedNode);
			transformedNode.setType(existingNode.getType());
			transformedNode.setLabel(existingNode.getDisplay());
			transformedNode.setData(existingNode.getData());

			if (isEachNodeLazy) {
				transformedNode.setLeaf(Boolean.FALSE);
				transformedNode.getData().setLazyNode(Boolean.TRUE);
				transformedNode.setStyleClass(STYLE_FOLDER);
				setFolderIcons(transformedNode);
			}
			else {
				// The flag isEachNodeLazy is overridden with false for each subsequent level
				List<TreeNode> transformedChildren = transformToDeclaredTypes(existingNode.getChildren(), false);
				transformedNode.getChildren().addAll(transformedChildren);

				if (transformedChildren.isEmpty()) {
					transformedNode.setStyleClass(STYLE_LEAF);
					transformedNode.setIcon(ICON_LEAF);
				}
				else {
					transformedNode.setStyleClass(STYLE_FOLDER);
					setFolderIcons(transformedNode);
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

	static private class MergeAssistant
	{
		private PlanCriteriaTO planCriteria = null;
		private PlanMergeAssistent pmAssist = null;
		private IndexMergeAssistent imAssist = null;

		private MergeAssistant init(Connection wipConn, HashMap<String, String> planCriteriaKey, boolean viewChanges)
			throws Exception
		{
			planCriteria = new PlanCriteriaTO(planCriteriaKey);
			planCriteria.setLoadNP(true);
			PlanCriteriaTO indexCriteria = new PlanCriteriaTO(planCriteria);
			indexCriteria.setLoadNP(true);

			// Read Plan Table T000X
			List<PlanCriteriaTO> planKeyList = new LinkedList<>();
			planKeyList.add(planCriteria);
			pmAssist = new PlanMergeAssistent(wipConn, planKeyList, true);

			// Read Subset Index Table T000XA
			String view = viewChanges ? "with" : "without";
			imAssist = new IndexMergeAssistent(wipConn, indexCriteria.toHashMap(), true, view, true);

			return this;
		}

		private PlanCriteriaTO getPlanCriteria()
		{
			return planCriteria;
		}

		private PlanMergeAssistent getPmAssistant()
		{
			return pmAssist;
		}

		private IndexMergeAssistent getImAssistant()
		{
			return imAssist;
		}
	}
}
