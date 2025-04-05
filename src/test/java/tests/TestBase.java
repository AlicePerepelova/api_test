package tests;

import config.WebDriverProvider;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
  @BeforeEach
  void setUp() {
    final String userName=System.getProperty("userName");
    final String password=System.getProperty("password");
    WebDriverProvider.setUp();
  }

  @AfterEach
  public void tearDown() {
    closeWebDriver();
  }
}
