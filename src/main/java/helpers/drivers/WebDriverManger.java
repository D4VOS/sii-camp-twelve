package helpers.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.AbstractDriverOptions;

public abstract class WebDriverManger {
    public abstract WebDriver newDriver();

    public abstract AbstractDriverOptions getOptions();
}
