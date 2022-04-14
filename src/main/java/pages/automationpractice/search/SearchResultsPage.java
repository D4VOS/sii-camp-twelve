package pages.automationpractice.search;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.automationpractice.AutomationPracticePageBase;
import pages.automationpractice.products.ProductListPage;
import pages.automationpractice.products.ProductTilePage;

import java.util.List;

public class SearchResultsPage extends AutomationPracticePageBase {

    @FindBy(css = ".page-not-found")
    private WebElement notFoundContent;

    @FindBy(css = ".page-not-found input[type=text]")
    private WebElement searchAgainInput;

    @FindBy(css = ".page-not-found button")
    private WebElement searchAgainSubmitButton;

    @FindBy(css = ".js-product-list")
    private WebElement productList;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public List<ProductTilePage> getProductList() {
        return new ProductListPage(driver, productList).getProducts();
    }

}
