package models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private Integer experience;
    private Integer age;
    private String gender;
    private String email;
    private List<String> professions;
    private List<String> commands;
    private String filePath;
    private String continent;
    private String additionalInfo;
}
