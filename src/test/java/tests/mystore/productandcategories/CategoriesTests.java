package tests.mystore.productandcategories;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.CategoryPage;
import pages.mystore.base.HeaderPage;
import pages.mystore.home.HomePage;
import tests.base.Pages;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesTests.class);

    @Test
    public void category_shouldBeCorrectlyDisplayed_whenOpened() {
        // Arrange
        Map<String, List<String>> categorySets = at(HomePage.class)
                .inHeader()
                .getCategorySets();

        categorySets.forEach((category, subcategories) -> {
            // Act & Assert
            menuItemPageValidate(category);
            subcategories.forEach(this::menuItemPageValidate);
        });
    }

    private void menuItemPageValidate(String categoryName) {
        logger.info("Validating " + categoryName + " page..");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToCategory(categoryName);

        String title = at(CategoryPage.class).getTitle();
        int productCount = at(CategoryPage.class).products().getAll().size();
        String productCountLabel = at(CategoryPage.class).products().getCountLabel();

        // Assert
        logger.debug("Title: " + title + ", product count: " + productCount + ", product count label: " + productCountLabel);
        assertThat(title).containsIgnoringCase(categoryName);
        assertThat(productCountLabel).contains("of " + productCount + " item(s)");
    }
}
