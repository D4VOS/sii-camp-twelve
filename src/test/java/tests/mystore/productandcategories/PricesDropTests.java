package tests.mystore.productandcategories;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import pages.mystore.products.PricesDropPagePage;
import pages.mystore.products.ProductTilePage;
import tests.base.Pages;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PricesDropTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(PricesDropTests.class);

    @Test
    public void priceDrop_shouldBeVisibleInFlags_whenProductIsOnSalePage() {
        // Arrange
        int discountPercentage = 20;
        String discountValue = "-" + discountPercentage + "%";

        // Act
        at(HomePage.class).inFooter()
                .clickOnPageLink("Prices drop")
                .waitForLoad();

        List<ProductTilePage> sut = at(PricesDropPagePage.class)
                .products()
                .getAll();

        // Assert
        assertThat(sut).allMatch(product -> product.isDiscountedBy(discountValue));
        assertThat(sut).allMatch(product -> {
            float currentPrice = product.getCurrentPrice();
            float regularPrice = product.getRegularPrice();
            float expectedPrice = regularPrice * (100 - discountPercentage) / 100;

            logger.info("Expected: " + expectedPrice + ", current: " + currentPrice);
            return expectedPrice == currentPrice;
        });
        ProductTilePage randomProduct = at(PricesDropPagePage.class).products().getRandom();
        assertThat(randomProduct.quickView().isDiscountedBy(discountPercentage)).isTrue();
    }
}
