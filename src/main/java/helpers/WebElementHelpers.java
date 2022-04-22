package helpers;

import exceptions.NotFoundMatchingOptionException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class WebElementHelpers {

    public static void selectMultipleOptionsAtOnceByText(WebDriver driver, List<WebElement> options, List<String> optionToSelect) {
        Actions multipleSelect = new Actions(driver);

        multipleSelect.keyDown(Keys.CONTROL);

        options.stream()
                .filter(o -> optionToSelect.stream().anyMatch(o.getText()::equalsIgnoreCase))
                .forEach(multipleSelect::click);

        multipleSelect.keyUp(Keys.CONTROL)
                .perform();
    }

    public static void selectMultipleOptionsByText(List<WebElement> options, List<String> optionToSelect) {
        options.stream()
                .filter(o -> optionToSelect.stream().anyMatch(o.getText()::equalsIgnoreCase))
                .forEach(WebElement::click);
    }

    public static <T> WebElement getOptionByValue(List<WebElement> options, T value) {
        return options.stream()
                .filter(o -> o.getAttribute("value").compareToIgnoreCase(String.valueOf(value)) == 0)
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Not found option with value: " + value));
    }

    public static <T> WebElement getOptionByText(List<WebElement> options, T text) {
        return options.stream()
                .filter(o -> {
                    System.out.printf("|" + o.getText() + "|");
                    return o.getText().equalsIgnoreCase(String.valueOf(text));
                })
                .findFirst()
                .orElseThrow(() -> new NotFoundMatchingOptionException("Not found option with text: " + text));
    }

    public static boolean isVisible(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (NoSuchElementException err) {
            return false;
        }
    }
}
