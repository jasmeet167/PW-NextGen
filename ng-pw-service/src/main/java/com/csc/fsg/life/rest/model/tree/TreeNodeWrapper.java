package com.csc.fsg.life.rest.model.tree;

import com.csc.fsg.life.pw.web.actions.tree.TreeAuthorizationTracer;
import com.csc.fsg.life.rest.model.TreeNode;
import com.csc.fsg.life.rest.model.TreeNodeData;
import com.csc.fsg.life.rest.model.TreeNodePlanKey;

public class TreeNodeWrapper
	extends TreeNode
{
	private int authTracer = 0;
	private String authTableId = null;

	public TreeNodeWrapper()
	{
		super();
	}

	public void deriveAuthTableId()
	{
		switch (authTracer) {
			case TreeAuthorizationTracer.COMPANY_1:
			case TreeAuthorizationTracer.COMMMON_TABLES_1:
			case TreeAuthorizationTracer.ORPHAN_TREE_1: {
				break;
			}
			case TreeAuthorizationTracer.COMPANY_2: {
				authTableId = "00X";
				break;
			}
			case TreeAuthorizationTracer.PRODUCT_1: {
				authTableId = "000";
				break;
			}
			case TreeAuthorizationTracer.COMMMON_TABLES_2:
			case TreeAuthorizationTracer.COMMMON_TABLES_3:
			case TreeAuthorizationTracer.ORPHAN_TREE_2:
			case TreeAuthorizationTracer.ORPHAN_TREE_3: {
				TreeNodeData data = getData();
				if (data != null) {
					authTableId = data.getTableId();
				}
				break;
			}
			case TreeAuthorizationTracer.PRODUCT_2:
			case TreeAuthorizationTracer.PRODUCT_3: {
				TreeNodeData data = getData();
				if (data != null) {
					TreeNodePlanKey key = data.getPlanKey();
					if (key != null) {
						authTableId = getData().getPlanKey().getTablePtrId();
					}
				}
				break;
			}
			default: {
				break;
			}
		}
	}

	public boolean isSecured()
	{
		return authTracer != 0;
	}

	public int getAuthTracer()
	{
		return authTracer;
	}

	public void setAuthTracer(int authTracer)
	{
		this.authTracer = authTracer;
	}

	public String getAuthTableId()
	{
		return authTableId;
	}
}
