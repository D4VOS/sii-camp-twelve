package helpers.handlers;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public enum FormatMapper {  // to support other data formats add value according to the schema: NEW_FORMAT(new NewFormatMapper()
    XML(new XmlMapper()),
    JSON(new JsonMapper()),
    YAML(new YAMLMapper());

    private final ObjectMapper mapper;

    FormatMapper(ObjectMapper mapper) {
        this.mapper = mapper;
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public ObjectMapper getMapper() {
        return mapper;
    }

    public String getExtension() {
        return '.' + name();
    }
}