package com.csc.fsg.life.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class abstracts Install.properties which permits the client to
 * configure the application based on the products and components installed.
 */
public class InstallProperties {

	private static ExtProperties props = null;
	private static final InstallProperties INSTANCE = new InstallProperties();

	static {
		try {
			props = new ExtProperties("install.properties");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (IllegalArgumentException iae) {
			iae.printStackTrace();
		}
	}
	private InstallProperties() {}

	/**
	 * Method getInstance 
	 * @return The singleton instance.
	 */
	public static synchronized InstallProperties getInstance() {
		return (INSTANCE);
	}

	/**
	   Returns true if product wizard is installed, false if not.
	   @return  true if product wizard is installed, false if not.
	 */
	public boolean isProdWizInstalled() {

		if (props.getProperty("PW").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if OTA is installed, false if not.
	   @return  true if OTA is installed, false if not.
	 */
	public boolean isOTAInstalled() {

		if (props.getProperty("OTA").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if FAV is installed, false if not.
	   @return true if FAV is installed, false if not.
	 */
	public boolean isFAVInstalled() {

		if (props.getProperty("FAV").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if HCC is installed, false if not.
	   @return  true if HCC is installed, false if not.
	 */
	public boolean isHCCInstalled() {

		if (props.getProperty("HCC").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if 24x7 is installed, false if not.
	   @return  true if 24x7 is installed, false if not.
	 */
	public boolean is24X7Installed() {

		if (props.getProperty("24X7").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if Output Management is installed, false if not.
	   @return true if Output Management is installed, false if not.
	 */
	public boolean isOPMgmtInstalled() {

		if (props.getProperty("OPMGMT").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if Compensation Designer is installed, false if not.
	   @return  true if Compensation Designer is installed, false if not.
	 */
	public boolean isCompDesignInstalled() {

		if (props.getProperty("CD").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if UL Product is installed, false if not.
	   @return true if UL Product is installed, false if not.
	 */
	public boolean isULProdInstalled() {

		if (props.getProperty("UL").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if Annuity Product is installed, false if not.
	   @return  true if Annuity Product is installed, false if not.
	 */
	public boolean isAnnuityProdInstalled() {

		if (props.getProperty("ANNUITY").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if Traditional Product is installed, false if not.
	   @return true if Traditional Product is installed, false if not.
	 */
	public boolean isTradProdInstalled() {

		if (props.getProperty("TRAD").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if NBU is installed, false if not.
	   @return true if NBU is installed, false if not.
	 */
	public boolean isNBUProdInstalled() {

		if (props.getProperty("NBU").equalsIgnoreCase("true")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	   Returns true if the requested product is installed, false if not.
	   @param pPrefix  Valid values are A (Annuities), U (Universal Life), T (Traditional)
	   or H (HCC).
	   @return  true if the requested product is installed, false if not.
	 */
	public boolean isProductInstalled(String pPrefix) {

		boolean install = false;

		if (pPrefix.equals("A") && isAnnuityProdInstalled()) {
			install = true;
		} else if (pPrefix.equals("U") && isULProdInstalled()) {
			install = true;
		} else if (pPrefix.equals("T") && isTradProdInstalled()) {
			install = true;
		} else if (pPrefix.equals("H") && isHCCInstalled()) {
			install = true;
		} else if (pPrefix.equals("N")) {
			install = true;
		}
		return install;
	}

	/**
	   Returns a list of the installed products.
	   @return Returns a list of the installed products.
	 */
	public ArrayList<String> getInstalledProducts() {

		ArrayList<String> al = new ArrayList<String>();
		al.add("N");
		if (isAnnuityProdInstalled()) {
			al.add("A");
		}
		if (isULProdInstalled()) {
			al.add("U");
		}
		if (isTradProdInstalled()) {
			al.add("T");
		}
		if (isHCCInstalled()) {
			al.add("H");
		}
		return al;
	}

	/**
	   Returns a list of valid products.
	**/
	@SuppressWarnings("unchecked")
	public ArrayList getDefaultProductList() {
		ArrayList<String> al = getInstalledProducts();
		ArrayList list = new ArrayList(al.size());
		for (int i = 0; i < al.size(); i++) {
			list.add(al.get(i) +"*");
		}
		return list;
	}

	/**
	 * Dumps installed product list to a string. 
	 * @param out
	 */
	public void toString(PrintWriter out) {

		StringBuffer sb = new StringBuffer(55);

		sb.append(is24X7Installed());
		sb.append("\t");
		sb.append(isAnnuityProdInstalled());
		sb.append("\t");
		sb.append(isCompDesignInstalled());
		sb.append("\t");
		sb.append(isFAVInstalled());
		sb.append("\t");
		sb.append(isHCCInstalled());
		sb.append("\t");
		sb.append(isNBUProdInstalled());
		sb.append("\t");
		sb.append(isOPMgmtInstalled());
		sb.append("\t");
		sb.append(isOTAInstalled());
		sb.append("\t");
		sb.append(isProdWizInstalled());
		sb.append("\t");
		sb.append(isTradProdInstalled());
		sb.append("\t");
		sb.append(isULProdInstalled());
		out.print(sb.toString());
	}
}
