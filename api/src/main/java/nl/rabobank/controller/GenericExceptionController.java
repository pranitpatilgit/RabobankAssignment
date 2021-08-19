package nl.rabobank.controller;

import lombok.extern.slf4j.Slf4j;
import nl.rabobank.dto.ErrorResponse;
import nl.rabobank.exception.NotFoundException;
import nl.rabobank.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ControllerAdvice
public class GenericExceptionController {

    private static final String ERROR_RESP_TEXT = "Sending error response - ";

    @ExceptionHandler({NotFoundException.class, ValidationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody
    ErrorResponse handleException(RuntimeException exception) {
        log.error(ERROR_RESP_TEXT, exception);
        return new ErrorResponse(exception.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    ErrorResponse handleException(Exception exception) {
        log.error(ERROR_RESP_TEXT, exception);
        return new ErrorResponse(exception.getMessage());
    }
}
