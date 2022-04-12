package tests.automationpractice.base;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.PageBase;

import java.lang.reflect.InvocationTargetException;

public class Pages extends TestBase {
    private static final Logger logger = LoggerFactory.getLogger(Pages.class.getName());

    public <T extends PageBase> T at(Class<T> pageType) {
        try {
            logger.info("Trying to create Page of " + pageType.getName() + " type");
            return pageType.getDeclaredConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            logger.error(e.getLocalizedMessage());
        }
        return null;
    }
}

