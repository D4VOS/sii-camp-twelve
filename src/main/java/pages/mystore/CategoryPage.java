package pages.mystore;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.mystore.products.ProductListPage;

public final class CategoryPage extends BasePage {

    @FindBy(css = "#js-product-list-header h1")
    private WebElement categoryTitle;

    @FindBy(css = "#search_filters")
    private WebElement filtersMenu;

    @FindBy(css = "#js-product-list")
    private WebElement productListGrid;

    public CategoryPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return categoryTitle.getText();
    }

    public boolean isFiltersVisible() {
        return isVisible(filtersMenu);
    }

    public ProductListPage products() {
        return new ProductListPage(driver, productListGrid);
    }
}
