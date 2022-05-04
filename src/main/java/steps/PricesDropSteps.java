package steps;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import pages.mystore.product.grid.PricesDropPage;
import pages.mystore.product.grid.ProductTilePage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PricesDropSteps extends HomePage {
    private static final Logger logger = LoggerFactory.getLogger(PricesDropSteps.class);

    public PricesDropSteps(WebDriver driver) {
        super(driver);
    }

    public PricesDropSteps goToPricesDropPage() {
        inFooter()
                .clickOnPageLink("Prices drop")
                .waitForLoad();
        return this;
    }

    public PricesDropSteps checkIfEveryProductIsDiscountedBy(int discount) {
        List<ProductTilePage> products = new PricesDropPage(driver)
                .products()
                .getAll();
        assertThat(products).allMatch(product -> product.isDiscountedBy("-" + discount + "%"));
        assertThat(products).allMatch(product -> {
            float currentPrice = product.getCurrentPrice();
            float regularPrice = product.getRegularPrice();
            float expectedPrice = regularPrice * (100 - discount) / 100;

            logger.info("Expected: " + expectedPrice + ", current: " + currentPrice);
            return expectedPrice == currentPrice;
        });
        return this;
    }

    public ProductSteps viewRandomProduct() {
        new PricesDropPage(driver).products().getRandom().view();
        return new ProductSteps(driver);
    }
}
