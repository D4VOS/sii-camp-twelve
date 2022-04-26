package pages.mystore.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.product.grid.ProductListPage;

public class SearchResultsPage extends MyStoreBasePage {
    @FindBy(css = "#js-product-list")
    private WebElement productList;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage products() {
        return new ProductListPage(driver, productList);
    }
}
