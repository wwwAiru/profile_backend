package ru.egartech.profile.model.task.value.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class DropdownOption {

    private String id;
    private String name;
    private String color;

    @JsonProperty("orderindex")
    private int orderIndex;

}
