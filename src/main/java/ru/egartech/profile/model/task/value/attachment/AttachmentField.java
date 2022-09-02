package ru.egartech.profile.model.task.value.attachment;

import lombok.Data;
import lombok.experimental.Accessors;
import ru.egartech.profile.error.exception.CustomFieldEmptyException;
import ru.egartech.profile.model.task.deserializer.FieldType;
import ru.egartech.profile.model.task.value.CustomField;

import java.util.List;

@Data
@Accessors(chain = true)
public class AttachmentField implements CustomField<List<Attachment>> {

    private String name;
    private FieldType type;
    private List<Attachment> value;

    public String getUrl() {
        return value.stream()
                .findFirst()
                .orElseThrow(() -> new CustomFieldEmptyException("Аватар"))
                .getUrl();
    }

}
