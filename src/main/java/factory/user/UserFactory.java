package factory.user;

import com.github.javafaker.Faker;
import models.config.User;

public class UserFactory {
    private static Faker faker = new Faker();
    private UserBuilder builder;

    public UserFactory() {
        builder = new UserBuilder();
    }

    public User getRandomUser() {
        return builder.title(faker.name().prefix())
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .birthDate(faker.date().birthday().toString())
                .build();
    }

    public User getAlreadyRegisteredUser() {
        return builder.title(System.getProperty("user.socialTitle"))
                .firstName(System.getProperty("user.firstName"))
                .lastName(System.getProperty("user.lastName"))
                .email(System.getProperty("user.email"))
                .password(System.getProperty("user.password"))
                .birthDate(System.getProperty("user.birthDate"))
                .build();
    }
}
