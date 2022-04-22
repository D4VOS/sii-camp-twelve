package pages.mystore.product;

import factory.user.UserFactory;
import models.config.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.base.WidgetsPage;

import java.util.Objects;

import static helpers.DataParsers.parsePrice;
import static helpers.WebElementHelpers.isVisible;

public class ProductViewPage extends MyStoreBasePage {
    private static final Logger logger = LoggerFactory.getLogger(ProductViewPage.class);

    private final WidgetsPage widgetsPage;
    @FindBy(css = ".current-price .discount")
    private WebElement discount;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".product-container [itemprop=name]")
    private WebElement name;
    @FindBy(css = "[itemprop=price]")
    private WebElement currentPrice;

    @FindBy(css = "#quantity_wanted")
    private WebElement quantityField;

    @FindBy(css = ".add-to-cart")
    private WebElement addToCart;

    @FindBy(css = ".product-customization button")
    private WebElement saveCustomizationButton;

    @FindBy(css = ".product-customization-item textarea")
    private WebElement customizationField;

    @FindBy(css = ".customization-message label")
    private WebElement customizedLabel;

    public ProductViewPage(WebDriver driver) {
        super(driver);
        widgetsPage = new WidgetsPage(driver);
    }

    public String getName() {
        return name.getText();
    }

    public Float getPrice() {
        return parsePrice(currentPrice.getText());
    }

    public String getCustomizedText() {
        if (isVisible(customizedLabel)) {
            return customizedLabel.getText();
        }
        return null;
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
        saveCustomizationButton.click();
        return this;
    }

    public boolean isCustomizable() {
        return isVisible(customizationField);
    }


    public ProductViewPage customizeIfPossible() {
        if (isVisible(customizationField)) {
            User fakePerson = new UserFactory().getRandomUser();
            String customText = fakePerson.toString();
            customizeItem(customText);
        }
        return this;
    }

}
