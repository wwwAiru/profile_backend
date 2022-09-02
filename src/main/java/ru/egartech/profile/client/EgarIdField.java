package ru.egartech.profile.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EgarIdField {

    @JsonProperty("field_id")
    private final String fieldId = "5c6128d2-1c1f-420d-890a-e85afd61b123";
    private final String operator = "=";
    private final String value;


    public EgarIdField(String egarId) {
        this.value = egarId + "@egartech.ru";
    }
}
