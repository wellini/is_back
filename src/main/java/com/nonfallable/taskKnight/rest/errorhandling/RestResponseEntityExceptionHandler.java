package com.nonfallable.taskKnight.rest.errorhandling;

import com.nonfallable.taskKnight.exceptions.ManagedException;
import com.nonfallable.taskKnight.exceptions.NotFoundException;
import com.nonfallable.taskKnight.security.ManagedSecurityException;
import com.nonfallable.taskKnight.rest.errorhandling.dto.ApiErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.ResponseEntity.ok;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    Logger logger = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    @ExceptionHandler(value = {ManagedException.class})
    public ResponseEntity<ApiErrorDTO> handleManagedException(ManagedException ex, WebRequest request) {
        logger.error("RestResponseEntityExceptionHandler", ex);
        return ok(new ApiErrorDTO().setMessage(ex.getExplanation()));
    }

    @ExceptionHandler(value = {ManagedSecurityException.class})
    public ResponseEntity<ApiErrorDTO> handleManagedException(ManagedSecurityException ex, WebRequest request) {
        logger.error("RestResponseEntityExceptionHandler", ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ApiErrorDTO().setMessage(ex.getExplanation()));
    }

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiErrorDTO> handleNotFoundException(NotFoundException ex, WebRequest request) {
        logger.error("RestResponseEntityExceptionHandler", ex);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorDTO().setMessage(ex.getExplanation()));
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ApiErrorDTO> handleRuntimeException(RuntimeException ex, WebRequest request) {
        logger.error("RestResponseEntityExceptionHandler", ex);
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorDTO().setMessage("Internal server error :("));
    }
}