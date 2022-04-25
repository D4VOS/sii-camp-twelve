package pages.mystore.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.account.orders.AccountOrdersPage;
import pages.mystore.base.MyStoreBasePage;

public class AccountPage extends MyStoreBasePage {

    @FindBy(css = "#history-link")
    private WebElement orderHistoryAndDetails;

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    public AccountOrdersPage goToOrders() {
        orderHistoryAndDetails.click();
        return new AccountOrdersPage(driver);
    }
}
