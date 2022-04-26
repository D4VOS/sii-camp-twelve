package helpers.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

public class ObjectFormatter {
    private static final Logger logger = LoggerFactory.getLogger(ObjectFormatter.class);
    static final StringBuilder builder = new StringBuilder();

    // deep search for property.key=value form of every nested field in object
    // that method allows you to add values from every model to System properties store ex. browser.name=chrome
    private static String convertToPropertiesForm(Object object, String propertyName) {
        Field[] allFields = object.getClass().getDeclaredFields();

        for (Field field : allFields) {
            try {
                field.setAccessible(true);

                String fieldName = field.getName();
                Object fieldValue = field.get(object);

                if (fieldValue == null) {
                    continue;
                }

                if (isCustomModel(fieldValue)) {
                    convertToPropertiesForm(field.get(object), fieldName);
                } else {
                    builder.append(propertyName)
                            .append('.')
                            .append(fieldName)
                            .append('=')
                            .append(fieldValue)
                            .append("\n");
                }

                field.setAccessible(false);
            } catch (IllegalAccessException ex) {
                logger.warn(String.valueOf(ex));
            }
        }
        return builder.toString();
    }

    public static boolean isCustomModel(Object obj) {
        return obj.getClass().getName().startsWith("models.");
    }

    public static String flattenObject(Object object, String propertyName) {
        String result = convertToPropertiesForm(object, propertyName);
        clearBuilder();
        return result;
    }

    private static void clearBuilder() {
        builder.setLength(0);
    }
}
