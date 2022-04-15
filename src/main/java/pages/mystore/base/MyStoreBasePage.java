package pages.mystore.base;

import org.openqa.selenium.WebDriver;
import pages.BasePage;

public abstract class MyStoreBasePage extends BasePage {

    public MyStoreBasePage(WebDriver driver) {
        super(driver);
    }

    public FooterPage inFooter() {
        return new FooterPage(driver);
    }

    public HeaderPage inHeader() {
        return new HeaderPage(driver);
    }
}