package config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import lombok.Data;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;
@Data
public class WebDriverProvider {
  private static WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
  public static final String userName2 = System.getProperty("jenkins.userName2");
  public static final String password2 = System.getProperty("jenkins.password2");
  public WebDriverProvider(WebConfig config) {
    this.config = config;
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