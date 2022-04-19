package helpers.drivers;

import models.config.Browser;
import org.openqa.selenium.WebDriver;

public class DriverFactory {
    public static WebDriver getDriverByName(String browserName) {
        return Browser.get(browserName).getDriver();
    }
}

