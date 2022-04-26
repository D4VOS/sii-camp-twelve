package tests.base;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasePage;

public abstract class Pages extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(Pages.class);

    public <T extends BasePage> T at(Class<T> pageType) {
        logger.debug("Initializing " + pageType.getSimpleName() + " object..");
        return PageFactory.initElements(driver, pageType);
    }
}

