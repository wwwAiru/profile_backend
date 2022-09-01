package ru.egartech.profile.model.task.value;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.egartech.profile.model.task.deserializer.FieldType;

@Data
@AllArgsConstructor
public class EmptyField implements CustomField<Object> {

    @Override
    public String getName() {
        return null;
    }

    @Override
    public FieldType getType() {
        return FieldType.EMPTY;
    }

    @Override
    public Object getValue() {
        return null;
    }
}
