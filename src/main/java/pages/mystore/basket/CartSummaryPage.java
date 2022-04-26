package pages.mystore.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.mystore.order.OrderAddressPage;

import static helpers.data.DataUtils.parsePrice;

public class CartSummaryPage extends BasePage {

    @FindBy(css = "#cart-subtotal-products .value")
    private WebElement totalItemPrice;

    @FindBy(css = "#cart-subtotal-shipping > .value")
    private WebElement shippingPrice;

    @FindBy(css = ".checkout .btn")
    private WebElement proceedToCheckoutButton;

    public CartSummaryPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public float getTotalItemPrice() {
        return parsePrice(totalItemPrice.getText());
    }

    public OrderAddressPage checkout() {
        proceedToCheckoutButton.click();
        return new OrderAddressPage(driver);
    }
}
