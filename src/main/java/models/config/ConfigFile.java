package models.config;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
public class ConfigFile {
    private BrowserConfig browser;
    private String activeEnvironment;
    @JsonAnyGetter
    private HashMap<String, Environment> environments;
}
