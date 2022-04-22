package tests.mystore.productandcategories;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import pages.mystore.product.CategoryPage;
import pages.mystore.product.grid.ProductTilePage;
import tests.base.Pages;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FiltersTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(FiltersTests.class);

    @Test
    public void properProduct_shouldBeVisible_whenFiltered() {
        // Arrange
        List<Pair<Float, Float>> filterPairs = List.of(
                Pair.of(9.00F, 29.00F),
                Pair.of(15.00F, 29.00F),
                Pair.of(9.00F, 25.00F)
        );
        at(HomePage.class).inHeader().selectCategory("Art");

        filterPairs.forEach(filters -> {
            float min = filters.getLeft();
            float max = filters.getRight();

            // Act
            at(CategoryPage.class).inFilters()
                    .setMinimumPrice(min)
                    .setMaximumPrice(max);

            List<ProductTilePage> sut = at(CategoryPage.class)
                    .products()
                    .getAll();

            // Assert
            logger.info("Product count: " + sut.size() + ", MIN: " + min + " MAX: " + max);
            assertThat(sut).isNotEmpty();
            assertThat(sut).allMatch(p -> p.getCurrentPrice() <= max && p.getCurrentPrice() >= min);

            at(CategoryPage.class).inFilters().clearAll();
        });
    }
}
