package factory.address;

import models.entities.Address;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddressBuilder {
    private static final Logger logger = LoggerFactory.getLogger(AddressBuilder.class);
    private Address address;

    protected AddressBuilder() {
        address = new Address();
    }

    public static AddressBuilder start() {
        return new AddressBuilder();
    }

    public Address build() {
        return address;
    }

    public AddressBuilder firstLine(String firstLine) {
        address.setFirstLine(firstLine);
        return this;
    }

    public AddressBuilder city(String city) {
        address.setCity(city);
        return this;
    }

    public AddressBuilder state(String state) {
        address.setState(state);
        return this;
    }

    public AddressBuilder zipPostalCode(String zipPostalCode) {
        address.setZipPostalCode(zipPostalCode);
        return this;
    }

    public AddressBuilder country(String country) {
        address.setCountry(country);
        return this;
    }
}
