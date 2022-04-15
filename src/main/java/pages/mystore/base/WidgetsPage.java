package pages.mystore.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

public final class WidgetsPage extends BasePage {

    @FindBy(css = ".modal-content")
    private WebElement modal;

    @FindBy(css = ".ui-widget")
    private WebElement searchDropdown;

    @FindBy(css = ".ui-widget .ui-menu-item")
    private List<WebElement> searchHints;

    public WidgetsPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getModal() {
        return getVisible(modal);
    }

    public WebElement getSearchDropdown() {
        return getVisible(searchDropdown);
    }

    public List<WebElement> getSearchHints() {
        return getVisible(searchHints);
    }

    private WebElement getVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return element;
    }

    private List<WebElement> getVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }
}
