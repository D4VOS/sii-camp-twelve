package config.properties;

import models.config.BrowserConfig;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static helpers.BrowserFactory.getDriverByName;

public class BrowserEnvironment {
    private static final Logger logger = LoggerFactory.getLogger(BrowserEnvironment.class);

    private final BrowserConfig config;
    private WebDriver driver;

    public BrowserEnvironment(BrowserConfig browserConfig) {
        this.config = browserConfig;
    }

    public BrowserConfig getConfig() {
        return config;
    }

    public WebDriver getDriver() {
        this.driver = getDriverByName(config.getName());
        logger.info("Initialized " + config.getName() + " browser driver.");
        
        driver.get(System.getProperty("app.url"));
        return driver;
    }
}
