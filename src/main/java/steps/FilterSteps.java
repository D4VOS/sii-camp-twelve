package steps;

import org.apache.commons.lang3.tuple.Pair;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import pages.mystore.product.CategoryPage;
import pages.mystore.product.grid.ProductTilePage;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FilterSteps extends CategoryPage {
    private static final Logger logger = LoggerFactory.getLogger(FilterSteps.class);

    public FilterSteps(WebDriver driver) {
        super(driver);
    }

    public FilterSteps goToCategory(String category) {
        new HomePage(driver)
                .inHeader()
                .selectCategory(category);
        return this;
    }

    public void checkEachPriceFilterPair(List<Pair<Float, Float>> filterPairs) {
        filterPairs.forEach(filters -> {
            float min = filters.getLeft();
            float max = filters.getRight();

            // Act
            inFilters()
                    .setMinimumPrice(min)
                    .setMaximumPrice(max);

            List<ProductTilePage> sut = products()
                    .getAll();

            // Assert
            logger.info("Product count: " + sut.size() + ", MIN: " + min + " MAX: " + max);
            assertThat(sut).isNotEmpty();
            assertThat(sut).allMatch(p -> p.getCurrentPrice() <= max && p.getCurrentPrice() >= min);

            inFilters().clearAll();
        });
    }
}
