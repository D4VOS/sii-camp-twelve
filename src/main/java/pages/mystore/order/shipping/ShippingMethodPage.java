package pages.mystore.order.shipping;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class ShippingMethodPage extends BasePage {

    @FindBy(css = ".carrier-name")
    private WebElement carrierName;

    @FindBy(css = ".custom-radio input")
    private WebElement selectedRadio;

    public ShippingMethodPage(WebDriver driver, WebElement element) {
        super(driver, element);
    }

    public ShippingMethodPage select() {
        if (!selectedRadio.isSelected()) {
            selectedRadio.click();
        }
        return this;
    }

    public String getCarrierName() {
        return carrierName.getText();
    }
}
