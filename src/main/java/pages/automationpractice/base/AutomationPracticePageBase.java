package pages.automationpractice.base;

import org.openqa.selenium.WebDriver;
import pages.PageBase;

public abstract class AutomationPracticePageBase extends PageBase {
    public AutomationPracticePageBase(WebDriver driver) {
        super(driver);
    }

    public FooterPage inFooter() {
        return new FooterPage(driver);
    }

    public HeaderPage inHeader() {
        return new HeaderPage(driver);
    }
}
