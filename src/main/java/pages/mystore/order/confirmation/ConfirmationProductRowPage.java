package pages.mystore.order.confirmation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.mystore.base.WidgetsPage;
import pages.mystore.basket.ProductInfoQueryable;

import static helpers.data.DataUtils.parsePrice;
import static helpers.web.WebElementHelpers.isVisible;

public class ConfirmationProductRowPage extends BasePage implements ProductInfoQueryable {

    private WidgetsPage widgets;

    @FindBy(css = ".details")
    private WebElement details;

    @FindBy(css = ".qty .row div:nth-child(1)")
    private WebElement unitPrice;

    @FindBy(css = ".qty .row div:nth-child(2)")
    private WebElement quantity;

    @FindBy(css = ".qty .row div:nth-child(3)")
    private WebElement totalPrice;


    @FindBy(css = ".customizations a")
    private WebElement customizationLabel;

    public ConfirmationProductRowPage(WebDriver driver, WebElement element) {
        super(driver, element);
        widgets = new WidgetsPage(driver);
    }

    public int getQuantity() {
        return Integer.parseInt(quantity.getText());
    }

    public float getTotalPrice() {
        return parsePrice(totalPrice.getText());
    }

    public String getName() {
        return details.getText().replaceAll(" - .*", "");
    }

    public float getPrice() {
        return parsePrice(unitPrice.getText());
    }

    @Override
    public String getCustomizedText() {
        if (isVisible(customizationLabel)) {
            customizationLabel.click();
            return widgets.getModal().findElement(By.className("value")).getText();
        }
        return null;
    }
}
