package tests.automationpractice;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.automationpractice.home.HomePage;
import pages.automationpractice.products.PricesDropPage;
import pages.automationpractice.products.ProductTilePage;
import tests.automationpractice.base.Pages;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ProductAndCategoriesTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(ProductAndCategoriesTests.class);

    @Test
    public void priceDrop_shouldBeVisibleInFlags_whenProductIsOnSalePage() {
        // Arrange
        String discountValue = "-20%";

        // Act
        at(HomePage.class).inFooter()
                .clickOnPageLink("Prices drop");

        List<ProductTilePage> sut = at(PricesDropPage.class)
                .products()
                .getAll();

        // Assert
        assertThat(sut).allMatch(product -> product.isDiscountedBy(discountValue));
    }

}
