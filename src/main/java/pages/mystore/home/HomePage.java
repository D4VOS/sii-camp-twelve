package pages.mystore.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.product.grid.ProductListPage;

public class HomePage extends MyStoreBasePage {

    @FindBy(css = "section.featured-products")
    private WebElement productListGrid;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage products() {
        return new ProductListPage(driver, productListGrid);
    }
}
