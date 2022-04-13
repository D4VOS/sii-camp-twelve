package helpers.drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class IEDriverManager extends WebDriverManger {
    @Override
    public WebDriver newDriver() {
        WebDriverManager.iedriver().setup();
        return new InternetExplorerDriver(getOptions());
    }

    @Override
    public InternetExplorerOptions getOptions() {
        return new InternetExplorerOptions();
    }
}
