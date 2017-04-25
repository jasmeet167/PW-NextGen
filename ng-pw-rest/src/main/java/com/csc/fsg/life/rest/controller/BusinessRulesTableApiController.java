package com.csc.fsg.life.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.csc.fsg.life.rest.api.BusinessRulesTableApi;
import com.csc.fsg.life.rest.model.TableModel;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;
import com.csc.fsg.life.rest.param.RestServiceParam;
import com.csc.fsg.life.rest.service.BusinessRulesTableService;

@Controller
public class BusinessRulesTableApiController
	extends RestApiController
	implements BusinessRulesTableApi
{
	@Autowired
	private BusinessRulesTableService businessRulesTableService = null;

	@RequestMapping(value = "/business-rules-table/inquiry/first", produces = { "application/json" }, consumes = { "application/json" }, method = RequestMethod.POST)
	public ResponseEntity<TableModel> getBusinessRulesTableFirstPage(@RequestHeader(value = "authToken", required = true) String authToken,
																	 @RequestHeader(value = "viewChanges", required = true) Boolean viewChanges,
																	 @RequestHeader(value = "envId", required = true) String envId,
																	 @RequestHeader(value = "companyCode", required = true) String companyCode,
																	 @RequestHeader(value = "productCode", required = true) String productCode,
																	 @RequestHeader(value = "tableName", required = true) String tableName,
																	 @RequestHeader(value = "tableSubset", required = true) String tableSubset,
																	 @RequestBody TreeNodePlanKey planKey)	
	{
		String productPrefix = null;
		if (productCode != null && productCode.length() > 0)
			productPrefix = productCode.substring(0, 1);
		boolean includeChanges = Boolean.TRUE.equals(viewChanges);

		RestServiceParam param = buildRestServiceParam(authToken, envId, companyCode);
		TableModel tableModel = businessRulesTableService.getBusinessRulesTable(param, includeChanges, productPrefix, tableName, tableSubset, planKey);
		return new ResponseEntity<>(tableModel, HttpStatus.OK);
	}
}
