package tests.mystore.productandcategories;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.PricesDropSteps;
import tests.base.Pages;

public class PricesDropTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(PricesDropTests.class);

    @Test
    public void priceDrop_shouldBeVisibleInFlags_whenProductIsOnSalePage() {
        // Arrange
        PricesDropSteps testSteps = new PricesDropSteps(driver);
        int discountPercentage = 20;

        // Act
        testSteps
                .goToPricesDropPage()
                .checkIfEveryProductIsDiscountedBy(discountPercentage)
                .viewRandomProduct()
                .checkIfHasDiscountLabelWithValue(discountPercentage);
    }
}
