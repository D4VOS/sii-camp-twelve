package models.config;

import exceptions.UnsupportedBrowserException;
import helpers.drivers.*;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.Objects;

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

    public static Browser get(String name) {
        return Arrays.stream(values())
                .filter(b -> Objects.equals(b.name, name))
                .findFirst()
                .orElseThrow(() -> new UnsupportedBrowserException(name + " browser is not supported yet."));
    }
}
