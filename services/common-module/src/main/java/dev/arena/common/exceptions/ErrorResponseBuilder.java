package dev.arena.common.exceptions;

import dev.arena.common.dto.ErrorResponseDTO;
import dev.arena.common.services.ErrorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponseBuilder {

    public static ResponseEntity<ErrorResponseDTO> build(Map<Class<? extends Exception>, HttpStatus> exceptionStatusMap, Exception exception, HttpServletRequest request) {

        HttpStatus status = exceptionStatusMap.getOrDefault(exception.getClass(), HttpStatus.INTERNAL_SERVER_ERROR);
        Map<String, String> errorMap = new HashMap<>();

        if (exception  instanceof MethodArgumentNotValidException) {
            for (FieldError fieldError : ((MethodArgumentNotValidException) exception).getBindingResult().getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
        } else if (exception  instanceof MethodArgumentTypeMismatchException) {
            errorMap.put(((MethodArgumentTypeMismatchException) exception).getName(), exception.getMessage());
        } else {
            errorMap.put("message", exception.getMessage());
        }

        ErrorResponseDTO errorResponseDTO = ErrorService.createErrorResponse(errorMap, status, request);

        return new ResponseEntity<>(errorResponseDTO, status);
    }
}
