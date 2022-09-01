package ru.egartech.profile.model.task.value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import ru.egartech.profile.model.task.deserializer.CustomFieldsDeserializer;
import ru.egartech.profile.model.task.deserializer.FieldType;

@JsonDeserialize(using = CustomFieldsDeserializer.class)
public interface CustomField<T> {

    String getName();
    FieldType getType();
    T getValue();

}
