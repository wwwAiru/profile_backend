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
public class DropdownTypeConfig {

    @JsonProperty("options")
    private List<DropdownOption> labelOptions;

    public DropdownOption byOrderIndex(Integer orderIndex) {
        return labelOptions.stream().filter(o -> o.getOrderIndex() == orderIndex).findFirst().orElseThrow();
    }
}
