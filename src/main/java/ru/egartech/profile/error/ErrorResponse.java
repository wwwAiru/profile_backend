package ru.egartech.profile.error;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class ErrorResponse {
    private final String message;
    private LocalDateTime timestamp = LocalDateTime.now();
}