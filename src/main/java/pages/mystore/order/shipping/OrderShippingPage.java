package pages.mystore.order.shipping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.order.OrderPage;
import pages.mystore.order.OrderPaymentPage;

public class OrderShippingPage extends OrderPage {

    @FindBy(css = ".delivery-options")
    private WebElement shippingMethods;

    @FindBy(css = "[name=confirmDeliveryOption]")
    private WebElement continueButton;

    public OrderShippingPage(WebDriver driver) {
        super(driver);
    }

    public ShippingMethodListPage shippingMethods() {
        return new ShippingMethodListPage(driver, shippingMethods);
    }

    public OrderPaymentPage goToPayment() {
        continueButton.click();
        return new OrderPaymentPage(driver);
    }
}
