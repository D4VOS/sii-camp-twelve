package exceptions;

import org.openqa.selenium.NotFoundException;

public class NotFoundItemInCartException extends NotFoundException {
    public NotFoundItemInCartException(String message) {
        super(message);
    }
}
