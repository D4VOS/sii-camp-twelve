package factory.user;

import com.github.javafaker.Faker;
import factory.address.AddressFactory;
import models.entities.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserFactory {
    private static Faker faker = new Faker();
    private static AddressFactory addressFactory = new AddressFactory();

    private static String[] allowedTitles = {"Mr.", "Mrs."};
    private UserBuilder builder;

    public UserFactory() {
        builder = new UserBuilder();
    }

    public User getRandomUser() {
        return builder.title(allowedTitles[faker.number().numberBetween(0, allowedTitles.length)])
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .birthDate(getDateAsString(faker.date().birthday(), builder.getDateValidator().getDateFormat()))
                .address(addressFactory.getRandomAddress())
                .build();
    }

    public User getAlreadyRegisteredUser() {
        return builder.title(System.getProperty("user.socialTitle"))
                .firstName(System.getProperty("user.firstName"))
                .lastName(System.getProperty("user.lastName"))
                .email(System.getProperty("user.email"))
                .password(System.getProperty("user.password"))
                .birthDate(System.getProperty("user.birthDate"))
                .address(addressFactory.getDefinedAddress())
                .build();
    }

    public String getDateAsString(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }
}
