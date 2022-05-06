package tests.mystore.productandcategories;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.FilterSteps;
import tests.base.Pages;

import java.util.List;

public class FiltersTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(FiltersTests.class);

    @Test
    public void properProduct_shouldBeVisible_whenFiltered() {
        FilterSteps testSteps = new FilterSteps(driver);
        // Arrange
        List<Pair<Float, Float>> filterPairs = List.of(
                Pair.of(9.00F, 29.00F),
                Pair.of(15.00F, 29.00F),
                Pair.of(9.00F, 25.00F)
        );

        testSteps
                .goToCategory("Art")
                .checkEachPriceFilterPair(filterPairs);
    }
}
