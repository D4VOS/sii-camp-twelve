package steps;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import pages.mystore.product.CategoryPage;

import static org.assertj.core.api.Assertions.assertThat;

public class CategorySteps extends CategoryPage {
    private static final Logger logger = LoggerFactory.getLogger(CategorySteps.class);

    public CategorySteps(WebDriver driver) {
        super(driver);
    }

    public CategorySteps goToCategory(String category) {
        logger.info("Validating " + category + " page..");
        new HomePage(driver)
                .inHeader()
                .selectCategory(category);
        return this;
    }

    public void checkIfOnCorrectPage(String category) {
        String title = getTitle();
        int productCount = products().getAll().size();
        String productCountLabel = products().getCountLabel();

        // Assert
        logger.info("Title: " + title + ", product count: " + productCount + ", product count label: " + productCountLabel);
        assertThat(title).isEqualTo(category);
        assertThat(productCountLabel).contains("of " + productCount + " item(s)");
        assertThat(filtersVisibility()).isTrue();
    }
}
