/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.web.actions.tree;

/**
 * Class PlanBuffer
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

public class PlanBuffer {

	private StringBuffer basePlans, riderPlans, archPlans, payoutPlans;

	public PlanBuffer() {
		basePlans = new StringBuffer();
		riderPlans = new StringBuffer();
		archPlans = new StringBuffer();
		payoutPlans = new StringBuffer();
	}

	public void appendBase(String s) {
		basePlans.append(s);
	}

	public void appendRider(String s) {
		riderPlans.append(s);
	}

	public void appendArch(String s) {
		archPlans.append(s);
	}

	public void appendPayout(String s) {
		payoutPlans.append(s);
	}

	public String getBase() {
		return basePlans.toString();
	}

	public String getRider() {
		return riderPlans.toString();
	}

	public String getArch() {
		return archPlans.toString();
	}

	public String getPayoutPlans() {
		return payoutPlans.toString();
	}

	public String getHcc() {
		return basePlans.toString();
	}
}
