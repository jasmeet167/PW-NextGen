package com.csc.fsg.life.biz.dao;

import org.apache.ibatis.executor.result.DefaultResultHandler;
import org.apache.ibatis.session.ResultContext;

import com.csc.fsg.life.biz.bo.BaseBusinessObject;
import com.csc.fsg.life.tools.xml.simple.SimpleXMLEncoder;

/* Modifications: T0199 */

public class ObjectStateResultHandler
	extends DefaultResultHandler
{
	@Override
	public void handleResult(ResultContext<?> context)
	{
		super.handleResult(context);

		if (context.getResultObject() instanceof BaseBusinessObject) {
			BaseBusinessObject bo = (BaseBusinessObject) context.getResultObject();

			// set object's initial state
			SimpleXMLEncoder encoder = new SimpleXMLEncoder();
			String state = encoder.buildXML(bo);
			bo.setInitialState(state);
		}
	}
}