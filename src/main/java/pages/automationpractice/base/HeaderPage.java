package pages.automationpractice.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.PageBase;

import java.util.List;
import java.util.stream.Collectors;

public final class HeaderPage extends PageBase {

    @FindBy(css = "#search_widget .ui-autocomplete-input")
    private WebElement searchInput;

    @FindBy(css = "#search_widget button")
    private WebElement searchSubmitButton;


    @FindBy(css = ".ui-widget")
    private WebElement dropdown;

    @FindBy(css = ".ui-widget .ui-menu-item")
    private List<WebElement> hints;

    public HeaderPage(WebDriver driver) {
        super(driver);
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
        wait.until(ExpectedConditions.visibilityOf(dropdown));
        return hints.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}
