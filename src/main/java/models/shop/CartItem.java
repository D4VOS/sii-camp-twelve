package models.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pages.mystore.basket.ProductInfoQueryable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class CartItem {
    String name;
    Float price;

    String customizeText;

    @Override
    public String toString() {
        return name + " ($" + price + ")" + (customizeText != null ? " customized: " + customizeText : "");
    }

    public CartItem(ProductInfoQueryable productInfo) {
        name = productInfo.getName();
        price = productInfo.getPrice();
        customizeText = productInfo.getCustomizedText();
        if (customizeText == null) {
            return;
        }
    }
}
