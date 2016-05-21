package hu.bme.aut.vshelter.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class VirtualShelterValidationHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ObjectError>> validationHandler(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest()
                .body(e.getBindingResult().getAllErrors());
    }

}
