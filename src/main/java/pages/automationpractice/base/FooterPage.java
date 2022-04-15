package pages.automationpractice.base;

import exceptions.NotFoundMatchingOption;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PageBase;

import java.util.List;
import java.util.Objects;

public final class FooterPage extends PageBase {

    @FindBy(css = "#footer li a")
    private List<WebElement> subPageLinks;

    public FooterPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnPageLink(String name) {
        subPageLinks.stream().filter(l -> Objects.equals(l.getText(), name))
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOption(name + " option not found."))
                .click();
    }
}
