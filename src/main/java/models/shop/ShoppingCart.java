package models.shop;

import exceptions.NotFoundItemInCartException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.mystore.basket.ProductInfoQueryable;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.data.DataUtils.round;

public class ShoppingCart extends HashMap<CartItem, Integer> {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingCart.class);

    public void add(CartItem toAdd, int amount) {
        put(toAdd, getOrDefault(toAdd, 0) + amount);
    }

    public void remove(CartItem key, int amount) {
        if (!containsKey(key)) {
            throw new NotFoundItemInCartException("Item " + key + " is not present in shop cart.");

        }
        int currentAmount = get(key);
        if (amount >= currentAmount) {
            remove(key);
        }
        put(key, currentAmount - amount);
    }

    public void update(CartItem key, int newAmount) {
        if (!containsKey(key)) {
            throw new NotFoundItemInCartException("Item " + key + " is not present in shop cart.");

        }
        put(key, newAmount);
    }

    public float getTotalPrice() {
        return round((float) entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum(), 2);
    }

    public int getTotalAmount() {
        return values().stream()
                .mapToInt(i -> i)
                .sum();
    }

    @Override
    public String toString() {
        return "Different products: " + size() +
                "\nTotal price: " + getTotalPrice() +
                "\nTotal amount: " + getTotalAmount() +
                "\nItems:\n" +
                entrySet().stream()
                        .map(e -> "\t- " + e.getKey().toString() + " x" + e.getValue() + "\n")
                        .collect(Collectors.joining());
    }

    public boolean isEquivalent(List<? extends ProductInfoQueryable> products) {
        logger.info("Current shopping cart state:\n" + this);
        return products.stream().allMatch(p -> {
            CartItem item = new CartItem(p);
            try {
                int itemQuantity = p.getQuantity();
                logger.info(itemQuantity + "x Item: " + item);

                boolean contains = containsKey(item);
                int cartQuantity = get(item);
                logger.info("\tisInCart: " + contains + "\tcartQuantity: " + cartQuantity);
                return contains && itemQuantity == cartQuantity;
            } catch (NullPointerException e) {
                logger.warn("Item " + item + " is not present in shopping cart.");
                return false;
            }
        });
    }
}
