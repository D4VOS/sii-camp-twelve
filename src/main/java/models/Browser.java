package models;

import helpers.drivers.*;
import org.openqa.selenium.WebDriver;

public enum Browser {
    CHROME("chrome", new ChromeDriverManager()),
    FIREFOX("firefox", new FirefoxDriverManager()),
    IE("ie", new IEDriverManager()),
    EDGE("edge", new EdgeDriverManager());

    private final String name;
    private final WebDriverManger driverManager;

    Browser(String name, WebDriverManger driverManger) {
        this.name = name;
        this.driverManager = driverManger;
    }

    public WebDriver getDriver() {
        return driverManager.newDriver();
    }
}
