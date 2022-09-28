package ru.egartech.profile.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.egartech.sdk.exception.ApplicationException;

@ResponseStatus(HttpStatus.NOT_FOUND)
@RequiredArgsConstructor
@Getter
public class MultipleTasksByEgarIdException extends ApplicationException {
    private final String egarId;
}
