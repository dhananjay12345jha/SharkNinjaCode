package com.salmon.test.framework.helpers;

import com.browserstack.local.Local;
import cucumber.api.Scenario;
import cucumber.api.java.Before;

import java.util.HashMap;


public class ScenarioHook {

  protected static String scenarioName;

  public static String getScenarioName() {
    return scenarioName;
  }

  @Before
  public void scenarioInfo(Scenario scenario) {
    this.scenarioName = scenario.getName();
  }
}
