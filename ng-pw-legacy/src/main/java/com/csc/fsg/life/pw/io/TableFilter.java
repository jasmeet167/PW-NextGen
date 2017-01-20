package com.csc.fsg.life.pw.io;

/* Modifications: ENH1006, WMA-1182 */

public class TableFilter {

	private String companyCode = null;
	private String productPrefix = null;
	private String tableName = null;
	private String tableSubset = null;

	private String view;
	
	private boolean pagingMode;
	private boolean isNextPage;
	private boolean isFirstPage;
	private boolean isPreviousPage;
	private String pagingKey;
	
	private boolean islocatorView;
	private String pagingModeStr;

	// WMA-1182
	private boolean isImport;
	
	private String userCondition = null;

	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String company) {
		this.companyCode = company;
	}
	public boolean isFirstPage() {
		return isFirstPage;
	}
	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}
	public boolean isLocatorView() {
		return islocatorView;
	}
	public void setIsLocatorView(boolean islocatorView) {
		this.islocatorView = islocatorView;
	}

	// WMA-1182
	public boolean isImport() {
		return isImport;
	}
	public void setIsImport(boolean isImport) {
		this.isImport = isImport;
	}
	
	public boolean isNextPage() {
		return isNextPage;
	}
	public void setNextPage(boolean isNextPage) {
		this.isNextPage = isNextPage;
	}
	public boolean isPreviousPage() {
		return isPreviousPage;
	}
	public void setPreviousPage(boolean isPreviousPage) {
		this.isPreviousPage = isPreviousPage;
	}
	public String getPagingKey() {
		return pagingKey;
	}
	public void setPagingKey(String pagingKey) {
		this.pagingKey = pagingKey;
	}
	public boolean isPagingMode() {
		return pagingMode;
	}
	public void setPagingMode(boolean pagingMode) {
		this.pagingMode = pagingMode;
	}
	public String getProductPrefix() {
		return productPrefix;
	}
	public void setProductPrefix(String productPrefix) {
		this.productPrefix = productPrefix;
	}
	public String getTableSubset() {
		return tableSubset;
	}
	public void setTableSubset(String subset) {
		this.tableSubset = subset;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getView() {
		return view;
	}
	public void setView(String view) {
		this.view = view;
	}

	public String getTableId(){
		if (tableName==null)
			return null;
		return TableDescriptorManager.getTableId(tableName);
	}
	
	public String getPagingModeStr() {
		return pagingModeStr;
	}
	
	public void setPagingModeStr(String pagingModeStr) {
		this.pagingModeStr = pagingModeStr;
	}
	
	public String getUserCondition() {
		return userCondition;
	}
	
	public void setUserCondition(String userCondition) {
		this.userCondition = userCondition;
	}
}
