package steps;

import org.openqa.selenium.WebDriver;
import pages.mystore.product.ProductViewPage;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductSteps extends ProductViewPage {
    public ProductSteps(WebDriver driver) {
        super(driver);
    }

    public ProductSteps checkIfHasDiscountLabelWithValue(int discount) {
        assertThat(isDiscountedBy(discount)).isTrue();
        return this;
    }
}
