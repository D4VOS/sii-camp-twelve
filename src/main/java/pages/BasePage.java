package pages;

import io.github.sukgu.ShadowDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BasePage {
    protected static final int TIMEOUT_S = Integer.parseInt(System.getProperty("webElement.timeOut"));
    protected static final int SLEEP_MS = Integer.parseInt(System.getProperty("webElement.polling"));
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class.getName());

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor jse;

    public BasePage(WebDriver driver) {
        initDrivers(driver);
        PageFactory.initElements(driver, this);
    }

    public BasePage(WebDriver driver, WebElement element) {
        initDrivers(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public void initDrivers(WebDriver driver) {
        driver = new ShadowDriver(driver);
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_S), Duration.ofMillis(SLEEP_MS));
        logger.debug("Created WebDriverWait with timeout: " + TIMEOUT_S + "s and sleep: " + SLEEP_MS + "ms");
    }

    public void highLight(WebElement element) {
        jse.executeScript("arguments[0].style.border='3px solid red'", element);
    }

    public Dimension getViewPortSize() {
        int width = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerWidth")));
        int height = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerHeight")));

        return new Dimension(width, height);
    }


    public boolean isPageLoaded() {
        return jse.executeScript("return document.readyState").equals("complete");
    }

    public void waitForLoad() {
        wait.until(driver -> isPageLoaded());
    }

    public WebElement getParentUntilHaveClass(WebElement element, String className) {
        int depthLimit = 3;
        WebElement current = element;
        if (current == null) {
            logger.info("Element should exist.");
            return null;
        }
        for (int i = 0; i < depthLimit; i++) {
            current = getParent(current);
            if (current == null) {
                logger.info("Not found parent with class " + className);
                return null;
            }
            if (current.getAttribute("class").contains(className)) {
                return current;
            }
        }
        logger.info("Not found parent with class " + className);
        return null;
    }

    public WebElement getParent(WebElement child) {
        return child.findElement(By.xpath("./.."));
    }

    public void hoverOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }
}
