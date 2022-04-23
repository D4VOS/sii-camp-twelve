package pages.mystore.basket;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.stream.IntStream;

import static helpers.data.DataUtils.parsePrice;
import static helpers.web.WebElementHelpers.isVisible;

public class CartItemPage extends BasePage implements ProductInfoQueryable {

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
            return customizationText.getText();
        }
        return null;
    }

    public CartItemPage setQuantity(int amount) {
        Actions action = new Actions(driver);
        action.moveToElement(quantity)
                .click(quantity)
                .sendKeys(Keys.chord(Keys.CONTROL, "a"))
                .sendKeys(Keys.BACK_SPACE)
                .sendKeys(String.valueOf(amount))
                .perform();
        totalPrice.click();
        waitForLoad();
        return this;
    }

    public CartItemPage increaseQuantity(int times) {
        changeQuantity(quantitySingleAdd, times);
        return this;
    }

    public CartItemPage decreaseQuantity(int times) {
        changeQuantity(quantitySingleRemove, times);
        return this;
    }

    private void changeQuantity(WebElement clickTarget, int times) {
        IntStream.range(0, times).forEach(i -> clickTarget.click());
        totalPrice.click();
        waitForLoad();
    }

    public void remove() {
        removeItem.click();
        waitForLoad();
    }
}
