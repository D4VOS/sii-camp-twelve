package pages.mystore.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.mystore.base.WidgetsPage;

import java.util.Objects;

import static helpers.DataParsers.parsePrice;

public final class ProductTilePage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ProductTilePage.class);
    private final WidgetsPage widgetsPage;
    @FindBy(css = ".product-title a")
    private WebElement name;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".price")
    private WebElement currentPrice;

    @FindBy(css = ".discount-percentage")
    private WebElement discountPercentage;

    @FindBy(css = ".variant-links .color")
    private WebElement colors;

    @FindBy(css = ".quick-view")
    private WebElement quickViewButton;

    @FindBy(css = ".product-flags .discount")
    private WebElement discountFlag;


    public ProductTilePage(WebDriver driver, WebElement element) {
        super(driver, element);
        widgetsPage = new WidgetsPage(driver);
    }

    public String getName() {
        return name.getText();
    }

    public boolean isDiscountedBy(String value) {
        boolean visibility = isVisible(discountFlag);
        String discountFlagText = discountFlag.getText();
        String discountPercentageText = discountPercentage.getAttribute("textContent");

        logger.debug("Visibility: " + visibility +
                ", Discount flag: " + discountFlagText +
                ", Discount: " + discountPercentageText +
                ", Expected: " + value);
        return visibility
                && Objects.equals(discountFlagText, value)
                && Objects.equals(discountPercentageText, value);
    }

    public float getCurrentPrice() {
        return parsePrice(currentPrice.getText());
    }

    public float getRegularPrice() {
        return parsePrice(regularPrice.getText());
    }


    public ProductQuickViewPage quickView() {
        Actions actions = new Actions(driver);
        actions.moveToElement(name).perform();
        quickViewButton.click();
        return new ProductQuickViewPage(driver, widgetsPage.getModal());
    }
}
