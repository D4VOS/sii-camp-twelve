package pages.automationpractice.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.automationpractice.AutomationPracticePageBase;
import pages.automationpractice.products.ProductListPage;
import pages.automationpractice.products.ProductTilePage;

import java.util.List;

public class HomePage extends AutomationPracticePageBase {

    @FindBy(css = "section.featured-products")
    private WebElement productListGrid;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public List<ProductTilePage> getProducts() {
        return new ProductListPage(driver, productListGrid).getProducts();
    }
}
