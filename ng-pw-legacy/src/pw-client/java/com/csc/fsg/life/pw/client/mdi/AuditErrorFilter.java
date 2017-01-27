/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.client.mdi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JTextField;

import org.apache.commons.logging.Log;

import com.csc.fsg.life.common.util.DataException;
//import com.csc.fsg.life.pw.client.PWClientLogManager;
//import com.csc.fsg.life.pw.client.ProductWizardApplet;
//import com.csc.fsg.life.pw.client.table.CscDate;
import com.csc.fsg.life.pw.client.util.*;
import com.csc.fsg.life.pw.common.*;
import com.csc.fsg.life.pw.common.util.Constants;

/* Modifications: ENH874, T0091, ENH961, WMABASEIXI-2722, WMABASEIXI-4717, T0129, WMA-2214 */

/**
 * Class AuditErrorFilter
 * 
 * @author CSC-FSG,E.Hartford
 * @version PW 2.0 , 09-24-2002
 */

//public class AuditErrorFilter extends AuditErrorFilterGUI implements
//		java.awt.event.ActionListener, FocusListener {
public class AuditErrorFilter {

	public static final String AUDIT_LOG = "AuditView";

	public static final String ERROR_LOG = "ErrorView";

	public static final String ERROR = "Error Info";

	private static SimpleDateFormat _format1;

	private int count = 0;

	private Vector<Integer> priority = null;

	private String schemaName = null;

	private String logFlag = AUDIT_LOG;

	private String _schema;

//	private HttpComm _httpComm;

	private Hashtable<String, String> filter = new Hashtable<String, String>();

//	private static Log _log = PWClientLogManager.getLog();

	// --------------------------------------------------------------------------------------------------
	// CONSTRUCTORS
	// --------------------------------------------------------------------------------------------------

	/**
	 * Constructor AuditErrorFilter
	 * 
	 * @param parent
	 * @param httpComm
	 */

//	public AuditErrorFilter(ProductWizardApplet parent, HttpComm httpComm) {
//		super(parent);
//		_httpComm = httpComm;
//		initializeComponents(_parent.getAuthorizedEnvironments());
//	}

	static {
		_format1 = new SimpleDateFormat("MM-dd-yyyy");
		_format1.setLenient(false);
	}

//	private void initializeComponents(Vector<EnvironmentElement> env) {
//
//		// EnvironmentElement prompt = new EnvironmentElement(0, "", "");
//		// _environments.addItem(prompt);
//		//
//		//			
//		// for (int i = 0; i < env.size(); i++)
//		// _environments.addItem(env.get(i));
//		//
//		// _environments.setEditable(false);
//		// _environments.setSelectedIndex(0);
//
//		ActionListener al = new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				if (_environments.getSelectedIndex() > 0) {
//					EnvironmentElement env = (EnvironmentElement) _environments
//							.getSelectedItem();
//					_schema = env.getSchema();
//					schemaName = env.getEnvironmentName();
//					populateFilters();
//				} else {
//					resetFormData(false);
//					_goButton.setEnabled(false);
//					_resetButton.setEnabled(false);
//				}
//			}
//		};
//
//		_environments.addActionListener(al);
//
//		_goButton.addActionListener(this);
//		_resetButton.addActionListener(this);
//		_auditRadio.addActionListener(this);
//		_errorRadio.addActionListener(this);
//		_packagePry.addFocusListener(this);
//		_projectPry.addFocusListener(this);
//		_userIdPry.addFocusListener(this);
//		_rulesPry.addFocusListener(this);
//
//	}

	/**
	 * Method focusLost
	 * 
	 * @param e
	 */

//	public void focusLost(FocusEvent e) {
//		String fieldName = ((JTextField) e.getSource()).getName();
//		String s = ((JTextField) e.getSource()).getText();
//		int i = 0;
//
//		if (s.trim().length() != 0) {
//			i = Integer.parseInt(s);
//			if (i < 1 || i > 4) {
//				((JTextField) e.getSource()).setText("");
//				CscModalDialog.showInfo("Sort Priority number should be from 1 through 4.", "Error");
//				enableToggleButton(fieldName, false);
//			} else {
//				enableToggleButton(fieldName, true);
//			}
//		} else {
//			enableToggleButton(fieldName, false);
//		}
//	}

	/**
	 * Method focusGained
	 * 
	 * @param e
	 */

	public void focusGained(FocusEvent e) {
	}

	/**
	 * Method enableToggleButton
	 * 
	 * @param name
	 * @param flag
	 */

//	public void enableToggleButton(String name, boolean flag) {
//
//		name = name.trim();
//		if (name.equalsIgnoreCase("projPry")) {
//			_projectOrder.setEnabled(flag);
//		} else if (name.equalsIgnoreCase("pkgePry")) {
//			_packageOrder.setEnabled(flag);
//		} else if (name.equalsIgnoreCase("rulePry")) {
//			_rulesOrder.setEnabled(flag);
//		} else if (name.equalsIgnoreCase("userPry")) {
//			_userIdOrder.setEnabled(flag);
//		}
//
//	}

	/**
	 * Method actionPerformed
	 * 
	 * @param event
	 */

//	public void actionPerformed(java.awt.event.ActionEvent event) {
//
//		if (((event.getActionCommand()).trim())
//				.equalsIgnoreCase("View Error Log")) {
//			initializeEnvironments();
//			_datesPanel.setVisible(false);
//			logFlag = ERROR_LOG;
//			resetFormData(true); // WMA TT SPR# 571 - Reset Audit/Error
//			// Filters
//		} else if (((event.getActionCommand()).trim())
//				.equalsIgnoreCase("View Audit Log")) {
//			initializeEnvironments();
//			_datesPanel.setVisible(true);
//			logFlag = AUDIT_LOG;
//			resetFormData(true); // WMA TT SPR# 571 - Reset Audit/Error
//			// Filters
//		} else if (((event.getActionCommand()).trim()).equalsIgnoreCase("Go")) {
//			try {
//				if (verifySortOrderAndPriority()) {
//
//					if (logFlag.equals(AuditErrorFilter.AUDIT_LOG)) {
//						if (!verifyDates()) {
//							return;
//						}
//						String stDate = (_startDate.getText()).trim();
//						String endDt = (_endDate.getText()).trim();
//
//						// Retrofit CCC SPR#V-D361 - Refactored to handle dates
//						// correctly.
//						if (stDate.length() != 0) {
//							_httpComm.addFormParameter("StDate", stDate);
//							filter.put("StDate", stDate);
//							if (endDt.length() == 0) {
//								_httpComm.addFormParameter("EndDate", ""
//										+ new Date());
//								filter.put("EndDate", _format1.format(new Date()));
//							}
//						} else { // stDate.length() == 0
//							filter.remove("StDate");
//							if ((endDt.length() == 0)) {
//								filter.remove("EndDate");
//							}
//						}
//
//						if (endDt.length() != 0) {
//							_httpComm.addFormParameter("EndDate", endDt);
//							filter.put("EndDate", endDt);
//						}
//
//						_httpComm.addFormParameter("Source", Constants.AUDIT);
//						filter.put("Source", Constants.AUDIT);
//					} else {
//						_httpComm.addFormParameter("Source",
//								AuditErrorFilter.ERROR);
//						filter.put("Source", AuditErrorFilter.ERROR);
//					}
//
//					String pkgList = "", prjList = "", tabList = "", usrList = "";
//
//					if (_packageList.getSelections() != null) {
//						pkgList = RCMClientUtilities.getDelimitedString(
//								_packageList.getSelections(), "\t");
//					}
//
//					if (_projectsList.getSelections() != null) {
//						prjList = RCMClientUtilities.getDelimitedString(
//								_projectsList.getSelections(), "\t");
//					}
//
//					if (_busRulesList.getSelections() != null) {
//						tabList = RCMClientUtilities.getDelimitedString(
//								_busRulesList.getSelections(), "\t");
//					}
//
//					if (_userIdList.getSelections() != null) {
//						usrList = RCMClientUtilities.getDelimitedString(
//								_userIdList.getSelections(), "\t");
//					}
//
//					/* ENH874 - Don't send all projects if nothing selected
//					if (_packageList.getSelections() == null
//							&& _projectsList.getSelections() == null
//							&& _busRulesList.getSelections() == null
//							&& _userIdList.getSelections() == null) {
//						prjList = RCMClientUtilities.getDelimitedString(
//								_projectsList.getFirstListValues(), "\t");
//					}
//					*/
//					_httpComm.addFormParameter("Packages", pkgList);
//					filter.put("Packages", pkgList);
//
//					_httpComm.addFormParameter("Projects", prjList);
//					filter.put("Projects", prjList);
//
//					_httpComm.addFormParameter("Tables", tabList);
//					filter.put("Tables", tabList);
//
//					_httpComm.addFormParameter("Users", usrList);
//					filter.put("Users", usrList);
//
//					_httpComm.addFormParameter("Environment", _schema);
//					filter.put("Environment", _schema);
//
//					_httpComm.addFormParameter("SortBy", getOrderByClause());
//					filter.put("SortBy", getOrderByClause());
//
//					StringBuffer tableData = new StringBuffer();
//
//					_httpComm.addFormParameter(Actions.ACTION,
//							Actions.AuditInfoAction);
//					int rc = _httpComm.postForm(tableData, false);
//
//					if (rc == 200) {
//						if (tableData.length() != 0) {
//							if (logFlag.equals(AuditErrorFilter.AUDIT_LOG)) {
//								// ENH874 - new parameters for AuditLog to support paging
//								AuditLog al = new AuditLog(_parent,
//										"Audit Log", true, true, true, true,
//										tableData, schemaName, _schema, filter,
//										_httpComm);
//
//								al.setFilter(filter);
//								_parent.getDesktopPane()
//										.add(al, Integer.valueOf(2));
//							} else {
//								ErrorLog el = new ErrorLog(_parent,
//										"Error Log", true, true, true, true,
//										tableData, schemaName);
//
//								el.setFilter(filter);
//								_parent.getDesktopPane().add(el, Integer.valueOf(2));
//							}
//						} else { // WMA TT SPR# 570 - If no tableData
//							// returned, display error.
//							String errMessage = "No matches found, Please try again...";
//							CscModalDialog.showError(errMessage, "Information");
//						}
//					} else if ((rc == 500)
//							&& _httpComm.getMessage().equals(
//									HttpComm.SESSION_EXPIRED_ERROR_HEADER)) {
//						CscModalDialog.sessionExpired();
//					} else if ((rc == 500) // WMA TT SPR# 569 - Display
//							// error-specific message from
//							// server
//							&& (_httpComm.getMessage() != null && _httpComm
//									.getMessage().trim().length() > 0)) {
//
//						String errMessage = "Runtime Exception: "
//								+ _httpComm.getMessage();
//
//						CscModalDialog.showError(errMessage, "Information");
//					} else { // WMA TT SPR# 570 - If no tableData returned,
//						// display error.
//						String errMessage = "No matches found, Please try again...";
//						if (rc == 400)
//							errMessage = _httpComm.getMessage();// "Exceeded
//						// view Limit";
//
//						CscModalDialog.showError(errMessage, "Information");
//					}
//				}
//			} catch (Exception exception) {
//				count = 0;
//				displayError(exception);
//			}
//		} else if (((event.getActionCommand()).trim())
//				.equalsIgnoreCase("Reset")) {
//			resetFormData(true); // WMA TT SPR# 571 - Reset Audit/Error
//			// Filters
//		}
//	}

//	private void populateFilters() {
//
//		resetFormData(false); // WMA TT SPR# 571 - Reset Audit/Error Filters
//		StringBuffer results = null;
//		int rc = -1;
//
//		try {
//			results = new StringBuffer();
//			_httpComm.addFormParameter("Process", Constants.GET_FILTER_INFO);
//			_httpComm.addFormParameter("Environment", _schema);
//
//			if (logFlag.equals(AuditErrorFilter.AUDIT_LOG)) {
//				_httpComm.addFormParameter("Source", Constants.AUDIT_VIEW);
//			} else {
//				_httpComm.addFormParameter("Source", AuditErrorFilter.ERROR);
//			}
//			_parent.getWaitCursor().on();
//			_httpComm.addFormParameter(Actions.ACTION, Actions.PackageAction);
//			rc = _httpComm.postForm(results, false);
//			_parent.getWaitCursor().off();
//		} catch (Exception ex) {
//			_log.error(ex.getMessage(), ex);
//		}
//		if ((rc == 200) && (results.length() != 0)) {
//			StringTokenizer st = new StringTokenizer(results.toString(), "\t");
//			Vector<String> prjVector = new Vector<String>();
//			Vector<String> pkgVector = new Vector<String>();
//			Vector<String> tabVector = new Vector<String>();
//			Vector<String> usrVector = new Vector<String>();
//
//			while (st.hasMoreTokens()) {
//				String token = st.nextToken();
//				int index = -1;
//
//				if ((index = token.indexOf(RCMClientUtilities.PKG_DELIM)) != -1) {
//					pkgVector.addElement((token.substring(index
//							+ RCMClientUtilities.DELIM_LENGTH)).trim());
//				}
//
//				if ((index = token.indexOf(RCMClientUtilities.PRJ_DELIM)) != -1) {
//					prjVector.addElement((token.substring(index
//							+ RCMClientUtilities.DELIM_LENGTH)).trim());
//				}
//
//				if ((index = token.indexOf(RCMClientUtilities.TAB_DELIM)) != -1) {
//					tabVector.addElement((token.substring(index
//							+ RCMClientUtilities.DELIM_LENGTH)).trim());
//				}
//
//				if ((index = token.indexOf(RCMClientUtilities.USR_DELIM)) != -1) {
//					usrVector.addElement((token.substring(index
//							+ RCMClientUtilities.DELIM_LENGTH)).trim());
//				}
//
//			}
//			_goButton.setEnabled(true);
//			_resetButton.setEnabled(true);
//			_packageList.addListElements(pkgVector);
//			_projectsList.addListElements(prjVector);
//			_busRulesList.addListElements(tabVector);
//			_userIdList.addListElements(usrVector);
//		} else if ((rc == 200) && (results.length() == 0)) {
//			CscModalDialog.showInfo("No data found for the environment.", "Results");
//			_goButton.setEnabled(false);
//			_resetButton.setEnabled(false);
//		}
//		// rpy
//		else {
//			CscModalDialog.handleErrorResponse(rc, _httpComm, "Error in Getting Values For Filter. "
//					+ _httpComm.getMessage(), "Action Error");
//		}
//	}

	/**
	 * Method checkPriority
	 * 
	 * @param val
	 * @throws Exception
	 */

//	public void checkPriority(String val) throws Exception {
//
//		int i = 0;
//		Integer pr = null;
//
//		if (val.equals("")) {
//			pr = Integer.valueOf(0);
//			priority.addElement(pr);
//		} else {
//			i = Integer.parseInt(val);
//			pr = Integer.valueOf(i);
//			if (i < 1 || i > 4) {
//				throw new Exception("Sort Priority number should be from 1 through 4.");
//			} else {
//
//				if (priority.contains(pr)) {
//					throw new Exception("Duplicate Sort Priority number.");
//				} else {
//					priority.addElement(pr);
//					count++;
//				}
//			}
//		}
//	}

	/**
	 * Method checkOrder
	 * 
	 * @param count
	 * @param integer
	 * @throws Exception
	 */

//	public void checkOrder(int count, int integer) throws Exception {
//
//		int i = integer;
//
//		if ((0 < i) && (i <= count)) {
//			if (priority.contains(Integer.valueOf(i))) {
//				if (i == count) {
//					return;
//				} else {
//					checkOrder(count, i + 1);
//				}
//			} else {
//				throw new Exception("Sort Priority Numbers must be sequential starting with 1.");
//				// throw new Exception("Priority "+i+" is not used");
//			}
//		}
//	}

//	private void displayError(Exception exception) {
//		CscModalDialog.showError(exception.getMessage(), "Error");
//	}

	/**
	 * Method resetFormData
	 */

//	public void resetFormData(boolean env) { // WMA TT SPR# 571 - Reset
//		// Audit/Error Filters
//
//		if (env) {
//			_environments.setSelectedIndex(0);
//		}
//
//		_packageList.initLists();
//		_projectsList.initLists();
//		_busRulesList.initLists();
//		_userIdList.initLists();
//
//		_startDate.setText("");
//		_endDate.setText("");
//
//		_packagePry.setText("");
//		_projectPry.setText("");
//		_rulesPry.setText("");
//		_userIdPry.setText("");
//
//		// manosh spr 3451
//		_packageOrder.setSelected(false);
//		_projectOrder.setSelected(false);
//		_rulesOrder.setSelected(false);
//		_userIdOrder.setSelected(false);
//
//		_packageOrder.setEnabled(false);
//		_projectOrder.setEnabled(false);
//		_rulesOrder.setEnabled(false);
//		_userIdOrder.setEnabled(false);
//		// manosh end spr 3451
//
//		// start rpy 03/15/2002 1:38:03 PM spr 166
//		_goButton.setEnabled(false);
//		// _environments.setSelectedIndex( 0 );
//		// end rpy 03/15/2002 1:38:03 PM spr 166
//
//	}

//	private boolean verifySortOrderAndPriority() {
//
//		boolean status = false;
//
//		try {
//			priority = new Vector<Integer>(5);
//			count = 0;
//			checkPriority(_packagePry.getText());
//			checkPriority(_projectPry.getText());
//			checkPriority(_rulesPry.getText());
//			checkPriority(_userIdPry.getText());
//			checkOrder(count, 1);
//			status = true;
//		} catch (Exception exception) {
//			count = 0;
//			displayError(exception);
//			status = false;
//		}
//		return status;
//	}

	// WMABASEIXI-2722 - use CscDate to validate date
//	private Date parseDate(String value) throws Exception {
//
//		try {
//			CscDate date = new CscDate(value);
//			if ( date.isAllNines() || date.isBlank() )
//				throw new DataException();
//			String date1 = CscDate.format(date);
//			return _format1.parse(date1);
//		} catch (DataException e) {
//			throw new Exception("\"" + value + "\" is not a valid date");
//		}
//	}

//	private boolean verifyDates() {
//
//		boolean validity = false;
//
//		try {
//			String startDt = (_startDate.getText()).trim();
//			String endDt = (_endDate.getText()).trim();
//
//			Date firstDate = null;
//			Date lastDate = null;
//
//			if ((startDt.length() != 0)) {
//				firstDate = parseDate(startDt);
//				validity = true;
//			}
//			if ((endDt.length() != 0)) {
//				lastDate = parseDate(endDt);
//				validity = true;
//			}
//			if ((endDt.length() != 0) && (startDt.length() != 0)) {
//				if ((firstDate != null) && (firstDate.after(lastDate))) {
//					CscModalDialog.showError("Start Date must not be after End Date", "Date Error");
//					validity = false;
//				}
//			} else if ((endDt.length() == 0) && (startDt.length() == 0)) {
//				validity = true;
//			}
//		} catch (Exception ex) {
//			validity = false;
//			CscModalDialog.showError(ex.getMessage(), "Date Error");
//		}
//		return validity;
//	}

	// ENH874 - rewrite to support audit paging
//	private String getOrderByClause() {
//
//		String orderBy = "";
//		String packagePriority = _packagePry.getText();
//		String userPriority = _userIdPry.getText();
//		String rulePriority = _rulesPry.getText();
//		String projectPriority = _projectPry.getText();
//		Hashtable<String, String> orderByKeys = new Hashtable<String, String>();
//
//		if (packagePriority.length() != 0) {
//			String pkgSortOrder = "Package_Id";
//
//			pkgSortOrder += (_packageOrder.isSelected()) ? " Desc" : " Asc";
//			orderByKeys.put(packagePriority, pkgSortOrder);
//		}
//		if (userPriority.length() != 0) {
//			String usrSortOrder = "Change_User_Id";
//
//			usrSortOrder += (_userIdOrder.isSelected()) ? " Desc" : " Asc";
//			orderByKeys.put(userPriority, usrSortOrder);
//		}
//		if (rulePriority.length() != 0) {
//			String ruleSortOrder = "DDLName";
//
//			ruleSortOrder += (_rulesOrder.isSelected()) ? " Desc" : " Asc";
//			orderByKeys.put(rulePriority, ruleSortOrder);
//		}
//		if (projectPriority.length() != 0) {
//			String prjSortOrder = "Project_Name";
//
//			prjSortOrder += (_projectOrder.isSelected()) ? " Desc" : " Asc";
//			orderByKeys.put(projectPriority, prjSortOrder);
//		}
//		if (!orderByKeys.isEmpty()) {
//			for (int i = 1; i < orderByKeys.size() + 1; i++) {
//				orderBy = orderBy + orderByKeys.get("" + i) + ",";
//			}
//		}
//		orderBy += "TIME_STAMP DESC";
//		
//		return orderBy;
//	}

//	public void initializeEnvironments() {
//
//		Vector<EnvironmentElement> env = _parent.getAuthorizedEnvironments();
//
//		if (_auditRadio.isSelected())
//			AuditEnvList.getAuditEnvironments(env, _httpComm, _parent);
//
//		_environments.removeAllItems();
//		EnvironmentElement prompt = new EnvironmentElement(0, "", "");
//		_environments.addItem(prompt);
//		for (int i = 0; i < env.size(); i++)
//			_environments.addItem(env.get(i));
//
//		_environments.setEditable(false);
//		_environments.setSelectedIndex(0);
//	}

}

