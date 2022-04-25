package pages.mystore.order.confirmation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ConfirmationDetailsPage extends BasePage {

    @FindBy(css = "li:nth-child(1)")
    private WebElement orderReference;

    @FindBy(css = "li:nth-child(2)")
    private WebElement paymentMethod;

    @FindBy(css = "li:nth-child(3)")
    private WebElement shippingMethod;

    public ConfirmationDetailsPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public String getOrderReference() {
        return orderReference.getText().replaceAll(".*: ", "");
    }

    public String getPaymentMethod() {
        return paymentMethod.getText().replaceAll(".*: ", "");
    }

    public String getShippingMethod() {
        return getSingleNodeText(shippingMethod).replaceAll(".*: ", "");
    }
}
