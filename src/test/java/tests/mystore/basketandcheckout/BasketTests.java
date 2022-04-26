package tests.mystore.basketandcheckout;

import models.shop.CartItem;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.basket.BasketPage;
import pages.mystore.basket.CartItemListPage;
import pages.mystore.basket.CartItemPage;
import pages.mystore.home.HomePage;
import tests.actions.mystore.ShoppingCartActions;
import tests.mystore.productandcategories.PricesDropTests;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static pages.mystore.base.MyStoreBasePage.shoppingCart;

public class BasketTests extends ShoppingCartActions {
    private static final Logger logger = LoggerFactory.getLogger(PricesDropTests.class);

    @Test
    public void basket_shouldWorksCorrectly_whenModifyingItems() {
        // Arrange
        shoppingCart = createShoppingCart(5, 5);

        // Act
        BasketPage basket = at(HomePage.class)
                .inHeader()
                .goToBasket();

        // Assert
        assertThat(shoppingCart.isEquivalent(basket.products().getAll())).isTrue();
        assertCartTotalPrice(basket);

        basket.products().getFirst().setQuantity(5);
        assertCartTotalPrice(basket);

        Arrays.stream(new int[]{2, -2}).forEach(amountDiff -> {
            // Arrange
            CartItemPage firstItem = basket.products().getFirst();
            int currentQuantity = firstItem.getQuantity();
            logger.info("Item: " + new CartItem(firstItem));
            // Act
            firstItem.changeQuantity(amountDiff);

            // Assert
            int actualQuantity = basket.products().getFirst().getQuantity();
            int expectedQuantity = currentQuantity + amountDiff;
            logger.info("Expected count: " + (currentQuantity + amountDiff) + " actual count: " + actualQuantity);
            assertThat(actualQuantity).isEqualTo(expectedQuantity);
            assertCartTotalPrice(basket);
        });

        CartItemListPage productsList = basket.products();
        for (int i = 0; i < productsList.getAll().size(); i++) {
            productsList.getFirst().remove();
            assertCartTotalPrice(basket);
        }
    }

    public static void assertCartTotalPrice(BasketPage basketPage) {
        try {
            logger.info("Price on page cart: " + basketPage.summary().getTotalItemPrice() + " price in shopping cart: " + shoppingCart.getTotalPrice());
            assertThat(basketPage.summary().getTotalItemPrice()).isEqualTo(shoppingCart.getTotalPrice());
        } catch (AssertionFailedError e) {
            logger.error("Assertion failed");
            logger.info("Shopping cart state:\n" + shoppingCart);
            throw e;
        }
    }
}
