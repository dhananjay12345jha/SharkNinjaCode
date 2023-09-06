package com.salmon.test.step_definitions.gui.SNEX;

import com.salmon.test.framework.helpers.Props;
import com.salmon.test.page_objects.gui.SNEX.LoginPage;
import cucumber.api.java.en.When;
import org.testng.Assert;

public class LoginPageSteps {

    private LoginPage loginPage;

    public LoginPageSteps(LoginPage loginPage){
        this.loginPage=loginPage;
    }

    @When("^I login in into SNEX with \"(.*?)\" and \"(.*?)\"$")
    public void i_login_Into_SNEX_With(String username,String password){
        String uname= Props.getProp(username);
        String pass= Props.getProp(password);
        Assert.assertTrue(loginPage.performSNEXLoginWith(uname,pass)!=null,"Unable to login into SNEX dashboard please check");
    }



}
