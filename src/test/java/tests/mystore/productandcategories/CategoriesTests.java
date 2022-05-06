package tests.mystore.productandcategories;

import models.shop.MenuOption;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import steps.CategorySteps;
import tests.base.Pages;

import java.util.List;

public class CategoriesTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(CategoriesTests.class);

    @Test
    public void category_shouldBeCorrectlyDisplayed_whenOpened() {
        CategorySteps testSteps = new CategorySteps(driver);

        // Arrange
        List<MenuOption> categories = at(HomePage.class)
                .inHeader()
                .getCategories();

        List<MenuOption> subcategories = at(HomePage.class)
                .inHeader()
                .getSubCategories();

        categories.forEach(category -> testSteps
                .goToCategory(category.getTitle())
                .checkIfOnCorrectPage(category.getTitle())
        );

        subcategories.forEach(subcategory -> testSteps
                .goToCategory(subcategory.getTitle())
                .checkIfOnCorrectPage(subcategory.getTitle())
        );
    }
}
