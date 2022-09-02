package ru.egartech.profile.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.egartech.profile.error.exception.CustomFieldEmptyException;
import ru.egartech.profile.error.exception.NotFoundException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleMissedException(Exception exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), exception.getClass().getSimpleName());
        exception.printStackTrace();
        return new ResponseEntity<>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse("Сотрудника с таким EgarId не существует!", exception.getClass().getSimpleName());
        exception.printStackTrace();
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomFieldEmptyException.class)
    protected ResponseEntity<Object> handleCustomFieldEmptyException(CustomFieldEmptyException exception, WebRequest webRequest) {
        ErrorResponse errorResponse = new ErrorResponse("Нужное поле сотрудника отсутствует или пусто: " + exception.getField(), exception.getClass().getSimpleName());
        exception.printStackTrace();
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

}
