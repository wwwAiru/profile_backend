package ru.egartech.profile.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EgarIdField {

    @JsonProperty("field_id")
    private final String fieldId = "836c9684-0c71-4714-aff2-900b0ded0685";
    private final String operator = "=";
    private final String value;


    public EgarIdField(String egarId) {
        this.value = egarId;
    }
}
