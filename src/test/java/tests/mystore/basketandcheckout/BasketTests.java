package tests.mystore.basketandcheckout;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steps.BasketSteps;
import tests.actions.mystore.ShoppingCartActions;
import tests.mystore.productandcategories.PricesDropTests;

import java.util.Arrays;

import static pages.mystore.base.MyStoreBasePage.shoppingCart;

public class BasketTests extends ShoppingCartActions {
    private static final Logger logger = LoggerFactory.getLogger(PricesDropTests.class);

    @Test
    public void basket_shouldWorksCorrectly_whenModifyingItems() {
        // Arrange
        shoppingCart = createShoppingCart(5, 5);
        BasketSteps testSteps = new BasketSteps(driver);
        // Act
        testSteps
                .goToBasket()
                .checkIfBasketContainsCorrectProducts()
                .checkIfTotalPriceIsCorrect()
                .setFirstItemQuantityTo(5)
                .checkIfTotalPriceIsCorrect()
                .checkIfTotalPriceIsCorrect();

        Arrays.stream(new int[]{2, -2}).forEach(testSteps::changeFirstItemQuantityAndCheckValue);

        testSteps.removeEachAndCheckTotalPrice();
    }
}
