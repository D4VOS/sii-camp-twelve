package models.shop;

import exceptions.NotFoundItemInCartException;

import java.util.HashMap;
import java.util.stream.Collectors;

public class ShoppingCart extends HashMap<CartItem, Integer> {

    public Integer add(CartItem toAdd, int amount) {
        return put(toAdd, getOrDefault(toAdd, 0) + amount);
    }

    public Integer remove(CartItem toRemove) {
        if (containsKey(toRemove)) {
            return put(toRemove, get(toRemove) - 1);
        }
        throw new NotFoundItemInCartException("Item " + toRemove + " is not present in shop cart.");
    }


    public float getTotalPrice() {
        return (float) entrySet().stream()
                .mapToDouble(e -> e.getKey().getPrice() * e.getValue())
                .sum();
    }

    public int getTotalAmount() {
        return values().stream()
                .mapToInt(i -> i)
                .sum();
    }

    @Override
    public String toString() {
        return "Total amount: " + size() +
                "\nTotal price: " + getTotalPrice() +
                "\nItems:\n" +
                entrySet().stream()
                        .map(e -> "\t- " + e.getKey().toString() + " x" + e.getValue() + "\n")
                        .collect(Collectors.joining());
    }
}
