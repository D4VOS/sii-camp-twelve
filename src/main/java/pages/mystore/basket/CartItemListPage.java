package pages.mystore.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public class CartItemListPage extends BasePage {

    @FindBy(css = ".cart-item")
    private List<WebElement> cartItems;

    public CartItemListPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public List<CartItemPage> getAll() {
        return cartItems.stream()
                .map(i -> new CartItemPage(driver, i))
                .collect(Collectors.toList());
    }

    public CartItemPage getFirst() {
        return getAll().get(0);
    }

    public CartItemPage get(int index) {
        return getAll().get(index);
    }
}
