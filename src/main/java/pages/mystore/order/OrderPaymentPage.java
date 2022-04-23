package pages.mystore.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.mystore.base.WidgetsPage;
import pages.mystore.order.confirmation.OrderConfirmationPage;

public class OrderPaymentPage extends OrderPage {
    private final WidgetsPage widgetsPage;
    @FindBy(css = "#payment-option-2")
    private WebElement payByBankWireOption;

    @FindBy(css = "#conditions_to_approve\\[terms-and-conditions\\]")
    private WebElement tosCheckbox;

    @FindBy(css = "#payment-confirmation button")
    private WebElement placeOrderButton;

    @FindBy(css = "#cta-terms-and-conditions-0")
    private WebElement termsOfUse;

    public OrderPaymentPage(WebDriver driver) {
        super(driver);
        widgetsPage = new WidgetsPage(driver);
    }

    public OrderPaymentPage payByBankWire() {
        if (!payByBankWireOption.isSelected()) {
            payByBankWireOption.click();
        }
        return this;
    }

    public OrderPaymentPage acceptTOS() {
        if (!tosCheckbox.isSelected()) {
            tosCheckbox.click();
        }
        return this;
    }

    public OrderConfirmationPage placeOrder() {
        placeOrderButton.click();
        return new OrderConfirmationPage(driver);
    }

    public String getTermsOfUse() {
        termsOfUse.click();
        WebElement tos = widgetsPage.getModal();
        String tosText = tos.getText();
        tos.findElement(By.className("close")).click();
        return tosText;
    }
}
