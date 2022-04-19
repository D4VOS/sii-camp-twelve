package models.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class CartItem {
    String name;
    float price;

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}
