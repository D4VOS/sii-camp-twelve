package pages.automationpractice.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.automationpractice.AutomationPracticePageBase;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProductListPage extends AutomationPracticePageBase {

    @FindBy(css = "article")
    private List<WebElement> productList;

    public ProductListPage(WebDriver driver, WebElement element) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public List<String> getProductNames() {
        return getProducts().stream()
                .map(ProductTilePage::getName)
                .collect(Collectors.toList());
    }

    public String getRandomProductName() {
        int random = new Random().nextInt(productList.size());
        return getProducts().get(random).getName();
    }

    public List<ProductTilePage> getProducts() {
        return productList.stream()
                .map(p -> new ProductTilePage(driver, p))
                .collect(Collectors.toList());
    }
}
