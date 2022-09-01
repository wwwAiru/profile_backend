package ru.egartech.profile.model.task.value.collection;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class LabelTypeConfig {

    @JsonProperty("options")
    private List<LabelOption> labelOptions;

    public LabelOption byLabelId(String id) {
        return labelOptions.stream().filter(o -> o.getId().equals(id)).findFirst().orElseThrow();
    }
}
