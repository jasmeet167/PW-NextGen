package com.csc.fsg.life.biz.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.springframework.aop.interceptor.AbstractMonitoringInterceptor;
import org.springframework.util.StopWatch;

import com.csc.fsg.life.common.config.ApplicationEnvironmentBean;
import com.csc.fsg.life.common.util.ResponseTimeMonitor;

/* Modifications: P0157 */

public class WMAPerformanceMonitorInterceptor 
	extends	AbstractMonitoringInterceptor 
	implements WMAPerformanceMonitor 
{
	Map<String, ServicesPerformanceStat> methodTimerMaps = new HashMap<String, ServicesPerformanceStat>();

	protected Object invokeUnderTrace(MethodInvocation invocation, Log logger)
			throws Throwable {
		String methodName = createInvocationTraceName(invocation);		
		StopWatch stopWatch = new StopWatch(methodName);		
		ServicesPerformanceStat servicesPerformanceStat = (ServicesPerformanceStat) getServicesPerformanceStat(methodName, stopWatch);
		createChildStopWatches(methodName, servicesPerformanceStat);
		stopWatch.start(methodName);
		try {
			return invocation.proceed();
		} catch (Throwable t) {
			//TODO record exception			
			throw t;
		} finally {			
			stopWatch.stop();			
			ResponseTimeMonitor responseTimeMonitor = servicesPerformanceStat.getResponseTimeMonitor();
			responseTimeMonitor.recordResponseTime(stopWatch.getLastTaskTimeMillis());			
		}
	}

	/**
	 * @param methodName
	 * @param servicesPerformanceStat
	 */
	private void createChildStopWatches(String methodName,
			ServicesPerformanceStat servicesPerformanceStat) {
		StopWatch xgreposInsertStopwatch = new StopWatch(methodName + " - XGReposInsert");
		StopWatch smResponseStopwatch = new StopWatch(methodName + " - SMResponse");
		//xgReposResponseTimeMonitor 
		XgReposInsertResponseTimeMonitor xgReposInsertResTimeMonitor = servicesPerformanceStat.getXgReposInsertTimerMap(methodName, xgreposInsertStopwatch);
		//ServiceManagerResponseTimeMonitor
		ServiceManagerResponseTimeMonitor smResTimeMonitor = servicesPerformanceStat.getSmResTimerMap(methodName, smResponseStopwatch);
		
		ApplicationEnvironmentBean apEnvironmentBean = ApplicationEnvironmentBean.getEnvironment();		
		if(apEnvironmentBean != null) {
			ServiceManager sm = apEnvironmentBean.getServiceManager();
			sm.setXgReposInsertResTimeMonitor(xgReposInsertResTimeMonitor);
			sm.setSmResTimeMonitor(smResTimeMonitor);
		}
	}

	private ServicesPerformanceStat getServicesPerformanceStat(
			String methodName, StopWatch stopWatch) {
		if (methodTimerMaps.get(methodName) != null) {
			ServicesPerformanceStat servicesPerformanceStat = (ServicesPerformanceStat) methodTimerMaps
					.get(methodName);
			return servicesPerformanceStat;
		} else {
			ResponseTimeMonitor responseTimeMonitorImpl = new ResponseTimeMonitor();
			ServicesPerformanceStat servicesPerformanceStat = new ServicesPerformanceStat(responseTimeMonitorImpl, stopWatch);
			methodTimerMaps.put(methodName, servicesPerformanceStat);
			return servicesPerformanceStat;
		}
	}
	
	@SuppressWarnings("unchecked")
	public String getDetailPerformanceStat() 
	{
		StringBuffer sb = new StringBuffer();
		sb.append('\n');
		Set set = methodTimerMaps.entrySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Map.Entry nextEntry = (Map.Entry)iterator.next();
			ServicesPerformanceStat type = (ServicesPerformanceStat) nextEntry.getValue();
			sb.append(type.toString());
		} 
		return sb.toString();
	}
}
