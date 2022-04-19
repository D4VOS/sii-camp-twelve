package models.shop;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class MenuOption {
    String title;
    String id;
    Integer level;
    MenuOption parent;

    @Override
    public boolean equals(Object o) {
        return Objects.equals(id, o) || Objects.equals(title, o);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, id, level, parent);
    }
}


