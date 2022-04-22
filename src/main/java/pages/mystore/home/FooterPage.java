package pages.mystore.home;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import java.util.List;

import static helpers.WebElementHelpers.getOptionByText;

public class FooterPage extends BasePage {

    @FindBy(css = "#footer li a")
    private List<WebElement> subPageLinks;

    public FooterPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public FooterPage clickOnPageLink(String name) {
        getOptionByText(subPageLinks, name).click();
        return this;
    }
}
