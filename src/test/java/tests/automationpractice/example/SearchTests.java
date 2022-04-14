package tests.automationpractice.example;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.automationpractice.home.HomePage;
import pages.automationpractice.search.SearchResultsPage;
import tests.automationpractice.base.Pages;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(ExampleTests.class);

    @Test
    public void itemFromHomePage_shouldBeVisibleOnResultPage_whenSearchedOut() {
        // Arrange
        String itemName = at(HomePage.class)
                .getProducts()
                .getRandom()
                .getName();

        // Act
        at(HomePage.class)
                .searchFor(itemName)
                .pressSearchSubmit();
        List<String> sut = at(SearchResultsPage.class)
                .getProducts()
                .getNames();

        // Assert
        assertThat(sut).contains(itemName);
    }

    @Test
    public void itemFromHomePage_shouldBeVisibleOnDropdownList_whenSearchedOut() {
        // Arrange
        String itemName = at(HomePage.class)
                .getProducts()
                .getRandom()
                .getName();

        // Act
        at(HomePage.class)
                .searchFor(itemName);

        List<String> sut = at(SearchResultsPage.class)
                .getSearchHints();

        // Assert
        logger.debug("List: " + sut + ", item name: " + itemName);
        assertThat(sut).anyMatch(i -> i.contains(itemName));
    }
}
