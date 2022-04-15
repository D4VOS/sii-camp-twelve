package pages.mystore.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.products.ProductListPage;

public final class HomePagePage extends MyStoreBasePage {

    @FindBy(css = "section.featured-products")
    private WebElement productListGrid;

    public HomePagePage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage products() {
        return new ProductListPage(driver, productListGrid);
    }
}
