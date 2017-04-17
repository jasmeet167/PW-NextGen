package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.BusinessRulesTreeSearchInput;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface BusinessRulesTreeService
{
	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRulesTreeCore(RestServiceParam param, String productCode, boolean includeOrphans);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRulesTreeCommonTables(RestServiceParam param, boolean includeChanges);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRulesTreePlanList(RestServiceParam param, BusinessRulesTreeSearchInput searchInput);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRulesTreePlanDetails(RestServiceParam param, TreeNodePlanKey planKey, boolean viewChanges);

	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRulesTreeOrphanSubsets(RestServiceParam param, BusinessRulesTreeSearchInput searchInput);
}
