package pages.mystore.account.orders.details;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class OrderDetailsProductListPage extends BasePage {
    @FindBy(css = "tbody tr")
    private List<WebElement> productList;

    public OrderDetailsProductListPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public List<OrderDetailsProductRowPage> getAll() {
        return productList.stream()
                .map(p -> new OrderDetailsProductRowPage(driver, p))
                .collect(Collectors.toList());
    }
}
