package pages.mystore.account.orders;

import exceptions.NotFoundMatchingOptionException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;

import java.util.List;
import java.util.stream.Collectors;

public class AccountOrdersPage extends MyStoreBasePage {

    @FindBy(css = "tbody tr")
    private List<WebElement> orderRows;

    public AccountOrdersPage(WebDriver driver) {
        super(driver);
    }

    public AccountOrderRowPage getByOrderId(String orderId) {
        return getAll().stream()
                .filter(o -> o.getOrderReference().equalsIgnoreCase(orderId))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Order with ID: " + orderId + " is not present in account orders"));
    }

    public List<AccountOrderRowPage> getAll() {
        return orderRows.stream()
                .map(o -> new AccountOrderRowPage(driver, o))
                .collect(Collectors.toList());
    }
}
