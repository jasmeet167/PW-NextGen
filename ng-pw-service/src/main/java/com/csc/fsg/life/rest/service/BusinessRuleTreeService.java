package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface BusinessRuleTreeService
{
	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRuleTreeCore(RestServiceParam param, String productCode, boolean includeOrphans);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRuleTreeCommonTables(RestServiceParam param, boolean includeChanges);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRuleTreePlanList(RestServiceParam param, BusinessRuleTreeSearchInput searchInput);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRuleTreePlanDetails(RestServiceParam param, TreeNodePlanKey planKey, boolean viewChanges);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRulesTree(RestServiceParam param, BusinessRuleTreeSearchInput searchInput);
}
