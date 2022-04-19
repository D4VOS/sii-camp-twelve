package tests.mystore.basketandcheckout;

import models.shop.CartItem;
import models.shop.ShoppingCart;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.home.HeaderPage;
import pages.mystore.product.ProductViewPage;
import pages.mystore.product.QuickCartSummaryPage;
import pages.mystore.product.grid.ProductTilePage;
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
            ProductTilePage product = at(HeaderPage.class)
                    .goToRandomCategory()
                    .products()
                    .getRandom();
            CartItem item = new CartItem(product.getName(), product.getCurrentPrice());

            ProductViewPage productView = product.view();
            if (productView.isCustomizable()) productView.customizeItem("TEST");

            QuickCartSummaryPage summary = productView.setAmount(amount)
                    .addToCart();

            shoppingCart.add(item, amount);

            // Assert
            logger.info("Shopping cart:\n" + shoppingCart +
                    "\nSelected product:\n" + item + " x" + amount);

            assertThat(item.getName()).isEqualTo(summary.getProductName());
            assertThat(item.getPrice()).isEqualTo(summary.getProductPrice());
            assertThat(shoppingCart.get(item)).isEqualTo(summary.getQuantity());
            assertThat(shoppingCart.getTotalAmount()).isEqualTo(summary.getCartTotalCount());

            summary.continueShopping();
        });


    }
}
