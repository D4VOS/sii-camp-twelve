package pages.mystore.product.grid;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static helpers.DataParsers.parsePrice;
import static helpers.WebElementHelpers.isVisible;

public class FilterPage extends BasePage {
    @FindBy(css = ".ui-slider-handle:nth-of-type(1)")
    private WebElement leftPriceSliderHandle;

    @FindBy(css = ".ui-slider-handle:nth-of-type(2)")
    private WebElement rightPriceSliderHandle;

    @FindBy(css = "li p")
    private WebElement currentPriceFilter;

    @FindBy(css = ".js-search-filters-clear-all")
    private WebElement clearAllFiltersButton;

    public FilterPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public FilterPage setMinimumPrice(float price) {
        if (isMinimumPriceEquals(price)) {
            return this;
        }
        int direction = getDirection(price, getCurrentMin());
        Actions action = new Actions(driver);

        action.clickAndHold(leftPriceSliderHandle);
        while (!isMinimumPriceEquals(price)) {
            action.moveByOffset(5 * direction, 0).perform();
        }
        action.release().perform();
        waitForLoad();
        return this;
    }

    public FilterPage setMaximumPrice(float price) {
        if (isMaximumPriceEquals(price)) {
            return this;
        }
        int direction = getDirection(price, getCurrentMax());
        Actions action = new Actions(driver);

        action.clickAndHold(rightPriceSliderHandle);
        while (!isMaximumPriceEquals(price)) {
            action.moveByOffset(5 * direction, 0).perform();
        }
        action.release().perform();
        waitForLoad();
        return this;
    }

    private int getDirection(float price, float current) {
        float difference = price - current;
        if (difference > 0) {
            return 1;
        } else if (difference < 0) {
            return -1;
        }
        return 0;
    }

    private boolean isMinimumPriceEquals(float price) {
        return getCurrentMin() == price;
    }

    private boolean isMaximumPriceEquals(float price) {
        return getCurrentMax() == price;
    }

    private float getCurrentMin() {
        return parsePrice(getPriceBorders()[0]);
    }

    private float getCurrentMax() {
        return parsePrice(getPriceBorders()[1]);
    }

    private String[] getPriceBorders() {
        String sep = ";";
        return currentPriceFilter.getText()
                .replaceAll("\\s+-\\s+", sep)
                .split(sep);
    }

    public void clearAll() {
        if (isVisible(clearAllFiltersButton)) {
            clearAllFiltersButton.click();
        }
    }
}
