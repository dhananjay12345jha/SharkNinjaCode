package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

public class ProductCategoryPage extends PageObject {

//	private static final By moreInfoBtn = By.xpath("");
	
			
	public String getProductCategoryPageTitle() {
		return webDriver.getTitle();
	}
	
}
