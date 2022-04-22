package models.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pages.mystore.basket.CartItemPage;
import pages.mystore.product.ProductViewPage;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class CartItem {
    String name;
    Float price;

    String customizeText;

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }

    public CartItem(ProductViewPage productView) {
        name = productView.getName();
        price = productView.getPrice();
        customizeText = productView.getCustomizedText();
    }

    public CartItem(CartItemPage item) {
        name = item.getName();
        price = item.getPrice();
        customizeText = item.getCustomizedText();
    }
}
