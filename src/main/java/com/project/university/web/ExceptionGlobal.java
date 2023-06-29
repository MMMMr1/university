package com.project.university.web;

import com.project.university.core.error.ExceptionErrorDTO;
import com.project.university.core.error.ExceptionListDTO;
import com.project.university.core.error.ExceptionStructuredDTO;
import com.project.university.core.exception.InvalidVersionException;
import com.project.university.core.exception.EntityNotFoundException;
import com.project.university.core.exception.ValidationUserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentConversionNotSupportedException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionGlobal  {

    //400 @Validated
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionListDTO> onMethodArgumentNotValidException(
            MethodArgumentNotValidException e) {
        List<ExceptionStructuredDTO> error = e.getBindingResult().getFieldErrors().stream()
                .map(s -> new ExceptionStructuredDTO(s.getField(), s.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionListDTO(error));
    }
    //    400
    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<List<ExceptionErrorDTO>>  ArgumentUserNotValidException(
            ValidationUserException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    @ExceptionHandler(value = {EntityNotFoundException.class })
    public ResponseEntity<List<ExceptionErrorDTO>>  ArgumentUserNotFoundException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    @ExceptionHandler(value = {InvalidVersionException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> ArgumentInvalidVersionException(
            RuntimeException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }

    //    500
    @ExceptionHandler()
    public ResponseEntity<List<ExceptionErrorDTO>> onHttpMessageNotReadableException(
            HttpMessageNotReadableException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
    @ExceptionHandler(value = {MethodArgumentTypeMismatchException.class,
            MethodArgumentConversionNotSupportedException.class})
    public ResponseEntity<List<ExceptionErrorDTO>> onArgumentTypeMismatchException(
            HttpMessageNotReadableException e ) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
    @ExceptionHandler
    public ResponseEntity<List<ExceptionErrorDTO>> handler(Throwable e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(List.of(new ExceptionErrorDTO(e.getMessage())));
    }
}