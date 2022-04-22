package tests.mystore.basketandcheckout;

import factory.user.UserFactory;
import models.config.User;
import models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tests.mystore.basketandcheckout.actions.BasketCheckoutActions;

public class CheckoutTests extends BasketCheckoutActions {
    private static final Logger logger = LoggerFactory.getLogger(CheckoutTests.class);

    @Test
    public void checkoutProcess_shouldBeSuccessful_whenFilling() {
        // Arrange
        User user = new UserFactory().getRandomUser();
        registerUser(user);

        ShoppingCart shoppingCart = createShoppingCart(5, 3);

        // Act

        // Assert

    }
}
