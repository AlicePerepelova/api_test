package tests;

import config.WebDriverProvider;
import helpers.Attach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
  @BeforeEach
  void setUp() { WebDriverProvider.setUp(); }

  @AfterEach
  public void tearDown() {
    Attach.screenshotAs("Last screen");
    Attach.addVideo();
    Attach.browserConsoleLogs();
    Attach.pageSource();
    closeWebDriver();
  }
}
