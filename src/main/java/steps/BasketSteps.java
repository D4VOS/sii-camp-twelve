package steps;

import models.shop.CartItem;
import org.openqa.selenium.WebDriver;
import org.opentest4j.AssertionFailedError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.basket.BasketPage;
import pages.mystore.basket.CartItemListPage;
import pages.mystore.basket.CartItemPage;
import pages.mystore.home.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class BasketSteps extends BasketPage {
    private static final Logger logger = LoggerFactory.getLogger(BasketSteps.class);

    public BasketSteps(WebDriver driver) {
        super(driver);
    }

    public BasketSteps goToBasket() {
        new HomePage(driver)
                .inHeader()
                .goToBasket();
        return this;
    }

    public BasketSteps checkIfBasketContainsCorrectProducts() {
        assertThat(shoppingCart.isEquivalent(products().getAll())).isTrue();
        return this;
    }

    public BasketSteps checkIfTotalPriceIsCorrect() {
        assertCartTotalPrice(this);
        return this;
    }

    public BasketSteps setFirstItemQuantityTo(int amount) {
        products().getFirst().setQuantity(amount);
        return this;
    }

    public void changeFirstItemQuantityAndCheckValue(int amountDiff) {
        // Arrange
        CartItemPage firstItem = products().getFirst();
        int currentQuantity = firstItem.getQuantity();
        logger.info("Item: " + new CartItem(firstItem));

        firstItem.changeQuantity(amountDiff);

        int actualQuantity = products().getFirst().getQuantity();
        int expectedQuantity = currentQuantity + amountDiff;
        logger.info("Expected count: " + (currentQuantity + amountDiff) + " actual count: " + actualQuantity);
        assertThat(actualQuantity).isEqualTo(expectedQuantity);
        assertCartTotalPrice(this);
    }

    public void removeEachAndCheckTotalPrice() {
        CartItemListPage productsList = products();
        for (int i = 0; i < productsList.getAll().size(); i++) {
            productsList.getFirst().remove();
            assertCartTotalPrice(this);
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
