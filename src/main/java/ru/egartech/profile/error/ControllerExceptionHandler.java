package ru.egartech.profile.error;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.egartech.profile.error.exception.CustomFieldEmptyException;
import ru.egartech.profile.error.exception.NotFoundException;

import java.util.Locale;

@ControllerAdvice
@AllArgsConstructor
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleMissedException(Exception exception, WebRequest webRequest) {

        exception.printStackTrace();

        ErrorResponse errorResponse = new ErrorResponse(
                buildMessage("unknownerror", webRequest, exception.getClass().getSimpleName())
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(NotFoundException exception, WebRequest webRequest) {

        ErrorResponse errorResponse = new ErrorResponse(
                buildMessage("noegarid", webRequest, exception.getId())
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomFieldEmptyException.class)
    protected ResponseEntity<Object> handleCustomFieldEmptyException(CustomFieldEmptyException exception, WebRequest webRequest) {

        ErrorResponse errorResponse = new ErrorResponse(
                buildMessage("emptyfield", webRequest, exception.getField())
        );

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    private String buildMessage(String code, WebRequest request, Object... args) {
        return messageSource.getMessage(code, args, request.getLocale());
    }
}
