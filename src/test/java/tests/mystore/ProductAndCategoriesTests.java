package tests.mystore;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePagePage;
import pages.mystore.products.PricesDropPagePage;
import pages.mystore.products.ProductTilePage;
import tests.mystore.base.Pages;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAndCategoriesTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(ProductAndCategoriesTests.class);

    @Test
    public void priceDrop_shouldBeVisibleInFlags_whenProductIsOnSalePage() {
        // Arrange
        int discountPercentage = 20;
        String discountValue = "-" + discountPercentage + "%";

        // Act
        at(HomePagePage.class).inFooter()
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
