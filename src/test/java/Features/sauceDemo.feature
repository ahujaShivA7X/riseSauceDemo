Feature: SauceDemo Automation

  @SmokeTest" @RegressionTest", @End2End
  Scenario Outline: Login to SauceDemo website and randomly choose 3 selections
    Given user is on the login page
    When user enters "<username>" and "<password>"
    Then user selects three selection randomly

    Examples:
      | username      | password     |
      | standard_user | secret_sauce |