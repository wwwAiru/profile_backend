package ru.egartech.profile.model.task.value;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.profile.model.task.deserializer.FieldType;

@Data
@Accessors(chain = true)
public class UnknownField implements CustomField<String> {

    private String name;
    private FieldType type;
    private String value;

}
