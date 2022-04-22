package pages.mystore.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.mystore.home.FooterPage;
import pages.mystore.home.HeaderPage;

public abstract class MyStoreBasePage extends BasePage {

    @FindBy(css = "#header")
    private WebElement header;

    @FindBy(css = "#footer")
    private WebElement footer;

    public MyStoreBasePage(WebDriver driver) {
        super(driver);
    }


    public FooterPage inFooter() {
        return new FooterPage(driver, footer);
    }

    public HeaderPage inHeader() {
        return new HeaderPage(driver, header);
    }
}
