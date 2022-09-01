package ru.egartech.profile.model.task.value.text;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.profile.model.task.deserializer.FieldType;
import ru.egartech.profile.model.task.value.CustomField;

import java.lang.reflect.Field;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TextField implements CustomField<String> {

    private String name;
    private FieldType type;
    private String value;

}
