package pages.mystore.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;
import java.util.stream.Collectors;

public final class HeaderPage extends BasePage {

    private final WidgetsPage widgetsPage;
    @FindBy(css = "#search_widget .ui-autocomplete-input")
    private WebElement searchInput;

    @FindBy(css = "#search_widget button")
    private WebElement searchSubmitButton;

    public HeaderPage(WebDriver driver) {
        super(driver);
        widgetsPage = new WidgetsPage(driver);
    }

    public HeaderPage searchFor(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        return this;
    }

    public void pressSearchSubmit() {
        searchSubmitButton.click();
    }

    public List<String> getSearchHintTexts() {
        wait.until(ExpectedConditions.visibilityOf(widgetsPage.getSearchDropdown()));
        return widgetsPage.getSearchHints().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
