package models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WebElement {
    private Integer polling;
    private Integer timeOut;
}
