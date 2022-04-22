package pages.mystore.product;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;
import pages.mystore.product.grid.FilterPage;
import pages.mystore.product.grid.ProductListPage;

import static helpers.WebElementHelpers.isVisible;

public class CategoryPage extends MyStoreBasePage {

    @FindBy(css = "#js-product-list-header h1")
    private WebElement categoryTitle;

    @FindBy(css = "#search_filters")
    private WebElement filtersMenu;

    @FindBy(css = "#js-product-list")
    private WebElement productListGrid;

    @FindBy(css = "#search_filters")
    private WebElement searchFilters;

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

    public FilterPage inFilters() {
        return new FilterPage(driver, searchFilters);
    }
}
