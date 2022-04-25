package pages.mystore.order.confirmation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

import static helpers.data.DataUtils.parsePrice;

public class ConfirmationProductListPage extends BasePage {

    @FindBy(css = ".order-line")
    private List<WebElement> orderProductItems;

    @FindBy(xpath = "//tbody/tr[1]/td[2]")
    private WebElement subTotalPrice;

    @FindBy(css = ".total-value td:nth-child(2)")
    private WebElement totalPrice;

    public ConfirmationProductListPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public List<ConfirmationProductRowPage> getAll() {
        return orderProductItems.stream()
                .map(i -> new ConfirmationProductRowPage(driver, i))
                .collect(Collectors.toList());
    }

    public float getTotalPrice() {
        return parsePrice(totalPrice.getText());
    }

    public float getSubTotalPrice() {
        return parsePrice(subTotalPrice.getText());
    }
}
