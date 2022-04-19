package exceptions;

import org.openqa.selenium.NotFoundException;

public class NotFoundMatchingOptionException extends NotFoundException {
    public NotFoundMatchingOptionException(String message) {
        super(message);
    }
}
