package steps;

import models.entities.User;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.account.orders.AccountOrderRowPage;
import pages.mystore.account.orders.AccountOrdersPage;
import pages.mystore.account.orders.details.AccountOrderDetailsPage;
import pages.mystore.account.orders.details.OrderDetailsProductRowPage;
import pages.mystore.order.OrderAddressPage;
import pages.mystore.order.OrderPaymentPage;
import pages.mystore.order.confirmation.OrderConfirmationPage;
import pages.mystore.order.shipping.OrderShippingPage;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutSteps extends OrderConfirmationPage {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutSteps.class);
    String orderId;
    final String paymentMethod;
    final String expectedPaymentStatus;
    final String shippingMethod;

    public CheckoutSteps(WebDriver driver, String paymentMethod, String expectedPaymentStatus, String shippingMethod) {
        super(driver);
        this.paymentMethod = paymentMethod;
        this.expectedPaymentStatus = expectedPaymentStatus;
        this.shippingMethod = shippingMethod;
    }

    public CheckoutSteps goToBasketSummary() {
        inHeader()
                .goToBasket()
                .summary()
                .checkout();
        return this;
    }

    public CheckoutSteps fillAddressWithUserData(User user) {
        new OrderAddressPage(driver).fillAddress(user);
        return this;
    }

    public CheckoutSteps goToShipping() {
        new OrderAddressPage(driver).goToShipping();
        return this;
    }

    public CheckoutSteps selectShippingByName() {
        new OrderShippingPage(driver)
                .shippingMethods()
                .selectByName(shippingMethod);
        return this;
    }

    public CheckoutSteps goToPaymentSection() {
        new OrderShippingPage(driver).goToPayment();
        return this;
    }

    public CheckoutSteps selectBankWireOption() {
        new OrderPaymentPage(driver).payByBankWire();
        return this;
    }

    public CheckoutSteps checkAndAcceptTOS() {
        String tosText = new OrderPaymentPage(driver)
                .acceptTOS()
                .getTermsOfUse();
        assertThat(tosText).isNotBlank();
        return this;
    }

    public CheckoutSteps submitOrder() {
        new OrderPaymentPage(driver).placeOrder();
        return this;
    }

    public CheckoutSteps checkOrderConfirmationDetails() {
        assertThat(shoppingCart.isEquivalent(products().getAll())).isTrue();
        assertThat(details().getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(details().getShippingMethod()).isEqualTo(shippingMethod);
        return this;
    }

    public CheckoutSteps goToOrders() {
        orderId = details()
                .getOrderReference();
        logger.info("Order reference: " + orderId);
        inHeader()
                .goToAccount()
                .goToOrders();
        return this;
    }

    public CheckoutSteps checkCurrentOrderRowInfo() {
        AccountOrderRowPage order = getCurrentOrder();
        assertThat(order.getDate()).isEqualTo(getCurrentDate("MM/dd/yyyy"));
        assertThat(order.getTotalPrice()).isEqualTo(shoppingCart.getTotalPrice());
        assertThat(order.getPaymentMethod()).isEqualTo(paymentMethod);
        assertThat(order.getPaymentStatus()).isEqualTo(expectedPaymentStatus);
        return this;
    }

    public CheckoutSteps goToOrderDetails() {
        getCurrentOrder().goToDetails();
        return this;
    }

    public CheckoutSteps checkIfDetailsContainsCorrectItems() {
        List<OrderDetailsProductRowPage> products = new AccountOrderDetailsPage(driver).products().getAll();
        assertThat(shoppingCart.isEquivalent(products)).isTrue();
        return this;
    }

    public void checkAddressesForUser(User user) {
        AccountOrderDetailsPage orderDetails = new AccountOrderDetailsPage(driver);
        String[] expectedAddressRows = {
                user.getFirstName() + " " + user.getLastName(),
                user.getAddress().getFirstLine(),
                user.getAddress().getZipPostalCode() + " " + user.getAddress().getCity(),
                user.getAddress().getCountry()
        };
        logger.info("Expected address rows: " + Arrays.toString(expectedAddressRows));
        assertThat(orderDetails.getDeliveryAddressRows()).containsExactly(expectedAddressRows);
        assertThat(orderDetails.getInvoiceAddressRows()).containsExactly(expectedAddressRows);
    }

    private AccountOrderRowPage getCurrentOrder() {
        return new AccountOrdersPage(driver).getByOrderId(orderId);
    }

    private String getCurrentDate(String format) {
        return new SimpleDateFormat(format).format(new Date());
    }
}
