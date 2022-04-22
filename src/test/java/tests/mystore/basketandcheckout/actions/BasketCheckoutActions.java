package tests.mystore.basketandcheckout.actions;

import models.shop.CartItem;
import models.shop.ShoppingCart;
import pages.mystore.home.HomePage;
import pages.mystore.product.ProductViewPage;
import tests.mystore.actions.MyStoreActions;

import java.util.Random;
import java.util.stream.IntStream;

public class BasketCheckoutActions extends MyStoreActions {
    public ShoppingCart createShoppingCart(int itemCount, int maxAtOnce) {
        ShoppingCart shoppingCart = new ShoppingCart();

        IntStream.range(0, itemCount).forEach(n -> {
            // Arrange
            int amount = new Random().nextInt(maxAtOnce) + 1;
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
