package pages.mystore.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static helpers.data.DataUtils.parsePrice;

public class QuickCartSummaryPage extends BasePage {

    @FindBy(css = ".btn-secondary")
    private WebElement continueShopping;

    @FindBy(css = ".btn-primary")
    private WebElement proceedCheckout;

    @FindBy(css = ".product-name")
    private WebElement productName;

    @FindBy(css = ".product-price")
    private WebElement productPrice;

    @FindBy(css = ".product-quantity strong")
    private WebElement productQuantity;

    @FindBy(css = ".product-total .value")
    private WebElement cartTotalPrice;

    @FindBy(css = ".cart-products-count")
    private WebElement cartTotalCount;

    public QuickCartSummaryPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public CategoryPage continueShopping() {
        continueShopping.click();
        return new CategoryPage(driver);
    }

    public int getQuantity() {
        return Integer.parseInt(productQuantity.getText());
    }

    public float getProductPrice() {
        return parsePrice(productPrice.getText());
    }

    public float getTotalPrice() {
        return parsePrice(cartTotalCount.getText());
    }

    public String getProductName() {
        return productName.getText();
    }

    public int getCartTotalCount() {
        return Integer.parseInt(cartTotalCount.getText().replaceAll("[^0-9?!]", ""));
    }
}
