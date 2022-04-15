package tests.mystore;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePagePage;
import pages.mystore.search.SearchResultsPagePage;
import tests.mystore.base.Pages;
import tests.mystore.example.ExampleTests;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(ExampleTests.class);

    @Test
    public void itemFromHomePage_shouldBeVisibleOnResultPage_whenSearchedOut() {
        // Arrange
        String itemName = at(HomePagePage.class)
                .products()
                .getRandom()
                .getName();

        // Act
        at(HomePagePage.class).inHeader()
                .searchFor(itemName)
                .pressSearchSubmit();

        List<String> sut = at(SearchResultsPagePage.class)
                .products()
                .getNames();

        // Assert
        logger.info("List: " + sut + ", item name: " + itemName);
        assertThat(sut).contains(itemName);
    }

    @Test
    public void itemFromHomePage_shouldBeVisibleOnDropdownList_whenSearchedOut() {
        // Arrange
        String itemName = at(HomePagePage.class)
                .products()
                .getRandom()
                .getName();

        // Act
        at(HomePagePage.class)
                .inHeader()
                .searchFor(itemName);

        List<String> sut = at(SearchResultsPagePage.class)
                .inHeader()
                .getSearchHintTexts();

        // Assert
        logger.debug("List: " + sut + ", item name: " + itemName);
        assertThat(sut).anyMatch(i -> i.contains(itemName));
    }
}
