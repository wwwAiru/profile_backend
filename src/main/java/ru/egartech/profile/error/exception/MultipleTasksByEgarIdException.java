package ru.egartech.profile.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.egartech.sdk.exception.ApplicationException;

@RequiredArgsConstructor
@Getter
public class MultipleTasksByEgarIdException extends ApplicationException {
    private final String egarId;
}
