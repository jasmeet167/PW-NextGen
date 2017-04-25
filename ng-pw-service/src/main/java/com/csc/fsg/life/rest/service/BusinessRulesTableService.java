package com.csc.fsg.life.rest.service;

import com.csc.fsg.life.rest.annotation.SecuredMethod;
import com.csc.fsg.life.rest.model.TableModel;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.param.AuthorizationAction;
import com.csc.fsg.life.rest.param.RestServiceParam;

public interface BusinessRulesTableService
{
	@SecuredMethod(action = AuthorizationAction.VIEW)
	public TableModel getBusinessRulesTable(RestServiceParam param, boolean viewChanges, String productPrefix, String tableName, String tableSubset, TreeNodePlanKey planKey);
}
