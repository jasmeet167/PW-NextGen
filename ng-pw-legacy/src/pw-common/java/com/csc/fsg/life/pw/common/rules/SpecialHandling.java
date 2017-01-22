/*
 * THIS PROGRAM IS THE PROPERTY OF CSC FINANCIAL SERVICES GROUP. IT MAY NOT BE
 * COPIED IN WHOLE OR IN PART WITHOUT THE EXPRESS WRITTEN CONSENT OF CSC
 * FINANCIAL SERVICES GROUP.
 */

package com.csc.fsg.life.pw.common.rules;

import java.util.*;

/* Modifications: V-D937, T0091, CCCV-E501, WMABASEIXI-2189, ENH992, ENH864, ENH1043, ENH922, WMA-797, ENH942 */
/*                ENH808, WMA-1083, ENH795, WMA-1190, ENH1052, ENH1130, ENH1167, ENH1112, ENH1046 */

public final class SpecialHandling {

	// Class variables

	private static SpecialHandling _instance;

	private static volatile Hashtable<String, String> _htProductPrefix = new Hashtable<String, String>(24);

	private static volatile Hashtable<String, String> _ngfFields = new Hashtable<String, String>(19);

	private static volatile ArrayList<NaturalLogCalculatedField> _nLog = new ArrayList<NaturalLogCalculatedField>(10);

	private static volatile ArrayList<String> _planRelatedCombined = new ArrayList<String>(24);

	private static volatile ArrayList<String> _subsetTables = new ArrayList<String>(108);

	private static volatile ArrayList _dssTables = new ArrayList(15);

	private static volatile ArrayList<String> _nbuTables = new ArrayList<String>(4);

	private static volatile ArrayList<String> _favTables = new ArrayList<String>(3);

	private static volatile ArrayList<String> _24x7Tables = new ArrayList<String>(2);

	private static volatile ArrayList<String> _hccTables = new ArrayList<String>(7);

//	private static volatile ArrayList _combinedTables = new ArrayList(24);

	private static volatile ArrayList<String> _commonTables = new ArrayList<String>(46);

	private static volatile ArrayList _prohibitDeleteTables = new ArrayList(12);
	
	private static volatile List<String> _prohibitEditTables = new ArrayList<String>();

	private static volatile Hashtable<String, String> _fieldNameExceptions = new Hashtable<String, String>();

	private static volatile ArrayList<String> _reservesTables = new ArrayList<String>(7);
	
	private static volatile ArrayList<String> _guideLineTables = new ArrayList<String>(7);

	/**
	 * Method getInstance
	 * 
	 * @return
	 */

	public synchronized static SpecialHandling getInstance() {

		if (_instance == null) {
			_instance = new SpecialHandling();
		}

		return _instance;
	}

	public boolean hasCrossTableEdits(String ddlName) {

		if (ddlName.equals("T006E")) {
			return true;
		}

		return false;
	}

	/**
	 * Method getProductPrefix
	 * 
	 * @param ddlName
	 * @return
	 */

	public String getProductPrefix(String ddlName) {
		return _htProductPrefix.get(ddlName.trim());
	}

	/**
	 * Method isPlanRelatedCombinedTable
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isPlanRelatedCombinedTable(String ddlName) {
		return _planRelatedCombined.contains(ddlName.trim());
	}
	
	// ENH992
	public List<String> getPlanRelatedCombinedTables() {
		return _planRelatedCombined;
	}

	/**
	 * Method isNBU
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isNBU(String ddlName) {
		return _nbuTables.contains(ddlName.trim());
	}

	/**
	 * Method is24x7
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean is24x7(String ddlName) {
		return _24x7Tables.contains(ddlName.trim());
	}

	/**
	 * Method isHCC
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isHCC(String ddlName) {
		return _hccTables.contains(ddlName.trim());
	}

	/**
	 * Method isFAV
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isFAV(String ddlName) {
		return _favTables.contains(ddlName.trim());
	}

	/**
	 * Method isCommonTable
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isCommonTable(String ddlName) {
		if (ddlName==null)
			return false; //WMABASEIXI-3758 
		return _commonTables.contains(ddlName.trim());
	}

	/**
	 * Method isDssTable
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isDssTable(String ddlName) {
		return _dssTables.contains(ddlName.trim());
	}

	
	public boolean isAllocationModelTable(String ddlName) {
		if (ddlName.equals("TW64X") || ddlName.equals("TW66X"))
			return true;
		
		return false;
	}

	/**
	 * Method isProhibitDeleteTable
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isProhibitDeleteTable(String ddlName) {
		return _prohibitDeleteTables.contains(ddlName.trim());
	}

	public boolean isProhibitEditTable(String ddlName) {
		return _prohibitEditTables.contains(ddlName.trim());
	}

	/**
	 * Method isSubsetTable
	 * 
	 * @param ddlName
	 * @return
	 */

	public boolean isPlanRelatedSubsetTable(String ddlName) {
		return _subsetTables.contains(ddlName.trim());
	}

	// ENH992
	public List<String> getSubsetTables() {
		return _subsetTables;
	}
	
	/**
	 * Method getCommonTables
	 * 
	 * @return
	 */

	public ArrayList<String> getCommonTables() {
		return _commonTables;
	}

	/**
	 * Method getNaturalLogCalculation
	 * 
	 * @deprecated since 09-2006 (wmA)as fields have been eliminated on the
	 *             database
	 * @param ddlName
	 * @param column
	 * @return
	 */

	public NaturalLogCalculatedField getNaturalLogCalculation(String ddlName,
	        String column) {

		for (int i = 0; i < _nLog.size(); i++) {
			NaturalLogCalculatedField nlcf = _nLog.get(i);
			if (nlcf.getTableName().equals(ddlName)
	         && nlcf.getColumnName().equals(column)) {
				return nlcf;
			}
		}

		return null;
	}

	/**
	 * Method isGroupingAllowed
	 * 
	 * @param ddlName
	 * @param column
	 * @return
	 */

	public boolean isGroupingAllowed(String ddlName, String column) {
		String colValue = _ngfFields.get(ddlName);
		String nextColumn="";
		if (colValue == null)
			return true;
		else {
		StringTokenizer st = new StringTokenizer(colValue, ",");
		int tokens=st.countTokens();
		if (tokens>0) {
			while(st.hasMoreTokens()){
				//for(int i=0;i<tokens;i++){			
				nextColumn = st.nextToken();
				if (nextColumn.equals(column))
					return false;
			}
		}
		else if (colValue.equals(column))
			return false;
		
		return true;
		}
	}

	/**
	 * Method isSystemMaintained
	 * 
	 * @param table
	 * @return
	 */

	public boolean isSystemMaintained(String table) {

		if (/* table.equals("T000X") || table.equals("T000XA")
		        || */ table.equals("T055X") || table.equals("T055X1")
		        || table.equals("TW70X") || table.equals("TW70X1")) {
			return true;
		}
		return false;
	}

	/**
	 * Method isTableZeroOrXA
	 * 
	 * @param table
	 * @return
	 */

	public boolean isTableZeroOrXA(String table) {

		if (table.equals("T000X") || table.equals("T000XA")) {
			return true;
		}
		return false;
	}

	/**
	 * Method isReadOnlyInETV
	 * 
	 * @param table
	 * @return
	 */

	// WMABASEIXI-2189 - specify read only tables for ETV
	public boolean isReadOnlyInETV(String table) {
		//return isTableZeroOrXA(table);
		return false;
	}

	/**
	 * Method cascadeChanges
	 * 
	 * @param table
	 * @param column
	 * @return
	 */

	public boolean cascadeChanges(String table, String column) {

		if (table.equals("T000X")) {
			return true;
		}

		return false;
	}

	public boolean useYYYY_MM_DD(String table, String column) {
		return true;
	}

	public String getFieldNameBase(String ddlName) {
		return _fieldNameExceptions.get(ddlName.trim());
	}

	// for mutants convert the field names
	public String convertFieldName(String field) {
		if (field == null)
			return field;

		field = field.trim().toUpperCase();

		if (field.endsWith("FAV_CODE"))
			return "FAV_CODE";

		if (field.startsWith("STAT_CO") || field.equals("STATUTORY_CODE"))
			return "STATUTORY_COMPANY";

		if (field.startsWith("PROD_CODE_"))
			return "PRODUCT_CODE";

		if (field.equals("RESIDENT_STATE_CDE") || field.equals("ISSUE_STATE"))
			return "STATE_CODE";

		// T021X fields ..same range for all 50 occurs
		if (field.startsWith("MAX_OFFER_PCT"))
			return "MAX_OFFER_PCT";

		if (field.startsWith("MINIMUM_AMT"))
			return "MINIMUM_AMT";

		if (field.startsWith("OFFER_AGE_DUR"))
			return "OFFER_AGE_DUR";

		if (field.startsWith("OFFERING_AMT"))
			return "OFFERING_AMT";

		// WMA TT SPR# 192 - Three Fields in T0C8X with prefix INVST_DD
		if (field.startsWith("INVST_DD"))
			return "INVST_DATE";

		return field;
	}

	/**
	 * Method initNaturalLogCalcFields
	 * 
	 * @deprecated since 09-2006 (wmA) as fields have been eliminated on the
	 *             database
	 */
	private void initNaturalLogCalcFields() {

		_nLog.add(new NaturalLogCalculatedField("T004E", "RISK_RATE_DIVISOR",
		        "NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T025X", "INTEREST_RATE",
		        "NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T026X", "GUAR_INT_RT",
		        "NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T027E", "BASE_MNY_INT_RT",
		        "NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T225B", "INTEREST_RATE",
		        "NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("TAE4F", "ASEQ_INTEREST_RATE",
		        "NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T060X", "GUAR_INTEREST_RATE",
		        "GUAR_NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T060X", "STAT_INTEREST_RATE",
		        "STAT_NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T060X", "GAAP_INTEREST_RATE",
		        "GAAP_NATURAL_LOG"));
		_nLog.add(new NaturalLogCalculatedField("T060X", "TAX_INTEREST_RATE",
		        "TAX_NATURAL_LOG"));
	}

	private void initNonGroupingFields() {
		////Added Year field to Non Grouping Fields HashTable:SPR #WMA-PH1-1516
		_ngfFields.put("T116X", "END_OF_DAY");
		_ngfFields.put("T006E", "AVERAGING_YEARS");
		_ngfFields.put("T060X", "ISSUE_YEAR,FUND_NUMBER");
		_ngfFields.put("T105X", "YEAR,FUND_NUMBER");
		_ngfFields.put("T108X", "TAX_YEAR");
		_ngfFields.put("TA59F", "ISSUE_YEAR");
		_ngfFields.put("TAE1F", "ISSUE_YEAR");
		_ngfFields.put("TW56X", "BIRTH_YEAR");
		_ngfFields.put("T026X", "MX_CAL_YY");
		_ngfFields.put("T034E", "MAX_DURATION");
		_ngfFields.put("T042E", "MAX_DURATION");
		_ngfFields.put("T043E", "MAX_DURATION");
		_ngfFields.put("T085X", "MX_COV_DUR");
		_ngfFields.put("T0C7X", "INDEX_PERD_DUR");
		_ngfFields.put("T115X", "DURATION");
		_ngfFields.put("T120X", "CHILD_MAX_ISS_AGE,CHILD_EXP_AGE,CHILD_EXP_NTC_DAYS");		
		_ngfFields.put("T009X", "FUND_NUMBER");
		_ngfFields.put("T010X", "FUND_NUMBER");
		_ngfFields.put("T014X", "FUND_NUMBER");
		_ngfFields.put("T023X", "FUND_NUMBER");
		_ngfFields.put("T024X", "FUND_NUMBER");
		_ngfFields.put("T040X", "FUND_NUMBER");
		_ngfFields.put("T057X", "FUND_NUMBER");
		_ngfFields.put("T097E", "FUND_NUMBER");
		_ngfFields.put("T0C8X", "FUND_NUMBER");
		_ngfFields.put("T0D4X", "FUND_NUMBER");
		_ngfFields.put("T113X", "FAV_FUND_NUMBER");
		_ngfFields.put("T205B", "FUND_NUMBER");
		_ngfFields.put("T210B", "FUND_NUMBER");
		_ngfFields.put("T224B", "FUND_NUMBER");
		_ngfFields.put("TA61F", "FUND_NUMBER");
		_ngfFields.put("TA62F", "FUND_NUMBER");
		_ngfFields.put("TAB1F", "FUND_NUMBER");
		_ngfFields.put("TAB3F", "FUND_NUMBER");
		_ngfFields.put("TW24X", "FUND_NUMBER");
		_ngfFields.put("TW66X", "FUND_NUMBER");		
		_ngfFields.put("T125X", "FUND_NUMBER");
		_ngfFields.put("T046E", "PCT_INIT_PREM_FND");
		_ngfFields.put("TH12H", "STEP_UP_BASIS,WITHDRAWAL_METHOD");
		_ngfFields.put("T038X", "MAX_MONTHLY_DUR");
		
	}

	/**
	 * Method initPlanRelatedCombinedSubsetTables indicates which business rule
	 * tables are plan-related and used across products (IE. UL, TRAD, Annuity).
	 * These types of business rules typically have a PRODUCT_PREFIX on them to
	 * differentiate behavior between products. This method is a subset of the
	 * list contained in method initPlanRelatedSubsetTables(). Update this
	 * method whenever you add plan-related business rule tables which contain a
	 * PRODUCT_PREFIX column.
	 */
	private void initPlanRelatedCombinedSubsetTables() {

		_planRelatedCombined.add("T000X");
		_planRelatedCombined.add("T007X");
		_planRelatedCombined.add("T010X");
		_planRelatedCombined.add("T011X");
		_planRelatedCombined.add("T012X");
		_planRelatedCombined.add("T021X");
		_planRelatedCombined.add("T022X");
		_planRelatedCombined.add("T023X");
		_planRelatedCombined.add("T024X");
		_planRelatedCombined.add("T025X");
		_planRelatedCombined.add("T026X");
		_planRelatedCombined.add("T036X");
		_planRelatedCombined.add("T038X");
		_planRelatedCombined.add("T039X");
		_planRelatedCombined.add("T040X");
		_planRelatedCombined.add("T058X");
		_planRelatedCombined.add("T060X");
		_planRelatedCombined.add("T066X");
		_planRelatedCombined.add("T071X");
		_planRelatedCombined.add("T085X");
		_planRelatedCombined.add("T088X");
		_planRelatedCombined.add("T091X");
		_planRelatedCombined.add("T119X");
		_planRelatedCombined.add("T120X");
		_planRelatedCombined.add("T124X");
		_planRelatedCombined.add("T127X");
		_planRelatedCombined.add("T128X");
		_planRelatedCombined.add("TXW1X");
		_planRelatedCombined.add("T0C5X");
		
		_planRelatedCombined.add("TW04X");
		_planRelatedCombined.add("TW05X");
		_planRelatedCombined.add("TW15X");
		_planRelatedCombined.add("TW24X");
		_planRelatedCombined.add("TW30X");
		_planRelatedCombined.add("TW54X");
		_planRelatedCombined.add("TW56X");
		_planRelatedCombined.add("TW57X");
		_planRelatedCombined.add("TW58X");
		_planRelatedCombined.add("TW64X");
		_planRelatedCombined.add("TW66X");
		_planRelatedCombined.add("TW71X");
		_planRelatedCombined.add("T0C3X");
		_planRelatedCombined.add("T0C6X");
		_planRelatedCombined.add("T0C7X");
		_planRelatedCombined.add("T0C8X");
		_planRelatedCombined.add("T0D4X");
		_planRelatedCombined.add("T0D5X");
		
		
	}

	private void initNBUTables() {

		_nbuTables.add("TE04Z");
		_nbuTables.add("TEC4Z");
		_nbuTables.add("TEC5Z");
		_nbuTables.add("TED7Z");
	}

	private void initFAVTables() {

		_favTables.add("T113X");
		_favTables.add("T114X");
		_favTables.add("T115X");
	}

	private void init24x7Tables() {
		_24x7Tables.add("T116X");
	}

	private void initHCCTables() {

		_hccTables.add("TH01H");
		_hccTables.add("TH11H");
		_hccTables.add("TH12H");
		_hccTables.add("TH13H");
		_hccTables.add("TH21H");
		_hccTables.add("TH22H");
		_hccTables.add("TH23H");
	}

	/**
	 * Method initPlanRelatedSubsetTables indicates which business rule tables
	 * are plan-related. This method is a superset of the list contained in
	 * method initPlanRelatedCombinedSubsetTables(). Update this method whenever
	 * you add plan-related business rule tables.
	 */
	private void initPlanRelatedSubsetTables() {

		_subsetTables.add("T004E");
		_subsetTables.add("T005E");
		_subsetTables.add("T006E");
		_subsetTables.add("T007X");
		_subsetTables.add("T010X");
		_subsetTables.add("T011X");
		_subsetTables.add("T012X");
		_subsetTables.add("T013E");
		_subsetTables.add("T019E");
		_subsetTables.add("T020E");
		_subsetTables.add("T021X");
		_subsetTables.add("T022X");
		_subsetTables.add("T023X");
		_subsetTables.add("T024X");
		_subsetTables.add("T025X");
		_subsetTables.add("T026X");
		_subsetTables.add("T027E");
		_subsetTables.add("T028E");
		_subsetTables.add("T029E");
		_subsetTables.add("T030E");
		_subsetTables.add("T031E");
		_subsetTables.add("T032E");
		_subsetTables.add("T033E");
		_subsetTables.add("T034E");
		_subsetTables.add("T035E");
		_subsetTables.add("T036X");
		_subsetTables.add("T037E");
		_subsetTables.add("T038X");
		_subsetTables.add("T039X");
		_subsetTables.add("T040X");
		_subsetTables.add("T041E");
		_subsetTables.add("T042E");
		_subsetTables.add("T043E");
		_subsetTables.add("T046E");
		_subsetTables.add("T048E");
		_subsetTables.add("T052E");
		_subsetTables.add("T053E");
		_subsetTables.add("T054E");
		_subsetTables.add("T056E");
		_subsetTables.add("T058X");
		_subsetTables.add("T060X");
		_subsetTables.add("T061E");
		_subsetTables.add("T061E1");
		_subsetTables.add("T062E");
		_subsetTables.add("T063E");
		_subsetTables.add("T064E");
		_subsetTables.add("T065E");
		_subsetTables.add("T066X");
		// _subsetTables.add("T069E");
		_subsetTables.add("T071X");
		_subsetTables.add("T083E");
		_subsetTables.add("T085X");
		_subsetTables.add("T086E");
		_subsetTables.add("T087E");
		_subsetTables.add("T088X");
		_subsetTables.add("T089E");
		_subsetTables.add("T091X");
		_subsetTables.add("T097E");
		_subsetTables.add("T0C3X");
		_subsetTables.add("T0C5X");
		_subsetTables.add("T0C6X");
		_subsetTables.add("T0C7X");
		_subsetTables.add("T0C8X");
		_subsetTables.add("T0D4X");
		_subsetTables.add("T0D5X");
		_subsetTables.add("T119X");
		_subsetTables.add("T120X");
		_subsetTables.add("T124X");
		_subsetTables.add("T127X");
		_subsetTables.add("T128X");
		_subsetTables.add("T204B");
		_subsetTables.add("T205B");
		_subsetTables.add("T210B");
		_subsetTables.add("T211B");
		_subsetTables.add("T222B");
		_subsetTables.add("T224B");
		_subsetTables.add("T225B");
		_subsetTables.add("TA04F");
		_subsetTables.add("TA19F");
		_subsetTables.add("TA22F");
		_subsetTables.add("TA33F");
		_subsetTables.add("TA59F");
		_subsetTables.add("TA61F");
		_subsetTables.add("TA62F");
		_subsetTables.add("TAB1F");
		_subsetTables.add("TAB2F");
		_subsetTables.add("TAB3F");
		_subsetTables.add("TAB6F");
		_subsetTables.add("TAB7F");
		_subsetTables.add("TAB8F");
		_subsetTables.add("TAC1F");
		_subsetTables.add("TAC2F");
		_subsetTables.add("TAD1F");
		_subsetTables.add("TAD2F");
		_subsetTables.add("TAD3F");
		_subsetTables.add("TAE1F");
		_subsetTables.add("TAE2F");
		_subsetTables.add("TAE3F");
		_subsetTables.add("TAE4F");
		_subsetTables.add("TE04Z");
		_subsetTables.add("TEC4Z");
		_subsetTables.add("TEC5Z");
		_subsetTables.add("TED7Z");
		_subsetTables.add("TH01H");
		_subsetTables.add("TH11H");
		_subsetTables.add("TH12H");
		_subsetTables.add("TH13H");
		_subsetTables.add("TH21H");
		_subsetTables.add("TH22H");
		_subsetTables.add("TH23H");
		_subsetTables.add("TT04T");
		_subsetTables.add("TT06T");
		_subsetTables.add("TT15T");
		_subsetTables.add("TT19T");
		_subsetTables.add("TT24T");
		_subsetTables.add("TT33T");
		_subsetTables.add("TT67T");
		_subsetTables.add("TT72T");
		_subsetTables.add("TT73T");
		_subsetTables.add("TT74T");
		_subsetTables.add("TT76T");
		_subsetTables.add("TT78T");
		_subsetTables.add("TT79T");
		_subsetTables.add("TT81T");
		_subsetTables.add("TT82T");
		_subsetTables.add("TT84T");
		_subsetTables.add("TTB1T");
		_subsetTables.add("TTB3T");
		_subsetTables.add("TTB4T");
		_subsetTables.add("TTB5T");
		_subsetTables.add("TTB6T");
		_subsetTables.add("TTB7T");
		_subsetTables.add("TTBAT");
		_subsetTables.add("TTBBT");
		_subsetTables.add("TTBCT");
		_subsetTables.add("TTBDT");
		_subsetTables.add("TTC1T");
		_subsetTables.add("TTC7T");
		_subsetTables.add("TTC8T");
		_subsetTables.add("TTD1T");
		_subsetTables.add("TW04X");
		_subsetTables.add("TW05X");
		_subsetTables.add("TW15X");
		_subsetTables.add("TW24X");
		_subsetTables.add("TW30X");
		_subsetTables.add("TW54X");
		_subsetTables.add("TW56X");
		_subsetTables.add("TW57X");
		_subsetTables.add("TW58X");
		_subsetTables.add("TW64X");
		_subsetTables.add("TW65X");
		_subsetTables.add("TW66X");
		_subsetTables.add("TW71X");
		_subsetTables.add("TXW1X");
	}

	/**
	 * Method initCommonTables specifies those tables which are not
	 * plan-related. Update this method whenever you add new business rules
	 * which are not to be involved in plan-related processing.
	 */
	private void initCommonTables() {

		_commonTables.add("T001X");
		_commonTables.add("T001X1");
		_commonTables.add("T002X");
		_commonTables.add("T008X");
		_commonTables.add("T009X");
		_commonTables.add("T014X");
		_commonTables.add("T015X");
		_commonTables.add("T016X");
		_commonTables.add("T018X");
		_commonTables.add("T045X");
		_commonTables.add("T047X");
		_commonTables.add("T049X");
		_commonTables.add("T049X1");
		_commonTables.add("T051X");
		_commonTables.add("T051X1");
		_commonTables.add("T055X");
		_commonTables.add("T055X1");
		_commonTables.add("T057X");
		_commonTables.add("T080X");
		_commonTables.add("T090X");
		_commonTables.add("T105X");
		_commonTables.add("T107X");
		_commonTables.add("T108X");
		_commonTables.add("T109X");
		_commonTables.add("T110X");
		_commonTables.add("T112X");
		_commonTables.add("T113X");
		_commonTables.add("T114X");
		_commonTables.add("T115X");
		_commonTables.add("T116X");
		_commonTables.add("T125X");
		_commonTables.add("T126X");

		_commonTables.add("TW06X");
		_commonTables.add("TW08X");
		_commonTables.add("TW09X");
		_commonTables.add("TW10X");
		
		
		_commonTables.add("TW67X");
		_commonTables.add("TW68X");
		_commonTables.add("TW68X1");
		_commonTables.add("TW69X");
		_commonTables.add("TW69X1");
		_commonTables.add("TW70X");
		_commonTables.add("TW70X1");

	}

	/**
	 * Method initProductPrefix specifies the PRODUCT_PREFIX for plan related
	 * business rules which belong to a specific product (IE. TT04T is a TRAD
	 * table) Update this method whenever you add new plan-related
	 * product-specific business rules. 
	 * 
	 * PLEASE NOTE: When defining entries in businessRuleColumnInformation.xml 
	 * for Tables that only support a single product, the entries should be 
	 * defined with np as the product_prefix. This allows processing in FieldMetaData's
	 * getDataNameRecord() method to successfully get hit on meta data.
	 */
	private void initProductPrefix() {

		_htProductPrefix.put("TT04T", "T");
		_htProductPrefix.put("T004E", "U");
		_htProductPrefix.put("TA04F", "A");
		_htProductPrefix.put("T005E", "U");
		_htProductPrefix.put("T006E", "U");
		_htProductPrefix.put("TT06T", "T");
		_htProductPrefix.put("T013E", "U");
		_htProductPrefix.put("TT15T", "T");
		_htProductPrefix.put("T019E", "U");
		_htProductPrefix.put("TA19F", "A");
		_htProductPrefix.put("TT19T", "T");
		_htProductPrefix.put("T020E", "U");
		_htProductPrefix.put("TA22F", "A");
		_htProductPrefix.put("TT24T", "T");
		_htProductPrefix.put("T027E", "U");
		_htProductPrefix.put("T028E", "U");
		_htProductPrefix.put("T029E", "U");
		_htProductPrefix.put("T030E", "U");
		_htProductPrefix.put("T031E", "U");
		_htProductPrefix.put("T032E", "U");
		_htProductPrefix.put("T033E", "U");
		_htProductPrefix.put("TA33F", "A");
		_htProductPrefix.put("TT33T", "T");
		_htProductPrefix.put("T034E", "U");
		_htProductPrefix.put("T035E", "U");
		_htProductPrefix.put("T037E", "U");
		_htProductPrefix.put("T041E", "U");
		_htProductPrefix.put("T042E", "U");
		_htProductPrefix.put("T043E", "U");
		_htProductPrefix.put("T046E", "U");
		_htProductPrefix.put("T048E", "U");
		_htProductPrefix.put("T052E", "U");
		_htProductPrefix.put("T053E", "U");
		_htProductPrefix.put("T054E", "U");
		_htProductPrefix.put("T056E", "U");
		_htProductPrefix.put("TA59F", "A");
		_htProductPrefix.put("TA61F", "A");
		_htProductPrefix.put("TA62F", "A");
		_htProductPrefix.put("T061E", "U");
		_htProductPrefix.put("T061E1", "U");
		_htProductPrefix.put("T062E", "U");
		_htProductPrefix.put("T063E", "U");
		_htProductPrefix.put("T064E", "U");
		_htProductPrefix.put("T065E", "U");
		_htProductPrefix.put("TT67T", "T");
		// _htProductPrefix.put("T069E", "U"); //spr 1004
		_htProductPrefix.put("TT72T", "T");
		_htProductPrefix.put("TT73T", "T");
		_htProductPrefix.put("TT74T", "T");
		_htProductPrefix.put("TT76T", "T");
		_htProductPrefix.put("TT78T", "T");
		_htProductPrefix.put("TT79T", "T");
		_htProductPrefix.put("TT81T", "T");
		_htProductPrefix.put("TT82T", "T");
		_htProductPrefix.put("T083T", "T");
		_htProductPrefix.put("T083E", "U");
		_htProductPrefix.put("TT84T", "T");
		_htProductPrefix.put("T086E", "U");
		_htProductPrefix.put("T087E", "U");
		_htProductPrefix.put("T089E", "U");
		_htProductPrefix.put("T097E", "U");
		_htProductPrefix.put("TA04F", "A");
		_htProductPrefix.put("TAB1F", "A");
		_htProductPrefix.put("TAB2F", "A");
		_htProductPrefix.put("TAB3F", "A");
		_htProductPrefix.put("TAB6F", "A");
		_htProductPrefix.put("TAB7F", "A");
		_htProductPrefix.put("TAB8F", "A");
		_htProductPrefix.put("TAC1F", "A");
		_htProductPrefix.put("TAC2F", "A");
		_htProductPrefix.put("TAD1F", "A");
		_htProductPrefix.put("TAD2F", "A");
		_htProductPrefix.put("TAD3F", "A");
		_htProductPrefix.put("TAE1F", "A");
		_htProductPrefix.put("TAE2F", "A");
		_htProductPrefix.put("TAE3F", "A");
		_htProductPrefix.put("TAE4F", "A");
		_htProductPrefix.put("TTB1T", "T");
		_htProductPrefix.put("TTB3T", "T");
		_htProductPrefix.put("TTB4T", "T");
		_htProductPrefix.put("TTB5T", "T");
		_htProductPrefix.put("TTB6T", "T");
		_htProductPrefix.put("TTB7T", "T");
		_htProductPrefix.put("TTBAT", "T");
		_htProductPrefix.put("TTBBT", "T");
		_htProductPrefix.put("TTBCT", "T");
		_htProductPrefix.put("TTBDT", "T");
		_htProductPrefix.put("TTC1T", "T");
		_htProductPrefix.put("TTC7T", "T");
		_htProductPrefix.put("TTC8T", "T");
		_htProductPrefix.put("TTD1T", "T");
		_htProductPrefix.put("T204B", "N");
		_htProductPrefix.put("T205B", "N");
		_htProductPrefix.put("T210B", "N");
		_htProductPrefix.put("T211B", "N");
		_htProductPrefix.put("T222B", "N");
		_htProductPrefix.put("T224B", "N");
		_htProductPrefix.put("T225B", "N");

//		_htProductPrefix.put("TW04X", "A");
//		_htProductPrefix.put("TW05X", "A");
//		_htProductPrefix.put("TW15X", "A");
//		_htProductPrefix.put("TW24X", "A");
//		_htProductPrefix.put("TW30X", "A");
//		_htProductPrefix.put("TW54X", "A");
//		_htProductPrefix.put("TW56X", "A");
//		_htProductPrefix.put("TW57X", "A");
//		_htProductPrefix.put("TW58X", "A");
//		_htProductPrefix.put("TW64X", "A");
		_htProductPrefix.put("TW65X", "A");
//		_htProductPrefix.put("TW66X", "A");
		_htProductPrefix.put("TW71X", "A");

	//	_htProductPrefix.put("T0C3X", "A");
	//	_htProductPrefix.put("T0C5X", "A"); /**WMABASEIXI-2486 */
	//	_htProductPrefix.put("T0C6X", "A");
	//	_htProductPrefix.put("T0C7X", "A");
	//	_htProductPrefix.put("T0C8X", "A");

	//	_htProductPrefix.put("T0D4X", "A");
	//	_htProductPrefix.put("T0D5X", "A");

		_htProductPrefix.put("TW06X", "C");
		_htProductPrefix.put("TW08X", "C");
		_htProductPrefix.put("TW09X", "C");
		_htProductPrefix.put("TW10X", "C");

		// HCC PW Integration Changes.
		_htProductPrefix.put("TH01H", "H");
		_htProductPrefix.put("TH11H", "H");
		_htProductPrefix.put("TH12H", "H");
		_htProductPrefix.put("TH13H", "H");
		_htProductPrefix.put("TH21H", "H");
		_htProductPrefix.put("TH22H", "H");
		_htProductPrefix.put("TH23H", "H");
	}

	private void initProhibitDeleteTables() {
		// not currently used
	}

	private void initFieldNameExceptions() {
		int i;
		String leader = "";
		for (i = 1; i <= 50; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("MAX_OFFER_PCT_" + leader + i,
			        "MAX_OFFER_PCT");
		}
		for (i = 1; i <= 9; i++) {
			_fieldNameExceptions.put("CNTRL_BRK_0" + i, "CNTRL_BRK");
		}
		for (i = 1; i <= 9; i++) {
			_fieldNameExceptions.put("PAGE_BRK_0" + i, "PAGE_BRK");
		}
		for (i = 1; i <= 20; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("PROD_CODE_" + leader + i, "PROD_CODE");
		}
		for (i = 1; i <= 2; i++) {
			_fieldNameExceptions
			        .put("REPORT_NAME_LINE" + i, "REPORT_NAME_LINE");
		}
		for (i = 1; i <= 9; i++) {
			_fieldNameExceptions.put("SORT_FLD_0" + i, "SORT_FLD");
		}
		for (i = 1; i <= 50; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions
			        .put("MINIMUM_AMT_" + leader + i, "MINIMUM_AMT");
		}
		for (i = 1; i <= 50; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("OFFER_AGE_DUR_" + leader + i,
			        "OFFER_AGE_DUR");
		}
		for (i = 1; i <= 50; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("OFFERING_AMT_" + leader + i,
			        "OFFERING_AMT");
		}
		for (i = 1; i <= 10; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("DEFAULT_VOTE_" + leader + i,
			        "DEFAULT_VOTE");
		}
		for (i = 1; i <= 9; i++) {
			_fieldNameExceptions.put("COI_IND_0" + i, "COI_IND");
		}
		for (i = 1; i <= 9; i++) {
			_fieldNameExceptions.put("FIX_FND_INT_0" + i, "FIX_FND_INT");
		}
		for (i = 1; i <= 9; i++) {
			_fieldNameExceptions.put("VAR_FND_PRJ_0" + i, "VAR_FND_PRJ");
		}
		for (i = 1; i <= 6; i++) {
			_fieldNameExceptions.put("PLAN_CODE_DIGIT_" + i, "PLAN_CODE_DIGIT");
		}
		for (i = 1; i <= 8; i++) {
			_fieldNameExceptions.put("DATABASE_VALUE_" + i, "DATABASE_VALUE");
		}
		for (i = 1; i <= 30; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("DAY_" + leader + i, "DAY");
		}
		for (i = 1; i <= 30; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("MONTH_" + leader + i, "MONTH");
		}
		for (i = 1; i <= 20; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("FUND_NUMBER_0" + leader + i,
			        "FUND_NUMBER");
		}
		for (i = 1; i <= 20; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("EXCLUSION_RDR_" + leader + i,
			        "EXCLUSION_RDR");
		}
		for (i = 1; i <= 2; i++) {
			_fieldNameExceptions
			        .put("OPT_BENEFIT_IND_0" + i, "OPT_BENEFIT_IND");
		}
		for (i = 1; i <= 2; i++) {
			_fieldNameExceptions
			        .put("OPT_BENEFIT_LBL_0" + i, "OPT_BENEFIT_LBL");
		}
		for (i = 1; i <= 2; i++) {
			_fieldNameExceptions.put("OPT_RIDER_IND_0" + i, "OPT_RIDER_IND");
		}
		for (i = 1; i <= 2; i++) {
			_fieldNameExceptions.put("OPT_RIDER_LBL_0" + i, "OPT_RIDER_LBL");
		}
		for (i = 1; i <= 40; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("REQRMTS_DATA_" + leader + i,
			        "REQRMTS_DATA");
		}
		for (i = 1; i <= 8; i++) {
			_fieldNameExceptions.put("FORMS_DESC_" + i, "FORMS_DESC");
		}
		for (i = 1; i <= 10; i++) {
			leader = (i < 10) ? "0" : "";
			_fieldNameExceptions.put("STAT_COMP_CODE_" + leader + i,
			        "STAT_COMP_CODE");
		}
		for (i = 1; i <= 10; i++) {
			_fieldNameExceptions.put("FLAT_EXT_REA_CD" + i, "FLAT_EXT_REA_CD");
		}
		for (i = 1; i <= 10; i++) {
			_fieldNameExceptions.put("SPCL_CL_CODE" + i, "SPCL_CL_CODE");
		}
		for (i = 1; i <= 3; i++) {
			_fieldNameExceptions.put("INVST_DD_" + i, "INVST_DD");
		}
	}

	private SpecialHandling() {
		initNaturalLogCalcFields();
		initPlanRelatedCombinedSubsetTables();
		initProductPrefix();
		initPlanRelatedSubsetTables();
		initNBUTables();
		initFAVTables();
		init24x7Tables();
		initHCCTables();
		initCommonTables();
		initNonGroupingFields();
		initProhibitDeleteTables();
		initFieldNameExceptions();
		initReserversTables();
		initGuideLineTables();
		initMixedCaseFields();
		initProhibitEditTables();
	}
	
	
	private void initProhibitEditTables() {
		_prohibitEditTables.add("T001X");
	}
	
	private void initReserversTables() {

		_reservesTables.add("TW67X");
		_reservesTables.add("TW68X");
		_reservesTables.add("TW68X1");
		_reservesTables.add("TW69X");
		_reservesTables.add("TW69X1");
		_reservesTables.add("TW70X");
		_reservesTables.add("TW70X1");
	}
	
	private void initGuideLineTables() {

		_guideLineTables.add("T047X");
		_guideLineTables.add("T049X");
		_guideLineTables.add("T049X1");
		_guideLineTables.add("T051X");
		_guideLineTables.add("T051X1");
		_guideLineTables.add("T055X");
		_guideLineTables.add("T055X1");
	}
	
	public boolean isGuideLineTable(String ddlName) {
		return _guideLineTables.contains(ddlName);
	}
	
	public boolean isReservesTable(String ddlName) {
		return _reservesTables.contains(ddlName);
	}

	public Vector<String> getGLVVector() {
		Vector<String> glvVector = new Vector<String>();
		glvVector.addElement("047");
		glvVector.addElement("049");
		glvVector.addElement("051");
		glvVector.addElement("49X");
		glvVector.addElement("51X");
		return glvVector;
	}
	
	public Vector<String> getPRVVector() {
		Vector<String> glvVector = new Vector<String>();
		glvVector.addElement("W67");
		glvVector.addElement("W68");
		glvVector.addElement("W69");
		glvVector.addElement("68X");
		glvVector.addElement("69X");
		return glvVector;
	}
	
	public boolean isRegenCommutationTable(String tableId) {
		return getGLVVector().contains(tableId.trim());
	}
	
	public boolean isRegenReserversTable(String tableId) {
		return getPRVVector().contains(tableId.trim());
	}

	
//	/** applicable only for MFE environment. Should come from config file-? */
//	public HashMap getFileCodes(){
//		HashMap fileCodes = new HashMap();
//		fileCodes.put("VCSTST93","A");
//		fileCodes.put("VCSTST94","B");
//		return fileCodes;
//	}
	
	/** Disable the row when condition is true  */
	public HashMap<String, String> getRowEdits(){
		HashMap<String, String> rowEdits = new HashMap<String, String>();
		rowEdits.put("TW53X","TABLE_PURPOSE=F");
		return rowEdits;
	}
	
	public String translateDataType(int dataType, int length, int scale){
	      switch (dataType) {
			case java.sql.Types.VARCHAR:
			    return "CHAR";
			case java.sql.Types.CHAR:
			    return "CHAR";
			case java.sql.Types.DATE:
			    return "DATE";
			case java.sql.Types.BIGINT:
			    return "BIGINT";
			case java.sql.Types.INTEGER:
			    return "INTEGER";
			case java.sql.Types.SMALLINT:
			    return "INTEGER";
			case java.sql.Types.DOUBLE:
			    return "DOUBLE";
			case java.sql.Types.DECIMAL:{
			    if (scale==0){
			        if (length>9)
			            return "BIGINT";
			        else
			            return "INTEGER";
			    }else{
			        return "DOUBLE";
			    }
			}
			default: return "UNKNOWN";
		}
	         
	}

	public static ArrayList<String> get_subsetTables() {
		return _subsetTables;
	}
	
	/** V-D937  Mixed Case in table cells*/
	private static volatile Hashtable<String, String> _mixedCaseFields = new Hashtable<String, String>();
	//Comma separated fields for each table.
	private void initMixedCaseFields() {
		_mixedCaseFields.put("T002X", "LONG_MESSAGE");
	}
	
	public boolean isMixedCase(String ddlName, String column) {
		if (!_mixedCaseFields.containsKey(ddlName))
			return false;
		String value = _mixedCaseFields.get(ddlName);
		String[] fields = value.split(",");
		for (String field : fields) {
			if (field.equalsIgnoreCase(column))
				return true;
		}
		return false;
	}
	/** V-D937  End*/

	// TT wmA SPR 5141
	public String getProductPrefix(String ddlName, String parentPrefix) {
		if ( isCommonTable(ddlName) )
			return "C";

		if ( isPlanRelatedCombinedTable(ddlName) )
			return parentPrefix;

		String pp = getProductPrefix(ddlName);
		if (pp != null)
			return pp;
		return "N";
	}
	
}
