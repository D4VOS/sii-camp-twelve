package pages.mystore.product;

import factory.user.UserFactory;
import models.entities.User;
import models.shop.CartItem;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.base.WidgetsPage;
import pages.mystore.basket.ProductInfoQueryable;

import java.util.Objects;

import static helpers.data.DataUtils.parsePrice;
import static helpers.web.WebElementHelpers.isVisible;
import static helpers.web.wrappers.InputActions.performSendKeys;

public class ProductViewPage extends MyStoreBasePage implements ProductInfoQueryable {
    private static final Logger logger = LoggerFactory.getLogger(ProductViewPage.class);

    private final WidgetsPage widgets;
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
        widgets = new WidgetsPage(driver);
    }

    public String getName() {
        return name.getText();
    }

    public float getPrice() {
        return parsePrice(currentPrice.getText());
    }

    public String getCustomizedText() {
        if (isVisible(customizedLabel)) {
            return customizedLabel.getText();
        }
        return null;
    }

    public int getQuantity() {
        return Integer.parseInt(quantityField.getAttribute("value"));
    }

    public boolean isDiscountedBy(int value) {
        String discountText = "SAVE " + value + "%";
        logger.info("Visibility: " + isVisible(discount) +
                ", Discount text: " + discount.getText() +
                ", Expected: " + discountText);
        return isVisible(discount) && Objects.equals(discount.getText(), discountText);
    }

    public ProductViewPage setAmount(int amount) {
        performSendKeys(quantityField, String.valueOf(amount));
        return this;
    }

    public QuickCartSummaryPage addToCart() {
        addToCart.click();
        CartItem item = new CartItem(this);
        logger.info("Added: " + getQuantity() + "x " + item);
        shoppingCart.add(item, getQuantity());
        WebElement modal = widgets.getModal();
        return new QuickCartSummaryPage(driver, modal);
    }

    public ProductViewPage customizeItem(String text) {
        performSendKeys(customizationField, text);
        saveCustomizationButton.click();
        logger.info("Customized with text: " + text);
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
