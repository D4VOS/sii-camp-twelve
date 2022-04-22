package pages.mystore.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.product.grid.ProductListPage;

public class SearchResultsPagePage extends MyStoreBasePage {

    @FindBy(css = ".page-not-found")
    private WebElement notFoundContent;

    @FindBy(css = ".page-not-found input[type=text]")
    private WebElement searchAgainInput;

    @FindBy(css = ".page-not-found button")
    private WebElement searchAgainSubmitButton;

    @FindBy(css = "#js-product-list")
    private WebElement productList;

    public SearchResultsPagePage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage products() {
        return new ProductListPage(driver, productList);
    }
}
