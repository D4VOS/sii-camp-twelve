package models.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import models.entities.User;

@Data
@NoArgsConstructor
public class Environment {
    private App app;
    private User user;
}
