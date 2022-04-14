package models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private String socialTitle;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String birthDate;
}
