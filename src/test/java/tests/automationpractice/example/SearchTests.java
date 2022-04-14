package tests.automationpractice.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.automationpractice.products.ProductListPage;
import tests.automationpractice.base.Pages;

public class SearchTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(ExampleTests.class);

    @Test
    public void something_shouldSomething_whenSomethingHappened() {
        // Arrange
        String itemName = at(ProductListPage.class).getRandomProductName();

        // Act
        at(ProductListPage.class).searchFor(itemName);

        // Assert
        return;
    }
}
