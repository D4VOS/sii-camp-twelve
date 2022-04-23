package factory.address;

import com.github.javafaker.Faker;
import models.entities.Address;

import java.util.Locale;

public class AddressFactory {
    private static Faker faker = new Faker(new Locale("pl-PL"));

    private static String[] allowedState = {"Alaska", "Texas", "Utah", "AA", "AE", "AP"};
    private static String[] allowedCountries = {"Poland"};
    private AddressBuilder builder;

    public AddressFactory() {
        builder = new AddressBuilder();
    }

    public Address getRandomAddress() {
        return builder.firstLine(faker.address().streetAddress())
                .city(faker.address().city())
                .state(allowedState[faker.number().numberBetween(0, allowedState.length - 1)])
                .zipPostalCode(faker.address().zipCode())
                .country(allowedCountries[faker.number().numberBetween(0, allowedCountries.length - 1)])
                .build();
    }

    public Address getDefinedAddress() {
        return builder.firstLine(System.getProperty("address.firstLine"))
                .city(System.getProperty("address.city"))
                .state(System.getProperty("address.state"))
                .zipPostalCode(System.getProperty("address.zipPostalCode"))
                .country(System.getProperty("address.country"))
                .build();
    }
}
