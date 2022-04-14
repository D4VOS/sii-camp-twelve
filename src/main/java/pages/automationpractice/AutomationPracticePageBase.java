package pages.automationpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PageBase;

import java.util.List;
import java.util.stream.Collectors;

public class AutomationPracticePageBase extends PageBase {
    @FindBy(css = "#search_widget .ui-autocomplete-input")
    private WebElement searchInput;

    @FindBy(css = "#search_widget button")
    private WebElement searchSubmitButton;

    @FindBy(css = ".ui-menu-item")
    private List<WebElement> hints;

    @FindBy(css = ".ui-menu.ui-widget")
    private WebElement dropdownWidget;

    public AutomationPracticePageBase(WebDriver driver) {
        super(driver);
    }

    // Search bar methods
    public AutomationPracticePageBase searchFor(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        return this;
    }

    public AutomationPracticePageBase pressSearchSubmit() {
        searchSubmitButton.click();
        return this;
    }

    public List<String> getSearchHints() {
        wait.until(ExpectedConditions.visibilityOf(dropdownWidget));
        return hints.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    // End of search bar methods

}
