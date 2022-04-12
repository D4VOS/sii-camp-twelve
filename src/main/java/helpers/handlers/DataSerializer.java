package helpers.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataSerializer extends FileHandler {
    private final ObjectMapper mapper;

    public DataSerializer(FormatMapper dataFormat) {
        this.mapper = dataFormat.getMapper();
    }

    public <T> T deserialize(String content, Class<T> targetType) {
        try {
            return mapper.readValue(content, targetType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> String serialize(T content) {
        try {
            return mapper.writeValueAsString(content);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public <T> T deserializeFile(String path, Class<T> targetType) {
        String content = readTextFile(path);
        return deserialize(content, targetType);
    }

    public <T> void serializeToFile(String path, T content) {
        String serializedContent = serialize(content);
        writeTextFile(serializedContent, path);
    }
}
