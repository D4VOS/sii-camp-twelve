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

    @FindBy(css = ".product")
    private List<WebElement> products;

    public ProductListPage(WebDriver driver, WebElement element) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public List<String> getNames() {
        return getAll().stream()
                .map(ProductTilePage::getName)
                .collect(Collectors.toList());
    }

    public ProductTilePage getRandom() {
        int random = new Random().nextInt(products.size());
        return getAll().get(random);
    }

    public List<ProductTilePage> getAll() {
        return products.stream()
                .map(p -> new ProductTilePage(driver, p))
                .collect(Collectors.toList());
    }
}
