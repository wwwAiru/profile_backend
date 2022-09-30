package ru.egartech.profile.error;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import ru.egartech.profile.error.exception.PersonNotFoundException;
import ru.egartech.sdk.exception.customfield.CustomFieldNotFoundException;
import ru.egartech.sdk.exception.customfield.CustomFieldValueNotFoundException;
import ru.egartech.sdk.exception.dto.ApiErrorDto;
import ru.egartech.sdk.exception.handler.AbstractRestExceptionHandler;
import ru.egartech.sdk.util.MessageSourceUtils;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ControllerExceptionHandler extends AbstractRestExceptionHandler {
    private final MessageSourceUtils messageSource;

    public ControllerExceptionHandler(MessageSourceUtils messageSourceUtils) {
        super(messageSourceUtils);
        this.messageSource = messageSourceUtils;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ApiErrorDto handleMissedException(RuntimeException exception, WebRequest webRequest) {
        exception.printStackTrace();
        return buildMessage(messageSource, exception, webRequest, "unknownerror", exception.getLocalizedMessage());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiErrorDto handleNotFoundException(PersonNotFoundException exception, WebRequest webRequest) {
        exception.printStackTrace();
        return buildMessage(messageSource, exception, webRequest, "noegarid", exception.getId());
    }

    @ExceptionHandler(CustomFieldNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ApiErrorDto handleCustomFieldNotFoundException(CustomFieldNotFoundException exception, WebRequest webRequest) {
        exception.printStackTrace();
        return buildMessage(messageSource, exception, webRequest, "emptyfield", exception.getLocalizedMessage());
    }

    @ExceptionHandler(CustomFieldValueNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ApiErrorDto handleCustomFieldValueNotFoundException(CustomFieldValueNotFoundException exception, WebRequest webRequest) {
        exception.printStackTrace();
        return buildMessage(messageSource, exception, webRequest, "emptyfield", exception.getLocalizedMessage());
    }
}
