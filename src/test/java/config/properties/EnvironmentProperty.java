package config.properties;

import helpers.file.DataSerializer;
import helpers.file.FormatMapper;
import models.config.ConfigFile;
import models.config.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static helpers.data.ObjectFormatter.flattenObject;


public class EnvironmentProperty {
    private static final Logger logger = LoggerFactory.getLogger(EnvironmentProperty.class);
    private static final String configFileName = "config";
    private static EnvironmentProperty instance = null;
    private final String appEnvName;
    private final BrowserEnvironment browserEnv;
    private final Environment environmentConfig;

    private EnvironmentProperty() {
        ConfigFile configFile = loadYamlConfig();
        appEnvName = configFile.getActiveEnvironment();
        logger.info("### Environment name: " + appEnvName);

        browserEnv = new BrowserEnvironment(configFile.getBrowser());
        environmentConfig = loadActiveEnvironmentConfig(configFile);
        logger.info("**** Running environment config:\n" + flattenObject(environmentConfig, "environment"));
        logger.info("**** Running browser config:\n" + flattenObject(browserEnv.getConfig(), "browser"));

        publishPropertiesToSystemStore();
    }

    public synchronized static EnvironmentProperty getInstance() {
        if (instance == null) {
            instance = new EnvironmentProperty();
        }
        return instance;
    }

    private Environment loadActiveEnvironmentConfig(ConfigFile configFile) {
        Environment environment = configFile.getEnvironments().get(appEnvName);
        try {
            assert environment != null;
            logger.info("Loaded " + appEnvName + " environment config.");
        } catch (AssertionError e) {
            logger.error("Environment " + appEnvName + " doesn't exist in config file. Please provide existing one.");
            assert false;
        }
        return environment;
    }

    private ConfigFile loadYamlConfig() {
        FormatMapper mapper = FormatMapper.YAML;
        String configFileFullName = configFileName + mapper.getExtension();
        DataSerializer dataSerializer = new DataSerializer(mapper); // used dataSerializer from previous lesson

        ConfigFile configFile = dataSerializer.deserializeFile(configFileFullName, ConfigFile.class);
        assert configFile != null;
        logger.info("Loaded config file: " + configFileFullName);

        return configFile;
    }

    public BrowserEnvironment getBrowserEnv() {
        return browserEnv;
    }

    public Environment getConfig() {
        return environmentConfig;
    }

    private void publishPropertiesToSystemStore() {
        List<String> properties = Stream.of(
                        flattenObject(browserEnv.getConfig(), "browser").split("\n"),
                        flattenObject(environmentConfig, "environment").split("\n")
                ).flatMap(Stream::of)
                .collect(Collectors.toList());
        logger.info("### Started publishing " + properties.size() + " properties.");

        properties.forEach(kvp -> {
            String key = getKey(kvp);
            String value = getValue(kvp);
            logger.debug("Trying to save " + key + " property with " + value + " value.");

            if (System.getProperty(key) == null) {
                System.setProperty(key, value);
                logger.info("\t+" + key + ": " + value);
            } else {
                logger.warn(key + " property is present in System store with value: " + System.getProperty(key));
            }
        });
    }

    private String getKey(String keyValuePair) {
        return keyValuePair.split("=")[0];
    }

    private String getValue(String keyValuePair) {
        return keyValuePair.replace("[", "")
                .replace("]", "")
                .split("=")[1];
    }
}
