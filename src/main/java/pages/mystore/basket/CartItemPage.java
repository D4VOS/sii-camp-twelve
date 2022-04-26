package pages.mystore.basket;

import models.shop.CartItem;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;
import pages.mystore.base.WidgetsPage;

import java.util.stream.IntStream;

import static helpers.data.DataUtils.parsePrice;
import static helpers.web.WebElementHelpers.isVisible;
import static pages.mystore.base.MyStoreBasePage.shoppingCart;

public class CartItemPage extends BasePage implements ProductInfoQueryable {

    private static final Logger logger = LoggerFactory.getLogger(CartItemPage.class);
    private final WidgetsPage widgets;
    @FindBy(css = ".js-cart-line-product-quantity")
    private WebElement quantity;

    @FindBy(css = ".current-price .price")
    private WebElement currentPrice;

    @FindBy(css = ".product-price")
    private WebElement totalPrice;

    @FindBy(css = ".product-line-info a")
    private WebElement name;

    @FindBy(css = "[data-target^='#product-customizations-modal']")
    private WebElement customizationShowModalButton;

    @FindBy(css = ".customization-modal .value")
    private WebElement customizationText;

    @FindBy(css = ".touchspin-up")
    private WebElement quantitySingleAdd;

    @FindBy(css = ".touchspin-down")
    private WebElement quantitySingleRemove;

    @FindBy(css = ".remove-from-cart")
    private WebElement removeItem;


    public CartItemPage(WebDriver driver, WebElement element) {
        super(driver, element);
        widgets = new WidgetsPage(driver);
    }

    public String getName() {
        return name.getText();
    }

    public float getPrice() {
        return parsePrice(currentPrice.getText());
    }

    public float getTotalPrice() {
        return parsePrice(totalPrice.getText());
    }

    public int getQuantity() {
        return Integer.parseInt(quantity.getAttribute("value"));
    }

    public String getCustomizedText() {
        if (isVisible(customizationShowModalButton)) {
            customizationShowModalButton.click();
            wait.until(driver -> !customizationText.getText().isEmpty());
            String text = customizationText.getText();
            widgets.closeModal();
            return text;
        }
        return null;
    }

    public CartItemPage setQuantity(int amount) {
        CartItem itemToUpdate = new CartItem(this);
        logger.info("Updating " + itemToUpdate + " amount: " + getQuantity() + "->" + amount);
        shoppingCart.update(itemToUpdate, amount);
        quantity.click();
        quantity.sendKeys(Keys.chord(Keys.CONTROL, "a"));   // Actions was flaky :/
        quantity.sendKeys(String.valueOf(amount), Keys.RETURN);
        waitForLoad();
        return this;
    }

    public CartItemPage changeQuantity(int times) {
        WebElement clickTarget;
        CartItem item = new CartItem(this);
        logger.info("Changing quantity by arrows. Times: " + times + ". Item: " + item);
        if (times > 0) {
            clickTarget = quantitySingleAdd;
            shoppingCart.add(new CartItem(this), Math.abs(times));
        } else {
            clickTarget = quantitySingleRemove;
            shoppingCart.remove(new CartItem(this), Math.abs(times));
        }
        IntStream.range(0, Math.abs(times)).forEach(i -> clickTarget.click());
        totalPrice.click();
        waitForLoad();
        return this;
    }

    public void remove() {
        CartItem item = new CartItem(this);
        logger.info("Removing item: " + item);
        shoppingCart.remove(item);
        removeItem.click();
        waitForLoad();
    }
}
