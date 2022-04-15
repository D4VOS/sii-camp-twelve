package pages.mystore.base;

import exceptions.NotFoundMatchingOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class HeaderPage extends BasePage {

    private final WidgetsPage widgetsPage;
    @FindBy(css = "#search_widget .ui-autocomplete-input")
    private WebElement searchInput;

    @FindBy(css = "#search_widget button")
    private WebElement searchSubmitButton;

    @FindBy(css = "#top-menu > .category")
    private List<WebElement> categories;

    @FindBy(css = ".category > a")
    private List<WebElement> subcategories;

    public HeaderPage(WebDriver driver) {
        super(driver);
        widgetsPage = new WidgetsPage(driver);
    }

    public HeaderPage searchFor(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        return this;
    }

    public void submitSearch() {
        searchSubmitButton.click();
    }

    public List<String> getSearchHintTexts() {
        wait.until(ExpectedConditions.visibilityOf(widgetsPage.getSearchDropdown()));
        return widgetsPage.getSearchHints().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }


    public Map<String, List<String>> getCategorySets() {
        return categories.stream()
                .collect(Collectors.toMap(WebElement::getText, this::getSubcategoryTitles));
    }

    private List<String> getSubcategoryTitles(WebElement category) {
        List<WebElement> subcategories = category.findElements(By.className("category"));
        hoverOnElement(category);
        return getItemTitles(subcategories);
    }

    private List<String> getItemTitles(List<WebElement> elements) {
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public WebElement getMenuItem(String title) {
        try {
            return filterByText(categories, title);
        } catch (NotFoundMatchingOption ignored) {
        }

        for (WebElement category : categories) {
            hoverOnElement(category);
            List<WebElement> subcategories = category.findElements(By.className("category"));
            try {
                return filterByText(subcategories, title);
            } catch (NotFoundMatchingOption ignored) {
            }
        }
        throw new NotFoundMatchingOption("Option with text: " + title + " not exist in menu");
    }

    private WebElement filterByText(List<WebElement> elements, String text) throws NotFoundMatchingOption {
        return elements.stream()
                .filter(i -> i.getText().equalsIgnoreCase(text))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOption("Option with text: " + text + " not exist in menu"));
    }

    public void goToCategory(String title) {
        getMenuItem(title).click();
    }
}
