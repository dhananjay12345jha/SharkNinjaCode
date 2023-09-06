package com.salmon.test.step_definitions.database;

import static org.assertj.core.api.Assertions.assertThat;

import com.salmon.test.framework.helpers.DatabaseHelper;
import com.salmon.test.framework.helpers.Props;
import com.salmon.test.models.database.UserRegModel;
import com.salmon.test.sql.UserRegDB;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.List;
import lombok.AllArgsConstructor;

/**
 * Step Definition implementation class for Cucumber Steps defined in Feature file
 */
@AllArgsConstructor
public class DatabaseSteps {

  private UserRegDB userRegDB;
  private List results;
  private List<UserRegModel> userRegModels;


  @When("^I run query \"([^\"]*)\" to get list of users in record set$")
  public void I_run_query_to_get_list_of_users_in_record_set(String sqlQuery) throws Throwable {
    results = DatabaseHelper.executeQuery(Props.getMessage(sqlQuery));
  }

  @When("^I run query \"([^\"]*)\" to get list of users in bean$")
  public void I_run_query_to_get_list_of_users_in_bean(String sqlQuery) {
    userRegModels = userRegDB.getUserRegResults(Props.getMessage(sqlQuery));
  }

  @Then("^the list of users is not empty$")
  public void the_list_of_users_is_not_empty() {
    assertThat(results.size()).isGreaterThan(0);
  }

  @Then("^the list of users contains \"([^\"]*)\" as a user$")
  public void the_list_of_users_contains_as_a_user(String userName) {
    List<UserRegModel> userRegModels = this.userRegModels;
    boolean user = userRegModels.stream()
        .map(userRegMode -> userRegMode.getLogonId().contains(userName)).findFirst().get();
    assertThat(user).isTrue();
  }


}