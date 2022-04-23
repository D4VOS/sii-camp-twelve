package tests.mystore.basketandcheckout;

import factory.user.UserFactory;
import models.entities.User;
import models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import pages.mystore.order.OrderAddressPage;
import pages.mystore.order.OrderPaymentPage;
import pages.mystore.order.confirmation.OrderConfirmationPage;
import pages.mystore.order.shipping.OrderShippingPage;
import tests.mystore.basketandcheckout.actions.BasketAndCheckoutActions;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckoutTests extends BasketAndCheckoutActions {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutTests.class);

    @Test
    public void checkoutProcess_shouldBeSuccessful_whenFilling() {
        // Arrange
        User user = new UserFactory().getRandomUser();
        registerUser(user);

        ShoppingCart shoppingCart = createShoppingCart(5, 3);

        // Act
        OrderAddressPage addressPage = at(HomePage.class).inHeader()
                .goToBasket()
                .summary()
                .checkout();

        addressPage.fillFirstName(user.getFirstName())
                .fillLastName(user.getLastName())
                .fillAddress(user.getAddress().getFirstLine())
                .fillCity(user.getAddress().getCity())
                .selectState(user.getAddress().getState())
                .fillZipCode(user.getAddress().getZipPostalCode())
                .selectCountry(user.getAddress().getCountry());

        OrderShippingPage shippingPage = addressPage.goToShipping();

        shippingPage.shippingMethods()
                .selectByName("TesterSii");

        OrderPaymentPage paymentPage = shippingPage.goToPayment()
                .payByBankWire()
                .acceptTOS();

        String tosText = paymentPage.getTermsOfUse();
        assertThat(tosText).isNotBlank();

        OrderConfirmationPage confirmationPage = paymentPage.placeOrder();

        // Assert
        return;
    }
}
