package pages.automationpractice.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.automationpractice.AutomationPracticePageBase;
import pages.automationpractice.products.ProductListPage;

public class HomePage extends AutomationPracticePageBase {

    @FindBy(css = "section.featured-products")
    private WebElement productListGrid;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage getProducts() {
        return new ProductListPage(driver, productListGrid);
    }
}
