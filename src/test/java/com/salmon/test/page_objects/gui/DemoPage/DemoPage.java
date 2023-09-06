package com.salmon.test.page_objects.gui.DemoPage;

import com.salmon.test.framework.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Demo Page Class - showing examples of best practices
 */
public class DemoPage extends PageObject {

    // ******************* PAGE IDENTIFIERS
    // ALWAYS START WITH BASIC PAGE UNIQUE ELEMENTS :: HEADING / TITLE / URL - will be used to assert we are on expected page !!
    public final String url = "http://toolsqa.com/automation-practice-form/";
    // in future main would be http://toolsqa.com/ and this page would simple concatenate the rest of the URL : 'automation-practice-form'
    public final String expectedTitle = "Demo Form for practicing Selenium Automation";//"Demo Form for practicing Selenium Automation";
    public Boolean isLoaded = false;

    // Below is to get around Zscaler Salmon authenication page
    public By authUnInput = By.cssSelector("#username_input");
    public By authPwInput = By.cssSelector("body > div > div > form > table.m_tbl > tbody > tr > td > table.table-upper > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(1) > td > div.table-right-column > table.table-field-input > tbody > tr > td > div > input");
    public By submitButton = By.cssSelector("input.btn");

    // ******************* PAGE MAPPINGS
    // ELEMENT MAPPING
    // Mapping H1 Title
    // leading '.' denotes class
    // leading '#' denotes id

    //private static final By pageTitle = By.cssSelector("h1.white");

    // Mapping Navigation table - that will be looped through
    public By navigationList = By.cssSelector(".navigation>ul>li");

    // Mapping Link
    private static final By navigationLink = By.cssSelector(".control-group.a");

    // Mapping Input Boxes
    public By firstNameInputBox = By.cssSelector("input[name='firstname']");
    private static final By surNameInputBox = By.cssSelector("input[name='lastname']");

    // Mapping Radio Button
    public By maleRadio = By.cssSelector("input#sex-0");
    // input
    public By inputField = By.cssSelector("input#datepicker");

    // Mapping select dropdown
    public By continentsDropDown = By.cssSelector("select#continents");

    public By demoPracticeTable = By.cssSelector("table.tsc_table_s13");

    // Mapping button
    private static final By button = By.cssSelector("button#submit");

    // Mapping text
    private static final By text = By.cssSelector("label.abc");

    // Mapping something can use getTextFor method on
    public By formTitleHeading = By.cssSelector("div.control-group");

    // ******************* PAGE ACTIONS





}
