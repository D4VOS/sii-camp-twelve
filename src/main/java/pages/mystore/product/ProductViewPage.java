package pages.mystore.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.mystore.base.WidgetsPage;

import java.util.Objects;

import static helpers.WebElementHelpers.isVisible;

public class ProductViewPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(ProductViewPage.class);

    private final WidgetsPage widgetsPage;
    @FindBy(css = ".current-price .discount")
    private WebElement discount;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".price")
    private WebElement currentPrice;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityField;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCart;

    @FindBy(css = ".product-customization button")
    private WebElement saveCustomizationButton;

    @FindBy(css = ".product-customization-item textarea")
    private WebElement customizationField;

    public ProductViewPage(WebDriver driver) {
        super(driver);
        widgetsPage = new WidgetsPage(driver);
    }

    public boolean isDiscountedBy(int value) {
        String discountText = "SAVE " + value + "%";
        logger.info("Visibility: " + isVisible(discount) +
                ", Discount text: " + discount.getText() +
                ", Expected: " + discountText);
        return isVisible(discount) && Objects.equals(discount.getText(), discountText);
    }

    public ProductViewPage setAmount(int amount) {
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(amount));
        return this;
    }

    public QuickCartSummaryPage addToCart() {
        addToCart.click();
        WebElement modal = widgetsPage.getModal();
        return new QuickCartSummaryPage(driver, modal);
    }

    public ProductViewPage customizeItem(String text) {
        customizationField.click();
        customizationField.sendKeys(text);
        saveCustomization();
        return this;
    }

    public void saveCustomization() {
        saveCustomizationButton.click();
    }

    public boolean isCustomizable() {
        return isVisible(customizationField);
    }
}
