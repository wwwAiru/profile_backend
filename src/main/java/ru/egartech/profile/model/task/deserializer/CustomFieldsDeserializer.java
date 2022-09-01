package ru.egartech.profile.model.task.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ru.egartech.profile.model.task.value.CustomField;
import ru.egartech.profile.model.task.value.EmptyField;
import ru.egartech.profile.model.task.value.UnknownField;
import ru.egartech.profile.model.task.value.attachment.Attachment;
import ru.egartech.profile.model.task.value.attachment.AttachmentField;
import ru.egartech.profile.model.task.value.collection.*;
import ru.egartech.profile.model.task.value.date.DateField;
import ru.egartech.profile.model.task.value.text.TextField;

import java.io.IOException;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CustomFieldsDeserializer extends JsonDeserializer<CustomField> {

    @Override
    public CustomField<?> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        TreeNode treeNode = jsonParser.readValueAsTree();

        if (
                treeNode.get("value") == null ||
                treeNode.get("name") == null
        ) {
            return new EmptyField();
        }

        String typeString = treeNode.get("type").toString().replaceAll("\"", "");
        String name = treeNode.get("name").toString().replaceAll("\"", "");

        FieldType type = FieldType.of(typeString);

        switch (type) {
            case LIST_RELATIONSHIP:
                break;
            case DROP_DOWN:
                return parseDropdownField(treeNode, name, type);
            case LABELS:
                return parseLabelsField(treeNode, name, type);
            case SHORT_TEXT:
            case PHONE:
            case EMAIL:
                return parseTextField(treeNode, name, type);
            case DATE:
                return parseDateField(treeNode, name, type);
            case URL:
                break;
            case ATTACHMENT:
                return parseAttachmentField(treeNode, name, type);
        }

        return new UnknownField()
                .setName(name)
                .setType(type)
                .setValue(treeNode.get("value").toString());
    }

    @SneakyThrows
    private AttachmentField parseAttachmentField(TreeNode treeNode, String name, FieldType type) {
        ObjectMapper mapper = new ObjectMapper();
        List<Attachment> values = mapper.readValue(treeNode.get("value").traverse(), new TypeReference<List<Attachment>>(){});

        return new AttachmentField()
                .setName(name)
                .setType(type)
                .setValue(values);
    }

    @SneakyThrows
    private LabelsField parseLabelsField(TreeNode treeNode, String name, FieldType type) {
        ObjectMapper mapper = new ObjectMapper();

        LabelTypeConfig typeConfig = mapper.readValue(treeNode.get("type_config").traverse(), LabelTypeConfig.class);
        Collection<String> values = mapper.readValue(treeNode.get("value").traverse(), new TypeReference<Collection<String>>() {});

        List<LabelOption> labelOptions = values.stream()
                .map(typeConfig::byLabelId)
                .collect(Collectors.toList());

        return new LabelsField()
                .setName(name)
                .setType(type)
                .setValue(labelOptions);
    }

    @SneakyThrows
    private DropdownField parseDropdownField(TreeNode treeNode, String name, FieldType type) {
        ObjectMapper mapper = new ObjectMapper();

        DropdownTypeConfig typeConfig = mapper.readValue(treeNode.get("type_config").traverse(), DropdownTypeConfig.class);
        int index = mapper.readValue(treeNode.get("value").traverse(), Integer.class);

        return new DropdownField()
                .setName(name)
                .setType(type)
                .setValue(typeConfig.byOrderIndex(index));
    }

    private DateField parseDateField(TreeNode treeNode, String name, FieldType type) {
        return new DateField()
                .setName(name)
                .setType(type)
                .setValue(
                        treeNode.get("value")
                                .toString()
                                .replaceAll("\"", "")
                );
    }

    private TextField parseTextField(TreeNode treeNode, String name, FieldType type) {
        return new TextField()
                .setName(name)
                .setType(type)
                .setValue(treeNode.get("value")
                        .toString()
                        .replaceAll("\"", "")
                );
    }
}
