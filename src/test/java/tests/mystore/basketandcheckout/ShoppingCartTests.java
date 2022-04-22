package tests.mystore.basketandcheckout;

import models.shop.CartItem;
import models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HomePage;
import pages.mystore.product.ProductViewPage;
import pages.mystore.product.QuickCartSummaryPage;
import tests.base.Pages;
import tests.mystore.productandcategories.PricesDropTests;

import java.util.Random;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTests extends Pages {
    private static final Logger logger = LoggerFactory.getLogger(PricesDropTests.class);

    @Test
    public void product_ShouldBeVisibleInShoppingCart_whenAdded() {
        ShoppingCart shoppingCart = new ShoppingCart();

        IntStream.range(0, 5).forEach(n -> {
            // Arrange
            int amount = new Random().nextInt(5) + 1;
            // Act
            ProductViewPage productView = at(HomePage.class)
                    .inHeader()
                    .selectRandomCategory()
                    .products()
                    .getRandom()
                    .view()
                    .customizeIfPossible()
                    .setAmount(amount);

            CartItem item = new CartItem(productView);
            shoppingCart.add(item, amount);

            QuickCartSummaryPage summary = productView.addToCart();

            // Assert
            logger.info("Shopping cart:\n" + shoppingCart +
                    "\nAdded product:\n" + item + " x" + amount);

            assertThat(item.getName()).isEqualTo(summary.getProductName());
            assertThat(item.getPrice()).isEqualTo(summary.getProductPrice());
            assertThat(shoppingCart.get(item)).isEqualTo(summary.getQuantity());
            assertThat(shoppingCart.getTotalAmount()).isEqualTo(summary.getCartTotalCount());

            summary.continueShopping();
        });
    }
}
