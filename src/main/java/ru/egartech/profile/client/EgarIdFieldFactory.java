package ru.egartech.profile.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class EgarIdFieldFactory {

    @Value("${fields.egar_id}")
    private String fieldId;
    private final String operator = "=";
    private String value;

    public EgarId of(String value) {
        return new EgarId(fieldId, value);
    }

    @RequiredArgsConstructor
    @Data
    public static class EgarId {
        @JsonProperty("field_id")
        private final String fieldId;
        private final String operator = "=";
        private final String value;
    }
}
