package pages.mystore.order;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.MyStoreBasePage;

public abstract class OrderPage extends MyStoreBasePage {

    @FindBy(css = "#js-checkout-summary")
    private WebElement summaryMenu;

    public OrderPage(WebDriver driver) {
        super(driver);
    }
    
}
