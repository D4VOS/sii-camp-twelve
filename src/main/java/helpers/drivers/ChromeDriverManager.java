package helpers.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverManager extends WebDriverManger {
    @Override
    public WebDriver newDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(getOptions());
    }

    @Override
    public ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        return options.addArguments("start-maximized");
    }
}
