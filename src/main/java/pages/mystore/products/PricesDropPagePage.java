package pages.mystore.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;

public final class PricesDropPagePage extends MyStoreBasePage {

    @FindBy(css = "#js-product-list")
    private WebElement productListGrid;

    public PricesDropPagePage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage products() {
        return new ProductListPage(driver, productListGrid);
    }
}
