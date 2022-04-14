package pages;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class PageBase {
    protected static final int TIMEOUT_S = Integer.parseInt(System.getProperty("webElement.timeOut"));
    protected static final int SLEEP_MS = Integer.parseInt(System.getProperty("webElement.polling"));
    private static final Logger logger = LoggerFactory.getLogger(PageBase.class.getName());

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor jse;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_S), Duration.ofMillis(SLEEP_MS));
        logger.debug("Created WebDriverWait with timeout: " + TIMEOUT_S + "s and sleep: " + SLEEP_MS + "ms");
        PageFactory.initElements(driver, this);
    }

    public void highLight(WebElement element) {
        jse.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public Dimension getViewPortSize() {
        int width = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerWidth")));
        int height = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerHeight")));

        return new Dimension(width, height);
    }
}