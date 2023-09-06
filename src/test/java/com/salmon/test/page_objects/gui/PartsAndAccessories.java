package com.salmon.test.page_objects.gui;

import com.salmon.test.framework.PageObject;

public class PartsAndAccessories extends PageObject {

//	private static final By moreInfoBtn = By.xpath("");


    public String getPartsAndAccessoriesPageTitle() {
        return webDriver.getTitle();

    }



}