package pages.mystore.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class ProductListPage extends BasePage {

    @FindBy(css = ".product")
    private List<WebElement> products;

    public ProductListPage(WebDriver driver, WebElement element) {
        super(driver, element);
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
                .map(product -> new ProductTilePage(driver, product))
                .collect(Collectors.toList());
    }
}
