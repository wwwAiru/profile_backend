package ru.egartech.profile.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private String message;

    private String debug;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> errors;

    public ErrorResponse(String message) {
        this.message = message;
    }

    public ErrorResponse(String message, String debug) {
        this.message = message;
        this.debug = debug;
    }

    public ErrorResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }
}