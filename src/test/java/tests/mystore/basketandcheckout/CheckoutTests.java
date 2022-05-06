package tests.mystore.basketandcheckout;

import factory.user.UserFactory;
import models.entities.User;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.AuthSteps;
import steps.CheckoutSteps;
import tests.actions.mystore.ShoppingCartActions;

import static pages.mystore.base.MyStoreBasePage.shoppingCart;

public class CheckoutTests extends ShoppingCartActions {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutTests.class);

    @Test
    public void checkoutProcess_shouldBeSuccessful_whenFilling() {

        // Arrange
        User user = new UserFactory().getRandomUser();
        new AuthSteps(driver).registerUser(user);
        shoppingCart = createShoppingCart(5, 3);
        CheckoutSteps testSteps = new CheckoutSteps(
                driver,
                "Bank transfer",
                "Awaiting bank wire payment",
                "TesterSii"
        );
        // Act
        testSteps
                .goToBasketSummary()
                .fillAddressWithUserData(user)
                .goToShipping()
                .selectShippingByName()
                .goToPaymentSection()
                .selectBankWireOption()
                .checkAndAcceptTOS()
                .submitOrder()
                .checkOrderConfirmationDetails()
                .goToOrders()
                .checkCurrentOrderRowInfo()
                .goToOrderDetails()
                .checkIfDetailsContainsCorrectItems()
                .checkAddressesForUser(user);
    }
}
