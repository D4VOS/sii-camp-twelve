package models.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataBase {
    private String user;
    private String password;
    private String host;
    private Integer port;
}
