package pages.mystore.basket;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;

public class BasketPage extends MyStoreBasePage {

    @FindBy(css = ".cart-overview")
    private WebElement cartItems;

    @FindBy(css = ".cart-summary")
    private WebElement summary;

    public BasketPage(WebDriver driver) {
        super(driver);
    }

    public CartItemListPage products() {
        return new CartItemListPage(driver, cartItems);
    }

    public CartSummaryPage summary() {
        return new CartSummaryPage(driver, summary);
    }

}
