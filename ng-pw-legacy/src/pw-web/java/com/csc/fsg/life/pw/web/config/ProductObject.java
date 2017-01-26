
package com.csc.fsg.life.pw.web.config;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.Document;

import com.csc.fsg.life.avm.environments.AVMConfigBean;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.common.util.InstallConfigBean;
import com.csc.fsg.life.pw.web.actions.rcm.beans.ExternalAuditBean;
import com.csc.fsg.life.pw.web.actions.tree.CommonTablesWriter;

/* Modifications: T0111, ENH946, T0129 */
// ENH946 - remove UL-specific fund handling

public class ProductObject {
	
	private String id = null;
	private String name = null;
	private String version = null;
	private String colorTheme = "WMA";
	// use constants for fund info defaults
	private String maxFund = Constants.MAX_FUND_NUMBER;
	private String maxFundDigits = Constants.MAX_FUND_DIGITS;
	private String defaultFund = Constants.DEFAULT_FUND;
	
	private InstallConfigBean installBean = null;
	private ExternalAuditBean extAuditBean = null;
	private Node commonTreeRoot = null;
	
	public AVMConfigBean getAvmBean() {
		return AppConfig.getAvmBean();
	}
	
	public String getColorTheme() {
		return colorTheme;
	}
	public void setColorTheme(String colorTheme) {
		this.colorTheme = colorTheme;
	}
	public String getDefaultFund() {
		return defaultFund;
	}
	public void setDefaultFund(String defaultFund) {
		this.defaultFund = defaultFund;
	}
	public InstallConfigBean getInstallBean() {
		return installBean;
	}
	public void setInstallBean(InstallConfigBean installBean) {
		this.installBean = installBean;
	}
	public String getMaxFund() {
		return maxFund;
	}
	public void setMaxFund(String maxFund) {
		this.maxFund = maxFund;
	}
	public String getMaxFundDigits() {
		return maxFundDigits;
	}
	public void setMaxFundDigits(String maxFundDigits) {
		this.maxFundDigits = maxFundDigits;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	
	public ExternalAuditBean getExtAuditBean() {
		return extAuditBean;
	}
	public void setExtAuditBean(ExternalAuditBean extAuditBean) {
		this.extAuditBean = extAuditBean;
	}
	
	public synchronized Node getCommonTreeRoot() throws Exception {
		if (commonTreeRoot == null) {
			String archTablesFile = getId().toLowerCase() + "ArchTables.xml";
			InputStream is = CommonTablesWriter.class.getResourceAsStream("/treeStructure/"
			        + archTablesFile);
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
			        .newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(is);
			commonTreeRoot = doc.getElementsByTagName("ARCHITECTURE_TABLES").item(0);
		}
		return commonTreeRoot;
	}
}
