package models.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Address {
    private String firstLine;
    private String city;
    private String state;
    private String zipPostalCode;
    private String country;
}
