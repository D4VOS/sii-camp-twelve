package pages.mystore.account.orders.details;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;

public class AccountOrderDetailsPage extends MyStoreBasePage {

    @FindBy(css = "#delivery-address address")
    private WebElement deliveryAddress;

    @FindBy(css = "#invoice-address address")
    private WebElement invoiceAddress;

    @FindBy(css = "#order-products")
    private WebElement productGrid;

    public AccountOrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String[] getDeliveryAddressRows() {
        return deliveryAddress.getText().split("\n");
    }

    public String[] getInvoiceAddressRows() {
        return invoiceAddress.getText().split("\n");
    }

    public OrderDetailsProductListPage products() {
        return new OrderDetailsProductListPage(driver, productGrid);
    }
}
