package pages.mystore.products;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

import java.util.Objects;

public class ProductQuickViewPage extends BasePage {

    private static final Logger logger = LoggerFactory.getLogger(ProductQuickViewPage.class);
    @FindBy(css = ".discount")
    private WebElement discount;

    @FindBy(css = ".regular-price")
    private WebElement regularPrice;

    @FindBy(css = ".price")
    private WebElement currentPrice;

    public ProductQuickViewPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public boolean isDiscountedBy(int value) {
        String discountText = "SAVE " + value + "%";
        logger.info("Visibility: " + isVisible(discount) +
                ", Discount: " + discount.getText() +
                ", Expected: " + value);
        return isVisible(discount) && Objects.equals(discount.getText(), discountText);
    }
}
