package pages.mystore.account.orders.details;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;
import pages.mystore.base.WidgetsPage;
import pages.mystore.basket.ProductInfoQueryable;

import static helpers.data.DataUtils.parsePrice;
import static helpers.web.WebElementHelpers.isVisible;

public class OrderDetailsProductRowPage extends BasePage implements ProductInfoQueryable {

    private WidgetsPage widgets;
    @FindBy(css = "td:nth-child(1) > strong")
    public WebElement details;

    @FindBy(css = "td:nth-child(2)")
    public WebElement quantity;

    @FindBy(css = "td:nth-child(3)")
    public WebElement unitPrice;

    @FindBy(css = "td:nth-child(4)")
    public WebElement totalPrice;

    @FindBy(css = ".customization a")
    public WebElement customizationLabel;

    public OrderDetailsProductRowPage(WebDriver driver, WebElement element) {
        super(driver, element);
        widgets = new WidgetsPage(driver);
        highLight(element, "blue");
    }

    @Override
    public String getName() {
        return details.getText().replaceAll(" - .*", "");
    }

    @Override
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

    @Override
    public int getQuantity() {
        return Integer.parseInt(quantity.getText());
    }
}
