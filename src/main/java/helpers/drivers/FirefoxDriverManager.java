package helpers.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverManager extends WebDriverManger {
    @Override
    public WebDriver newDriver() {
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver(getOptions());
    }

    @Override
    public FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        return options.addArguments("start-maximized");
    }
}
