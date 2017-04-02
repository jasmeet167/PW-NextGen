
package com.csc.fsg.life.pw.web.config;

import java.util.*;

import com.csc.fsg.life.pw.common.util.Constants;
import com.csc.fsg.life.pw.common.util.InstallConfigBean;
import com.csc.fsg.life.pw.web.actions.rcm.beans.ExternalAuditBean;

/* Modification: CONFIGENH, ENH946, T0129 */

public class ProductManager {
	
	private Map<String, ProductObject> products = new TreeMap<String, ProductObject>();		
	
    private static ProductManager productMgr = null;
            
    private ProductManager()   {
    	products.put("WMA", getWMAProductObject());
    }
    
    public static ProductManager getInstance() {
    	if (productMgr==null){
    		productMgr = new ProductManager();
     	}
        return productMgr;
    }

	private ProductObject getWMAProductObject() {
		ProductObject po= new ProductObject();
		po.setId("WMA");
		po.setColorTheme("WMA");
		// ENH946 - use constants for fund info defaults
		po.setDefaultFund(Constants.DEFAULT_FUND);
		po.setMaxFund(Constants.MAX_FUND_NUMBER);
		po.setMaxFundDigits(Constants.MAX_FUND_DIGITS);
		po.setName("Wealth Management Accelerator Product Wizard");
		po.setVersion("1601");
		//po.setTableConstraintsBean(TableConstraintsBean.getInstance());
		po.setInstallBean((InstallConfigBean)AppConfig.getContext().getBean("wmaInstallConfig"));
		po.setExtAuditBean(ExternalAuditBean.getInstance());
		return po;
	}
    
	public Map<String, ProductObject> getProducts() {
		return products;
	}

	public void setProducts(Map<String, ProductObject> products) {
		this.products = products;
	}
	

	public ProductObject getProduct(String key){
		if (key!=null)
			return (ProductObject)products.get(key.trim());
		return null;
	}	
	
	
}
