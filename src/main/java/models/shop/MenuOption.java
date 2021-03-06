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
    public int hashCode() {
        return Objects.hash(id);
    }
}


