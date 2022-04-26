package pages.mystore.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

import java.util.List;

import static helpers.web.WebElementHelpers.isVisible;

// Those widgets can be handled by WebDriver::switchTo but for modularity and code clarity aspect chosen PO
public class WidgetsPage extends BasePage {
    @FindBy(css = ".modal.in")
    private WebElement modal;

    @FindBy(css = ".modal-header .close")
    private WebElement modalCloseButton;

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

    public void closeModal() {
        modalCloseButton.click();
    }

    public WebElement getSearchDropdown() {
        return getVisible(searchDropdown);
    }

    public List<WebElement> getSearchHints() {
        return getVisible(searchHints);
    }

    private WebElement getVisible(WebElement element) {
        wait.until(driver -> isVisible(element));
        return element;
    }

    private List<WebElement> getVisible(List<WebElement> elements) {
        wait.until(ExpectedConditions.visibilityOfAllElements(elements));
        return elements;
    }
}
