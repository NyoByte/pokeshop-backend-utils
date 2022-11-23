package com.pokeshop.ecommerce.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pokeshop.ecommerce.util.ApiResponse;
import com.pokeshop.ecommerce.util.enumerado.EnumTypeException;
import feign.FeignException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;

@ControllerAdvice
@RestController
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<Object> handleFeignException(FeignException e) {
        e.printStackTrace();
        try {
            return new ResponseEntity<>(new ObjectMapper().readValue(e.contentUTF8(), ApiResponse.class), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (JsonProcessingException e1) {
            return new ResponseEntity<>(ApiResponse.error(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(java.lang.Exception.class)
    public final ResponseEntity<Object> manejarTodasExcepciones(java.lang.Exception ex, WebRequest request) {
        if (ex instanceof GenericException suex) {
            return new ResponseEntity<>(ApiResponse.error(ex.getMessage(), suex.getCodigo(), suex.getType()), HttpStatus.BAD_REQUEST);
        } else {
            ex.printStackTrace();
            return new ResponseEntity<>(ApiResponse.error(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(java.lang.Exception ex, WebRequest request) {
        return new ResponseEntity<>("Access denied message here", new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.error("Method Argument Not Valid", ex.getMessage(), EnumTypeException.E), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.error("Media Type Not Supported", ex.getMessage(), EnumTypeException.E),
                HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ApiResponse.error("Message Not Readable", ex.getMessage(), "500", EnumTypeException.E), HttpStatus.BAD_REQUEST);
    }

}