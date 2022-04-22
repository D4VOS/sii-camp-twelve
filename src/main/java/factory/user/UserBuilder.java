package factory.user;

import models.config.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import validators.FormatDateValidator;

public class UserBuilder {
    private static final Logger logger = LoggerFactory.getLogger(UserBuilder.class);
    private User user;
    FormatDateValidator dateValidator = new FormatDateValidator("MM/dd/yyyy");

    UserBuilder() {
        user = new User();
    }

    public FormatDateValidator getDateValidator() {
        return dateValidator;
    }

    public static UserBuilder start() {
        return new UserBuilder();
    }

    public User build() {
        return user;
    }

    public UserBuilder title(String title) {
        user.setSocialTitle(title);
        return this;
    }

    public UserBuilder firstName(String firstName) {
        user.setFirstName(firstName);
        return this;
    }

    public UserBuilder lastName(String lastName) {
        user.setLastName(lastName);
        return this;
    }

    public UserBuilder email(String email) {
        user.setEmail(email);
        return this;
    }

    public UserBuilder password(String password) {
        user.setPassword(password);
        return this;
    }

    public UserBuilder birthDate(String birthDate) {
        if (dateValidator.isValid(birthDate)) {
            user.setBirthDate(birthDate);
        } else {
            logger.warn(birthDate + " is not valid data in (" + dateValidator.getDateFormat() + ") format.");
        }
        return this;
    }
}
