package pages.automationpractice.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.automationpractice.base.AutomationPracticePageBase;

public final class PricesDropPage extends AutomationPracticePageBase {

    @FindBy(css = "#js-product-list")
    private WebElement productListGrid;

    public PricesDropPage(WebDriver driver) {
        super(driver);
    }

    public ProductListPage products() {
        return new ProductListPage(driver, productListGrid);
    }
}
