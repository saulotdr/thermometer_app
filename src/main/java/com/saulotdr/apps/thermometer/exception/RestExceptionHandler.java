package com.saulotdr.apps.thermometer.exception;

import com.saulotdr.apps.thermometer.schedule.WeatherBatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    @ExceptionHandler(RestValidationException.class)
    protected ResponseEntity<Object> handle(RuntimeException ex) {
        logger.info("RestValidationException: {}", ex.getMessage());
        return ResponseEntity.badRequest()
                .body(ex.getMessage());
    }
}