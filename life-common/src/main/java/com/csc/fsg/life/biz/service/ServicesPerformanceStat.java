package com.csc.fsg.life.biz.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;
import org.springframework.util.StopWatch.TaskInfo;

import com.csc.fsg.life.common.util.ResponseTimeMonitor;

/* Modifications: P0157 */

public class ServicesPerformanceStat {
	ResponseTimeMonitor responseTimeMonitor;
	StopWatch stopWatch;	
	Map<String, XgReposInsertResponseTimeMonitor> xgReposInsertTimerMap = new HashMap<String, XgReposInsertResponseTimeMonitor>();
	Map<String, ServiceManagerResponseTimeMonitor> smResponseTimeMonitorMap = new HashMap<String, ServiceManagerResponseTimeMonitor>();

	public ServicesPerformanceStat(ResponseTimeMonitor responseTimeMonitor, StopWatch stopWatch) {
		this.responseTimeMonitor = responseTimeMonitor;
		this.stopWatch = stopWatch;
	}
	
	public XgReposInsertResponseTimeMonitor getXgReposInsertTimerMap(
			String methodName, StopWatch stopWatch) {
		if (xgReposInsertTimerMap.get(methodName) != null) {
			XgReposInsertResponseTimeMonitor xgReposInsertResponseTimeMonitor = (XgReposInsertResponseTimeMonitor) xgReposInsertTimerMap
					.get(methodName);
			return xgReposInsertResponseTimeMonitor;
		} else {
			ResponseTimeMonitor responseTimeMonitor = new ResponseTimeMonitor();
			XgReposInsertResponseTimeMonitor xgReposInsertResponseTimeMonitor = new XgReposInsertResponseTimeMonitor(
					responseTimeMonitor, stopWatch);
			xgReposInsertTimerMap.put(methodName, xgReposInsertResponseTimeMonitor);
			return xgReposInsertResponseTimeMonitor;
		}
	}	
	
	public ServiceManagerResponseTimeMonitor getSmResTimerMap(
			String methodName, StopWatch stopWatch) {
		if (smResponseTimeMonitorMap.get(methodName) != null) {
			ServiceManagerResponseTimeMonitor smResponseTimeMonitor = (ServiceManagerResponseTimeMonitor) smResponseTimeMonitorMap
					.get(methodName);
			return smResponseTimeMonitor;
		} else {
			ResponseTimeMonitor responseTimeMonitor = new ResponseTimeMonitor();
			ServiceManagerResponseTimeMonitor smResponseTimeMonitor = new ServiceManagerResponseTimeMonitor(
					responseTimeMonitor, stopWatch);
			smResponseTimeMonitorMap.put(methodName, smResponseTimeMonitor);
			return smResponseTimeMonitor;
		}
	}	

	public ResponseTimeMonitor getResponseTimeMonitor() {
		return responseTimeMonitor;
	}

	public StopWatch getStopwatch() {
		return stopWatch;
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append('\n');
		TaskInfo[] tasks = stopWatch.getTaskInfo();
		for (int i = 0; i < tasks.length; i++) {
			String task = StringUtils.unqualify(tasks[i].getTaskName());
			sb.append("Method - " + task + "\n");
			sb.append(responseTimeMonitor.toString());
		}		
		sb.append('\n');
		Set set = xgReposInsertTimerMap.entrySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Map.Entry nextEntry = (Map.Entry)iterator.next();
			XgReposInsertResponseTimeMonitor type = (XgReposInsertResponseTimeMonitor) nextEntry.getValue();
			sb.append("\t XgReposInsert Time"+  type.toString());			
		}		
		sb.append('\n');
		set = smResponseTimeMonitorMap.entrySet();
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			Map.Entry nextEntry = (Map.Entry)iterator.next();
			ServiceManagerResponseTimeMonitor type = (ServiceManagerResponseTimeMonitor) nextEntry.getValue();
			sb.append("\t Total ServiceManager Time "+  type.toString());			
		} 
		return sb.toString();
		
	}		

}
