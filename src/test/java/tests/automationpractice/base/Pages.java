package tests.automationpractice.base;

import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.PageBase;

public class Pages extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(Pages.class.getName());

    public <T extends PageBase> T at(Class<T> pageType) {
        logger.info("Initializing " + pageType.getSimpleName() + " object..");
        return PageFactory.initElements(driver, pageType);
    }
}

