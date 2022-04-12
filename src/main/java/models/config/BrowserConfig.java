package models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BrowserConfig {
    private String name;
    private WebElement webElement;
    private boolean attachScreenshot;
    private boolean headless;
}
