package ru.egartech.profile.model.task.value.date;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.profile.model.task.deserializer.FieldType;
import ru.egartech.profile.model.task.value.CustomField;

import java.time.LocalDate;
import java.util.Date;

@Data
@Accessors(chain = true)
public class DateField implements CustomField<String> {

    private String name;
    private FieldType type;
    private String value;

}
