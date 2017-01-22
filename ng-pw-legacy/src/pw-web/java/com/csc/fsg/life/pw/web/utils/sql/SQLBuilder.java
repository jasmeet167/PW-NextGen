package com.csc.fsg.life.pw.web.utils.sql;

import java.lang.reflect.Method;
import java.util.StringTokenizer;
import java.util.Vector;

import com.csc.fsg.life.exceptions.WrapperException;
import com.csc.fsg.life.common.util.StringBufferPool;
import com.csc.fsg.life.pw.common.transferobjects.PlanCriteriaTO;
import com.csc.fsg.life.pw.common.transferobjects.PlanTO;
import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.web.io.ColumnDescriptor;
import com.csc.fsg.life.pw.web.io.TableDescriptor;
import com.csc.fsg.life.pw.web.environment.Environment;
import com.csc.fsg.life.pw.web.environment.EnvironmentManager;

/* Modifications: T0103, T0091, WMABASEIXI-4941 */
// T0103 - new class for refactor plan key

public abstract class SQLBuilder {
	public static final int BUSINESS_RULES = 1;
	public static final int APPL = 2;
	
	private Environment environment;
	private String schemaName;
	private String tableName;
	private TableDescriptor tableDescriptor;
	
	private StringBufferPool sbPool = StringBufferPool.getInstance();
	protected StringBuffer sql = sbPool.getEntry();
	
	private boolean selectCount = false;
	private boolean selectDistinct = false;
	
	private boolean ignoreHighValues = false;
	// TT wmA SPR 6140 - conditionally ignore product suffix == "*"
	private boolean ignoreHighValueProductSuffix = true;
	
	private boolean appendConditionalOperator = false;
	private boolean appendWHERE = true;
	private boolean appendAND = true;
	private boolean appendOR = false;
	private boolean appendComma = false;
	
	private String[] columnNames = null;
	private int openParenthesis = 0;
	
	protected SQLBuilder(String envId, int schemaType, String table) {
		environment = EnvironmentManager.getInstance().getEnvironment(envId);
		if ( environment != null ) {
			if ( schemaType == APPL )
				setSchemaName(environment.getApplSchema());
			else
				setSchemaName(environment.getSchema());
			
			TableDescriptor td = environment.getTableDescMgr().getTableDescriptor(table);
			if (td != null) {
				setTableDescriptor(td);
				setTableName(td.getTableName());
			} else
				setTableName(table);
		}
	}
	
	protected SQLBuilder(String envId, String schema, String table) {
		environment = EnvironmentManager.getInstance().getEnvironment(envId);
		setSchemaName(schema);
		setTableName(table);
	}
	
	protected SQLBuilder(String envId, int schemaType, TableDescriptor td) {
		environment = EnvironmentManager.getInstance().getEnvironment(envId);
		if ( environment != null ) {
			if ( schemaType == APPL )
				setSchemaName(environment.getApplSchema());
			else
				setSchemaName(environment.getSchema());
		}
		setTableName(td.getTableName());
		setTableDescriptor(td);
	}
	
	/** @todo tmp */
	public static boolean compareSQL(String sql1, String sql2) {
		boolean breakpoint = false;
		sql1 = sql1.toUpperCase().replaceAll(" ", "").replaceAll("\n", "");
		sql2 = sql2.toUpperCase().replaceAll(" ", "").replaceAll("\n", "");
		if (!sql1.equals(sql2))
			breakpoint = true;
		return sql1.equals(sql2);
	}

	public String prepareInsertStatement() {
		return getTableDescriptor().prepareInsertStmt(getSchemaName());
	}
	
	public String prepareUpdateStatement() {
		return getTableDescriptor().prepareUpdateStmt(getSchemaName());
	}

	public String prepareDeleteStatement() {
		return getTableDescriptor().prepareDeleteStmt(getSchemaName());
	}

	public String buildSelectTableStatement() {
		addSelectAllClause();
		addFromClause();
		return getFinalStatement();
	}

	public String toString() {
		return sql.toString();
	}
	
	protected String getGetterMethodName(String name, boolean ignoreOLD) {
		if (ignoreOLD == true) {
			name = name.toUpperCase().replaceFirst("OLD", "");
		}
		StringBuffer sb = new StringBuffer();
		StringTokenizer tokenizer = new StringTokenizer(name.toLowerCase(), "_");
		String token;
		char[] chars;
		sb.append("get");
		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();
			chars = token.toCharArray();
			sb.append(Character.toUpperCase(chars[0]));
			sb.append(token.substring(1));
		}
		return sb.toString();
	}
	

	protected void addDeclareTableClause() {
		addDeclareTableClause(getSchemaName(), getTableName());
	}
	protected void addDeclareTableClause(String schema, String table) {
		startDeclareTableClause();
		sql.append(schema + "." + table);
	}
	protected void addDeclareColumns(String[] columnDefs) {
		startDeclareColumns();
		openParenthesis();
		this.setColumnNames(columnDefs);
		for (String columnDef : columnDefs) {
			addColumnDefinition(columnDef);
		}
		closeParenthesis();
		addNewLine();
		sql.append(" ON COMMIT PRESERVE ROWS");
	}

	protected void addInsertIntoClause() {
		addInsertIntoClause(getSchemaName(), getTableName());
	}
	protected void addInsertIntoClause(String schema, String table) {
		startInsertIntoClause();
		sql.append(schema + "." + table);
	}
	protected void addInsertColumns() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		String[] columnNames = new String[cds.size()];
		ColumnDescriptor cd;
		for (int i = 0; i < cds.size(); i++) {
			cd = cds.get(i);
			columnNames[i] = cd.getDbColumnName();
		}
		addInsertColumns(columnNames);
	}
	protected void addInsertColumns(String[] columnNames) {
		startInsertColumns();
		openParenthesis();
		setColumnNames(columnNames);
		for (String columnName : columnNames) {
			if (columnName != null) {
				addColumn(columnName);
			}
		}
		closeParenthesis();
		addNewLine();
	}
	protected void addInsertValues() {
		String[] columnNames = getColumnNames();
		int columns = columnNames.length;
		String[] values = new String[columns];
		for (int i = 0; i < columns; i++) {
			if (columnNames[i] != null) {
				values[i] = "?";
			}
		}
		addInsertValues(values);
	}
	protected void addInsertValues(PlanTO plan) {
		String[] columnNames = getColumnNames();
		String name;
		String value;
		String[] values = new String[columnNames.length];
		Class<? extends PlanTO> planClass = plan.getClass();
		Method[] methods = planClass.getMethods();
		Method method;
		String methodName;
		boolean methodExists;
		Object[] args = new Object[0];
		Class[] argTypes = new Class[0];
		try {
			for (int i = 0; i < columnNames.length; i++) {
				value = null;
				name = columnNames[i];
				if (name != null) {
					methodName = getGetterMethodName(name, true);
					methodExists = false;
					for (Method method2 : methods) {
						if (method2.getName().equals(methodName)) {
							methodExists = true;
							break;
						}
					}
					if (methodExists == true) {
					    method = planClass.getMethod(methodName, argTypes);
						value = (String) method.invoke(plan, args);
					}
					if (value != null) {
						value = "'" + value + "'";
					}
					values[i] = value;
				}
			}
		}
		catch (Exception e) {
			throw WrapperException.wrap(e);
		}
		addInsertValues(values);
	}
	protected void addInsertValues(PlanCriteriaTO planCriteria) {
		String[] columnNames = getColumnNames();
		String name;
		String value;
		String[] values = new String[columnNames.length];
		Class<? extends PlanCriteriaTO> planClass = planCriteria.getClass();
		Method[] methods = planClass.getMethods();
		Method method;
		String methodName;
		boolean methodExists;
		Object[] args = new Object[0];
		Class[] argTypes = new Class[0];
		try {
			for (int i = 0; i < columnNames.length; i++) {
				value = null;
				name = columnNames[i];
				if (name != null) {
					methodName = getGetterMethodName(name, true);
					methodExists = false;
					for (Method method2 : methods) {
						if (method2.getName().equals(methodName)) {
							methodExists = true;
							break;
						}
					}
					if (methodExists == true) {
					    method = planClass.getMethod(methodName, argTypes);
						value = (String) method.invoke(planCriteria, args);
					}
					if ((value != null) && (!value.equals("?"))) {
						value = "'" + value + "'";
					}
					values[i] = value;
				}
			}
		}
		catch (Exception e) {
			throw WrapperException.wrap(e);
		}
		addInsertValues(values);
	}
	
	protected void addInsertValues(String[] values) {
		startInsertValues();
		openParenthesis();
		for (int i = 0; i < getColumnNames().length; i++) {
			if (values[i] != null) {
				addValue(values[i]);
			}
		}
		closeParenthesis();
	}

	protected void addSelectColumnClause(String columnName) {
		String[] columnNames = {columnName};
		addSelectColumnsClause(columnNames);
		/*
		startSelectClause();
		if (isSelectCount()) {
			sql.append("COUNT ");
			openParenthesis();
		}
		if (isSelectDistinct()) {
			sql.append("DISTINCT ");
		}
		addColumn(columnName);
		closeAllParenthesis();
		*/
	}
	protected void addSelectColumnsClause(String[] columnNames) {
		startSelectClause();
		if (isSelectCount()) {
			sql.append("COUNT ");
			openParenthesis();
		}
		if (isSelectDistinct()) {
			sql.append("DISTINCT ");
		}
		for (String columnName : columnNames) {
			addColumn(columnName);
		}
		closeAllParenthesis();
	}
	protected void addSelectAllClause() {
		startSelectClause();
		if (isSelectCount()) {
			sql.append("COUNT ");
			openParenthesis();
		}
		sql.append("*");
		closeAllParenthesis();
	}
	protected void addSelectColumnDescriptorsClause() {
		startSelectClause();
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		ColumnDescriptor cd;
		for (int i = 0; i < cds.size(); i++) {
			cd = cds.get(i);
			addColumn(cd.getDbColumnName());
			}
	}

	protected void addUpdateClause() {
		addUpdateClause(getSchemaName(), getTableName());
	}
	protected void addUpdateClause(String schema, String table) {
		startUpdateClause();
		sql.append(schema + "." + table);
	}
	protected void addUpdateColumns() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		String[] columnNames = new String[cds.size()];
		ColumnDescriptor cd;
		for (int i = 0; i < cds.size(); i++) {
			cd = cds.get(i);
			columnNames[i] = cd.getDbColumnName();
		}
		addUpdateColumns(columnNames);
	}
	protected void addUpdateColumns(String[] columnNames) {
		startUpdateColumns();
		setColumnNames(columnNames);
		for (String columnName : columnNames) {
			if (columnName != null)
				addSetColumn(columnName, "?");
		}
	}

	protected void addDeleteClause() {
		addDeleteClause(getSchemaName(), getTableName());
	}
	protected void addDeleteClause(String schema, String table) {
		startDeleteClause();
		sql.append(schema + "." + table);
	}

	protected void addFromClause() {
		addFromClause(getSchemaName(), getTableName());
	}
	protected void addFromClause(String schema, String table) {
		startFromClause();
		sql.append(schema + "." + table);
	}

	protected void addWherePlanKeysClause(PlanTO plan) {
		startWhereClause();
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		ColumnDescriptor cd;
		Class<? extends PlanTO> planClass = plan.getClass();
		Method[] methods = planClass.getMethods();
		Method method;
		String methodName;
		String value;
		boolean methodExists;
		Object[] args = new Object[0];
		Class[] argTypes = new Class[0];

		try {
			for (int i = 0; i < cds.size(); i++) {
				cd = cds.get(i);
				methodName = cd.getGetterMethod().getName();
				methodExists = false;
				for (Method method2 : methods) {
					if (method2.getName().equals(methodName)) {
						methodExists = true;
						break;
					}
				}
				if (methodExists == false)
					break;
			    method = planClass.getMethod(methodName, argTypes);
				value = (String) method.invoke(plan, args);
				addWhereColumnCondition(cd.getDbColumnName(), value);
			}
		} 
		catch (Exception e) {
			throw WrapperException.wrap(e);
		}
	}

	protected void addWherePlanRowKeysClause(PlanCriteriaTO planCriteria) {
		startWhereClause();
		ColumnDescriptor cd = getTableDescriptor().getColumnDescriptor(PlanCriteriaTO.ENVIRONMENT_NAME);
		String value = planCriteria.getEnvironment();
		if ((cd == null) && (!value.equals(""))) {
			addWhereColumnCondition(PlanCriteriaTO.ENVIRONMENT_NAME, value);
		}

		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		Class<? extends PlanCriteriaTO> planClass = planCriteria.getClass();
		Method[] methods = planClass.getMethods();
		Method method;
		String methodName;
		boolean methodExists;
		Object[] args = new Object[0];
		Class[] argTypes = new Class[0];

		try {
			for (int i = 0; i < cds.size(); i++) {
				cd = cds.get(i);
				methodName = cd.getGetterMethod().getName();
				methodExists = false;
				for (Method method2 : methods) {
					if (method2.getName().equals(methodName)) {
						methodExists = true;
						break;
					}
				}
				if (methodExists == false)
					break;
			    method = planClass.getMethod(methodName, argTypes);
				value = (String) method.invoke(planCriteria, args);
				addWhereColumnCondition(cd.getDbColumnName(), value);
			}
		} 
		catch (Exception e) {
			throw WrapperException.wrap(e);
		}
	}

	protected void setColumnNames() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		String[] columnNames = new String[cds.size()];
		for (int i = 0; i < cds.size(); i++) {
			ColumnDescriptor cd = cds.get(i);
			columnNames[i] = cd.getDbColumnName();
		}
		setColumnNames(columnNames);
	}
	protected void addWhereClause() {
		startWhereClause();
		String[] columnNames = getColumnNames();
		for (String columnName : columnNames) {
			if (columnName != null) {
				addWhereColumnCondition(columnName, "?");
			}
		}
	}

	protected void addWhereClause(String[] columnNames) {
		startWhereClause();
		for (String columnName : columnNames) {
			addWhereColumnCondition(columnName, "?");
		}
	}

	protected void addGroupByClause(String[] columnNames) {
		startGroupByClause();
		for (String columnName : columnNames) {
			if (columnName != null) {
				addColumn(columnName);
			}
		}
	}

	protected void addOrderByClause() {
		Vector<ColumnDescriptor> cds = getTableDescriptor().getColumnDescriptors();
		String[] columnNames = new String[cds.size()];
		ColumnDescriptor cd;
		for (int i = 0; i < cds.size(); i++) {
			cd = cds.get(i);
			if (cd.isKey()) {
				columnNames[i] = cd.getDbColumnName();
			}
		}
		addOrderByClause(columnNames);
	}
	protected void addOrderByPlanKeysClause() {
		TableDescriptor td0 = environment.getTableDescMgr().getTableDescriptor(Constants.TABLE_ZERO_ID);
		Vector<ColumnDescriptor> cds = td0.getColumnDescriptors();
		String[] columnNames = new String[cds.size()];
		ColumnDescriptor cd;
		for (int i = 0; i < cds.size(); i++) {
			cd = cds.get(i);
			if (cd.isKey()) {
				columnNames[i] = cd.getDbColumnName();
			}
		}
		addOrderByClause(columnNames);
	}
	protected void addOrderByClause(String[] columnNames) {
		startOrderByClause();
		for (String columnName : columnNames) {
			if (columnName != null) {
				addColumn(columnName);
			}
		}
	}
	
	protected void startDeclareTableClause() {
		sql.append("DECLARE GLOBAL TEMPORARY TABLE ");
		setColumnNames(null);
	}
	protected void startDeclareColumns() {
		addNewLine();
		setAppendComma(false);
	}
	protected void addColumnDefinition(String columnDefinition) {
		if (isAppendComma()) 
			sql.append(", ");
		else
			setAppendComma(true);
		sql.append(columnDefinition);
		addNewLine();
	}
	protected void startSelectClause() {
		sql.append("SELECT ");
		setIgnoreHighValues(true);
		setAppendComma(false);
	}
	protected void addColumn(String name) {
		if (isAppendComma()) 
			sql.append(", ");
		else
			setAppendComma(true);
		sql.append(name);
	}
	protected void startFromClause() {
		addNewLine();
		sql.append("FROM ");
	}
	protected void startInsertIntoClause() {
		sql.append("INSERT INTO ");
		setColumnNames(null);
	}
	protected void startInsertColumns() {
		sql.append(" ");
		setAppendComma(false);
	}
	protected void startInsertValues() {
		sql.append("VALUES ");
		setAppendConditionalOperator(false);
		setAppendComma(false);
	}
	protected void addValue(String value) {
		if (isAppendComma()) 
			sql.append(", ");
		else
			setAppendComma(true);
		sql.append(value);
	}
	protected void startUpdateClause() {
		sql.append("UPDATE ");
		setIgnoreHighValues(false);
		setColumnNames(null);
	}
	protected void startUpdateColumns() {
		addNewLine();
		sql.append("SET ");
		setAppendComma(false);
	}
	protected void addSetColumn(String name, String value) {
		if (value != null) {
			if (isAppendComma()) 
				sql.append(", ");
			else
				setAppendComma(true);
			if (value.equals("?"))
				sql.append(name + " = " + value);
			else
				sql.append(name + " = '" + value + "'");
		}
	}
	protected void startDeleteClause() {
		sql.append("DELETE FROM ");
		setIgnoreHighValues(false);
	}
	protected void startWhereClause() {
		setAppendWHERE(true);
		setAppendConditionalOperator(false);
		setAppendAND(true);
	}
	protected void addConditionalOperator() {
		if (isAppendConditionalOperator()) {
			if (appendAND)
				sql.append(" AND ");
			else
				sql.append(" OR ");
		}
	}

	protected void addWhereColumnCondition(String name, String value) {
		addWhereColumnCondition(name, "=", value);
	}
	// TT wmA SPR 6140 - conditionally ignore product suffix == "*"
	protected void addWhereColumnCondition(String name, String operator, String value) {
		boolean isProductSuffix = PlanCriteriaTO.PRODUCT_SUFFIX_NAME.equals(name);
		boolean addColumn = !value.equals("") &&
							( !value.startsWith("*")
									|| (!isIgnoreHighValues() && (!isProductSuffix || !isIgnoreHighValueProductSuffix()) )
							);
		if ( addColumn ) {
			if (isAppendWHERE()) {
				addNewLine();
				sql.append("WHERE ");
				setAppendWHERE(false);
			}
			addConditionalOperator();
			//if ((value.indexOf("%") > 0) || (value.indexOf("_") > 0)) {
			//	operator = "LIKE";
			if ( value.indexOf("%") > 0 ) {
				operator = "LIKE";
			} else if (value.indexOf(",") > 0) {
				operator = "IN";
				value = "(" + value + ")";
			}
			if ((!value.equals("?")) && (!operator.equals("IN"))) {
				value = "'" + value + "'";
			}
			sql.append(name + " " + operator + " " + value);
			setAppendConditionalOperator(true);
		}
	}
	
	protected void startGroupByClause() {
		addNewLine();
		sql.append("GROUP BY ");
		setAppendComma(false);
	}
	protected void startOrderByClause() {
		addNewLine();
		sql.append("ORDER BY ");
		setAppendComma(false);
	}
	
	protected void addNewLine() {
		sql.append("\n");
	}
	protected void openParenthesis() {
		addConditionalOperator();
		sql.append("( ");
		openParenthesis++;
		setAppendConditionalOperator(false);
	}
	protected void closeParenthesis() {
		sql.append(" )");
		openParenthesis--;
		setAppendConditionalOperator(true);
	}
	protected void closeAllParenthesis() {
		while (openParenthesis > 0) {
			closeParenthesis();
		}
			
	}
	protected String getFinalStatement() {
		addNewLine();
		String s = sql.toString();
		sbPool.releaseEntry(sql);
		return s;
	}

	protected Environment getEnvironment() {
		return environment;
	}
	protected void setEnvironment(Environment env) {
		environment = env;
	}
	protected String getSchemaName() {
		return schemaName;
	}
	protected void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	protected String getTableName() {
		return tableName;
	}
	protected void setTableName(String tableName) {
		this.tableName = tableName;
	}
	protected TableDescriptor getTableDescriptor() {
		return tableDescriptor;
	}
	protected void setTableDescriptor(TableDescriptor tableDescriptor) {
		this.tableDescriptor = tableDescriptor;
	}
	protected boolean isSelectCount() {
		return selectCount;
	}
	protected void setSelectCount(boolean selectCount) {
		this.selectCount = selectCount;
	}
	protected boolean isSelectDistinct() {
		return selectDistinct;
	}
	protected void setSelectDistinct(boolean selectDistinct) {
		this.selectDistinct = selectDistinct;
	}
	protected boolean isAppendAND() {
		return appendAND;
	}
	protected void setAppendAND(boolean appendAND) {
		this.appendAND = appendAND;
		appendOR = appendAND ? false : true;
	}
	private boolean isAppendConditionalOperator() {
		return appendConditionalOperator;
	}
	private void setAppendConditionalOperator(boolean appendLogicalOperator) {
		this.appendConditionalOperator = appendLogicalOperator;
	}
	protected boolean isAppendOR() {
		return appendOR;
	}
	protected void setAppendOR(boolean appendOR) {
		this.appendOR = appendOR;
		appendAND = appendOR ? false : true;
	}
	private boolean isAppendComma() {
		return appendComma;
	}
	private void setAppendComma(boolean appendComma) {
		this.appendComma = appendComma;
	}
	protected boolean isAppendWHERE() {
		return appendWHERE;
	}
	protected void setAppendWHERE(boolean appendWHERE) {
		this.appendWHERE = appendWHERE;
	}
	protected String[] getColumnNames() {
		return columnNames;
	}
	protected void setColumnNames(String[] columnNames) {
		this.columnNames = columnNames;
	}
	protected boolean isIgnoreHighValues() {
		return ignoreHighValues;
	}
	protected void setIgnoreHighValues(boolean ignoreHighValues) {
		this.ignoreHighValues = ignoreHighValues;
	}	
	// TT wmA SPR 6140 - conditionally ignore product suffix == "*"
	protected boolean isIgnoreHighValueProductSuffix() {
		return ignoreHighValueProductSuffix;
	}
	protected void setIgnoreHighValueProductSuffix(boolean ignore) {
		this.ignoreHighValueProductSuffix = ignore;
	}
}
