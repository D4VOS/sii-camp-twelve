package pages.mystore.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.mystore.base.WidgetsPage;

import java.util.Objects;

public class ProductQuickViewPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductQuickViewPage.class);
    private final WidgetsPage widgetsPage;
    @FindBy(css = ".discount")
    private WebElement discount;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".price")
    private WebElement currentPrice;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityField;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCart;

    public ProductQuickViewPage(WebDriver driver, WebElement element) {
        super(driver, element);
        widgetsPage = new WidgetsPage(driver);
    }

    public boolean isDiscountedBy(int value) {
        String discountText = "SAVE " + value + "%";
        logger.info("Visibility: " + isVisible(discount) +
                ", Discount: " + discount.getText() +
                ", Expected: " + value);
        return isVisible(discount) && Objects.equals(discount.getText(), discountText);
    }

    public ProductQuickViewPage setAmount(int amount) {
        quantityField.clear();
        quantityField.sendKeys(String.valueOf(amount));
        return this;
    }

    public QuickCartSummaryPage addToCart() {
        addToCart.click();
        wait.until(ExpectedConditions.invisibilityOf(addToCart));
        return new QuickCartSummaryPage(driver, widgetsPage.getModal());
    }
}