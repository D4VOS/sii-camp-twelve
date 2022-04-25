package models.shop;

import exceptions.NotFoundItemInCartException;
import pages.mystore.basket.ProductInfoQueryable;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static helpers.data.DataUtils.round;

public class ShoppingCart extends HashMap<CartItem, Integer> {

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

    public void update(CartItem key, int amount) {
        if (!containsKey(key)) {
            throw new NotFoundItemInCartException("Item " + key + " is not present in shop cart.");

        }
        put(key, amount);
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
        return products.stream().allMatch(p -> {
            CartItem item = new CartItem(p);
            return containsKey(item) && p.getQuantity() == get(item);
        });
    }
}
