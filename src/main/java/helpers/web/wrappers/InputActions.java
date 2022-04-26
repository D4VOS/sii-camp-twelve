package helpers.web.wrappers;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputActions {
    private static final Logger logger = LoggerFactory.getLogger(InputActions.class);

    public static void performSendKeys(WebElement field, String text) {
        logger.info("<<<<< Entering: '" + text + "' into the field at: " + field.getLocation());
        field.clear();
        field.sendKeys(text);
    }
}
