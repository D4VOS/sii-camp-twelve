package tests.mystore.productandcategories;

import models.shop.MenuOption;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.CategoryPage;
import pages.mystore.base.HeaderPage;
import pages.mystore.home.HomePage;
import tests.base.Pages;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoriesTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesTests.class);

    @Test
    public void category_shouldBeCorrectlyDisplayed_whenOpened() {
        // Arrange
        List<MenuOption> categories = at(HomePage.class)
                .inHeader()
                .getCategories();

        List<MenuOption> subcategories = at(HomePage.class)
                .inHeader()
                .getSubCategories();

        categories.forEach(this::menuItemPageValidate);
        subcategories.forEach(this::menuItemPageValidate);
    }

    private void menuItemPageValidate(MenuOption category) {
        logger.info("Validating " + category.getTitle() + " page..");
        HeaderPage headerPage = new HeaderPage(driver);
        headerPage.goToCategory(category.getTitle());

        String title = at(CategoryPage.class).getTitle();
        int productCount = at(CategoryPage.class).products().getAll().size();
        String productCountLabel = at(CategoryPage.class).products().getCountLabel();

        // Assert
        logger.info("Title: " + title + ", product count: " + productCount + ", product count label: " + productCountLabel);
        assertThat(title).containsIgnoringCase(category.getTitle());
        assertThat(productCountLabel).contains("of " + productCount + " item(s)");
    }
}
