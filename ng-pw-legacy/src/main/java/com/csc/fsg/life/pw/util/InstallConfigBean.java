package com.csc.fsg.life.pw.util;

import java.util.*;

/* Modifications: T0091, CCCV-E501, T0129, WMA-1009 */
// CCCV-E501 - replace payout with v1mode

public class InstallConfigBean {

	public static String TAB = "\t";

	public boolean ul = false;

	public boolean annuity = false;

	public boolean trad = false;
	
	public boolean v1mode = false;


	private InstallConfigBean() {
	}

	public boolean getAnnuity() {
		return annuity;
	}

	public void setAnnuity(boolean annuity) {
		this.annuity = annuity;
	}

	public boolean getHcc() {
		return true;
	}

	public void setHcc(boolean hcc) {
	}

	public boolean getNbu() {
		return true;
	}

	public void setNbu(boolean nbu) {
	}

	public boolean getTrad() {
		return trad;
	}

	public void setTrad(boolean trad) {
		this.trad = trad;
	}

	public boolean getTwentyfourXseven() {
		return true;
	}

	public void setTwentyfourXseven(boolean twentyfourXseven) {
	}

	public boolean getUl() {
		return ul;
	}

	public void setUl(boolean ul) {
		this.ul = ul;
	}

	public boolean isProductInstalled(String pPrefix) {
		boolean install = false;
		if (pPrefix.equals("A") && getAnnuity()) {
			install = true;
		} else if (pPrefix.equals("U") && getUl()) {
			install = true;
		} else if (pPrefix.equals("T") && getTrad()) {
			install = true;
		} else if (pPrefix.equals("H") && getHcc()) {
			install = true;
		} else if (pPrefix.equals("N")) {
			install = true;
		}
		return install;
	}

	public ArrayList<String> getInstalledProducts() {

		ArrayList<String> al = new ArrayList<String>();
		al.add("N");
		if (getAnnuity()) {
			al.add("A");
		}
		if (getUl()) {
			al.add("U");
		}
		if (getTrad()) {
			al.add("T");
		}
		if (getHcc()) {
			al.add("H");
		}
		return al;
	}

	public ArrayList<String> getDefaultProductList() {
		ArrayList<String> al = getInstalledProducts();
		ArrayList<String> list = new ArrayList<String>(al.size());
		for (int i = 0; i < al.size(); i++) {
			list.add(al.get(i) + "*");
		}
		return list;
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(getUl()).append(TAB);
		sb.append(getAnnuity()).append(TAB);
		sb.append(getTrad()).append(TAB);
		return sb.toString();
	}

	public InstallConfigBean(String s) {
		StringTokenizer tokenizer = new StringTokenizer(s, "\t");
		setUl(tokenizer.nextToken().equals("true"));
		setAnnuity(tokenizer.nextToken().equals("true"));
		setTrad(tokenizer.nextToken().equals("true"));
	}

	public boolean isV1mode() {
		return v1mode;
	}

	public void setV1mode(boolean v1mode) {
		this.v1mode = v1mode;
	}

}
