package pages.automationpractice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageBase;

public class AutomationPracticePageBase extends PageBase {
    @FindBy(css = "#search_widget .ui-autocomplete-input")
    private WebElement searchInput;

    @FindBy(css = "#search_widget button")
    private WebElement searchSubmitButton;

    public AutomationPracticePageBase(WebDriver driver) {
        super(driver);
    }

    public AutomationPracticePageBase searchFor(String text) {
        searchInput.clear();
        searchInput.sendKeys(text);
        return this;
    }

    public AutomationPracticePageBase pressSearchSubmit() {
        searchSubmitButton.clear();
        return this;
    }
}
