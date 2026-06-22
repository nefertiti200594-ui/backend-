package com.sara.backend;
import com.sara.backend.dto.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleValidationException(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });

        return errors;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponse handleResponseStatusException(
            ResponseStatusException ex

    ) {
        return new ErrorResponse(

                ex.getReason(),

                ex.getStatusCode().value()

        );
    }
    @ExceptionHandler(DataIntegrityViolationException.class)

    public ErrorResponse handleDuplicateEmail(

            DataIntegrityViolationException ex

    ) {

        return new ErrorResponse(

                "Bu email artıq mövcuddur",

                400

        );


    }


}