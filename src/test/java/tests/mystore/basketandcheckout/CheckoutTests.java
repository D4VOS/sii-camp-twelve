package tests.mystore.basketandcheckout;

import factory.user.UserFactory;
import models.entities.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.account.orders.AccountOrderRowPage;
import pages.mystore.account.orders.AccountOrdersPage;
import pages.mystore.account.orders.details.AccountOrderDetailsPage;
import pages.mystore.home.HomePage;
import pages.mystore.order.OrderAddressPage;
import pages.mystore.order.OrderPaymentPage;
import pages.mystore.order.confirmation.OrderConfirmationPage;
import pages.mystore.order.shipping.OrderShippingPage;
import tests.actions.mystore.ShoppingCartActions;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static pages.mystore.base.MyStoreBasePage.shoppingCart;

public class CheckoutTests extends ShoppingCartActions {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutTests.class);

    @Test
    public void checkoutProcess_shouldBeSuccessful_whenFilling() {
        // Arrange
        String expectedPaymentMethod = "Bank transfer";
        String expectedPaymentStatus = "Awaiting bank wire payment";
        String expectedShippingMethod = "TesterSii";
        User user = new UserFactory().getRandomUser();
        at(HomePage.class).inHeader()
                .goToLogin()
                .goToRegister()
                .registerUser(user);

        shoppingCart = createShoppingCart(5, 3);

        // Act
        OrderAddressPage addressPage = at(HomePage.class).inHeader()
                .goToBasket()
                .summary()
                .checkout()
                .fillAddress(user);

        OrderShippingPage shippingPage = addressPage.goToShipping();
        shippingPage.shippingMethods()
                .selectByName(expectedShippingMethod);

        OrderPaymentPage paymentPage = shippingPage.goToPayment()
                .payByBankWire()
                .acceptTOS();
        String tosText = paymentPage.getTermsOfUse();
        assertThat(tosText).isNotBlank();

        OrderConfirmationPage confirmationPage = paymentPage.placeOrder();
        assertThat(shoppingCart.isEquivalent(confirmationPage.products().getAll())).isTrue();
        assertThat(confirmationPage.details().getPaymentMethod()).isEqualTo(expectedPaymentMethod);
        assertThat(confirmationPage.details().getShippingMethod()).isEqualTo(expectedShippingMethod);
        
        String orderId = confirmationPage.details()
                .getOrderReference();
        logger.info("Order reference: " + orderId);

        AccountOrdersPage accountOrders = at(OrderConfirmationPage.class).inHeader()
                .goToAccount()
                .goToOrders();
        AccountOrderRowPage order = accountOrders.getByOrderId(orderId);
        assertThat(order.getDate()).isEqualTo(getCurrentDate("MM/dd/yyyy"));
        assertThat(order.getTotalPrice()).isEqualTo(shoppingCart.getTotalPrice());
        assertThat(order.getPaymentMethod()).isEqualTo(expectedPaymentMethod);
        assertThat(order.getPaymentStatus()).isEqualTo(expectedPaymentStatus);

        AccountOrderDetailsPage orderDetails = order.goToDetails();
        assertThat(shoppingCart.isEquivalent(orderDetails.products().getAll())).isTrue();
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
}
