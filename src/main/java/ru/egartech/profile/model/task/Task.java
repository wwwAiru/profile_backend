package ru.egartech.profile.model.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.egartech.profile.model.task.deserializer.CustomFieldsDeserializer;
import ru.egartech.profile.model.task.value.CustomField;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("subtasks")
    private List<Task> subtasks = List.of();

    @JsonProperty("custom_fields")
    private List<CustomField<?>> customFields;


}
