package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebDriverProvider {
  private static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
  final String userName = System.getProperty("userName");
  final String password = System.getProperty("password");
  public WebDriverProvider(WebConfig config) {
    this.config = config;
  }
  public static String getUserName() {
    return System.getProperty("userName");
  }

  public static String getPassword() {
    return System.getProperty("password");
  }

  public static void setUp() {

    RestAssured.baseURI = config.getBaseUrl();
    Configuration.browser = config.getBrowser().toString();
    Configuration.browserVersion = config.getBrowserVersion();
    Configuration.pageLoadStrategy = "eager";



    if (config.isRemote()) {
      Configuration.remote = String.valueOf(config.getRemoteUrl());

      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("selenoid:options", Map.of(
        "enableVNC", true,
        "enableVideo", true,
        "enableLog", true

      ));

      Configuration.browserCapabilities = capabilities;
    }
  }
}