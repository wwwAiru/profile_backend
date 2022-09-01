package ru.egartech.profile.model.task.value.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.profile.model.task.deserializer.FieldType;
import ru.egartech.profile.model.task.value.CustomField;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelsField implements CustomField<List<LabelOption>> {

    private String name;
    private FieldType type;
    private List<LabelOption> value;

}
