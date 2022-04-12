package models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebElement {
    private int polling;
    private int timeOut;
}
