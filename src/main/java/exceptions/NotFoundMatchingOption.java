package exceptions;

import org.openqa.selenium.NotFoundException;

public class NotFoundMatchingOption extends NotFoundException {
    public NotFoundMatchingOption(String message) {
        super(message);
    }
}
