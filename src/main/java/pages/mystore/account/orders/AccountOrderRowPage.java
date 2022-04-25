package pages.mystore.account.orders;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.mystore.account.orders.details.AccountOrderDetailsPage;

import static helpers.data.DataUtils.parsePrice;

public class AccountOrderRowPage extends BasePage {

    @FindBy(css = "th")
    private WebElement orderReference;

    @FindBy(xpath = "//td[1]")
    private WebElement date;

    @FindBy(xpath = "//td[2]")
    private WebElement totalPrice;

    @FindBy(xpath = "//td[3]")
    private WebElement paymentMethod;

    @FindBy(xpath = "//td[4]")
    private WebElement paymentStatus;

    @FindBy(css = "[data-link-action=view-order-details]")
    private WebElement orderDetails;

    public AccountOrderRowPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public AccountOrderDetailsPage goToDetails() {
        orderDetails.click();
        return new AccountOrderDetailsPage(driver);
    }

    public String getOrderReference() {
        return orderReference.getText();
    }

    public String getDate() {
        return date.getText();
    }

    public float getTotalPrice() {
        return parsePrice(totalPrice.getText());
    }

    public String getPaymentMethod() {
        return paymentMethod.getText();
    }

    public String getPaymentStatus() {
        return paymentStatus.getText();
    }
}
