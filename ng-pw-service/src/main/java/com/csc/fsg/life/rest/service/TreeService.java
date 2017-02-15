package com.csc.fsg.life.rest.service;

import java.util.List;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.BusinessRuleTreeSearchInput;
import com.csc.fsg.life.rest.model.tree.TreeNode;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface TreeService
{
	@SecuredMethod(action = AuthorizationAction.VIEW)
	public List<TreeNode> getBusinessRulesTree(RestServiceParam param, BusinessRuleTreeSearchInput input);
}
