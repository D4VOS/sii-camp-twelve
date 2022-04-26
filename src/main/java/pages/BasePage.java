package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;

public abstract class BasePage {
    protected static final int TIMEOUT_S = Integer.parseInt(System.getProperty("webElement.timeOut"));
    protected static final int SLEEP_MS = Integer.parseInt(System.getProperty("webElement.polling"));
    private static final Logger logger = LoggerFactory.getLogger(BasePage.class);

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

    private void initDrivers(WebDriver driver) {
//        driver = new ShadowDriver(driver);
        this.driver = driver;
        this.jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_S), Duration.ofMillis(SLEEP_MS));
        logger.debug("Created WebDriverWait with timeout: " + TIMEOUT_S + "s and sleep: " + SLEEP_MS + "ms");
    }

    protected void highLight(WebElement element, String color) {
        jse.executeScript("arguments[0].style.border='3px solid " + color + '\'', element);
    }

    protected void highLight(WebElement element) {
        highLight(element, "red");
    }

    public Dimension getViewPortSize() {
        int width = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerWidth")));
        int height = Integer.parseInt(String.valueOf(jse.executeScript("return window.innerHeight")));

        return new Dimension(width, height);
    }

    public boolean isPageLoaded() {
        return isDOMLoaded() && isAjaxCompletedTasks();
    }

    public boolean isDOMLoaded() {
        String state = jse.executeScript("return document.readyState").toString();
        return state.equals("complete");
    }

    public boolean isAjaxCompletedTasks() {
        String state = jse.executeScript("return jQuery.active").toString();
        return state.equals("0");
    }

    public void waitForLoad() {
        wait.until(driver -> isPageLoaded());
    }

    public void hoverOnElement(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

    protected String getSingleNodeText(WebElement element) {
        String text = element.getText().trim();
        List<WebElement> children = element.findElements(By.xpath("./*"));
        for (WebElement child : children) {
            text = text.replaceFirst(child.getText(), "").trim();
        }
        return text;
    }
}
