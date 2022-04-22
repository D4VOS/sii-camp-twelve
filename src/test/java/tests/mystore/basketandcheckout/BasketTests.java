package tests.mystore.basketandcheckout;

import models.shop.CartItem;
import models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.basket.BasketPage;
import pages.mystore.basket.CartItemPage;
import pages.mystore.home.HomePage;
import pages.mystore.product.ProductViewPage;
import tests.base.Pages;
import tests.mystore.productandcategories.PricesDropTests;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(PricesDropTests.class);

    @Test
    public void basket_shouldWorksCorrectly_whenModifyingItems() {
        // Arrange
        ShoppingCart shoppingCart = createShoppingCart(5);

        // Act
        BasketPage basket = at(HomePage.class)
                .inHeader()
                .goToBasket();

        List<CartItemPage> products = basket.products().getAll();

        // Assert
        assertThat(products).allMatch(item -> {
            CartItem cartItem = new CartItem(item);
            int amount = shoppingCart.get(cartItem);
            boolean contains = shoppingCart.containsKey(cartItem);

            return contains && amount == item.getQuantity();
        });
        assertCartTotalPrice(basket, shoppingCart);

        CartItemPage firstItem = basket.products().getFirst();
        CartItem cartItem = new CartItem(firstItem);

        firstItem.setQuantity(5);
        shoppingCart.update(cartItem, 5);
        assertCartTotalPrice(basket, shoppingCart);

        firstItem = basket.products().getFirst();
        int currentQuantity = firstItem.getQuantity();
        firstItem.increaseQuantity(2);
        shoppingCart.add(cartItem, 2);
        assertThat(basket.products().getFirst().getQuantity()).isEqualTo(currentQuantity + 2);
        assertCartTotalPrice(basket, shoppingCart);

        firstItem = basket.products().getFirst();
        currentQuantity = firstItem.getQuantity();
        firstItem.decreaseQuantity(2);
        shoppingCart.remove(cartItem, 2);
        assertThat(basket.products().getFirst().getQuantity()).isEqualTo(currentQuantity - 2);
        assertCartTotalPrice(basket, shoppingCart);

        int itemCount = basket.products().getAll().size();

        for (int i = 0; i < itemCount; i++) {
            firstItem = basket.products().getFirst();
            cartItem = new CartItem(firstItem);
            firstItem.remove();
            shoppingCart.remove(cartItem);
            assertCartTotalPrice(basket, shoppingCart);
        }
    }

    public void assertCartTotalPrice(BasketPage basketPage, ShoppingCart shoppingCart) {
        assertThat(basketPage.summary().getTotalItemPrice()).isEqualTo(shoppingCart.getTotalPrice());
    }

    public ShoppingCart createShoppingCart(int itemCount) {
        ShoppingCart shoppingCart = new ShoppingCart();

        IntStream.range(0, itemCount).forEach(n -> {
            // Arrange
            int amount = new Random().nextInt(5) + 1;
            // Act
            ProductViewPage productView = at(HomePage.class).inHeader()
                    .selectRandomCategory()
                    .products()
                    .getRandom()
                    .view()
                    .customizeIfPossible()
                    .setAmount(amount);

            CartItem item = new CartItem(productView);
            shoppingCart.add(item, amount);

            productView.addToCart()
                    .continueShopping();
        });

        return shoppingCart;
    }
}
