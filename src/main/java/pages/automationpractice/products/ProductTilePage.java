package pages.automationpractice.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.DefaultElementLocatorFactory;
import pages.PageBase;

import java.util.Objects;

public final class ProductTilePage extends PageBase {

    @FindBy(css = ".product-title a")
    private WebElement name;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".price")
    private WebElement price;

    @FindBy(css = ".discount-percentage")
    private WebElement discountPercentage;

    @FindBy(css = ".variant-links .color")
    private WebElement colors;

    @FindBy(css = ".quick-view")
    private WebElement quickView;

    @FindBy(css = ".product-flags .discount")
    private WebElement discountFlag;

    public ProductTilePage(WebDriver driver, WebElement element) {
        super(driver);
        PageFactory.initElements(new DefaultElementLocatorFactory(element), this);
    }

    public String getName() {
        return name.getText();
    }

    public boolean isDiscountedBy(String value) {
        return isVisible(discountFlag) && Objects.equals(discountFlag.getText(), value);
    }


}
