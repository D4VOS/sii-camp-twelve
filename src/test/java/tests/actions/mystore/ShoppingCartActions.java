package tests.actions.mystore;

import models.shop.CartItem;
import models.shop.ShoppingCart;
import pages.mystore.home.HomePage;
import pages.mystore.product.ProductViewPage;
import pages.mystore.search.SearchResultsPage;

import java.util.Random;
import java.util.stream.IntStream;

public class ShoppingCartActions extends MyStoreActions {
    public ShoppingCart createShoppingCart(int itemCount, int maxAtOnce) {
        ShoppingCart shoppingCart = new ShoppingCart();

        IntStream.range(0, 1).forEach(n -> {
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

        at(HomePage.class).inHeader()
                .typeInSearchBar("CUSTOMIZED").submitSearch();

        ProductViewPage productViewPage = at(SearchResultsPage.class).products().getRandom().view();


        productViewPage = productViewPage.customizeIfPossible().setAmount(1);
        shoppingCart.add(new CartItem(productViewPage), 1);
        
        productViewPage.addToCart().continueShopping();
        return shoppingCart;
    }
}
