package com.saga.ecommerce.web.filters;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.saga.ecommerce.core.domain.InvalidAddressCEPException;
import com.saga.ecommerce.core.usecase.exceptions.*;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionFilter extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CepNotFoundException.class, CustomerNotFoundException.class })
    public ResponseEntity<ErrorDto> onCEPNotFoundOrCustomerNotFound(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        String path = request.getRequestURI();
        var errorModel = new ErrorDto(HttpStatus.NOT_FOUND.value(), Instant.now(),
                "Not Found", exception.getMessage(), path);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
    }

    @ExceptionHandler({ InvalidAddressCEPException.class })
    public ResponseEntity<ErrorDto> onInvalidCEPFormat(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        String path = request.getRequestURI();
        var errorModel = new ErrorDto(HttpStatus.BAD_REQUEST.value(), Instant.now(),
                "Bad Request", exception.getMessage(), path);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ErrorDto> onAnyOtherException(Exception exception, HttpServletRequest request) {
        exception.printStackTrace();
        String path = request.getRequestURI();
        var errorModel = new ErrorDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), Instant.now(),
                "Internal Server Error", exception.getMessage(), path);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorModel);
    }
}
