package ru.egartech.profile.model.task.value.collection;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.profile.model.task.deserializer.FieldType;
import ru.egartech.profile.model.task.value.CustomField;

import java.util.List;

@Data
@Accessors(chain = true)
public class DropdownField implements CustomField<DropdownOption> {

    private String name;
    private FieldType type;
    private DropdownOption value;

}
