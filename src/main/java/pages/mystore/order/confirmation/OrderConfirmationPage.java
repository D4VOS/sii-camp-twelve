package pages.mystore.order.confirmation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;

public class OrderConfirmationPage extends MyStoreBasePage {

    @FindBy(css = "#order-items")
    private WebElement orderItems;

    @FindBy(css = "#order-details")
    private WebElement orderDetails;

    public OrderConfirmationPage(WebDriver driver) {
        super(driver);
    }

    public ConfirmationProductListPage products() {
        return new ConfirmationProductListPage(driver, orderItems);
    }

    public ConfirmationDetailsPage details() {
        return new ConfirmationDetailsPage(driver, orderDetails);
    }
}
