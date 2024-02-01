package com.colodev.demo.exception;

import com.colodev.demo.dto.ErrorDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice(annotations = RestController.class)
@Log4j2
class GlobalControllerExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDTO> handleResponseStatusException(ResponseStatusException ex) {
        log.error("response status exception", ex);
        ErrorDTO errorDto = new ErrorDTO(ex.getMessage());
        return new ResponseEntity<>(errorDto, ex.getStatusCode());
    }

    @ExceptionHandler(ToDoException.class)
    public ResponseEntity<ErrorDTO> handleToDoException(ToDoException ex) {
        log.error("todo custom exception", ex);
        ErrorDTO errorDto = new ErrorDTO(ex.getMessage());
        HttpStatus httpStatus = HttpStatus.resolve(ex.getStatus());
        return new ResponseEntity<>(errorDto, httpStatus);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleRuntimeException(RuntimeException ex) {
        log.error("internal server error", ex);
        ErrorDTO errorDto = new ErrorDTO("internal server error");
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
