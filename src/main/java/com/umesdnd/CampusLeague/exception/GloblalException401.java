package com.umesdnd.CampusLeague.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GloblalException401 {

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFoundError(NoHandlerFoundException ex) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", 401);
        errorResponse.put("error", "Ruta no encontrada");
        errorResponse.put("message", "La ruta solicitada no existe en el servidor");
        errorResponse.put("path", ex.getRequestURL());

        return new ResponseEntity<>(errorResponse, HttpStatus.PAYMENT_REQUIRED);
    }
}
