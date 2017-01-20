package com.csc.fsg.life.biz.service;

import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import com.csc.fsg.life.common.util.ResponseTimeMonitor;

/* Modifications: P0157 */

public class ServiceManagerResponseTimeMonitor
{
	ResponseTimeMonitor responseTimeMonitor;
	StopWatch stopWatch;
	
	public ServiceManagerResponseTimeMonitor(ResponseTimeMonitor responseTimeMonitor, StopWatch stopWatch) {
		this.responseTimeMonitor = responseTimeMonitor;
		this.stopWatch = stopWatch;
	}

	public ResponseTimeMonitor getResponseTimeMonitor() {
		return responseTimeMonitor;
	}

	public StopWatch getStopwatch() {
		return stopWatch;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();		
		TaskInfo[] tasks = stopWatch.getTaskInfo();
		for (int i = 0; i < tasks.length; i++) {
			sb.append("\t"+tasks[i].getTaskName() + "\n");
			sb.append("\t"+responseTimeMonitor.toString());
			break;
		}	
		return sb.toString();
	}
}